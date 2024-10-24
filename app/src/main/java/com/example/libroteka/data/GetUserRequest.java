package com.example.libroteka.data;

public class GetUserRequest {
    private String email;
    private String first_name;
    private String last_name;
    private String password;
    private String dni;
    private String username;

        public GetUserRequest(String email, String first_name, String last_name, String password, String dni, String username) {
            this.email = email;
            this.first_name = first_name;
            this.last_name = last_name;
            this.password = password;
            this.dni = dni;
            this.username = username;
        }

        // Getters and setters
    }

