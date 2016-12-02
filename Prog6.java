/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog6;

import java.util.ArrayList;

/**
 *
 * @author Patrick
 */
public class Prog6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
      Queue stack;
      long startTime = System.nanoTime();  
      if(args.length>0){
             stack = new Queue(Integer.parseInt(args[0]));
            stack.populateMatrix(args[1]);
            stack.path();
       long endTime = System.nanoTime();
       long duration = (endTime - startTime);
          System.out.println("Time "+duration);
           
            
          
        }
        
        
    }
    
}
