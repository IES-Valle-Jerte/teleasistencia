package com.example.teleappsistencia.api.clases;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Agenda implements Serializable {
    private final static long serialVersionUID = 5092617412720296199L;

    @SerializedName("id")
    private int id;
    @SerializedName("fecha_registro")
    private String fechaRegistro;
    @SerializedName("fecha_prevista")
    private String fechaPrevista;
    @SerializedName("fecha_resolucion")
    private Object fechaResolucion;
    @SerializedName("observaciones")
    private String observaciones;
    @SerializedName("id_paciente")
    private Paciente paciente;
    @SerializedName("id_tipo_agenda")
    private TipoAgenda tipoAgenda;
    @SerializedName("id_persona")
    private Persona persona;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaPrevista() {
        return fechaPrevista;
    }

    public void setFechaPrevista(String fechaPrevista) {
        this.fechaPrevista = fechaPrevista;
    }

    public Object getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Object fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public TipoAgenda getTipoAgenda() {
        return tipoAgenda;
    }

    public void setTipoAgenda(TipoAgenda tipoAgenda) {
        this.tipoAgenda = tipoAgenda;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

}
