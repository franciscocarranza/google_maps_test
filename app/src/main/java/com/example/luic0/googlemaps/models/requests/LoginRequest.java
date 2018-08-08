package com.example.luic0.googlemaps.models.requests;

/**
 * Created by Francisco Carranza on 8/7/18.
 * eSoft del Pacifico
 */
public class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
