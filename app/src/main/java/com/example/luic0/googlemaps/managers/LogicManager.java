package com.example.luic0.googlemaps.managers;

import com.example.luic0.googlemaps.models.requests.LogicRequest;
import com.example.luic0.googlemaps.models.responses.LogicResponse;
import com.example.luic0.googlemaps.services.LogicService;

import io.reactivex.Observable;

/**
 * Created by Francisco Carranza on 8/22/18.
 * eSoft del Pacifico
 */
public class LogicManager {
    private LogicService mService;

    public LogicManager(){
        this.mService = new LogicService();
    }

    public Observable<LogicResponse> logic(){
        LogicRequest request = new LogicRequest();

        return mService.getApi().logic(request);
    }
}
