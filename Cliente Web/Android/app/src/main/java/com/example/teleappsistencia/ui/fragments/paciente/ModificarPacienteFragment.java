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
 * Una clase {@link Fragment} para recoger los datos a modificar de un Paciente particular.
 * <p> Esta clase es una subclase de {@link Fragment} y hereda de ella todos sus métodos y atributos.
 */
public class ModificarPacienteFragment extends Fragment implements View.OnClickListener {

    /**
     * El paciente que se quiere modificar.
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
    private Button btnModificarPaciente;
    private Button btnVolverPacienteModificar;

    // Constructor por defecto.
    public ModificarPacienteFragment() {
    }

    
    /**
     * Esta función crea una nueva instancia del fragmento y pasa el objeto al fragmento.
     * 
     * @param paciente es el objeto que quiero pasar al fragmento
     * @return Una nueva instancia del fragmento.
     */
    public static ModificarPacienteFragment newInstance(Paciente paciente) {
        ModificarPacienteFragment fragment = new ModificarPacienteFragment();
        Bundle args = new Bundle();
        args.putSerializable("objetoPaciente", paciente);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * La función se llama cuando se crea el fragmento.
     * 
     * @param savedInstanceState El estado guardado del fragmento, o nulo si se trata de un fragmento
     * recién creado.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paciente = (Paciente) getArguments().getSerializable("objetoPaciente");
        }
    }

    /**
     * La función onCreateView() se llama cuando se crea el fragmento
     * 
     * @param inflater El objeto LayoutInflater que se puede usar para inflar cualquier vista en el
     * fragmento,
     * @param container La vista principal a la que se debe adjuntar la interfaz de usuario del
     * fragmento.
     * @param savedInstanceState Si el fragmento se vuelve a crear a partir de un estado guardado
     * anterior, este es el estado.
     * @return La vista del fragmento.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_modificar_paciente, container, false);
        // Inicializamos los atributos de la interfaz de usuario (UI).
        obtenerComponentes(root);
        // Rellenamos los campos con los datos del paciente que se quiere modificar.
        rellenarCampos();
        // Añadimos los eventos de los botones.
        this.btnVolverPacienteModificar.setOnClickListener(this);
        this.btnModificarPaciente.setOnClickListener(this);
        return root;
    }

    private void rellenarCampos() {
        // Inicializamos los spinners.
        inicializarSpinnerTerminal();
        inicializarSpinnerPersona();
        // Fijamos si el paciente tiene UCR evitando que se muestren booleanos y sea algo más legible.
        fijarSiTieneUCR();
        // Rellenamos los campos con los datos del paciente que se quiere modificar.
        editTextNumeroExpediente.setText(paciente.getNumeroExpediente());
        editTextNumeroSeguridadSocial.setText(paciente.getNumeroSeguridadSocial());
        editTextPrestacionOtrosServicios.setText(paciente.getPrestacionOtrosServiciosSociales());
        editTextObservacionesMedicas.setText(paciente.getObservacionesMedicas());
        editTextInteresesActividades.setText(paciente.getInteresesYActividades());
    }


    /**
     * Si el paciente tiene una UCR, el campo de texto mostrará "Si", de lo contrario, mostrará "No".
     */
    private void fijarSiTieneUCR() {
        if (paciente.isTieneUcr()) {
            this.editTextTieneUCR.setText("Si");
        } else {
            this.editTextTieneUCR.setText("No");
        }
    }

    /**
     * Método que inicializa el Spinner de Personas en la interfaz de usuario.
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
                    Persona persona = (Persona) Utilidad.getObjeto(paciente.getPersona(), "Persona");
                    spinnerPersona.setAdapter(adapter);
                    spinnerPersona.setSelection(buscarPosicionSpinnerPersona(listadoPersona,persona.getId()));
                } else {
                    Toast.makeText(getContext(), "Error al obtener listado de personas", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Persona>> call, Throwable t) {

            }
        });
    }

    private List<String> convertirListaPersonas(List<Persona> listadoPersona) {
        List<String> listadoPersonaString = new ArrayList<>();
        for (Persona persona : listadoPersona) {
            listadoPersonaString.add(persona.getNombre() + " " + persona.getApellidos());
        }
        return listadoPersonaString;
    }

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
                    Terminal terminal = (Terminal) Utilidad.getObjeto(paciente.getTerminal(), "Terminal");
                    spinnerTerminal.setAdapter(adapter);
                    spinnerTerminal.setSelection(buscarPosicionSpinnerTerminal(listadoTerminales,terminal.getId()), true);
                }
            }

            @Override
            public void onFailure(Call<List<Terminal>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private List<String> convertirListaTerminales(List<Terminal> listadoTerminales) {
        List<String> listadoTerminalesString = new ArrayList<>();
        for (Terminal terminal : listadoTerminales) {
            listadoTerminalesString.add("Nº de terminal: " + terminal.getNumeroTerminal());
        }
        return listadoTerminalesString;
    }


    private int buscarPosicionSpinnerTerminal(List<Terminal> listadoTerminales, int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado) {
            if (listadoTerminales.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return i-1;
    }

    private int buscarPosicionSpinnerPersona(List<Persona> listadoPersonas,int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado) {
            if (listadoPersonas.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return i-1;
    }

   /**
    * Método que obtiene los componentes de la interfaz de usuario (UI) del fragment.
    *
    * @param root La vista del fragmento.
     */
    private void obtenerComponentes(View root) {
        this.btnVolverPacienteModificar = (Button) root.findViewById(R.id.btnVolverPacienteModificar);
        this.btnModificarPaciente = (Button) root.findViewById(R.id.btnModificarPaciente);
        this.spinnerTerminal = (Spinner) root.findViewById(R.id.spinnerTerminal);
        this.spinnerPersona = (Spinner) root.findViewById(R.id.spinnerPersona);
        this.spinnerTipoModalidadPaciente = (Spinner) root.findViewById(R.id.spinnerTipoModalidadPaciente);
        this.editTextTieneUCR = (EditText) root.findViewById(R.id.editTextTieneUCR);
        this.editTextNumeroExpediente = (EditText) root.findViewById(R.id.editTextNumeroExpediente);
        this.editTextNumeroSeguridadSocial = (EditText) root.findViewById(R.id.editTextNumeroSeguridadSocial);
        this.editTextPrestacionOtrosServicios = (EditText) root.findViewById(R.id.editTextPrestacionOtrosServicios);
        this.editTextObservacionesMedicas = (EditText) root.findViewById(R.id.editTextObservacionesMedicas);
        this.editTextInteresesActividades = (EditText) root.findViewById(R.id.editTextInteresesActividades);
    }


    /**
     * Una función que se llama cuando se hace clic en un botón.
     * 
     * @param view La vista en la que se hizo clic.
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnModificarPaciente:
                accionBotonGuardar();
                break;
            case R.id.btnVolverPacienteModificar:
                accionBotonVolver();
                break;
        }
    }

    /**
     * Método que se ejecuta cuando se hace clic en el botón de guardar.
     * Si los campos son válidos, guarde los datos. De lo contrario, mostrar un mensaje de error.
     */
    private void accionBotonGuardar() {
        if (validarCampos()) {

        } else {
            Toast.makeText(getContext(), "Error al guardar", Toast.LENGTH_SHORT).show();
        }
    }

    
    private boolean validarCampos() {
        return false;
    }

    /**
     * La función se llama cuando el usuario hace clic en el botón Volver.
     */
    private void accionBotonVolver() {
//        getActivity().getSupportFragmentManager().popBackStack();
        getActivity().onBackPressed();
    }
}