package com.example.luic0.googlemaps.managers;

import com.example.luic0.googlemaps.models.requests.ContactRequest;
import com.example.luic0.googlemaps.models.responses.ContactResponse;
import com.example.luic0.googlemaps.services.ContactService;

import io.reactivex.Observable;

/**
 * Created by Francisco Carranza on 8/21/18.
 * eSoft del Pacifico
 */
public class ContactManager {
    private ContactService mService;

    public ContactManager(){
        this.mService = new ContactService();
    }

    public Observable<ContactResponse> contact(String name, String subject, String email, String message){
        ContactRequest request = new ContactRequest();
        request.setName(name);
        request.setSubject(subject);
        request.setEmail(email);
        request.setMessage(message);

        return mService.getAPi().contact(request);
    }
}
