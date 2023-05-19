import java.io.*; 
import java.util.*;
import java.math.*;
import java.text.*;
import java.util.regex.*;
public class a5 { //method for transposing
    public int[] transpose(int[] array,int N){
        int[] An = new int[array.length]; //array of integers to store transpose
        for(int i=0,k=0;i< N ; i ++ ){     //loop with i 0 -> N, as matrix is N*N
            for(int j=0;j<array.length;j +=N){ //loop wiht j with +4 to move as columns not rows 
                An[k++] = array[i+j];          //storing current element in the An with index of k
            }
        }
        return An;   //returning An 
    }
    public static void main(String[] args){
        try{
        Scanner sc = new Scanner(System.in); //scanning the input
        String sin = sc.nextLine().replaceAll("\\[|\\]",""); //storing the first line in sin deleting the "[]" at the first & end
        String[] s = sin.split(", ");  // spliting sin into elements 
        int[] arr = new int[s.length];   //creating array of int to store the elements as integers
        if(s.length == 1 && s[0].isEmpty())   // if s is empty print out []
            System.out.print("[[]]");
        else{
            System.out.print("[[");   //start printing the output with "["
            for(int i = 0; i < s.length; ++i)   //looping to store int elements inside arr from s with -> "parseInt"
                arr[i]= Integer.parseInt(s[i]); 
        int N = (int) Math.sqrt((double)arr.length);
        int[] res = new a5().transpose(arr,N);  // storing answer from the moveValue method inside res
        for(int i=0; i < res.length;i++){   // looping to output each element of res with ", " between each element (not putting it at the end)
            System.out.print(res[i]);       //printing out elements
            if((i+1) % N ==0 && (i+1) != Math.pow(N,2)){  //if current index + 1 is multiplication of N then close the array with "[" and open new array with "]" 
                System.out.print("], [");
                continue;     //continue not to print ", " twice between arrays
            }
            if(i != res.length -1)
                System.out.print(", ");

        }
        System.out.println("]]");

        }
    }  
        catch(Exception e){  
        System.out.println("Error"); //try& catch to detect any error and print error
        }
    }
}