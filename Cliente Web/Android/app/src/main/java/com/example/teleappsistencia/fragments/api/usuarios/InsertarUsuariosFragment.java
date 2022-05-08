package com.example.teleappsistencia.fragments.api.usuarios;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teleappsistencia.APIService;
import com.example.teleappsistencia.MainActivity;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.clases.Grupo;
import com.example.teleappsistencia.clases.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
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
 * Use the {@link InsertarUsuariosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InsertarUsuariosFragment extends Fragment {

    private Button btn_insertar;
    private EditText editText_nombreUsuario;
    private EditText editText_password;
    private EditText editText_nombre;
    private EditText editText_apellidos;
    private EditText editText_email;
    private Spinner spinner_grupos;

    public InsertarUsuariosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment InsertarUsuariosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InsertarUsuariosFragment newInstance() {
        InsertarUsuariosFragment fragment = new InsertarUsuariosFragment();
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
        View view = inflater.inflate(R.layout.fragment_insertar_usuarios, container, false);

        this.btn_insertar = (Button) view.findViewById(R.id.btn_guardar_usuario);
        this.editText_nombreUsuario = (EditText) view.findViewById(R.id.editText_nombreUsuario_usuario);
        this.editText_password = (EditText) view.findViewById(R.id.editText_password_usuario);
        this.editText_nombre = (EditText) view.findViewById(R.id.editText_nombre_usuario);
        this.editText_apellidos = (EditText) view.findViewById(R.id.editText_apellidos_usuario);
        this.editText_email = (EditText) view.findViewById(R.id.editText_email_usuario);
        this.spinner_grupos = (Spinner) view.findViewById(R.id.spinner_grupos_usuario);

        inicializarSpinnerGrupos();

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertarUsuario();
            }
        });

        return view;
    }

    private void inicializarSpinnerGrupos() {
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

        Call<List<Grupo>> call = apiService.getGrupos("Bearer " + MainActivity.token.getAccess());
        call.enqueue(new Callback<List<Grupo>>() {
            @Override
            public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {
                if (response.isSuccessful()) {
                    List<Grupo> grupos = response.body();
                    System.out.println(grupos);
                    spinner_grupos.setAdapter(new ArrayAdapter<Grupo>(getContext(), R.layout.support_simple_spinner_dropdown_item, grupos));
                }
            }

            @Override
            public void onFailure(Call<List<Grupo>> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    private void insertarUsuario() {
        String password = this.editText_password.getText().toString();
        String username = this.editText_nombreUsuario.getText().toString();
        String firstName = this.editText_nombre.getText().toString();
        String lastName = this.editText_apellidos.getText().toString();
        String email = this.editText_email.getText().toString();
        Grupo group = (Grupo) this.spinner_grupos.getSelectedItem();

        Usuario usuario = new Usuario();

        usuario.setPassword(password);
        usuario.setUsername(username);
        usuario.setFirstName(firstName);
        usuario.setLastName(lastName);
        usuario.setEmail(email);
        usuario.setGroups(group.getPk());

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

        Call<Object> call = apiService.addUsuario(usuario, "Bearer " + MainActivity.token.getAccess());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Object usuario = response.body();
                    System.out.println(usuario);
                    if(usuario instanceof String){
                        Toast.makeText(getContext(), usuario.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Se ha añadido un nuevo usuario.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Error al añadir el nuevo usuario. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }
}