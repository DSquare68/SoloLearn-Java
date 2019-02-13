package Main;

import java.util.Scanner;

public class NumbersSnake {
		private static int size=1;
	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.out.println("Enter odd number");
		size = s.nextInt();
		int[][] snake= getSnakeRecursively(size, size);
		print(snake);
	}

	private static int[][] getSnakeRecursively(int lenght, int i) {
		if(i==1) {
			return new int[][] {{(int) Math.pow(lenght,2)}};
		} else {
			int[][] result = new int[i][i];
			int j=1;
			for( int[] row : getSnakeRecursively(lenght,i-2)) 
				result[j++] = rewrite(row);
			return fill(result);
		}
	}

	private static int[] rewrite(int[] row) {
		int k=1;
		int[] is = new int[row.length+2];
		for(int i : row)
			is[k++]=i;
		return is;
	}

	private static int[][] fill(int[][] result) {
		int i=1,j=0,value=0;;
		value=result[1][1];
		while(i!=0||j!=0) {
				result[i][j]=--value;
				if(i>=1&&i!=result.length-1&&j!=result.length-1) {
					i++;
				} else if(i==result.length-1&&j!=result.length-1) {
					j++;
				} else if(j==result.length-1&&i!=0) {
					i--;
				}else if(i==0&&j!=0) {
					j--;
				}
			}
		result[0][0]=--value;
		return result;
	}
	private static void print(int[][] snake) {
		int max = snake[0].length*snake[0].length;
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
