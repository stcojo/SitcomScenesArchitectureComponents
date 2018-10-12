package com.flukeapps.notesarchcomponents.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import android.os.AsyncTask;

import com.flukeapps.notesarchcomponents.model.Scene;
import com.flukeapps.notesarchcomponents.room.SceneDAO;
import com.flukeapps.notesarchcomponents.room.SceneDatabase;

import java.util.List;

public class SceneRepository {

    private SceneDAO sceneDAO;
    private LiveData<List<Scene>> allScenes;

    public SceneRepository(Application application){
        SceneDatabase database = SceneDatabase.getInstance(application);

        sceneDAO = database.sceneDAO();
        allScenes = sceneDAO.getAllScenes();
    }

    public void insert(Scene scene){
        new InsertSceneAsyncTask(sceneDAO).execute(scene);
    }

    public void update(Scene scene){
        new UpdateSceneAsyncTask(sceneDAO).execute(scene);
    }

    public void delete(Scene scene){
        new DeleteSceneAsyncTask(sceneDAO).execute(scene);
    }

    public void deleteAllScenes(){
        new DeleteAllScenesAsyncTask(sceneDAO).execute();
    }

    public LiveData<List<Scene>> getAllScenes() {
        return allScenes;
    }

    private static class InsertSceneAsyncTask extends AsyncTask<Scene, Void, Void> {
        private SceneDAO sceneDao;

        private InsertSceneAsyncTask(SceneDAO sceneDao) {
            this.sceneDao = sceneDao;
        }

        @Override
        protected Void doInBackground(Scene... scenes) {
            sceneDao.insert(scenes[0]);
            return null;
        }
    }

    private static class UpdateSceneAsyncTask extends AsyncTask<Scene, Void, Void> {
        private SceneDAO sceneDao;

        private UpdateSceneAsyncTask(SceneDAO sceneDao) {
            this.sceneDao = sceneDao;
        }

        @Override
        protected Void doInBackground(Scene... scenes) {
            sceneDao.update(scenes[0]);
            return null;
        }
    }

    private static class DeleteSceneAsyncTask extends AsyncTask<Scene, Void, Void> {
        private SceneDAO sceneDao;

        private DeleteSceneAsyncTask(SceneDAO sceneDao) {
            this.sceneDao = sceneDao;
        }

        @Override
        protected Void doInBackground(Scene... scenes) {
            sceneDao.delete(scenes[0]);
            return null;
        }
    }

    private static class DeleteAllScenesAsyncTask extends AsyncTask<Void, Void, Void> {
        private SceneDAO sceneDao;

        private DeleteAllScenesAsyncTask(SceneDAO sceneDao) {
            this.sceneDao = sceneDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            sceneDao.deleteAllScenes();
            return null;
        }
    }
}

