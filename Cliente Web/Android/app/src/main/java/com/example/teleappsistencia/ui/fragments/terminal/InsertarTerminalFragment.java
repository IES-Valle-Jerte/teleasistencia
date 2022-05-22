package com.example.teleappsistencia.ui.fragments.terminal;

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

import com.example.teleappsistencia.modelos.Paciente;
import com.example.teleappsistencia.modelos.TipoVivienda;
import com.example.teleappsistencia.servicios.APIService;
import com.example.teleappsistencia.servicios.ClienteRetrofit;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.utilidades.Utilidad;
import com.example.teleappsistencia.modelos.Persona;
import com.example.teleappsistencia.modelos.Terminal;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertarTerminalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertarTerminalFragment extends Fragment implements View.OnClickListener {

    private Terminal terminal;

    private Spinner spinnerTitularInsertar;
    private Spinner spinnerTipoViviendaInsertar;
    private EditText editTextNumeroTerminalInsertar;
    private EditText editTextModoAccesoViviendaInsertar;
    private EditText editTextBarrerasArquitectonicasInsertar;

    private Button btnInsertarTerminal;
    private Button btnVolverTerminalInsertar;

    public InsertarTerminalFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static InsertarTerminalFragment newInstance() {
        InsertarTerminalFragment fragment = new InsertarTerminalFragment();
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
        View root = inflater.inflate(R.layout.fragment_insertar_terminal, container, false);

        obtenerComponentes(root);
        inicializarSpinnerTitular();
        inicializarSpinnerTipoVivienda();
        // Inflate the layout for this fragment
        return root;
    }

    private void obtenerComponentes(View root) {
        spinnerTitularInsertar = root.findViewById(R.id.spinnerTitularInsertar);
        spinnerTipoViviendaInsertar = root.findViewById(R.id.spinnerTipoViviendaInsertar);
        editTextNumeroTerminalInsertar = root.findViewById(R.id.editTextNumeroTerminalInsertar);
        editTextModoAccesoViviendaInsertar = root.findViewById(R.id.editTextModoAccesoViviendaInsertar);
        editTextBarrerasArquitectonicasInsertar = root.findViewById(R.id.editTextBarrerasArquitectonicasInsertar);
        btnInsertarTerminal = root.findViewById(R.id.btnInsertarTerminal);
        btnVolverTerminalInsertar = root.findViewById(R.id.btnVolverTerminalInsertar);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnInsertarTerminal:
                accionBotonGuardar();
                break;
            case R.id.btnVolverTerminalInsertar:
                accionBotonVolver();
                break;
        }
    }

    private void accionBotonGuardar() {

    }

    private void accionBotonVolver() {

    }

    private void inicializarSpinnerTitular() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<LinkedTreeMap>> call = apiService.getPacientes("Bearer " + Utilidad.getToken().getAccess());
        call.enqueue(new retrofit2.Callback<List<LinkedTreeMap>>() {
            @Override
            public void onResponse(Call<List<LinkedTreeMap>> call, Response<List<LinkedTreeMap>> response) {
                if (response.isSuccessful()) {
                    List<LinkedTreeMap> listadoPacientes = response.body();
                    List<Paciente> listadoPacientesCompleto = new ArrayList<>();
                    for (LinkedTreeMap paciente : listadoPacientes) {
                        listadoPacientesCompleto.add ((Paciente) Utilidad.getObjeto(paciente, "Paciente"));
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, convertirListaPacientes(listadoPacientesCompleto));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerTitularInsertar.setAdapter(adapter);
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
        List<String> listadoTerminalesString = new ArrayList<>();
        for (Paciente paciente : listadoPacientes) {
            listadoTerminalesString.add("Exp: " + paciente.getNumeroExpediente());
        }
        return listadoTerminalesString;
    }


    private void inicializarSpinnerTipoVivienda() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<TipoVivienda>> call = apiService.getListadoTipoVivienda("Bearer " + Utilidad.getToken().getAccess());
        call.enqueue(new retrofit2.Callback<List<TipoVivienda>>() {
            @Override
            public void onResponse(Call<List<TipoVivienda>> call, Response<List<TipoVivienda>> response) {
                if (response.isSuccessful()) {
                    List<TipoVivienda> listadoTipoVivienda = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, convertirListaTipoVivienda(listadoTipoVivienda));
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerTipoViviendaInsertar.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<TipoVivienda>> call, Throwable t) {

            }
        });
    }

    private List<String> convertirListaTipoVivienda(List<TipoVivienda> listadoTipoVivienda) {
        List<String> listadoTipoViviendaString = new ArrayList<>();
        for (TipoVivienda tipoVivienda : listadoTipoVivienda) {
            listadoTipoViviendaString.add(tipoVivienda.getNombre());
        }
        return listadoTipoViviendaString;
    }

    private List<String> convertirListaPersonas(List<Persona> listadoPersona) {
        List<String> listadoPersonaString = new ArrayList<>();
        for (Persona persona : listadoPersona) {
            listadoPersonaString.add(persona.getNombre() + " " + persona.getApellidos());
        }
        return listadoPersonaString;
    }
}