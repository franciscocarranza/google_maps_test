package com.example.luic0.googlemaps.interfaces;

import com.example.luic0.googlemaps.models.responses.PasswordRecoveryResponse;

/**
 * Created by Francisco Carranza on 8/9/18.
 * eSoft del Pacifico
 */
public interface IPasswordRecovery {
    void passwordRecoveryOk(PasswordRecoveryResponse response);
    void passwordRecoveryError(String message);
}

