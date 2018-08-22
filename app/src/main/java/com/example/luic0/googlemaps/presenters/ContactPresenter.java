package com.example.luic0.googlemaps.presenters;

import android.content.Context;

import com.example.luic0.googlemaps.application.ActivityBase;
import com.example.luic0.googlemaps.interfaces.IContact;
import com.example.luic0.googlemaps.managers.ContactManager;

/**
 * Created by Francisco Carranza on 8/21/18.
 * eSoft del Pacifico
 */
public class ContactPresenter extends ActivityBase{
    private IContact mView;
    private Context mContext;

    public ContactPresenter(IContact iContact, Context context){
        this.mView = iContact;
        this.mContext = context;
    }

    public void context(String name, String subject, String email, String message){
        final ContactManager manager = new ContactManager();
    }
}
