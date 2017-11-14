
public class Fraction {

	private int num, denom;

	public Fraction(double d) {
		String s = String.valueOf(d);
		int digitsDec = s.length() - 1 - s.indexOf('.');        

		int denom = 1;
		for(int i = 0; i < digitsDec; i++){
			d *= 10;
			denom *= 10;
		}
		int num = (int) Math.round(d);

		this.num = num; this.denom = denom;
	}

	public Fraction(int num, int denom) {
		this.num = num; this.denom = denom;
	}
	public int gcd(int num, int denom){
        while (denom != 0) {
            int temp = denom;
            denom = num % denom;
            num = temp;
        }
        return num;
    }
		
	public Fraction simplify(int num, int denom){
	    int gcd         = gcd(num, denom);
	    int num_simpl   = num/gcd;
	    int denom_simpl = denom/gcd;

	    return new Fraction(num_simpl, denom_simpl);
	}
	public int getNum(){ return num;}
	public int getDenom(){ return denom;}

	public String toString() {
		return String.valueOf(num) + "/" + String.valueOf(denom);
	}
}