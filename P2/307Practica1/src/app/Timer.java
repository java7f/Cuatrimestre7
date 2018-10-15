package app;

public class Timer 
{
	
	protected long startTime;
	protected long endTime;
	
	/**
	 * Constructor - Se inicializa el tiempo de inicio y fin en 0
	 */
	public Timer()
	{
		startTime = 0;
		endTime = 0;
	}
	
	/**
	 * Se asigna el tiempo de inicio
	 */
	public void timerStart()
	{
		startTime = (long)System.nanoTime();
	}
	
	/**
	 * Se asigna el tiempo de termino
	 */
	public void timerEnd()
	{
		endTime = (long)System.nanoTime();
	}
	
	/**
	 * Se calcula y retorna la diferencia de tiempo que hubo entre startTime y endTime
	 * @return
	 */
	public long getTime()
	{
		return endTime-startTime;
	}
	
	/**
	 * Se reinicia el tiempo de inicio y fin en 0
	 */
	public void resetTimer()
	{
		startTime = 0;
		endTime = 0;
	}
	
	
	

}
