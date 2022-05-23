package com.example.springboot;

import java.util.*;

public class TodoRepository {

    // This is a very simple, in-memory type of database
    // Everytimt the application is re-started the list will be empty
    // You could add items to the list here if you wanted some data to persist
    public static ArrayList<ArrayList<String>> datastore = new ArrayList<ArrayList<String>>();

}

