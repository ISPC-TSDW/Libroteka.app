package com.example.libroteka;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.libroteka.data.ApiManager;
import com.example.libroteka.data.GetUserResponse;
import com.example.libroteka.data.GetUserRequest;

public class ProfileActivity extends AppCompatActivity {
    private ApiManager apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Initialize views
        Button editProfileButton = findViewById(R.id.editProfileButton);
        Button contactUsButton = findViewById(R.id.contactUsButton);
        Button faqButton = findViewById(R.id.faqButton);
        Button logoutButton = findViewById(R.id.logoutButton);
        Button goBackButton = findViewById(R.id.goBackButton);
        TextView emailTextView = findViewById(R.id.userEmail);

        apiManager = new ApiManager();
        String email = getIntent().getStringExtra("email");
        apiManager.getUser(email, new ApiManager.ApiCallback<GetUserResponse>() {
                @Override
                public void onSuccess(GetUserResponse response) {
                    emailTextView.setText(response.getEmail());
                };
                @Override
                public void onFailure(String errorMessage) {
                    Toast.makeText(ProfileActivity.this, "Error fetching user details: " + errorMessage, Toast.LENGTH_SHORT).show();
                };
            });
        // Set up button listeners
        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the EditProfileActivity
                Intent intent = new Intent(ProfileActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        contactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Contact Us action
                Intent intent = new Intent(ProfileActivity.this, ContactActivity.class);
                startActivity(intent);
            }
        });

        faqButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al presionar Preguntas Frecuentes
                Intent intent = new Intent(ProfileActivity.this, FaqActivity.class);
                startActivity(intent);
            }
        });
        goBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, Home.class);
                startActivity(intent);
            }
        });
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alerta = new AlertDialog.Builder(ProfileActivity.this, R.style.AlertDialog);
                alerta.setMessage("¿Quieres cerrar la app?")
                        .setCancelable(false)
                        .setPositiveButton("Aceptar" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        })
                        .setNegativeButton("cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setTitle("Cerrar sesión");
                titulo.show();
            }

        });

    }

}