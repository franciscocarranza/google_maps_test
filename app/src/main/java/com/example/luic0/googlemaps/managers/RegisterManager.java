package com.example.luic0.googlemaps.managers;

import com.example.luic0.googlemaps.models.requests.RegisterRequest;
import com.example.luic0.googlemaps.models.responses.RegisterResponse;
import com.example.luic0.googlemaps.services.RegisterService;

import io.reactivex.Observable;

/**
 * Created by Francisco Carranza on 8/7/18.
 * eSoft del Pacifico
 */

public class RegisterManager {
    private RegisterService mService;

    public RegisterManager(){
        this.mService = new RegisterService();
    }

    public Observable<RegisterResponse> register(String user, String password, String email){
        RegisterRequest request = new RegisterRequest();
        request.setName(user);
        request.setPassword(password);
        request.setEmail(email);

        return mService.getApi().register(request);
    }
}
