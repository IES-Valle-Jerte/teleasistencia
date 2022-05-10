package com.example.teleappsistencia.ui.clases;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TipoVivienda implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("nombre")
    private String nombre;

    /**
     * Constructor para mostrar un TipoVivienda.
     * @param id
     * @param nombre
     */
    public TipoVivienda(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    /**
     * CDonstructor para modificar o insertar un nuevo TipoVivienda.
     * @param nombre
     */
    public TipoVivienda(String nombre){
        this.nombre = nombre;
    }


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

    @Override
    public String toString() {
        return "Tipo_vivienda{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
