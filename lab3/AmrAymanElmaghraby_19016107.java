import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


interface IPlayersFinder {   //IplayersFinder interface
    LinkedList F_player(char[][] tot,char player,int thres); //method to separate each chain
    void getChain(char[][]tot,int i,int j,char player);    //method to get each chain 
    void list(int thres); //method to get min and max values
}

public class amr{
    static boolean[][] visited;                             //define visited boolean array as public to use to know if the element was check before or not 
    static LinkedList<Integer> xi= new LinkedList<Integer>();  // linkedlist to stor x index of each element inside matrix
    static LinkedList<Integer> yi= new LinkedList<Integer>();  // linkedlist to stor y index of each element inside matrix
    static LinkedList<Integer> op= new LinkedList<Integer>();  // linkedlist to get the output value inside it without separating of x ,y 
    public static LinkedList F_player(char[][] tot,char player,int thres){
         for(int i=0;i<tot.length;i++){                       //looping with length of rows
            for(int j=0;j<tot[0].length;j++){              // length of culomns
                if(!visited[i][j]){                        //check if the element was checked before
                visited[i][j]=true;                      //if not check it and change it's boolean coffiecient inside visisted to true
                xi.add(i);                              //add element to linkedlist of x index value
                yi.add(j);                              // add to y index
                    if(tot[i][j]==player){              //if element is equal to the wanted value
                        getChain(tot,i,j,player);       //go to the chain function 
                        if(4*xi.size()>=thres){         // if the chain size is egger than threshold then pass it's values to list 
                        list(thres);}            
                    }                    
                }
                xi.clear();                      //clearing linkedlist after each chain waiting for the next chain
                yi.clear();  
            }
         }
        return op;    //returning op with the mean value
    }
    public static void getChain(char[][]tot,int i,int j,char player){
        if(j+1<tot[0].length&&!visited[i][j+1]){  // if the element to the right to the element = element = player value and wasn't checked before
            visited[i][j+1]=true;                 //add it's value to the chain and change it's statue to true
            if(tot[i][j+1]==player){             
                xi.add(i);
                yi.add(j+1);
                getChain(tot,i,(j+1),player);  //calling the method again to start with the new value add to the chain
            }
        }   //same for the four loops looping to the 4 directions rigth, left, down, up
        if(j-1>=0&&!visited[i][j-1]){
            visited[i][j-1]=true;
            if(tot[i][j-1]==player){
                xi.add(i);
                yi.add(j-1);
                getChain(tot,i,(j-1),player);
            }
        }
            
        if(i+1<tot.length&&!visited[i+1][j]){
            visited[i+1][j]=true;
            if(tot[i+1][j]==player){
                xi.add(i+1);
                yi.add(j);
                getChain(tot,(i+1),j,player);
            }
        }            
        if(i-1>=0&&!visited[i-1][j]){
            visited[i-1][j]=true;
            if(tot[i-1][j]==player){
                xi.add(i-1);
                yi.add(j);
                getChain(tot,(i-1),j,player);
            }
        }
    }
    public static void list(int thres){
        int minx =xi.get(0); // initializing each value with the first element value 
        int maxx =xi.get(0);
        int miny =yi.get(0);
        int maxy =yi.get(0);
        for(int i=1;i<xi.size();i++){     //storing the max, min value of both x, y
            if(xi.get(i)>maxx){maxx=xi.get(i);}
         else if(xi.get(i)<minx){minx=xi.get(i);}
            if(yi.get(i)>maxy){maxy=yi.get(i);}
         else if(yi.get(i)<miny){miny=yi.get(i);}
        }
        maxx=2*maxx+1;            //the equation to get the coffiecients of x, y correct
        minx=2*minx+1;
        maxy=2*maxy+1;
        miny=2*miny+1;
        int x = (maxy+miny)/2;op.add(x);    // for mean of x devide the max of y and min of y by 2 
        int y = (maxx+minx)/2;op.add(y);    // and vice versa for y
    }
 
    public static void main(String[] args) {
      try{
         Scanner sc = new Scanner(System.in); //tr, catch to get any error and taking input from user with scanner
        String F_L = sc.nextLine();             //storing the first line in F_L 
        String[] frs = F_L.split(", ");  //split with ", "
        int rows = Integer.parseInt(frs[0]);   // convert first element to rows and second to cculomns
        int columns =Integer.parseInt(frs[1]);
        if(rows==0 ||columns==0){              //if rows or culomns is zero print out empty 2d array
            System.out.println("[]");
            return;
        }
        char[][] tot = new char[rows][columns];       //stroing the matrix in tot
        for(int i=0;i<rows;i++){                      //looping with length of the rows
            String roo = sc.nextLine();               //each line inside roo
            for(int j=0;j<columns;j++){      //then separate each character by using of charAt and looping with length of columns
                tot[i][j] = roo.charAt(j);
            }
        }
        char player =  sc.next().charAt(0); // stroing next char inside player 
        int thrsh = sc.nextInt();    //storing next int in thrsh ('threshold')
        visited = new boolean[rows][columns];   //initialize visited boolean[][] with length of rows and columns 
        LinkedList val = F_player(tot,player,thrsh);  //creating linkedlist from output of F_plyaer method 
        int[][] output = new int[val.size()/2][2];   // convert linkedlist to 2d array
        for(int i=0;i<val.size()/2;i++){
            output[i][0]=(int)val.get(2*i);
            output[i][1]=(int)val.get(2*i+1);
        }
        Arrays.sort(output, Comparator.comparingDouble(o -> o[0]));   //sorting 2d-array with sort and comparator 
        if(output.length==0){System.out.println("[]");return;}   //if the player is not inside the input print out[]
        System.out.print("[(");   //printing out the wanted output in the correct form
        for(int i=0;i<output.length;i++){
            System.out.print(output[i][0]+", "+output[i][1]);
            if(i!=output.length-1){
            System.out.print("), (");}
        }
        System.out.println(")]");
    }
    catch(Exception e){System.out.println("[]");} //if any error was detected print out empty
  }
}