import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

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
class Node{   //class of the single linked list node
    private int element;//defining element, next
    private Node next;
    Node(int element){//constructor of Node class
        this.element=element;//to define element with input element, next
        this.next=null;
    }
    public int getElement(){//getElement method to return Element
        return element;
    }

    public void setElement(int element) {//setElement method to set node with given element
        this.element = element;
    }

    public Node getNext() {//getNext method to return next node
        return next;
    }

    public void setNext(Node next) { //setNext method to set next pointer to another
        this.next = next;
    }
}


class SingleLinkedList implements ILinkedList {//class of SLL
    static boolean error=false;//boolean error as a flag to any error
    private Node head;//head ,tail nodes as begining and end of the SLL
    private Node tail;
    private int size;// size of the SLL
    public SingleLinkedList(){//constructor, defining (head& tail) to null and size to 0
        head=null;
        this.tail=null;
        size=0;
    }

    @Override
    public void add(int index, Object element) {//addToIndex method taking index and element given as parameters
        if(index==0){//if index is 0 add given element as a head 
            Node newNode= new Node((int)element);
            newNode.setNext(head);
            head=newNode;
            size++;
            return;
        }
        if(index>=size || index<0){//check index is valid 
            error=true; //if index is 0 add given element as a head node
            return;

        }
        else {
            Node temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.getNext();
            }
            Node nextNode = temp.getNext();//nextNode is the node at the given index to be "shifted to right" 
            Node newNode = new Node((int) element);// newNode is the given element to be inserted between temp and nextNode
            temp.setNext(newNode);
            newNode.setNext(nextNode);
            size++;//increasing size as an node was added to the SLL
        }
    }
    // addlast
    @Override
    public void add( Object element){//add an element at the end of the list
        Node newNode = new Node((int)element);
        if(head==null && tail==null)//if the list is empty"there's no list", create a list with the given element
        {
            head=newNode;
            tail=newNode;
            size++;
        }
        else{ //adding element after tail, then change tail to be the last node
            tail.setNext(newNode);
            tail=newNode;
            size++;
        }
    }
    @Override
    public Object get(int index){//get element at the given index
        if(index>=size || index<0) { //check index is valid
            error=true;
            return null;
        }

        Node temp=head;
        for (int i = 0; i <index ; i++) {
            temp=temp.getNext();
        }
        return temp.getElement();  //returning element

    }
    @Override
    public boolean isEmpty(){
        return size == 0;//if size =0 then it is empty 
    }
    @Override
    public void remove(int index){ //removing an element from list
        if( index<0 || index>=size)//check index
        {
            error=true;
            return;
        }
        Node temp = head;
        if(index==0){//if index=0 then remove head node, making next node as the new 
            head=head.getNext();
            temp.setNext(null);
            size--;

        }
        else {
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
    public int size(){
        return size;//returning size of SLL
    }

    public void printList(ILinkedList list){//method to print SLL
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));

            if(i!=list.size()-1) System.out.print(", ");//not to put a comma at the end of the list
            

        }
        System.out.println("]");
    }
}
interface IQueue{
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
public class LinkedListQueue implements IQueue{
    static ILinkedList list = new SingleLinkedList();
    /**
     * Inserts an item at the queue front.
     * @param item
     */
    public void enqueue(Object item){
        list.add(0,item);
    }
    /**
     * Removes the object at the queue rear and returnsit.
     * @return queue
     */
    public Object dequeue(){
        int size = (int)list.size();
        if(size==0){
            System.out.println("Error");
            System.exit(0);
        }
        list.remove(size-1);
        return list;
    }
    /**
     * Tests if this queue is empty
     * @return true if empty, false if not
     */
    public boolean isEmpty(){
        return list.isEmpty();
    }
    /**
     * get the number of elements in the queue
     * @return size
     */
    public int size(){
        return (int)list.size();
    }

       /***
     * tha main method
     * @param args
     */
    public static void main(String[] args){
        try{
            Scanner inp = new Scanner (System.in);
            String s = inp.nextLine().replaceAll("\\[|\\]","");
            String[] str = s.split(", "); 
            if( !(str.length==1 && str[0].isEmpty()) ) {
                for (int i = 0; i < str.length; i++) {
                    list.add(Integer.parseInt(str[i]));
                }
             }
             IQueue que = new LinkedListQueue();
             String q = inp.nextLine();
             switch(q){
                case"enqueue":{
                int x = inp.nextInt();
                que.enqueue(x);
                list.printList(list);
                break;}
                case"dequeue":{
                    que.dequeue();
                    list.printList(list);
                    break;
                }
                case"isEmpty":{
                    boolean check = que.isEmpty();
                    if(check == true){System.out.println("True");}
                    else{System.out.println("False");}
                    break;
                }
                case"size":{
                    int size = que.size();
                    System.out.println(size);
                    break;
                }
                default:{
                System.out.println("Error");}
             }
            }catch(Exception e){System.out.println("Error");}
    }
}