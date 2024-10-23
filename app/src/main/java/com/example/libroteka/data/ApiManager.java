package com.example.libroteka.data;

import com.example.libroteka.retrofit.ApiInterface;
import com.example.libroteka.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiManager {

    private ApiInterface apiInterface;

    public ApiManager() {
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
    }

    public void loginUser(String username, String password, final ApiCallback<UserResponse> callback) {
        LoginRequest loginRequest = new LoginRequest(username, password);
        Call<UserResponse> call = apiInterface.loginUser(loginRequest);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
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

    public void registerUser(RegisterRequest registerRequest, final ApiCallback<RegisterResponse> callback) {
        // Make the call to the register API
        Call<RegisterResponse> call = apiInterface.registerUser(registerRequest);

        // Handle the API response asynchronously
        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Registration successful, return the success callback
                    callback.onSuccess(response.body());
                } else {
                    // Registration failed, return the failure callback
                    callback.onFailure("Registration failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                // Handle the error during the call (e.g., network issue)
                callback.onFailure("Registration failed: " + t.getMessage());
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T response);
        void onFailure(String errorMessage);
    }
}
