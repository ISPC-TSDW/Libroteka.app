package com.example.libroteka.data;

public class DeleteProfileRequest {
    private String email;
    private boolean is_active;

        public DeleteProfileRequest(String email, boolean is_active) {
            this.email = email;
            this.is_active = is_active;
        }

        // Getters and setters
        public String getEmail() {
            return email;
        }

        public boolean getIs_active() {
            return is_active;
        }
    }


