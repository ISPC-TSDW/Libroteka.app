package com.example.libroteka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.libroteka.data.ApiManager;
import com.example.libroteka.data.UserResponse;



public class Main_login2 extends AppCompatActivity {

    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login2);

        //ref campos de entrada
        emailEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_pass);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    //método para el btn crear cuenta

    public void register (View view){

        Intent register = new Intent(this, main_login.class);
        startActivity(register);
    }

    //evento para regresar a login

    /*public void previo (View view){
        Intent  previo = new Intent(this,main_login.class);
        startActivity(previo);
    } */


    //método para el botón de inicio de sesión
    public void onLoginClick(View view) {
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (validateInputs(email, password)) {
            loginUser(email, password);
        }
    }

    public void goBackToStart(View view) {
        Intent principalView = new Intent(this, main_login.class);
        startActivity(principalView);
    }

    //método para validar las entradas de usuario
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
        if (password.length() < 8) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.matches(".*[A-Z].*")) {
            Toast.makeText(this, "La contraseña debe incluir al menos una letra mayúscula", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.matches(".*[0-9].*")) {
            Toast.makeText(this, "La contraseña debe incluir al menos un número", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!password.matches(".*[!@#$%^&*+=?-].*")) {
            Toast.makeText(this, "La contraseña debe incluir al menos un carácter especial (!@#$%^&*+=?-)", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

//metodo para el btn olvido contraseña

    public void recuperarContraseña (View view){

        Intent recuperarContraseña = new Intent(this, Main_forgotten.class);
        startActivity(recuperarContraseña);
    }

    private void loginUser(String email, String password) {
        ApiManager apiManager = new ApiManager();
        apiManager.loginUser(email, password, new ApiManager.UserApiCallback<UserResponse>() {
            @Override
            public void onSuccess(UserResponse response) {
                Intent intent = new Intent(Main_login2.this, Home.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(Main_login2.this, "Login failed: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }
}