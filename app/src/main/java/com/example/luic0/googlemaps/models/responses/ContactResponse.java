package com.example.luic0.googlemaps.models.responses;

/**
 * Created by Francisco Carranza on 8/21/18.
 * eSoft del Pacifico
 */
public class ContactResponse {
    public Object Data;
    public Boolean IsValid;
    public String Message;

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
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
