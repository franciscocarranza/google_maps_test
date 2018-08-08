package com.example.luic0.googlemaps.application;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Julian Nuñez on 29/07/18.
 * eSoft del Pacifico
 */

public class Preferences {
    //Configuration
    private  final String SHARED_PREFS_FILE = "Preferencias";
    private static Preferences mInstance;
    private SharedPreferences mMyPreferences;
    private Context mContext;

    //variables
    private final static String TOKEN = "token";
    private final static String USER_NAME = "userName";
    private final static String SESSION = "session";
    private Preferences(){}

    public static Preferences getInstance(){
        if(mInstance == null){
            mInstance = new Preferences();
        }
        return mInstance;
    }

    public void initialize(Context context){
        mContext = context;
        mMyPreferences = mContext.getSharedPreferences(SHARED_PREFS_FILE, 0);
    }

    public void setToken(String token){
        SharedPreferences.Editor editor = mMyPreferences.edit();
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public String getToken(){
        return mMyPreferences.getString(TOKEN, "");
    }

    public void setSession(Boolean isLogged){
        SharedPreferences.Editor editor = mMyPreferences.edit();
        editor.putBoolean(SESSION, isLogged);
        editor.commit();
    }

    public Boolean getSession(){
        return mMyPreferences.getBoolean(SESSION, false);
    }

    public void setUserName(String userName){
        SharedPreferences.Editor editor = mMyPreferences.edit();
        editor.putString(USER_NAME, userName);
        editor.commit();
    }

    public String getUserName(){
        return mMyPreferences.getString(USER_NAME, "");
    }
}
