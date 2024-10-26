package com.example.libroteka;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.libroteka.data.ApiManager;
import com.example.libroteka.data.GetUserResponse;
import com.example.libroteka.data.MyApp;
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
    private EditText etEditarCorreo;
    private String username;
    private String email;
    private String name;
    private String lastName;
    private Integer DNI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        apiManager = new ApiManager();
        MyApp app = (MyApp) getApplicationContext();
        String userEmail = app.getUserEmail(); // Obtener el email del usuario

        // Inicializar los campos del formulario
        etEditarUsuario = findViewById(R.id.etEditarUsuario);
        etEditarCorreo = findViewById(R.id.etEditarCorreo);
        etEditarNombre = findViewById(R.id.etEditarNombre);
        etEditarApellido = findViewById(R.id.etEditarApellido);
        etEditarDNI = findViewById(R.id.etEditarDNI);
        saveButton = findViewById(R.id.saveButton);
        cancelButton = findViewById(R.id.cancelButton);


//         Cargar los datos del usuario usando el API manager
        apiManager.getUserByEmail(userEmail, new ApiManager.ApiCallback<GetUserResponse>() { // Obtener el usuario por email
            @Override
            public void onSuccess(GetUserResponse response) {
                if (response != null) { // Verifica que la respuesta contenga datos del usuario
                    cargarData(response);
                    saveButton.setEnabled(true);
                } else {
                    Toast.makeText(EditProfileActivity.this, "No se encontraron datos para este usuario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(String errorMessage) {
                Toast.makeText(EditProfileActivity.this, "Error fetching user details: " + errorMessage, Toast.LENGTH_SHORT).show();
            }
        });


//         Deshabilitar el botón de guardar al inicio
        saveButton.setEnabled(false);

        // Agregar TextWatcher para validar cambios en los campos de texto
        etEditarUsuario.addTextChangedListener(textWatcher);
        //etEditarCorreo.addTextChangedListener(textWatcher);
        etEditarNombre.addTextChangedListener(textWatcher);
        etEditarApellido.addTextChangedListener(textWatcher);
        etEditarDNI.addTextChangedListener(textWatcher);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etEditarUsuario.getText().toString().trim();
                String email = etEditarCorreo.getText().toString().trim();
                String firstName = etEditarNombre.getText().toString().trim();
                String lastName = etEditarApellido.getText().toString().trim();
                Integer dni = Integer.parseInt(etEditarDNI.getText().toString().trim());    // Convertir el String a Integer

                // Create an UpdateProfileRequest object
                UpdateProfileRequest updateRequest = new UpdateProfileRequest(email, firstName, lastName, dni, username);

                AlertDialog.Builder alerta = new AlertDialog.Builder(EditProfileActivity.this, R.style.AlertDialog);
                alerta.setMessage("¿Desea por editar sus datos?")
                        .setCancelable(false)
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                // Call the updateUserProfile method from ApiManager
                                apiManager.updateUserProfile(updateRequest, new ApiManager.ApiCallback<UpdateResponse>() {
                                    @Override
                                    public void onSuccess(UpdateResponse response) {
                                        Toast.makeText(EditProfileActivity.this, "Datos guardados correctamente", Toast.LENGTH_SHORT).show();
                                        // Guardar los datos actualizados en SharedPreferences
                                        actualizarDatosLocales(updateRequest);

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

    // Método para actualizar los datos locales
    private void actualizarDatosLocales(UpdateProfileRequest updateRequest) {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", updateRequest.getUsername());
        editor.putString("email", updateRequest.getEmail());
        editor.putString("firstName", updateRequest.getFirstName());
        editor.putString("lastName", updateRequest.getLastName());
        editor.putInt("dni", updateRequest.getDni());
        editor.apply();
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

    private void cargarData(GetUserResponse response) {
        String username = response.getUsername();
        String email = response.getEmail();
        String name = response.getFirstName();
        String lastName = response.getLastName();
        Integer DNI = response.getDni();
        // Asigna los datos a los EditText
        etEditarUsuario.setText(username);
        etEditarCorreo.setText(email);
        etEditarNombre.setText(name);
        etEditarApellido.setText(lastName);
        etEditarDNI.setText(DNI);
        // Desactiva el campo de correo electrónico para que no sea editable
        etEditarCorreo.setEnabled(false);
    }


    // Función para habilitar o deshabilitar el botón de guardar
    private void validateChanges() {
        // Comparar los valores actuales con los originales
        String currentUsername = etEditarUsuario.getText().toString().trim();
        String currentNombre = etEditarNombre.getText().toString().trim();
        String currentApellido = etEditarApellido.getText().toString().trim();
        String currentDNI = etEditarDNI.getText().toString().trim();

        // Limpiar errores anteriores
        etEditarUsuario.setError(null);
        etEditarNombre.setError(null);
        etEditarApellido.setError(null);
        etEditarDNI.setError(null);

         //Verificar si los campos están vacíos

        if (currentUsername.isEmpty()) {
            etEditarUsuario.setError("El nombre de usuario no puede estar vacío");
            saveButton.setEnabled(false);
            return;
        }
        if (currentNombre.isEmpty()) {
            etEditarNombre.setError("El nombre no puede estar vacío");
            saveButton.setEnabled(false);
            return;
        }
        if (currentApellido.isEmpty()) {
            etEditarApellido.setError("El apellido no puede estar vacío");
            saveButton.setEnabled(false);
            return;
        }
        if (currentDNI.isEmpty()) {
            etEditarDNI.setError("El DNI no puede estar vacío");
            saveButton.setEnabled(false);
            return;
        }


        // Habilitar el botón solo si alguno de los valores ha cambiado
        boolean isChanged = !currentUsername.equals(username) || !currentNombre.equals(name) || !currentApellido.equals(lastName) || !currentDNI.equals(DNI);
        saveButton.setEnabled(isChanged);
    }





}
