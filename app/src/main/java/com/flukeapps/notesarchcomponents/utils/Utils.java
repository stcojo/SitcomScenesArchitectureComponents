package com.flukeapps.notesarchcomponents.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.flukeapps.notesarchcomponents.model.Note;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utils {

    private static List<String> tasks = Arrays.asList("Buy milk","Buy eggs","Do pushups","Write novel","Research");
    private static List<String> places = Arrays.asList("At work", "At supermarket", "At Starbucks", "On the bus", "At gym", "At KFC");

    public static Note generateRandomNote(){

        ArrayList<String> tasks_list = new ArrayList<>(tasks);
        ArrayList<String> places_list = new ArrayList<>(places);

        String random_task = tasks_list.get(new Random().nextInt(tasks.size()));
        String random_location = places_list.get(new Random().nextInt(places.size()));
        int random_priority = new Random().nextInt(10);

        return new Note(random_task,random_location,random_priority);
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
