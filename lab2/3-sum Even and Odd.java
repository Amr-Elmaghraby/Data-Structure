import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;
import java.util.regex.*;
public class a3 {
    public int[] reverse(int[] array){
        int even = 0, odd = 0;
        for(int i = 0; i< array.length ;++ i){
            if(array[i] % 2 ==0)
                even += array[i];
            else{
                odd += array[i];
            }
        }
        int[]  EvenOdd = {even , odd};
        return EvenOdd;
    }
    public static void main(String[] args){
        try{
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]","");
        String[] s = sin.split(", ");
        int[] arr = new int[s.length];
        if(s.length == 1 && s[0].isEmpty())
            System.out.print("[0, 0]");
        else{
            System.out.print("[");
            for(int i = 0; i < s.length; ++i)
                arr[i]= Integer.parseInt(s[i]); 
        int[] res = new a3().reverse(arr);
        System.out.print(res[0] + ", " + res[1]);
        System.out.println("]");
        }
    }  
        catch(Exception e){
        System.out.println("Error");
        }
    }
}