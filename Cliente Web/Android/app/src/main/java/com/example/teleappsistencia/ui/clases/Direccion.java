package com.example.teleappsistencia.ui.clases;

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

    /**
     * Constructor para mostrar una Direccion.
     * @param id
     * @param localidad
     * @param provincia
     * @param direccion
     * @param codigoPostal
     */
    public Direccion(int id, String localidad, String provincia, String direccion, String codigoPostal) {
        this.id = id;
        this.localidad = localidad;
        this.provincia = provincia;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }

    /**
     * Constructor para modificar o insertar una nueva Direccion.
     * @param localidad
     * @param provincia
     * @param direccion
     * @param codigoPostal
     */
    public Direccion(String localidad, String provincia, String direccion, String codigoPostal) {
        this.localidad = localidad;
        this.provincia = provincia;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
    }


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
