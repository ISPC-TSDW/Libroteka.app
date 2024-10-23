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

    public void getBooks(final ApiCallback<List<BookResponse>> callback) {
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

    public void getFavorites(final ApiCallback<List<FavoriteRequest>> callback) {
        Call<List<FavoriteRequest>> call = apiInterface.getFavorites();

        call.enqueue(new Callback<List<FavoriteRequest>>() {
            @Override
            public void onResponse(Call<List<FavoriteRequest>> call, Response<List<FavoriteRequest>> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Hubo un error al intentar recuperar los libros: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<FavoriteRequest>> call, Throwable t) {
                callback.onFailure("Hubo un error al intentar recuperar tus favoritos: " + t.getMessage());
            }
        });
    }

    public void getFavoriteStatus(String userId, int bookId, final ApiCallback<Boolean> callback) {
        // Create a request model or object to send, if necessary
        // FavoriteRequest request = new FavoriteRequest(userId, bookId); // Remove this if not needed

        // Adjust the API call to match the return type of Boolean
        Call<Boolean> call = apiInterface.getFavoriteStatus(userId, bookId); // Pass userId and bookId

        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(response.body());
                } else {
                    callback.onFailure("Failed to retrieve favorite status: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                callback.onFailure("Failed to retrieve favorite status: " + t.getMessage());
            }
        });
    }

    // New method for toggling favorite books
    public void toggleFavorite(String userId, Integer bookId, final ApiCallback<Void> callback) {
        FavoriteRequest favoriteRequest = new FavoriteRequest(userId, bookId);
        Call<Void> call = apiInterface.toggleFavorite(favoriteRequest);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null); // No response body needed for a successful toggle
                } else {
                    callback.onFailure("Toggle favorite failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure("Toggle favorite failed: " + t.getMessage());
            }
        });
    }

    public void removeFavorite(String userId, Integer bookId, final ApiCallback<Void> callback) {
        FavoriteRequest favoriteRequest = new FavoriteRequest(userId, bookId);
        Call<Void> call = apiInterface.toggleFavorite(favoriteRequest);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    callback.onSuccess(null); // No response body needed for a successful toggle
                } else {
                    callback.onFailure("Toggle favorite failed: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                callback.onFailure("Toggle favorite failed: " + t.getMessage());
            }
        });
    }

    public interface ApiCallback<T> {
        void onSuccess(T response);
        void onFailure(String errorMessage);
    }

}