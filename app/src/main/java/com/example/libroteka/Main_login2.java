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

public class Main_login2 extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_login2);

        emailEditText = findViewById(R.id.et_email);
        passwordEditText = findViewById(R.id.et_pass);
        loginButton = findViewById(R.id.loginUser);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();  // Llamar al método inicio cuando el botón sea presionado
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }


        //metodo para el btn crear cuenta

    public void register(View view) {

        Intent register = new Intent(this, main_login.class);
        startActivity(register);
    }

    //evento para regresar a login

    public void previo(View view) {
        Intent previo = new Intent(this, main_login.class);
        startActivity(previo);
    }

    //validar datos
    public void loginUser() {
        // Obtener el texto de los campos de email y contraseña
        String emailIngresado = emailEditText.getText().toString().trim();
        String contrasenaIngresada = passwordEditText.getText().toString().trim();

        String emailCorrecto = "1234@gmail.com";
        String contrasenaCorrecta = "password123";

        // Validar si el email existe en "la base de datos" y si la contraseña es correcta
        if (!emailIngresado.equals(emailCorrecto)) {
            // El correo no está registrado
            mostrarMensaje("El correo ingresado no existe en nuestra base de datos");
        } else if (!contrasenaIngresada.equals(contrasenaCorrecta)) {
            // La contraseña es incorrecta
            mostrarMensaje("Contraseña incorrecta");
        } else {
            // Inicio de sesión exitoso
            mostrarMensaje("Inicio de sesión exitoso");
            // Navegar a la siguiente actividad
        }

    }

    // Método para mostrar mensajes al usuario
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();
    }
}