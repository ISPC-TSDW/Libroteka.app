package com.example.libroteka;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Definimos las referencias a los campos de entrada
        EditText etNombre = findViewById(R.id.etNombre);
        EditText etCorreo = findViewById(R.id.etCorreo);
        EditText etContrasena = findViewById(R.id.etContrasena);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        TextView tvIniciarSesion = findViewById(R.id.tvIniciarSesion);

        // Configuramos el botón de registro
        btnRegistrar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            String correo = etCorreo.getText().toString().trim();
            String contrasena = etContrasena.getText().toString().trim();

            // Validamos los campos
            if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validar que el nombre no contenga caracteres especiales o números
            if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
                Toast.makeText(RegisterActivity.this, "El nombre no debe contener números o caracteres especiales", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validar formato correo electrónico
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                Toast.makeText(RegisterActivity.this, "Por favor, ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validar que el correo tenga un dominio como "gmail.com", "hotmail.com", etc.
            String[] validDomains = {"gmail.com", "hotmail.com", "yahoo.com", "outlook.com"};
            String domain = correo.substring(correo.indexOf("@") + 1);
            boolean isDomainValid = false;
            for (String validDomain : validDomains) {
                if (domain.equalsIgnoreCase(validDomain)) {
                    isDomainValid = true;
                    break;
                }
            }

            if (!isDomainValid) {
                Toast.makeText(RegisterActivity.this, "Por favor, usa un dominio de correo electrónico válido (ej. gmail.com, hotmail.com)", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validar longitud contraseña
            if (contrasena.length() < 8) {
                Toast.makeText(RegisterActivity.this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
                return;
            }

            // Aquí enviamos los datos a una base de datos o backend
            Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        });


        // Si hacemos clic en "Iniciar sesión"
        tvIniciarSesion.setOnClickListener(v -> {
            // Navegamos a la actividad de inicio de sesión
            Intent intent = new Intent(RegisterActivity.this, main_login.class);
            startActivity(intent);
        });
    }
}