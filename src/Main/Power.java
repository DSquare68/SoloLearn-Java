package Main;

import java.util.Scanner;

public class Power {
	
	private static final int STEPS=20;
	private static final int STEPS2=2000;
	private static final double LOGE2=0.69314718;
	public static void main(String[] args) {
		//enter two numbers(double) base(b) and exponent(e) b^e
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		double base = s.nextDouble();
		double exponent = s.nextDouble();
		System.out.println(base+"^"+exponent+"=");
		System.out.println("My function:");
		System.out.println(elementPowerSeries(exponent*logMultiplication(base), STEPS));
		System.out.println("With math class:");
		System.out.println(Math.pow(base, exponent));
	}
	/**
	 *  Log(a*b)=log(a)+log(b)
	 * @param l
	 * @return if l>2 log(2)+logMulticaton(l/2)
	 * else elementMaclaurinSeries(l,STEPS2);
	 */
	private static double logMultiplication(double l) {
		if(l>2) return LOGE2+logMultiplication(l/2); else return elementMaclaurinSeries(l, STEPS2); 
	}
	/**
	 * Power Series
	 * @param p
	 * @param n - Steps
	 * @return SUM k=0..n (p^k/k!)
	 */
	private static double elementPowerSeries(double p, int n) {
		if (n==0) return 1;
		return pow(p,n)/fractorial(n)+elementPowerSeries(p, --n);
	}
	
	/**
	 * Maclaurin's Series
	 * @param m
	 * @param n
	 * @return SUM k=1..n ((-1)^(k+1)*(m-1)^n/n
	 */
	private static double elementMaclaurinSeries(double m, int n) {
		if(n==1) return m-1;
		return (n%2==0 ? -1 : 1)*pow(m-1,n)/n+elementMaclaurinSeries(m, --n);
	}
	
	/**
	 * @param a
	 * @param n
	 * @return a^n
	 */
	private static double pow(double a, int n) {
		if (n==1) return a;
		return a*pow(a,--n);
	}
	
	/**
	 * @param i
	 * @return i!
	 */
	private static long fractorial(int i) {
		if(i==0) return 1;
		else if(i==1) return 1;
		else return i*fractorial(--i);
	}
}
