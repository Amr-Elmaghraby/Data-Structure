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
     * Replaces the element at the specified position in this list with the
     * specified element.
     * @param index
     * @param element
     */
    public void set(int index, Object element);
    /**
     * Removes all the elements from this list.
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


public class SingleLinkedList implements ILinkedList {
    static boolean error=false;
    private Node head;
    private Node tail;
    private int size;
    public SingleLinkedList(){
        head=null;
        this.tail=null;
        size=0;
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
    public void add( Object element){
        Node newNode = new Node((int)element);
        if(head==null && tail==null)
        {
            head=newNode;
            tail=newNode;
            size++;
        }
        else{
            tail.setNext(newNode);
            tail=newNode;
            size++;
        }
    }
    @Override
    public Object get(int index){
        if(index>=size || index<0) {
            error=true;
            return null;
        }

        Node temp=head;
        for (int i = 0; i <index ; i++) {
            temp=temp.getNext();
        }
        return temp.getElement();

    }
    @Override
    public void set(int index, Object element) {


        if (index >= size || index < 0) {


            error = true;
            return;
        }
        else {
            Node temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            temp.setElement((int) element);
        }
    }
    @Override
    public void clear(){
        Node temp=head;
        Node nextNode;
        for (int i = 0; i < size; i++) {
            nextNode=temp.getNext();
            temp.setNext(null);
            temp=nextNode;

        }
        size=0;

    }
    @Override
    public boolean isEmpty(){
        return size == 0;
    }
    @Override
    public void remove(int index){
        if( index<0 || index>=size)
        {
            error=true;
            return;
        }
        Node temp = head;
        if(index==0){
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
        return size;
    }
    @Override
    public ILinkedList sublist(int fromIndex, int toIndex){
        if(fromIndex<0 || toIndex>=size || toIndex<fromIndex )
        {
            error=true;
            return null;
        }
        ILinkedList sublist= new SingleLinkedList();
        Node temp =head;
        for (int i = 0; i < fromIndex; i++) {
            temp=temp.getNext();
        }

        for (int i =fromIndex; i <toIndex+1 ; i++) {
            sublist.add(temp.getElement());
            temp=temp.getNext();
        }
        return sublist;

    }
    @Override
    public boolean contains(Object o){
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if(temp.getElement()== (int)o )return true;
            else temp=temp.getNext();

        }
        return false;
    }

    public void printList(ILinkedList list){
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));

            if(i!=list.size()-1) System.out.print(", ");

        }
        System.out.println("]");
    }
    /***
     * tha main method
     * @param args
     */
    public static void main(String[] args) {
        try {
            Scanner inp = new Scanner(System.in);
            String s = inp.nextLine().replaceAll("\\[|\\]", "");
            String[] str_elements = s.split(", ");

            ILinkedList list = new SingleLinkedList();
            if( !(str_elements.length==1 && str_elements[0].isEmpty()) ) {


                for (int i = 0; i < str_elements.length; i++) {
                    list.add(Integer.parseInt(str_elements[i]));
                }
            }


            String func = inp.nextLine();
            switch (func) {
                case "add": {
                    int x = inp.nextInt();
                    list.add(x);
                    list.printList(list);
                    break;
                }
                case "addToIndex": {
                    int index = inp.nextInt();
                    int x = inp.nextInt();
                    list.add(index, x);
                    if(error==true) System.out.println("Error");
                    else
                        list.printList(list);
                    break;
                }
                case "get": {
                    int index = inp.nextInt();
                    int x =(int)list.get(index);
                    if(error==true) System.out.println("Error");
                    else
                        System.out.println(x);
                    break;
                }
                case "set": {
                    int index = inp.nextInt();
                    int x = inp.nextInt();
                    list.set(index, x);
                    if(error==true) System.out.println("Error");
                    else
                        list.printList(list);
                    break;
                }
                case "clear": {
                    list.clear();
                    list.printList(list);
                    break;
                }
                case "isEmpty":
                    if (list.isEmpty() == false) System.out.println("False");
                    else System.out.println("True");
                    break;
                case "remove":
                    int index = inp.nextInt();
                    list.remove(index);
                    if(error==true) System.out.println("Error");
                    else
                        list.printList(list);
                    break;
                case "sublist":
                    int index1 = inp.nextInt();
                    int index2 = inp.nextInt();
                    ILinkedList sub = list.sublist(index1, index2);
                    if(error==true) System.out.println("Error");
                    else
                        list.printList(sub);
                    break;
                case "contains":
                    int x = inp.nextInt();
                    if (list.contains(x) == false) System.out.println("False");
                    else System.out.println("True");
                    break;

                case "size":
                    System.out.println(list.size());
                    break;
                default:
                    System.out.println("Error");
            }
        }catch (Exception e){
            System.out.println("Error");
        }
    }
}