package com.example.luic0.googlemaps.services;

import com.example.luic0.googlemaps.application.Services;
import com.example.luic0.googlemaps.models.requests.LoginRequest;
import com.example.luic0.googlemaps.models.responses.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Francisco Carranza on 8/7/18.
 * eSoft del Pacifico
 */
public class LoginService {
    private LoginApi mApi;

    public LoginService(){
        mApi = Services.getRetrofitInstance().create(LoginApi.class);
    }

    public interface LoginApi {

        @Headers("Content-Type: application/json")
        @POST("Users/Login")
        Observable<LoginResponse> login(@Body LoginRequest request );

    }

    public LoginApi getApi(){
        return mApi;
    }
}
