package com.flukeapps.notesarchcomponents;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.flukeapps.notesarchcomponents.adapter.NoteAdapter;
import com.flukeapps.notesarchcomponents.model.Note;
import com.flukeapps.notesarchcomponents.model.NoteViewModel;
import com.flukeapps.notesarchcomponents.retrofit.RetrofitApi;
import com.flukeapps.notesarchcomponents.retrofit.RetrofitClient;
import com.flukeapps.notesarchcomponents.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
            if (Utils.isNetworkAvailable(this)){
                Toast.makeText(this, "Loading from server...", Toast.LENGTH_SHORT).show();
                fetchDataFromServer();
            } else {
                Toast.makeText(this, "Generating locally...", Toast.LENGTH_SHORT).show();
                Note note = Utils.generateRandomNote();
                noteViewModel.insert(note);
            }
        });

        fab.setOnLongClickListener(view -> {
            noteViewModel.deleteAllNotes();
            return true;
        });
    }

    //WRONG
    //TODO repository should choose
    private void fetchDataFromServer(){
        Retrofit retrofit = RetrofitClient.getClient();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<List<Note>> call = retrofitApi.getWebNotes();

        call.enqueue(new Callback<List<Note>>() {
            @Override
            public void onResponse(Call<List<Note>> call, Response<List<Note>> response) {
                List<Note> notes = response.body();
                for (int i=0;i<notes.size();i++){
                    noteViewModel.insert(notes.get(i));
                }
            }

            @Override
            public void onFailure(Call<List<Note>> call, Throwable t) {

            }
        });
    }

}
