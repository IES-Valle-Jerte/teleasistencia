package com.example.teleappsistencia.fragments.relacion_paciente_persona;

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

import com.example.teleappsistencia.APIService;
import com.example.teleappsistencia.ClienteRetrofit;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.Utilidades;
import com.example.teleappsistencia.clases.Persona;
import com.example.teleappsistencia.clases.RelacionPacientePersona;
import com.example.teleappsistencia.clases.Terminal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarRelacionPacientePersonaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarRelacionPacientePersonaFragment extends Fragment implements View.OnClickListener {

    private RelacionPacientePersona relacionPacientePersona;
    private Spinner spinnerPersonaModificar;
    private EditText editTextTipoRelacionModificar;
    private EditText editTextTieneLlaveViviendaModificar;
    private EditText editTextDisponibilidadModificar;
    private EditText editTextObservacionesModificar;
    private EditText editTextPrioridadModificar;

    private Button btnModificarRelacionPacientePersona;
    private Button btnVolverRelacionPacientePersonaModificar;

    public ModificarRelacionPacientePersonaFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static ModificarRelacionPacientePersonaFragment newInstance(RelacionPacientePersona relacionPacientePersona) {
        ModificarRelacionPacientePersonaFragment fragment = new ModificarRelacionPacientePersonaFragment();
        Bundle args = new Bundle();
        args.putSerializable("objetoRelacionPacientePersona", relacionPacientePersona);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            relacionPacientePersona = (RelacionPacientePersona) getArguments().getSerializable("objetoRelacionPacientePersona");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_modificar_relacion_paciente_persona, container, false);
        obtenerComponentes(root);
        rellenarCampos();
        // Inflate the layout for this fragment
        return root;
    }

    private void obtenerComponentes(View root) {
        spinnerPersonaModificar = root.findViewById(R.id.spinnerPersonaModificar);
        editTextTipoRelacionModificar = root.findViewById(R.id.editTextTipoRelacionModificar);
        editTextTieneLlaveViviendaModificar = root.findViewById(R.id.editTextTieneLlaveViviendaModificar);
        editTextDisponibilidadModificar = root.findViewById(R.id.editTextDisponibilidadModificar);
        editTextObservacionesModificar = root.findViewById(R.id.editTextObservacionesModificar);
        editTextPrioridadModificar = root.findViewById(R.id.editTextPrioridadModificar);

        btnModificarRelacionPacientePersona = root.findViewById(R.id.btnModificarRelacionPacientePersona);
        btnVolverRelacionPacientePersonaModificar = root.findViewById(R.id.btnVolverRelacionPacientePersonaModificar);

        btnModificarRelacionPacientePersona.setOnClickListener(this);
        btnVolverRelacionPacientePersonaModificar.setOnClickListener(this);
    }

    private void rellenarCampos() {
        inicializarSpinnerPersona();
        fijarSiTieneLlaves();
        editTextTipoRelacionModificar.setText(relacionPacientePersona.getTipoRelacion());
        editTextDisponibilidadModificar.setText(relacionPacientePersona.getDisponibilidad());
        editTextObservacionesModificar.setText(relacionPacientePersona.getObservaciones());
        editTextPrioridadModificar.setText(String.valueOf(relacionPacientePersona.getIdPersona()));
    }

    private void fijarSiTieneLlaves() {
        if (relacionPacientePersona.isTieneLlavesVivienda()) {
            this.editTextTieneLlaveViviendaModificar.setText("Si");
        } else {
            this.editTextTieneLlaveViviendaModificar.setText("No");
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
                    Persona persona = (Persona) Utilidades.getObjeto(relacionPacientePersona.getIdPersona(), "Persona");
                    spinnerPersonaModificar.setAdapter(adapter);
                    spinnerPersonaModificar.setSelection(buscarPosicionSpinnerPersona(listadoPersona, persona.getId()));
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

    private int buscarPosicionSpinnerTerminal(List<Terminal> listadoTerminales, int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado) {
            if (listadoTerminales.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return i - 1;
    }

    private int buscarPosicionSpinnerPersona(List<Persona> listadoPersonas, int id) {
        boolean encontrado = false;
        int i = 0;
        while (!encontrado) {
            if (listadoPersonas.get(i).getId() == id) {
                encontrado = true;
            }
            i++;
        }
        return i - 1;
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