package com.example.libroteka.data;

public class UserResponse {
    private String message;
    private String access;  // Token de acceso
    private String refresh; // Token de refresco

    public String getMessage() {
        return message;
    }

    public String getAccess() {
        return access;
    }

    public String getRefresh() {
        return refresh;
    }

}