/*
 * main.cpp
 *
 *  Created on: Oct 24, 2018
 *      Author: Javier
 */
#include <mpi.h>
#include <iostream>

int const MASTER_PROCESS = 0;


int main(int argc, char *argv[])
{
    int nPes, myRank;
    int message = 45;
    int prevProcess, nextProcess;
    MPI_Status status;

    MPI_Init(&argc, &argv);

    MPI_Comm_size(MPI_COMM_WORLD, &nPes);
    MPI_Comm_rank(MPI_COMM_WORLD, &myRank);

    prevProcess = nPes -1;

    if(myRank == MASTER_PROCESS)
    {
        MPI_Send(&message, 1, MPI_INT, 1, 0, MPI_COMM_WORLD);
        std::cout << "El proceso maestro hace el primer envio \n";


        MPI_Recv(&message, 1, MPI_INT, prevProcess, 0, MPI_COMM_WORLD, &status);
        std::cout << "El proceso maestro recibe " << message << " del proceso " << prevProcess << "\n";
    }
    else
    {
        prevProcess = myRank -1;
        nextProcess = (myRank + 1) % nPes;

        MPI_Recv(&message, 1, MPI_INT, prevProcess, 0, MPI_COMM_WORLD, &status);

        std::cout << "El proceso " << myRank << " recibió " << message << " del proceso " << status.MPI_SOURCE << "\n";

        message *= myRank;

        MPI_Send(&message, 1, MPI_INT, nextProcess, 0, MPI_COMM_WORLD);

        std::cout << "El proceso " << myRank << " envió " << message << " al proceso " << nextProcess << "\n";

    }

    MPI_Finalize();
}
