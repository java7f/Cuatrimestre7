package base;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import app.Timer;

/**
 * @author Manuel
 *
 */
public abstract class Matrix 
{
	protected int[][] matrixData;
	protected int columnDimension;
	protected int rowDimension;
	protected static Random rObject;
	protected static final int RANDOM_SCALE = 1000;
	
	/**
	 * Default constructor
	 */
	public Matrix()
	{
	}
	
	/**
	 * Constructor #1 - El contenido de la matriz se genera aleatoriamente
	 * @param rDimension
	 * @param cDimension
	 */
	public Matrix(int rDimension, int cDimension)
	{
		if (rObject == null) 
			rObject = new Random(System.currentTimeMillis());
		this.rowDimension = rDimension;
		this.columnDimension = cDimension;
		
		matrixData = new int[this.rowDimension][this.columnDimension];
				
		for(int i = 0; i < rowDimension; i++)
		{
			for(int j = 0; j < columnDimension; j++)
			{
				matrixData[i][j] = rObject.nextInt() % RANDOM_SCALE;
			
			}//end-for(1)
		
		}//end-for(2)
	}
	
	/**
	 * Constructor #2 - Se asume que el contenido de la matriz se pasa como parametro
	 * @param rDimension
	 * @param cDimension
	 * @param mData
	 */
	public Matrix(int rDimension, int cDimension, int[][] mData)
	{
		this.rowDimension = rDimension;
		this.columnDimension = cDimension;
		this.matrixData = mData;
	}
	
	/**
	 * Constructor #3 - Se le asigna valores leidos de un archivo especificado a la matriz
	 * @param rDimension
	 * @param cDimension
	 * @param file
	 */
	public Matrix(int rDimension, int cDimension, String file)
	{
		
		this.rowDimension = rDimension;
		this.columnDimension = cDimension;
		
		matrixData = new int[this.rowDimension][this.columnDimension];
		
		Scanner scanner = null;
		
		try 
		{
			scanner = new Scanner(new File(file));
		}
		catch(Exception e)
		{
			System.out.println("Could not find file!");
		}
		
		
		for(int i = 0; i < rDimension; i++)
		{
			for(int j = 0; j < cDimension; j++)
			{
				matrixData[i][j] = scanner.nextInt();
			}
		}
		
	}
	
	
	public int[][] getData()
	{
		return this.matrixData.clone();
	}
	
	
	
	/**
	 * Se accede a una entrada en particular de la matriz
	 * 
	 * @param rowPosition
	 * @param columnPosition
	 * @return
	 */
	public int getEntry(int rowPosition, int columnPosition)
	{
		return this.matrixData[rowPosition][columnPosition];
	}
	
	/**
	 * Se obtiene una copia del vector fila cuyo indice se pasa como parametro
	 * @param i
	 * @return
	 */
	public int[] getRowVector(int i)
	{
		return this.matrixData[i].clone();
	}
	
	public int[] getColumnVector(int i)
	{
		int[] columnVector = new int[this.columnDimension];
		
		for(int k = 0; k < this.rowDimension; k++)
			columnVector[k] = matrixData[k][i];
		
		return columnVector.clone();
	}
	
	/**
	 * Devuelve el numero de filas 
	 * @return
	 */
	public int getRowDimension()
	{
		return this.rowDimension;
	}
	
	/**
	 * Devuelve el numero de columnas
	 * @return
	 */
	public int getColumnDimension()
	{
		return this.columnDimension;
	}
	
	public String toString()
	{		
		StringBuffer returnValue = new StringBuffer();
		
		for(int i = 0; i < this.rowDimension; i++)
		{
			returnValue.append(Arrays.toString(this.matrixData[i]) + "\n");
		}
		
		return returnValue.toString();
	}
	
	/**
	 * Declaracion abstracta del metodo cuya implementacion sera 
	 * el algoritmo de multiplicacion de matrices
	 * 
	 * @param secondMatrix
	 * @return
	 */
	public abstract Matrix multiply(Matrix secondMatrix);
	

}