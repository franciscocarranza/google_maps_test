package com.example.luic0.googlemaps.models.data;

/**
 * Created by Francisco Carranza on 8/22/18.
 * eSoft del Pacifico
 */
public class LogicData {
    public String name;
    public String address;
    public Void logo;
    public Integer latitud;
    public Integer longitud;
    public String created_at;
    public Integer logiaType;
    public String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Void getLogo() {
        return logo;
    }

    public void setLogo(Void logo) {
        this.logo = logo;
    }

    public Integer getLatitud() {
        return latitud;
    }

    public void setLatitud(Integer latitud) {
        this.latitud = latitud;
    }

    public Integer getLongitud() {
        return longitud;
    }

    public void setLongitud(Integer longitud) {
        this.longitud = longitud;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public Integer getLogiaType() {
        return logiaType;
    }

    public void setLogiaType(Integer logiaType) {
        this.logiaType = logiaType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
