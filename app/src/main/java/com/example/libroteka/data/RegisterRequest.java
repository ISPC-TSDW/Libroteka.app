package com.example.libroteka.data;

public class RegisterRequest {
    private String username;
    private String first_name;
    private String last_name;
    private Integer dni;
    private String password;
    private String email;

    public RegisterRequest(String username, String first_name, String last_name, Integer dni, String password, String email) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.dni = dni;
        this.password = password;
        this.email = email;
    }

    // Getters y setters (si es necesario)
}
