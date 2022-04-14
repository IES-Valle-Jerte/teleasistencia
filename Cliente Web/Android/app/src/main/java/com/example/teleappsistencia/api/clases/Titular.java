package com.example.teleappsistencia.api.clases;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Titular implements Serializable {
    private final static long serialVersionUID = 564315766794077984L;

    @SerializedName("id")
    private int id;
    @SerializedName("tiene_ucr")
    private boolean tieneUcr;
    @SerializedName("numero_expediente")
    private String numeroExpediente;
    @SerializedName("numero_seguridad_social")
    private String numeroSeguridadSocial;
    @SerializedName("prestacion_otros_servicios_sociales")
    private String prestacionOtrosServiciosSociales;
    @SerializedName("observaciones_medicas")
    private String observacionesMedicas;
    @SerializedName("intereses_y_actividades")
    private String interesesYActividades;
    @SerializedName("id_terminal")
    private Terminal terminal;
    @SerializedName("id_persona")
    private Persona persona;
    @SerializedName("id_tipo_modalidad_paciente")
    private TipoModalidadPaciente tipoModalidadPaciente;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTieneUcr() {
        return tieneUcr;
    }

    public void setTieneUcr(boolean tieneUcr) {
        this.tieneUcr = tieneUcr;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getNumeroSeguridadSocial() {
        return numeroSeguridadSocial;
    }

    public void setNumeroSeguridadSocial(String numeroSeguridadSocial) {
        this.numeroSeguridadSocial = numeroSeguridadSocial;
    }

    public String getPrestacionOtrosServiciosSociales() {
        return prestacionOtrosServiciosSociales;
    }

    public void setPrestacionOtrosServiciosSociales(String prestacionOtrosServiciosSociales) {
        this.prestacionOtrosServiciosSociales = prestacionOtrosServiciosSociales;
    }

    public String getObservacionesMedicas() {
        return observacionesMedicas;
    }

    public void setObservacionesMedicas(String observacionesMedicas) {
        this.observacionesMedicas = observacionesMedicas;
    }

    public String getInteresesYActividades() {
        return interesesYActividades;
    }

    public void setInteresesYActividades(String interesesYActividades) {
        this.interesesYActividades = interesesYActividades;
    }

    public Terminal getTerminal() {
        return terminal;
    }

    public void setTerminal(Terminal terminal) {
        this.terminal = terminal;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public TipoModalidadPaciente getTipoModalidadPaciente() {
        return tipoModalidadPaciente;
    }

    public void setTipoModalidadPaciente(TipoModalidadPaciente tipoModalidadPaciente) {
        this.tipoModalidadPaciente = tipoModalidadPaciente;
    }

    @Override
    public String toString() {
        return "Titular{" +
                "id=" + id +
                ", tieneUcr=" + tieneUcr +
                ", numeroExpediente='" + numeroExpediente + '\'' +
                ", numeroSeguridadSocial='" + numeroSeguridadSocial + '\'' +
                ", prestacionOtrosServiciosSociales='" + prestacionOtrosServiciosSociales + '\'' +
                ", observacionesMedicas='" + observacionesMedicas + '\'' +
                ", interesesYActividades='" + interesesYActividades + '\'' +
                ", terminal=" + terminal +
                ", persona=" + persona +
                ", tipoModalidadPaciente=" + tipoModalidadPaciente +
                '}';
    }
}
