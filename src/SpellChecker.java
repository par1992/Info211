/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Patrick
 */
public class SpellChecker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        long words;
        long notWords;
        long comp;
        long notComp;
        
          
        
        
        Speller b= new Speller();
        b.createDictionary("random_dictionary.txt");

            
            b.makeOliver("oliver.txt");
            
            System.out.println("words found: "+ (double)b.words);
            System.out.println("words not found: " +(double)b.notWords);
            System.out.println("Average comparisons:  "+(double)b.comp/(double)b.words);
            System.out.println("Average comparisons not found: "+ (double)b.notComp/(double)b.notWords);
        }
    }

        
//            
//            
//    }
//    

