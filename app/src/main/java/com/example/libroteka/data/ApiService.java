package com.example.libroteka.data;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/api/login/")
    Call<UserResponse> registerUser(@Body UserRequest userRequest);
    @POST("/api/users/")
    Call<UserResponse> loginUser(@Body UserRequest userRequest);
}
