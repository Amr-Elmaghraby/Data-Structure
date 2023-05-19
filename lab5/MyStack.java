import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

interface ILinkedList {

    /**
     * Inserts a specified element at the specified position in the list.
     * @param index
     * @param element
     */
    public void add(int index, Object element);
    /**
     * Inserts the specified element at the end of the list.
     * @param element
     */
    public void add(Object element);
    /**
     * @param index
     * @return the element at the specified position in this list.
     */
    public Object get(int index);

    /**
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();
    /**
     * Removes the element at the specified position in this list.
     * @param index
     */
    public void remove(int index);
    /**
     * @return the number of elements in this list.
     */
    public int size();

    /***
     * method to print linked list
     * @param list linked list
     */
    public void printList(ILinkedList list);

}
class Node{
    private int element;
    private Node next;
    Node(int element){
        this.element=element;
        this.next=null;
    }
    public int getElement(){
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}


class SingleLinkedList implements ILinkedList {
    static boolean error = false;
    private Node head;
    private Node tail;
    private int size;

    public SingleLinkedList() {
        head = null;
        this.tail = null;
        size = 0;
    }

    @Override
    public void add(int index, Object element) {
        if (index == 0) {
            Node newNode = new Node((int) element);
            newNode.setNext(head);
            head = newNode;
            size++;
            return;
        }
        
        if (index >= size || index < 0) {
            error = true;
            return;

        }
         else {
            Node temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            Node nextNode = temp.getNext();
            Node newNode = new Node((int) element);
            temp.setNext(newNode);
            newNode.setNext(nextNode);
            size++;
        }
    }

    // addlast
    @Override
    public void add(Object element) {
        Node newNode = new Node((int) element);
        if (head == null && tail == null) {
            head = newNode;
            tail = newNode;
            size++;
        } else {
            tail.setNext(newNode);
            tail = newNode;
            size++;
        }
    }

    @Override
    public Object get(int index) {
        if (index >= size || index < 0) {
            error = true;
            return null;
        }

        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        return temp.getElement();

    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            error = true;
            return;
        }
        Node temp = head;
        if (index == 0) {
            head = head.getNext();
            temp.setNext(null);
            size--;

        } else {


            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            Node nextNode = temp.getNext();
            temp.setNext(nextNode.getNext());
            nextNode.setNext(null);
            size--;

        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printList(ILinkedList list) {
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));

            if (i != list.size() - 1) System.out.print(", ");

        }
        System.out.println("]");
    }
}
/***
 * @author Amr Elmaghraby
 */
interface IStack {

    /*** Removes the element at the top of stack and returnsthat element.
     * @return top of stack element, or through exception if empty
     */

    public Object pop();

    /*** Get the element at the top of stack without removing it from stack.
     * @return top of stack element, or through exception if empty
     */

    public Object peek();

    /*** Pushes an item onto the top of this stack.
     * @param element to insert element to stack
     */

    public void push(Object element);

    /*** Tests if this stack is empty
     * @return true if stack empty
     */
    public boolean isEmpty();
    /***
     * get size of the stack
     * @return size of stack
     */

    public int size();
}
public class MyStack implements IStack {

    static ILinkedList list = new SingleLinkedList();

    /*** Removes the element at the top of stack and returnsthat element.
     * @return top of stack element, or through exception if empty
     */
    @Override
    public Object pop(){
        if(list.size()==0){
            System.out.println("Error");
            return 0;}
        list.remove(0);

        return list.get(0);
    }

    /*** Get the element at the top of stack without removing it from stack.
     * @return top of stack element, or through exception if empty
     */
    @Override
    public Object peek(){
        return list.get(0);

    }

    /*** Pushes an item onto the top of this stack.
     * @param element to insert element to stack
     */
    @Override
    public void push(Object element){
        list.add(0,element);
    }

    /*** Tests if this stack is empty
     * @return true if stack empty
     */
    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }

    /***
     * get size of the stack
     * @return size of stack
     */
    @Override
    public int size(){
        return (int)list.size();
    }

    /***
     * the main method
     * @param args
     */

    public static void main(String[] args) {
        try{
            Scanner input = new Scanner(System.in);
            String ss = input.nextLine().replaceAll("\\[|\\]","");
            String[] ele = ss.split(", ");
            if(!(ele.length==1 && ele[0].isEmpty())){
                for(int i=0;i<ele.length;i++){
                    list.add(Integer.parseInt(ele[i]));
                }
            }
            IStack stack = new MyStack();
            String meth =input.nextLine();
            switch (meth){
                case "push":{
                    int value = input.nextInt();
                    stack.push(value);
                    list.printList(list);
                    break;
                }
                case"pop":{
                    stack.pop();
                    list.printList(list);
                    break;
                }
                case"peek":{
                    int peek=(int) stack.peek();
                    System.out.println(peek);
                    break;
                }
                case"isEmpty":{
                    boolean bo = stack.isEmpty();
                    if(bo==true){System.out.println("True");}
                    else
                        System.out.println("False");
                    break;
                }
                case"size":{
                    int size = stack.size();
                    System.out.println(size);
                    break;
                }
                default:{
                    System.out.println("Error");
                }
            }

        }catch(Exception e){System.out.println("Error");}
    }
}