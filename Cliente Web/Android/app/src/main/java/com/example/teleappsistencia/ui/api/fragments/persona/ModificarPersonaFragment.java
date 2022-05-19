package com.example.teleappsistencia.ui.api.fragments.persona;

import android.app.DatePickerDialog;
import android.os.Bundle;
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

import androidx.fragment.app.Fragment;

import com.example.teleappsistencia.ui.api.APIService;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.ui.dialogs.AlertDialogBuilder;
import com.example.teleappsistencia.ui.utils.Utils;
import com.example.teleappsistencia.ui.api.ClienteRetrofit;
import com.example.teleappsistencia.ui.clases.Direccion;
import com.example.teleappsistencia.ui.clases.Persona;
import com.example.teleappsistencia.ui.dialogs.DatePickerFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarPersonaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarPersonaFragment extends Fragment {

    private Persona persona;

    private Button btn_insertar;
    private Button btn_volver;
    private EditText editText_nombre;
    private EditText editText_apellidos;
    private EditText editText_dni;
    private EditText editText_fechaNacimiento;
    private EditText editText_telefonoFijo;
    private EditText editText_telefonoMovil;
    private EditText editText_localidad;
    private EditText editText_provincia;
    private EditText editText_direccion;
    private EditText editText_codigoPostal;
    private Spinner spinner_sexo;

    private TextView textView_error_nombre;
    private TextView textView_error_apellidos;
    private TextView textView_error_dni;
    private TextView textView_error_fechaNacimiento;
    private TextView textView_error_telefonoFijo;
    private TextView textView_error_telefonoMovil;
    private TextView textView_error_localidad;
    private TextView textView_error_provincia;
    private TextView textView_error_direccion;
    private TextView textView_error_codigoPostal;

    public ModificarPersonaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param persona
     * @return A new instance of fragment InsertarPersonaFragment.
     */
    public static ModificarPersonaFragment newInstance(Persona persona) {
        ModificarPersonaFragment fragment = new ModificarPersonaFragment();
        Bundle args = new Bundle();
        args.putSerializable(Utils.OBJECT, persona);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            persona = (Persona) getArguments().getSerializable(Utils.OBJECT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modificar_persona, container, false);

        this.btn_insertar = view.findViewById(R.id.btn_guardar_persona);
        this.btn_volver = view.findViewById(R.id.btn_volver_persona);
        this.editText_nombre = view.findViewById(R.id.editText_nombre_persona);
        this.editText_apellidos = view.findViewById(R.id.editText_apellidos_persona);
        this.editText_dni = view.findViewById(R.id.editText_dni_persona);
        this.editText_fechaNacimiento = view.findViewById(R.id.editText_fechaNacimiento_persona);
        this.editText_telefonoFijo = view.findViewById(R.id.editText_telefonoFijo_persona);
        this.editText_telefonoMovil = view.findViewById(R.id.editText_telefonoMovil_persona);
        this.editText_localidad = (EditText) view.findViewById(R.id.editText_localidad_direccionPersona);
        this.editText_provincia = (EditText) view.findViewById(R.id.editText_provincia_direccionPersona);
        this.editText_direccion = (EditText) view.findViewById(R.id.editText_direccion_direccionPersona);
        this.editText_codigoPostal = (EditText) view.findViewById(R.id.editText_codigoPostal_direccionPersona);
        this.spinner_sexo = view.findViewById(R.id.spinner_sexo_persona);

        this.textView_error_nombre = view.findViewById(R.id.textView_error_nombre_persona);
        this.textView_error_apellidos = view.findViewById(R.id.textView_error_apellidos_persona);
        this.textView_error_dni = view.findViewById(R.id.textView_error_dni_persona);
        this.textView_error_fechaNacimiento = view.findViewById(R.id.textView_error_fechaNacimiento_persona);
        this.textView_error_telefonoFijo = view.findViewById(R.id.textView_error_telefonoFijo_persona);
        this.textView_error_telefonoMovil = view.findViewById(R.id.textView_error_telefonoMovil_persona);
        this.textView_error_localidad = (TextView) view.findViewById(R.id.textView_error_localidad_direccionPersona);
        this.textView_error_provincia = (TextView) view.findViewById(R.id.textView_error_provincia_direccionPersona);
        this.textView_error_direccion = (TextView) view.findViewById(R.id.textView_error_direccion_direccionPersona);
        this.textView_error_codigoPostal = (TextView) view.findViewById(R.id.textView_error_codigoPostal_direccionPersona);

        if(persona.getSexo().equalsIgnoreCase(getString(R.string.sexo_masculino))){
            this.spinner_sexo.setAdapter(new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, new String[]{getString(R.string.sexo_masculino), getString(R.string.sexo_femenimo)}));
        } else {
            this.spinner_sexo.setAdapter(new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, new String[]{getString(R.string.sexo_femenimo), getString(R.string.sexo_masculino)}));
        }

        rellenarDatos();

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarPersona()) {
                    modificarPersona();
                }
            }
        });

        this.btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        this.editText_fechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrarDatePickerDialog();
            }
        });

        rellenarDatos();

        inicializarListeners();

        return view;
    }

    private void rellenarDatos() {
        this.editText_nombre.setText(persona.getNombre());
        this.editText_apellidos.setText(persona.getApellidos());
        this.editText_dni.setText(persona.getDni());
        this.editText_fechaNacimiento.setText(persona.getFechaNacimiento());
        this.editText_telefonoFijo.setText(persona.getTelefonoFijo());
        this.editText_telefonoMovil.setText(persona.getTelefonoMovil());

        Direccion direccion = (Direccion) Utils.getObjeto(persona.getDireccion(), getString(R.string.direccion_class));
        this.editText_localidad.setText(direccion.getLocalidad());
        this.editText_provincia.setText(direccion.getProvincia());
        this.editText_direccion.setText(direccion.getDireccion());
        this.editText_codigoPostal.setText(direccion.getCodigoPostal());
    }

    private void mostrarDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                String selectedDate = year + "-" + Utils.twoDigitsDate(month + 1) + "-" + Utils.twoDigitsDate(day);
                editText_fechaNacimiento.setText(selectedDate);
            }
        });

        newFragment.show(getActivity().getSupportFragmentManager(), getString(R.string.tag_date_picker));
    }

    /**
     * Método para modificar una persona de la base de datos.
     * El método realiza una petición a la API con los datos proporcionados por el usuario.
     */
    private void modificarPersona() {
        String nombre = this.editText_nombre.getText().toString();
        String apellidos = this.editText_apellidos.getText().toString();
        String dni = editText_dni.getText().toString();
        String fechaNacimiento = editText_fechaNacimiento.getText().toString();
        String sexo = spinner_sexo.getSelectedItem().toString();
        String telefonoFijo = editText_telefonoFijo.getText().toString();
        String telefonoMovil = editText_telefonoMovil.getText().toString();

        String localidad = this.editText_localidad.getText().toString();
        String provincia = this.editText_provincia.getText().toString();
        String direc = this.editText_direccion.getText().toString();
        String codigoPostal = this.editText_codigoPostal.getText().toString();

        Direccion direccion = new Direccion(localidad, provincia, direc, codigoPostal);
        Persona persona = new Persona(nombre, apellidos, dni, fechaNacimiento, sexo, telefonoFijo, telefonoMovil, direccion);

        APIService apiService = ClienteRetrofit.getInstance().getAPIService();

        Call<Object> call = apiService.modifyPersona(this.persona.getId(), persona, "Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Object persona = response.body();
                    AlertDialogBuilder.crearInfoAlerDialog(getContext(), getString(R.string.infoAlertDialog_modificado_persona));
                    getActivity().onBackPressed();
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
     * Método que revisa si los datos de los EditText de persona son válidos.
     *
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

        if ((validNombre) && (validApellidos) && (validDni) && (validFechaNacimiento) && (validTelefonoFijo) && (validTelefonoMovil) && (validDireccion)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que revisa si los datos de los EditText de dirección son válidos.
     * @return Devuelve true si es válido de lo contrario devuelve false.
     */
    private boolean validarDireccion() {
        boolean validLocalidad, validProvincia, validDireccion, validCodigoPostal;

        validLocalidad = validarLocalidad(editText_localidad.getText().toString());
        validProvincia = validarProvincia(editText_provincia.getText().toString());
        validDireccion = validarDir(editText_direccion.getText().toString());
        validCodigoPostal = validarCodigoPostal(editText_codigoPostal.getText().toString());


        if((validLocalidad) && (validProvincia) && (validDireccion) && (validCodigoPostal)){
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
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarNombre(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        this.editText_apellidos.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarApellidos(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        this.editText_dni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarDni(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        this.editText_fechaNacimiento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarFechaNacimiento(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        this.editText_telefonoFijo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarTelefonoFijo(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        this.editText_telefonoMovil.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarTelefonoMovil(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        this.editText_localidad.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarLocalidad(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        this.editText_provincia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarProvincia(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        this.editText_direccion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarDir(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        this.editText_codigoPostal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarCodigoPostal(charSequence.toString());
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

    public boolean validarLocalidad(String localidad) {
        boolean valid = false;
        if ((localidad.isEmpty()) || (localidad.trim().equals(""))) {     // Reviso si la localidad está vacia.
            textView_error_localidad.setText(R.string.textview_localidad_obligatoria);
            textView_error_localidad.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacia entonces le asigno el texto de que es obligatoria y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_localidad.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }

    public boolean validarProvincia(String provincia) {
        boolean valid = false;
        if ((provincia.isEmpty()) || (provincia.trim().equals(""))) {     // Reviso si la provincia está vacia.
            textView_error_provincia.setText(R.string.textview_provincia_obligatoria);
            textView_error_provincia.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacia entonces le asigno el texto de que es obligatoria y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_provincia.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }

    public boolean validarDir(String direccion) {
        boolean valid = false;
        if ((direccion.isEmpty()) || (direccion.trim().equals(""))) {     // Reviso si la dirección está vacia.
            textView_error_direccion.setText(R.string.textview_direccion_obligatoria);
            textView_error_direccion.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacia entonces le asigno el texto de que es obligatoria y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_direccion.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }

    public boolean validarCodigoPostal(String codigoPostal) {
        boolean valid = false;
        if ((codigoPostal.isEmpty()) || (codigoPostal.trim().equals(""))) {     // Reviso si la dirección está vacia.
            textView_error_codigoPostal.setText(R.string.textview_codigoPostal_obligatoria);
            textView_error_codigoPostal.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacia entonces le asigno el texto de que es obligatoria y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_codigoPostal.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }
}