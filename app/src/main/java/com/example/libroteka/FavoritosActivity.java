package com.example.libroteka;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libroteka.data.ApiManager;
import com.example.libroteka.data.FavoriteRequest;
import com.example.libroteka.data.MyApp;

import java.util.ArrayList;
import java.util.List;

public class FavoritosActivity extends AppCompatActivity {

    private RecyclerView recyclerViewFavorites;
    private FavoritesAdapter favoritesAdapter;
    private List<FavoriteRequest> favoriteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        MyApp app = (MyApp) getApplicationContext();
        recyclerViewFavorites = findViewById(R.id.recyclerViewFavorites);
        recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(this));

        favoriteList = new ArrayList<>();
        favoritesAdapter = new FavoritesAdapter(favoriteList);
        recyclerViewFavorites.setAdapter(favoritesAdapter);

        // Replace with the actual user ID or email stored in SharedPreferences
        String userId = app.getUserEmail();
        if (userId != null) {
            loadFavorites(userId);
        } else {
            Toast.makeText(this, "No user logged in", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadFavorites(String userId) {
        // Assuming you have an APIClient instance and ApiCallback interface
        ApiManager apiClient = new ApiManager();
        apiClient.getFavorites(userId, new ApiManager.ApiCallback<List<FavoriteRequest>>() {
            @Override
            public void onSuccess(List<FavoriteRequest> result) {
                favoriteList.clear();
                favoriteList.addAll(result);
                favoritesAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(FavoritosActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
