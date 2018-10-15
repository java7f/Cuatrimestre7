package base;
import base.Matrix;
import java.io.PrintWriter;

import app.Timer;

public class MatrixTimeRegister 
{
	
	protected PrintWriter writer;
	private Timer timer = new Timer();
	
	/**
	 * Constructor
	 */
	public MatrixTimeRegister()
	{
		
	}
	
	/**
	 * Se ocupa de registrar el tiempo de ejecucion de la multiplicacion en un archivo de texto al igual que
	 * la dimension de la matriz
	 * @param firstMatrix
	 * @param secondMatrix
	 * @return
	 */
	public void registerTime(Matrix firstMatrix, Matrix secondMatrix)
	{
		
		timer.timerStart();
		firstMatrix.multiply(secondMatrix);
		timer.timerEnd();
		writer.println(firstMatrix.getRowDimension() + "x" + firstMatrix.getColumnDimension()
						+ "             " + timer.getTime());
		
	}
	
	
	/**
	 * Inicializa el PrintWriter con el archivo especificado
	 * @param file
	 */
	public void openWriter(String file) 
	{
		try 
		{
			writer = new PrintWriter(file, "UTF-8");
		}
		catch(Exception e)
		{
			System.out.println("Could not find file!");
		}
	}
	
	/**
	 * cierra el PrintWriter
	 */
	public void closeWriter()
	{
		writer.close();
	}

}
