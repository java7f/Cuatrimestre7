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
		
		// Instanciar el polinomio q de grado 2.
		q= new RealPolynomial(2);
		// Completar la instancia. ¡Hacerlo!
		
		// Escribir en consola los coeficientes del polinomio q.
		// ¡Hacerlo!
		
		
		// Sumar los polinomios p y q y depositar el resultado en s.
		// Escribir en consola los coeficientes del polinomio.
		// ¡Hacerlo!
		
		// Escribir en consola los coeficientes del polinomio suma.
		// Escribir en consola los coeficientes del polinomio.
		// ¡Hacerlo!
		

		// Evaluar el polinomio a para x= 1 y escribir el resultado
		// en la consola.
		// ¡Hacerlo!
		
	}

}
