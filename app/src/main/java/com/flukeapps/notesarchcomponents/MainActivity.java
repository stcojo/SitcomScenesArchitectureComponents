package com.flukeapps.notesarchcomponents;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.flukeapps.notesarchcomponents.adapter.NoteAdapter;
import com.flukeapps.notesarchcomponents.model.Note;
import com.flukeapps.notesarchcomponents.model.NoteViewModel;
import com.flukeapps.notesarchcomponents.room.NoteDAO;
import com.flukeapps.notesarchcomponents.room.NoteDatabase;
import com.flukeapps.notesarchcomponents.room.NoteRepository;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteViewModel noteViewModel;
    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        noteAdapter = new NoteAdapter();
        recyclerView.setAdapter(noteAdapter);

        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(@Nullable List<Note> notes) {
                noteAdapter.setNotes(notes);
            }
        });

        fab.setOnClickListener(view -> {
            Note note = new Note("Lambda note", "Note created from lambda using ARCH" , 10);
            noteViewModel.insert(note);
        });
    }

}
