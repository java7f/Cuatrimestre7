
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IncrementThreads threadArray[] = new IncrementThreads[10];
		Counter counter = new Counter();
		
		for(int i=0; i< threadArray.length; i++)
		{
			threadArray[i] = new IncrementThreads(counter);
		}
		
		for(int i=0; i< threadArray.length; i++)
		{
			threadArray[i].start();
		}

	}

}
