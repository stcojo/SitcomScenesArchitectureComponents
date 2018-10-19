package com.flukeapps.notesarchcomponents;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Toast;

import com.flukeapps.notesarchcomponents.adapter.SceneAdapter;
import com.flukeapps.notesarchcomponents.model.Scene;
import com.flukeapps.notesarchcomponents.model.SceneViewModel;
import com.flukeapps.notesarchcomponents.retrofit.RetrofitApi;
import com.flukeapps.notesarchcomponents.retrofit.RetrofitClient;
import com.flukeapps.notesarchcomponents.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
                sceneAdapter.setScenes(scenes);
            }
        });

        fab.setOnClickListener(view -> {
            sceneViewModel.insert();
          });

        fab.setOnLongClickListener(view -> {
            sceneViewModel.deleteAllScenes();
            return true;
        });
    }
}
