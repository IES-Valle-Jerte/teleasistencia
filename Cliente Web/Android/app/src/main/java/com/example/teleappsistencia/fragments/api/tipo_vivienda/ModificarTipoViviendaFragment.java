package com.example.teleappsistencia.fragments.api.tipo_vivienda;

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
import com.example.teleappsistencia.clases.TipoVivienda;
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
 * Use the {@link ModificarTipoViviendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarTipoViviendaFragment extends Fragment {

    private Button btn_insertar;
    private EditText editText_nombre_tipo_vivienda;


    public ModificarTipoViviendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InsertarTipoViviendaFragment.
     */
    public static ModificarTipoViviendaFragment newInstance() {
        ModificarTipoViviendaFragment fragment = new ModificarTipoViviendaFragment();
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

        this.editText_nombre_tipo_vivienda = (EditText) view.findViewById(R.id.editText_nombre_tipoVivienda);
        this.btn_insertar = (Button) view.findViewById(R.id.btn_guardar_tipoVivienda);

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarTipoVivienda();
            }
        });

        return view;
    }

    private void insertarTipoVivienda() {
        String nombre = this.editText_nombre_tipo_vivienda.getText().toString();

        TipoVivienda tipoVivienda = new TipoVivienda(nombre);


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

        Call<TipoVivienda> call = apiService.addTipoVivienda(tipoVivienda, "Bearer " + MainActivity.token.getAccess());
        call.enqueue(new Callback<TipoVivienda>() {
            @Override
            public void onResponse(Call<TipoVivienda> call, Response<TipoVivienda> response) {
                if (response.isSuccessful()) {
                    TipoVivienda tipo_vivienda = response.body();
                    System.out.println(tipo_vivienda);
                    Toast.makeText(getContext(), "Se ha añadido un nuevo tipo de vivienda.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Error al añadir el nuevo tipo de vivienda. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TipoVivienda> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });

    }
}