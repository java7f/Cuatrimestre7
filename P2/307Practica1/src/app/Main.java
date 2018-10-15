package app;
import base.Matrix;
import sequential.SequentialMatrix;
import base.MatrixTimeRegister;
import parallel.ParallelMatrix;

public class Main {

	public static void main(String[] args) {
		
		//se crea el timer que se va a encargar de medir el tiempo de ejecucion
		MatrixTimeRegister timeRegister = new MatrixTimeRegister();
		
		//se crean las matrices a partir de los valores en los archivos especificados
		Matrix x100A = new SequentialMatrix(100, 100, "100x100A.txt");
		Matrix x100B = new SequentialMatrix(100, 100, "100x100B.txt");
		Matrix x200A = new SequentialMatrix(200, 200, "200x200A.txt");
		Matrix x200B = new SequentialMatrix(200, 200, "200x200B.txt");
		Matrix x300A = new SequentialMatrix(300, 300, "300x300A.txt");
		Matrix x300B = new SequentialMatrix(300, 300, "300x300B.txt");
		Matrix x400A = new SequentialMatrix(400, 400, "400x400A.txt");
		Matrix x400B = new SequentialMatrix(400, 400, "400x400B.txt");
		Matrix x500A = new SequentialMatrix(500, 500, "500x500A.txt");
		Matrix x500B = new SequentialMatrix(500, 500, "500x500B.txt");
		Matrix x600A = new SequentialMatrix(600, 600, "600x600A.txt");
		Matrix x600B = new SequentialMatrix(600, 600, "600x600B.txt");
		Matrix x700A = new SequentialMatrix(700, 700, "700x700A.txt");
		Matrix x700B = new SequentialMatrix(700, 700, "700x700B.txt");
		Matrix x800A = new SequentialMatrix(800, 800, "800x800A.txt");
		Matrix x800B = new SequentialMatrix(800, 800, "800x800B.txt");
		Matrix x900A = new SequentialMatrix(900, 900, "900x900A.txt");
		Matrix x900B = new SequentialMatrix(900, 900, "900x900B.txt");
		
		Matrix p100A = new ParallelMatrix(100, 100, "100x100A.txt");
		Matrix p100B = new ParallelMatrix(100, 100, "100x100B.txt");
		Matrix p200A = new ParallelMatrix(200, 200, "200x200A.txt");
		Matrix p200B = new ParallelMatrix(200, 200, "200x200B.txt");
		Matrix p300A = new ParallelMatrix(300, 300, "300x300A.txt");
		Matrix p300B = new ParallelMatrix(300, 300, "300x300B.txt");
		Matrix p400A = new ParallelMatrix(400, 400, "400x400A.txt");
		Matrix p400B = new ParallelMatrix(400, 400, "400x400B.txt");
		Matrix p500A = new ParallelMatrix(500, 500, "500x500A.txt");
		Matrix p500B = new ParallelMatrix(500, 500, "500x500B.txt");
		Matrix p600A = new ParallelMatrix(600, 600, "600x600A.txt");
		Matrix p600B = new ParallelMatrix(600, 600, "600x600B.txt");
		Matrix p700A = new ParallelMatrix(700, 700, "700x700A.txt");
		Matrix p700B = new ParallelMatrix(700, 700, "700x700B.txt");
		Matrix p800A = new ParallelMatrix(800, 800, "800x800A.txt");
		Matrix p800B = new ParallelMatrix(800, 800, "800x800B.txt");
		Matrix p900A = new ParallelMatrix(900, 900, "900x900A.txt");
		Matrix p900B = new ParallelMatrix(900, 900, "900x900B.txt");
		
		
		//se le indica en que archivo va a guardar los tiempos
		timeRegister.openWriter("time_register.txt");
		
		timeRegister.registerTime(x100A, x100B);
		System.out.println("Done 100x100");
		timeRegister.registerTime(x200A, x200B);
		System.out.println("Done 200x200");
		timeRegister.registerTime(x300A, x300B);
		System.out.println("Done 300x300");
		timeRegister.registerTime(x400A, x400B);
		System.out.println("Done 400x400");
		timeRegister.registerTime(x500A, x500B);
		System.out.println("Done 500x500");
		timeRegister.registerTime(x600A, x600B);
		System.out.println("Done 600x600");
		timeRegister.registerTime(x700A, x700B);
		System.out.println("Done 700x700");
		timeRegister.registerTime(x800A, x800B);
		System.out.println("Done 800x800");
		timeRegister.registerTime(x900A, x900B);
		System.out.println("Done 900x900");

		timeRegister.registerTime(p100A, p100B);
		System.out.println("Done 100x100");
		timeRegister.registerTime(p200A, p200B);
		System.out.println("Done 200x200");
		timeRegister.registerTime(p300A, p300B);
		System.out.println("Done 300x300");
		timeRegister.registerTime(p400A, p400B);
		System.out.println("Done 400x400");
		timeRegister.registerTime(p500A, p500B);
		System.out.println("Done 500x500");
		timeRegister.registerTime(p600A, p600B);
		System.out.println("Done 600x600");
		timeRegister.registerTime(p700A, p700B);
		System.out.println("Done 700x700");
		timeRegister.registerTime(p800A, p800B);
		System.out.println("Done 800x800");
		timeRegister.registerTime(p900A, p900B);
		System.out.println("Done 900x900");
		
		
		timeRegister.closeWriter();
		System.out.println("Done");
	}

}
