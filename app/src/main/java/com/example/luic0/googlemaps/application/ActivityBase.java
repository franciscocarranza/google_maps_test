package com.example.luic0.googlemaps.application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.example.luic0.googlemaps.R;

/**
 * Created by Francisco Carranza on 8/20/18.
 * eSoft del Pacifico
 */
public class ActivityBase extends AppCompatActivity {

    protected ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showProgressBar () {
        if ( progressBar == null ) {
            progressBar = findViewById(R.id.progress_bar);
        }

        if ( progressBar != null ) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    public void hideProgressBar () {
        if ( progressBar == null ) {
            progressBar = findViewById(R.id.progress_bar);
        }

        if ( progressBar != null ) {
            progressBar.setVisibility(View.GONE);
        }
    }
}
