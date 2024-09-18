package com.example.libroteka.data;

import android.os.Bundle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiManager {

    private final ApiService apiService;

    public ApiManager() {
        apiService = RetrofitClient.getClient().create(ApiService.class);
    }

    // Login API call
    public void loginUser(String username, String password, final ApiCallback<UserResponse> callback) {
        UserRequest request = new UserRequest(username, password, null);
        Call<UserResponse> call = apiService.loginUser(request);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Login failed");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    // Register API call
    public void registerUser(String username, String password, String email, final ApiCallback<UserResponse> callback) {
        UserRequest request = new UserRequest(username, password, email);
        Call<UserResponse> call = apiService.registerUser(request);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Registration failed");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    // Define a callback interface to handle the response in the activity
    public interface ApiCallback<T> {
        void onSuccess(T response);
        void onFailure(String errorMessage);

        void onCreate(Bundle savedInstanceState);
    }
}

