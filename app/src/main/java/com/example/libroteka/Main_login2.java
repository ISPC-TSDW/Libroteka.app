package com.example.libroteka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.libroteka.data.ApiManager;
import com.example.libroteka.data.LoginRequest;
import com.example.libroteka.data.MyApp;
import com.example.libroteka.data.UserResponse;

public class Main_login2 extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button button;
    private ApiManager apiManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login2);

        apiManager = new ApiManager();
        emailEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_pass);
        button = findViewById(R.id.button); // Asegúrate de que este ID sea correcto

        button.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (validateInputs(email, password)) {
                loginUser(email, password);
            }
        });
    }

    private void loginUser(String email, String password) {
        LoginRequest loginRequest = new LoginRequest(email, password);

        apiManager.loginUser(loginRequest, new ApiManager.ApiCallback<UserResponse>() {
            @Override
            public void onSuccess(UserResponse response) {
                MyApp app = (MyApp) getApplicationContext();
                app.setUserEmail(email);
                Toast.makeText(Main_login2.this, "Login exitoso!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main_login2.this, Home.class);
                intent.putExtra("email", email);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String errorMessage) {
                if (errorMessage.equals("User account is deactivated")) {
                    Toast.makeText(Main_login2.this, "Cuenta desactivada. Por favor, contacta al soporte.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Main_login2.this, "Login fallido", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateInputs(String email, String password) {
        if (email.isEmpty()) {
            Toast.makeText(this, "El correo electrónico no puede estar vacío", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Por favor, ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.isEmpty()) {
            Toast.makeText(this, "La contraseña no puede estar vacía", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 12) {
            Toast.makeText(this, "La contraseña debe tener al menos 12 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }
        // Añadir validaciones adicionales de contraseña aquí si es necesario.
        return true;
    }
}
