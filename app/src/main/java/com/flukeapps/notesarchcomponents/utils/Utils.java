package com.flukeapps.notesarchcomponents.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.flukeapps.notesarchcomponents.model.Scene;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {

    private static List<String> tasks = Arrays.asList("Buy milk","Buy eggs","Do pushups","Write novel","Research");
    private static List<String> places = Arrays.asList("At work", "At supermarket", "At Starbucks", "On the bus", "At gym", "At KFC");

    public static Scene generateRandomScene(){

        ArrayList<String> tasks_list = new ArrayList<>(tasks);
        ArrayList<String> places_list = new ArrayList<>(places);

<<<<<<< HEAD
        String random_task = tasks_list.get(new Random().nextInt(tasks.size()));
=======
        String random_character1 = names_list.get(new Random().nextInt(names.size()));
        String random_character2 = names_list.get(new Random().nextInt(names.size()));
>>>>>>> f08aaca7a4d1508c27bb5cdbec25167e5d1402a5
        String random_location = places_list.get(new Random().nextInt(places.size()));

<<<<<<< HEAD
        return new Note(random_task,random_location,random_priority);
=======
        return new Scene(random_character1,random_character2,random_location);
>>>>>>> f08aaca7a4d1508c27bb5cdbec25167e5d1402a5
    }

    public static boolean isNetworkAvailable(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        try{
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

            return activeNetwork != null &&
                    activeNetwork.isConnectedOrConnecting();
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
