package com.example.luic0.googlemaps.view.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.luic0.googlemaps.R;
import com.example.luic0.googlemaps.interfaces.IRegister;
import com.example.luic0.googlemaps.models.responses.RegisterResponse;
import com.example.luic0.googlemaps.presenters.RegisterPresenter;
import com.example.luic0.googlemaps.utils.Utilerias;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistroActivity extends AppCompatActivity implements IRegister{

    RegisterPresenter registerPresenter;
    String user, password, email;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @BindView(R.id.edt_name)
    EditText nameEdt;

    @BindView(R.id.edt_contraseña)
    EditText contraEdt;

    @BindView(R.id.edt_correo)
    EditText correoEdt;

    @BindView(R.id.check_accepto)
    CheckBox acceptoCheck;

    @BindView(R.id.btn_crear_cuenta)
    Button crearCuentaBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
        crearCuentaBtn.getBackground().setAlpha(120);
        crearCuentaBtn.setClickable(false);

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

        registerPresenter = new RegisterPresenter(this, this);
    }

    @OnClick(R.id.btn_crear_cuenta)
    public void registro () {
        if (validarCampos()) {
            user = nameEdt.getText().toString();
            password = contraEdt.getText().toString();
            email = correoEdt.getText().toString().trim();

            if (email.matches(emailPattern)) {
                registerPresenter.register(user, password, email);
            } else {
                Toast.makeText(getApplicationContext(),"Correo electronico invalido", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Ingrese los campos requeridos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void registerOk(RegisterResponse response) {
        Toast.makeText(this, response.getMessage(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void registerError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public boolean validarCampos() {
        boolean ret = true;
        if (!Utilerias.hasText(nameEdt, "Campo requerido"))
            ret = false;
        if (!Utilerias.hasText(correoEdt, "Campo requerido"))
            ret = false;
        if (!Utilerias.hasText(contraEdt, "Campo requerido"))
            ret = false;
        if (!Utilerias.checked(acceptoCheck, "la casilla no está marcada"))
            ret = false;
        return ret;
    }

    @OnClick(R.id.check_accepto)
    public void checkboxState() {
        if ( !acceptoCheck.isChecked() ) {
            crearCuentaBtn.setClickable(false);
            crearCuentaBtn.getBackground().setAlpha(120);
        } else{
            crearCuentaBtn.setClickable(true);
            crearCuentaBtn.getBackground().setAlpha(255);
        }
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
