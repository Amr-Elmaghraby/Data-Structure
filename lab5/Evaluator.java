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
        if( ( (index>=size) && (size!=0) ) || ( index<0) ){
            error=true;
            return;

        }
        if(index==0){
            Node newNode= new Node((int)element);
            newNode.setNext(head);
            head=newNode;
            size++;
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
    public void printList(ILinkedList list){
        System.out.print("[");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i));

            if(i!=list.size()-1) System.out.print(", ");

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
     * @param element to insert element
     */

    public void push(Object element);

    /*** Tests if this stack is empty
     * @return true if stack empty
     */
    public boolean isEmpty();

    /***
     * get size of stack
     * @return size
     */
    public int size();
}
class MyStack implements IStack {

    static ILinkedList list = new SingleLinkedList();

    @Override
    public Object pop(){
        if(list.size()==0){
            System.out.println("Error");
            return 0;}
        list.remove(0);

        return list.get(0);
    }
    @Override
    public Object peek(){
        return list.get(0);

    }
    @Override
    public void push(Object element){
        list.add(0,element);
    }
    @Override
    public boolean isEmpty(){
        return list.isEmpty();
    }
    @Override
    public int size(){
        return (int)list.size();
    }
}
/***
 * @author Amr Elmaghraby
 */
interface IExpressionEvaluator {

    /**
     * Takes a symbolic/numeric infix expression as input and converts it to
     * postfix notation. There is no assumption on spaces between terms or the
     * length of the term (e.g., two digits symbolic or numeric term)
     *
     * @param expression infix expression
     * @return postfix expression
     */

    public String infixToPostfix(String expression);


    /**
     * Evaluate a postfix numeric expression, with a single space separator
     * @param expression postfix expression
     * @return the expression evaluated value
     */

    public int evaluate(String expression);

}

/***
 * @author Amr Elmaghraby
 * nklbsndlbkdrtnklnb drlk
 */
public class Evaluator implements IExpressionEvaluator {
    static int letters = 0;
    static int[] val;
    static char[] cha;
    static boolean tr=false;
    static ILinkedList tst = new SingleLinkedList();
    int let=0;
    int tpo=0;

    /**
     * Takes a symbolic/numeric infix expression as input and converts it to
     * postfix notation. There is no assumption on spaces between terms or the
     * length of the term (e.g., two digits symbolic or numeric term)
     *
     * @param expression infix expression
     * @return postfix expression
     */
    @Override
    public String infixToPostfix(String expression) {
        IStack stack = new MyStack();
        String res = new String("");
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '-' && expression.charAt(i + 1) == c) {
                i++;

                if (letters != 0) {
                    char tp0 = expression.charAt(i - 2);
                    if (tp0 == '+' || tp0 == '*' || tp0 == '/' || tp0 == '^') {
                        continue;
                    } else {
                        c = '+';
                    }
                } else {
                    continue;
                }
            }
            if (Character.isLetterOrDigit(c)) {
                res += c;
                for (int j = 0; j < tst.size(); j++) {
                    if (c == (char) ((int) tst.get(j))) {
                        let = 1;
                    }
                }
                if (let == 0) {
                    letters++;
                    tst.add((int) c);
                }
                let = 0;
            } else if (c == '(') {
                stack.push((int) c);
            } else if (c == ')') {
                while (!stack.isEmpty() && (char) (int) stack.peek() != '(') {
                    res += (char) (int) stack.peek();
                    stack.pop();
                    tpo = 1;
                }
                if (tpo == 0 || stack.isEmpty()) {
                    return "Error";
                }
                stack.pop();
                tpo = 0;
            } else if (c == '+' || c == '-' || c == '*' || c == '/' || c == '^') {
                if (i == expression.length() - 1 || (i == 0 && c != '-')) {
                    return "Error";
                }
                char ci = expression.charAt(i + 1);
                if (ci == '+' || ci == '*' || ci == '/' || ci == '^') {
                    return "Error";
                }
                while (!stack.isEmpty() &&
                        precedence(0, 0, c, 0) <= precedence(0, 0, (char) (int) stack.peek(), 0)) {
                    res += (char) (int) stack.peek();
                    stack.pop();
                }
                stack.push((int) c);
            } else {
                return "Error";
            }
        }
        while (!stack.isEmpty()) {
            if ((char) (int) stack.peek() == '(') {
                return "Error";
            }
            res += (char) (int) stack.peek();
            stack.pop();
        }

        return res;
    }

    /**
     * Evaluate a postfix numeric expression, with a single space separator
     * @param expression postfix expression
     * @return the expression evaluated value
     */
    @Override
    public int evaluate(String expression){
        int yy = 0;
        for(int i=0;i<cha.length;i++){
            for(int j=0;j<letters;j++){
                if(cha[i]==(char)((int)tst.get(j))){
                    yy++;
                }
            }
        }if(yy!=letters){tr=true;return 10;}
        IStack stack = new MyStack();
        int temp=0;
        if(expression.length()==1){
            return val[0];
        }
        for(int i=0;i<expression.length();i++){

            char c = expression.charAt(i);
            if(c=='+'||c=='-'||c=='*'||c=='/'||c=='^'){
                int tm=1;
                int[] par = {0,0};
                while(tm!=-1){
                    if(stack.isEmpty()){break;}
                    par[tm] = (int)stack.peek();
                    stack.pop();
                    tm--;
                }
                temp = precedence(par[0],par[1],c,1);
                stack.push(temp);
            }else{
                for(int j=0;j<val.length;j++){
                    if(c==cha[j]){
                        stack.push(val[j]);
                        break;
                    }
                }
            }
        }
        return temp;
    }

    /***
     * Get precedence of operator OR to calculate the operation
     * @param x first operand
     * @param y second operand
     * @param ope operator
     * @param prece to know to get precedence of operator or to calculate operation
     * @return precedence of operator or operation result
     */
    public int precedence(int x, int y,char ope,int prece){
        switch(ope){
            case'+':{
                if(prece ==0){return 1;}
                else{
                    return x+y;
                }
            }
            case'-':{
                if(prece ==0){return 1;}
                else{
                    return x-y;
                }
            }
            case'*':{
                if(prece ==0){return 2;}
                else{
                    return x*y;
                }
            }
            case'/':{
                if(prece ==0){return 2;}
                else{
                    if(y==0){System.out.println("Error");}
                    else{
                        return x/y;}
                }
            }
            case'^':{
                if(prece ==0){return 2;}
                else{
                    return (int)Math.pow((double)x,(double)y);
                }
            }
        }
        return -5;
    }

    /***
     * the main method
     * @param args main String
     */
    public static void main(String[] args) {
        try{Scanner input = new Scanner(System.in);
            String s = input.nextLine();
            if(s.isEmpty()){System.out.println("Error");return;}
            IExpressionEvaluator eval = new Evaluator();
            String res = eval.infixToPostfix(s);
            if(res=="Error"||letters==0){
                System.out.println("Error");
                return;}
            val = new int[letters];
            cha = new char[letters];
            for(int i=0; i<letters;i++){
                String str = input.nextLine();
                String[] se = str.split("=");
                cha[i] = se[0].charAt(0);
                val[i] = Integer.parseInt(se[1]);
            }
            int value = eval.evaluate(res);
            if(tr){System.out.println("Errortt");return;}
            System.out.println(res);
            System.out.println(value);  }
        catch(Exception e){System.out.println("Error");}
    }
}
