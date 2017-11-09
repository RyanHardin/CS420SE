package Main;

import java.util.ArrayList;
import java.util.List;

public class Calculations {
	private List<Integer> coefficients;
	private List<String> signs;
	private List<Integer> polynomial;
	private List<Integer> roots;

	public Calculations(List<Integer> coefficients, List<String> signs) {
		this.coefficients = coefficients;
		this.signs = signs;
		Descartes();
		this.roots = new ArrayList<Integer>();
		this.polynomial = makePolynomial();
		factor();
		displayRoots();
	}

	// determine number of real zeroes
	public void Descartes() {
		int count = 0;
		String temp = "";
		// Positive root case
		for (int i = 0; i < signs.size(); i++) {
			if (i > 0 && !temp.equals(signs.get(i))) {
				count++;
			}
			temp = signs.get(i);
		}
	}

	public List<Integer> makePolynomial() {
		List<Integer> polynomial = coefficients;
		for (int i = 0; i < coefficients.size(); i++) {
			if (signs.get(i) == "-") {
				polynomial.set(i, -(polynomial.get(i)));
				System.out.println(polynomial.get(i));
			}
		}
		return polynomial;

	}

	public void quadratic() {
		int a = polynomial.get(0);
		int b = polynomial.get(1);
		int c = polynomial.get(2);

		int root1 = (int) ((-b + Math.sqrt(b * b - 4 * a * c)) / 2);
		int root2 = (int) ((-b - Math.sqrt(b * b - 4 * a * c)) / 2);

		roots.add(root1);
		roots.add(root2);
	}

	public int rationalZeroTest(List<Integer> root2) {
		int root = 0;
		return root;
	}

	public List<Integer> synthetic(List<Integer> dividend, List<Integer> root) {
		List<Integer> poly = new ArrayList();
		dividend = polynomial;
		root = roots;

		int divisor = rationalZeroTest(root);
		
		//The first coefficient is already added to the new list
		poly.add(dividend.get(0));
		
		int count = dividend.get(0) * -1;
		int num = 0;
		int fin = 0;
		
		for(int i = 1; i < dividend.size(); i++) {
			num = divisor * count;
			fin = dividend.get(i) + num;
			poly.add(fin);
			count = fin;
		}
		
		if (Integer.toString(root.get(0)) == "x" || Integer.toString(root.get(0)) == "1x") {
				System.out.println("Correctly written divisor!");
		} else {
			System.out.println("Error: Check to see if divisor is not a first degree "
					+ "polynomial or whether it has a coefficient greater than 1");
		}
		return poly;
	}

	public void factor() {
		if (polynomial.size() == 3) {
			quadratic();
		}
		//int root = rationalZeroTest();
		// synthetic();

	}

	public void displayRoots() {
		for (int i = 0; i < roots.size(); i++) {
			System.out.println("These are the roots!: " + roots.get(i));
		}
	}
}
