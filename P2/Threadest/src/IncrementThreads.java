
public class IncrementThreads extends Thread{
	
	Counter threadCounter;
	
	public IncrementThreads(Counter tCounter)
	{
		threadCounter = tCounter;
	}
	
	public void run()
	{
		int incValue = threadCounter.getAndIncrement();
		System.out.println(incValue);
	}
	
	
}
