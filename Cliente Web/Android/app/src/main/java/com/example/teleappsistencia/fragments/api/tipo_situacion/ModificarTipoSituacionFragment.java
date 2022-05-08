package com.example.teleappsistencia.fragments.api.tipo_situacion;

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
import com.example.teleappsistencia.clases.TipoSituacion;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarTipoSituacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarTipoSituacionFragment extends Fragment {

    private Button btn_insertar;
    private EditText editText_nombre_tipo_situacion;

    public ModificarTipoSituacionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InsertarTipoSituacionFragment.
     */
    public static ModificarTipoSituacionFragment newInstance() {
        ModificarTipoSituacionFragment fragment = new ModificarTipoSituacionFragment();
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
        View view = inflater.inflate(R.layout.fragment_insertar_tipo_situacion, container, false);

        this.editText_nombre_tipo_situacion = (EditText) view.findViewById(R.id.editText_nombre_tipoSituacion);
        this.btn_insertar = (Button) view.findViewById(R.id.btn_guardar_tipoSituacion);

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarTipoSituacion();
            }
        });

        return view;
    }

    private void insertarTipoSituacion() {
        String nombre = this.editText_nombre_tipo_situacion.getText().toString();

        TipoSituacion tipoSituacion = new TipoSituacion(nombre);


        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                //Si la conexión del servidor es lenta, no intenta de nuevo y evita una nueva petición (OKHTTP si la conexión es lenta, intenta de nuevo)
                .retryOnConnectionFailure(Boolean.FALSE)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getResources().getString(R.string.api_base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIService apiService = retrofit.create(APIService.class);

        Call<TipoSituacion> call = apiService.addTipoSituacion(tipoSituacion, "Bearer " + MainActivity.token.getAccess());
        call.enqueue(new Callback<TipoSituacion>() {
            @Override
            public void onResponse(Call<TipoSituacion> call, Response<TipoSituacion> response) {
                if (response.isSuccessful()) {
                    TipoSituacion tipoSituacion = response.body();
                    System.out.println(tipoSituacion);
                    Toast.makeText(getContext(), "Se ha añadido un nuevo tipo de situación.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error al añadir el nuevo tipo de situación. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TipoSituacion> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });

    }
}