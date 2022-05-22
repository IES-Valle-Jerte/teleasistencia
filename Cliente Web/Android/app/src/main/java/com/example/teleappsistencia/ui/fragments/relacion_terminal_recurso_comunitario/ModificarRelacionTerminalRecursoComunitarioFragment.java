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

import com.example.teleappsistencia.modelos.RecursoComunitario;
import com.example.teleappsistencia.servicios.APIService;
import com.example.teleappsistencia.servicios.ClienteRetrofit;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.utilidades.Utilidad;
import com.example.teleappsistencia.modelos.Paciente;
import com.example.teleappsistencia.modelos.Persona;
import com.example.teleappsistencia.modelos.RelacionTerminalRecursoComunitario;
import com.example.teleappsistencia.modelos.Terminal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarRelacionTerminalRecursoComunitarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarRelacionTerminalRecursoComunitarioFragment extends Fragment implements View.OnClickListener {

    private RelacionTerminalRecursoComunitario relacionTerminalRecursoComunitario;

    private Spinner spinnerTerminalModificarRelacionTerminalRecursoComunitario;
    private Spinner spinnerRecursoComunitarioModificarRelacionTerminalRecursoComunitario;
    private Button btnModificarRelacionTerminalRecursoComunitario;
    private Button btnVolverRelacionTerminalRecursoComunitarioModificar;

    public ModificarRelacionTerminalRecursoComunitarioFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ModificarRelacionTerminalRecursoComunitarioFragment newInstance(RelacionTerminalRecursoComunitario relacionTerminalRecursoComunitario) {
        ModificarRelacionTerminalRecursoComunitarioFragment fragment = new ModificarRelacionTerminalRecursoComunitarioFragment();
        Bundle args = new Bundle();
        args.putSerializable("objetoRelacionTerminalRecursoComunitario", relacionTerminalRecursoComunitario);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            relacionTerminalRecursoComunitario = (RelacionTerminalRecursoComunitario) getArguments().getSerializable("objetoRelacionTerminalRecursoComunitario");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_modificar_relacion_terminal_recurso_comunitario, container, false);

        obtenerComponentes(root);

        rellenarCampos();

        // Inflate the layout for this fragment
        return root;
    }

    private void rellenarCampos() {
        inicializarSpinnerTerminal();
        inicializarSpinnerRecursoComunitario();
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
                    Terminal terminal = (Terminal) Utilidad.getObjeto(relacionTerminalRecursoComunitario.getIdTerminal(), "Terminal");
                    spinnerTerminalModificarRelacionTerminalRecursoComunitario.setAdapter(adapter);
                    spinnerTerminalModificarRelacionTerminalRecursoComunitario.setSelection(buscarPosicionSpinnerTerminal(listadoTerminales,terminal.getId()), true);
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

    private void inicializarSpinnerRecursoComunitario() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();
        Call<List<RecursoComunitario>> call = apiService.getListadoRecursoComunitario("Bearer " + Utilidad.getToken().getAccess());
        call.enqueue(new retrofit2.Callback<List<RecursoComunitario>>() {
            @Override
            public void onResponse(Call<List<RecursoComunitario>> call, Response<List<RecursoComunitario>> response) {
                if (response.isSuccessful()) {
                    List<RecursoComunitario> listadoRecursoComunitario = response.body();
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, convertirListaRecursoComunitario(listadoRecursoComunitario));
                    RecursoComunitario recursoComunitario = (RecursoComunitario) Utilidad.getObjeto(relacionTerminalRecursoComunitario.getIdRecursoComunitario(), "RecursoComunitario");
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerRecursoComunitarioModificarRelacionTerminalRecursoComunitario.setAdapter(adapter);
                    spinnerRecursoComunitarioModificarRelacionTerminalRecursoComunitario.setSelection(buscarPosicionSpinnerRecursoComunitario(listadoRecursoComunitario,recursoComunitario.getId()));
                }
            }

            @Override
            public void onFailure(Call<List<RecursoComunitario>> call, Throwable t) {
                Toast.makeText(getContext(), "Error al obtener los datos", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private int buscarPosicionSpinnerRecursoComunitario(List<RecursoComunitario> listadoRecursoComunitario, int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado) {
            if (listadoRecursoComunitario.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return i-1;
    }

    private List<String> convertirListaRecursoComunitario(List<RecursoComunitario> listadoRecursoComunitario) {
        List<String> listadoRecursoComunitarioString = new ArrayList<>();
        for (RecursoComunitario recursoComunitario : listadoRecursoComunitario) {
            listadoRecursoComunitarioString.add(recursoComunitario.getNombre());
        }
        return listadoRecursoComunitarioString;
    }

    private void obtenerComponentes(View root) {
        this.spinnerTerminalModificarRelacionTerminalRecursoComunitario = root.findViewById(R.id.spinnerTerminalModificarRelacionTerminalRecursoComunitario);
        this.spinnerRecursoComunitarioModificarRelacionTerminalRecursoComunitario = root.findViewById(R.id.spinnerRecursoComunitarioModificarRelacionTerminalRecursoComunitario);
        this.btnModificarRelacionTerminalRecursoComunitario = root.findViewById(R.id.btnModificarRelacionTerminalRecursoComunitario);
        this.btnVolverRelacionTerminalRecursoComunitarioModificar = root.findViewById(R.id.btnVolverRelacionTerminalRecursoComunitarioModificar);
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