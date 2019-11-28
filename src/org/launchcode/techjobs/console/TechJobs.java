package org.launchcode.techjobs.console;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    private static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by:", actionChoices);

            if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {

                    //print jobs first mentioned here
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term: ");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) {

                    //here I am looking for a term
                    System.out.println("I am looking for " +  searchTerm);
                    // pass searchTerm to my new function.
                    // my new function return a list of jobs. OK?
                    printJobs(JobData.findByValue(searchTerm.toLowerCase()));
                } else {
                    printJobs(JobData.findByColumnAndValue(searchField, searchTerm));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        Integer choiceIdx;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        Integer i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (Integer j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            choiceIdx = in.nextInt();
            in.nextLine();

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    //code here
    // found hashmap outline in debugger for someJobs
    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {

        // someJobs is a list of 98 items.
        // each item is a HashMap.
        // each Hashmap has 5 atributes, position, name, employer...

        // left to right position >> key   -    ex: position type is key, name is a key

        // first check if someJobs is empty
        if(someJobs.size() == 0){
            //if size is 0 I print
            System.out.println("No Results");
        }
        //then loop thru all of  someJobs
        for (int i = 0; i < someJobs.size(); i++){
            //for each somejobs, I have a hashset, so for example someJobs.get(2) is a hashset
            // for example:
            // { "position type" => Web Fullstack
            //  "name" => Full Stack Engineer
            //

            //so if I get hashset.get(name) I get the name.

            //  doing someJobs.get(i).get("position type"))
            /// I search for someJob in position i, the "position type". Position Type from the hashset prints.

            System.out.println("************");
            System.out.println("position type: " + someJobs.get(i).get("position type"));
            System.out.println("name: " + someJobs.get(i).get("name"));
            System.out.println("employer: " + someJobs.get(i).get("employer"));
            System.out.println("location: " + someJobs.get(i).get("location"));
            System.out.println("core competency: " + someJobs.get(i).get("core competency"));
            System.out.println("************");
        }
    }
}
