package com.flukeapps.notesarchcomponents.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "scene_table")
public class Scene {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String character1;
    private String character2;
    private String location;
    private int locationDrawable;

    public Scene(String character1, String character2, String location, int locationDrawable) {
        this.character1 = character1;
        this.character2 = character2;
        this.location = location;
        this.locationDrawable = locationDrawable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacter1() {
        return character1;
    }

    public void setCharacter1(String character1) {
        this.character1 = character1;
    }

    public String getCharacter2() {
        return character2;
    }

    public void setCharacter2(String character2) {
        this.character2 = character2;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getLocationDrawable() {
        return locationDrawable;
    }

    public void setLocationDrawable(int locationDrawable) {
        this.locationDrawable = locationDrawable;
    }

    @Override
    public String toString() {
        return "Scene{" +
                "id=" + id +
                ", character1='" + character1 + '\'' +
                ", character2='" + character2 + '\'' +
                ", location='" + location + '\'' +
                ", locationDrawable=" + locationDrawable +
                '}';
    }
}
