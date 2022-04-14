package com.example.teleappsistencia.api.clases;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HistoricoAgenda implements Serializable {
    private final static long serialVersionUID = -2216343229360516441L;

    @SerializedName("id")
    private int id;
    @SerializedName("fecha_llamada")
    private String fechaLlamada;
    @SerializedName("observaciones")
    private String observaciones;
    @SerializedName("id_agenda")
    private Agenda agenda;
    @SerializedName("id_teleoperador")
    private UsuarioSistema usuarioSistema;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaLlamada() {
        return fechaLlamada;
    }

    public void setFechaLlamada(String fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public UsuarioSistema getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(UsuarioSistema usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    @Override
    public String toString() {
        return "HistoricoAgenda{" +
                "id=" + id +
                ", fechaLlamada='" + fechaLlamada + '\'' +
                ", observaciones='" + observaciones + '\'' +
                ", agenda=" + agenda +
                ", teleoperador=" + usuarioSistema +
                '}';
    }
}
