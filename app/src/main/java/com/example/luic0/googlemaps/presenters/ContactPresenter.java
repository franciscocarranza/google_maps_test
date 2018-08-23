package com.example.luic0.googlemaps.presenters;

import android.content.Context;
import android.util.Log;

import com.example.luic0.googlemaps.application.ActivityBase;
import com.example.luic0.googlemaps.interfaces.IContact;
import com.example.luic0.googlemaps.managers.ContactManager;
import com.example.luic0.googlemaps.models.responses.ContactResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

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

    public void contact(String name, String subject, String email, String message){
        final ContactManager manager = new ContactManager();
        manager.contact(name, subject, email, message)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        ((ActivityBase) mContext).showProgressBar();
                    }
                })
                .subscribe(new DisposableObserver<ContactResponse>() {
                    @Override
                    public void onNext(ContactResponse contactResponse) {
                        Log.e("", "");
                        if(contactResponse.getValid()){
                            mView.contactOk(contactResponse);
                        } else {
                            mView.contactError(contactResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("","");
                        mView.contactError(e.toString());
                        ((ActivityBase) mContext).hideProgressBar();
                    }

                    @Override
                    public void onComplete() {
                        Log.e("", "");
                        ((ActivityBase) mContext).hideProgressBar();
                    }
                });
    }
}
