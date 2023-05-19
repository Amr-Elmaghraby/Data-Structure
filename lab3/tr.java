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


public class tr{

	/*
       Implement your class here
    */
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
        System.out.println(l_x);
        int x = l_x.get(12);
        System.out.println(x);
        


        System.out.println(l_x);
 

    }
}



