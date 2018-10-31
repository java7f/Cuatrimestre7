#include <iostream>
#include <mpi.h>


using namespace std;

int const MAT_SIZE_1 = 4;
int const MASTER_PROCESS = 0;

void fillMatrix(int size, int arr[MAT_SIZE_1][MAT_SIZE_1]){
    for(int i = 0; i < size; i++){
        for(int j = 0; j < size; j++){
            int value = rand() % 11;
            arr[i][j] = value;
        }
    }
}

void printMatrix(int size, int arr[MAT_SIZE_1][MAT_SIZE_1]){
    for(int i = 0; i < size; i++){
        for(int j = 0; j < size; j++){
            cout << arr[i][j] << " ";
        }
        cout << endl;
    }
    cout << endl;
}

int main(int argc, char** argv) {

    int rank, processes;
    double startTime, endTime;
    int lowerBound, upperBound;
    int portion;
    MPI_Status status;

    int fstMat[MAT_SIZE_1][MAT_SIZE_1];
    int secMat[MAT_SIZE_1][MAT_SIZE_1];
    int result[MAT_SIZE_1][MAT_SIZE_1] = {0};


    fillMatrix(MAT_SIZE_1, fstMat);
    //printMatrix(MAT_SIZE_1, fstMat);

    cout << endl;
    fillMatrix(MAT_SIZE_1, secMat);
    //printMatrix(MAT_SIZE_1, secMat);

    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &processes);

    if(rank == MASTER_PROCESS){
        startTime = MPI_Wtime();
        for(int i = 1; i < processes; i++){
            portion = (MAT_SIZE_1 / (processes - 1));
            lowerBound = (i - 1) * portion;
            upperBound = lowerBound + portion;

            MPI_Send(&lowerBound, 1, MPI_INT, i, 1, MPI_COMM_WORLD);
            MPI_Send(&upperBound, 1, MPI_INT, i, 1 + 1, MPI_COMM_WORLD);
            MPI_Send(&fstMat [lowerBound][0], (upperBound - lowerBound) * MAT_SIZE_1, MPI_INT, i, 1 + 2, MPI_COMM_WORLD);

            MPI_Bcast(&secMat, 1 * MAT_SIZE_1, MPI_INT, 0, MPI_COMM_WORLD);
        }
    }

    if (rank > MASTER_PROCESS) {
        MPI_Recv(&lowerBound, 1, MPI_INT, 0, 1, MPI_COMM_WORLD, &status);
        MPI_Recv(&upperBound, 1, MPI_INT, 0, 1 + 1, MPI_COMM_WORLD, &status);
        MPI_Recv(&fstMat[lowerBound][0], (upperBound - lowerBound) * MAT_SIZE_1, MPI_DOUBLE, 0, 1 + 2, MPI_COMM_WORLD, &status);
        for (int i = lowerBound; i <= upperBound; i++) {
            for (int j = 0; j < MAT_SIZE_1; j++) {
                for (int k = 0; k < MAT_SIZE_1; k++) {
                    result[i][j] += (fstMat[i][k] * secMat[k][j]);
                }
            }
        }

        MPI_Send(&lowerBound, 1, MPI_INT, 0, 1, MPI_COMM_WORLD);
        MPI_Send(&upperBound, 1, MPI_INT, 0, 1 + 1, MPI_COMM_WORLD);
        MPI_Send(&result[lowerBound][0], (upperBound - lowerBound) * MAT_SIZE_1, MPI_DOUBLE, 0, 1 + 2, MPI_COMM_WORLD);
    }

    if(rank == MASTER_PROCESS){
        for (int i = 1; i < processes; i++) {
            MPI_Recv(&lowerBound, 1, MPI_INT, i, 1, MPI_COMM_WORLD, &status);
            MPI_Recv(&upperBound, 1, MPI_INT, i, 1 + 1, MPI_COMM_WORLD, &status);
            MPI_Recv(&result[lowerBound][0], (upperBound - lowerBound) * MAT_SIZE_1, MPI_DOUBLE, i, 1 + 2, MPI_COMM_WORLD, &status);
        }
        endTime = MPI_Wtime();
        cout << "Fst Matrix" << endl;
        cout << endl;
        printMatrix(MAT_SIZE_1, fstMat);

        cout << "Scnd Matrix" << endl;
        cout << endl;
        printMatrix(MAT_SIZE_1, secMat);

        cout << "Result Matrix" << endl;
        cout << endl;
        printMatrix(MAT_SIZE_1, result);

        cout << "Running Time = " << endTime - startTime << endl;

    }

    MPI_Finalize();
    return 0;
}