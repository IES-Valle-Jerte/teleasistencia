package com.example.teleappsistencia.api.clases;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RecursoComunitario implements Serializable
{
    private final static long serialVersionUID = -7703354461267734537L;

    @SerializedName("id")
    private int id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("telefono")
    private String telefono;
    @SerializedName("id_tipos_recurso_comunitario")
    private TipoRecursoComunitario tipoRecursoComunitario;
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

    public TipoRecursoComunitario getTipoRecursoComunitario() {
        return tipoRecursoComunitario;
    }

    public void setTipoRecursoComunitario(TipoRecursoComunitario tipoRecursoComunitario) {
        this.tipoRecursoComunitario = tipoRecursoComunitario;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

}
