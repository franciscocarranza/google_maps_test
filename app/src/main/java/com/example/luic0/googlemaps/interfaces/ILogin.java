package com.example.luic0.googlemaps.interfaces;

import com.example.luic0.googlemaps.models.responses.LoginResponse;

/**
 * Created by Francisco Carranza on 8/7/18.
 * eSoft del Pacifico
 */
public interface ILogin {
    void loginOk(LoginResponse response);
    void loginError(String message);
}
