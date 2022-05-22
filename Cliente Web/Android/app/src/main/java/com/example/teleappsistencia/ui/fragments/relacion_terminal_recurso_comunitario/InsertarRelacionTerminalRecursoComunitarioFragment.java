package com.example.teleappsistencia.ui.fragments.relacion_terminal_recurso_comunitario;

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
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertarRelacionTerminalRecursoComunitarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertarRelacionTerminalRecursoComunitarioFragment extends Fragment implements View.OnClickListener {

    private Paciente paciente;

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

    public InsertarRelacionTerminalRecursoComunitarioFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InsertarRelacionTerminalRecursoComunitarioFragment newInstance() {
        InsertarRelacionTerminalRecursoComunitarioFragment fragment = new InsertarRelacionTerminalRecursoComunitarioFragment();
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
        View root = inflater.inflate(R.layout.fragment_insertar_relacion_terminal_recurso_comunitario, container, false);

        obtenerComponentes(root);
        inicializarSpinnerTerminal();
        inicializarSpinnerPersona();
        // Inflate the layout for this fragment
        return root;
    }

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

    private void accionBotonGuardar() {

    }

    private void accionBotonVolver() {

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

    private List<String> convertirListaTerminales(List<Terminal> listadoTerminales) {
        List<String> listadoTerminalesString = new ArrayList<>();
        for (Terminal terminal : listadoTerminales) {
            listadoTerminalesString.add("Nº de terminal: " + terminal.getNumeroTerminal());
        }
        return listadoTerminalesString;
    }


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

    private List<String> convertirListaPersonas(List<Persona> listadoPersona) {
        List<String> listadoPersonaString = new ArrayList<>();
        for (Persona persona : listadoPersona) {
            listadoPersonaString.add(persona.getNombre() + " " + persona.getApellidos());
        }
        return listadoPersonaString;
    }
}