package com.example.luic0.googlemaps.services;

import com.example.luic0.googlemaps.application.Services;
import com.example.luic0.googlemaps.models.requests.LogicRequest;
import com.example.luic0.googlemaps.models.responses.LogicResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Francisco Carranza on 8/22/18.
 * eSoft del Pacifico
 */
public class LogicService {
    private LogicApi mApi;

    public LogicService(){
        mApi = Services.getRetrofitInstance().create(LogicApi.class);
    }

    public interface LogicApi {

        @Headers("Content-Type: application/json")
        @POST("Logia/GetAll")
        Observable<LogicResponse> logic(@Body LogicRequest request);
    }

    public LogicApi getApi() {
        return mApi;
    }
}
