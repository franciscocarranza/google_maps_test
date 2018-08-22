package com.example.luic0.googlemaps.view.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luic0.googlemaps.R;
import com.example.luic0.googlemaps.application.ActivityBase;
import com.example.luic0.googlemaps.interfaces.ILogin;
import com.example.luic0.googlemaps.interfaces.IPasswordRecovery;
import com.example.luic0.googlemaps.models.responses.LoginResponse;
import com.example.luic0.googlemaps.models.responses.PasswordRecoveryResponse;
import com.example.luic0.googlemaps.presenters.LoginPresenter;
import com.example.luic0.googlemaps.presenters.PasswordRecoveryPresenter;
import com.example.luic0.googlemaps.utils.Utilerias;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends ActivityBase implements ILogin, IPasswordRecovery{

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private static final int LOCATION_SETTINGS_REQUEST = 200;
    private Dialog mDialog;
    PasswordRecoveryPresenter passwordRecoveryPresenter;
    LoginPresenter loginPresenter;
    String email, password;
    EditText enterEmailEdt;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @BindView(R.id.edt_email)
    EditText emailEdt;

    @BindView(R.id.edt_password)
    EditText passwordEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        isServicesOK();
        displayLocationSettings();

        //when the user clicks outside of an edttxt the keyboard will be hidden
        emailEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        passwordEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        loginPresenter = new LoginPresenter(this, this);
        passwordRecoveryPresenter = new PasswordRecoveryPresenter(this, this);

    }

    @OnClick(R.id.txt_haz_click)
    public void goToRegistro() {
        startActivity(new Intent(this, RegistroActivity.class));
    }

    @OnClick(R.id.btn_map)
    public void goToMap () {
        if (validarCampos()) {
            email = emailEdt.getText().toString().trim();
            password = passwordEdt.getText().toString();

            if (email.matches(emailPattern)) {
                loginPresenter.login(email, password);
            } else {
                Toast.makeText(getApplicationContext(),"Correo electronico invalido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Ingrese los campos requeridos", Toast.LENGTH_SHORT).show();
        }
    }

    //goes to dialog window
    @OnClick(R.id.txt_olvidastes_contra)
    public void goToPassRecovery (){
        mDialog = new Dialog(this);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //mDialog.setCancelable(false);
        mDialog.setContentView(R.layout.dialog_forgot_password);

        Button btnRecuperar = mDialog.findViewById(R.id.btn_reset_pass);
        enterEmailEdt = mDialog.findViewById(R.id.edt_enter_email);

        btnRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarCorreo()) {
                    String verifyEmail = enterEmailEdt.getText().toString().trim();

                    if (verifyEmail.matches(emailPattern)) {
                        passwordRecoveryPresenter.passwordRecovery(verifyEmail);
                        //Toast.makeText(getApplicationContext(),"valid email ",Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(),"Correo electronico invalido", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Ingrese los campos requeridos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mDialog.show();
    }

    @Override
    public void loginOk(LoginResponse response) {
        startActivity(new Intent(this, MapActivity.class));

    }

    @Override
    public void loginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void passwordRecoveryOk(PasswordRecoveryResponse response) {
        Toast.makeText(MainActivity.this, "Contraseña reseteada", Toast.LENGTH_SHORT).show();
        mDialog.dismiss();
    }

    @Override
    public void passwordRecoveryError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        mDialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("", "");
    }

    //hides soft keyboard
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

        //checks google play services version
    public void isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error has occurred but we can resolve it
            Log.d(TAG, "isServicesOk: an error occurred but we can fix it");
            Dialog dialog =GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "no puedes hacer solicitudes de mapas", Toast.LENGTH_SHORT).show();
        }
    }

    public void displayLocationSettings() {
        final LocationRequest mLocationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10 * 1000)
                .setFastestInterval(1000);

        LocationSettingsRequest.Builder settingsBuilder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);
        settingsBuilder.setAlwaysShow(true);

        Task<LocationSettingsResponse> result = LocationServices.getSettingsClient(this)
                .checkLocationSettings(settingsBuilder.build());

        result.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                try {
                    LocationSettingsResponse response =
                            task.getResult(ApiException.class);
                } catch (ApiException ex) {
                    switch (ex.getStatusCode()) {
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            try {
                                ResolvableApiException resolvableApiException =
                                        (ResolvableApiException) ex;
                                resolvableApiException
                                        .startResolutionForResult(MainActivity.this, LOCATION_SETTINGS_REQUEST);
                            } catch (IntentSender.SendIntentException e) {

                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:

                            break;
                    }
                }
            }
        });
    }

    //validates if the user has left any edttexts empty
    public boolean validarCampos() {
        boolean ret = true;
        if (!Utilerias.hasText(emailEdt, "Campo requerido"))
             ret = false;
        if (!Utilerias.hasText(passwordEdt, "Campo requerido"))
             ret = false;
        return ret;
    }

    public boolean validarCorreo(){
        boolean ret = true;
        if (!Utilerias.hasText(enterEmailEdt, "Campo requerido"))
            ret = false;
        return ret;
    }
}
