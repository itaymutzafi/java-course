package il.ac.tau.cs.sw1.polynomial;

public class Polynomial {
	 private double [] coefficient;
	 private int degree;
	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	public Polynomial()
	{
		this.coefficient = new double[1]; //default val is 0
		this.degree = 0;
	} 
	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) 
	{
		int len = coefficients.length;
		this.coefficient = new double[len];
        System.arraycopy(coefficients, 0, this.coefficient, 0, len);
		this.degree = len-1;
	}
	/*
	 * Addes this polynomial to the given one
	 *  and retruns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial)
	{
		int min_deg = Math.min(this.degree, polynomial.degree);
		int max_deg = Math.max(this.degree, polynomial.degree);
		double [] new_coef = new double[max_deg+1];
		for (int i=0; i<=max_deg; i++){
			if(i<=min_deg) {
				double sum = this.coefficient[i] + polynomial.coefficient[i];
				new_coef[i] = sum;
			}
			else {
				if (max_deg == this.degree) new_coef[i] = this.coefficient[i];
				if (max_deg == polynomial.degree) new_coef[i] = polynomial.coefficient[i];
			}
			}
        return new Polynomial(new_coef);
		}

	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a)
	{
		if (a ==0) return new Polynomial();
		double [] new_coef = new double[this.degree+1];
		for (int i=0; i<=this.degree; i++){
			new_coef[i] = this.coefficient[i] *a;
		}
		return new Polynomial(new_coef);
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree()
	{
		return this.degree;
	}
	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n)
	{
		if (n<=this.degree && n>=0) return this.coefficient[n];
		else return 0;
	}
	
	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that the coefficient of the variable x
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c)
	{
		if(this.degree < n) { //expends the coefficient
			double [] new_coef = new double[n+1];
			System.arraycopy(this.coefficient,0,new_coef,0,this.degree+1);
			new_coef[n] = c;
			this.coefficient = new_coef;
			this.degree = n;
		}
		if(c == 0 &&  n == this.degree) { //shrinks the coefficient
			this.coefficient[n] = 0;
			int new_deg=0;
			for(int i=n; i>0; i--){
				if (this.coefficient[i] != 0){
                    new_deg = i;
					break;
				}
			}
			double [] new_coef = new double[new_deg+1];
			System.arraycopy(this.coefficient,0,new_coef,0,new_deg+1);
			this.degree = new_deg;
			this.coefficient = new_coef;

		}
		else {
			this.coefficient[n] = c;
		}
	}
	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	
	 */
	public Polynomial getFirstDerivation()
	{
		if (this.degree == 0) return new Polynomial();
		double [] new_coef = new double[this.degree];
		for (int i =1; i<=this.degree ;i++){
			new_coef[i-1] = this.coefficient[i] * i;
		}
		return new Polynomial(new_coef);
	}
	
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x)
	{
		double res = 0;
		for (int i=0; i<=this.degree; i++){
			res += this.coefficient[i]* Math.pow(x,i);
		}
		return res;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is an extrema point (local minimum or local maximum of this polynomial)
	 * x is an extrema point if and only if The value of first derivation of a polynomal at x is 0
	 * and the second derivation of a polynomal value at x is not 0.
	 */
	public boolean isExtrema(double x)
	{
		Polynomial first_der = this.getFirstDerivation();
		Polynomial second_der = first_der.getFirstDerivation();
		return (first_der.computePolynomial(x) == 0 && second_der.computePolynomial(x) != 0);
	}
	
	
	
	

    
    

}
