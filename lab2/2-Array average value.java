import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;
import java.util.regex.*;
public class a2 {
    public double reverse(int[] array){
        double sum = 0;
        for(int i = 0; i< array.length ;++ i){
            sum += array[i];
        }
        double AV = sum / array.length;
        return AV;

    }
    public static void main(String[] args){
        try{
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]","");
        String[] s = sin.split(", ");
        int[] arr = new int[s.length];
        if(s.length == 1 && s[0].isEmpty())
            System.out.println(0.0);
        else{
            for(int i = 0; i < s.length; ++i)
                arr[i]= Integer.parseInt(s[i]); 
        double res = new a2().reverse(arr);
        System.out.println(res);
        }
    }  
        catch(Exception e){
        System.out.println("Error");
        }
    }
}