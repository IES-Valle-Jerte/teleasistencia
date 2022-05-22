package com.example.teleappsistencia.modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
/**
 * Clase POJO "RelacionUsuarioCentro" utilizada para parsear la respuesta JSON del servidor.
 */
public class RelacionUsuarioCentro implements Serializable {

    /**
     * Atributos de la clase POJO con sus anotaciones GSON correspondientes,
     * que se utilizan para mapear las JSON keys hacia campos Java.
     */
    @SerializedName("id")
    private int id;
    @SerializedName("persona_contacto")
    private String personaContacto;
    @SerializedName("distancia")
    private int distancia;
    @SerializedName("tiempo")
    private int tiempo;
    @SerializedName("observaciones")
    private String observaciones;
    @SerializedName("id_paciente")
    private Object idPaciente;
    @SerializedName("id_centro_sanitario")
    private Object idCentroSanitario;

    //Getters y setters
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Object getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Object idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Object getIdCentroSanitario() {
        return idCentroSanitario;
    }

    public void setIdCentroSanitario(Object idCentroSanitario) {
        this.idCentroSanitario = idCentroSanitario;
    }
}
