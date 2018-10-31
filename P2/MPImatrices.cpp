#include <iostream>
#include <mpi.h>

/*
 * main.cpp
 *
 *  Created on: Oct 27, 2018
 *  Author: Javier
 */

int const MASTER_PROCESS = 0;
int const MASTER_TO_CHILD = 1;
int const CHILD_TO_MASTER = 4;


int main(int argc, char *argv[]) 
{
	int size = 60;
    int nPes, myRank;
    int rowsPerProcess, startingRow, endRow;
    int sendSize;
    double firstMatrix[size][size], scndMatrix[size][size];
    double resultMatrix[size][size] = {{0}};
    MPI_Status status;


    double startTime, endTime, resultTime;

    //Inicialización
    MPI_Init(&argc, &argv);

    MPI_Comm_size(MPI_COMM_WORLD, &nPes);
    MPI_Comm_rank(MPI_COMM_WORLD, &myRank);

    if(myRank == MASTER_PROCESS) //Si es el proceso maestro, se realiza la partición
    {
    	startTime = MPI_Wtime(); //Comienzo de la medición del tiempo.

        /*Ciclo de llenado de las matrices.*/
        for (int i=0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                firstMatrix[i][j] = rand() %100;
                scndMatrix[i][j] =  rand()%100;
            }
        }

        

        /*Particionando y enviando las tareas de cada proceso.*/
        for(int i=1; i < nPes; i++) {
        	rowsPerProcess = (size/(nPes-1)); //Cálculo de la cantidad de filas a ejecutar por cada proceso.
            startingRow= (i - 1) * rowsPerProcess; //Asignación de la fila de punto de partida.

            //Asignación de la fila tope.
            if ((i+1 == nPes) && (size % (nPes - 1)) != 0) {
                endRow = size;
            } else {
                endRow = startingRow + rowsPerProcess;
            }

            //El proceso maestro envía las particiones a cada hijo.
            MPI_Send(&startingRow, 1, MPI_INT, i, MASTER_TO_CHILD, MPI_COMM_WORLD);
            MPI_Send(&endRow, 1, MPI_INT, i, MASTER_TO_CHILD+1, MPI_COMM_WORLD);
            MPI_Send(&firstMatrix[startingRow][0], (endRow - startingRow) * size, MPI_DOUBLE, i, MASTER_TO_CHILD+2, MPI_COMM_WORLD);
        }

    }

    //Se distribuye la segunda matriz para todos los procesos.
    MPI_Bcast(&scndMatrix, size*size, MPI_DOUBLE, MASTER_PROCESS, MPI_COMM_WORLD);

    /*Si es un proceso hijo, se computa la multiplicación*/
    if(myRank > MASTER_PROCESS) {

        //Recibiendo los datos a computar del proceso maestro.
        MPI_Recv(&startingRow, 1, MPI_INT, MASTER_PROCESS, MASTER_TO_CHILD, MPI_COMM_WORLD, &status);
        MPI_Recv(&endRow, 1, MPI_INT, MASTER_PROCESS, MASTER_TO_CHILD+1, MPI_COMM_WORLD, &status);
        MPI_Recv(&firstMatrix[startingRow][0], (endRow - startingRow) * size, MPI_DOUBLE, MASTER_PROCESS, MASTER_TO_CHILD+2, MPI_COMM_WORLD, &status);

        //Multiplicar.
        for(int i = startingRow ; i < endRow ; i++)
        {
            for(int j = 0 ; j < size ; j++)
            {
                for(int k = 0 ; k < size ; k++)
                {
                    resultMatrix[i][j] += (firstMatrix[i][k] * scndMatrix[k][j]);
                }

            }
            
        }

        //Devolviendo los resultados computados por cada proceso.
        MPI_Send(&startingRow, 1, MPI_INT, MASTER_PROCESS, MASTER_TO_CHILD, MPI_COMM_WORLD);
        MPI_Send(&endRow, 1, MPI_INT, MASTER_PROCESS, MASTER_TO_CHILD+1, MPI_COMM_WORLD);
        MPI_Send(&resultMatrix[startingRow][0], (endRow - startingRow) * size, MPI_DOUBLE, MASTER_PROCESS, MASTER_TO_CHILD+2, MPI_COMM_WORLD);

    }

    if(myRank == MASTER_PROCESS)
    {
        //Recibiendo los resultados de los procesos que calculan.
        for(int i=1; i < nPes; i++)
        {
            MPI_Recv(&startingRow, 1, MPI_INT, i, MASTER_TO_CHILD, MPI_COMM_WORLD, &status);
            MPI_Recv(&endRow, 1, MPI_INT, i, MASTER_TO_CHILD+1, MPI_COMM_WORLD, &status);
            MPI_Recv(&resultMatrix[startingRow][0], (endRow - startingRow) * size, MPI_DOUBLE, i, MASTER_TO_CHILD+2, MPI_COMM_WORLD, &status);
        }
        endTime = MPI_Wtime(); //Finalización de la toma de tiempo.

        resultTime = endTime - startTime;

        //Impresión de la matriz resultante.
        for (int i = 0; i < size; i++) {
        std::cout << "\n";
        for (int j= 0; j < size; j++)
            std::cout << resultMatrix[i][j]<< "\t";
    	}

		std::cout<<"\n Execution Time: " <<resultTime<< std::endl;

    }

    MPI_Finalize(); //Cerrar
    return 0;
}
