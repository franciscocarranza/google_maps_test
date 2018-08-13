 package com.example.luic0.googlemaps.presenters;

import android.content.Context;
import android.util.Log;

import com.example.luic0.googlemaps.interfaces.ILogin;
import com.example.luic0.googlemaps.managers.LoginManager;
import com.example.luic0.googlemaps.models.responses.LoginResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Francisco Carranza on 8/7/18.
 * eSoft del Pacifico
 */
public class LoginPresenter {
    private ILogin mView;
    private Context context;

    public LoginPresenter(ILogin iLogin, Context context){
        this.mView = iLogin;
        this.context = context;
    }

    public void login(String email, String password){
        final LoginManager manager = new LoginManager();
        manager.login(email, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<LoginResponse>() {
                    @Override
                    public void onNext(LoginResponse loginResponse) {
                        Log.e("", "");
                        if(loginResponse.getValid()){
                            mView.loginOk(loginResponse);

                        } else {
                            mView.loginError(loginResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("", "");
                        mView.loginError(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("", "");
                    }
                });
    }
}
