package com.example.teleappsistencia.modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TipoModalidadPaciente implements Serializable {

    // Declaración de atributos.
    @SerializedName("id")
    private int id;
    @SerializedName("nombre")
    private String nombreTipoModalidadPaciente;

    /**
     * Inicializamos las variables en el constructor parametrizado.
     *
     * @param nombreTipoModalidadPaciente
     */
    public TipoModalidadPaciente(String nombreTipoModalidadPaciente) {
        this.nombreTipoModalidadPaciente = nombreTipoModalidadPaciente;
    }

    // Establecemos y obtenemos los atributos de la clase con sus getters y setters.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreTipoModalidadPaciente() {
        return nombreTipoModalidadPaciente;
    }

    public void setNombreTipoModalidadPaciente(String nombreTipoModalidadPaciente) {
        this.nombreTipoModalidadPaciente = nombreTipoModalidadPaciente;
    }
}