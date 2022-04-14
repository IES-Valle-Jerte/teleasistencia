package com.example.teleappsistencia.api.clases;

import com.google.gson.annotations.SerializedName;

public class Direccion {

    @SerializedName("id")
    private int id;
    @SerializedName("localidad")
    private String localidad;
    @SerializedName("provincia")
    private String provincia;
    @SerializedName("direccion")
    private String direccion;
    @SerializedName("codigo_postal")
    private String codigoPostal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Direccion(String localidad, String provincia, String direccion, String codigoPostal) {
        this.localidad = localidad;
        this.provincia = provincia;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "id=" + id +
                ", localidad='" + localidad + '\'' +
                ", provincia='" + provincia + '\'' +
                ", direccion='" + direccion + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                '}';
    }
}