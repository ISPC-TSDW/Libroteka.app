package com.example.libroteka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libroteka.data.FavoriteRequest;
import java.util.List;

class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {

    private List<FavoriteRequest> favoriteList;
    private OnDeleteClickListener onDeleteClickListener;

    // Constructor with delete click listener
    public FavoritesAdapter(List<FavoriteRequest> favoriteList, OnDeleteClickListener onDeleteClickListener) {
        this.favoriteList = favoriteList;
        this.onDeleteClickListener = onDeleteClickListener;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorito, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        FavoriteRequest favorite = favoriteList.get(position);

        // Set book image
        String imageName = "id_" + favorite.getId_book();
        Context context = holder.itemView.getContext();
        int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        if (imageResId != 0) { // Ensure the image resource exists
            holder.bookImageView.setImageResource(imageResId);
        }

        // Handle delete button click
        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClickListener.onDeleteClick(favorite.getId(), position); // Pass book ID and position
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    // ViewHolder class
    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImageView;
        ImageButton deleteButton;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImageView = itemView.findViewById(R.id.bookImageView);
            deleteButton = itemView.findViewById(R.id.deleteButton); // Reference to delete button
        }
    }

    // Callback interface to handle delete action
    public interface OnDeleteClickListener {
        void onDeleteClick(int bookId, int position);
    }
}