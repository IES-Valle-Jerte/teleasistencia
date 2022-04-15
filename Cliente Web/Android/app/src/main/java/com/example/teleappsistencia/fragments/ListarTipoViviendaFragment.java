package com.example.teleappsistencia.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
import com.example.teleappsistencia.clases.Tipo_vivienda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListarTipoViviendaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarTipoViviendaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView textView_listar_tipo_vivienda;

    private static List<Tipo_vivienda> listaTiposViviendas;
    public static boolean listaActualizada = false;

    public ListarTipoViviendaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListarTipoViviendaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListarTipoViviendaFragment newInstance(String param1, String param2) {
        ListarTipoViviendaFragment fragment = new ListarTipoViviendaFragment();
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

        View view = inflater.inflate(R.layout.fragment_listar_tipo_vivienda, container, false);

        this.textView_listar_tipo_vivienda = (TextView) view.findViewById(R.id.textView_listar_tipo_vivienda);
        if((listaTiposViviendas == null) || (!listaActualizada)) {
            listarTipoVivienda();
        } else{
            this.textView_listar_tipo_vivienda.setText(listaTiposViviendas.toString());
        }
        // Inflate the layout for this fragment
        return view;
    }

    private void listarTipoVivienda() {

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

        Call<List<Tipo_vivienda>> call = apiService.getTipoVivienda("Bearer " + MainActivity.token.getAccess());
        call.enqueue(new Callback<List<Tipo_vivienda>>() {
            @Override
            public void onResponse(Call<List<Tipo_vivienda>> call, Response<List<Tipo_vivienda>> response) {
                if (response.isSuccessful()) {
                    listaTiposViviendas = response.body();
                    listaActualizada = true;
                    textView_listar_tipo_vivienda.setText(listaTiposViviendas.toString());
                } else {
                    Toast.makeText(getContext(), "Error al listar los tipos de vivienda. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Tipo_vivienda>> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });

    }
}