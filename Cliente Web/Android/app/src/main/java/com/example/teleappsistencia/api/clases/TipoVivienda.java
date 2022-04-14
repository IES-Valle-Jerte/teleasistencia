package com.example.teleappsistencia.api.clases;

import com.google.gson.annotations.SerializedName;

public class TipoVivienda {

    @SerializedName("id")
    private int id;
    @SerializedName("nombre")
    private String nombre;

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
        return "TipoVivienda{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
