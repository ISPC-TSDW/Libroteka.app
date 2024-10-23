package com.example.libroteka.data;

public class UserResponse {
    private String message;
    private String access; // el token acceso (JWT)
    private String refresh // token de refresh

    public String getMessage() {
        return message;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String access) {
        this.refresh = refresh;
    }

//    public String getToken() {
//        return token;
//    }
}
