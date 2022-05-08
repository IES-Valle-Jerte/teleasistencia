package com.example.teleappsistencia.fragments.api.usuarios;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teleappsistencia.APIService;
import com.example.teleappsistencia.MainActivity;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.Utils;
import com.example.teleappsistencia.clases.Usuario;
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
 * Use the {@link ListarUsuariosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListarUsuariosFragment extends Fragment {

    private TextView textView_listar_usuarios;
    private ProgressBar progressBar;

    public ListarUsuariosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ListarUsuariosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListarUsuariosFragment newInstance() {
        ListarUsuariosFragment fragment = new ListarUsuariosFragment();
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
        View view = inflater.inflate(R.layout.fragment_listar_usuarios, container, false);

        this.textView_listar_usuarios = (TextView) view.findViewById(R.id.textView_listar_usuarios);
        this.progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        listarUsuarios();
        return view;
    }

    private void listarUsuarios() {
        APIService apiService = Utils.inicializarApiService(getContext());

        Call<List<Usuario>> call = apiService.getUsuarios("Bearer " + MainActivity.token.getAccess());
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {
                    textView_listar_usuarios.setText(response.body().toString());
                    progressBar.setVisibility(View.GONE);
                    textView_listar_usuarios.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(getContext(), "Error al listar los usuarios. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }
}