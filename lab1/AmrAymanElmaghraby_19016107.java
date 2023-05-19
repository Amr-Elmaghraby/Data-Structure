
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

interface ICalculator {
    //methods for adding and dividing
    public int add(int x, int y);
    public float divide(int x, int y) throws RuntimeException;
}
class Calculator implements ICalculator{
    //implementing methods from ICalculator to Calculator 
    //Adding
    public int add(int x,int y){
        int z = x + y;
        return z;
    }
    //Dividing
    public float divide(int x, int y){
        float yi = (float) y;
        float z = x / yi;
        return z; 
  }}
//main class
public class Cal{
    public static void main(String[] args){
        // defining operator , first and second numbers
        char op;
        int num1, num2;
        //try, catch to detect errors
        try{
        // creating an object of Scanner class
        Scanner inp = new Scanner(System.in);
        //taking input from user, defining first number then operation then second number
        num1 = inp.nextInt();
        op = inp.next().charAt(0);
        num2 = inp.nextInt();
            
        //creating object from class CAlculator
        Calculator Obj = new Calculator();
        switch (op){
                //case of adding, add and printout the result
            case '+':
                int out1 =Obj.add(num1,num2);
                System.out.println(out1);
                break;
                //case of dividing, divide and printout the result
            case '/':
            if(num2 == 0){
                System.out.println("Error");
                break;
            }
                float out2 =Obj.divide(num1,num2);
                System.out.println(out2);
                break;

        }}
        catch(Exception e){
            System.out.println("Error");
        }
    }    
}
