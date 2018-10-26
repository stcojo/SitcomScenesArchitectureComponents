package com.flukeapps.notesarchcomponents.adapter;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.flukeapps.notesarchcomponents.R;
import com.flukeapps.notesarchcomponents.model.Scene;

public class SceneAdapter extends ListAdapter<Scene, SceneAdapter.SceneHolder> {

    public SceneAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Scene> DIFF_CALLBACK = new DiffUtil.ItemCallback<Scene>() {
        @Override
        public boolean areItemsTheSame(@NonNull Scene oldItem, @NonNull Scene newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Scene oldItem, @NonNull Scene newItem) {
            return oldItem.getCharacter1().equals(newItem.getCharacter1()) &&
                    oldItem.getCharacter2().equals(newItem.getCharacter2()) &&
                    oldItem.getLocation().equals(newItem.getLocation());
        }
    };


    @NonNull
    @Override
    public SceneHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.scene_item, parent, false);
        return new SceneHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SceneHolder holder, int position) {
        Scene currentScene = getItem(position);
        holder.txt_char1.setText(currentScene.getCharacter1());
        holder.txt_char2.setText(currentScene.getCharacter2());
        holder.txt_location.setText(currentScene.getLocation());

        Glide.with(holder.itemView.getContext())
                .load(getCorrectImage(currentScene.getLocation()))
                .apply(RequestOptions.centerCropTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL))
                .into(holder.imagine);
    }

    private int getCorrectImage(String location){
        switch (location){
            case "Sheldon's apartment": return R.drawable.sheldonap;
            case "Sheldon's spot": return R.drawable.sheldonspot;
            case "Comic store": return R.drawable.comicstore;
            case "Penny's apartment": return R.drawable.pennyap;
            case "The elevator": return R.drawable.elevator;
            case "Cafeteria": return R.drawable.cafeteria;
            case "Comic con": return R.drawable.comiccon;
            default: return R.drawable.sheldonap;
        }
    }


    public Scene getSceneAt(int position) {
        return getItem(position);
    }

    class SceneHolder extends RecyclerView.ViewHolder {
        private TextView txt_char1;
        private TextView txt_char2;
        private TextView txt_location;
        private ImageView imagine;

        public SceneHolder(View itemView) {
            super(itemView);
            txt_char1 = itemView.findViewById(R.id.txt_character1);
            txt_char2 = itemView.findViewById(R.id.txt_character2);
            txt_location = itemView.findViewById(R.id.txt_location);
            imagine = itemView.findViewById(R.id.imagine);
        }
    }
}