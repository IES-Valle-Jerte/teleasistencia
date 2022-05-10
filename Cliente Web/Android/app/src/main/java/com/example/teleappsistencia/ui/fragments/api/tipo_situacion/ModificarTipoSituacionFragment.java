package com.example.teleappsistencia.ui.fragments.api.tipo_situacion;

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
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.Utils;
import com.example.teleappsistencia.ui.clases.TipoSituacion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarTipoSituacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarTipoSituacionFragment extends Fragment {

    private int id;

    private Button btn_insertar;
    private EditText editText_nombre_tipo_situacion;
    private TextView textView_error_nombre;

    public ModificarTipoSituacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id
     * @return A new instance of fragment InsertarTipoSituacionFragment.
     */
    public static ModificarTipoSituacionFragment newInstance(int id) {
        ModificarTipoSituacionFragment fragment = new ModificarTipoSituacionFragment();
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
        View view = inflater.inflate(R.layout.fragment_modificar_tipo_situacion, container, false);

        this.editText_nombre_tipo_situacion = (EditText) view.findViewById(R.id.editText_nombre_tipoSituacion);
        this.btn_insertar = (Button) view.findViewById(R.id.btn_guardar_tipoSituacion);
        this.textView_error_nombre = (TextView) view.findViewById(R.id.textView_error_nombre_tipoSituacion);

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarTipoSituacion()) {
                    modificarTipoSituacion();
                }
            }
        });

        inicializarListeners();

        return view;
    }

    private void modificarTipoSituacion() {
        String nombre = this.editText_nombre_tipo_situacion.getText().toString();

        TipoSituacion tipoSituacion = new TipoSituacion(nombre);

        APIService apiService = Utils.inicializarApiService(getContext());

        Call<Object> call = apiService.modifyTipoSituacion(id, tipoSituacion, "Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Object tipoSituacion = response.body();
                    System.out.println(tipoSituacion);
                    Toast.makeText(getContext(), "Se ha modificado el tipo de situación.", Toast.LENGTH_SHORT).show();
                    borrarEditTexts();
                } else {
                    Toast.makeText(getContext(), "Error al modificar el tipo de situación. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
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
        this.editText_nombre_tipo_situacion.setText(getString(R.string.espacio_en_blanco));
        this.textView_error_nombre.setVisibility(View.GONE);
    }

    private boolean validarTipoSituacion() {
        boolean validNombre;

        validNombre = validarNombre(editText_nombre_tipo_situacion.getText().toString());

        if(validNombre){
            return true;
        } else {
            return false;
        }
    }

    private void inicializarListeners() {
        this.editText_nombre_tipo_situacion.addTextChangedListener(new TextWatcher() {
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