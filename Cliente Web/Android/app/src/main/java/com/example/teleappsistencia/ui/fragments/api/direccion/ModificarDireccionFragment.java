package com.example.teleappsistencia.ui.fragments.api.direccion;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.teleappsistencia.APIService;
import com.example.teleappsistencia.MainActivity;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.Utils;
import com.example.teleappsistencia.ui.clases.Direccion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarDireccionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarDireccionFragment extends Fragment {

    private int id;

    private Button btn_insertar;
    private EditText editText_localidad;
    private EditText editText_provincia;
    private EditText editText_direccion;
    private EditText editText_codigoPostal;

    private TextView textView_error_localidad;
    private TextView textView_error_provincia;
    private TextView textView_error_direccion;
    private TextView textView_error_codigoPostal;

    public ModificarDireccionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id
     * @return A new instance of fragment InsertarDireccionFragment.
     */
    public static ModificarDireccionFragment newInstance(int id) {
        ModificarDireccionFragment fragment = new ModificarDireccionFragment();
        Bundle args = new Bundle();
        args.putInt(fragment.getString(R.string.id), id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            id = getArguments().getInt(getString(R.string.id), 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modificar_direccion, container, false);

        this.btn_insertar = (Button) view.findViewById(R.id.btn_guardar_direccion);
        this.editText_localidad = (EditText) view.findViewById(R.id.editText_localidad_direccion);
        this.editText_provincia = (EditText) view.findViewById(R.id.editText_provincia_direccion);
        this.editText_direccion = (EditText) view.findViewById(R.id.editText_direccion_direccion);
        this.editText_codigoPostal = (EditText) view.findViewById(R.id.editText_codigoPostal_direccion);

        this.textView_error_localidad = (TextView) view.findViewById(R.id.textView_error_localidad_direccion);
        this.textView_error_provincia = (TextView) view.findViewById(R.id.textView_error_provincia_direccion);
        this.textView_error_direccion = (TextView) view.findViewById(R.id.textView_error_direccion_direccion);
        this.textView_error_codigoPostal = (TextView) view.findViewById(R.id.textView_error_codigoPostal_direccion);

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarDireccion()) {
                    insertarDireccion();
                }
            }
        });

        inicializarListeners();

        return view;
    }

    private void insertarDireccion() {
        String localidad = this.editText_localidad.getText().toString();
        String provincia = this.editText_provincia.getText().toString();
        String direc = this.editText_direccion.getText().toString();
        String codigoPostal = this.editText_codigoPostal.getText().toString();

        Direccion direccion = new Direccion(localidad, provincia, direc, codigoPostal);

        APIService apiService = Utils.inicializarApiService(getContext());
        Call<Object> call = apiService.modifyDireccion(id, direccion, "Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Object direccion = response.body();
                    System.out.println(direccion);
                    Toast.makeText(getContext(), "Se ha modificado la dirección.", Toast.LENGTH_SHORT).show();
                    borrarEditTexts();
                } else {
                    Toast.makeText(getContext(), "Error al modificar la dirección. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    private void borrarEditTexts() {
        this.editText_localidad.setText(getString(R.string.espacio_en_blanco));
        this.editText_provincia.setText(getString(R.string.espacio_en_blanco));
        this.editText_direccion.setText(getString(R.string.espacio_en_blanco));
        this.editText_codigoPostal.setText(getString(R.string.espacio_en_blanco));

        this.textView_error_localidad.setVisibility(View.GONE);
        this.textView_error_provincia.setVisibility(View.GONE);
        this.textView_error_direccion.setVisibility(View.GONE);
        this.textView_error_codigoPostal.setVisibility(View.GONE);
    }

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

    private void inicializarListeners() {
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