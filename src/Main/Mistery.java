package Main;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Daniel
 *	Please try to figure it out how the algorithm works before reading the code :P
 */
public class Mistery {
	
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		Random r = new Random();
		int size = s.nextInt();
		size = size%2==0?size-1:size;
		int[][] square = makeMath(size,r.nextInt(10)+1);
		print(square);
	}
	/**
	 * @param size of an array
	 * @param base of an array
	 * @return calculated array
	 */
	private static int[][] makeMath(int size, int base) {
		int[][] result = new int[size][size];
		result[0][0]=base;
		result[0][1]=base+1;
		result[1][0]=base+1;
		result[1][size-1]=base+1;
		result[0][size-1]=base;
		result[0][size-2]=base+1;
		result[size-1][0]=base;
		result[size-1][1]=base+1;
		result[size-2][0]=base+1;
		result[size-1][size-1]=base;
		result[size-1][size-2]=base+1;
		result[size-2][size-1]=base+1;
		for(int i=2;i<size/2+1;i++) { 
		  	result[i][0]=result[i-1][0]+result[i-2][0];
			result[i][size-1]=result[i-1][size-1]+result[i-2][size-1];
		  	result[size-i-1][0]=result[size-i][0]+result[size-i+1][0];
		  	result[size-i-1][size-1]=result[size-i][size-1]+result[size-i+1][size-1];
		 }
		for(int i=0;i<size;i++)
			for(int j=1;j<(size)/2+1;j++) {
				if(j==1) {
					result[i][j]=result[i][j-1]+1;
					result[i][size-2]=result[i][size-1]+1;
				}
				if(j>1&&j<=size/2+1) { 
				    result[i][j]=result[i][j-1]+result[i][j-2];
				  	result[i][size-j-1]=result[i][size-j]+result[i][size-j+1];
			  }
			}
		return result;
	}
	private static void print(int[][] snake) {
		int max = (int) Math.pow(2, snake[0].length-1);
		int maxLenght = String.valueOf(max).length();
		for(int[] i : snake){
			for(int j : i) {
				int spaces = maxLenght-String.valueOf(j).length();
				System.out.print("[ ");
				for(int k=0;k<spaces;k++) 
					System.out.print(" ");
				System.out.print(j+" ]");
			}
			System.out.println("");
		}
		
	}
}
