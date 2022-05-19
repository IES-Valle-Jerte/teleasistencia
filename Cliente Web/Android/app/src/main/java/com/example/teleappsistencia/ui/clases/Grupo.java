package com.example.teleappsistencia.ui.clases;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Grupo implements Serializable {

    /**
     * Atributos de la clase
     */
    @SerializedName("pk")
    private int pk;
    @SerializedName("name")
    private String name;

    /**
     * Constructor para mostrar un Grupo.
     * @param pk
     * @param name
     */
    public Grupo(int pk, String name) {
        this.pk = pk;
        this.name = name;
    }

    /**
     * Constructor para modicar o insertar un nuevo Grupo.
     * @param name
     */
    public Grupo(String name) {
        this.name = name;
    }


    /*
     * Getters y setters
     */
    public int getPk() {
        return pk;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}