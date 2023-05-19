import java.io.*;
import java.util.*;
import java.math.*;
import java.text.*;
import java.util.regex.*;
public class a1 {
    public int[] reverse(int[] array){
        int[] x = new int[array.length];
        for(int i = 0; i< x.length ;++ i){
            x[i]=array[x.length - 1 - i];
        }
        return x;

    }
    public static void main(String[] args){
        try{
        Scanner sc = new Scanner(System.in);
        String sin = sc.nextLine().replaceAll("\\[|\\]","");
        String[] s = sin.split(", ");
        int[] arr = new int[s.length];
        if(s.length == 1 && s[0].isEmpty())
            arr = new int[]{};
        else{
            for(int i = 0; i < s.length; ++i)
                arr[i]= Integer.parseInt(s[i]);
        } 
        int[] res = new a1().reverse(arr);
        System.out.print("[");
        for(int i=0; i < res.length;i++){
            System.out.print(res[i]);
            if(i != s.length -1)
                System.out.print(", ");
        }
        System.out.println("]");
    }  catch(Exception e){
        System.out.println("Error");
        }
    }
}