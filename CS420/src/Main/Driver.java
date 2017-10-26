package Main;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> coefficient = new ArrayList<Integer>();
		List<String> signs = new ArrayList<String>();
		//x^2 +x + 1
		coefficient.add(1); coefficient.add(2); coefficient.add(1);
		//+ - +
		signs.add("+"); signs.add("+"); signs.add("+");
		Calculations c = new Calculations(coefficient, signs);

	}

}
