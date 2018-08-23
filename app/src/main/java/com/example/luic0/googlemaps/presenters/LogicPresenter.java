package com.example.luic0.googlemaps.presenters;

import android.content.Context;

import com.example.luic0.googlemaps.application.ActivityBase;
import com.example.luic0.googlemaps.interfaces.ILogic;

/**
 * Created by Francisco Carranza on 8/22/18.
 * eSoft del Pacifico
 */
public class LogicPresenter extends ActivityBase{
    private ILogic mView;
    private Context mContext;

    public LogicPresenter(ILogic iLogic, Context context){
        this.mView = iLogic;
        this.mContext = context;
    }
}
