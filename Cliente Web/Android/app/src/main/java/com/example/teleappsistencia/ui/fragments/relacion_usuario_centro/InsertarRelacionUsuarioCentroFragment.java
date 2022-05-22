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
 * Use the {@link InsertarRelacionUsuarioCentroFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertarRelacionUsuarioCentroFragment extends Fragment implements View.OnClickListener {

    private Paciente paciente;

    private Spinner spinnerPacienteInsertar;
    private Spinner spinnerCentroSanitarioInsertar;
    private EditText editTextPersonaContactoInsertar;
    private EditText editTextDistanciaInsertar;
    private EditText editTextTiempoInsertar;
    private EditText editTextObservacionesInsertar;
    private Button btnInsertarRelacionUsuarioCentro;
    private Button btnVolverRelacionUsuarioCentroInsertar;

    public InsertarRelacionUsuarioCentroFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InsertarRelacionUsuarioCentroFragment newInstance() {
        InsertarRelacionUsuarioCentroFragment fragment = new InsertarRelacionUsuarioCentroFragment();
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
        View root = inflater.inflate(R.layout.fragment_insertar_relacion_usuario_centro, container, false);

        obtenerComponentes(root);
        inicializarSpinnerPaciente();
        inicializarSpinnerCentroSanitario();
        // Inflate the layout for this fragment
        return root;
    }


    private void obtenerComponentes(View root) {
        spinnerCentroSanitarioInsertar = root.findViewById(R.id.spinnerCentroSanitarioInsertar);
        spinnerPacienteInsertar = root.findViewById(R.id.spinnerPacienteInsertar);
        editTextPersonaContactoInsertar = root.findViewById(R.id.editTextPersonaContactoInsertar);
        editTextDistanciaInsertar = root.findViewById(R.id.editTextDistanciaInsertar);
        editTextTiempoInsertar = root.findViewById(R.id.editTextTiempoInsertar);
        editTextObservacionesInsertar = root.findViewById(R.id.editTextObservacionesInsertar);
        btnInsertarRelacionUsuarioCentro = root.findViewById(R.id.btnInsertarRelacionUsuarioCentro);
        btnVolverRelacionUsuarioCentroInsertar = root.findViewById(R.id.btnVolverRelacionUsuarioCentroInsertar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInsertarRelacionUsuarioCentro:
                accionBotonGuardar();
                break;
            case R.id.btnVolverRelacionUsuarioCentroInsertar:
                accionBotonVolver();
                break;
        }
    }

    private void accionBotonGuardar() {

    }

    private void accionBotonVolver() {

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
                    spinnerPacienteInsertar.setAdapter(adapter);
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

    private void inicializarSpinnerCentroSanitario() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<CentroSanitario>> call = apiService.getListadoCentroSanitario("Bearer " + Utilidad.getToken().getAccess());
        call.enqueue(new retrofit2.Callback<List<CentroSanitario>>() {
            @Override
            public void onResponse(Call<List<CentroSanitario>> call, Response<List<CentroSanitario>> response) {
                if (response.isSuccessful()) {
                    List<CentroSanitario> lCentrosSanitarios = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, convertirListaCentrosSanitarios(lCentrosSanitarios));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCentroSanitarioInsertar.setAdapter(adapter);
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


}