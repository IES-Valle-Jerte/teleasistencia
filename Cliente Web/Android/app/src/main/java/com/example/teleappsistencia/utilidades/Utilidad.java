package com.example.teleappsistencia.utilidades;

import com.example.teleappsistencia.modelos.CentroSanitario;
import com.example.teleappsistencia.modelos.Direccion;
import com.example.teleappsistencia.modelos.Paciente;
import com.example.teleappsistencia.modelos.Persona;
import com.example.teleappsistencia.modelos.RecursoComunitario;
import com.example.teleappsistencia.modelos.RelacionPacientePersona;
import com.example.teleappsistencia.modelos.RelacionTerminalRecursoComunitario;
import com.example.teleappsistencia.modelos.Terminal;
import com.example.teleappsistencia.modelos.TipoModalidadPaciente;
import com.example.teleappsistencia.modelos.Token;
import com.example.teleappsistencia.modelos.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Utilidad {

    /**
     * Token para poder realizar las peticiones a la API.
     */
    private static Token token;

    public static Object getObjeto(Object linkedTreeMap, String tipo) {
        Gson gson = new Gson();
        Type type = null;
        Object objeto = null;
        switch (tipo) {
            case "Paciente":
                type = new TypeToken<Paciente>() {
                }.getType();
                break;
            case "RelacionPacientePersonaViewholder":
                type = new TypeToken<RelacionPacientePersona>() {
                }.getType();
                break;
            case "CentroSanitario":
                type = new TypeToken<CentroSanitario>() {
                }.getType();
                break;
            case "Direccion":
                type = new TypeToken<Direccion>() {
                }.getType();
                break;
            case "Persona":
                type = new TypeToken<Persona>() {
                }.getType();
                break;
            case "RelacionTerminalRecursoComunitario":
                type = new TypeToken<RelacionTerminalRecursoComunitario>() {
                }.getType();
                break;
            case "Terminal":
                type = new TypeToken<Terminal>() {
                }.getType();
                break;
            case "Usuario":
                type = new TypeToken<Usuario>() {
                }.getType();
                break;
            case "TipoModalidadPaciente":
                type = new TypeToken<TipoModalidadPaciente>() {
                }.getType();
                break;
            case "RecursoComunitario":
                type = new TypeToken<RecursoComunitario>() {
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
