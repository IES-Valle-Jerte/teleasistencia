package com.example.teleappsistencia.ui.fragments.api.tipo_vivienda;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teleappsistencia.APIService;
import com.example.teleappsistencia.MainActivity;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.Utils;
import com.example.teleappsistencia.ui.clases.TipoVivienda;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InsertarTipoViviendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertarTipoViviendaFragment extends Fragment {

    private Button btn_insertar;
    private EditText editText_nombre_tipo_vivienda;
    private TextView textView_error_nombre;


    public InsertarTipoViviendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InsertarTipoViviendaFragment.
     */
    public static InsertarTipoViviendaFragment newInstance() {
        InsertarTipoViviendaFragment fragment = new InsertarTipoViviendaFragment();
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

        View view = inflater.inflate(R.layout.fragment_insertar_tipo_vivienda, container, false);

        this.btn_insertar = (Button) view.findViewById(R.id.btn_guardar_tipoVivienda);
        this.editText_nombre_tipo_vivienda = (EditText) view.findViewById(R.id.editText_nombre_tipoVivienda);
        this.textView_error_nombre = (TextView) view.findViewById(R.id.textView_error_nombre_tipoVivienda);

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarTipoVivienda()){
                    insertarTipoVivienda();
                }
            }
        });

        inicializarListeners();

        return view;
    }

    private void insertarTipoVivienda() {
        String nombre = this.editText_nombre_tipo_vivienda.getText().toString();

        TipoVivienda tipoVivienda = new TipoVivienda(nombre);

        APIService apiService = Utils.inicializarApiService(getContext());
        Call<Object> call = apiService.addTipoVivienda(tipoVivienda, "Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Object tipo_vivienda = response.body();
                    System.out.println(tipo_vivienda);
                    Toast.makeText(getContext(), "Se ha añadido un nuevo tipo de vivienda.", Toast.LENGTH_SHORT).show();
                    borrarEditTexts();
                } else {
                    Toast.makeText(getContext(), "Error al añadir el nuevo tipo de vivienda. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
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
        this.editText_nombre_tipo_vivienda.setText(getString(R.string.espacio_en_blanco));
        this.textView_error_nombre.setVisibility(View.GONE);
    }

    private boolean validarTipoVivienda() {
        boolean validNombre;

        validNombre = validarNombre(editText_nombre_tipo_vivienda.getText().toString());

        if(validNombre){
            return true;
        } else {
            return false;
        }
    }

    private void inicializarListeners() {
        this.editText_nombre_tipo_vivienda.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarNombre(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    public boolean validarNombre(String nombre) {
        boolean valid = false;
        if ((nombre.isEmpty()) || (nombre.trim().equals(""))) {     // Reviso si el nombre está vacio.
            textView_error_nombre.setText(R.string.textview_nombre_obligatorio);
            textView_error_nombre.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacia entonces le asigno el texto de que es obligatorio y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_nombre.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }
}