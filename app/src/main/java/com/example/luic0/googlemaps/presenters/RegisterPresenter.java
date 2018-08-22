package com.example.luic0.googlemaps.presenters;

import android.content.Context;
import android.util.Log;

import com.example.luic0.googlemaps.application.ActivityBase;
import com.example.luic0.googlemaps.interfaces.IRegister;
import com.example.luic0.googlemaps.managers.RegisterManager;
import com.example.luic0.googlemaps.models.responses.RegisterResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Francisco Carranza on 8/7/18.
 * eSoft del Pacifico
 */

public class RegisterPresenter extends ActivityBase{
    private IRegister mView;
    private Context mContext;

    public RegisterPresenter(IRegister iRegister, Context context){
        this.mView = iRegister;
        this.mContext = context;
    }

    public void register(String user, String password, String email){
        final RegisterManager manager = new RegisterManager();
        manager.register(user, password, email)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        ((ActivityBase) mContext).showProgressBar();
                    }
                })
                .subscribe(new DisposableObserver<RegisterResponse>() {
                    @Override
                    public void onNext(RegisterResponse registerResponse) {
                        Log.e("", "");
                        if(registerResponse.getValid()){
                            mView.registerOk(registerResponse);

                        } else {
                            mView.registerError(registerResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("", "");
                        mView.registerError(e.toString());
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
