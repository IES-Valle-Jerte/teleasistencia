package com.example.teleappsistencia.modelos;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class RelacionTerminalRecursoComunitario implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("id_terminal")
    private Object idTerminal;
    @SerializedName("id_recurso_comunitario")
    private Object idRecursoComunitario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(Object idTerminal) {
        this.idTerminal = idTerminal;
    }

    public Object getIdRecursoComunitario() {
        return idRecursoComunitario;
    }

    public void setIdRecursoComunitario(Object idRecursoComunitario) {
        this.idRecursoComunitario = idRecursoComunitario;
    }

}
