package com.flukeapps.notesarchcomponents.utils;

import com.flukeapps.notesarchcomponents.R;
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
    private static List<Integer> places_drawables = Arrays.asList(R.drawable.sheldonspot, R.drawable.elevator, R.drawable.comiccon,
            R.drawable.pennyap, R.drawable.sheldonap, R.drawable.comicstore, R.drawable.cafeteria);

    public static Scene generateRandomScene(){

        ArrayList<String> names_list = new ArrayList<>(names);
        ArrayList<String> places_list = new ArrayList<>(places);
        ArrayList<Integer> places_drawables_list = new ArrayList<>(places_drawables);

        String random_character1 = names_list.get(new Random().nextInt(names.size()));
        String random_character2 = names_list.get(new Random().nextInt(names.size()));

        int randomLocationIndex = new Random().nextInt(places.size());
        String random_location = places_list.get(randomLocationIndex);
        int random_location_drawable = places_drawables_list.get(randomLocationIndex);

        return new Scene(random_character1,random_character2,random_location, random_location_drawable);
    }
}
