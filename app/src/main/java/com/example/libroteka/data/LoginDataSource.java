package com.example.libroteka.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.libroteka.data.model.LoggedInUser;
import com.example.libroteka.data.UserResponse;
import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private ApiManager apiManager;
    private SharedPreferences sharedPreferences;

    public LoginDataSource(Context context) {
        // Inicializa ApiManager, que maneja las solicitudes HTTP con Retrofit
        this.apiManager = new ApiManager(context);
    }

    // Cambiar para utilizar el callback asíncrono para manejar la respuesta
    public void login(String username, String password, LoginCallback callback) {
        // Llama al método de login en ApiManager
        apiManager.loginUser(username, password, new ApiManager.ApiCallback<UserResponse>() {
            @Override
            public void onSuccess(UserResponse response) {
                // Si la autenticación es exitosa, crea el usuario autenticado
                LoggedInUser loggedInUser = new LoggedInUser(
                        java.util.UUID.randomUUID().toString(),
                        username);

                // Devuelve el resultado exitoso utilizando el callback
                callback.onLoginSuccess(new Result.Success<>(loggedInUser));
            }

            @Override
            public void onFailure(String errorMessage) {
                // En caso de error, devuelve un error
                callback.onLoginError(new Result.Error(new IOException(errorMessage)));
            }
        });
    }

    public void logout() {
        // Eliminar el token JWT de SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("JWT_ACCESS_TOKEN");
        editor.apply();
    }


    // Interfaz de callback para manejar el login
    public interface LoginCallback {
        void onLoginSuccess(Result<LoggedInUser> result);
        void onLoginError(Result.Error error);
    }


}