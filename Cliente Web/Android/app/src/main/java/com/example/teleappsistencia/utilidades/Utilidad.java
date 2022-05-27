package com.example.teleappsistencia.utilidades;

import com.example.teleappsistencia.modelos.Token;
import com.example.teleappsistencia.modelos.CentroSanitario;
import com.example.teleappsistencia.modelos.RecursoComunitario;
import com.example.teleappsistencia.modelos.TipoCentroSanitario;
import com.example.teleappsistencia.modelos.TipoModalidadPaciente;
import com.example.teleappsistencia.modelos.TipoRecursoComunitario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Utilidad {

    /**
     * Token para poder realizar las peticiones a la API.
     */
    private static Token token;

    /**
     * Este método convierte un Objeto LinkedTreeMap en un Objeto de otra clase, dependiendo de nuestras
     * necesidades.
     * @param linkedTreeMap
     * @param tipo
     * @return
     */
    public static Object getObjeto(Object linkedTreeMap, String tipo) {
        Gson gson = new Gson();
        Type type = null;
        Object objeto = null;
        switch (tipo) {
            case "TipoCentroSanitario":
                type = new TypeToken<TipoCentroSanitario>() {
                }.getType();
                break;
            case "TipoModalidadPaciente":
                type = new TypeToken<TipoModalidadPaciente>() {
                }.getType();
                break;
            case "CentroSanitario":
                type = new TypeToken<CentroSanitario>() {
                }.getType();
                break;
            case "TipoRecursoComunitario":
                type = new TypeToken<TipoRecursoComunitario>() {
                }.getType();
                break;
            case "RecursoComunitario":
                type = new TypeToken<RecursoComunitario>() {
                }.getType();
                break;
            case "ArrayList<TipoCentroSanitario>":
                type = new TypeToken<ArrayList<TipoCentroSanitario>>() {
                }.getType();
                break;
            case "ArrayList<TipoRecursoComunitario>":
                type = new TypeToken<ArrayList<TipoRecursoComunitario>>() {
                }.getType();
                break;
        }
        if (type != null) {
            objeto = gson.fromJson(gson.toJson(linkedTreeMap), type);
        }
        return objeto;
    }

    public static Token getToken() {
        return token;
    }

    public static void setToken(Token token) {
        Utilidad.token = token;
    }
}