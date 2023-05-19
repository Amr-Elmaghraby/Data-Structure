import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/**
 * @author Amr Ayman Elmaghraby
 */
interface IQueue {
    
  /**
     * Inserts an item at the queue front.
     * @param item
     */
  public void enqueue(Object item);
  /**
   * Removes the object at the queue rear and returnsit.
   * @return queue
   */
  public Object dequeue();
  /**
   * Tests if this queue is empty
   * @return true if empty, false if not
   */
  public boolean isEmpty();
  /**
   * get the number of elements in the queue
   * @return size
   */
  public int size();
}

public class ArrayQueue implements IQueue {
    static int n =100; //defining n to be the max size of array
    static int[] que = new int[100];
       // to make sure to initialize array elements with zeros ===> Arrays.fill(que,0);
    static int st=99; //two indicators for starts, end of queue
    static int en=99;
    /**
     * Inserts an item at the queue front.
     * @param item
     */
  public void enqueue(Object item){
    que[st] = (int) item ;
    st--;
  }
  /**
   * Removes the object at the queue rear and returnsit.
   * @return queue
   */
  public Object dequeue(){
    if(st == en){
        System.out.println("Error");
        System.exit(0);
    }
    que[en] = 0;
    en--;
    return en;
  }
  /**
   * Tests if this queue is empty
   * @return true if empty, false if not
   */
  public boolean isEmpty(){
    if(st == en){
        return true;}
    else{return false;}
  }
  /**
   * get the number of elements in the queue
   * @return size
   */
  public int size(){
    return (en-st);
  }
/**
 * method to print out queue
 * @param queue
 */
  static public void printqueue(int[] queue){
    System.out.print("[");
    for(int i=st+1;i<=en;i++){
        System.out.print(que[i]);
        if(i!=en){
            System.out.print(", ");
        }
    }
    System.out.println("]");
  }

    public static void main(String[] args) {
        try{Scanner inp = new Scanner(System.in);
        String in = inp.nextLine().replaceAll("\\[|\\]", "");
        String[] str = in.split(", ");
        if( !(str.length==1 && str[0].isEmpty()) ){
        for(int i=(str.length-1);i>=0;i--){
            que[st]= Integer.parseInt(str[i]);
            st--;
        } }
        IQueue qu = new ArrayQueue();
        String q = inp.nextLine();
             switch(q){
                case"enqueue":{
                int x = inp.nextInt();
                qu.enqueue(x);
                printqueue(que);
                break;}
                case"dequeue":{
                    qu.dequeue();
                    printqueue(que);
                    break;
                }
                case"isEmpty":{
                    boolean check = qu.isEmpty();
                    if(check == true){System.out.println("True");}
                    else{System.out.println("False");}
                    break;
                }
                case"size":{
                    int size = qu.size();
                    System.out.println(size);
                    break;
                }
                default:{
                System.out.println("Error");}
             }
            }catch(Exception e){System.out.println("Error");}

    }
}