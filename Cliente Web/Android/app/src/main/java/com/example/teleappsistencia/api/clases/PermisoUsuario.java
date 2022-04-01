package com.example.teleappsistencia.api.clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PermisoUsuario{

    /**
     * Atributos de la clase
     */
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("codename")
    private String codename;
    @SerializedName("content_type")
    private int contentType;

    /**
     * Getters y setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    public int getContentType() {
        return contentType;
    }

    public void setContentType(int contentType) {
        this.contentType = contentType;
    }

}
