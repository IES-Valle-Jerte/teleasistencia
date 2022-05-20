package com.example.teleappsistencia.utilidades;

import com.example.teleappsistencia.modelos.Direccion;
import com.example.teleappsistencia.modelos.Grupo;
import com.example.teleappsistencia.modelos.Terminal;
import com.example.teleappsistencia.modelos.TipoAlarma;
import com.example.teleappsistencia.modelos.TipoSituacion;
import com.example.teleappsistencia.modelos.Token;
import com.example.teleappsistencia.modelos.Usuario;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Utils {

    /**
     * Constante utilizada en la recogida del parámetro objeto de las nuevas instancias de los fragments modificar y consultar.
     */
    public final static String OBJECT = "OBJECT";

    /**
     * Token para poder realizar las peticiones a la API.
     */
    private static Token token;

    /**
     * Usuario logueado en el sistema.
     */
    private static Usuario userLogged;

    /**
     * Si isAdmin está en true se podrá acceder a todas las opciones del menu.
     * Si es false algunas opciones se ocultarán.
     */
    private static boolean isAdmin;

    /**
     * Método que recibe un número y si se encuentra entre 1 y 9 le añade un 0 delante.
     * Este método es utilizado para los meses y días de las fechas.
     * @param number Número al que hay que añadirle el 0 si cumple con los requisitos.
     * @return Devuelve el número como un String y con el 0 delante si cumple con los requisitos.
     */
    public static String twoDigitsDate(int number) {
        return (number<=9) ? ("0"+number) : String.valueOf(number);
    }

    /**
     * Método que recibe un objeto y un tipo y transforma el objeto en el tipo indicado.
     * @param lTM Objeto a transformar.
     * @param tipo Tipo al que se transformará el objeto.
     * @return Devuelve el objeto con el tipo ya cambiado.
     */
    public static Object getObjeto(Object lTM, String tipo) {
        Gson gson = new Gson();
        Type type = null;
        Object objeto = null;
        switch(tipo){
            case "Usuario":
                type = new TypeToken<Usuario>(){}.getType();
                break;
            case "Grupo":
                type = new TypeToken<Grupo>(){}.getType();
                break;
            case "Direccion":
                type = new TypeToken<Direccion>(){}.getType();
                break;
            case "Terminal":
                type = new TypeToken<Terminal>(){}.getType();
                break;
            case "TipoSituacion":
                type = new TypeToken<TipoSituacion>(){}.getType();
                break;
            case "TipoAlarma":
                type = new TypeToken<TipoAlarma>(){}.getType();
                break;
        }
        if(type != null){
            objeto = gson.fromJson(gson.toJson(lTM), type);
        }
        return objeto;
    }

    /* Getters y Setters */

    public static Token getToken() {
        return token;
    }

    public static void setToken(Token token) {
        Utils.token = token;
    }

    public static Usuario getUserLogged() {
        return userLogged;
    }

    public static void setUserLogged(Usuario userLogged) {
        Utils.userLogged = userLogged;
    }

    public static boolean isAdmin() {
        return isAdmin;
    }

    public static void setIsAdmin(boolean isAdmin) {
        Utils.isAdmin = isAdmin;
    }
}
