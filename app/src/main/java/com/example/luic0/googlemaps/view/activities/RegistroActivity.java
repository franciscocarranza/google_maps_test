package com.example.luic0.googlemaps.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luic0.googlemaps.R;
import com.example.luic0.googlemaps.utils.Utilerias;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroActivity extends AppCompatActivity {

    @BindView(R.id.edt_name)
    EditText nameEdt;

    @BindView(R.id.edt_contraseña)
    EditText contraEdt;

    @BindView(R.id.edt_correo)
    EditText correoEdt;

    @BindView(R.id.check_accepto)
    CheckBox acceptoCheck;

    @OnClick(R.id.btn_crear_cuenta)
    public void registro () {
        if (validarCampos()) {
            startActivity(new Intent(this, MainActivity.class));
        } else {
            Toast.makeText(this, "llena los campos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);

        nameEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        contraEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });

        correoEdt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    hideKeyboard(v);
                }
            }
        });
    }

    public boolean validarCampos() {
        boolean ret = true;
        if (Utilerias.hasText(nameEdt, "Campo requerido"))
            ret = false;
        if (Utilerias.hasText(correoEdt, "Campo requerido"))
            ret = false;
        if (Utilerias.hasText(contraEdt, "Campo requerido"))
            ret = false;
        if (!Utilerias.checked(acceptoCheck, "la casilla de verificación \n no está marcada"))
            ret = false;
        return ret;
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}