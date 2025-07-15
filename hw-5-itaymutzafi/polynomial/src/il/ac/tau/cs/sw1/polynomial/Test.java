package il.ac.tau.cs.sw1.polynomial;

public class Test {
	private static boolean arePolynomialsEqual(Polynomial p1, Polynomial p2) {
		if (p1.getDegree() != p2.getDegree()) {
			return false;
		}
		for (int i = 0; i <= p1.getDegree(); i++) {
			if (p1.getCoefficient(i) != p2.getCoefficient(i)) {
				return false;
			}
		}
		return true;
	}

	private static void testGetCoefficient(TestSuite suite) {
		Polynomial original = new Polynomial(new double[] { 1.0, 2.0, 3.0 });
		suite.test(original.getCoefficient(0) == 1.0, "regular getCoefficient (1)");
		suite.test(original.getCoefficient(1) == 2.0, "regular getCoefficient (2)");
		suite.test(original.getCoefficient(2) == 3.0, "regular getCoefficient (3)");
		suite.test(original.getCoefficient(3) == 0.0, "Out of bounds getCoefficient (1)");
		suite.test(original.getCoefficient(-1) == 0.0, "Out of bounds getCoefficient (2)");
		suite.test((new Polynomial()).getCoefficient(0) == 0.0, "empty polynomial getCoefficient (1)");
		suite.test((new Polynomial()).getCoefficient(17) == 0.0, "empty polynomial getCoefficient (2)");
		suite.test((new Polynomial(new double[0])).getCoefficient(0) == 0.0, "polynomial initialized with zero-length array");
	}

	private static void testSetCoefficient(TestSuite suite) {
		Polynomial degree_2 = new Polynomial(new double[] { 1.0, 2.0, 3.0 });
		degree_2.setCoefficient(1, -5.2);
		suite.test(
				arePolynomialsEqual(degree_2, new Polynomial(new double[] { 1.0, -5.2, 3.0 })),
				"existing setCoefficient");

		Polynomial degree_1 = new Polynomial();
		degree_1.setCoefficient(2, -6.2);
		suite.test(
				arePolynomialsEqual(degree_1, new Polynomial(new double[] { 0.0, 0.0, -6.2 })),
				"extending setCoefficient");

		Polynomial degree_3 = new Polynomial(new double[] { 1.0, 2.0, 0.0, 4.0 });
		degree_3.setCoefficient(3, 0);
		suite.test(
				arePolynomialsEqual(degree_3, new Polynomial(new double[] { 1.0, 2.0 })),
				"shrinking setCoefficient");

		Polynomial degree_4 = new Polynomial(new double[] { 1.0, 2.0, 0.0, 4.0, 5.0 });
		degree_4.setCoefficient(30, 0);
		suite.test(
				arePolynomialsEqual(degree_4, new Polynomial(new double[] { 1.0, 2.0, 0.0, 4.0, 5.0 })),
				"no-op setCoefficient");

	}

	private static void testGetDegree(TestSuite suite) {
		suite.test((new Polynomial(new double[] { 1.0 })).getDegree() == 0, "regular getDegree (1)");
		suite.test((new Polynomial(new double[] { 1.0, 2.0, 3.0 })).getDegree() == 2, "regular getDegree (2)");
		suite.test((new Polynomial()).getDegree() == 0, "empty getDegree");
	}

	private static void testMultiply(TestSuite suite) {
		Polynomial degree_3 = new Polynomial(new double[] { 0.1, 0, 3 });
		Polynomial degree_0 = new Polynomial();
		suite.test(arePolynomialsEqual(degree_0.multiply(2), new Polynomial()),
				"multiply 0 polynomial");
		suite.test(arePolynomialsEqual(degree_3.multiply(0), new Polynomial()),
				"multiply polynomial by 0");

		Polynomial original = new Polynomial(new double[] { 1.0, 2.0, 3.0 });
		Polynomial multiplied = original.multiply(2.0);
		suite.test(original.getCoefficient(1) == 2.0, "the coefficient of x is 2.0");
		suite.test(multiplied.getCoefficient(1) == 4.0, "the coefficient of x is 4.0");
		suite.test(arePolynomialsEqual(multiplied, new Polynomial(new double[] { 2.0, 4.0, 6.0 })),
				"regular multiplication");
		suite.test(arePolynomialsEqual(original, new Polynomial(new double[] { 1.0, 2.0, 3.0 })),
				"immutability of multiplication");
	}

	private static void testAdds(TestSuite suite) {
		Polynomial degree_3 = new Polynomial(new double[] { 0.1, -4.2, 0, 3 });
		Polynomial degree_1 = new Polynomial(new double[] { 0.3, -0.3 });
		Polynomial degree_0 = new Polynomial();
		suite.test(arePolynomialsEqual(degree_3.adds(degree_1), new Polynomial(new double[] { 0.4, -4.5, 0, 3 })),
				"different sizes adds (1)");
		suite.test(arePolynomialsEqual(degree_1.adds(degree_3), new Polynomial(new double[] { 0.4, -4.5, 0, 3 })),
				"different sizes adds (2)");
		suite.test(arePolynomialsEqual(degree_1.adds(degree_0), degree_1),
				"different add degree 0 (1)");
		suite.test(arePolynomialsEqual(degree_0.adds(degree_1), degree_1),
				"different add degree 0 (2)");
		suite.test(arePolynomialsEqual(degree_1.adds(degree_1), new Polynomial(new double[] { 0.6, -0.6 })),
				"add one polynomial with itself");

		Polynomial original = new Polynomial(new double[] { 1.0, 2.0, 3.0 });
		Polynomial multiplied = original.multiply(2.0);
		Polynomial addition = multiplied.adds(original);
		suite.test(addition.getDegree() == 2, "the degree of multiplied is 2");
		suite.test(
				arePolynomialsEqual(addition, new Polynomial(new double[] { 3.0, 6.0, 9.0 })),
				"multiply then add");
		suite.test(arePolynomialsEqual(multiplied, new Polynomial(new double[] { 2.0, 4.0, 6.0 })),
				"immutability of addition");
	}

	private static void testGetFirstDerivation(TestSuite suite) {
		Polynomial original = new Polynomial(new double[] { 3.0, 6.0, 9.0 });
		Polynomial derivative = original.getFirstDerivation();
		suite.test(derivative.getDegree() == 1, "getFirstDerivation degree");
		suite.test(
				arePolynomialsEqual(derivative, new Polynomial(new double[] { 6.0, 18.0 })),
				"regular getFirstDerivation");
		suite.test(
				arePolynomialsEqual(original, new Polynomial(new double[] { 3.0, 6.0, 9.0 })),
				"immutability of getFirstDerivation");
		suite.test(
				arePolynomialsEqual(new Polynomial(new double[] { 2.1 }).getFirstDerivation(), new Polynomial()),
				"getFirstDerivation of constant polynomial");
		suite.test(
				arePolynomialsEqual(new Polynomial().getFirstDerivation(), new Polynomial()),
				"getFirstDerivation of empty polynomial");
	}

	private static void testComputePolynomial(TestSuite suite) {
		Polynomial empty = new Polynomial();
		suite.test(empty.computePolynomial(3) == 0.0, "the value of empty with x=3 is 0.0");

		double[] coefficients = new double[] { 1.0, 2.0, 3.0 };
		Polynomial p2 = new Polynomial(coefficients);
		suite.test(p2.computePolynomial(3) == 34.0, "the value of p2 with x=3 is 34.0");

		Polynomial original = new Polynomial(new double[] { 1.0, 3.0, 1.0 });
		Polynomial multiplied = original.multiply(2.0);
		Polynomial addition = multiplied.adds(original);
		Polynomial derivative = addition.getFirstDerivation();
		suite.test(original.computePolynomial(0) == 1, "computePolynomial with 0");
		suite.test(original.computePolynomial(2) == 11, "computePolynomial positive value");
		suite.test(original.computePolynomial(-2) == -1, "computePolynomial positive value");
		suite.test(multiplied.computePolynomial(2) == 22, "computePolynomial after multiply");
		suite.test(addition.computePolynomial(2) == 33, "computePolynomial after addition");
		suite.test(derivative.computePolynomial(2) == 21, "computePolynomial after derivative");
	}

	private static void testExtrema(TestSuite suite) {
		Polynomial original = new Polynomial(new double[] { 1.0, 2.0, -3.0, 0.0, 2.0 });
		suite.test(original.isExtrema(-1) == true, "correct extrema point");
		suite.test(original.getFirstDerivation().computePolynomial(0.5) == 0, "sanity before extrema edge case");
		suite.test(original.isExtrema(0.5) == false, "both derivatives 0 so not extrema point");
		suite.test(original.isExtrema(-0.5) == false, "only 2nd derivative is 0 so not extrema point");
		suite.test(original.isExtrema(0) == false, "no derivative is 0 so not extrema point");
	}

	public static void main(String[] args) {
		TestSuite suite = new TestSuite();

		try {
			testSetCoefficient(suite);
			testGetCoefficient(suite);
			testGetDegree(suite);
			testMultiply(suite);
			testAdds(suite);
			testComputePolynomial(suite);
			testGetFirstDerivation(suite);
			testExtrema(suite);
		} catch (Exception e) {
			suite.test(false, "One test raised an exception");
			TestSuite.colorPrint(">>> Full stack trace at the end", TestSuite.ANSI_RED);
			throw e;
		} finally {
			suite.summary();
		}
	}
}
