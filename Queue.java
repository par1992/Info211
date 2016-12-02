package prog6;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;
import java.io.*;

/**
 * Info 211
 * 12/2/16
 * Dr H
 *
 * @author Patrick Ray This program implements the traveling salemen algorithm
 * to find the best cost out of the cities.
 */
public class Queue {

    int citi; //cities
    int[][] adjecency;
    int bestcost = Integer.MAX_VALUE;
    ArrayList<Integer> bestpath;
    Stack<Integer> pathStack;
    boolean[] vistedcities;

    public Queue(int N) {
        citi = N;
        adjecency = new int[citi][citi];
        bestpath = new ArrayList<>();
        vistedcities = new boolean[N];
        pathStack= new Stack<>();
    }

    public void output() {
        
        for (int i = 0; i < pathStack.size(); i++) {
            System.out.print(pathStack.get(i) + ",");

        }
        System.out.println("Bestcost=:" + bestcost);
    }

    public void populateMatrix(String fname) {  //input String fname
        File f = new File(fname);               // output string populated with the file given by the user
        try {                                   // This method creates the string by reading the file an giveing the stroing elements 
            Scanner input = new Scanner(f);

            int value;
            for (int i = 0; i < citi && input.hasNext(); i++) {

                for (int j = i; j < citi && input.hasNext(); j++) {

                    if (i == j) {
                        adjecency[i][j] = 0;
                    } else {
                        value = input.nextInt();
                        adjecency[i][j] = value;
                        adjecency[j][i] = value;

                    }  // end if
                } //end for              

            }//end for

            input.close();
        } catch (IOException e) {
            System.out.println("File reading failed");

        }
        
        
    }

    public int cost(Stack <Integer> path) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {               // input ArrayList path 
            cost += adjecency[path.get(i)][path.get(i + 1)];    //output return the cost by searching through the array list
            // This method finds the cost throughout the array list.
        }
        if (path.size() == citi) {
            cost += adjecency[path.get(path.size() - 1)][0];
        }
        return cost;

    }
//
//    public void tspdf(ArrayList<Integer> partialTour, ArrayList<Integer> remainingCities) {  //input two arraylist
//        if (remainingCities.isEmpty()) {                                                     // output the output() if value cost is less than bestcost
//            int valuecost = cost(partialTour);                                                // otherwise it goes through recursion 
//            // This method compares the cost and finds the best out of all of the cities
//
//            if (valuecost < bestcost) {
//                bestpath = partialTour;
//                bestcost = valuecost;
//                output();
//            }
//
//        } else {
//            for (int i = 0; i < remainingCities.size(); i++) {
//                ArrayList<Integer> newPartialTour = new ArrayList<>(partialTour);
//                newPartialTour.add(remainingCities.get(i));
//                // cost(newPartialTour);
//
//                if (cost(partialTour) < bestcost) {
//                    ArrayList<Integer> newRemainingCities = new ArrayList<>(remainingCities);
//                    newRemainingCities.remove(remainingCities.get(i));
//                    // cost(newPartialTour);
//
//                    tspdf(newPartialTour, newRemainingCities);
//                }
//
//            }
//        }
//    }
    
       public void path() {                     //input: the file given by the user 
        int currentCity = 0;                    // output: the the best path to each city  
        pathStack.push(currentCity);            // Prints zero when the pathStack is empty
        int closestCity = 0;                    // This Method uses a queue to find the bestpath in the file 
        vistedcities[0] = true;
        boolean minFlag = false;
        System.out.println(currentCity);

        while (!pathStack.isEmpty()) {
            currentCity=pathStack.pop();
            int min = Integer.MAX_VALUE;
            for (int i = 1; i < vistedcities.length; i++) {
                if ((adjecency[i][currentCity] != 0) && (vistedcities[i] == false)) {
                    if (adjecency[i][currentCity] < min) {
                        min = adjecency[i][currentCity];
                        closestCity = i;
                        minFlag = true;
                    }
                }

            }
            if (minFlag) {
                vistedcities[closestCity] = true;
                pathStack.push(closestCity);
                System.out.println(closestCity);
                minFlag = false;
                continue;
            }
         

        }

    }

}
