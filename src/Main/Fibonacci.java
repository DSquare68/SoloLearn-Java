package Main;

import java.math.MathContext;
import java.util.Scanner;

public class Fibonacci {
	static int n;
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
	private static long eulerBinetaEquation(double n) {
		return (long) (1.0/Math.sqrt(5)*Math.pow((1+Math.sqrt(5))/2.0,n)-1/Math.sqrt(5)*Math.pow((1-Math.sqrt(5))/2,n));
	}
	private static long binomialCoefficientFibonacci(int n) {
		int f=0;
		for (int i=1;i<=Math.floor((n+1)/2);i++) {
			f+=binomialCoefficient(n-i, i-1);
		}
		return f;
	}
	private static long binomialCoefficient(int n,int k) {
		return fractorial(n)/(fractorial(n-k)*fractorial(k));
	}
	private static long fractorial(int i) {
		if(i==0) return 1;
		else if(i==1) return 1;
		else return i*fractorial(i-1);
	}
	private static long recursion(int n) {
		if(n==0) return 0;
		else if (n==1) return 1;
		else if (n==2) return 1;
		else return recursion(n-1)+recursion(n-2);
		
	}
	
	
}
