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

import com.example.teleappsistencia.modelos.TipoVivienda;
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
 * Use the {@link ModificarTerminalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarTerminalFragment extends Fragment implements View.OnClickListener {

    private Terminal terminal;

    private Spinner spinnerTitularModificar;
    private Spinner spinnerTipoViviendaModificar;
    private EditText editTextNumeroTerminalModificar;
    private EditText editTextModoAccesoViviendaModificar;
    private EditText editTextBarrerasArquitectonicasModificar;

    private Button btnModificarTerminal;
    private Button btnVolverTerminalModificar;

    public ModificarTerminalFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ModificarTerminalFragment newInstance(Terminal terminal) {
        ModificarTerminalFragment fragment = new ModificarTerminalFragment();
        Bundle args = new Bundle();
        args.putSerializable("objetoTerminal", terminal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            terminal = (Terminal) getArguments().getSerializable("objetoTerminal");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_modificar_terminal, container, false);

        obtenerComponentes(root);

        rellenarCampos();

        this.btnModificarTerminal.setOnClickListener(this);
        this.btnVolverTerminalModificar.setOnClickListener(this);

        // Inflate the layout for this fragment
        return root;
    }

    private void rellenarCampos() {
        inicializarSpinnerTipoVivienda();
        inicializarSpinnerTitular();
        editTextModoAccesoViviendaModificar.setText(terminal.getModoAccesoVivienda());
        editTextBarrerasArquitectonicasModificar.setText(terminal.getBarrerasArquitectonicas());
        editTextNumeroTerminalModificar.setText(terminal.getNumeroTerminal());
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
                    Paciente paciente = (Paciente) Utilidad.getObjeto(terminal.getTitular(), "Paciente");
                    spinnerTitularModificar.setAdapter(adapter);
                    spinnerTitularModificar.setSelection(buscarPosicionSpinnerTitular(listadoPacientesCompleto,paciente.getId()));
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
                    TipoVivienda tipoVivienda = (TipoVivienda) Utilidad.getObjeto(terminal.getTipoVivienda(), "TipoVivienda");
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerTipoViviendaModificar.setAdapter(adapter);
                    spinnerTipoViviendaModificar.setSelection(buscarPosicionSpinnerTipoVivienda(listadoTipoVivienda,tipoVivienda.getId()));
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


    private int buscarPosicionSpinnerTitular(List<Paciente> listadoTitulares, int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado) {
            if (listadoTitulares.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return i-1;
    }

    private int buscarPosicionSpinnerTipoVivienda(List<TipoVivienda> listadoTipoVivienda,int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado) {
            if (listadoTipoVivienda.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return i-1;
    }

    private void obtenerComponentes(View root) {
        spinnerTipoViviendaModificar = root.findViewById(R.id.spinnerTipoViviendaModificar);
        spinnerTitularModificar = root.findViewById(R.id.spinnerTitularModificar);
        editTextBarrerasArquitectonicasModificar = root.findViewById(R.id.editTextBarrerasArquitectonicasModificar);
        editTextNumeroTerminalModificar = root.findViewById(R.id.editTextNumeroTerminalModificar);
        editTextModoAccesoViviendaModificar = root.findViewById(R.id.editTextModoAccesoViviendaModificar);
        btnModificarTerminal = root.findViewById(R.id.btnModificarTerminal);
        btnVolverTerminalModificar = root.findViewById(R.id.btnVolverTerminalModificar);
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