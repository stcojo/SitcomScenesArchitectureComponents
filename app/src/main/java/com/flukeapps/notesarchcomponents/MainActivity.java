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
            if (Utils.isNetworkAvailable(this)){
                Toast.makeText(this, "Loading from server...", Toast.LENGTH_SHORT).show();
                fetchDataFromServer();
            } else {
                Toast.makeText(this, "Generating locally...", Toast.LENGTH_SHORT).show();
                Scene scene = Utils.generateRandomScene();
                sceneViewModel.insert(scene);
            }
        });

        fab.setOnLongClickListener(view -> {
            sceneViewModel.deleteAllScenes();
            return true;
        });
    }

    //WRONG
    //TODO repository should choose
    private void fetchDataFromServer(){
        Retrofit retrofit = RetrofitClient.getClient();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<List<Scene>> call = retrofitApi.getWebNotes();

        call.enqueue(new Callback<List<Scene>>() {
            @Override
            public void onResponse(Call<List<Scene>> call, Response<List<Scene>> response) {
                List<Scene> scenes = response.body();
                for (int i = 0; i< scenes.size(); i++){
                    sceneViewModel.insert(scenes.get(i));
                }
            }

            @Override
<<<<<<< HEAD
            public void onFailure(Call<List<Note>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to contact server:"+t.getMessage(), Toast.LENGTH_LONG).show();
=======
            public void onFailure(Call<List<Scene>> call, Throwable t) {

>>>>>>> f08aaca7a4d1508c27bb5cdbec25167e5d1402a5
            }
        });
    }

}
