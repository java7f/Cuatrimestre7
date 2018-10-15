/**
 * @file TestPolynomial.java
 * @brief Fuente de la aplicación TestPolynomial
 */
package apps;
import algos.RealPolynomial;

/**
 * Aplicación que crea dos polinomios de prueba, los suma
 * y escribe el resultado de la suma.
 * @author Rodrigo Orizondo
 * @todo Completar según lo indicado en los comentarios.
 */
public class TestPolynomial {

	/**
	 * Datos del primer polinomio.
	 */
	static RealPolynomial p;
	static double[] coefsP= {1, 2, 3.1416, 4};
	
	/**
	 * Segundo polinomio.
	 */
	static RealPolynomial q;
	
	/**
	 * Polinomio suma de p y q.
	 */
	static RealPolynomial s;
	
	/**
	 * Punto de entrada de la aplicación.
	 * @param args No se emplean.
	 * @todo Completar el código. Orientarse por los comentarios presentes.
	 */
	public static void main(String[] args) {
		System.out.println("Prueba de la clase RealPolynomial.");
		System.out.println();
		// Instanciar el polinomio p
		p= new RealPolynomial(coefsP);

		// Escribir en consola los coeficientes del polinomio p.
		// ¡Hacerlo!
		System.out.println("Coeficientes del polinomio p: ");
		System.out.println();
		int pDegree = p.degree();
		for(int i = 0; i <= pDegree; i++)
		{
			System.out.print(p.getCoef(i) + "\t");
		}
		System.out.println("\n\n");
		
		// Instanciar el polinomio q de grado 2.
		q= new RealPolynomial(2);
		// Completar la instancia. ¡Hacerlo!
		q.setCoef(0, 3);
		q.setCoef(1, 5);
		q.setCoef(2, 4.15);
		
		// Escribir en consola los coeficientes del polinomio q.
		// ¡Hacerlo!
		System.out.println("Coeficientes del polinomio q: ");
		System.out.println();
		int qDegree = q.degree();
		for(int i = 0; i <= qDegree; i++)
		{
			System.out.print(q.getCoef(i) + "\t");
		}
		System.out.println("\n\n");
		
		
		// Sumar los polinomios p y q y depositar el resultado en s.
		s = (RealPolynomial) p.sum(q);
		// Escribir en consola los coeficientes del polinomio.
		// ¡Hacerlo!
		System.out.println("Coeficientes del polinomio s: ");
		System.out.println();
		int sDegree = s.degree();
		for(int i = 0; i <= sDegree; i++)
		{
			System.out.print(s.getCoef(i) + "\t");
		}
		System.out.println("\n\n");
		

		// Evaluar el polinomio a para x= 1 y escribir el resultado
		// en la consola.
		// ¡Hacerlo!
		System.out.println("Evaluación del polinomio p con x=1: ");
		System.out.println();
		System.out.println(p.eval(1));
		
	}

}
