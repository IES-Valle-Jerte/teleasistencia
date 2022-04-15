package com.example.teleappsistencia.clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Tipo_vivienda implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("nombre")
    private String nombre;

    public Tipo_vivienda(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Tipo_vivienda(){
        this.id = 0;
        this.nombre = "";
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
