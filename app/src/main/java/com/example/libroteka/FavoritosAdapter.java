package com.example.libroteka;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libroteka.data.FavoriteRequest;
import java.util.List;

class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoriteViewHolder> {

    private List<FavoriteRequest> favoriteList;

    public FavoritesAdapter(List<FavoriteRequest> favoriteList) {
        this.favoriteList = favoriteList;
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

        // Assuming the image name is id_{id_book}
        String imageName = "id_" + favorite.getId_book();
        Context context = holder.itemView.getContext();
        int imageResId = context.getResources().getIdentifier(imageName, "drawable", context.getPackageName());

        // Set image to the ImageView
        if (imageResId != 0) { // Ensure the image resource exists
            holder.bookImageView.setImageResource(imageResId);
        }
    }

    @Override
    public int getItemCount() {
        return favoriteList.size();
    }

    public static class FavoriteViewHolder extends RecyclerView.ViewHolder {
        ImageView bookImageView;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            bookImageView = itemView.findViewById(R.id.bookImageView);
        }
    }
}