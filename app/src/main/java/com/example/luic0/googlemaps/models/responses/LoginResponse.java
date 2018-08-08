package com.example.luic0.googlemaps.models.responses;

import com.example.luic0.googlemaps.models.data.LoginData;

/**
 * Created by Francisco Carranza on 8/7/18.
 * eSoft del Pacifico
 */

public class LoginResponse {
    public LoginData Data;
    public Boolean IsValid;
    public String Message;
    public Integer Type;
    public Boolean HasMessages;

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

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
    }

    public Boolean getHasMessages() {
        return HasMessages;
    }

    public void setHasMessages(Boolean hasMessages) {
        HasMessages = hasMessages;
    }
}
