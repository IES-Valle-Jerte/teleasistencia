package com.example.teleappsistencia.modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HistoricoTipoSituacion implements Serializable {


    @SerializedName("id")
    private int id;
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("id_tipo_situacion")
    private Object idTipoSituacion;
    @SerializedName("tipo_situacion")
    private Object tipoSituacion;
    @SerializedName("id_terminal")
    private Object terminal;

    /**
     * Constructor para mostrar un HistoricoTipoSituacion.
     * @param id
     * @param fecha
     * @param idTipoSituacion
     * @param terminal
     */
    public HistoricoTipoSituacion(int id, String fecha, int idTipoSituacion, int terminal) {
        this.id = id;
        this.fecha = fecha;
        this.idTipoSituacion = idTipoSituacion;
        this.terminal = terminal;
    }

    /**
     * Constructor para insertar un HistoricoTipoSituacion.
     * @param fecha
     * @param tipoSituacion
     * @param terminal
     */
    public HistoricoTipoSituacion(String fecha, Object tipoSituacion, Object terminal) {
        this.fecha = fecha;
        this.tipoSituacion = tipoSituacion;
        this.terminal = terminal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Object getIdTipoSituacion() {
        return idTipoSituacion;
    }

    public void setIdTipoSituacion(Object idTipoSituacion) {
        this.idTipoSituacion = idTipoSituacion;
    }

    public Object getTipoSituacion() {
        return tipoSituacion;
    }

    public void setTipoSituacion(Object tipoSituacion) {
        this.tipoSituacion = tipoSituacion;
    }

    public Object getTerminal() {
        return terminal;
    }

    public void setTerminal(Object terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "HistoricoTipoSituacion{" +
                "id=" + id +
                ", fecha='" + fecha + '\'' +
                ", idTipoSituacion=" + idTipoSituacion +
                ", tipoSituacion=" + tipoSituacion +
                ", terminal=" + terminal +
                '}';
    }
}
