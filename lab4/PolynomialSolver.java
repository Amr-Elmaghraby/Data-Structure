import java.util.Scanner;
interface ILinkedList {

    public void add(int index, Object element);

    public void add(Object element);

    public Object get(int index);


    public void set(int index, Object element);

    public void clear();

    public boolean isEmpty();

    public void remove(int index);

    public int size();

    public ILinkedList sublist(int fromIndex, int toIndex);

    public boolean contains(Object o);
}

class Node {
    private int element;
    private Node next;

    Node(int element) {
        this.element = element;
        this.next = null;
    }

    public int getElement() {
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
        if (index >= size || index < 0) {
            error = true;
            return;

        }
        if (index == 0) {
            Node newNode = new Node((int) element);
            newNode.setNext(head);
            head = newNode;
            size++;
        } else {
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
    public void set(int index, Object element) {


        if (index >= size || index < 0) {


            error = true;
            return;
        } else {
            Node temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
            temp.setElement((int) element);
        }
    }

    @Override
    public void clear() {
        Node temp = head;
        Node nextNode;
        for (int i = 0; i < size; i++) {
            nextNode = temp.getNext();
            temp.setNext(null);
            temp = nextNode;

        }
        size = 0;

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
    public ILinkedList sublist(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex >= size || toIndex < fromIndex) {
            error = true;
            return null;
        }
        ILinkedList sublist = new SingleLinkedList();
        Node temp = head;
        for (int i = 0; i < fromIndex; i++) {
            temp = temp.getNext();
        }

        for (int i = fromIndex; i < toIndex + 1; i++) {
            sublist.add(temp.getElement());
            temp = temp.getNext();
        }
        return sublist;

    }

    @Override
    public boolean contains(Object o) {
        Node temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.getElement() == (int) o) return true;
            else temp = temp.getNext();

        }
        return false;
    }
}





interface IPolynomialSolver {
    /**
     * Set polynomial terms (coefficients & exponents)
     * @param poly: name of the polynomial
     * @param terms: array of [coefficients][exponents]
     */
    void setPolynomial(char poly, int[][] terms);

    /**
     * Print the polynomial in ordered human readable representation
     * @param poly: name of the polynomial
     * @return: polynomial in the form like 27x^2+x-1
     */
    String print(char poly);

    /**
     * Clear the polynomial
     * @param poly: name of the polynomial
     */
    void clearPolynomial(char poly);

    /**
     * Evaluate the polynomial
     * @param poly: name of the polynomial
     * @param value: the polynomial constant value
     * @return the value of the polynomial
     */
    float evaluatePolynomial(char poly, float value);

    /**
     * Add two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return the result polynomial
     */
    int[][] add(char poly1, char poly2);

    /**
     * Subtract two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return the result polynomial*/
    int[][] subtract(char poly1, char poly2);

    /**
     * Multiply two polynomials
     * @param poly1: first polynomial
     * @param poly2: second polynomial
     * @return: the result polynomial
     */
    int[][] multiply(char poly1, char poly2);
}


public class PolynomialSolver implements IPolynomialSolver {

    static ILinkedList A=new SingleLinkedList();
    static ILinkedList B=new SingleLinkedList();
    static ILinkedList C=new SingleLinkedList();
    static ILinkedList J=new SingleLinkedList();
    static boolean error=false;




    @Override
    public void setPolynomial(char poly, int[][] terms) {
        switch (poly) {
            case 'A':

                for (int i = 0; i < terms[1].length; i++) {
                    A.add(terms[0][i]);
                }

                break;
            case 'B':

                for (int i = 0; i < terms[1].length; i++) {
                    B.add(terms[0][i]);
                }
                break;
            case 'C':

                for (int i = 0; i < terms[1].length; i++) {
                    C.add(terms[0][i]);
                }

                break;
            case 'J':

                for (int i = 0; i < terms[1].length; i++) {
                    J.add(terms[0][i]);
                }

                break;

            default:
                System.out.print("Error");
        }

    }

    @Override
    public String print(char poly) {
        ILinkedList coeff = switchCase(poly);

        int size = coeff.size() - 1;
        String polynomial = "";
        for (int i = 0; i <= size; i++) {
            if ((int) coeff.get(i) == 0)
                continue;
            if (((int) coeff.get(i) > 0 && i != 0) && !(i == 1 && (int) coeff.get(0) == 0))
                polynomial = polynomial + "+";

            if (i == size)
                polynomial = polynomial + coeff.get(i);
            else if(i==size-1){
                if((int)coeff.get(i)==1)
                    polynomial=polynomial+("x");
                else if((int)coeff.get(i)==-1)
                    polynomial=polynomial+("-x");
                else
                    polynomial=polynomial+(coeff.get(i)+"x");
            }




            else if ((int) coeff.get(i) == 1)
                polynomial = polynomial + ("x^" + (size - i));
            else if ((int) coeff.get(i) == -1)
                polynomial = polynomial + ("-x^" + (size - i));

            else
                polynomial = polynomial + (coeff.get(i) + "x^" + (size - i));

        }
        return polynomial;
    }

    @Override
    public void clearPolynomial(char poly) {

        ILinkedList coeff = switchCase(poly);
        coeff.clear();

    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        ILinkedList coeff = switchCase(poly);
        float sum = 0;
        int size = coeff.size() - 1;
        for (int i = size; i >= 0; i--) {
            float x = ((float) Math.pow((double) value, (double) i));
            int y = (int) coeff.get(size - i);
            sum = sum + (x * y);

        }
        return sum;

    }

    @Override
    public int[][] add(char poly1, char poly2) {
        ILinkedList x = switchCase(poly1);
        ILinkedList y = switchCase(poly2);
        int size;
        int max, diff;
        if (x.size() > y.size()) {
            size = x.size();
            max = 1;
            diff = size - y.size();
        } else {
            size = y.size();
            max = 0;
            diff = size - x.size();
        }
        int[][] addition = new int[2][size];
        for (int i = 0; i < size; i++) {
            if (max == 1) {
                if (i < diff) {
                    addition[0][i] = (int) x.get(i);
                } else {
                    addition[0][i] = (int) y.get(i - diff) + (int) x.get(i);
                }
            }
            if (max == 0) {
                if (i < diff) {
                    addition[0][i] = (int) y.get(i);
                } else {
                    addition[0][i] = (int) y.get(i) + (int) x.get(i - diff);
                }
            }
        }
        return addition;
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        ILinkedList x = switchCase(poly1);
        ILinkedList y = switchCase(poly2);
        int size;
        int max, diff;
        if (x.size() > y.size()) {
            size = x.size();
            max = 1;
            diff = size - y.size();
        } else {
            size = y.size();
            max = 0;
            diff = size - x.size();
        }
        int[][] sub = new int[2][size];
        for (int i = 0; i < size; i++) {
            if (max == 1) {
                if (i < diff) {
                    sub[0][i] = (int) x.get(i);
                } else {
                    sub[0][i] = (int) x.get(i) - (int) y.get(i - diff);
                }
            }
            if (max == 0) {
                if (i < diff) {
                    sub[0][i] = -(int) y.get(i);
                } else {
                    sub[0][i] = (int) x.get(i - diff) - (int) y.get(i - diff);
                }
            }
        }
        return sub;
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        ILinkedList x = switchCase(poly1);
        ILinkedList y = switchCase(poly2);
        int[][] mul = new int[2][x.size() + y.size() - 1];
        for (int i = 0; i < mul[1].length; i++) {
            mul[0][i] = 0;
        }
        for (int i = 0; i < x.size(); i++) {
            for (int j = 0; j < y.size(); j++) {

                mul[0][i + j] += (int) x.get(i) * (int) y.get(j);
            }
        }
        return mul;
    }

    public ILinkedList switchCase(char Poly) {

        ILinkedList coeff = new SingleLinkedList();
        switch (Poly) {
            case 'A':
                coeff = A;
                break;
            case 'B':
                coeff = B;
                break;
            case 'C':
                coeff = C;
                break;
            case 'J':
                coeff = J;
                break;
            default:
                System.out.print("Error");
                error=true;
        }
        return coeff;
    }



        public static void main(String[] args) {
        error=false;

            try {
                Scanner input = new Scanner(System.in);
                IPolynomialSolver[] Polynomial = new IPolynomialSolver[3];
                boolean t = true;
                int iloop = -1;
                char polyn = 0;
                while ((t) && (iloop < 3) &&  !error && input.hasNextLine() ) {
                    String s = input.nextLine();
                    switch (s) {
                        case "set": {
                            iloop++;
                            if (iloop > 2) {
                                return;
                            }
                            polyn = input.nextLine().charAt(0);
                            if(polyn!='A' && polyn!='B' && polyn!='C'){
                                System.out.println("Error");
                                return;
                            }
                            String ar = input.nextLine().replaceAll("\\[|\\]", "");
                            String[] arr = ar.split(",");
                            int[][] iarr = new int[2][arr.length];
                            for (int i = 0; i < arr.length; i++) {
                                iarr[0][i] = Integer.parseInt(arr[i]);
                                iarr[1][i] = i;
                            }
                            Polynomial[iloop] = new PolynomialSolver();
                            Polynomial[iloop].setPolynomial(polyn, iarr);
                            break;
                        }
                        case "print": {
                            char pp = input.nextLine().charAt(0);
                            String sq = Polynomial[iloop].print(pp);
                            System.out.println(sq);
                            if (iloop >= 2) {
                                return;
                            }
                            break;
                        }
                        case "add": {
                            char[] poll = new char[2];
                            for (int i = 0; i < 2; i++) {
                                poll[i] = input.nextLine().charAt(0);
                                if(poll[i]!='A' && poll[i]!='B' && poll[i]!='C'){
                                    System.out.println("Error");
                                    return;
                                }
                            }
                            int[][] ad = Polynomial[iloop].add(poll[0], poll[1]);
                            char J = 'J';
                            Polynomial[iloop].setPolynomial(J, ad);
                            String si = Polynomial[iloop].print(J);

                            if(si.isEmpty())
                                System.out.println("[]");
                            else
                                System.out.println(si);
                            t = false;

                            break;
                        }
                        case "sub": {
                            char[] poll = new char[2];
                            for (int i = 0; i < 2; i++) {
                                poll[i] = input.nextLine().charAt(0);
                                if(poll[i]!='A' && poll[i]!='B' && poll[i]!='C'){
                                    System.out.println("Error");
                                    return;
                                }
                            }
                            int[][] sub = Polynomial[iloop].subtract(poll[0], poll[1]);
                            char J = 'J';
                            Polynomial[iloop].setPolynomial(J, sub);
                            String si = Polynomial[iloop].print(J);
                            if(si.isEmpty())
                                System.out.println("[]");
                            else
                            System.out.println(si);
                            t = false;
                            break;
                        }
                        case "mult": {
                            char[] poll = new char[2];
                            for (int i = 0; i < 2; i++) {
                                poll[i] = input.nextLine().charAt(0);
                                if(poll[i]!='A' && poll[i]!='B' && poll[i]!='C'){
                                    System.out.println("Error");
                                    return;
                                }
                            }
                            int[][] multi = Polynomial[iloop].multiply(poll[0], poll[1]);
                            char J = 'J';
                            Polynomial[iloop].setPolynomial(J, multi);
                            String si = Polynomial[iloop].print(J);
                            if(si.isEmpty())
                                System.out.println("[]");
                            else
                                System.out.println(si);
                            t = false;
                            break;
                        }
                        case "clear": {
                            char cClear = input.nextLine().charAt(0);
                            if(cClear!='A' && cClear!='B' && cClear!='C'){
                                System.out.println("Error");
                                return;
                            }

                            Polynomial[iloop].clearPolynomial(cClear);
                            String si = Polynomial[iloop].print(cClear);
                            if (si.isEmpty()) {
                                System.out.println("[]");
                            }
                            t = false;
                            break;
                        }
                        case "eval": {
                            char eEval = input.nextLine().charAt(0);
                            if(eEval!='A' && eEval!='B' && eEval!='C'){
                                System.out.println("Error");
                                return;
                            }
                            int value = input.nextInt();
                            int sum = (int)Polynomial[iloop].evaluatePolynomial(eEval, value);
                            System.out.println(sum);
                            t = false;
                            break;
                        }
                        default:
                            System.out.println("Error");
                            return;
                    }

                }

            } catch (Exception e) {
                System.out.println("Error");
            }

        }
    }