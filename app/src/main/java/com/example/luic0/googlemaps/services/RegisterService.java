package com.example.luic0.googlemaps.services;

import com.example.luic0.googlemaps.application.Services;
import com.example.luic0.googlemaps.models.requests.RegisterRequest;
import com.example.luic0.googlemaps.models.responses.RegisterResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Francisco Carranza on 8/7/18.
 * eSoft del Pacifico
 */

public class RegisterService {
    private RegisterApi mApi;

    public RegisterService(){
        mApi = Services.getRetrofitInstance().create(RegisterApi.class);
    }

    public interface RegisterApi {

        @Headers("Content-Type: application/json")
        @POST("Users/Create")
        Observable<RegisterResponse> register(@Body RegisterRequest request );

    }

    public RegisterApi getApi(){
        return mApi;
    }
}
