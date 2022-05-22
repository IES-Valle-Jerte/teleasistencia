package com.example.teleappsistencia.utilidades;

import android.os.Handler;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.teleappsistencia.R;
import com.example.teleappsistencia.modelos.CentroSanitario;
import com.example.teleappsistencia.modelos.Direccion;
import com.example.teleappsistencia.modelos.Paciente;
import com.example.teleappsistencia.modelos.Persona;
import com.example.teleappsistencia.modelos.RecursoComunitario;
import com.example.teleappsistencia.modelos.RelacionPacientePersona;
import com.example.teleappsistencia.modelos.RelacionTerminalRecursoComunitario;
import com.example.teleappsistencia.modelos.RelacionUsuarioCentro;
import com.example.teleappsistencia.modelos.Terminal;
import com.example.teleappsistencia.modelos.TipoModalidadPaciente;
import com.example.teleappsistencia.modelos.Token;
import com.example.teleappsistencia.modelos.Usuario;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Utilidad {

    /**
     * Token para poder realizar las peticiones a la API.
     */
    private static Token token;

    public static Token getToken() {
        return token;
    }

    public static void setToken(Token token) {
        Utilidad.token = token;
    }

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
            case "RelacionUsuarioCentro":
                type = new TypeToken<RelacionUsuarioCentro>() {
                }.getType();
                break;
        }
        if (type != null) {
            objeto = gson.fromJson(gson.toJson(linkedTreeMap), type);
        }
        return objeto;
    }

    /**
     * Método para mostrar una capa de espera mientras se obtienen los datos de la API.
     *
     * @param view Vista
     */
    public static void generarCapaEspera(View view, ConstraintLayout dataConstraintLayout) {
        // Creamos una capa de espera
        ShimmerFrameLayout shimmerFrameLayout =
                (ShimmerFrameLayout) view.findViewById(R.id.listviewPlaceholder);
        // Obtenemos el layout con nuestros datos
        //ConstraintLayout dataConstraintLayout = (ConstraintLayout) view.findViewById(R.id.listViewDataPacientes);

        // Hacemos que la capa de datos se oculte para mostrarla cuando ya se hayan obtenido los datos.
        dataConstraintLayout.setVisibility(View.INVISIBLE);
        // Inicializamos la animación de la capa de espera
        shimmerFrameLayout.startShimmer();

        // Creando un nuevo objeto Handler para manejar la carga de datos.
        Handler handler = new Handler();

        // Un controlador que retrasa la ejecución del código dentro del método de ejecución durante
        // 2500 milisegundos.
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                // Hacemos que la capa de datos se muestre
                dataConstraintLayout.setVisibility(View.VISIBLE);
                // Detenemos la animación de espera
                shimmerFrameLayout.stopShimmer();
                // Eliminamos la capa de espera
                shimmerFrameLayout.setVisibility(View.GONE);
            }
        }, 2500);
    }

    /**
     * Toma una lista de objetos Persona y devuelve una lista de cadenas que contienen el nombre y
     * apellido de cada persona para mostrar en el Spinner.
     *
     * @param listadoPersona Lista de objetos de tipo Persona
     * @return Una lista de String.
     */
    private List<String> convertirListaPersonas(List<Persona> listadoPersona) {
        List<String> listadoPersonaString = new ArrayList<>();
        for (Persona persona : listadoPersona) {
            listadoPersonaString.add(persona.getNombre() + " " + persona.getApellidos());
        }
        return listadoPersonaString;
    }

    /**
     * Toma una lista de objetos Terminal y devuelve una lista de String para mostrar en el Spinner.
     *
     * @param listadoTerminales Lista de objetos Terminal
     * @return Una lista de String.
     */
    private List<String> convertirListaTerminales(List<Terminal> listadoTerminales) {
        List<String> listadoTerminalesString = new ArrayList<>();
        for (Terminal terminal : listadoTerminales) {
            listadoTerminalesString.add("Nº de terminal: " + terminal.getNumeroTerminal());
        }
        return listadoTerminalesString;
    }
}
