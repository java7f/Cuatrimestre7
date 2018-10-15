/**
 * @file PolynomialInterface.java
 * @brief Fuente de la interface PolynomialInterface
 */
package algos;

/**
 * Interface de especificación de polinomios reales.
 * @author Rodrigo Orizondo
 */
public interface PolynomialInterface {
	/**
	 * Obtiene el grado del polinomio.
	 * @return Grado del polinomio.
	 */
	int degree();
	
	/**
	 * Obtiene el valor del coeficiente con índice dado por el parámetro.
	 * @param i Índice del coeficiente.
	 * @return Valor del coeficiente.
	 * @remark Si i es negativo o superior al grado se devuelve 0.
	 */
	double getCoef(int i);
	
	/**
	 * Sitúa el valor de un coeficiente.
	 * @param i Índice del coeficiente.
	 * @param val Valor asignado.
	 * @return Instancia sobre la que se actúa.
	 * @throws IndexOutOfBoundsException si el índice es negativo o mayor
	 * que el grado del polinomio.
	 */
	PolynomialInterface setCoef(int i, double val) throws IndexOutOfBoundsException;
	
	/**
	 * Indaga si el polinomio es equivalente a otro dado.
	 * @param x Polinomio a comparar.
	 * @return true si son equivalentes.
	 */
	Boolean equal(PolynomialInterface x);

	/**
	 * Agrega un polinomio al existente y devuelve uno NUEVO con el resultado.
	 * @param a Polinomio a agregar.
	 * @return NUEVO polinomio suma.
	 */
	PolynomialInterface sum(PolynomialInterface a);
	
	/**
	 * Evalúa el polinomio. 
	 * @param x Valor de la variable.
	 * @return Resultado de la evaluación.
	 */
	double eval(double x);
}
