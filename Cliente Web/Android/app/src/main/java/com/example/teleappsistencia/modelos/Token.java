package com.example.teleappsistencia.modelos;

import java.io.Serializable;

/**
 * Clase encargada del modelo del Token.
 */
public class Token implements Serializable {

    /**
     * Atributos de la clase
     */
    private String refresh;
    private String access;

    /**
     * Getters y setters
     */
    public String getRefresh() {
        return refresh;
    }

    public void setRefresh(String refresh) {
        this.refresh = refresh;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    /**
     * Método toString
     * @return
     */
    @Override
    public String toString() {
        return "Token{" +
                "refresh='" + refresh + '\'' +
                ", access='" + access + '\'' +
                '}';
    }
}
