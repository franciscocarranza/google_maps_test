package com.example.luic0.googlemaps.presenters;

import android.content.Context;
import android.util.Log;

import com.example.luic0.googlemaps.interfaces.IPasswordRecovery;
import com.example.luic0.googlemaps.managers.PasswordRecoveryManager;
import com.example.luic0.googlemaps.models.responses.PasswordRecoveryResponse;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Francisco Carranza on 8/9/18.
 * eSoft del Pacifico
 */
public class PasswordRecoveryPresenter {
    private IPasswordRecovery mView;
    private Context context;

    public PasswordRecoveryPresenter(IPasswordRecovery iPasswordRecovery, Context context){
        this.mView = iPasswordRecovery;
        this.context = context;
    }

    public void passwordRecovery(String email){
        final PasswordRecoveryManager manager = new PasswordRecoveryManager();
        manager.passwordRecovery(email)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<PasswordRecoveryResponse>() {
                    @Override
                    public void onNext(PasswordRecoveryResponse passwordRecoveryResponse) {
                        Log.e("", "");
                        if(passwordRecoveryResponse.getValid()){
                            mView.passwordRecoveryOk(passwordRecoveryResponse);

                        } else {
                            mView.passwordRecoveryError(passwordRecoveryResponse.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("", "");
                        mView.passwordRecoveryError(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("", "");
                    }
                });
    }
}
