#include <iostream>
#include <fstream>
#include <math.h>
#include <mpi.h>

using namespace std;

//Convierte a número real.
double toReal(int num, int width, double minR, double maxR);
//Convierte a número imaginario.
double toImaginary(int num, int height, double minI, double maxI);
// Función que retorna el número de iteraciones que toma hacer el cálculo del píxel.
int calcMandelbrot(double complexR, double complexI, int iterations);
//Mueve la matriz resultado al archivo .ppm
void mandelbrotPPM();

//Tags
int const MASTER_PROCESS = 0;
int const MASTER_TO_CHILD = 1;

//Constantes para la especificación de los datos del archivo .ppm
int const WIDTH = 5000;
int const HEIGHT = 5000;
int const ITERATIONS = 600;
int const STRIPS = 200;
int const MIN_R = -2;
int const MAX_R = 2;
int const MIN_I = -2;
int const MAX_I = 2;

//Matriz resultante que contendrá el número de iteraciones devuelto por la función calcMandelbrot.
double resultMatrix[WIDTH][HEIGHT] = {{0.0}};

int main(int argc, char *argv[]) {

    int nPes, myRank, n;
    int stripsPartition, stripsPerProcess, startingStrip, endStrip;
    double complexR, complexI;
    double startTime, endTime, resultTime;
    MPI_Status status;

    //Inicialización
    MPI_Init(&argc, &argv);

    MPI_Comm_size(MPI_COMM_WORLD, &nPes);
    MPI_Comm_rank(MPI_COMM_WORLD, &myRank);

    if(myRank == 0)
    {
        startTime = MPI_Wtime(); //Comienzo de la medición del tiempo.
        stripsPartition = (HEIGHT/STRIPS); //Cálculo de la cantidad de bandas en las que se dividirá el archivo.
        stripsPerProcess = STRIPS / (nPes - 1); //Cálculo de la cantidad de bandas que calculará cada proceso.


        /*Particionando y enviando las tareas de cada proceso.*/
        for (int i = 1; i < nPes; i++)
        {
            startingStrip = (i - 1) * (stripsPerProcess * stripsPartition); //Asignación de la fila de punto de partida.
            endStrip = startingStrip + (stripsPerProcess * stripsPartition); //Asignación de la fila tope

            //El proceso maestro envía las particiones a cada hijo.
            MPI_Send(&startingStrip, 1, MPI_INT, i, MASTER_TO_CHILD, MPI_COMM_WORLD);
            MPI_Send(&endStrip, 1, MPI_INT, i, MASTER_TO_CHILD+1, MPI_COMM_WORLD);
        }
    }

    if(myRank > 0)
    {
        //Recibiendo los datos a computar del proceso maestro.
        MPI_Recv(&startingStrip, 1, MPI_INT, MASTER_PROCESS, MASTER_TO_CHILD, MPI_COMM_WORLD, &status);
        MPI_Recv(&endStrip, 1, MPI_INT, MASTER_PROCESS, MASTER_TO_CHILD+1, MPI_COMM_WORLD, &status);

        /*
         * Ciclo que llena la matriz de los resultados arrojados por calcMandelbrot
         */
        for(int i = startingStrip; i < endStrip; i++)
        {

            for(int j = 0; j<WIDTH; j++)
            {
                complexR = toReal(j, WIDTH, MIN_R, MAX_R);
                complexI = toImaginary(i, HEIGHT, MIN_I, MAX_I);

                n = calcMandelbrot(complexR, complexI, ITERATIONS);

                resultMatrix[i][j] = n;
            }

            cout<<"calculating...";
            system("clear");

        }

        //Devolviendo los resultados computados por cada proceso.
        MPI_Send(&startingStrip, 1, MPI_INT, MASTER_PROCESS, MASTER_TO_CHILD, MPI_COMM_WORLD);
        MPI_Send(&endStrip, 1, MPI_INT, MASTER_PROCESS, MASTER_TO_CHILD+1, MPI_COMM_WORLD);
        MPI_Send(&resultMatrix[startingStrip][0], (endStrip - startingStrip) * WIDTH, MPI_DOUBLE, MASTER_PROCESS, MASTER_TO_CHILD+2, MPI_COMM_WORLD);

    }

    if(myRank == MASTER_PROCESS) {
        //Recibiendo los resultados de los procesos que calculan.
        for (int i = 1; i < nPes; i++) {
            MPI_Recv(&startingStrip, 1, MPI_INT, i, MASTER_TO_CHILD, MPI_COMM_WORLD, &status);
            MPI_Recv(&endStrip, 1, MPI_INT, i, MASTER_TO_CHILD + 1, MPI_COMM_WORLD, &status);
            MPI_Recv(&resultMatrix[startingStrip][0], (endStrip - startingStrip) * WIDTH, MPI_DOUBLE, i, MASTER_TO_CHILD + 2, MPI_COMM_WORLD, &status);
        }

        endTime = MPI_Wtime(); //Finalización de la toma de tiempo.
        resultTime = endTime - startTime;

        //Se llena el archivo.
        mandelbrotPPM();

        std::cout<<"\n Execution Time: " <<resultTime<< std::endl;std::cout<<"\n Execution Time: " <<resultTime<< std::endl;
    }

    MPI_Finalize(); //Cerrar
    return 0;
}

double toReal(int num, int width, double minR, double maxR)
{
    double range = maxR-minR;

    return num*(range/width) + minR;

}

double toImaginary(int num, int height, double minI, double maxI)
{
    double range = maxI-minI;

    return num*(range/height) +minI;

}

int  calcMandelbrot(double complexR, double complexI, int iterations)
{
    int i = 0;
    double t;

    double cr = 0.0, ci = 0.0;

    for(i = 0; i<iterations; i++)
    {
        t = cr*cr - ci*ci + complexR;
        ci = 2.0 * cr * ci + complexI;
        cr = t;
        i++;

        if(cr*cr + ci*ci >= 4.0)
            break;
    }
    return i;

}

void mandelbrotPPM()
{
    ofstream fout("pgm_fractal.ppm");
    fout <<"P3"<<endl; //magic number
    fout <<WIDTH <<" "<<HEIGHT <<endl;
    fout <<500<<endl;
    int r,g,b;

    //Impresión de la matriz resultante en el archivo luego de calcular el color del píxel.
    for (int i = 0; i < HEIGHT; i++) {
        for (int j= 0; j < WIDTH; j++) {

            /**
             * change the colors!
             */
            r = (int)(resultMatrix[i][j]*3.14)%500;
            g = (int)(resultMatrix[i][j])%500;
            b = (int)(resultMatrix[i][j])%500;

            fout<<r<<" "<<g<<" "<<b<<"   ";


        }
        fout<<endl;
       	cout<<((double)i/(double)HEIGHT)*100.0<<endl;
    }

    fout.close(); //Cerrar el archivo.
}