package com.example.teleappsistencia.fragments.api.grupos;

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
import com.example.teleappsistencia.clases.Grupo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarGruposFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarGruposFragment extends Fragment {

    private Button btn_insertar;
    private EditText editText_nombre;

    public ModificarGruposFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InsertarGrupoFragment.
     */
    public static ModificarGruposFragment newInstance(String param1, String param2) {
        ModificarGruposFragment fragment = new ModificarGruposFragment();
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
        View view = inflater.inflate(R.layout.fragment_insertar_grupo, container, false);

        this.btn_insertar = (Button) view.findViewById(R.id.btn_guardar_grupo);
        this.editText_nombre = (EditText) view.findViewById(R.id.editText_nombre_grupo);

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarGrupo();
            }
        });

        return view;
    }

    private void insertarGrupo() {
        String nombre = this.editText_nombre.getText().toString();

        Grupo grupo = new Grupo(nombre);

        APIService apiService = Utils.inicializarApiService(getContext());
        Call<Grupo> call = apiService.addGrupo(grupo, "Bearer " + MainActivity.token.getAccess());
        call.enqueue(new Callback<Grupo>() {
            @Override
            public void onResponse(Call<Grupo> call, Response<Grupo> response) {
                if (response.isSuccessful()) {
                    Grupo grupo = response.body();
                    System.out.println(grupo);
                    Toast.makeText(getContext(), "Se ha añadido un nuevo grupo.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error al añadir el nuevo grupo. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Grupo> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }
}