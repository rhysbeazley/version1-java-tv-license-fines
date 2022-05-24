package com.example.springboot;

import java.util.*;

public class TvlRepository {

    // This is a very simple, in-memory type of database
    // Everytimt the application is re-started the list will be empty
    // You could add items to the list here if you wanted some data to persist
    public static ArrayList<ArrayList<String>> userstore = new ArrayList<ArrayList<String>>();
    public static ArrayList<ArrayList<String>> finestore = new ArrayList<ArrayList<String>>();

}
