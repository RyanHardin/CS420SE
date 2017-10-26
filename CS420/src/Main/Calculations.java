package Main;

import java.util.ArrayList;
import java.util.List;

public class Calculations {
    private List<Integer> coefficients;
    private List<String> signs;
    private List<Integer> polynomial;
    private List<Integer> roots;
    
    public Calculations(List<Integer> coefficients, List<String> signs){
        this.coefficients = coefficients;
        this.signs = signs;
        Descartes();
        this.roots = new ArrayList<Integer>();
        this.polynomial = makePolynomial();
        factor();
        displayRoots();
        
    }
    //determine number of real zeroes
    public void Descartes(){
        int count = 0;
        String temp = "";
        //Positive root case
        for(int i = 0; i<signs.size(); i++){
            if(i > 0 && !temp.equals(signs.get(i))){
                count++;
            }
            temp = signs.get(i);
        }
    }
    
    public List<Integer> makePolynomial(){
    	List<Integer> polynomial = coefficients;
    	for(int i = 0; i<coefficients.size(); i++){
    		if(signs.get(i) == "-"){
    			polynomial.set(i, -(polynomial.get(i)));
    			System.out.println(polynomial.get(i));
    		}
    	}
    	return polynomial;
    	
    }

    //-b+-sqrt(b^2 - 4ac)/2a
    public void quadratic(){
    	int a = polynomial.get(0);
    	int b = polynomial.get(1);
    	int c = polynomial.get(2);
    	
    	int root1 = (int) ((-b+Math.sqrt(b*b-4*a*c))/2);
    	int root2 = (int) ((-b-Math.sqrt(b*b-4*a*c))/2);

    	roots.add(root1);
    	roots.add(root2);
    }
    
    public int rationalZeroTest(){
    	int root=0;
    	
    	return root;
    }
    
    public void synthetic(){
    	
    }
    
    public void factor(){
    	if(polynomial.size() == 3){
    		quadratic();
    	}
    	int root = rationalZeroTest();
    	synthetic();
    	
    }
    public void displayRoots(){
    	for(int i = 0; i<roots.size(); i++){
    		System.out.println(roots.get(i));
    	}
    }
}
