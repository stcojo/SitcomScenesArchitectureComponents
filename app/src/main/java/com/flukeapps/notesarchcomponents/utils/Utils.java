package com.flukeapps.notesarchcomponents.utils;

import com.flukeapps.notesarchcomponents.model.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {

    private static List<String> names = Arrays.asList("Sheldon Cooper","Penny","Leonard Hofstadter","Howard Wolowitz","Rajesh Koothrappali");
    private static List<String> places = Arrays.asList("Sheldon's spot", "The elevator", "Pasadena", "Penny's apartment", "Sheldon's apartment", "Cal Tech");

    public static Note generateRandomNote(){

        ArrayList<String> names_list = new ArrayList<>(names);
        ArrayList<String> places_list = new ArrayList<>(places);

        String random_name = names_list.get(new Random().nextInt(names.size()));
        String random_location = places_list.get(new Random().nextInt(places.size()));
        int random_priority = new Random().nextInt(10);

        return new Note(random_name,random_location,random_priority);
    }
}
