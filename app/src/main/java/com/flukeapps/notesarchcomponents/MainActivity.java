package com.flukeapps.notesarchcomponents;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.flukeapps.notesarchcomponents.adapter.SceneAdapter;
import com.flukeapps.notesarchcomponents.model.Scene;
import com.flukeapps.notesarchcomponents.model.SceneViewModel;
import com.flukeapps.notesarchcomponents.settings.SettingsActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import androidx.annotation.NonNull;
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
        addSwipeFunctionality(recyclerView);

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
    }

    public void addSwipeFunctionality(RecyclerView recyclerView){
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                sceneViewModel.delete(sceneAdapter.getSceneAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Scene deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_scenes:
                sceneViewModel.deleteAllScenes();
                return true;
            case R.id.menu_settings:
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
