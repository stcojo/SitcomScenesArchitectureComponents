package com.flukeapps.notesarchcomponents.model;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

public class MyDiffCallback extends DiffUtil.Callback{

    List<Scene> oldScenes;
    List<Scene> newScenes;

    public MyDiffCallback(List<Scene> newScenes, List<Scene> oldScenes) {
        this.newScenes = newScenes;
        this.oldScenes = oldScenes;
    }

    @Override
    public int getOldListSize() {
        return oldScenes.size();
    }

    @Override
    public int getNewListSize() {
        return newScenes.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldScenes.get(oldItemPosition).getId() == newScenes.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldScenes.get(oldItemPosition).equals(newScenes.get(newItemPosition));
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        //you can return particular field for changed item.
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}