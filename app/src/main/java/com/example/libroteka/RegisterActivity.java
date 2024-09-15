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
            String nombre = etNombre.getText().toString();
            String correo = etCorreo.getText().toString();
            String contrasena = etContrasena.getText().toString();

            // Validamos los campos
            if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
                Toast.makeText(RegisterActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                // Aquí enviamos los datos a una base de datos o backend
                Toast.makeText(RegisterActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            }
        });

        // Si hacemos clic en "Iniciar sesión"
        tvIniciarSesion.setOnClickListener(v -> {
            // Navegamos a la actividad de inicio de sesión
            Intent intent = new Intent(RegisterActivity.this, main_login.class);
            startActivity(intent);
        });
    }
}
