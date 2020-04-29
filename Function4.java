
public class Function4 extends Function{
	public double fnValue(double x) {
		double function3 = (8*x-Math.sqrt(x))/(Math.pow(x,3) - (7*Math.pow(x,2)) + 15*x - 9);
		
		if (function3 <= 0) {
			return x;
		}else {
			return function3;
		}
	}
	
	public String toString() {
		return "(8*x-sqrt(x))/x^3 - 7*x^2 + 15*x – 9";
	}
}
