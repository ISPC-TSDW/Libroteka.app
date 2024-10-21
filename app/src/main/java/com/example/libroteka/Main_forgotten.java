package com.example.libroteka;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Main_forgotten extends AppCompatActivity {

    private EditText emailEditText;
    private Button recoverButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forgotten);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Inicializar los elementos de la vista
        emailEditText = findViewById(R.id.et_mail);
        recoverButton = findViewById(R.id.btn_recuperar);

        // Agregar TextWatcher para validar el email en tiempo real
        emailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No se necesita acción aquí
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Validar el email mientras se escribe
                validateEmail();
            }

            @Override
            public void afterTextChanged(Editable s) {
                // No se necesita acción aquí
            }
        });
    }

    // Evento para regresar a login
    public void previo1(View view) {
        Intent previo1 = new Intent(this, main_login.class);
        startActivity(previo1);
    }

    // Validar el correo electrónico
    private boolean validateEmail() {
        String email = emailEditText.getText().toString().trim();
        if (email.isEmpty()) {
            emailEditText.setError("El correo electrónico no puede estar vacío");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Por favor, ingresa un correo electrónico válido");
            return false;
        }
        return true;
    }

    // Acción para el botón "Recuperar"
    public void onRecoverClick(View view) {
        if (validateEmail()) {
            // Aquí iría la lógica para enviar el correo de recuperación
            Toast.makeText(this, "Se ha enviado un correo de recuperación a " + emailEditText.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}