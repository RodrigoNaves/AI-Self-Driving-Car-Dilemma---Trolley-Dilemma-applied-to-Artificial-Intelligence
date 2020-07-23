 /* Written by 
 *@author Rodrigo Naves Vargas
 * The University of Melbourne COMP90041
 * Final Project
 * June 2020
 */


/**
 *
 * @author Rodrigo
 */

import java.util.Scanner;
public class ScanningInput {
    static Scanner ScanningInput = new Scanner(System.in);
    
    public static String ScanningInput()
    {
        return ScanningInput.next();
    }
    
    public static String nextLine()
    {
        return ScanningInput.nextLine();
    }
    
    public static int nextInt()
    {
        return ScanningInput.nextInt();
    }
    public static void reset()
    {
        ScanningInput.reset();
    }
    public static boolean hasNextInt()
    {
       return ScanningInput.hasNextInt();
    }
}