package Main;
import java.util.Scanner;

public class Fibonacci {
	static int n; // n should be  between 0 and 22 (22!>2^63-1) and MAX long is 2^63-1 
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		n=s.nextInt();
		System.out.print("Recurrence relation: ");
		long a = System.nanoTime();
		System.out.println(recursion(n));
		System.out.println("Calculated in "+(System.nanoTime()-a));
		
		System.out.print("With binomial coefficient: ");
		a = System.nanoTime();
		System.out.println(binomialCoefficientFibonacci(n));
		System.out.println("Calculated in "+(System.nanoTime()-a));
		
		System.out.print("With Eulera-Bineta equation: ");
		a = System.nanoTime();
		System.out.println(eulerBinetaEquation(n));
		System.out.println("Calculated in "+(System.nanoTime()-a));
		
	}
	/**
	 * (1/5^(1/2))*((1+5^(1/2))/2)^n-(1/5^(1/2))*((1-5^(1/2))/2)^n
	 * @param n
	 * @return
	 */
	private static long eulerBinetaEquation(double n) {
		return (long) (1.0/Math.sqrt(5)*Math.pow((1+Math.sqrt(5))/2.0,n)-1/Math.sqrt(5)*Math.pow((1-Math.sqrt(5))/2.0,n));
	}
	/**
	 * Sum i=1 .. floor((n+1)/2) (binomial[n-i,i-1])
	 * @param n
	 * @return
	 */
	private static long binomialCoefficientFibonacci(int n) {
		int f=0;
		for (int i=1;i<=Math.floor((n+1)/2);i++) {
			f+=binomialCoefficient(n-i, i-1);
		}
		return f;
	}
	/**
	 * 
	 * Binomial[n,m] = n!/((n-k)!*k!)
	 * @param n
	 * @param k
	 * @return binomial
	 */
	private static long binomialCoefficient(int n,int k) {
		return fractorial(n)/(fractorial(n-k)*fractorial(k));
	}
	/**
	 * @param i
	 * @return i!
	 */
	private static long fractorial(int i) {
		if(i==0) return 1;
		else if(i==1) return 1;
		else return i*fractorial(i-1);
	}
	/**
	 *  Recurion Fibonacci
	 *  Fn=Fn-1+Fn-2
	 * @param n
	 * @return
	 */
	private static long recursion(int n) {
		if(n==0) return 0;
		else if (n==1) return 1;
		else if (n==2) return 1;
		else return recursion(n-1)+recursion(n-2);
		
	}
	
	
}
