package com.flukeapps.notesarchcomponents.room;

import android.content.Context;
import android.os.AsyncTask;

import com.flukeapps.notesarchcomponents.model.Scene;
import com.flukeapps.notesarchcomponents.utils.Utils;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Scene.class, version = 4)
public abstract class SceneDatabase extends RoomDatabase {

    private static SceneDatabase instance;

    public abstract SceneDAO sceneDAO();

    public static synchronized SceneDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SceneDatabase.class, "scene_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sampleDataCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback sampleDataCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    //Random data for the DB first creation
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private SceneDAO sceneDAO;

        private PopulateDbAsyncTask(SceneDatabase db){
            sceneDAO = db.sceneDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i=0;i<5;i++){
                sceneDAO.insert(Utils.generateRandomScene());
            }
            return null;
        }
    }
}
