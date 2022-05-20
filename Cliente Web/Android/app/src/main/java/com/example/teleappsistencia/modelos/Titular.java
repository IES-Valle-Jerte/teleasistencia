package com.example.teleappsistencia.modelos;

import com.google.gson.annotations.SerializedName;

public class Titular {

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
    private Object terminal;
    @SerializedName("id_persona")
    private Object persona;
    @SerializedName("id_tipo_modalidad_paciente")
    private Object tipoModalidadPaciente;
}
