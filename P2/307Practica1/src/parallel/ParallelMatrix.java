/**
 * 
 */
package parallel;

import base.Matrix;

/**
 * @author Manuel
 *
 */
public class ParallelMatrix extends Matrix {
	
	

	/* (non-Javadoc)
	 * @see base.Matrix#multiply(base.Matrix)
	 */
	
	/**
	 * Constructor parametrizado
	 * 
	 * @param rDimension
	 * @param cDimension
	 */
	public ParallelMatrix(int rDimension, int cDimension)
	{
		super(rDimension, cDimension);
		//this.multiplierThreads = new MultiplierThread[rDimension];
	}
	
	public ParallelMatrix(int rDimension, int cDimension, String file) {
		// TODO Auto-generated constructor stub
		super(rDimension, cDimension, file);
		//this.multiplierThreads = new MultiplierThread[rDimension];
	}
	
	public ParallelMatrix(int rDimension, int cDimension, int[][] mData) {
		// TODO Auto-generated constructor stub
		super(rDimension, cDimension, mData);
		//this.multiplierThreads = new MultiplierThread[rDimension];
	}
	
	
	@Override
	public Matrix multiply(Matrix secondMatrix) 
	{
		int[][] resultMatrix= new int[rowDimension][columnDimension];
		int matrixA[][] = this.matrixData;
 		MultiplierThread[] multiplierThreads = new MultiplierThread[rowDimension];

		for(int i = 0; i < rowDimension; i++)
		{
			multiplierThreads[i] = new MultiplierThread(matrixA[i], secondMatrix);
			multiplierThreads[i].start();
			
		}
		
		for(int i=0; i< rowDimension; i++)
		{
			resultMatrix[i]= multiplierThreads[i].getResult(); 
			try {
				multiplierThreads[i].join();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return new ParallelMatrix(rowDimension, columnDimension, resultMatrix);
	}

}
