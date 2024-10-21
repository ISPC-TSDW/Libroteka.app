package com.example.libroteka.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.libroteka.retrofit.ApiInterface;
import com.example.libroteka.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiManager {

    private ApiInterface apiInterface;
    private SharedPreferences sharedPreferences;

    public ApiManager(Context context) {
        apiInterface = RetrofitClient.getRetrofitInstance(context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)).create(ApiInterface.class);
        sharedPreferences = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE);
    }

    // Método para el login
    public void loginUser(String username, String password, final ApiCallback<UserResponse> callback) {
        LoginRequest loginRequest = new LoginRequest(username, password);
        Call<UserResponse> call = apiInterface.loginUser(loginRequest);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {

                    // Mostrar el cuerpo de la respuesta en el log
                    System.out.println("Respuesta de la API: " + response.body());

                    UserResponse userResponse = response.body();

                    // Guardar el token JWT en SharedPreferences
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("JWT_ACCESS_TOKEN", userResponse.getAccess());
                    editor.apply();

                    callback.onSuccess(userResponse);
                } else {
                    callback.onFailure("Login failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                callback.onFailure("Login failed: " + t.getMessage());
            }
        });
    }

    // Método para obtener los datos del usuario
    public void getUserData(final ApiCallback<UserResponse> callback) {
        Call<UserResponse> call = apiInterface.getUser();

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to fetch user data: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                callback.onFailure("Request failed: " + t.getMessage());
            }
        });
    }


    // Interfaz de callback para manejar las respuestas de la API
    public interface ApiCallback<T> {
        void onSuccess(T response);
        void onFailure(String errorMessage);
    }

}