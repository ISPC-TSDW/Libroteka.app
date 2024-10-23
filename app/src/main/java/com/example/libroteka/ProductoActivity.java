package com.example.libroteka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProductoActivity extends AppCompatActivity {

    private TextView txtTitle;
    private TextView txtAuthor;
    private TextView txtDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        // Initialize your views
        txtTitle = findViewById(R.id.txtTitle);
        txtAuthor = findViewById(R.id.txtAuthor);
        txtDescription = findViewById(R.id.txtDescription);

        // Get data from the Intent
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String author = intent.getStringExtra("author");
            String description = intent.getStringExtra("description");
            String id = intent.getStringExtra("book_id");
            // Add other details as needed

            // Set data to the views
            txtTitle.setText(title);
            txtAuthor.setText(author);
            txtDescription.setText(description);

            // Set other details
        }
    }

    // Existing methods...
}