package Main;
import java.util.Scanner;
import java.util.Random;

public class Matrix
{
    public static void main(String[] args) {
        Scanner s =new Scanner(System.in);
        int num = s.nextInt(); // the variable that we expect must be positive
        Random R = new Random();
        int[][] tab = new int[num][num];
        for(int i=0; i<num; i++){
           for(int j=0; j<num; j++){
              tab[i][j] = R.nextInt(100);
           }
        }
        printMatrix(tab,num);
        int det=0; // the determinant of the matrix
        int a;
        if(num==2||num==1) a=1; else a=num;
        det= getComplementMatrix(tab,num,-1,-1);
        System.out.println();
        int[][] deterTab = new int[num][num];
        for(int i=0;i<num;a=i++){
            for(int j=0;j<num;j++){
                deterTab[i][j]=getComplementMatrix(tab,num,i,j);
            }
        }
        deterTab=cofactor(deterTab);
        printMatrix(deterTab,num);
        deterTab=transpose(deterTab,num);
        printMatrix(deterTab,num);
        
        
    }
    private static int[][] adjugate(int[][] deterTab) {
    	int temp=0;
    	for(int i=0;i<deterTab.length/2+1;i++){
    		for(int j=0;j<deterTab.length;j++){
    			if(i<j){
    				if(j==deterTab.length) break;
    				temp=deterTab[j][i];
    				deterTab[j][i]=deterTab[i][j];
    				deterTab[i][j]=temp;
    			}
    		}
    	}
		return deterTab;
	}
	static void printMatrix(int[][] tab,int num){
         // code to present the Matrix
        for(int i=0; i<num; i++){
            System.out.print("|");
           for(int j=0; j<num; j++){
              if(tab[i][j]<10)
              System.out.print("  "+tab[i][j]+" ");
              else
               System.out.print(" "+tab[i][j]+" ");
           }
           System.out.println("|");
        }
    }
    static private int[][] transpose(int[][] tab,int num){
        int temp;
        for(int i=0;i<num;i++){
            for(int j=0;j<num;j++){
                if(j!=i&&i>=j){
                    temp=tab[i][j];
                    tab[i][j]=tab[j][i];
                    tab[j][i]=temp;
                }
            }
        }
        return tab;
       
    }
    /**
     * A method to count the minor of matrix
     * @param tab 
     * @param num size of a matrix
     * @param row location of a new value in matrix
     * @param col location of a new value in matrix
     * @return returns the determinant of minor
     */
    static private int getComplementMatrix(int[][] tab,int num,int row,int col){
        int a;    // helpfull variable (see next line) 
        if(num==2||num==1) a=1; //this is important because if we have Matrix 2x2 or 1x1 we need to calculate next loop only once
        else if(row==-1) a=num; //when row is -1 the method will calculate the determinant of a Matrix
        else a=num-1;  //when we are counting minors, we are counting the determinant a matrix one size less
        int det=0,k=0,l=0,i=0,j=0;
        for(int n=0;n<a-1;n++,i++){
            int x=1;
            int y=1;
            l=0;
            k=0;
            j=0;
            i=0;
            for(int m=0;m<a;m++,l++,k++,j++){
                if((j+i)%num==col) {i++; }
                if(k==row)  k++;
                if(num-l-1==row) l++;
                x*=tab[(j+i)%num][k%num];
                y*=tab[(j+i)%num][num-l-1];
            }
            det+=x-y;
        }
        return det;
    }
    static private int[][] cofactor(int[][] tab){
    	for(int i=0;i<tab.length;i++){
    		for(int j=0;j<tab[0].length;j++){
    			if((i%2)==1&&(j%2)==0){
    				tab[i][j]=-tab[i][j];
    			}
    		}
    	}
    	return tab;
    }
}