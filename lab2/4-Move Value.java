import java.io.*; 
import java.util.*;
import java.math.*;
import java.text.*;
import java.util.regex.*;
public class a4 { 
    public int[] moveValue(int[] array, int value){ //method for moving value
        int j=0; //j to move to the previous element of the array as we store the "value element" at the end
        int[] Ans= new int[array.length]; //array to contain the answer 
        for(int i = 0,k= 0; i< array.length ;++ i){ //looping the whole elements of the array
            if(array[i] == value){    //check if the current element contain the value
                Ans[array.length-1-j] = value;           //if yes put the value in the last element of the array
                j++;                           //increase j with 1 to movw to the previous index
            }
            else{
                Ans[k++] = array[i];   //if the current element is not equal to the value then put the element in the Ans with index of 'k'
            }
        }
        return Ans;   //returning Ans from the method
    }
    public static void main(String[] args){
        try{
        Scanner sc = new Scanner(System.in); //scanning the input
        String sin = sc.nextLine().replaceAll("\\[|\\]",""); //storing the first line in sin deleting the "[]" at the first & end
        int mov = sc.nextInt();   //storing the next int in mov
        String[] s = sin.split(", ");  // spliting sin into elements 
        int[] arr = new int[s.length];   //creating array of int to store the elements as integers
        if(s.length == 1 && s[0].isEmpty())   // if s is empty print out []
            System.out.print("[]");
        else{
            System.out.print("[");   //start printing the output with "["
            for(int i = 0; i < s.length; ++i)   //looping to store int elements inside arr from s with -> "parseInt"
                arr[i]= Integer.parseInt(s[i]); 
        int[] res = new a4().moveValue(arr,mov);  // storing answer from the moveValue method inside res
        for(int i=0; i < res.length;i++){   // looping to output each element of res with ", " between each element (not putting it at the end)
            System.out.print(res[i]);
            if(i != res.length -1)
                System.out.print(", ");
        }
        System.out.println("]");
        }
    }  
        catch(Exception e){  
        System.out.println("Error"); //try& catch to detect any error and print error
        }
    }
}