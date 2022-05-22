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

import com.example.teleappsistencia.servicios.APIService;
import com.example.teleappsistencia.servicios.ClienteRetrofit;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.utilidades.Utilidad;
import com.example.teleappsistencia.modelos.Persona;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertarRelacionPacientePersonaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertarRelacionPacientePersonaFragment extends Fragment implements View.OnClickListener {

    private Spinner spinnerPersonaInsertar;
    private EditText editTextTipoRelacionInsertar;
    private EditText editTextTieneLlaveViviendaInsertar;
    private EditText editTextDisponibilidadInsertar;
    private EditText editTextObservacionesInsertar;
    private EditText editTextPrioridadInsertar;

    private Button btnInsertarRelacionPacientePersona;
    private Button btnVolverRelacionPacientePersonaInsertar;

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

        obtenerComponentes(root);
        inicializarSpinnerPersona();
        // Inflate the layout for this fragment
        return root;
    }

    private void obtenerComponentes(View root) {
        spinnerPersonaInsertar = root.findViewById(R.id.spinnerPersonaModificar);
        editTextTipoRelacionInsertar = root.findViewById(R.id.editTextTipoRelacionModificar);
        editTextTieneLlaveViviendaInsertar = root.findViewById(R.id.editTextTieneLlaveViviendaModificar);
        editTextDisponibilidadInsertar = root.findViewById(R.id.editTextDisponibilidadModificar);
        editTextObservacionesInsertar = root.findViewById(R.id.editTextObservacionesModificar);
        editTextPrioridadInsertar = root.findViewById(R.id.editTextPrioridadModificar);
        btnInsertarRelacionPacientePersona = root.findViewById(R.id.btnModificarRelacionPacientePersona);
        btnVolverRelacionPacientePersonaInsertar = root.findViewById(R.id.btnVolverRelacionPacientePersonaModificar);
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
                    spinnerPersonaInsertar.setAdapter(adapter);
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