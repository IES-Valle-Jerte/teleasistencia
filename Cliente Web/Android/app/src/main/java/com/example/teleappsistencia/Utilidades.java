package com.example.teleappsistencia;

import com.example.teleappsistencia.clases.CentroSanitario;
import com.example.teleappsistencia.clases.Direccion;
import com.example.teleappsistencia.clases.Paciente;
import com.example.teleappsistencia.clases.Persona;
import com.example.teleappsistencia.clases.RecursoComunitario;
import com.example.teleappsistencia.clases.RelacionPacientePersona;
import com.example.teleappsistencia.clases.RelacionTerminalRecursoComunitario;
import com.example.teleappsistencia.clases.Terminal;
import com.example.teleappsistencia.clases.TipoModalidadPaciente;
import com.example.teleappsistencia.clases.Token;
import com.example.teleappsistencia.clases.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Utilidades {

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
        Utilidades.token = token;
    }
}
