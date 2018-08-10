package com.example.luic0.googlemaps.managers;

import com.example.luic0.googlemaps.models.requests.PasswordRecoveryRequest;
import com.example.luic0.googlemaps.models.responses.PasswordRecoveryResponse;
import com.example.luic0.googlemaps.services.PasswordRecoveryService;

import io.reactivex.Observable;

/**
 * Created by Francisco Carranza on 8/9/18.
 * eSoft del Pacifico
 */

public class PasswordRecoveryManager {
    private PasswordRecoveryService mService;

    public PasswordRecoveryManager(){
        this.mService = new PasswordRecoveryService();
    }

    public Observable<PasswordRecoveryResponse> passwordRecovery(String email){
        PasswordRecoveryRequest request = new PasswordRecoveryRequest();
        request.setEmail(email);

        return mService.getApi().passwordRecovery(request);
    }
}
