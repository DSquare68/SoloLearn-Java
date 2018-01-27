package Main;

import java.util.Scanner;
/*
  There are 10 types of people.
  These who understand binary number system, and these who don't understand binary number system
 */
public class Sinus {
	
	final static int STEPS=15;
	private static Scanner s; 
	public static void main(String[] args) {
		
		s = new Scanner(System.in);
		double x = s.nextDouble();
		double y=toRadian(x);
		System.out.println("Radian: "+x);
		System.out.println("With build in function from Math class to count sinus: ");
		System.out.println(Math.sin(x)+"\n");
		
		System.out.println("Standardized radian: "+y+"\n");
		System.out.println("Taylor's formula: ");
		System.out.println(taylorsFormula(y));
		System.out.println("Infinite product: ");
		System.out.println(infiniteProduct(y));
		System.out.println("Continued fraction:");
		System.out.println(continuedFraction(y));

	}
	private static double toRadian(double x) {
		while(x>Math.PI) {
			x-=2*Math.PI;
		}
		while(x<-Math.PI){
			x+=2*Math.PI;
		} 
		return x;
		
	}
	private static double continuedFraction(double x) {
		int i=0;
		double d=x/(1+fractionStep(x,i));
		return d;
	}

	private static double fractionStep(double x, int i) {
		if(i==STEPS-1) return 0;
		if(i==0)return x*x/((i+2)*(i+3)-x*x+fractionStep(x,++i));
		return ((i+3)*(i+4)*x*x/((i+5)*(i+6)-x*x)+fractionStep(x, ++i));
		
	}

	private static double infiniteProduct(double x) {
		double d =x;
		for(int i=1;i<STEPS+1;i++){
			d*=(1-x*x/(Math.PI*Math.PI*i*i));
		}
		return d;
	}

	private static double taylorsFormula(double x) {
		double d=0;
		for (int i=0;i<STEPS;i++){
			d+=Math.pow(-1, i)*Math.pow(x, 2*i+1)/factorial(2*i+1);
		}
		return d;
	}

	private static long factorial(int i) {
		long d =1;
		if(i==1) return 1;
		d*=i*factorial(i-1);
		return d;
	}

}
