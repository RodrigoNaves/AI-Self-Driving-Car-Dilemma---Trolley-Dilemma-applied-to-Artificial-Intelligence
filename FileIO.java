 /** Written by 
 *@author Rodrigo Naves Vargas
 * The University of Melbourne COMP90041
 * Final Project
 * June 2020
 */

import java.io.BufferedReader;
import java.io.FileReader;

public class FileIO {
    
    public void Welcomesaver()
    {
       try 
       {
           String welcomeInput = "welcome.ascii";
            BufferedReader br = new BufferedReader(new FileReader(welcomeInput)); 
            int iLine = 0;
            String line;
            while ((line = br.readLine()) != null) 
            {
                System.out.println( line);
                iLine++;
            }
            
            br.close();
        } 
       catch(Exception a)
       {
           System.out.println("Error in file \n");
       }
    
   }

} 