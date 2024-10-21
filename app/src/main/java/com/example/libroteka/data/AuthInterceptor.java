package com.example.libroteka.data;

import android.content.SharedPreferences;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import java.io.IOException;

public class AuthInterceptor implements Interceptor {
    private SharedPreferences sharedPreferences;

    public AuthInterceptor(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String token = sharedPreferences.getString("JWT_ACCESS_TOKEN", null);

        if (token != null) {
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + token)
                    .build();
            return chain.proceed(request);
        }  else {
            System.out.println("Token JWT no disponible, enviando solicitud sin autenticación.");
        }

        return chain.proceed(chain.request());
    }
}