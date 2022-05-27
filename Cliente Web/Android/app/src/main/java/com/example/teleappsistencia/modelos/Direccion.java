package com.example.teleappsistencia.modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Direccion implements Serializable {

    // Declaración de atributos.
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

    // Establecemos y obtenemos los atributos de la clase con sus getters y setters.

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

    public void setDireccion(String dirección) {
        this.direccion = dirección;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}