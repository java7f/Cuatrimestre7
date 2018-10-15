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
	
	//Almacena el grado del polinomio
	private int pDegree;
	
	//Almacena los coeficientes del polinomio
	private double[] coeficientes;

	/**
	 * Instancia un polinomio nulo de grado dado.
	 * @param degree Grado del polinomio nulo.
	 * Se le suma 1 al tamaño del arreglo debido a que un polinomio contiene grado+1 términos.
	 */
	public RealPolynomial(int degree) {
		pDegree = degree;
		coeficientes= new double[degree + 1];
	}
	
	/**
	 * Instancia un polinomio nulo de grado 0 (constante).
	 */
	public RealPolynomial() {
		pDegree = 0;
		coeficientes = new double[1];
	}
	
	/**
	 * Instancia un polinomio dados sus coeficientes.
	 * @param coefs Coeficientes del polinomio.
	 * Se le resta 1 al grado debido a que el tamaño del arreglo siempre será mayor por una unidad.
	 */
	public RealPolynomial(double[] coefs) {
		pDegree = coefs.length - 1;
		coeficientes = coefs;
	}
	
	/* (non-Javadoc)
	 * @see algos.PolynomialInterface#degree()
	 */
	/**
	 * @todo Completar.
	 */
	@Override
	public int degree() {
		return pDegree;
	}

	/* (non-Javadoc)
	 * @see algos.PolynomialInterface#setCoef(int, double)
	 */
	@Override
	/**
	 * Antes de hacer la asignación del coeficiente, se evalúa si el índice i es válido.
	 * En caso de que no lo sea, arroja una excepción.
	 */
	public PolynomialInterface setCoef(int i, double val) throws IndexOutOfBoundsException {
		if(i < 0)
		{
			throw new IndexOutOfBoundsException("El índice no puede ser negativo");
		}
		else if (i > pDegree) 
		{
			throw new IndexOutOfBoundsException("El índice no puede ser mayor al grado del polinomio");
		}
		
		coeficientes[i] = val;
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
		
		if(i < 0) {
			return 0;
		}
		
		return coeficientes[i];
	}

	/* (non-Javadoc)
	 * @see algos.PolynomialInterface#sum(PolynomialInterface)
	 */
	@Override
	/**
	 * La función realiza la suma de los polinomios tomando en cuenta 3 condiciones:
	 * 1. Si el índice i es menor o igual que el grado menor entre ambos polinomios, 
	 * indica que ambos polinomios presentan coeficientes en esa posición y deben sumarse.
	 * 2. Si el índice i es mayor al grado de a, el resultado será igual al valor que se encuentre en esta instancia en la posición marcada por i.
	 * 3. Si el índice i es mayor al grado de esta instancia, el resultado será igual al valor que se encuentre en a en la posición marcada por i.
	 */
	public PolynomialInterface sum(PolynomialInterface a) {
		
		int maxDegree = Math.max(this.pDegree, a.degree());
		int minDegree = Math.min(this.pDegree, a.degree());
		double newValue;
		PolynomialInterface result = new RealPolynomial(maxDegree);
		
		for(int i = 0; i <= maxDegree; i++)
		{
			if(i <= minDegree)
			{
				newValue = coeficientes[i] + a.getCoef(i);
				result.setCoef(i, newValue);
			}
			else if (i > a.degree()) {
				result.setCoef(i, coeficientes[i]);
			}
			else {
				newValue = a.getCoef(i);
				result.setCoef(i, newValue);
			}
		}
		
		return result;
		
	}

	/* (non-Javadoc)
	 * @see algos.PolynomialInterface#equal(algos.PolynomialInterface)
	 */
	@Override
	/**
	 * La función evalúa la equivalencia del polinomio tomando en cuenta el grado y los coeficientes.
	 * Devuelve verdadero cuando ambos polinomios tienen exactamente el mismo grado y los mismos coeficientes, 
	 * devuelve falso en el caso contrario.
	 */
	public Boolean equal(PolynomialInterface x) {
		Boolean areEqual = true;
		
		if(this.pDegree != x.degree())
		{
			areEqual = false;
		}
		else
		{
			for(int i = 0; i <= pDegree; i++)
			{
				if(coeficientes[i] != x.getCoef(i))
				{
					areEqual = false;
					break;
				}
			}
		}
		
		return areEqual;
	}

	/* (non-Javadoc)
	 * @see algos.PolynomialInterface#eval(double)
	 */
	@Override
	/**
	 * La función evalúa el polinomio con el valor de x otorgado. 
	 * El resultado estará compuesto de la suma sucesiva de cada coeficiente multiplicado por el valor de x, que estará elevada al número
	 * de la posición del coeficiente.
	 */
	public double eval(double x) {
		double result = 0;
		for(int i = 0; i<= pDegree; i++)
		{
			result += coeficientes[i] * Math.pow(x, i);
		}
		return result;
	}

}
