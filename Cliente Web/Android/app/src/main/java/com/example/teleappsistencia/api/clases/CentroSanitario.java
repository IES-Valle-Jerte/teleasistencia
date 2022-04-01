package com.example.teleappsistencia.api.clases;

import com.google.gson.annotations.SerializedName;

public class CentroSanitario {

    @SerializedName("id")
    private int id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("telefono")
    private String telefono;
    @SerializedName("id_tipos_centro_sanitario")
    private TipoCentroSanitario tipoCentroSanitario;
    @SerializedName("id_direccion")
    private Direccion direccion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoCentroSanitario getTipoCentroSanitario() {
        return tipoCentroSanitario;
    }

    public void setTipoCentroSanitario(TipoCentroSanitario tipoCentroSanitario) {
        this.tipoCentroSanitario = tipoCentroSanitario;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}
