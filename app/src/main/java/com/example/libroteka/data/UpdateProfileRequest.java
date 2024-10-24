package com.example.libroteka.data;

public class UpdateProfileRequest {
    private String email;
    private String first_name;
    private String last_name;
    private String dni;
    private String username;

    public UpdateProfileRequest(String email, String first_name, String last_name, String dni, String username) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dni = dni;
        this.username = username;
    }

    // Getters and setters
}
