package Main;

import java.util.Random;

public class Integral {
	final static int MAX_DEGREE=5;
	final static int MAX_A=50;
	final static int MAX_COEFFICENT=60;
	final static int SAMPLES=10;
	static double[] coeffiecents;
	static double[] coeffiecentsIntegral;
	static String[] coeffiecentsString;
	
	public static void main(String[] args) {
		System.out.println("Numerical integration");
		Random r = new Random(); 
		int startPoint = r.nextInt(MAX_A)-MAX_A/2;
		int endPoint = r.nextInt(MAX_A)+Math.abs(startPoint);
		int degree=r.nextInt(MAX_DEGREE)+1;
		coeffiecents = new double[degree];
		for(int i =0;i<degree;i++) {
			//with int there is not big difference between algorithms of course it depends on SAMPLES
			coeffiecents[i]=r.nextInt(MAX_COEFFICENT)-MAX_COEFFICENT/2;
			//coeffiecents[i]= r.nextInt(MAX_COEFFICENT)/((double) r.nextInt(MAX_COEFFICENT/4)+1);
		}
		System.out.println(printPolynomial(false));
		coeffiecentsIntegral = new double[degree+1];
		coeffiecentsString = new String[degree+1];
		coeffiecentsIntegral = calculateIntegralFunction(coeffiecents, coeffiecentsString);
		System.out.println(printPolynomial(true));
		System.out.println("Start point: "+startPoint);
		System.out.println("End point: "+endPoint);
		System.out.print("Riemann sum: ");
		System.out.println(riemannSum(coeffiecents,SAMPLES,startPoint,endPoint));
		System.out.print("Trapezoidal rule: ");
		System.out.println(trapezoidalRule(coeffiecents,SAMPLES,startPoint,endPoint));
		System.out.print("Simpson's rule: ");
		System.out.println(simpsonsRule(coeffiecents,SAMPLES,startPoint,endPoint));
		System.out.print("Riemann integral: ");
		System.out.println(riemanIntegral(coeffiecentsIntegral,startPoint,endPoint));
		
		
	}
	
	private static double simpsonsRule(double[] coeffiecents, int samples, int start, int end) {
		double sum=0;
		double dx= Math.abs(start-end)/(double) samples,xi=(double)start, xi2=xi+dx;
		for(int i=0; i<samples;i++, xi+=dx,xi2+=dx) {
			sum+=(xi2-xi)/6*(countPolynomial(xi2,coeffiecents)+4*countPolynomial((xi2+xi)/2, coeffiecents)+countPolynomial(xi,coeffiecents));
		}
		return sum;
	}

	/**
	 *   We are basically divide function on small trapezoid 
	 *  with base one (value of polynomial in xi), base two (value of polynomial in xi2) and height(dx)  
	 *  
	 * @param coeffiecentsIntegral2
	 * @param samples - number of rectangles
	 * @param start - start point of calculation
	 * @param end - end point of calculation 
	 * @return - sum(i=0 .. samples) (f(xi)+f(xi2))*dx/2
	 */
	private static double trapezoidalRule(double[] coeffiecents, int samples, int start, int end) {
		double sum=0;
		double dx= Math.abs(start-end)/(double) samples,xi=(double)start, xi2=xi+dx;
		for(int i=0; i<samples;i++, xi+=dx,xi2+=dx) {
			sum+=(countPolynomial(xi2,coeffiecents)+countPolynomial(xi,coeffiecents))*dx/2;
		}
		return sum;
	}

	/**
	 * 	Counting the value of integral NOTE that we already calculate the indefinite integral
	 * 	formula of integral from a to b from function f(x):
	 * 		F(b)-F(a)	
	 *  
	 * @param coeffiecentsIntegral 
	 * @param start
	 * @param end
	 * @return - F(end) - F(start)
	 */
	private static double riemanIntegral(double[] coeffiecentsIntegral, int start, int end) {
		return countPolynomial(end, coeffiecentsIntegral)-countPolynomial(start, coeffiecentsIntegral);
	}

	/**
	 * 	Riemann sum. We are basically divide function on small rectangles 
	 *  with height(value of polynomial in xi) and width(dx)  
	 * 
	 * @param coeffiecents 
	 * @param samples - number of rectangles
	 * @param start - start point of calculation
	 * @param end - end point of calculation 
	 * @return - sum(i=0 .. samples) f(xi)*dx
	 */
	private static double riemannSum(double[] coeffiecents, int samples,int start, int end) {
		double dx= Math.abs(start-end)/(double) samples,xi=(double)start;
		double sum=0;
		for(int i=0; i<samples;i++, xi+=dx) {
			sum+=countPolynomial(xi,coeffiecents)*dx;
		}
		return sum;
	}

	/**
	 * 		It counts a integral from polynomial
	 * @param coeffiecents
	 * @param coeffiecentsString   TODO: maybe try to show instead of (12/2) show 6
	 * @return
	 */
	private static double[] calculateIntegralFunction(double[] coeffiecents, String[] coeffiecentsString) {
		double[] coeffiecentsReturn = new double[coeffiecents.length+1];
		for(int i=0,k=coeffiecents.length;i<coeffiecents.length+1;i++,k--) {
			coeffiecentsReturn[i] = (i!=coeffiecents.length) ? coeffiecents[i]/((double) k) : 1;
			coeffiecentsString[i] = (i!=coeffiecents.length) ? (double) coeffiecents[i]+"/"+(k) : "";
		}
		return coeffiecentsReturn;
	}
	/**
	 * function to present polynomial as String
	 * @param isIntegral 
	 * 			when true add the C (as constant of integration) 
	 * @return function as String 
	 *			for example: x^2-2x+1 or 1/3*x^3-x^2+x+C
	 */
	private static String printPolynomial(boolean isIntegral) {
		String s="";
		if(isIntegral) {
			for(int i=0;i<coeffiecentsIntegral.length-1;i++) {
				s+="("+coeffiecentsString[i]+")"+")*x^("+(coeffiecents.length-i)+")+";
			}
			s+="C";
		} else {
			for(int i=0;i<coeffiecents.length-1;i++) {
				s+="("+ coeffiecents[i]+")*x^("+(coeffiecents.length-1-i)+")+";
			}
			s+="("+coeffiecents[coeffiecents.length-1]+")";
		}
		return s;
	 }
	
	/**
	  * method to count value of function
	 * @param x point of function
	 * @return value
	 */
	private static double countPolynomial(double x,double[] coeffiecents) {
		double result=0;
		for(int i=0;i<coeffiecents.length;i++) {
			result+=coeffiecents[i]*(coeffiecents.length-i>0 ? Math.pow(x, coeffiecents.length-1-i) : 1);
		}
		return result;
	 }
}
