package com.example.teleappsistencia.ui.fragments.paciente;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teleappsistencia.servicios.APIService;
import com.example.teleappsistencia.servicios.ClienteRetrofit;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.utilidades.Utilidad;
import com.example.teleappsistencia.modelos.Paciente;
import com.example.teleappsistencia.modelos.Persona;
import com.example.teleappsistencia.modelos.Terminal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Una clase {@link Fragment} para recoger los datos para insertar un Paciente nuevo.
 * <p> Esta clase es una subclase de {@link Fragment} y hereda de ella todos sus métodos y atributos.
 */
public class InsertarPacienteFragment extends Fragment implements View.OnClickListener {

    /**
     * El paciente que se va a consultar.
     */
    private Paciente paciente;

    // Atributos de la interfaz de usuario (UI) del fragment.
    private Spinner spinnerTerminal;
    private Spinner spinnerPersona;
    private Spinner spinnerTipoModalidadPaciente;
    private EditText editTextTieneUCR;
    private EditText editTextNumeroExpediente;
    private EditText editTextNumeroSeguridadSocial;
    private EditText editTextPrestacionOtrosServicios;
    private EditText editTextObservacionesMedicas;
    private EditText editTextInteresesActividades;
    private Button btnInsertarPaciente;
    private Button btnVolverPacienteInsertar;

    // Constructor por defecto.
    public InsertarPacienteFragment() {}

    /**
        * Método que crea una instancia de la clase.
     */
    public static InsertarPacienteFragment newInstance() {
        InsertarPacienteFragment fragment = new InsertarPacienteFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    
    /**
     * Esta función se llama cuando la actividad se crea por primera vez.
     * 
     * @param savedInstanceState Si la actividad se reinicializa después de cerrarse previamente, este
     * paquete contiene los datos que suministró más recientemente en onSaveInstanceState(Bundle).
     * Nota: De lo contrario es nulo.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * La función onCreateView() se llama cuando se crea el fragmento
     * 
     * @param inflater El objeto LayoutInflater que se puede usar para inflar cualquier vista en el
     * fragmento,
     * @param container La vista principal a la que se debe adjuntar la interfaz de usuario del
     * fragmento.
     * @param savedInstanceState Un objeto Bundle que contiene el estado guardado previamente de la
     * actividad. Si la actividad nunca ha existido antes, el valor del objeto Bundle es nulo.
     * @return La vista del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_insertar_paciente, container, false);
        // Inicialización de los atributos de la interfaz de usuario (UI).
        obtenerComponentes(root);
        // Inicialización de los Spinners del formulario
        inicializarSpinnerTerminal();
        inicializarSpinnerPersona();
        return root;
    }

    /**
    * Método que obtiene los componentes de la interfaz de usuario (UI) del fragment.
    *
    * @param root La vista del fragmento.
     */
    private void obtenerComponentes(View root) {
        spinnerTerminal = root.findViewById(R.id.spinnerTerminalInsertar);
        spinnerPersona = root.findViewById(R.id.spinnerPersonaModificar);
        spinnerTipoModalidadPaciente = root.findViewById(R.id.spinnerTipoModalidadPacienteInsertar);
        editTextTieneUCR = root.findViewById(R.id.editTextTieneUCRInsertar);
        editTextNumeroExpediente = root.findViewById(R.id.editTextNumeroExpedienteInsertar);
        editTextNumeroSeguridadSocial = root.findViewById(R.id.editTextNumeroSeguridadSocialInsertar);
        editTextPrestacionOtrosServicios = root.findViewById(R.id.editTextPrestacionOtrosServiciosInsertar);
        editTextObservacionesMedicas = root.findViewById(R.id.editTextObservacionesMedicasInsertar);
        editTextInteresesActividades = root.findViewById(R.id.editTextInteresesActividadesInsertar);
        btnInsertarPaciente = root.findViewById(R.id.btnInsertarPaciente);
        btnVolverPacienteInsertar = root.findViewById(R.id.btnVolverPacienteInsertar);
    }


    /**
     * Una función que se llama cuando se presiona un botón.
     * 
     * @param view La vista en la que se hizo clic.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInsertarPaciente:
                accionBotonGuardar();
                break;
            case R.id.btnVolverPacienteInsertar:
                accionBotonVolver();
                break;
        }
    }

    /**
     * Método que se ejecuta cuando se presiona el botón de Guardar.
     */
    private void accionBotonGuardar() {

    }

    /**
     * Método que se ejecuta cuando se presiona el botón de Volver.
     */
    private void accionBotonVolver() {

    }

    /**
     * Método que inicializa el Spinner de Terminal.
     */
    private void inicializarSpinnerTerminal() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<Terminal>> call = apiService.getListadoTerminal("Bearer " + Utilidad.getToken().getAccess());
        call.enqueue(new retrofit2.Callback<List<Terminal>>() {
            @Override
            public void onResponse(Call<List<Terminal>> call, Response<List<Terminal>> response) {
                if (response.isSuccessful()) {
                    List<Terminal> listadoTerminales = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, convertirListaTerminales(listadoTerminales));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerTerminal.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Terminal>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
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

    /**
     * Método que inicializa el Spinner de Persona.
     */
    private void inicializarSpinnerPersona() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<Persona>> call = apiService.getListadoPersona("Bearer " + Utilidad.getToken().getAccess());
        call.enqueue(new retrofit2.Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                if (response.isSuccessful()) {
                    List<Persona> listadoPersona = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, convertirListaPersonas(listadoPersona));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerPersona.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), "Error al obtener listado de personas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {

            }
        });
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
}