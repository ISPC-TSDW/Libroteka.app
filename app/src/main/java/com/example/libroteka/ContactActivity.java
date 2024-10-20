package com.example.libroteka;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    private EditText nameEditText;
    private EditText emailEditText;
    private EditText messageEditText;
    private TextView contactTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);

        // Initialize the views
        contactTitle = findViewById(R.id.et_contacto);
        nameEditText = findViewById(R.id.et_name);
        emailEditText = findViewById(R.id.emailText);
        messageEditText = findViewById(R.id.descriptionText);

        findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitContactForm();
            }
        });
    }

    // Handle form submission
    private void submitContactForm() {
        String name = nameEditText.getText().toString().trim();
        String email = emailEditText.getText().toString().trim();
        String message = messageEditText.getText().toString().trim();

        // Simple validation
        if (name.isEmpty() || email.isEmpty() || message.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO: Handle the actual form submission, such as sending the data to a backend

        // For now, just display a success message
        Toast.makeText(this, "¡Mensaje enviado con éxito!", Toast.LENGTH_SHORT).show();
    }
}
