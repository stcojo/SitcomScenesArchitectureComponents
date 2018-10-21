package com.flukeapps.notesarchcomponents.room;

import com.flukeapps.notesarchcomponents.model.Scene;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SceneDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Scene scene);

    @Update
    void update(Scene scene);

    @Delete
    void delete(Scene scene);

    @Query("DELETE FROM scene_table")
    void deleteAllScenes();

    @Query("SELECT * FROM scene_table")
    LiveData<List<Scene>> getAllScenes();
}
