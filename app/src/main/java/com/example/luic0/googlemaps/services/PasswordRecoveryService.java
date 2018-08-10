package com.example.luic0.googlemaps.services;

import com.example.luic0.googlemaps.application.Services;
import com.example.luic0.googlemaps.models.requests.PasswordRecoveryRequest;
import com.example.luic0.googlemaps.models.responses.PasswordRecoveryResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Francisco Carranza on 8/9/18.
 * eSoft del Pacifico
 */
public class PasswordRecoveryService {
    private PasswordRecoveryApi mApi;

    public PasswordRecoveryService(){
        mApi = Services.getRetrofitInstance().create(PasswordRecoveryApi.class);
    }

    public interface PasswordRecoveryApi {

        @Headers("Content-Type: application/json")
        @POST("Users/PasswordRecovery")
        Observable<PasswordRecoveryResponse> passwordRecovery(@Body PasswordRecoveryRequest request );
    }

    public PasswordRecoveryApi getApi() {
        return mApi;
    }
}
