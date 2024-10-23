package com.example.libroteka.retrofit;

import retrofit2.Call;
import retrofit2.http.Body;

import com.example.libroteka.data.GetUserRequest;
import com.example.libroteka.data.GetUserResponse;
import com.example.libroteka.data.LoginRequest;
import com.example.libroteka.data.RegisterRequest;
import com.example.libroteka.data.RegisterResponse;
import com.example.libroteka.data.UpdateProfileRequest;
import com.example.libroteka.data.UpdateResponse;
import com.example.libroteka.data.UserResponse;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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

}
