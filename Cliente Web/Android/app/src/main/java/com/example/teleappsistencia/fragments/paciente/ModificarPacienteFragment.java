package com.example.teleappsistencia.fragments.paciente;

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

import com.example.teleappsistencia.APIService;
import com.example.teleappsistencia.ClienteRetrofit;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.Utilidades;
import com.example.teleappsistencia.clases.Paciente;
import com.example.teleappsistencia.clases.Persona;
import com.example.teleappsistencia.clases.Terminal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarPacienteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarPacienteFragment extends Fragment implements View.OnClickListener {

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
    private Button btnModificarPaciente;
    private Button btnVolverPacienteModificar;

    public ModificarPacienteFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ModificarPacienteFragment newInstance(Paciente paciente) {
        ModificarPacienteFragment fragment = new ModificarPacienteFragment();
        Bundle args = new Bundle();
        args.putSerializable("objetoPaciente", paciente);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            paciente = (Paciente) getArguments().getSerializable("objetoPaciente");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_modificar_paciente, container, false);

        obtenerComponentes(root);

        rellenarCampos();

        this.btnVolverPacienteModificar.setOnClickListener(this);
        this.btnModificarPaciente.setOnClickListener(this);

        // Inflate the layout for this fragment
        return root;
    }

    private void rellenarCampos() {
        inicializarSpinnerTerminal();
        inicializarSpinnerPersona();
        fijarSiTieneUCR();
        editTextNumeroExpediente.setText(paciente.getNumeroExpediente());
        editTextNumeroSeguridadSocial.setText(paciente.getNumeroSeguridadSocial());
        editTextPrestacionOtrosServicios.setText(paciente.getPrestacionOtrosServiciosSociales());
        editTextObservacionesMedicas.setText(paciente.getObservacionesMedicas());
        editTextInteresesActividades.setText(paciente.getInteresesYActividades());
    }

    private void fijarSiTieneUCR() {
        if (paciente.isTieneUcr()) {
            this.editTextTieneUCR.setText("Si");
        } else {
            this.editTextTieneUCR.setText("No");
        }
    }

    private void inicializarSpinnerPersona() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<Persona>> call = apiService.getListadoPersona("Bearer " + Utilidades.getToken().getAccess());
        call.enqueue(new retrofit2.Callback<List<Persona>>() {
            @Override
            public void onResponse(Call<List<Persona>> call, Response<List<Persona>> response) {
                if (response.isSuccessful()) {
                    List<Persona> listadoPersona = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, convertirListaPersonas(listadoPersona));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Persona persona = (Persona) Utilidades.getObjeto(paciente.getPersona(), "Persona");
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
        Call<List<Terminal>> call = apiService.getListadoTerminal("Bearer " + Utilidades.getToken().getAccess());
        call.enqueue(new retrofit2.Callback<List<Terminal>>() {
            @Override
            public void onResponse(Call<List<Terminal>> call, Response<List<Terminal>> response) {
                if (response.isSuccessful()) {
                    List<Terminal> listadoTerminales = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, convertirListaTerminales(listadoTerminales));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    Terminal terminal = (Terminal) Utilidades.getObjeto(paciente.getTerminal(), "Terminal");
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

    private void accionBotonGuardar() {
        if (validarCampos()) {

        } else {
            Toast.makeText(getContext(), "Error al guardar", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarCampos() {
        return false;
    }

    private void accionBotonVolver() {
//        getActivity().getSupportFragmentManager().popBackStack();
        getActivity().onBackPressed();
    }
}