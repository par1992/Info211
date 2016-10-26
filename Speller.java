/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellchecker;

import java.io.*;
import java.util.*;

/**
 *
 * @author Patrick
 */
public class Speller {

    protected long words = 0;
    protected long notWords = 0;
    protected long comp = 0;
    protected long notComp = 0;
    int[]counter=new int[1];
    SpellClass[] dictionary = new SpellClass[26];

    public Speller() {
        for (int i = 0; i < dictionary.length; i++) {
            dictionary[i] = new SpellClass<String>();

        }

    }
//input: the file name given by the user
//output: a populated file filled with the words with in the file
// This method read the file and prints the word of the file and makeing all the words lowercase
 // input must be a string
    public void createDictionary(String fname) {
        File f = new File(fname);
        
        try {
            String l = " ";
            Scanner input = new Scanner(f);
            while (input.hasNextLine()) {
                l = input.nextLine().toLowerCase();
                dictionary[l.charAt(0) - 97].add(l);
            }
            input.close();
        } catch (Exception e) {
            System.out.println("file not found");
        }

    }
    // input The file which is given by the user
    // output The words found, words not found, comparisons found and the comparisons not found
    // This method reads the text document and counts how many words it can find and how many comparisons it does by checking it by letter by letter
    //input must be a string
    
    public void makeOliver(String fname) {
        File f = new File(fname);
        
        try {
            
            Scanner input = new Scanner(f);
            while (input.hasNext()) {
                String l = input.nextLine().toLowerCase();
                String[] sa = l.split(" ");
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < sa.length; i++) {
                  
                    for (int j = 0; j < sa[i].length(); j++) {

                        if (Character.isLetter(sa[i].charAt(j))) {
                            sb.append(sa[i].charAt(j));
                        }
                    }
                    //System.out.println(sb.toString());
                    
                    
                
                if (!sb.toString().isEmpty()) {

                    if (dictionary[sb.charAt(0) - 97].contains(sb.toString(),counter)) {
                        words++;
                        comp=comp + counter[0];

                    }
                    else{
                        notWords++;
                        notComp=notComp+ counter[0];
                    }
                }
                sb.setLength(0);

            }
            }
           
        } catch (Exception e) {
            System.out.println("file not found");
        }

    }

}
