package com.example.luic0.googlemaps.models.requests;

/**
 * Created by Francisco Carranza on 8/6/18.
 * eSoft del Pacifico
 */
public class RegisterRequest {
    private String nombre;
    private String contra;
    private String correo;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
