package com.example.libroteka;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libroteka.data.ApiManager;
import com.example.libroteka.data.FavoriteRequest;
import com.example.libroteka.data.MyApp;

import java.util.ArrayList;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity implements FavoritesAdapter.OnDeleteClickListener {

    private RecyclerView recyclerViewFavorites;
    private FavoritesAdapter adapter;
    private ApiManager apiManager;
    private String userId;
    private List<FavoriteRequest> favoritesList; // Store the favorites list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        recyclerViewFavorites = findViewById(R.id.recyclerViewFavorites);
        recyclerViewFavorites.setLayoutManager(new GridLayoutManager(this, 2));

        apiManager = new ApiManager();
        MyApp app = (MyApp) getApplicationContext();
        userId = app.getUserEmail();  // Assume userId is the email

        // Fetch the favorites
        fetchFavorites(userId);
    }

    private void fetchFavorites(String userId) {
        apiManager.getFavorites(userId, new ApiManager.ApiCallback<List<FavoriteRequest>>() {
            @Override
            public void onSuccess(List<FavoriteRequest> favorites) {
                favoritesList = favorites;
                adapter = new FavoritesAdapter(favoritesList, FavoritosActivity.this);
                recyclerViewFavorites.setAdapter(adapter);

                // Handle empty list state initially
                if (favoritesList.isEmpty()) {
                    Toast.makeText(FavoritosActivity.this, "No favorites available", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(FavoritosActivity.this, "Failed to fetch favorites: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onDeleteClick(int favId, int position) {
        // Call the API to remove the favorite
        apiManager.removeFavorite(favId, new ApiManager.ApiCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                // Remove the item from the list and notify the adapter
                favoritesList.remove(position);
                adapter.notifyItemRemoved(position);

                // Notify the adapter of range change to update other item positions
                adapter.notifyItemRangeChanged(position, favoritesList.size());

                // Check if the list is empty after deletion
                if (favoritesList.isEmpty()) {
                    Toast.makeText(FavoritosActivity.this, "No more favorites", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                // Check if the item is already deleted (404 error)
                if (errorMessage.contains("404")) {
                    Toast.makeText(FavoritosActivity.this, "Favorite not found", Toast.LENGTH_SHORT).show();
                    // Remove the item locally and update the list
                    favoritesList.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, favoritesList.size());

                    if (favoritesList.isEmpty()) {
                        Toast.makeText(FavoritosActivity.this, "No more favorites", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    favoritesList.remove(position);
                    adapter.notifyItemRemoved(position);
                    adapter.notifyItemRangeChanged(position, favoritesList.size());
                    Toast.makeText(FavoritosActivity.this, "Favorito eliminado", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
