package Main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Calculations{
	private List<Double> roots;
    public Calculations(List<Integer> coefficients, List<String> signs){
    	this.roots = new ArrayList<Double>();
    	//Combines the list of coefficients + signs to make the actual coefficients of the polynomial.
        List<Integer> polynomial = makePolynomial(signs, coefficients);
        //factors the polynomial
        factor(polynomial);
        
    }
    
    /* Creates a "polynomial" by combining the coefficient with the sign.
     * Ex. [1, 2, 3] + ["+", "-", "+"] -> [1, -2, 3]
     * 
     * Input: string list of signs, integer list of coefficients
     * Returns: a list of integers that is the "polynomial"
     */
    public List<Integer> makePolynomial(List<String> signs, List<Integer> coefficients){
    	//Create a list of integers with the same coefficients as the list of coefficients.
    	List<Integer> polynomial = coefficients;
    	for(int i = 0; i<coefficients.size(); i++){
    		//If the current index of signs is a negative sign.
    		if(signs.get(i) == "-"){
    			//Makes the coefficient in polynomial negative.
    			polynomial.set(i, -(polynomial.get(i)));
    		}
    	}
		System.out.print("The polynomial i: ");
		for(int i=0;i<polynomial.size();i++){
			System.out.print(polynomial.get(i) + " ");
		}
		System.out.println();
    	return polynomial;
    }
    
    /* Creates a list of strings representing the signs of the coefficients of 
     * the polynomial.
     * 
     * Input: the polynomial
     * Returns: a string list of signs
     */
    public List<String> getSigns(List<Integer> polynomial){
    	List<String> signs = new ArrayList<String>();
    	for(int i = 0; i<polynomial.size(); i++){
    		//if the coefficient of polynomial is >= 0.
    		if(polynomial.get(i) >= 0)
    			//add a string "+" to the List signs.
    			signs.add("+");
    		else signs.add("-"); //otherwise add a string "-" to signs List
    	}
    	return signs;
    }
    
    /* Determines the number of positive and negative roots of our "polynomial".
     * 
     * Input: a list of integers that is the "polynomial".
     */
    public void Descartes(List<Integer> polynomial){
    	List<String> signs = getSigns(polynomial);
    	//The number of positive roots
        int count = 0;
        //The number of negative roots
        int negRoots = 0;
        String temp = "";
        //Positive root case
        for(int i = 0; i<signs.size(); i++){
        	//if i is positive and the sign has changed.
            if(i > 0 && !temp.equals(signs.get(i))){
            	//Every time the sign changes add one to the number of positive roots.
                count++;
            }
            temp = signs.get(i);
        }
        //Negative root case
        negRoots = signs.size()-1-count;
        
        //Display result
        System.out.println("The number of positivite roots is: " + count);
        System.out.println("The number of negative roots is: " + negRoots);
        
    }
   
    
    /* Gets factors of a numbers.
     * Input: the number
     * Returns: a list of numbers that are the factors of the number.
     */
    public List<Double> getFactors(double num){
        int ceiling = (int)(Math.sqrt(num));
        ArrayList<Double> factors = new ArrayList<Double>();
        for(int i=1;i <= ceiling; i+= 1){
            if(num%i == 0){
                factors.add((double) i);
                if(i != num/i){
                    factors.add(num/i);
                }
            }
        }
        //Sorts the list
        Collections.sort(factors);
        return factors;
    }
    
    /* Creates a list of possible integer roots. Removes fractions from
     * the list.
     * 
     * Input: the list of all possible roots
     * Returns: the list of all possible integer roots
     */
    public List<Integer> possibleIntRoots(List<Double> possibleRoots){
    	List<Integer> possibleIntRoots = new ArrayList<Integer>(); 
    	for (int i = 0; i < possibleRoots.size();i++){
    		//Checks if the root is a whole number
    		if(possibleRoots.get(i) % 1 != 0)
    			//if it is not a root remove the number
    			possibleRoots.remove(i);
    		//"Converts" from double to integer
    		else possibleIntRoots.add(possibleRoots.get(i).intValue());
    	}
    	return possibleIntRoots;
    }
    
    /* Creates a list of all possible roots.
     * 
     * Input: the list that is the "polynomial".
     * Returns: a list of all possible roots.
     */
    public List<Double> possibleRoots(List<Integer> polynomial){
    	//The value of the leading coefficient of the polynomial (first term)
    	double leadingCoefficient = Math.abs(polynomial.get(0));
    	//The value of the constant of the polynomial (last term)
    	double constant = Math.abs(polynomial.get(polynomial.size()-1));
    	//Create a Set first to ensure no duplicates
    	Set<Double> possibleRoots = new HashSet<Double>();
    	//List that will hold the possible roots 
    	List<Double> possRoots = new ArrayList<Double>();
    	
    	//Dividend consists of +- the factors of the constant. 
    	//Divisor consists of  +- the factors of the leading coefficient
    	List<Double> divisor = getFactors(leadingCoefficient);
    	List<Double> dividend = getFactors(constant);
    	
    	//Initializes index of possibleRoots set
    	double possibleRoot = 0.0;
    	
    	for(int i = 0; i < dividend.size();i++){
    		for(int j = 0; j < divisor.size(); j++){
    			// Calculates the possible root
    			possibleRoot = (double) (dividend.get(i)/divisor.get(j));
    			
    			//adds both +- to the set
    			possibleRoots.add(possibleRoot);
    			possibleRoots.add(-possibleRoot);
    		}
    	}
    	//Adds the set to the list. This is done to ensure no duplicates.
    	possRoots.addAll(possibleRoots);
    	//sorts the list in ascending order.
    	//Collections.sort(possRoots);
    	
    	return possRoots;
    }
    
    /*
     * Finds one real root out of the list of possible roots.
     * Calculates P(x) = 0 and returns that root.
     * 
     * Input: list of possible roots, list of integers that are the "polynomial"
     * Returns: the root that gives P(x) = 0
     */
    public int findZero(List<Integer> possibleRoots, List<Integer> polynomial){
    	
    	for(int i = 0; i<possibleRoots.size();i++){
    		int root = possibleRoots.get(i);
    		int sum = 0;
    		for(int j = 0; j<polynomial.size(); j++){
    			sum += polynomial.get(j)*Math.pow(root, polynomial.size()-j);
    		}
    	
    		if(sum == 0) return root;
    	}
    	
    	return 0;	
    }
    
    /* Performs the rational zero test on the polynomial to get
     * a list of possible roots.
     * 
     * Input: a list of integers that is the polynomial
     * Returns: an integer list of possible roots.
     */
    public void rationalZeroTest(List<Integer> polynomial){
    	//A list that focuses on all possible roots of the polynomial
    	List<Double> possibleRoots = possibleRoots(polynomial);
    	
    	//A list that focuses on just the integer roots of the polynomial
    	List<Integer> possibleIntRoots = possibleIntRoots(possibleRoots);
    	
    	//Gets one real root of the polynomial 
    	int realRoot = findZero(possibleIntRoots, polynomial);
    	//If it found a root, it performs synthetic division.
    	if(realRoot != 0) 
    		polynomial = synthetic(polynomial, realRoot);
    		factor(polynomial);
    		
    	//Otherwise it tries the non-integer values
    	//Insert that here
    	//Otherwise error
    	
    }
    

	public List<Integer> synthetic(List<Integer> polynomial, int root){
		List<Integer> newPolynomial = new ArrayList<Integer>();
		int term = polynomial.get(0);
		int product = 0;
		newPolynomial.add(term);
		for(int i=1; i<polynomial.size();i++){
			product = term*root;
			term = polynomial.get(i)+product;
			if(term !=0) newPolynomial.add(term);
		}
		System.out.print("The new polynomial: ");
		for(int i=0;i<newPolynomial.size();i++){
			System.out.print(newPolynomial.get(i) + " ");
		}
		System.out.println(); System.out.println();
		roots.add((double)root);
    	return newPolynomial;
    }

    //-b+-sqrt(b^2 - 4ac)/2a
    public void quadratic(List<Integer> polynomial){
    	
    	
    	int a = polynomial.get(0);
    	int b = polynomial.get(1);
    	int c = polynomial.get(2);
    	double d, root1, root2;
		d = b * b - 4 * a * c;
		
		// Calculating Roots for quadratic equation
		if (d > 0) {
			root1 = (-b + Math.sqrt(d)) / (2 * a);
			root2 = (-b - Math.sqrt(d)) / (2 * a);
			roots.add(root1);
			roots.add(root2);
			System.out.println("The roots given by the quadratic: " + root1 + " " + root2);
		}
		else if (d == 0) {
			root1 = (-b + Math.sqrt(d)) / (2 * a);
			roots.add(root1); roots.add(root1);
			System.out.println("The roots given by the quadratic: " + root1 + " " + root1);
		} 
		else {
		System.out.println("Roots are imaginary");
	}
    	
		System.out.println();
    	displayRoots();
    	displayFactors();
    }
    /* 
     * Displays the roots of the polynomial
     */
	public void displayRoots(){
		System.out.print("The roots of the polynomial: ");
		for(int i=0;i<roots.size();i++){
			System.out.print(roots.get(i) + " ");
		}
		System.out.println();
    }
	
	/*
	 * Displays the factors of polynomial
	 */
	public void displayFactors(){
		System.out.print("Factor form: ");
		for(int i = 0; i< roots.size();i++){
			if(roots.get(i) % 1 == 0){
				if(roots.get(i) > 0) 
					System.out.print("(x - " + (roots.get(i))+")");
				else
					System.out.print("(x + " + (-roots.get(i))+")");
			}
			else{
				Fraction root = new Fraction(roots.get(i));
				root = root.simplify(Math.abs(root.getNum()), Math.abs(root.getDenom()));
				if(roots.get(i)>0){
					System.out.print("(" + root.getDenom()+"x - " + (root.getNum())+")");
				}
				else System.out.print("(" + root.getDenom()+"x + " + (root.getNum())+")");
			}
			
		}
		System.out.println();
	}
	
	/* Recursive function to factor a polynomial.
	 * 
	 * Input: a list of integers that are the "polynomial"
	 */
    public void factor(List<Integer> polynomial){
    	//Find the total number of positive and negative roots of the polynomial
    	Descartes(polynomial);
    	//If the polynomial has a degree higher than 3
    	if(polynomial.size()>3){
    		//Finds a real root and uses synthetic division to recursively
    		//factor the polynomial
    		rationalZeroTest(polynomial);
    	}
    	//If the polynomial is a trinomial perform the quadratic equation
    	else if(polynomial.size() == 3){
    		quadratic(polynomial);	
    	}
    	else System.out.println("Error: Binomial or monomial");

    	
    }
}
