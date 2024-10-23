package com.example.libroteka.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;

import com.example.libroteka.data.BookResponse;
import com.example.libroteka.data.LoginRequest;
import com.example.libroteka.data.RegisterRequest;
import com.example.libroteka.data.RegisterResponse;
import com.example.libroteka.data.UserResponse;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    // Login Endpoint
    @POST("api/login/")
    Call<UserResponse> loginUser(@Body LoginRequest loginRequest);

    // Get User Data
    @GET("api/users/")
    Call<UserResponse> getUser();

    @POST("api/users/")
    Call<RegisterResponse> registerUser(@Body RegisterRequest registerRequest);

    @GET("api/book")
    Call<List<BookResponse>> getBooks();
}
