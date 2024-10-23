package com.example.libroteka;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libroteka.data.ApiManager;
import com.example.libroteka.data.UpdateProfileRequest;
import com.example.libroteka.data.UpdateResponse;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {
    private ApiManager apiManager;

    private Button saveButton;
    private Button cancelButton;
    private EditText etEditarUsuario;
    private EditText etEditarNombre;
    private EditText etEditarApellido;
    private EditText etEditarDNI;
    private EditText etEditarContrasena;
    private EditText etEditarCorreo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Inicializar los campos del formulario
        etEditarUsuario = findViewById(R.id.etEditarUsuario);
        etEditarNombre = findViewById(R.id.etEditarNombre);
        etEditarApellido = findViewById(R.id.etEditarApellido);
        etEditarDNI = findViewById(R.id.etEditarDNI);
        etEditarContrasena = findViewById(R.id.etEditarContrasena);
        etEditarCorreo = findViewById(R.id.etEditarCorreo);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);

        // Cargar los datos del usuario
        cargarData();

        // Deshabilitar el botón de guardar al inicio
        saveButton.setEnabled(false);

        // Agregar TextWatcher para validar cambios en los campos de texto
        etEditarUsuario.addTextChangedListener(textWatcher);
        etEditarCorreo.addTextChangedListener(textWatcher);
        etEditarNombre.addTextChangedListener(textWatcher);
        etEditarApellido.addTextChangedListener(textWatcher);
        etEditarDNI.addTextChangedListener(textWatcher);
        etEditarContrasena.addTextChangedListener(textWatcher);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etEditarUsuario.getText().toString().trim();
                String email = etEditarCorreo.getText().toString().trim();
                String firstName = etEditarNombre.getText().toString().trim();
                String lastName = etEditarApellido.getText().toString().trim();
                String password = etEditarContrasena.getText().toString().trim();
                String dni = etEditarDNI.getText().toString().trim();


                AlertDialog.Builder alerta = new AlertDialog.Builder(EditProfileActivity.this, R.style.AlertDialog);
                alerta.setMessage("¿Desea por editar sus datos?")
                        .setCancelable(false)
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Create an UpdateProfileRequest object
                                UpdateProfileRequest updateRequest = new UpdateProfileRequest(email, firstName, lastName, password, dni, username);

                                // Call the updateUserProfile method from ApiManager
                                apiManager.updateUserProfile(updateRequest, new ApiManager.ApiCallback<UpdateResponse>() {
                                    @Override
                                    public void onSuccess(UpdateResponse response) {
                                        Toast.makeText(EditProfileActivity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();

                                        // Navigate back to the profile activity
                                        Intent intent = new Intent(EditProfileActivity.this, ProfileActivity.class);
                                        startActivity(intent);
                                        finish(); // Close the activity
                                    }

                                    @Override
                                    public void onFailure(String errorMessage) {
                                        Toast.makeText(EditProfileActivity.this, "Error al guardar los datos: " + errorMessage, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setTitle("Confirmar edición");
                titulo.show();
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
        String currentUsername = etEditarUsuario.getText().toString().trim();
        String currentEmail = etEditarCorreo.getText().toString().trim();

        // Verificar si los campos están vacíos
        if (currentUsername.isEmpty()) {
            saveButton.setEnabled(false);
            Toast.makeText(this, "El nombre de usuario no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        if (currentEmail.isEmpty()) {
            saveButton.setEnabled(false);
            Toast.makeText(this, "El correo electrónico no puede estar vacío", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar formato del correo electrónico
        if (!Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches()) {
            saveButton.setEnabled(false);
            Toast.makeText(this, "Por favor, ingresa un correo electrónico válido", Toast.LENGTH_SHORT).show();
            return;
        }


        // Habilitar el botón solo si alguno de los valores ha cambiado
        boolean isChanged = !currentUsername.equals(obtenerUsernameDesdeAPI()) || !currentEmail.equals(obtenerEmailDesdeAPI());
        saveButton.setEnabled(isChanged);
    }

    private void cargarData() {
        // Simular la carga de datos desde el backend
        String usernameApi = obtenerUsernameDesdeAPI(); // Simula obtener el nombre
        String emailApi = obtenerEmailDesdeAPI(); // Simula obtener el correo

        // Asigna los datos a los EditText
        etEditarUsuario.setText(usernameApi);
        etEditarCorreo.setText(emailApi);
        etEditarNombre.setText(usernameApi);
        etEditarApellido.setText(usernameApi);
        etEditarDNI.setText(usernameApi);
        etEditarContrasena.setText(usernameApi);
    }
    private String obtenerUsernameDesdeAPI() {
        return "NombreDeUsuarioReal"; // Simulación de respuesta de API
    }

    private String obtenerEmailDesdeAPI() {
        return "usuarioreal@ejemplo.com"; // Simulación de respuesta de API
    }

    //traer datos de la base de datos si es que se hace esta accion
   /* private void cargarData() {
        // Simular la carga de datos desde el backend
        String usernameFromAPI = obtenerUsernameDesdeAPI(); // Simula obtener el nombre
        String emailFromAPI = obtenerEmailDesdeAPI(); // Simula obtener el correo

        // Asigna los datos a los EditText
        usernameEditText.setText(usernameFromAPI);
        emailEditText.setText(emailFromAPI);
    }

    private String obtenerUsernameDesdeAPI() {
        // Simulación de respuesta de API
        return "NombreDeUsuarioReal";
    }

    private String obtenerEmailDesdeAPI() {
        // Simulación de respuesta de API
        return "usuarioreal@ejemplo.com";
    }*/
}
