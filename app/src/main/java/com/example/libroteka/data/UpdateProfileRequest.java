package com.example.libroteka.data;

public class UpdateProfileRequest {
    private String email;
    private String first_name;
    private String last_name;
    private Integer dni;
    private String username;

    public UpdateProfileRequest(String email, String first_name, String last_name, Integer dni, String username) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dni = dni;
        this.username = username;
    }

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public Integer getDni() {
        return dni;
    }

    public String getUsername() {
        return username;
    }
}

