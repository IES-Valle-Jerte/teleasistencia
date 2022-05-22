package com.example.teleappsistencia.utilidades;

public class Constantes {

    /* Constantes comunes */
    public final static String BEARER_ESPACIO = "Bearer ";

    /* Constantes Clase AlarmaWebSocketListener */
    public final static String CONEXION_ESTABLECIDA_WEBSOCKET = "Conexión establecida con el WebSocket" ;
    public final static String ACTION = "action";
    public final static String ALARMA_MIN = "alarma";
    public final static String NEW_ALARM = "new_alarm";
    public final static String ALARM_ASSIGNMENT= "alarm_assignment";
    public final static String MENSAJE_FALLO_CONEXION_WS = "La conexión con el servidor falló. \n No se recibirán notificaciones.";
    public final static String NOTIFICATION_CHANNEL_ID = "AlarmChannel";
    public final static String NOTIFICATION_CHANNEL_NAME = "Canal Alarmas";
    public final static String NUEVA_ALARMA = "Nueva alarma";
    public final static String ALARMA_CREADA_CON_ID = "Alarma creada con id: ";

    /* Constantes ADAPTERS y Fragments */
    public final static String ID_ALARMA_DP_SP = "ID Alarma: ";
    public final static String ESTADO_DP_SP = "Estado: ";
    public final static String FECHA_DP_SP = "Fecha: ";
    public final static String TIPO_DP_SP = "Tipo: ";
    public final static String ID_TERMINAL = "ID Terminal";
    public final static String ID_PACIENTE = "ID Paciente";
    public final static String ABIERTA = "Abierta";
    public static final String ID_DP_SP = "ID: ";
    public static final String PERSONA_DP_SP = "Persona: ";
    public static final String CENTRO_DP_SP = "Centro: ";
    public static final String RECURSO_COMUNITARIO_DP_SP = "Recurso Comunitario: ";
    public static final String NOMBRE_DP_SP = "Nombre: ";
    public static final String CODIGO_DP_SP = "Código: ";
    public static final String DISPOSITIVO_DP_SP = "Dispositivo: ";
    public static final String CLASIFICACION_DP_SP = "Clasificación: ";

    /* Constantes Simbolos Varios */
    public final static String ESPACIO_GUION_ESPACIO = " - ";
    public final static String ESPACIO_PARENTESIS_AP = " (";
    public final static String PARENTESIS_CIERRE = ")";
    public final static String ESPACIO = " ";


    /* Nombres de los Modelos */
    public final static String PERSONA = "Persona";
    public final static String RECURSO_COMUNITARIO = "RecursoComunitario";
    public final static String CENTRO_SANITARIO = "CentroSanitario";
    public final static String TIPO_ALARMA = "TipoAlarma";
    public final static String TELEOPERADOR = "Teleoperador";
    public final static String PACIENTE = "Paciente";
    public final static String TERMINAL = "Terminal";
    public final static String AL_TIPO_ALARMA = "ArrayList<TipoAlarma>";
    public final static String AL_TERMINAL = "ArrayList<Terminal>";
    public final static String AL_PACIENTE = "ArrayList<Paciente>";
    public final static String AL_ALARMA = "ArrayList<Alarma>";
    public final static String AL_CLASIFICACION_ALARMA= "ArrayList<ClasificacionAlarma>";
    public final static String AL_CENTRO_SANITARIO_ALARMA = "ArrayList<CentroSanitarioEnAlarma>";
    public final static String AL_PERSONAS_CONTACTO_EN_ALARMA = "ArrayList<PersonaContactoEnAlarma>";
    public final static String AL_RECURSOS_COMUNITARIOS_EN_ALARMA = "ArrayList<RecursoComunitarioEnAlarma>";
    public static final String ALARMA = "Alarma";
    public static final String CLASIFICACION_ALARMA = "ClasificacionAlarma";

    /* Constantes Mensajes Peticiones */
    public final static String ALARMA_BORRADA = "Alarma borrada correctamente.";
    public final static String PERSONA_CONTACTO_EN_ALARMA_BORRADA = "Persona Contacto En Alarma borrado correctamente.";
    public final static String RECURSO_EN_ALARMA_BORRADO = "Recurso Comunitario En Alarma borrado correctamente.";
    public final static String TIPO_ALARMA_BORRADO = "Tipo de Alarma borrada correctamente.";
    public final static String CENTRO_EN_ALARMA_BORRADO = "Centro Sanitario en Alarma borrado correctamente.";
    public final static String CLASIFICACION_ALARMA_BORRADA = "Clasificacion de Alarma borrada correctamente.";
    public final static String ERROR_BORRADO = "Error al intentar borrar los datos.";
    public final static String ERROR_ = "Error: ";
    public final static String ALARMA_GUARDADA = "Alarma guardada con éxito";
    public final static String ALARMA_MODIFICADA = "Alarma modificada con éxito";
    public final static String ERROR_MODIFICACION = "Error en la modificación: ";
    public final static String PISTA_TELEOPERADOR_ID = " (Pista: ¿existe Teleoperador con ese ID?)";
    public final static String MODIFICADO_CON_EXITO = "Modificado con éxito";
    public final static String PISTA_ALARMA_CENTRO_ID = " (Pista: ¿existe Alarma y/o Centro con ese ID?)";
    public final static String PISTA_ALARMA_PERSONA_ID = " (Pista: ¿existe Alarma y/o Persona con ese ID?)";
    public final static String PISTA_ALARMA_RECURSOCOMUNITARIO_ID = " (Pista: ¿existe Alarma y/o Recurso Comunitario con ese ID?)";
    public final static String ERROR_CARGAR_DATOS = "Error al cargar los datos";
    public static final String GUARDADO_CON_EXITO = "Guardado con éxito";
    public static final String ERROR_CREACION = "Error en la creación: ";


    /* Constantes Comprobaciones */
    public final static String OBLIGATORIO_INDICAR_TELEOPERADOR = "Es obligatorio indicar un ID de Teleoperador";
    public final static String DEBES_INTRODUCIR_FECHA = "Debes introducir una fecha";
    public final static String DEBES_INTRODUCIR_ID_ALARMA = "Debes introducir un ID de Alarma";
    public final static String DEBES_INTRODUCIR_ID_CENTRO = "Debes introducir un ID de Centro Sanitario";
    public static final String DEBES_INTRODUCIR_ID_PERSONA = "Debes introducir un ID de Persona";
    public static final String DEBES_INTRODUCIR_NOMBRE = "Debes introducir un nombre";
    public static final String DEBES_INTRODUCIR_CODIGO_TIPO_ALARMA = "Debes introducir el código del Tipo de Alarma.";
    public static final String DEBES_INTRODUCIR_ID_RECURSO_COMUNITARIO = "Debes introducir un ID de Recurso Comunitario";
    public static final String DEBES_INTRODUCIR_CODIGO_2_3_LETRAS = "Debes introducir un Código de 2 o 3 letras.";



}
