package com.example.teleappsistencia.fragments.api.persona;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.teleappsistencia.APIService;
import com.example.teleappsistencia.MainActivity;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.Utils;
import com.example.teleappsistencia.clases.Direccion;
import com.example.teleappsistencia.clases.Persona;
import com.example.teleappsistencia.fragments.dialogs.DatePickerFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarPersonaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarPersonaFragment extends Fragment {

    private Button btn_insertar;
    private EditText editText_nombre;
    private EditText editText_apellidos;
    private EditText editText_dni;
    private EditText editText_fechaNacimiento;
    private EditText editText_telefonoFijo;
    private EditText editText_telefonoMovil;
    private Spinner spinner_sexo;
    private Spinner spinner_direcciones;

    public ModificarPersonaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InsertarPersonaFragment.
     */
    public static ModificarPersonaFragment newInstance(String param1, String param2) {
        ModificarPersonaFragment fragment = new ModificarPersonaFragment();
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
        View view = inflater.inflate(R.layout.fragment_insertar_persona, container, false);

        this.btn_insertar = (Button) view.findViewById(R.id.btn_guardar_persona);
        this.editText_nombre = (EditText) view.findViewById(R.id.editText_nombre_persona);
        this.editText_apellidos = (EditText) view.findViewById(R.id.editText_apellidos_persona);
        this.editText_dni = (EditText) view.findViewById(R.id.editText_dni_persona);
        this.editText_fechaNacimiento = (EditText) view.findViewById(R.id.editText_fechaNacimiento_persona);
        this.editText_telefonoFijo = (EditText) view.findViewById(R.id.editText_telefonoFijo_persona);
        this.editText_telefonoMovil = (EditText) view.findViewById(R.id.editText_telefonoMovil_persona);
        this.spinner_sexo = (Spinner) view.findViewById(R.id.spinner_sexo_persona);
        this.spinner_direcciones = (Spinner) view.findViewById(R.id.spinner_direccion_persona);

        this.spinner_sexo.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, new String[]{"Hombre", "Mujer"}));
        inicializarSpinnerDirecciones();

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarPersona();
            }
        });

        this.editText_fechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDatePickerDialog();
            }
        });

        return view;
    }

    private void mostrarDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String selectedDate = year + "-" + Utils.twoDigitsDate(month+1) + "-" + Utils.twoDigitsDate(day);
                editText_fechaNacimiento.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");
    }

    private void inicializarSpinnerDirecciones() {
        APIService apiService = Utils.inicializarApiService(getContext());

        Call<List<Direccion>> call = apiService.getDirecciones("Bearer " + MainActivity.token.getAccess());
        call.enqueue(new Callback<List<Direccion>>() {
            @Override
            public void onResponse(Call<List<Direccion>> call, Response<List<Direccion>> response) {
                if (response.isSuccessful()) {
                    List<Direccion> direcciones = response.body();
                    System.out.println(direcciones);
                    spinner_direcciones.setAdapter(new ArrayAdapter<Direccion>(getContext(), R.layout.support_simple_spinner_dropdown_item, direcciones));
                }
            }

            @Override
            public void onFailure(Call<List<Direccion>> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    private void insertarPersona() {
        String nombre = this.editText_nombre.getText().toString();
        String apellidos = this.editText_apellidos.getText().toString();
        String dni = editText_dni.getText().toString();
        String fechaNacimiento = editText_fechaNacimiento.getText().toString();
        String sexo = spinner_sexo.getSelectedItem().toString();
        String telefonoFijo = editText_telefonoFijo.getText().toString();
        String telefonoMovil = editText_telefonoMovil.getText().toString();
        Direccion direccion = (Direccion) spinner_direcciones.getSelectedItem();

        Persona persona = new Persona(nombre,apellidos,dni, fechaNacimiento,sexo,telefonoFijo,telefonoMovil, direccion.getId());


        APIService apiService = Utils.inicializarApiService(getContext());

        Call<Persona> call = apiService.addPersona(persona, "Bearer " + MainActivity.token.getAccess());
        call.enqueue(new Callback<Persona>() {
            @Override
            public void onResponse(Call<Persona> call, Response<Persona> response) {
                if (response.isSuccessful()) {
                    Persona persona = response.body();
                    System.out.println(persona);
                    Toast.makeText(getContext(), "Se ha añadido una nueva persona.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error al añadir la nueva personaº. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Persona> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }
}