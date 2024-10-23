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


    public void loginUser(LoginRequest loginRequest, final ApiCallback<UserResponse> callback) {
        Call<UserResponse> call = apiInterface.loginUser(loginRequest);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Login fallido: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                callback.onFailure("Login fallido: " + t.getMessage());
            }
        });
    }

    public void registerUser(RegisterRequest registerRequest, final ApiCallback<RegisterResponse> callback) {
        Call<RegisterResponse> call = apiInterface.registerUser(registerRequest);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Registro fallido: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                callback.onFailure("Registro fallido: " + t.getMessage());
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T response);
        void onFailure(String errorMessage);
    }
}
