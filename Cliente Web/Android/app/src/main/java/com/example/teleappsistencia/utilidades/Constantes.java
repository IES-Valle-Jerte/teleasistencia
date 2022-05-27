package com.example.teleappsistencia.utilidades;

/**
 * Clase que almacena todas las constantes utilizadas en las clases java.
 */
public class Constantes {

    /**
     * Constantes de la API.
     */
    public static final String API_BASE_URL = "http://10.0.2.2:8000/";
    // Se utiliza "http://localhost:8000/" con dispositivos físicos y con el emulador se ha de utilizar "http://10.0.2.2:8000/"
    public static final String TOKEN_BEARER = "Bearer ";
    public static final String FORMATEADOR_API = "yyyy-MM-dd'T'HH:mm:ssZ";


    /**
     * Constantes de autorización.
     */
    public static final String UNAUTHORIZED = "Unauthorized";
    public static final String PROFESOR = "Profesor";

    /**
     * Constantes de los parámetros de los fragments consultar y modificar.
     */
    public static final String DIRECCION = "Direccion";
    public static final String DISPOSITIVO_AUXILIAR = "Dispositivo auxiliar";
    public static final String GRUPO = "Grupo";
    public static final String HISTORICO_TIPO_SITUACION = "Historico tipo situacion";
    public static final String PERSONA = "Persona";
    public static final String TIPO_SITUACION = "Tipo situacion";
    public static final String TIPO_VIVIENDA = "Tipo vivienda";
    public static final String USUARIO = "Usuario";
    public static final String TERMINAL = "Terminal";
    public static final String TIPO_ALARMA = "Tipo alarma";

    /**
     * Constantes con los nombres de los submenus.
     */
    public static final String SUBMENU_INSERTAR = "Insertar";
    public static final String SUBMENU_LISTAR = "Listar";

    /**
     * Constantes de uso general.
     */
    public static final String ID_CON_DOS_PUNTOS = "ID: ";
    public static final String STRING_VACIO = "";
    public static final String ESPACIO_EN_BLANCO = " ";

    /**
     * Constantes para los infoAlertDialogs
     */
    public static final String ELIMINAR_ELEMENTO = "Eliminar datos";
    public static final String ESTAS_SEGURO_ELIMINAR = "¿Deseas realmente eliminar los datos?";
    public static final String SI = "SI";
    public static final String NO = "NO";

    public static final String INFORMACION = "Información";
    public static final String INFO_ALERTDIALOG_CREDENCIALES_INCORRECTOS_LOGIN = "Nombre de usuario o contraseña incorrectos.";
    public static final String INFO_ALERTDIALOG_USUARIO_YA_EXISTENTE = "Ya existe un usuario con el nombre de usuario indicado.";

    public static final String INFO_ALERTDIALOG_CREADO_USUARIO = "Se ha creado correctamente un nuevo usuario.";
    public static final String INFO_ALERTDIALOG_CREADO_TIPO_VIVIENDA = "Se ha creado correctamente un nuevo tipo de vivienda.";
    public static final String INFO_ALERTDIALOG_CREADO_TIPO_SITUACION = "Se ha creado correctamente un nuevo tipo de situación.";
    public static final String INFO_ALERTDIALOG_CREADO_PERSONA = "Se ha creado correctamente una nueva persona.";
    public static final String INFO_ALERTDIALOG_CREADO_HISTORICO_TIPO_SITUACION = "Se ha creado correctamente un nuevo hístorico tipo de situación.";
    public static final String INFO_ALERTDIALOG_CREADO_GRUPO = "Se ha creado correctamente un nuevo grupo.";
    public static final String INFO_ALERTDIALOG_CREADO_DISPOSITIVO_AUXILIAR = "Se ha creado correctamente un nuevo dispositivo auxiliar en terminal.";
    public static final String INFO_ALERTDIALOG_CREADO_DIRECCION = "Se ha creado correctamente una nueva dirección.";

    public static final String INFO_ALERTDIALOG_MODIFICADO_USUARIO = "Se ha modificado correctamente el usuario.";
    public static final String INFO_ALERTDIALOG_MODIFICADO_TIPO_VIVIENDA = "Se ha modificado correctamente el tipo de vivienda.";
    public static final String INFO_ALERTDIALOG_MODIFICADO_TIPO_SITUACION = "Se ha modificado correctamente el tipo de situación.";
    public static final String INFO_ALERTDIALOG_MODIFICADO_PERSONA = "Se ha modificado correctamente la persona.";
    public static final String INFO_ALERTDIALOG_MODIFICADO_HISTORICO_TIPO_SITUACION = "Se ha modificado correctamente el hístorico tipo de situación.";
    public static final String INFO_ALERTDIALOG_MODIFICADO_GRUPO = "Se ha modificado correctamente el grupo.";
    public static final String INFO_ALERTDIALOG_MODIFICADO_DISPOSITIVO_AUXILIAR = "Se ha modificado correctamente el dispositivo auxiliar en terminal.";
    public static final String INFO_ALERTDIALOG_MODIFICADO_DIRECCION = "Se ha modificado correctamente la dirección.";

    public static final String INFO_ALERTDIALOG_ELIMINADO_USUARIO = "Se ha eliminado correctamente el usuario.";
    public static final String INFO_ALERTDIALOG_ELIMINADO_TIPO_VIVIENDA = "Se ha eliminado correctamente el tipo de vivienda.";
    public static final String INFO_ALERTDIALOG_ELIMINADO_TIPO_SITUACION = "Se ha eliminado correctamente el tipo de situación.";
    public static final String INFO_ALERTDIALOG_ELIMINADO_PERSONA = "Se ha eliminado correctamente la persona.";
    public static final String INFO_ALERTDIALOG_ELIMINADO_HISTORICO_TIPO_SITUACION = "Se ha eliminado correctamente el hístorico tipo de situación.";
    public static final String INFO_ALERTDIALOG_ELIMINADO_GRUPO = "Se ha eliminado correctamente el grupo.";
    public static final String INFO_ALERTDIALOG_ELIMINADO_DISPOSITIVO_AUXILIAR = "Se ha eliminado correctamente el dispositivo auxiliar en terminal.";
    public static final String INFO_ALERTDIALOG_ELIMINADO_DIRECCION = "Se ha eliminado correctamente la dirección.";

    /**
     * Constantes para los errorAlertDialogs
     */
    public static final String ERROR = "Error";
    public static final String ERROR_AL_CONECTARSE_AL_SERVIDOR = "Ha ocurrido un error al conectarse al servidor, intentelo más tarde.";
    public static final String ERROR_ALERTDIALOG = "Ha ocurrido un error. Código de error: ";

    /**
     * Constantes de los DatePicker
     */
    public static final String TAG_DATEPICKER = "datePicker";

    /**
     * Constantes para el spinner del modelo Persona
     */
    public static final String SEXO_MASCULINO = "Hombre";
    public static final String SEXO_FEMENINO = "Mujer";

    /**
     * Constantes de comprobaciones.
     */
    public static final String PATRON_DNI = "[0-9]{8}[A-Z]";
    public static final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";
    public static final String PATRON_TELEFONO = "[+-]?\\d*(\\.\\d+)?";
}
