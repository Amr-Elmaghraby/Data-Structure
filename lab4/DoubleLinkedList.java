import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;
/***
 * @author Amr Elmaghraby
 */
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
    * Replaces the element at the specified position in this list with the
    * specified element.
    * @param index
    * @param element
    */
    public void set(int index, Object element);
    /**
    * Removes all of the elements from this list.
    */
    public void clear();
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
    /**
    * @param fromIndex
    * @param toIndex
    * @return a view of the portion of this list between the specified fromIndex and toIndex, inclusively.
    */
    public ILinkedList sublist(int fromIndex, int toIndex);
    /**
    * @param o
    * @return true if this list contains an element with the same value as the specified element.
    */
    public boolean contains(Object o);
    }
class DNode{                              //class of the duble linked list node
    private int element;                  //defining element, next, prev
    private DNode next,prev;
    DNode(int element){                   //constructor of DNode class 
        this.element=element;               //to define element with input element, (next & prev) to null
        this.next=null;
        this.prev=null;
    }
    public int getElement(){              //getElement method to return Element
        return element;
    }
    public void setElement(int New_Element){  //setElement method to set node with given element
        this.element=New_Element;
    }
    public DNode getNext(){                   //getNext method to return next node
        return next;
    }
    public void setNext(DNode New_Next){      //setNext method to set next pointer to another node
        this.next=New_Next;
    }
    public DNode getPrev(){                 //getPrev method to return prev node 
        return prev;
    }
    public void setprev(DNode New_Prev){  //setPrev method to set prev pointer to another node
        this.prev=New_Prev;
    }

}
/**
 * @author Amr Elmaghraby
 */
public class DoubleLinkedList implements ILinkedList{   //class of DLL
    static boolean error=false;                       //boolean error as a flag to any error
    private DNode head,tail;                          //head ,tail nodes as begining and end of the DLL
    private int size;                                //getting size of the DLL
    public DoubleLinkedList(){                     //constructor, defining (head& tail) to null and size to 0
        this.head=null;
        this.tail=null;
        size=0;
    }
    @Override
    public void add(int index,Object element){       //addToIndex method taking index and element given as parameters
        if(index==0){                                  //if index is 0 add given element as a head node
            DNode newNode= new DNode((int)element);
            newNode.setNext(head);
            newNode.setprev(null);
            head.setprev(newNode);
            head=newNode;
            size++;
            return;
        }
    if(index>=size||index<0){error=true;return;}    //check index is valid 
    else{                                     
        DNode temp;
        if(index<=size/2){             //check if given index is at the first or at the end of the list, to decide to loop from head or tail
            temp=Looping_Next(index-1); } 
        else{
             temp=Looping_Prev(index-1);
        }  
        DNode nextNode=temp.getNext();     //nextNode is the node at the given index to be "shifted to right" 
        DNode newNode=new DNode((int)element); // newNode is the given element to be inserted between temp and nextNode
        temp.setNext(newNode);
        newNode.setNext(nextNode);
        newNode.setprev(temp);
        nextNode.setprev(newNode);
        size++;       //increasing size as an node was added to the DLL
        }
    }
    @Override
    public void add(Object element){   //add an element at the end of the list
        DNode newNode=new DNode((int)element);
        if(head==null&&tail==null){          //if the list is empty"there's no list", create a list with the given element
            head=newNode;
            tail=newNode;
            size++;
        } 
        else{
            tail.setNext(newNode);           //adding element after tail, then change tail to be the last node
            newNode.setprev(tail);
            tail=newNode;
            size++;
        }
    }
    public Object get(int index){                           //get element at the given index
        if(index<0||index>=size){error=true;return null;}   //check index is valid
        DNode temp;
        if(index<=size/2){                      //check to loop from tail or head
            temp=Looping_Next(index); } 
        else{
             temp=Looping_Prev(index);
        } 
        return temp.getElement();             //returning element
    } 
    @Override
    public void set(int index,Object element){   //change element at the given index to the given element
        if(index<0||index>=size){error=true;return;}    //chekc index is valid
        DNode temp;
        if(index<=size/2){                    //check to loop with head or tail
            temp=Looping_Next(index); } 
        else{
             temp=Looping_Prev(index);
        } 
        temp.setElement((int)element);   //changing element 
    }
    @Override
    public void clear(){           //clearing list
        DNode temp = head;
        for(int i=0;i<size-1;i++){
            temp= temp.getNext();
            temp.setprev(null);
        }size=0;
    }
    @Override
    public boolean isEmpty(){   
         return size==0;       //if size =0 then it is empty 
    }
    @Override
    public void remove(int index){                   //removing an element from list
        if(index<0||index>=size){error=true;return;}  //check index
        DNode temp=head;
        if(index==0){           //if index=0 then remove head node, making next node as the new head
            head=head.getNext();
            head.setprev(null);
            temp.setNext(null);
        }
        else if(index==size-1){              //if given index is the last element, remove tail, aking prev node as the new tail
            temp=tail;
            tail=tail.getPrev();
            tail.setNext(null);
            temp.setprev(null);
        }
        else{                                  //other than that remove node by setting it's next and prev to null, changing prev and next of it's arounding nodes
            if(index<=size/2){
                temp=Looping_Next(index); } 
            else{
                 temp=Looping_Prev(index);
            }
        DNode nextNode=temp.getNext();
        DNode prevNode=temp.getPrev();
        nextNode.setprev(prevNode);
        prevNode.setNext(nextNode);
        temp.setNext(null);
        temp.setprev(null);
        }
        size--;
    }
    @Override
    public int size(){return size;}                //returning size of DLL
    @Override
    public ILinkedList sublist(int fromindex,int toindex){       //sublist of the list 
        if(fromindex<0||toindex>size-1||toindex<fromindex){
            error=true;
            return null;}
        ILinkedList sublist=new DoubleLinkedList();    //create new DLL
        DNode temp;
        if(fromindex<=size/2){
            temp=Looping_Next(fromindex); }    //check looping
        else{
             temp=Looping_Prev(fromindex);
        }
        for(int i=fromindex;i<toindex+1;i++){
            sublist.add(temp.getElement());    //set sublist nodes of the original DLL
            temp=temp.getNext();                
        }
        return sublist;
    }
    @Override
    public boolean contains(Object o){    //check if the list contains the given element 
        DNode temp = head;
        for (int i=0;i<size;i++){                   
        if(temp.getElement()==(int)o)return true;  
        else temp=temp.getNext();
        }
        return false;
    }
    /***
     * method to reach wanted node from head if index is smaller than or equal to half of the list
     * @param index index of node
     * @return node
     */
    public DNode Looping_Next(int index){   //reaching wanted node from head
        DNode temp=head;
        for(int i=0;i<index;i++){
            temp=temp.getNext();
        }
        return temp;
    }
    /***
     * ethod to reach wanted node from tail if index is bigger than half of the list
     * @param index
     * @return
     */
    public DNode Looping_Prev(int index){ //reaching wanted node from tail 
        DNode temp=tail;
        for(int i=size-1;i>index;i--){
            temp=temp.getPrev();
        }
        return temp;
    }
    /***
     * printing linked list
     * @param list  Double linked list
     */
    public static void printList(ILinkedList list){  //method to print DLL
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));

            if(i!=list.size()-1) System.out.print(", "); //not to put a comma at the end of the list
            
        }
        System.out.println("]");
    }

    /***
     * the main method
     * @param args
     */
    public static void main(String[] args) {
        try{ Scanner input = new Scanner(System.in);
        String s =input.nextLine().replaceAll("\\[|\\]", ""); //saving given list and removing"[]"
        String[] str_element=s.split(", ");  //getting each element of the list 

        ILinkedList list = new DoubleLinkedList();   //creating DLL
        if( !(str_element.length==1 && str_element[0].isEmpty()) ) { //check given list is valid
        for(int i=0;i<str_element.length;i++){
            list.add(Integer.parseInt(str_element[i]));          //add each element of given list to DLL
        }
      }
        
        String func=input.nextLine();  //saving method to be done on the DLL
        switch (func){ //to know which method to be performed
            case "add":{
                int x = input.nextInt(); //element to be add to the list
                list.add(x);
                printList(list);
                break;
            }
            case "addToIndex": {   
                int index = input.nextInt(); //index to add element in 
                int x = input.nextInt();
                list.add(index, x);
                if(error==true)System.out.println("Error"); //print an error if there was an error in the method
                else{
                printList(list);}
                break;
            }
            case "get":{
                int index = input.nextInt();  //index to get it's element
                int x=(int)list.get(index);
                if(error==true)System.out.println("Error");
                else{
                System.out.println(x);}
                break;
            }
            case"set":{
                int index=input.nextInt();   //index to change it's value to the given element "x"
                int x=input.nextInt();
                list.set(index,x);
                if(error==true)System.out.println("Error");
                else{
                    printList(list);
                }
                break;
            }
            case"clear":{
                list.clear(); //just clear all the list
                printList(list);
                    break;
            }
            case"isEmpty":{
                if(list.isEmpty()==false)System.out.println("False"); //print False or True if list is empty or not respectively
                else{System.out.println("True");}
                break;
            }
            case"remove":{
                int index=input.nextInt();  //index to remove it's element
                list.remove(index);
                if(error==true)System.out.println("Error");
                else{printList(list);}
                break;
            }
            case"sublist":{
                int index1=input.nextInt();   //first and last elements in the sublist
                int index2=input.nextInt();
                ILinkedList sub=list.sublist(index1, index2);
                if(error==true)System.out.println("Error");
                else{printList(sub);}
                break;
            }
            case"contains":{
                int x=input.nextInt();    //element to check if it's in the list
                if(list.contains(x)==false)System.out.println("False");
                else{System.out.println("True");}
                break;
            }
            case"size":{
                System.out.println(list.size());
                break;
            }
            default:
            System.out.println("Error"); //if no case was entered then there's an error in the input given
        }
       }catch(Exception e){
        System.out.println("Error"); //print Error if there's any error occured during taking input
        }
    }
}
