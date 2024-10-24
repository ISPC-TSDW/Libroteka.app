package com.example.libroteka.data;

public class RegisterRequest {
    private String username;
    private String name;
    private String lastName;
    private String dni;
    private String password;
    private String email;

    public RegisterRequest(String username, String name, String lastName, String dni, String password, String email) {
        this.username = username;
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.password = password;
        this.email = email;
    }

    // Getters y setters (si es necesario)
}
