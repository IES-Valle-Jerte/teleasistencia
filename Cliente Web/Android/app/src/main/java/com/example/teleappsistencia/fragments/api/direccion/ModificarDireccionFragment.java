package com.example.teleappsistencia.fragments.api.direccion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.teleappsistencia.APIService;
import com.example.teleappsistencia.MainActivity;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.Utils;
import com.example.teleappsistencia.clases.Direccion;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarDireccionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarDireccionFragment extends Fragment {

    private Button btn_insertar;
    private EditText editText_localidad;
    private EditText editText_provincia;
    private EditText editText_direccion;
    private EditText editText_codigoPostal;

    public ModificarDireccionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InsertarDireccionFragment.
     */
    public static ModificarDireccionFragment newInstance() {
        ModificarDireccionFragment fragment = new ModificarDireccionFragment();
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
        View view = inflater.inflate(R.layout.fragment_insertar_direccion, container, false);

        this.btn_insertar = (Button) view.findViewById(R.id.btn_guardar_direccion);
        this.editText_localidad = (EditText) view.findViewById(R.id.editText_localidad_direccion);
        this.editText_provincia = (EditText) view.findViewById(R.id.editText_provincia_direccion);
        this.editText_direccion = (EditText) view.findViewById(R.id.editText_direccion_direccion);
        this.editText_codigoPostal = (EditText) view.findViewById(R.id.editText_codigoPostal_direccion);

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarDireccion();
            }
        });

        return view;
    }

    private void insertarDireccion() {
        String localidad = this.editText_localidad.getText().toString();
        String provincia = this.editText_provincia.getText().toString();
        String direc = this.editText_direccion.getText().toString();
        String codigoPostal = this.editText_codigoPostal.getText().toString();

        Direccion direccion = new Direccion(localidad, provincia, direc, codigoPostal);

        APIService apiService = Utils.inicializarApiService(getContext());
        Call<Direccion> call = apiService.addDireccion(direccion, "Bearer " + MainActivity.token.getAccess());
        call.enqueue(new Callback<Direccion>() {
            @Override
            public void onResponse(Call<Direccion> call, Response<Direccion> response) {
                if (response.isSuccessful()) {
                    Direccion direccion = response.body();
                    System.out.println(direccion);
                    Toast.makeText(getContext(), "Se ha añadido una nueva dirección.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error al añadir la nueva dirección. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Direccion> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }
}