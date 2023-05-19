import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

/* 
interface IPlayersFinder {

    
     * Search for players locations at the given photo
     * @param photo
     *     Two dimension array of photo contents
     *     Will contain between 1 and 50 elements, inclusive
     * @param team
     *     Identifier of the team
     * @param threshold
     *     Minimum area for an element
     *     Will be between 1 and 10000, inclusive
     * @return
     *     Array of players locations of the given team
     
    java.awt.Point[] findPlayers(String[] photo, int team, int threshold);
}*/


public class tst3{

  

    public static int[][][] cal( LinkedList<Integer> l1 , LinkedList<Integer> l2, int row,int columns){
        int x = l1.getFirst();
        int y = l2.getFirst();
        LinkedList<Integer> lu1 = new LinkedList<Integer>();
        LinkedList<Integer> lu2 = new LinkedList<Integer>();
        LinkedList<Integer> clone1 = (LinkedList) l1.clone();
        LinkedList<Integer> clone2 = (LinkedList) l2.clone();
        lu1.add(x);
        lu2.add(y);

            for(int j=1;j<l1.size();j++){
                if((l1.get(j)==x&&l2.get(j)==y+1)){
                    lu1.add(l1.get(j));clone1.set(j,0);
                    lu2.add(l2.get(j));clone2.set(j,0);}
                else if((l1.get(j)==x+1&&l2.get(j)==y)){
                     lu1.add(l1.get(j));clone1.set(j,0);
                     lu2.add(l2.get(j));clone2.set(j,0);}
                }
                int yi= lu1.size();

                 for(int j=1;j<l1.size();j++){
                    for(int i=1;i<lu1.size;i++){
                    x=lu1.get(i);
                    y=lu2.get(i);
                if(((l1.get(j)==x+1)&&(l2.get(j)==y))||((l1.get(j)==x-1)&&(l2.get(j)==y))){
                    lu1.add(l1.get(j));clone1.set(j,0);
                    lu2.add(l2.get(j));clone2.set(j,0);
                }
                else if(((l1.get(j)==x)&&(l2.get(j)==y+1))||((l1.get(j)==x)&&(l2.get(j)==y-1))){
                    lu1.add(l1.get(j));clone1.set(j,0);
                    lu2.add(l2.get(j));clone2.set(j,0);
                }
            }}
            for(int i=0;i<clone1.size();i++){
                if(clone1.get(i)==0&&clone2.get(i)==0){
                    clone1.remove(i);clone2.remove(i);i=0;}
                    if(clone1.get(0)==0&&clone2.get(0)==0){clone1.remove(0);clone2.remove(0);}
            }
            int[][][] ar = new int [1][lu1.size()][2];
            for(int i=0;i<lu1.size();i++){
                ar[0][i][0]=lu1.get(i);
                ar[0][i][1]=lu2.get(i);
            }
            System.out.println("=====>"+clone1);
            System.out.println("----><"+clone2);
            return ar;
            


    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String row = sc.nextLine();
        String[] frs = row.split(", ");
        int rows = Integer.parseInt(frs[0]);
        int columns =Integer.parseInt(frs[1]);
        char[][] tot = new char[rows][columns];
        LinkedList<Integer> l_x= new LinkedList<Integer>();
        LinkedList<Integer> l_y= new LinkedList<Integer>();
        for(int i=0;i<rows;i++){
            String roo = sc.nextLine();
            for(int j=0;j<columns;j++){
                tot[i][j] = roo.charAt(j);
            }
        }
        char player =  sc.next().charAt(0);
        int thrsh = sc.nextInt();
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(tot[i][j]==player){
                    l_x.add(i);
                    l_y.add(j);
                }
            }
        }

        int si = l_x.size();int iz=0;
        //while(si!=0){
        int[][][] end = tst3.cal(l_x,l_y,rows,columns);
       // si--;}
       int min = end[0][0][0];
       int max = min;
       int miny=end[0][0][1];
       int maxy= miny;
       for(int i=1;i<end[0].length;i++){
        if(end[0][i][0]>max){max=end[0][i][0];}
        if(end[0][i][0]<min){min=end[0][i][0];}
        if(end[0][i][1]>maxy){maxy=end[0][i][1];}
        if(end[0][i][1]<miny){miny=end[0][i][1];}
    }
    min=min*2+1;max=max*2+1;
    int val = (min+max)/2; 
    miny=miny*2+1;maxy=maxy*2+1;
    int valy = (miny+maxy)/2; 
        System.out.println(val +"-->&&<---" +valy);
      

        System.out.println("=====================");
        System.out.println(rows);
        System.out.println(columns); 
        System.out.println(Arrays.deepToString(tot));
        System.out.println(player);
        System.out.println(thrsh);
        System.out.print(l_x + ", ");
        System.out.println(l_y);
        System.out.println("=====================");
        System.out.println(Arrays.deepToString(end));



    }
    }



