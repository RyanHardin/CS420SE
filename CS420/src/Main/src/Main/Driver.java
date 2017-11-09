package Main;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> coefficient = new ArrayList<Integer>();
		List<String> signs = new ArrayList<String>();
		//x^2 +x + 1
		coefficient.add(99); coefficient.add(54); coefficient.add(5);
		//+ - +
		signs.add("+"); signs.add("+"); signs.add("+");
		Calculations c = new Calculations(coefficient, signs);	
		
		
		List<String> str = new ArrayList();
		str.add("1x");
		str.add("x^2");
		str.add("1");
		synthetic(str);
	}
	

	public static void synthetic(List<String> divisor) {
		if(divisor.get(0) == "x" || divisor.get(0) == "3x") {
			System.out.println(divisor.get(0));
			} else {
			System.out.println("divisor is not a first degree polynomial");		
			}
		}
}
