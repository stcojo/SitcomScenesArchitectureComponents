package com.flukeapps.notesarchcomponents.room;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.flukeapps.notesarchcomponents.model.Note;

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDAO noteDAO();

    //Only gets called on first run of the application
    public static synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private NoteDAO noteDAO;

        private PopulateDbAsyncTask(NoteDatabase db){
            noteDAO = db.noteDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDAO.insert(new Note("Sheldon Cooper", "Main character", 1));
            noteDAO.insert(new Note("Penny", "Main character", 1));
            noteDAO.insert(new Note("Leonard Hofstadter", "Main character", 1));
            noteDAO.insert(new Note("Howard Wolowitz","Main character", 5));
            noteDAO.insert(new Note("Rajesh Koothrappali", "Main character", 3));
            return null;
        }
    }
}