package com.example.luic0.googlemaps.interfaces;

import com.example.luic0.googlemaps.models.responses.ContactResponse;

/**
 * Created by Francisco Carranza on 8/21/18.
 * eSoft del Pacifico
 */
public interface IContact {
    void contactOk(ContactResponse response);
    void contactoError(String message);
}
