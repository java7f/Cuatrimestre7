package sequential;

import base.Matrix;

public class SequentialMatrix extends Matrix 
{
	
	public SequentialMatrix(int rDimension, int cDimension) 
	{
		super(rDimension, cDimension);
		// TODO Auto-generated constructor stub
	}
	
	public SequentialMatrix(int rDimension, int cDimension, int[][] mData) 
	{
		super(rDimension, cDimension, mData);
		// TODO Auto-generated constructor stub
	}
	
	public SequentialMatrix(int rDimension, int cDimension, String file)
	{
		super(rDimension, cDimension, file);
	}

	/**
	 * Multiplica secuencialmente la matriz con otra pasada por parametro
	 * @param secondMatrix
	 */
	@Override
	public Matrix multiply(Matrix secondMatrix) 
	{
		
		int[][]  temp= new int[this.columnDimension][secondMatrix.getRowDimension()];
		 
		
		int n = this.rowDimension;
		int[][] fstMatrix = this.matrixData;
		int[][] scdMatrix = secondMatrix.getData();
		
		
		for(int i = 0 ; i < n ; i++) 
		{
			for(int j = 0 ; j < n ; j++) 
			{
				for(int k = 0 ; k < n ; k++) 
				{
					temp[i][j] += fstMatrix[i][k] * scdMatrix[k][j];
					
				}
			}
		}
	
		return new SequentialMatrix(this.rowDimension, this.columnDimension, temp);
	}
	
	

}