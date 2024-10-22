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

    public interface ApiCallback<T> {
        void onSuccess(T response);
        void onFailure(String errorMessage);
    }


}