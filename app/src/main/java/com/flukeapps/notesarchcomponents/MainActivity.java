package com.flukeapps.notesarchcomponents;

import android.os.Bundle;
import android.widget.Toast;

import com.flukeapps.notesarchcomponents.adapter.SceneAdapter;
import com.flukeapps.notesarchcomponents.model.Scene;
import com.flukeapps.notesarchcomponents.model.SceneViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private SceneViewModel sceneViewModel;
    private RecyclerView recyclerView;
    private SceneAdapter sceneAdapter;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        sceneAdapter = new SceneAdapter();
        recyclerView.setAdapter(sceneAdapter);

        sceneViewModel = ViewModelProviders.of(this).get(SceneViewModel.class);

        sceneViewModel.getAllScenes().observe(this, new Observer<List<Scene>>() {
            @Override
            public void onChanged(@Nullable List<Scene> scenes) {
                sceneAdapter.submitList(scenes);
            }
        });

        fab.setOnClickListener(view -> sceneViewModel.insert());

        fab.setOnLongClickListener(view -> {
            sceneViewModel.deleteAllScenes();
            return true;
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                sceneViewModel.delete(sceneAdapter.getSceneAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Scene deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }



}
