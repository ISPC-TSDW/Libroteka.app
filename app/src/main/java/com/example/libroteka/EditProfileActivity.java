package com.example.libroteka;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText emailEditText;
    private Button saveButton;
    private Button cancelButton;

    // Variables para los datos originales
    private String originalUsername = "NombreDeUsuarioActual";
    private String originalEmail = "usuarioactual@ejemplo.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Inicializar los campos del formulario
        usernameEditText = findViewById(R.id.usernameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        // Simular la carga de los datos del usuario
        usernameEditText.setText(originalUsername);
        emailEditText.setText(originalEmail);

        // Deshabilitar el botón de guardar al inicio
        saveButton.setEnabled(false);

        // Agregar TextWatcher para validar cambios en los campos de texto
        usernameEditText.addTextChangedListener(textWatcher);
        emailEditText.addTextChangedListener(textWatcher);

        // Acción para el botón "Guardar"
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Aquí iría la lógica para actualizar los datos del usuario
                    // Mostrar un mensaje de éxito (o manejar la actualización real)
                    Toast.makeText(EditProfileActivity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();

                    // Volver a la pantalla principal
                    Intent intent = new Intent(EditProfileActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Finaliza esta actividad para que no quede en el historial
                } catch (Exception e) {
                    // Mostrar un mensaje de error si algo falla
                    Toast.makeText(EditProfileActivity.this, "Error al guardar los datos: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Acción para el botón "Cancelar"
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Volver a la pantalla anterior sin guardar cambios
                finish(); // Finaliza esta actividad y regresa a la anterior
            }
        });
    }

    // TextWatcher común para los dos EditText
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // No se necesita acción antes de cambiar el texto
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Llamar a la función para habilitar o deshabilitar el botón de guardar
            validateChanges();
        }

        @Override
        public void afterTextChanged(Editable s) {
            // No se necesita acción después de cambiar el texto
        }
    };

    // Función para habilitar o deshabilitar el botón de guardar
    private void validateChanges() {
        // Comparar los valores actuales con los originales
        String currentUsername = usernameEditText.getText().toString();
        String currentEmail = emailEditText.getText().toString();

        // Habilitar el botón solo si alguno de los valores ha cambiado
        boolean isChanged = !currentUsername.equals(originalUsername) || !currentEmail.equals(originalEmail);
        saveButton.setEnabled(isChanged);
    }
}
