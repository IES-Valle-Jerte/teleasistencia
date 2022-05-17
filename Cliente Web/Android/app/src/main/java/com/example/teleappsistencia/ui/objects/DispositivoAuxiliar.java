package com.example.teleappsistencia.ui.objects;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DispositivoAuxiliar implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("id_terminal")
    private Object terminal;
    @SerializedName("id_tipo_alarma")
    private Object tipoAlarma;


    public DispositivoAuxiliar(int id, Object terminal, Object tipoAlarma) {
        this.id = id;
        this.terminal = terminal;
        this.tipoAlarma = tipoAlarma;
    }

    public DispositivoAuxiliar(Object terminal, Object tipoAlarma) {
        this.terminal = terminal;
        this.tipoAlarma = tipoAlarma;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getTerminal() {
        return terminal;
    }

    public void setTerminal(Object terminal) {
        this.terminal = terminal;
    }

    public Object getTipoAlarma() {
        return tipoAlarma;
    }

    public void setTipoAlarma(Object tipoAlarma) {
        this.tipoAlarma = tipoAlarma;
    }

    @Override
    public String toString() {
        return "DispositivoAuxiliar{" +
                "id=" + id +
                ", terminal=" + terminal +
                ", tipoAlarma=" + tipoAlarma +
                '}';
    }
}
