package com.example.teleappsistencia.modelos;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RecursoComunitario implements Serializable {

    // Declaración de atributos.
    @SerializedName("id")
    private int id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("telefono")
    private String telefono;
    @SerializedName("id_tipos_recurso_comunitario")
    private Object tipoRecursoComunitario;
    @SerializedName("id_direccion")
    private Direccion direccion;

    // Establecemos y obtenemos los atributos de la clase con sus getters y setters.

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

    public Object getTipoRecursoComunitario() {
        return tipoRecursoComunitario;
    }

    public void setTipoRecursoComunitario(Object tipoRecursoComunitario) {
        this.tipoRecursoComunitario = tipoRecursoComunitario;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    /**
     * Se devuelve el nombre del Recurso Comunitario para identificarlo en ListViews, Spinners etc.
     * @return
     */
    @NonNull
    @Override
    public String toString() {
        return this.nombre;
    }
}