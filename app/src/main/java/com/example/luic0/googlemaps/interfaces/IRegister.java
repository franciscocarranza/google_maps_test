package com.example.luic0.googlemaps.interfaces;

import com.example.luic0.googlemaps.models.responses.RegisterResponse;

/**
 * Created by Francisco Carranza on 8/7/18.
 * eSoft del Pacifico
 */
public interface IRegister {
    void registerOk(RegisterResponse response);
    void registerError(String message);
}
