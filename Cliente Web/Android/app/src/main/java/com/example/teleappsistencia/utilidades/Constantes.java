package com.example.teleappsistencia.utilidades;

public class Constantes {

    /* Direcciones */
    public static final String DIRECCION_WEBSOCKET = "ws://10.0.2.2:8000/ws/socket-server/";
    public static final String BASE_URL = "http://10.0.2.2:8000/";
    //public static final String DIRECCION_WEBSOCKET = "ws://localhost:8000/ws/socket-server/";
    //public static final String BASE_URL = "http://localhost:8000/";

    /* Constantes comunes */
    public final static String BEARER_ESPACIO = "Bearer ";
    public static final String SI = "Sí";
    public static final String NO = "No";
    public static final String CERO = "0";
    public static final String OK = "OK";
    public static final String FORMATO_FECHAS_RETROFIT = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String SIN_ASIGNAR = "Sin Asignar";
    public static final String MIS_ALARMAS = "Mis Alarmas";

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


    /* Constantes de argumentos para pasar datos a los Fragments */
    public static final String ARG_ALARMA = "Alarma";
    public static final String ARG_TIPOALARMA = "TipoAlarma";
    public static final String ARG_PERSONACONTACTOEA = "PCEA";
    public final static String ARG_CLASIFICACIONALARMA = "ClasificacionAlarma";
    public static final String ARG_CENTROSANITARIOEA = "CSEA";
    public static final String ARG_RCEA = "RCEA";
    public static final String ARG_NOMBREPACIENTE = "NombrePaciente";
    public static final String ARG_NUMEROTELEFONO = "NumeroTelefono";
    public static final String ARG_LCONTACTOS = "ListaContactos";
    public static final String ARG_PACIENTE = "Paciente";
    public static final String ARG_TERMINAL = "Terminal";
    public static final String ARG_COLOR = "Color";

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
    public static final String ID_TERMINAL_DP_SP = "ID Terminal: ";
    public static final String PACIENTE_DP_SP = "Paciente: ";
    public static final String TELEFONO_DP_SP = "Teléfono: ";

    /* Constantes Simbolos Varios */
    public final static String ESPACIO_GUION_ESPACIO = " - ";
    public final static String ESPACIO_PARENTESIS_AP = " (";
    public final static String PARENTESIS_CIERRE = ")";
    public final static String ESPACIO = " ";
    public final static String SLASH = "/";
    public static final String GUION = "-";
    public static final String VACIO = "";
    public static final String SALTO_LINEA = "\n";

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
    public static final String CONTACTO = "Contacto";
    public static final String AL_CONTACTOS = "ArrayList<Contacto>";
    public static final String AL_RECURSOS_COMUNITARIOS = "ArrayList<RecursoComunitario>";
    public static final String RELACION_USUARIO_CENTRO = "ArrayList<RelacionUsuarioCentro>";
    public static final String AL_RELACION_TERMINAL_RECURSO_COMUNITARIO = "ArrayList<RelacionTerminalRecursoComunitario>";
    public static final String AL_RELACION_USUARIO_CENTRO = "ArrayList<RelacionUsuarioCentro>";
    public static final String DIRECCION = "Direccion";

    /* Constantes Mensajes Peticiones */
    public final static String ALARMA_BORRADA = "Alarma borrada correctamente.";
    public final static String PERSONA_CONTACTO_EN_ALARMA_BORRADA = "Persona Contacto En Alarma borrado correctamente.";
    public final static String RECURSO_EN_ALARMA_BORRADO = "Recurso Comunitario En Alarma borrado correctamente.";
    public final static String TIPO_ALARMA_BORRADO = "Tipo de Alarma borrada correctamente.";
    public final static String CENTRO_EN_ALARMA_BORRADO = "Centro Sanitario en Alarma borrado correctamente.";
    public final static String CLASIFICACION_ALARMA_BORRADA = "Clasificacion de Alarma borrada correctamente.";
    public final static String ERROR_BORRADO = "Error al intentar borrar los datos: ";
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
    public static final String ERROR_ALARMA_YA_ASIGNADA = "Esta alarma ya fue asignada a otro teleoperador";
    public static final String ERROR_NO_CONTACTOS = "Error. No se han podido obtener los contactos";

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

    /* Constantes Gestión de Alarmas */
    public final static String LLAMADA_REGISTRADA_A = " (Llamada registrada a ";
    public final static String A_LAS = " a las ";
    public final static String EDITAR = "Editar";
    public final static String LLAMADA_REGISTRADA_EXITO = "Llamada registrada con éxito";
    public final static String ERROR_REGISTRAR_LLAMADA = "Error al registrar la llamada.";
    public final static String TEXTO_MINIMO_10 = "Texto mínimo de 10 caracteres.";
    public final static String ACUERDO_CORTO_TEXTO_MINIMO_10 = "Acuerdo alcanzado muy corto. Texto mínimo de 10 caracteres.";
    public static final String INTRODUCIR_NOMBRE_PERSONA_LLAMADA = "Introducir el nombre de la persona que atendió la llamada.";
    public static final String INFORMACION_DEL_PACIENTE = "Información del Paciente";
    public static final String NUMERO_EXPEDIENTE_DP_SP = "Número de expediente: ";
    public static final String NUMERO_DE_LA_SS_DP_SP = "Número de la SS: ";
    public static final String OBSERVACIONES_MEDDICAS_DP_SP = "Observaciones médicas: ";
    public static final String INFORMACION_CONTACTO = "Información Contacto";
    public static final String TELEFONO_MOVIL_DP_SP = "Teléfono móvil: ";
    public static final String TELEFONO_FIJO_DP_SP = "Teléfono fijo: ";
    public static final String RELACION_CON_PACIENTE_DP_SP = "Relación con paciente: ";
    public static final String DISPONIBILIDAD_DP_SP = "Disponibilidad: ";
    public static final String OBSERVACIONES_DP_SP = "Observaciones: ";
    public static final String INTERR_TIENE_LLAVES_DP_SP = "¿Tiene llaves?: ";
    public static final String INFORMACION_CENTRO_SANITARIO = "Información Centro Sanitario";
    public static final String LOCALIDAD_DP_SP = "Localidad: ";
    public static final String DIRECCION_DP_SP = "Direccion: ";
    public static final String INFORMACION_RECURSO_COMUNITARIO = "Información Recurso Comunitario";
    public static final String ATENCION = "ATENCIÓN";
    public static final String EN_CONSTRUCCION = "En construcción. Disculpe las molestias.";
    public static final String CERRADA = "Cerrada";
    public static final String ALARMA_YA_CERRADA = "La Alarma ya fue gestionada y Cerrada";
    public static final String RESUMEN_MINIMO_10 = "Debe escribir un resumen (mínimo 10 caracteres).";
    public static final String NO_REGISTRO_LLAMADA_PACIENTE = "No ha registrado llamada al paciente.";
    public static final String ALARMA_GESTIONADA_CORRECTAMENTE = "Alarma gestionada correctamente.";
    public static final String ERROR_CERRAR_ALARMA = "No se ha podido cerrar la alarma correctamente. ";
    public static final String NO_HAY_CONTACTOS = "No hay personas de contacto para este paciente";
    public static final String TERMINAL_DP_SP = "Terminal: ";
}
