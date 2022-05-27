package com.example.teleappsistencia.utilidades;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.teleappsistencia.modelos.Alarma;
import com.example.teleappsistencia.modelos.CentroSanitario;
import com.example.teleappsistencia.modelos.CentroSanitarioEnAlarma;
import com.example.teleappsistencia.modelos.ClasificacionAlarma;
import com.example.teleappsistencia.modelos.Contacto;
import com.example.teleappsistencia.modelos.Direccion;
import com.example.teleappsistencia.modelos.Paciente;
import com.example.teleappsistencia.modelos.Persona;
import com.example.teleappsistencia.modelos.PersonaContactoEnAlarma;
import com.example.teleappsistencia.modelos.RecursoComunitario;
import com.example.teleappsistencia.modelos.RecursoComunitarioEnAlarma;
import com.example.teleappsistencia.modelos.RelacionTerminalRecursoComunitario;
import com.example.teleappsistencia.modelos.RelacionUsuarioCentro;
import com.example.teleappsistencia.modelos.Teleoperador;
import com.example.teleappsistencia.modelos.Terminal;
import com.example.teleappsistencia.modelos.TipoAlarma;
import com.example.teleappsistencia.websocket.AlarmaWebSocketListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;

public class Utilidad {

    /**
     * Método usado para establecer la conexión con el WebSocket del servidor.
     * @param activity le llega la activity porque el WSListener lo va a necesitar
     */
    public static void iniciarEscuchaAlarmas(Activity activity) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(Constantes.DIRECCION_WEBSOCKET).build();
        AlarmaWebSocketListener aWSListener = new AlarmaWebSocketListener(activity);
        WebSocket ws = client.newWebSocket(request, aWSListener);
        client.dispatcher().executorService().shutdown();
    }

    /**
     * Este método convierte un Objeto LinkedTreeMap en un Objeto de otra clase, dependiendo de nuestras
     * necesidades. Se usa cuando
     * @param lTM
     * @param tipo
     * @return
     */
    public static Object getObjeto(Object lTM, String tipo) {
        Gson gson = new Gson();
        Type type = null;
        Object objeto = null;
        switch(tipo){
            case Constantes.TELEOPERADOR:
                type = new TypeToken<Teleoperador>(){}.getType();
                break;
            case Constantes.TERMINAL:
                type = new TypeToken<Terminal>(){}.getType();
                break;
            case Constantes.PACIENTE:
                type = new TypeToken<Paciente>(){}.getType();
                break;
            case Constantes.PERSONA:
                type = new TypeToken<Persona>(){}.getType();
                break;
            case Constantes.TIPO_ALARMA:
                type = new TypeToken<TipoAlarma>(){}.getType();
                break;
            case Constantes.CLASIFICACION_ALARMA:
                type = new TypeToken<ClasificacionAlarma>(){}.getType();
                break;
            case Constantes.CONTACTO:
                type = new TypeToken<Contacto>(){}.getType();
                break;
            case Constantes.AL_CONTACTOS:
                type = new TypeToken<ArrayList<Contacto>>(){}.getType();
                break;
            case Constantes.AL_RECURSOS_COMUNITARIOS:
                type = new TypeToken<ArrayList<RecursoComunitario>>(){}.getType();
                break;
            case Constantes.RELACION_USUARIO_CENTRO:
                type = new TypeToken<ArrayList<RelacionUsuarioCentro>>(){}.getType();
                break;
            case Constantes.AL_RELACION_TERMINAL_RECURSO_COMUNITARIO:
                type = new TypeToken<ArrayList<RelacionTerminalRecursoComunitario>>(){}.getType();
                break;
            case Constantes.CENTRO_SANITARIO:
                type = new TypeToken<CentroSanitario>(){}.getType();
                break;
            case Constantes.RECURSO_COMUNITARIO:
                type = new TypeToken<RecursoComunitario>(){}.getType();
                break;
            case Constantes.DIRECCION:
                type = new TypeToken<Direccion>(){}.getType();
                break;
            case Constantes.AL_ALARMA:
                type = new TypeToken<ArrayList<Alarma>>(){}.getType();
                break;
            case Constantes.AL_CENTRO_SANITARIO_ALARMA:
                type = new TypeToken<ArrayList<CentroSanitarioEnAlarma>>(){}.getType();
                break;
            case Constantes.AL_TIPO_ALARMA:
                type = new TypeToken<ArrayList<TipoAlarma>>(){}.getType();
                break;
            case Constantes.AL_TERMINAL:
                type = new TypeToken<ArrayList<Terminal>>(){}.getType();
                break;
            case Constantes.AL_PACIENTE:
                type = new TypeToken<ArrayList<Paciente>>(){}.getType();
                break;
            case Constantes.ALARMA:
                type = new TypeToken<Alarma>(){}.getType();
                break;
            case Constantes.AL_CLASIFICACION_ALARMA:
                type = new TypeToken<ArrayList<ClasificacionAlarma>>(){}.getType();
                break;
            case Constantes.AL_PERSONAS_CONTACTO_EN_ALARMA:
                type = new TypeToken<ArrayList<PersonaContactoEnAlarma>>(){}.getType();
                break;
            case Constantes.AL_RECURSOS_COMUNITARIOS_EN_ALARMA:
                type = new TypeToken<ArrayList<RecursoComunitarioEnAlarma>>(){}.getType();
                break;
        }
        if(type != null){
            objeto = gson.fromJson(gson.toJson(lTM), type);
        }
        return objeto;
    }

    /**
     * Este método devuelve un String con Sí cuando le pasamos un true, y No si es false
     * @param condicion
     * @return
     */
    public static String trueSifalseNo(boolean condicion){
        if(condicion){
            return Constantes.SI;
        }
        return Constantes.NO;
    }

    /**
     * Este método de vuelve la fecha del sistema con el formato año-mes-día
     * @return
     */
    public static String getStringFechaNowYYYYMMDD(){
        Date fecha;
        SimpleDateFormat sdf= new SimpleDateFormat("YYYY-MM-dd");
        fecha = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        return sdf.format(fecha);
    }

    /* Este método crea un Dialogo de selección de fecha. Le asigna el valor a una variable y además
       escribe la fecha en el EditText. */
    public static void dialogoFecha(Context context, EditText editText){
        DatePickerDialog dpd = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String mes = String.valueOf(month+1);;
                String dia = String.valueOf(day);
                if(month < 9){
                    mes = Constantes.CERO+String.valueOf(month+1);
                }
                if(day < 10){
                    dia = Constantes.CERO+String.valueOf(day);
                }

                editText.setText(year+Constantes.GUION + mes + Constantes.GUION + dia);
            }
        }, LocalDate.now().getYear(), LocalDate.now().getMonthValue()-1, LocalDate.now().getDayOfMonth());
        dpd.show();
    }


}
