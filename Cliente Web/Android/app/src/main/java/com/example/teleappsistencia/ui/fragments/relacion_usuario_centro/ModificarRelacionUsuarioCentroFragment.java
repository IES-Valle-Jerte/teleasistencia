package com.example.teleappsistencia.ui.fragments.relacion_usuario_centro;

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

import com.example.teleappsistencia.modelos.CentroSanitario;
import com.example.teleappsistencia.modelos.RelacionUsuarioCentro;
import com.example.teleappsistencia.servicios.APIService;
import com.example.teleappsistencia.servicios.ClienteRetrofit;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.utilidades.Utilidad;
import com.example.teleappsistencia.modelos.Paciente;
import com.example.teleappsistencia.modelos.Persona;
import com.example.teleappsistencia.modelos.Terminal;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarRelacionUsuarioCentroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarRelacionUsuarioCentroFragment extends Fragment implements View.OnClickListener {

    private RelacionUsuarioCentro relacionUsuarioCentro;

    private Spinner spinnerPacienteModificar;
    private Spinner spinnerCentroSanitarioModificar;
    private EditText editTextPersonaContactoModificar;
    private EditText editTextDistanciaModificar;
    private EditText editTextTiempoModificar;
    private EditText editTextObservacionesModificarRelacionUsuarioCentro;
    private Button btnModificarRelacionUsuarioCentro;
    private Button btnVolverRelacionUsuarioCentroModificar;

    public ModificarRelacionUsuarioCentroFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ModificarRelacionUsuarioCentroFragment newInstance(RelacionUsuarioCentro relacionUsuarioCentro) {
        ModificarRelacionUsuarioCentroFragment fragment = new ModificarRelacionUsuarioCentroFragment();
        Bundle args = new Bundle();
        args.putSerializable("objetoRelacionUsuarioCentro", relacionUsuarioCentro);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            relacionUsuarioCentro = (RelacionUsuarioCentro) getArguments().getSerializable("objetoRelacionUsuarioCentro");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_modificar_relacion_usuario_centro, container, false);

        obtenerComponentes(root);
        inicializarSpinnerPaciente();
        inicializarSpinnerCentroSanitario();
        editTextDistanciaModificar.setText(String.valueOf(relacionUsuarioCentro.getDistancia()));
        editTextTiempoModificar.setText(String.valueOf(relacionUsuarioCentro.getTiempo()));
        editTextObservacionesModificarRelacionUsuarioCentro.setText(relacionUsuarioCentro.getObservaciones());
        editTextPersonaContactoModificar.setText(relacionUsuarioCentro.getPersonaContacto());
        this.btnModificarRelacionUsuarioCentro.setOnClickListener(this);
        this.btnVolverRelacionUsuarioCentroModificar.setOnClickListener(this);

        // Inflate the layout for this fragment
        return root;
    }

    private void obtenerComponentes(View root) {
        this.spinnerPacienteModificar = root.findViewById(R.id.spinnerPacienteModificar);
        this.spinnerCentroSanitarioModificar = root.findViewById(R.id.spinnerCentroSanitarioModificar);
        this.editTextPersonaContactoModificar = root.findViewById(R.id.editTextPersonaContactoModificar);
        this.editTextDistanciaModificar = root.findViewById(R.id.editTextDistanciaModificar);
        this.editTextTiempoModificar = root.findViewById(R.id.editTextTiempoModificar);
        this.editTextObservacionesModificarRelacionUsuarioCentro = root.findViewById(R.id.editTextObservacionesModificarRelacionUsuarioCentro);
        this.btnModificarRelacionUsuarioCentro = root.findViewById(R.id.btnModificarRelacionUsuarioCentro);
        this.btnVolverRelacionUsuarioCentroModificar = root.findViewById(R.id.btnVolverRelacionUsuarioCentroModificar);
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

    private void inicializarSpinnerCentroSanitario() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<CentroSanitario>> call = apiService.getListadoCentroSanitario("Bearer " + Utilidad.getToken().getAccess());
        call.enqueue(new retrofit2.Callback<List<CentroSanitario>>() {
            @Override
            public void onResponse(Call<List<CentroSanitario>> call, Response<List<CentroSanitario>> response) {
                if (response.isSuccessful()) {
                    List<CentroSanitario> lCentrosSanitarios = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, convertirListaCentrosSanitarios(lCentrosSanitarios));
                    CentroSanitario centroSanitario = (CentroSanitario) Utilidad.getObjeto(relacionUsuarioCentro.getIdCentroSanitario(), "CentroSanitario");
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCentroSanitarioModificar.setAdapter(adapter);
                    spinnerCentroSanitarioModificar.setSelection(buscarPosicionSpinnerCentroSanitario(lCentrosSanitarios, centroSanitario.getId()));
                }
            }

            @Override
            public void onFailure(Call<List<CentroSanitario>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private List<String> convertirListaCentrosSanitarios(List<CentroSanitario> lCentrosSanitarios) {
        List<String> listadoCentrosSanitariosString = new ArrayList<>();
        for (CentroSanitario centroSanitario : lCentrosSanitarios) {
            listadoCentrosSanitariosString.add(centroSanitario.getNombre());
        }
        return listadoCentrosSanitariosString;
    }

    private int buscarPosicionSpinnerCentroSanitario(List<CentroSanitario> lCentrosSanitarios, int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado) {
            if (lCentrosSanitarios.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return i-1;
    }



    private void inicializarSpinnerPaciente() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<LinkedTreeMap>> call = apiService.getPacientes("Bearer " + Utilidad.getToken().getAccess());
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
                    Paciente paciente = (Paciente) Utilidad.getObjeto(relacionUsuarioCentro.getIdPaciente(), "Paciente");
                    if(relacionUsuarioCentro.getIdPaciente() != null) {
                        spinnerPacienteModificar.setAdapter(adapter);
                        spinnerPacienteModificar.setSelection(buscarPosicionSpinnerPaciente(listadoPacientes, paciente.getId()));
                    } else {
                        spinnerPacienteModificar.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<LinkedTreeMap>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private List<String> convertirListaPacientes(List<Paciente> listadoPacientes) {
        List<String> listadoPacientesString = new ArrayList<>();
        for (Paciente paciente : listadoPacientes) {
            listadoPacientesString.add("Nº expediente: " + paciente.getNumeroExpediente());
        }
        return listadoPacientesString;
    }

    private int buscarPosicionSpinnerPaciente(List<Paciente> listadoPacientes, int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado) {
            if (listadoPacientes.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return i-1;
    }
}