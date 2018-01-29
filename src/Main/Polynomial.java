package Main;

import java.util.Random;

public class Polynomial {

	static final int MAX_DEGREE=5;  // max variable degree, example for 2: x^2+x+1
	static final int MAX_COEFFICENT=60;  // example for 20: 20x
	
	static final int START=-50; // start point where we find roots
	static final int END=50;    // end point where we find roots
	static final double STEP=0.5;    // in checkIfRootsExist difference between two points. 
								//For example for 2: f(a) f(a+2), if values has different sign then root exists.
	static int[] coeffiecents;
	static double[] roots;
	static int nrOfRoots=0;
	 public static void main(String[] args) {
		 Random r = new Random();
		 int degree=r.nextInt(MAX_DEGREE)+1;
		 coeffiecents = new int[degree];
		 roots = new double[degree-1];
		 for(int i =0;i<degree;i++) {
			 coeffiecents[i]= r.nextInt(MAX_COEFFICENT)-MAX_COEFFICENT/2;
		 }
		 System.out.println(printPolynomial());
		 checkIfRootsExist(START, END, STEP);
		 for(int i=0;i<nrOfRoots;i++){
			 System.out.print(roots[i]+"  ");
		 }
		 if(nrOfRoots==0) System.out.println("No roots");
	 }
	 private static String printPolynomial() {
		 String s="";
		 for(int i=0;i<coeffiecents.length-1;i++) {
			 s+="("+coeffiecents[i]+")*x^("+(coeffiecents.length-1-i)+")+";
		 }
		 s+="("+coeffiecents[coeffiecents.length-1]+")";
		 return s;
	 }
	 /**
	  * method to count value of function
	 * @param x point of function
	 * @return value
	 */
	private static double countPolynomial(double x) {
		 double result=0;
		 for(int i=0;i<coeffiecents.length;i++) {
			 result+=coeffiecents[i]*(coeffiecents.length-i>0 ? Math.pow(x, coeffiecents.length-1-i) : 1);
		 }
		 return result;
	 }
	 /**
	  *  The method search for roots between start point and end point.
	  *  If it finds root the diff is getting smaller for better approximation of root. 
	  */
	 private static void checkIfRootsExist(double start, double end, double diff) {
		 
		 //Checking if the approximation of root is close enough
		 if((diffrentSign(countPolynomial(start),countPolynomial(end-diff))&&(Math.abs(countPolynomial(end-diff))<0.01||Math.abs(countPolynomial(start))<0.01))) {
			 if(nrOfRoots==roots.length) return;
			 roots[nrOfRoots++]=(start+end)/2; // adding root
			 return;
			 
		 }
		 if(diff<0.00001) return;
		 double a=diff,b=start-diff;
		 while(b<end+a){
			 if(countPolynomial(b)==0) roots[nrOfRoots++]=(start);
			 if(diffrentSign(countPolynomial(b),countPolynomial(b+a))){
				 checkIfRootsExist(b,b+a,a/2);
			 }
			 b+=a;
		 } 
	 }
	 private static boolean diffrentSign(double a, double b) {
		 if(a>0&&b<0||a<0&&b>0) return true; else return false;
	 }
}
