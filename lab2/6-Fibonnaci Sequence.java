import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class a6{
    public int fibonacci(int N){
        if(N==0 || N==1)
            return 0;
        else if(N==2)
            return 1;
        else 
        return fibonacci(N -1) + fibonacci(N -2);

    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int N = scan.nextInt();
        int fib = new a6().fibonacci(N); 
        System.out.print(fib);
    }
}