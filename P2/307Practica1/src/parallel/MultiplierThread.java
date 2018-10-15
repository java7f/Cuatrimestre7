package parallel;

import base.Matrix;

public class MultiplierThread extends Thread 
{

	private int[] rowVector;
	private Matrix matrixB;
	private int[] result;
	
	/**
	 * Constructor parametrizado 
	 * 
	 * @param rowVector
	 * @param matrixB
	 */
	public MultiplierThread(int[] rowVector, Matrix matrixB)
	{
		this.setRowVector(rowVector);
		this.setMatrixB(matrixB);
		result = new int[rowVector.length];
	}

	public int[] getRowVector() {
		return rowVector;
	}

	public void setRowVector(int[] rowVector) {
		this.rowVector = rowVector;
	}

	public Matrix getMatrixB() {
		return matrixB;
	}

	public void setMatrixB(Matrix matrixB) {
		this.matrixB = matrixB;
	}

	public int[] getResult() {
		return result;
	}

	public void setResult(int[] result) {
		this.result = result;
	}
	
	/**
	 * 
	 */
	public void run()
	{
		int[][] matrixBData = matrixB.getData();
		int elements = matrixB.getRowDimension();
		for(int i=0; i<elements; i++)
		{
			for (int j = 0; j < elements; j++)
			{
				result[i] += rowVector[j] * matrixBData[j][i];
			}
		}
	}
	
	
}
