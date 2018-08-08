package com.example.luic0.googlemaps.managers;

import com.example.luic0.googlemaps.models.requests.LoginRequest;
import com.example.luic0.googlemaps.models.responses.LoginResponse;
import com.example.luic0.googlemaps.services.LoginService;

import io.reactivex.Observable;

/**
 * Created by Francisco Carranza on 8/7/18.
 * eSoft del Pacifico
 */

public class LoginManager {
    private LoginService mService;

    public LoginManager(){
        this.mService = new LoginService();
    }

    public Observable<LoginResponse> login(String email, String password){
        LoginRequest request = new LoginRequest();
        request.setEmail(email);
        request.setPassword(password);

        return mService.getApi().login(request);
    }
}
