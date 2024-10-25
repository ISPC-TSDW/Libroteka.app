package com.example.libroteka.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;

import com.example.libroteka.data.GetUserRequest;
import com.example.libroteka.data.GetUserResponse;
import com.example.libroteka.data.BookResponse;
import com.example.libroteka.data.FavoriteRequest;
import com.example.libroteka.data.LoginRequest;
import com.example.libroteka.data.RegisterRequest;
import com.example.libroteka.data.RegisterResponse;
import com.example.libroteka.data.UpdateProfileRequest;
import com.example.libroteka.data.UpdateResponse;
import com.example.libroteka.data.UserResponse;

import java.util.List;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.Path;

public interface ApiInterface {

    // Login Endpoint
    @POST("api/login/")
    Call<UserResponse> loginUser(@Body LoginRequest loginRequest);

    // Get User Data
    @GET("api/users/")
    Call<GetUserResponse> getUser(@Query("email") String email);

    @POST("api/users/")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @PUT("api/user/update/")
    Call<UpdateResponse> updateUserProfile(@Body UpdateProfileRequest updateProfileRequest);

    @GET("api/book")
    Call<List<BookResponse>> getBooks();

    @POST("api/favorites/")
    Call<Void> toggleFavorite(@Body FavoriteRequest favoriteRequest);

    @DELETE("api/favorites/{id}/")
    Call<Void> removeFavorite(
            @Path("id") int favId
    );

    @GET("api/favorites/")
    Call<List<FavoriteRequest>> getFavorites(@Query("id_user") String userId);

    @GET("api/favorites/")
    Call<Boolean> getFavoriteStatus(
            @Query("id_user") String userId,
            @Query("id_book") int bookId
    );
}
