import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DoublePolynomial{
	private List<Double> roots;
	public DoublePolynomial(List<Integer> polynomial, List<Double> roots){
		this.roots = roots;
		List<Double> newPolynomial = makeDouble(polynomial);
		factor(newPolynomial);
	}
	
	public List<Double> makeDouble(List<Integer> polynomial){
		List<Double> newPolynomial = new ArrayList<Double>();
		for(int i = 0; i<polynomial.size(); i++){
			newPolynomial.add(polynomial.get(i).doubleValue());
		}
		return newPolynomial;
	}
	
    /* Creates a list of all possible roots.
     * 
     * Input: the list that is the "polynomial".
     * Returns: a list of all possible roots.
     */
    public List<Double> possibleRoots(List<Double> polynomial){
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

	public void Descartes(List<Double> polynomial){
    	List<String> signs = getSigns(polynomial);
    	//The number of positive roots
        int count = 0;
        int zerocount = 0;
        //The number of negative roots
        int negRoots = 0;
        String temp = "";
        //Positive root case
        for(int i = 0; i<signs.size(); i++){
        	//if i is positive and the sign has changed.
            if(polynomial.get(i) > 0 && !temp.equals(signs.get(i))){
            	//Every time the sign changes add one to the number of positive roots.
                count++;
            }
            if(polynomial.get(i)==0) zerocount++;
            temp = signs.get(i);
        }
        //Negative root case
        negRoots = signs.size()-1-count-zerocount;
        
        //Display result
        System.out.println("The number of positivite roots is: " + count);
        System.out.println("The number of negative roots is: " + negRoots);
	}
    public void rationalZeroTest(List<Double> polynomial){
    	List<Double> possibleRoots = possibleRoots(polynomial);
    	
    	//Gets one real root of the polynomial 
    	double realRoot = findZero(possibleRoots, polynomial);
    	//If it found a root, it performs synthetic division.
    	if(realRoot != 0){
    		polynomial = synthetic(polynomial, realRoot);
    		factor(polynomial);}
    		
    	//Otherwise it tries the non-integer values
        else{
            System.out.println("No rational roots.");
        }
    }
    public List<Double> synthetic(List<Double> polynomial, double realRoot){
		List<Double> newPolynomial = new ArrayList<Double>();
		double term = polynomial.get(0);
		double product = 0;
		newPolynomial.add(term);
		for(int i=1; i<polynomial.size();i++){
			product = term*realRoot;
			term = polynomial.get(i)+product;
			if(term !=0) newPolynomial.add(term);
		}
		System.out.print("The new polynomial: ");
		for(int i=0;i<newPolynomial.size();i++){
			System.out.print(newPolynomial.get(i) + " ");
		}
		System.out.println(); System.out.println();
		roots.add((double)realRoot);
    	return newPolynomial;
    	
    }
    public void quadratic(List<Double> polynomial){
    	double a = polynomial.get(0);
    	double b = polynomial.get(1);
    	double c = polynomial.get(2);
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
    public List<String> getSigns(List<Double> polynomial){
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
    
    public double findZero(List<Double> possibleRoots, List<Double> polynomial){
    	for(int i = 0; i<possibleRoots.size();i++){
    		double root = possibleRoots.get(i);
    		double sum = 0;
    		for(int j = 0; j<polynomial.size(); j++){
    			sum += polynomial.get(j)*Math.pow(root, polynomial.size()-j);
    		}
    	
    		if(sum == 0) return root;
    	}
    	
    	return 0;
    }
    
    public void displayRoots(){
		System.out.print("The roots of the polynomial: ");
		for(int i=0;i<roots.size();i++){
			System.out.print(roots.get(i) + " ");
		}
		System.out.println();
    }
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
    public void factor(List<Double> polynomial){
        for(int i = 0; i < polynomial.size(); i++){
            if(polynomial.get(0) == 0){
                polynomial.remove(0);
            }
        }
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