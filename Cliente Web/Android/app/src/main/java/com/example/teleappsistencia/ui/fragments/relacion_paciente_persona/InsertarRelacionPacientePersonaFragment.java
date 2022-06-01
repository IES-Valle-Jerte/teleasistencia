package com.example.teleappsistencia.ui.fragments.relacion_paciente_persona;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.teleappsistencia.R;
import com.example.teleappsistencia.modelos.Paciente;
import com.example.teleappsistencia.modelos.Persona;
import com.example.teleappsistencia.modelos.RelacionPacientePersona;
import com.example.teleappsistencia.servicios.APIService;
import com.example.teleappsistencia.servicios.ClienteRetrofit;
import com.example.teleappsistencia.utilidades.Constantes;
import com.example.teleappsistencia.utilidades.Utilidad;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertarRelacionPacientePersonaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertarRelacionPacientePersonaFragment extends Fragment implements View.OnClickListener {

    private Spinner spinnerPersonaInsertar;
    private Spinner spinnerPacienteInsertarRelacionPacientePersona;
    private EditText editTextTipoRelacionInsertar;
    private EditText editTextTieneLlaveViviendaInsertar;
    private EditText editTextDisponibilidadInsertar;
    private EditText editTextObservacionesInsertar;
    private EditText editTextPrioridadInsertar;

    private Button btnInsertarRelacionPacientePersona;
    private Button btnVolverRelacionPacientePersonaInsertar;

    //Atributos de la clase
    private RelacionPacientePersona relacionPacientePersonaInsertar;

    public InsertarRelacionPacientePersonaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InsertarRelacionPacientePersonaFragment newInstance() {
        InsertarRelacionPacientePersonaFragment fragment = new InsertarRelacionPacientePersonaFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_insertar_relacion_paciente_persona, container, false);
        relacionPacientePersonaInsertar = new RelacionPacientePersona();
        obtenerComponentes(root);
        inicializarSpinnerPersona();
        inicializarSpinnerPaciente();
        // Inflate the layout for this fragment
        return root;
    }

    private void obtenerComponentes(View root) {
        spinnerPersonaInsertar = root.findViewById(R.id.spinnerPersonaInsertar);
        spinnerPacienteInsertarRelacionPacientePersona = root.findViewById(R.id.spinnerPacienteInsertarRelacionPacientePersona);
        editTextTipoRelacionInsertar = root.findViewById(R.id.editTextTipoRelacionInsertar);
        editTextTieneLlaveViviendaInsertar = root.findViewById(R.id.editTextTieneLlaveViviendaInsertar);
        editTextDisponibilidadInsertar = root.findViewById(R.id.editTextDisponibilidadInsertar);
        editTextObservacionesInsertar = root.findViewById(R.id.editTextObservacionesInsertar);
        editTextPrioridadInsertar = root.findViewById(R.id.editTextPrioridadInsertarRelacionPacientePersona);
        btnInsertarRelacionPacientePersona = root.findViewById(R.id.btnInsertarRelacionPacientePersona);
        btnVolverRelacionPacientePersonaInsertar = root.findViewById(R.id.btnVolverRelacionPacientePersonaInsertar);
        this.btnInsertarRelacionPacientePersona.setOnClickListener(this);
        this.btnVolverRelacionPacientePersonaInsertar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInsertarRelacionPacientePersona:
                accionBotonGuardar();
                break;
            case R.id.btnVolverRelacionPacientePersonaModificar:
                volver();
                break;
        }
    }

    private void accionBotonGuardar() {
        if (validarCampos()) {
            obtenerDatosFormulario();
        }
    }

    private void obtenerDatosFormulario() {
        //Obtemos el id del paciente seleccionado desde el Spinner esta vez, ya que incluye el ID
        String pacienteSeleccionado = spinnerPacienteInsertarRelacionPacientePersona.getSelectedItem().toString();
        String[] pacienteSplit = pacienteSeleccionado.split("-");
        pacienteSeleccionado = pacienteSplit[0].replaceAll("\\s+", "");
        //Obtemos el id de la persona seleccionada desde el Spinner esta vez, ya que incluye el ID
        String personaSeleccionada = spinnerPersonaInsertar.getSelectedItem().toString();
        String[] personaSplit = personaSeleccionada.split("-");
        personaSeleccionada = personaSplit[0].replaceAll("\\s+", "");
        relacionPacientePersonaInsertar.setIdPersona(personaSeleccionada);
        //Obtenemos el resto de atributos de los editText
        if (editTextTieneLlaveViviendaInsertar.getText().toString().equals("Sí") || editTextTieneLlaveViviendaInsertar.getText().toString().equals("Si")) {
            relacionPacientePersonaInsertar.setTieneLlavesVivienda(true);
        } else {
            relacionPacientePersonaInsertar.setTieneLlavesVivienda(false);
        }
        relacionPacientePersonaInsertar.setTipoRelacion(editTextTipoRelacionInsertar.getText().toString());
        relacionPacientePersonaInsertar.setDisponibilidad(editTextDisponibilidadInsertar.getText().toString());
        relacionPacientePersonaInsertar.setObservaciones(editTextObservacionesInsertar.getText().toString());
        relacionPacientePersonaInsertar.setPrioridad(Integer.parseInt(editTextPrioridadInsertar.getText().toString()));
        insertarRelacionPacientePersona(pacienteSeleccionado, relacionPacientePersonaInsertar);
    }

    private void insertarRelacionPacientePersona(String pacienteSeleccionado, RelacionPacientePersona relacionPacientePersona) {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<RelacionPacientePersona> call = apiService.addRelacionPacientePersonaSeleccionadoPaciente(Integer.parseInt(pacienteSeleccionado), relacionPacientePersona, Constantes.BEARER + Utilidad.getToken().getAccess());
        call.enqueue(new Callback<RelacionPacientePersona>() {
            @Override
            public void onResponse(Call<RelacionPacientePersona> call, Response<RelacionPacientePersona> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(getContext(), Constantes.RELACIÓN_PACIENTE_PERSONA_INSERTADA_CORRECTAMENTE, Toast.LENGTH_SHORT).show();
                    limpiarCampos();
                    volver();
                }
            }

            @Override
            public void onFailure(Call<RelacionPacientePersona> call, Throwable t) {

            }
        });
    }

    public void limpiarCampos() {
        editTextTieneLlaveViviendaInsertar.setText("");
        editTextTipoRelacionInsertar.setText("");
        editTextDisponibilidadInsertar.setText("");
        editTextObservacionesInsertar.setText("");
        editTextPrioridadInsertar.setText("");
    }

    private void volver() {
        ListarRelacionPacientePersonaFragment listarRelacionPacientePersonaFragment = new ListarRelacionPacientePersonaFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, listarRelacionPacientePersonaFragment)
                .addToBackStack(null)
                .commit();
    }

    private boolean validarCampos() {
        if (editTextDisponibilidadInsertar.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), Constantes.CAMPO_VACIO, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (editTextObservacionesInsertar.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), Constantes.CAMPO_VACIO, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (String.valueOf(editTextPrioridadInsertar.getText().toString()).isEmpty()) {
            Toast.makeText(getContext(), Constantes.CAMPO_VACIO, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (editTextTieneLlaveViviendaInsertar.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), Constantes.CAMPO_VACIO, Toast.LENGTH_SHORT).show();
            return false;
        }
        if (editTextTipoRelacionInsertar.getText().toString().isEmpty()) {
            Toast.makeText(getContext(), Constantes.CAMPO_VACIO, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void inicializarSpinnerPaciente() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<LinkedTreeMap>> call = apiService.getPacientes(Constantes.BEARER + Utilidad.getToken().getAccess());
        call.enqueue(new retrofit2.Callback<List<LinkedTreeMap>>() {
            @Override
            public void onResponse(Call<List<LinkedTreeMap>> call, Response<List<LinkedTreeMap>> response) {
                if (response.isSuccessful()) {
                    List<Paciente> listadoPacientes = new ArrayList<>();
                    List<LinkedTreeMap> lPacientes = response.body();
                    for (LinkedTreeMap lPaciente : lPacientes) {
                        listadoPacientes.add((Paciente) Utilidad.getObjeto(lPaciente, "Paciente"));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, convertirListaPacientes(listadoPacientes));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerPacienteInsertarRelacionPacientePersona.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<LinkedTreeMap>> call, Throwable t) {
                Toast.makeText(getContext(), Constantes.ERROR_AL_OBTENER_LOS_DATOS, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private List<String> convertirListaPacientes(List<Paciente> listadoPacientes) {
        List<String> listadoPacientesString = new ArrayList<>();
        for (Paciente paciente : listadoPacientes) {
            listadoPacientesString.add(paciente.getId() + Constantes.REGEX_SEPARADOR_GUION + "Nº expediente: " + paciente.getNumeroExpediente());
        }
        return listadoPacientesString;
    }

    private void inicializarSpinnerPersona() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<Persona>> call = apiService.getListadoPersona(Constantes.BEARER + Utilidad.getToken().getAccess());
        call.enqueue(new retrofit2.Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                if (response.isSuccessful()) {
                    List<Persona> listadoPersona = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, convertirListaPersonas(listadoPersona));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerPersonaInsertar.setAdapter(adapter);
                } else {
                    Toast.makeText(getContext(), Constantes.ERROR_AL_OBTENER_LOS_DATOS, Toast.LENGTH_SHORT).show();
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
            listadoPersonaString.add(persona.getId() + Constantes.REGEX_SEPARADOR_GUION + persona.getNombre() + " " + persona.getApellidos());
        }
        return listadoPersonaString;
    }
}