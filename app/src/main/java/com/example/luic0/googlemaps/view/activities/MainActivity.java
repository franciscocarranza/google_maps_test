package com.example.luic0.googlemaps.view.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luic0.googlemaps.R;
import com.example.luic0.googlemaps.interfaces.ILogin;
import com.example.luic0.googlemaps.interfaces.IPasswordRecovery;
import com.example.luic0.googlemaps.models.responses.LoginResponse;
import com.example.luic0.googlemaps.models.responses.PasswordRecoveryResponse;
import com.example.luic0.googlemaps.presenters.LoginPresenter;
import com.example.luic0.googlemaps.presenters.PasswordRecoveryPresenter;
import com.example.luic0.googlemaps.utils.Utilerias;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ILogin, IPasswordRecovery{

    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;
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
        statusCheck();

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

        //checks to see if location is on
    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        assert manager != null;
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            buildAlertMessageNoGps();

        }
    }

        //creates dialog box window to turn on location
    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Se requiere GPS para esta aplicación, habilítela")
                .setCancelable(false)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
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
