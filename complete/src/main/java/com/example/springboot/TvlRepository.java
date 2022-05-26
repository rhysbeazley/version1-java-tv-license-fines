package com.example.springboot;

import java.util.*;

public class TvlRepository {

    // This is a very simple, in-memory type of database
    // Everytime the application is re-started the list will be empty
    // You could add items to the list here if you wanted some data to persist
    public static ArrayList<ArrayList<String>> userstore = new ArrayList<ArrayList<String>>();
    public static ArrayList<ArrayList<String>> userfines = new ArrayList<ArrayList<String>>();
    public static ArrayList<ArrayList<String>> finestore = new ArrayList<ArrayList<String>>();

    public static ArrayList<ArrayList<String>> getByEmail(String email) {

        // List<String> fine = Arrays.asList("", "", "", "", "", "", "", "", "");

        for (int x = 0; x < TvlRepository.finestore.size(); x++) {
            List<String> row = TvlRepository.finestore.get(x);
            if (email.equals(row.get(1))) {
                userfines.add(TvlRepository.finestore.get(x));
            }
        }

        return userfines;

    }

    public static List<String> getById(Integer id) {

        List<String> fine = Arrays.asList("", "", "", "", "", "", "", "", "");

        for (int x = 0; x < TvlRepository.finestore.size(); x++) {
            List<String> row = TvlRepository.finestore.get(x);
            // for (int y = 0; y < row.size(); y++) {
            if (id == Integer.parseInt(row.get(0))) {
                fine = TvlRepository.finestore.get(x);
            }
            // }
        }

        return fine;

    }

}
