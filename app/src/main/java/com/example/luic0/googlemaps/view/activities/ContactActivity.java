package com.example.luic0.googlemaps.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luic0.googlemaps.R;
import com.example.luic0.googlemaps.application.ActivityBase;
import com.example.luic0.googlemaps.interfaces.IContact;
import com.example.luic0.googlemaps.interfaces.ILogic;
import com.example.luic0.googlemaps.models.responses.ContactResponse;
import com.example.luic0.googlemaps.models.responses.LogicResponse;
import com.example.luic0.googlemaps.presenters.ContactPresenter;
import com.example.luic0.googlemaps.utils.Utilerias;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactActivity extends ActivityBase implements IContact{

    ContactPresenter contactPresenter;
    String asunto, mensaje, nombre, correo;

    @BindView(R.id.edt_asunto)
    EditText asuntoEdt;

    @BindView(R.id.edt_mensaje)
    EditText mensajeEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        ButterKnife.bind(this);
        getSupportActionBar().hide();

        asuntoEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        mensajeEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        contactPresenter = new ContactPresenter(this, this);
    }

    @OnClick(R.id.btn_enviar)
    public void goToMap () {
        if (validarCampos()) {
            asunto = asuntoEdt.getText().toString();
            mensaje = mensajeEdt.getText().toString();
            nombre = "francisco";
            correo = "fran@gmail.com";
            contactPresenter.contact(nombre, asunto, correo, mensaje);
        } else {
            Toast.makeText(ContactActivity.this, "Ingrese los campos requeridos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void contactOk(ContactResponse response) {
        startActivity(new Intent(this, MapActivity.class));
        Toast.makeText(this, "mensaje se envio", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void contactError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public boolean validarCampos(){
        boolean ret = true;
        if (!Utilerias.hasText(asuntoEdt, "Campo requerido"))
            ret = false;
        if (!Utilerias.hasText(mensajeEdt, "Campo requerido"))
            ret = false;
        return ret;
    }

}
