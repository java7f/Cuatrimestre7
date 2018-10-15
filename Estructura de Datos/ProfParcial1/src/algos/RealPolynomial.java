/**
 * @file RealPolynomial.java
 * @brief Fuente de la clase RealPolynomial
 */
package algos;

/**
 * Realiza un polinomio de coeficientes reales sobre los reales.
 * @author Rodrigo Orizondo
 * @todo Completar.
 */
public class RealPolynomial implements PolynomialInterface {

	/**
	 * Instancia un polinomio nulo de grado dado.
	 * @param degree Grado del polinomio nulo.
	 * @todo Completar.
	 */
	public RealPolynomial(int degree) {
	}
	
	/**
	 * Instancia un polinomio nulo de grado 0 (constante).
	 */
	public RealPolynomial() {
		this(0);
	}
	
	/**
	 * Instancia un polinomio dados sus coeficientes.
	 * @param coefs Coeficientes del polinomio.
	 * @todo Completar.
	 */
	public RealPolynomial(double[] coefs) {
	}
	
	/* (non-Javadoc)
	 * @see algos.PolynomialInterface#degree()
	 */
	/**
	 * @todo Completar.
	 */
	@Override
	public int degree() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see algos.PolynomialInterface#setCoef(int, double)
	 */
	@Override
	/**
	 * @todo Completar.
	 */
	public PolynomialInterface setCoef(int i, double val) throws IndexOutOfBoundsException {
		return this;
	}

	/* (non-Javadoc)
	 * @see algos.PolynomialInterface#getCoef(int)
	 */
	@Override
	/**
	 * @todo Completar.
	 */
	public double getCoef(int i) {
		return 0;
	}

	/* (non-Javadoc)
	 * @see algos.PolynomialInterface#sum(PolynomialInterface)
	 */
	@Override
	/**
	 * @todo Completar.
	 */
	public PolynomialInterface sum(PolynomialInterface a) {
		return null;
	}

	/* (non-Javadoc)
	 * @see algos.PolynomialInterface#equal(algos.PolynomialInterface)
	 */
	@Override
	/**
	 * @todo Completar.
	 */
	public Boolean equal(PolynomialInterface x) {
		return true;
	}

	/* (non-Javadoc)
	 * @see algos.PolynomialInterface#eval(double)
	 */
	@Override
	/**
	 * @todo Completar.
	 */
	public double eval(double x) {
		return 0.0;
	}

}
