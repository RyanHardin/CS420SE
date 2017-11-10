package Main;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> coefficient = new ArrayList<Integer>();
		List<String> signs = new ArrayList<String>();
		//10x^5+86x^4+158x^3-254x^2-792x-360
		//Use these values to change the coefficient
		//The values should only be positive
		coefficient.add(10); coefficient.add(86); coefficient.add(158);coefficient.add(254); coefficient.add(792); coefficient.add(360);
		//+ + + - - -
		//use these strings to determine the sign of the coefficients
		signs.add("+"); signs.add("+"); signs.add("+"); signs.add("-");signs.add("-");signs.add("-");
		@SuppressWarnings("unused")
		Calculations c = new Calculations(coefficient, signs);
		Fraction root = new Fraction(6.2);

	}

}
