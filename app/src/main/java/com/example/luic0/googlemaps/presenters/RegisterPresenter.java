package com.example.luic0.googlemaps.presenters;

import android.content.Context;
import android.util.Log;

import com.example.luic0.googlemaps.interfaces.IRegister;
import com.example.luic0.googlemaps.managers.RegisterManager;
import com.example.luic0.googlemaps.models.responses.RegisterResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Francisco Carranza on 8/7/18.
 * eSoft del Pacifico
 */

public class RegisterPresenter {
    private IRegister mView;
    private Context context;

    public RegisterPresenter(IRegister iRegister, Context context){
        this.mView = iRegister;
        this.context = context;
    }

    public void register(String user, String password, String email){
        final RegisterManager manager = new RegisterManager();
        manager.register(user, password, email)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
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
                    }

                    @Override
                    public void onComplete() {
                        Log.e("", "");
                    }
                });
    }
}
