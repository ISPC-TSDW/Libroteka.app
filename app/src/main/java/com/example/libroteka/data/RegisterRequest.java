package com.example.libroteka.data;

public class RegisterRequest {
    private String nombre;
    private String apellido;
    private String dni;
    private String password;
    private String email;

    public RegisterRequest(String nombre, String apellido, String dni, String password, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.password = password;
        this.email = email;
    }

    // Getters and setters can be added here if needed

}