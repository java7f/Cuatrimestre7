
public class Counter {
	
	
	private int value;
	
	public Counter()
	{
		value =0;
	}
	
	
	public int getAndIncrement() 
	{
		int temp;
		synchronized (this) {
			temp = value;
			value = value + 1;
			
		}
		return temp;
	}
}

