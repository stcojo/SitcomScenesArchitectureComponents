package com.flukeapps.notesarchcomponents.model;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;

import com.flukeapps.notesarchcomponents.adapter.SceneAdapter;
import com.flukeapps.notesarchcomponents.repository.SceneRepository;

import java.util.List;

public class SceneViewModel extends AndroidViewModel {

    private SceneRepository repository;
    private LiveData<List<Scene>> allScenes;

    public SceneViewModel(@NonNull Application application) {
        super(application);
        repository = new SceneRepository(application);
        allScenes = repository.getAllScenes();
    }

    public void insert(){
        repository.InsertScene_fetchedOrGenerated();
    }

    public void update(Scene scene){
        repository.update(scene);
    }

    public void delete(Scene scene){
        repository.delete(scene);
    }

    public void deleteAllScenes(){
        repository.deleteAllScenes();
    }

    public LiveData<List<Scene>> getAllScenes() {
        return allScenes;
    }
}
