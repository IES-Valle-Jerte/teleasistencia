package com.example.teleappsistencia.ui.api.fragments.persona;

import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.teleappsistencia.ui.api.APIService;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.ui.dialogs.AlertDialogBuilder;
import com.example.teleappsistencia.ui.utils.Utils;
import com.example.teleappsistencia.ui.api.ClienteRetrofit;
import com.example.teleappsistencia.ui.objects.Direccion;
import com.example.teleappsistencia.ui.objects.Persona;
import com.example.teleappsistencia.ui.dialogs.DatePickerFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertarPersonaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertarPersonaFragment extends Fragment {

    private Button btn_insertar;
    private EditText editText_nombre;
    private EditText editText_apellidos;
    private EditText editText_dni;
    private EditText editText_fechaNacimiento;
    private EditText editText_telefonoFijo;
    private EditText editText_telefonoMovil;
    private Spinner spinner_sexo;
    private Spinner spinner_direccion;

    private TextView textView_error_nombre;
    private TextView textView_error_apellidos;
    private TextView textView_error_dni;
    private TextView textView_error_fechaNacimiento;
    private TextView textView_error_telefonoFijo;
    private TextView textView_error_telefonoMovil;

    public InsertarPersonaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InsertarPersonaFragment.
     */
    public static InsertarPersonaFragment newInstance() {
        InsertarPersonaFragment fragment = new InsertarPersonaFragment();
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
        this.spinner_direccion = (Spinner) view.findViewById(R.id.spinner_direccion_persona);

        this.textView_error_nombre = (TextView) view.findViewById(R.id.textView_error_nombre_persona);
        this.textView_error_apellidos = (TextView) view.findViewById(R.id.textView_error_apellidos_persona);
        this.textView_error_dni = (TextView) view.findViewById(R.id.textView_error_dni_persona);
        this.textView_error_fechaNacimiento = (TextView) view.findViewById(R.id.textView_error_fechaNacimiento_persona);
        this.textView_error_telefonoFijo = (TextView) view.findViewById(R.id.textView_error_telefonoFijo_persona);
        this.textView_error_telefonoMovil = (TextView) view.findViewById(R.id.textView_error_telefonoMovil_persona);

        this.spinner_sexo.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.support_simple_spinner_dropdown_item, new String[]{"Hombre", "Mujer"}));
        inicializarSpinnerDirecciones();

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarPersona()) {
                    insertarPersona();
                }
            }
        });

        /* Creo un dialog datePicker para recoger la fecha de nacimiento */
        this.editText_fechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDatePickerDialog();
            }
        });

        inicializarListeners();

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

        newFragment.show(getActivity().getSupportFragmentManager(), getString(R.string.tag_date_picker));
    }

    private void inicializarSpinnerDirecciones() {
        APIService apiService = ClienteRetrofit.getInstance().getAPIService();

        Call<List<Direccion>> call = apiService.getDirecciones("Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<List<Direccion>>() {
            @Override
            public void onResponse(Call<List<Direccion>> call, Response<List<Direccion>> response) {
                if (response.isSuccessful()) {
                    List<Direccion> direcciones = response.body();
                    System.out.println(direcciones);
                    spinner_direccion.setAdapter(new ArrayAdapter<Direccion>(getContext(), R.layout.support_simple_spinner_dropdown_item, direcciones));
                }
            }

            @Override
            public void onFailure(Call<List<Direccion>> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    /**
     * Método para insertar una nueva persona en la base de datos.
     * El método realiza una petición a la API con los datos proporcionados por el usuario.
     */
    private void insertarPersona() {
        String nombre = this.editText_nombre.getText().toString();
        String apellidos = this.editText_apellidos.getText().toString();
        String dni = editText_dni.getText().toString();
        String fechaNacimiento = editText_fechaNacimiento.getText().toString();
        String sexo = spinner_sexo.getSelectedItem().toString();
        String telefonoFijo = editText_telefonoFijo.getText().toString();
        String telefonoMovil = editText_telefonoMovil.getText().toString();
        Direccion direccion = (Direccion) spinner_direccion.getSelectedItem();

        Persona persona = new Persona(nombre,apellidos,dni, fechaNacimiento,sexo,telefonoFijo,telefonoMovil, direccion.getId());

        APIService apiService = ClienteRetrofit.getInstance().getAPIService();

        Call<Object> call = apiService.addPersona(persona, "Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Object persona = response.body();
                    AlertDialogBuilder.crearInfoAlerDialog(getContext(), getString(R.string.infoAlertDialog_insertado_persona));
                    borrarEditTexts();
                } else {
                    AlertDialogBuilder.crearErrorAlerDialog(getContext(), Integer.toString(response.code()));
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    /**
     * Método que borra todos los datos de los EditText y quita los mensajes de error.
     */
    private void borrarEditTexts() {
        this.editText_nombre.setText(getString(R.string.string_vacio));
        this.editText_apellidos.setText(getString(R.string.string_vacio));
        this.editText_dni.setText(getString(R.string.string_vacio));
        this.editText_fechaNacimiento.setText(getString(R.string.string_vacio));
        this.editText_telefonoFijo.setText(getString(R.string.string_vacio));
        this.editText_telefonoMovil.setText(getString(R.string.string_vacio));

        this.textView_error_nombre.setVisibility(View.GONE);
        this.textView_error_apellidos.setVisibility(View.GONE);
        this.textView_error_dni.setVisibility(View.GONE);
        this.textView_error_fechaNacimiento.setVisibility(View.GONE);
        this.textView_error_telefonoFijo.setVisibility(View.GONE);
        this.textView_error_telefonoMovil.setVisibility(View.GONE);
    }

    /**
     * Método que revisa si los datos de los EditText son válidos.
     * @return Devuelve true si es válido de lo contrario devuelve false.
     */
    private boolean validarPersona() {
        boolean validNombre, validApellidos, validDni, validFechaNacimiento, validTelefonoFijo, validTelefonoMovil, validDireccion;

        validNombre = validarNombre(editText_nombre.getText().toString());
        validApellidos = validarApellidos(editText_apellidos.getText().toString());
        validDni = validarDni(editText_dni.getText().toString());
        validFechaNacimiento = validarFechaNacimiento(editText_fechaNacimiento.getText().toString());
        validTelefonoFijo = validarTelefonoFijo(editText_telefonoFijo.getText().toString());
        validTelefonoMovil = validarTelefonoMovil(editText_telefonoMovil.getText().toString());
        validDireccion = validarDireccion();

        if((validNombre) && (validApellidos) && (validDni) && (validFechaNacimiento) && (validTelefonoFijo) && (validTelefonoMovil) && (validDireccion)){
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que inicializa todos los TextWachers de los EditTexts.
     * Los TextWachers se encuentran constantemente revisando si se ha añadido algo a los EditText.
     * Si se ha añadido algo, revisa si lo escrito en el EditText es válido o no.
     */
    private void inicializarListeners() {
        this.editText_nombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarNombre(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        this.editText_apellidos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarApellidos(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        this.editText_dni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarDni(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        this.editText_fechaNacimiento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarFechaNacimiento(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        this.editText_telefonoFijo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarTelefonoFijo(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        this.editText_telefonoMovil.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarTelefonoMovil(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    public boolean validarNombre(String nombre) {
        boolean valid = false;
        if ((nombre.isEmpty()) || (nombre.trim().equals(""))) {         // Reviso si el nombre está vacio.
            textView_error_nombre.setText(R.string.textview_nombre_obligatorio);
            textView_error_nombre.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacio entonces le asigno el texto de que es obligatorio y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_nombre.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }

    public boolean validarApellidos(String apellidos) {
        boolean valid = false;
        if ((apellidos.isEmpty()) || (apellidos.trim().equals(""))) {    // Reviso si el apellido está vacio.
            textView_error_apellidos.setText(R.string.textview_apellidos_obligatorios);
            textView_error_apellidos.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacio entonces le asigno el texto de que es obligatorio y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_apellidos.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }

    public boolean validarDni(String dni) {
        boolean valid = false;
        if ((dni.isEmpty()) || (dni.trim().equals(""))) {               // Reviso si el dni está vacio.
            textView_error_dni.setText(R.string.textview_dni_obligatorio);
            textView_error_dni.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacio entonces le asigno el texto de que es obligatorio y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_dni.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }

    public boolean validarFechaNacimiento(String fechaNacimiento) {
        boolean valid = false;
        if ((fechaNacimiento.isEmpty()) || (fechaNacimiento.trim().equals(""))) {     // Reviso si la fecha de nacimiento está vacia.
            textView_error_fechaNacimiento.setText(R.string.textview_fechaNacimiento_obligatorio);
            textView_error_fechaNacimiento.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacia entonces le asigno el texto de que es obligatorio y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_fechaNacimiento.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }

    public boolean validarTelefonoFijo(String telefonoFijo) {
        boolean valid = false;
        if ((telefonoFijo.isEmpty()) || (telefonoFijo.trim().equals(""))) {     // Reviso si el teléfono fijo está vacio.
            textView_error_telefonoFijo.setText(R.string.textview_telefonoFijo_obligatorio);
            textView_error_telefonoFijo.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacio entonces le asigno el texto de que es obligatorio y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_telefonoFijo.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }

    public boolean validarTelefonoMovil(String telefonoMovil) {
        boolean valid = false;
        if ((telefonoMovil.isEmpty()) || (telefonoMovil.trim().equals(""))) {     // Reviso si el teléfono móvil está vacio.
            textView_error_telefonoMovil.setText(R.string.textview_telefonoMovil_obligatorio);
            textView_error_telefonoMovil.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacio entonces le asigno el texto de que es obligatorio y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_telefonoMovil.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }

    private boolean validarDireccion() {
        if(spinner_direccion.getSelectedItem() != null) {
            return true;
        } else{
            return false;
        }
    }

}