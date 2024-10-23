package com.example.libroteka;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.libroteka.data.ApiManager;

public class ProductoActivity extends AppCompatActivity {

    private TextView txtTitle;
    private TextView txtAuthor;
    private TextView txtDescription;
    private ApiManager apiManager;
    private boolean isFavorite = false; // Track favorite status
    private String userId; // Set this with the current user ID
    private Integer bookId; // Store the book ID from Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        // Initialize your views
        txtTitle = findViewById(R.id.txtTitle);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtDescription = findViewById(R.id.txtDescription);
       ImageButton btnFavorite = findViewById(R.id.btnFavorite); // Ensure you have a button in your layout

        // Get data from the Intent
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String author = intent.getStringExtra("author");
            String description = intent.getStringExtra("description");
            bookId = intent.getIntExtra("book_id", -1); // Store the book ID
            userId = "libroReg@gmail.com"; // Get this dynamically as needed

            // Set data to the views
            txtTitle.setText(title);
            txtAuthor.setText(author);
            txtDescription.setText(description);
        }

        // Initialize the ApiManager
        apiManager = new ApiManager();
        getFavoriteStatus();
        // Set up favorite button click listener
        btnFavorite.setOnClickListener(v -> toggleFavorite());
    }

    private void getFavoriteStatus() {
        apiManager.getFavoriteStatus(userId, bookId, new ApiManager.ApiCallback<Boolean>() {
            @Override
            public void onSuccess(Boolean response) {
                isFavorite = response; // Update favorite status
                updateFavoriteIcon(); // Update the icon based on status
            }

            @Override
            public void onFailure(String errorMessage) {
                // Handle the error appropriately (e.g., show a toast)
            }
        });
    }


    private void toggleFavorite() {
        if (isFavorite) {
            // Call API to remove from favorites
            apiManager.removeFavorite(userId, bookId, new ApiManager.ApiCallback<Void>() {
                @Override
                public void onSuccess(Void response) {
                    isFavorite = false; // Update favorite status
                    updateFavoriteIcon(); // Change the icon to outline
                }

                @Override
                public void onFailure(String errorMessage) {
                    // Handle the error appropriately (e.g., show a toast)
                }
            });
        } else {
            // Call API to add to favorites
            apiManager.toggleFavorite(userId, bookId, new ApiManager.ApiCallback<Void>() {
                @Override
                public void onSuccess(Void response) {
                    isFavorite = true; // Update favorite status
                    updateFavoriteIcon(); // Change the icon to filled
                }

                @Override
                public void onFailure(String errorMessage) {
                    // Handle the error appropriately (e.g., show a toast)
                }
            });
        }
    }

    private void updateFavoriteIcon() {
        // Change the icon color based on the favorite status
        ImageButton btnFavorite = findViewById(R.id.btnFavorite); // Ensure you have a button in your layout
        if (isFavorite) {
            btnFavorite.setBackgroundResource(R.drawable.ic_heart); // Change to filled icon
        } else {
            btnFavorite.setBackgroundResource(R.drawable.ic_heart_outline); // Change to outline icon
        }
    }
}