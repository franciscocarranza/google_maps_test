package com.example.luic0.googlemaps.interfaces;

import com.example.luic0.googlemaps.models.responses.LogicResponse;

/**
 * Created by Francisco Carranza on 8/22/18.
 * eSoft del Pacifico
 */
public interface ILogic {
    void logicOk(LogicResponse response);
    void logicError(String message);
}
