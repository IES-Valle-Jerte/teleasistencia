package com.example.teleappsistencia.fragments.tipo_vivienda;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
 * Use the {@link InsertarTipoViviendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertarTipoViviendaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btn_insertar_tipo_vivienda;
    private EditText editText_nombre_tipo_vivienda;


    public InsertarTipoViviendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InsertarTipoViviendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertarTipoViviendaFragment newInstance(String param1, String param2) {
        InsertarTipoViviendaFragment fragment = new InsertarTipoViviendaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_insertar_tipo_vivienda, container, false);

        this.editText_nombre_tipo_vivienda = (EditText) view.findViewById(R.id.editText_nombre_tipo_vivienda);
        this.btn_insertar_tipo_vivienda = (Button) view.findViewById(R.id.btn_insertar_tipo_vivienda);

        this.btn_insertar_tipo_vivienda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarTipoVivienda();
            }
        });

        return view;
    }

    private void insertarTipoVivienda(){
        if(!editText_nombre_tipo_vivienda.getText().toString().trim().equalsIgnoreCase("")) {

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

            OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .readTimeout(30, TimeUnit.SECONDS)
                    //Si la conexión del servidor es lenta, no intenta de nuevo y evita una nueva petición (OKHTTP si la conexión es lenta, intenta de nuevo)
                    .retryOnConnectionFailure(Boolean.FALSE)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://127.0.0.1:3333/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            APIService apiService = retrofit.create(APIService.class);

            Call<TipoVivienda> call = apiService.addTipoVivienda(editText_nombre_tipo_vivienda.getText().toString(), "Bearer " + MainActivity.token.getAccess());
            call.enqueue(new Callback<TipoVivienda>() {
                @Override
                public void onResponse(Call<TipoVivienda> call, Response<TipoVivienda> response) {
                    if (response.isSuccessful()) {
                        TipoVivienda tipo_vivienda = response.body();
                        System.out.println(tipo_vivienda);
                        ListarTipoViviendaFragment.listaActualizada = false;
                        Toast.makeText(getContext(), "Se ha añadido un nuevo tipo de vivienda.", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(getContext(), "Error al añadir el nuevo tipo de vivienda. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<TipoVivienda> call, Throwable t) {
                    t.printStackTrace();
                    System.out.println(t.getMessage());
                }
            });
        } else{
            Toast.makeText(getContext(), "El nombre no debe estar vacio.", Toast.LENGTH_SHORT).show();
        }
    }
}