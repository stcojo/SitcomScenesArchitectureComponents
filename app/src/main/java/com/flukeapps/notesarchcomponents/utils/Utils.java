package com.flukeapps.notesarchcomponents.utils;

import com.flukeapps.notesarchcomponents.model.Scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {

    private static List<String> names = Arrays.asList("Sheldon Cooper","Penny","Leonard Hofstadter","Howard Wolowitz",
            "Raj Koothrappali");
    private static List<String> places = Arrays.asList("Sheldon's spot", "The elevator", "Comic con",
            "Penny's apartment", "Sheldon's apartment", "Comic store", "Cafeteria");
    public static String BASE_URL = "http://stefancojocaru.info/api/";

    public static Scene generateRandomScene(){

        ArrayList<String> names_list = new ArrayList<>(names);
        ArrayList<String> places_list = new ArrayList<>(places);

        int randomNr = new Random().nextInt(names.size());
        String random_character1 = names_list.get(randomNr);
        names_list.remove(randomNr);
        String random_character2 = names_list.get(new Random().nextInt(names.size()-1));
        String random_location = places_list.get(new Random().nextInt(places.size()));
        int randomId = new Random().nextInt(100);
        return new Scene(randomId, random_character1,random_character2,random_location);
    }
}
