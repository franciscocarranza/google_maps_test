package com.example.luic0.googlemaps.models.responses;

import com.example.luic0.googlemaps.models.data.LoginData;

/**
 * Created by Francisco Carranza on 8/21/18.
 * eSoft del Pacifico
 */
public class LogicResponse {
    public LoginData Data;
    public Boolean IsValid;
    public String Message;

    public LoginData getData() {
        return Data;
    }

    public void setData(LoginData data) {
        Data = data;
    }

    public Boolean getValid() {
        return IsValid;
    }

    public void setValid(Boolean valid) {
        IsValid = valid;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
