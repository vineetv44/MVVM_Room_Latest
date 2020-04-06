package com.all_latest_in_android.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.all_latest_in_android.data.local.entity.HeadlineEntity;
import com.all_latest_in_android.databinding.ItemHeadlineListBinding;
import com.all_latest_in_android.ui.base.BaseAdapter;
import com.all_latest_in_android.ui.callbacks.HeadlineListCallback;
import java.util.ArrayList;
import java.util.List;

public class HeadlineAdapter extends BaseAdapter<HeadlineAdapter.HeadlineViewHolder, HeadlineEntity> {

    private List<HeadlineEntity> articleEntities;
    private final HeadlineListCallback headlineListCallback;

    public HeadlineAdapter(@NonNull HeadlineListCallback headlineListCallback) {
        articleEntities = new ArrayList<>();
        this.headlineListCallback = headlineListCallback;
    }

    @Override
    public void setData(List<HeadlineEntity> entities) {
        this.articleEntities = entities;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HeadlineViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return HeadlineViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, headlineListCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadlineViewHolder viewHolder, int i) {
        viewHolder.onBind(articleEntities.get(i));
    }

    @Override
    public int getItemCount() {
        return articleEntities.size();
    }

    static class HeadlineViewHolder extends RecyclerView.ViewHolder {

        private static HeadlineViewHolder create(LayoutInflater inflater, ViewGroup parent, HeadlineListCallback callback) {
            ItemHeadlineListBinding itemMovieListBinding = ItemHeadlineListBinding.inflate(inflater, parent, false);
            return new HeadlineViewHolder(itemMovieListBinding, callback);
        }

        final ItemHeadlineListBinding binding;

        private HeadlineViewHolder(ItemHeadlineListBinding binding, HeadlineListCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onHeadlineItemClicked(binding.getHeadline()));
        }

        private void onBind(HeadlineEntity headlineEntity) {
            binding.setHeadline(headlineEntity);
            binding.executePendingBindings();
        }
    }
}
