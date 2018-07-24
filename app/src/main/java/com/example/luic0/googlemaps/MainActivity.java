package com.example.luic0.googlemaps;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";

    private static final int ERROR_DIALOG_REQUEST = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        hideSoftKeyboard();

        if (isServicesOK()){
            goToMap();
        }
    }

        @OnClick(R.id.btn_map)
        public void goToMap () {
            startActivity(new Intent(this, MapActivity.class));
        }

        @OnClick(R.id.txt_haz_click)
        public void goTORegistro () {
            startActivity(new Intent(this, RegistroActivity.class));
        }

    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: cheking google services vesion");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(MainActivity.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error has ocurred but we can resolve it
            Log.d(TAG, "isServicesOk: an error ocurred but we can fix it");
            Dialog dialog =GoogleApiAvailability.getInstance().getErrorDialog(MainActivity.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(this, "you cant make make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
