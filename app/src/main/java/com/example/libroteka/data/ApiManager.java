package com.example.libroteka.data;

import com.example.libroteka.retrofit.ApiInterface;
import com.example.libroteka.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiManager {

    private ApiInterface apiInterface;

    public ApiManager() {
        apiInterface = RetrofitClient.getRetrofitInstance().create(ApiInterface.class);
    }

    public void loginUser(String username, String password, final UserApiCallback<UserResponse> callback) {
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

    public void getBooks(final BookApiCallback<List<BookResponse>> callback) {
        Call<List<BookResponse>> call = apiInterface.getBooks();

        call.enqueue(new Callback<List<BookResponse>>() {
            @Override
            public void onResponse(Call<List<BookResponse>> call, Response<List<BookResponse>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Hubo un error al intentar recuperar los libros: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<BookResponse>> call, Throwable t) {
                callback.onFailure("Hubo un error al intentar recuperar los libros: " + t.getMessage());
            }
        });
    }

    public interface UserApiCallback<T> {
        void onSuccess(T response);
        void onFailure(String errorMessage);
    }

    public interface BookApiCallback<T> {
        void onSuccess(T response);
        void onFailure(String errorMessage);
    }
}
