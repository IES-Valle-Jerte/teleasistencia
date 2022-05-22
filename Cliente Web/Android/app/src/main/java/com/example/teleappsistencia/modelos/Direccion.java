package com.example.teleappsistencia.modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Ejemplo de clase POJO para el objeto Direccion
 */
public class Direccion implements Serializable {

    //Atributos privados de la clase
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

    //Constructor vacio por defecto
    public Direccion(){}

    //Getters y setters

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
}