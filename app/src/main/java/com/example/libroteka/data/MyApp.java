package com.example.libroteka.data;

import android.app.Application;

public class MyApp extends Application {
    private String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
