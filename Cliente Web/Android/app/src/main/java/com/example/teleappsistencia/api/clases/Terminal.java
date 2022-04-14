package com.example.teleappsistencia.api.clases;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Terminal implements Serializable {
    private final static long serialVersionUID = 7948631309169889790L;

    @SerializedName("id")
    private int id;
    @SerializedName("numero_terminal")
    private String numeroTerminal;
    @SerializedName("modo_acceso_vivienda")
    private String modoAccesoVivienda;
    @SerializedName("barreras_arquitectonicas")
    private String barrerasArquitectonicas;
    @SerializedName("id_titular")
    private Titular titular;
    @SerializedName("id_tipo_vivienda")
    private TipoVivienda tipoVivienda;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumeroTerminal() {
        return numeroTerminal;
    }

    public void setNumeroTerminal(String numeroTerminal) {
        this.numeroTerminal = numeroTerminal;
    }

    public String getModoAccesoVivienda() {
        return modoAccesoVivienda;
    }

    public void setModoAccesoVivienda(String modoAccesoVivienda) {
        this.modoAccesoVivienda = modoAccesoVivienda;
    }

    public String getBarrerasArquitectonicas() {
        return barrerasArquitectonicas;
    }

    public void setBarrerasArquitectonicas(String barrerasArquitectonicas) {
        this.barrerasArquitectonicas = barrerasArquitectonicas;
    }

    public Titular getTitular() {
        return titular;
    }

    public void setTitular(Titular titular) {
        this.titular = titular;
    }

    public TipoVivienda getTipoVivienda() {
        return tipoVivienda;
    }

    public void setTipoVivienda(TipoVivienda tipoVivienda) {
        this.tipoVivienda = tipoVivienda;
    }

}
