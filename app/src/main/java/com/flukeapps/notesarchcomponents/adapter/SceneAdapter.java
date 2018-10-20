package com.flukeapps.notesarchcomponents.adapter;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flukeapps.notesarchcomponents.R;
import com.flukeapps.notesarchcomponents.model.Scene;

import java.util.ArrayList;
import java.util.List;

public class SceneAdapter extends RecyclerView.Adapter<SceneAdapter.SceneHolder> {
    private List<Scene> scenes = new ArrayList<>();

    public void setScenes(List<Scene> newScenes) {
        scenes = newScenes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SceneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scene_item, parent, false);
        return new SceneHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SceneHolder holder, int position) {
        Scene currentScene = scenes.get(position);
        holder.txt_char1.setText(currentScene.getCharacter1());
        holder.txt_char2.setText(currentScene.getCharacter2());
        holder.txt_location.setText(currentScene.getLocation());
    }

    @Override
    public int getItemCount() {
        return scenes.size();
    }

    class SceneHolder extends RecyclerView.ViewHolder {
        private TextView txt_char1;
        private TextView txt_char2;
        private TextView txt_location;

        public SceneHolder(View itemView) {
            super(itemView);
            txt_char1 = itemView.findViewById(R.id.txt_character1);
            txt_char2 = itemView.findViewById(R.id.txt_character2);
            txt_location = itemView.findViewById(R.id.txt_location);
        }
    }
}