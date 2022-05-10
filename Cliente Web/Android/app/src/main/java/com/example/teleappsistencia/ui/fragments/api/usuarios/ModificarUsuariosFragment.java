package com.example.teleappsistencia.ui.fragments.api.usuarios;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.teleappsistencia.APIService;
import com.example.teleappsistencia.R;
import com.example.teleappsistencia.Utils;
import com.example.teleappsistencia.ui.clases.Grupo;
import com.example.teleappsistencia.ui.clases.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ModificarUsuariosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModificarUsuariosFragment extends Fragment {

    private int id;

    private Button btn_insertar;
    private EditText editText_nombreUsuario;
    private EditText editText_password;
    private EditText editText_email;
    private Spinner spinner_grupo;

    private TextView textView_error_nombreUsuario;
    private TextView textView_error_password;
    private TextView textView_error_email;

    public ModificarUsuariosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param id
     * @return A new instance of fragment InsertarUsuariosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModificarUsuariosFragment newInstance(int id) {
        ModificarUsuariosFragment fragment = new ModificarUsuariosFragment();
        Bundle args = new Bundle();
        args.putInt(fragment.getString(R.string.id), id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            id = getArguments().getInt("ID", 0);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modificar_usuarios, container, false);

        this.btn_insertar = (Button) view.findViewById(R.id.btn_guardar_usuario);
        this.editText_nombreUsuario = (EditText) view.findViewById(R.id.editText_nombreUsuario_usuario);
        this.editText_password = (EditText) view.findViewById(R.id.editText_password_usuario);
        this.editText_email = (EditText) view.findViewById(R.id.editText_email_usuario);
        this.spinner_grupo = (Spinner) view.findViewById(R.id.spinner_grupos_usuario);

        this.textView_error_nombreUsuario = (TextView) view.findViewById(R.id.textView_error_nombreUsuario_usuario);
        this.textView_error_password = (TextView) view.findViewById(R.id.textView_error_password_usuario);
        this.textView_error_email = (TextView) view.findViewById(R.id.textView_error_email_usuario);

        inicializarSpinnerGrupos();

        this.btn_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarUsuario()){
                    modificarUsuario();
                }
            }
        });

        inicializarListeners();

        return view;
    }

    private void inicializarSpinnerGrupos() {
        APIService apiService = Utils.inicializarApiService(getContext());

        Call<List<Grupo>> call = apiService.getGrupos("Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<List<Grupo>>() {
            @Override
            public void onResponse(Call<List<Grupo>> call, Response<List<Grupo>> response) {
                if (response.isSuccessful()) {
                    List<Grupo> grupos = response.body();
                    System.out.println(grupos);
                    spinner_grupo.setAdapter(new ArrayAdapter<Grupo>(getContext(), R.layout.support_simple_spinner_dropdown_item, grupos));
                }
            }

            @Override
            public void onFailure(Call<List<Grupo>> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }

    private void modificarUsuario() {
        String password = this.editText_password.getText().toString();
        String username = this.editText_nombreUsuario.getText().toString();
        String email = this.editText_email.getText().toString();
        Grupo group = (Grupo) this.spinner_grupo.getSelectedItem();

        Usuario usuario = new Usuario();

        usuario.setPassword(password);
        usuario.setUsername(username);
        usuario.setEmail(email);
        usuario.setGroups(group.getPk());

        APIService apiService = Utils.inicializarApiService(getContext());

        Call<Object> call = apiService.modifyUsuario(id, usuario, "Bearer " + Utils.getToken().getAccess());
        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if (response.isSuccessful()) {
                    Object usuario = response.body();
                    System.out.println(usuario);
                    if(usuario instanceof String){
                        Toast.makeText(getContext(), usuario.toString(), Toast.LENGTH_SHORT).show();
                    } else {
                        borrarEditTexts();
                        Toast.makeText(getContext(), "Se ha modificado el usuario.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Error al modificar el usuario. Código de error: " + response.code(), Toast.LENGTH_SHORT).show();
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
        this.editText_nombreUsuario.setText(getString(R.string.espacio_en_blanco));
        this.editText_password.setText(getString(R.string.espacio_en_blanco));
        this.editText_email.setText(getString(R.string.espacio_en_blanco));

        this.textView_error_nombreUsuario.setVisibility(View.GONE);
        this.textView_error_password.setVisibility(View.GONE);
        this.textView_error_email.setVisibility(View.GONE);
    }

    private boolean validarUsuario() {
        boolean validNombreUsuario, validPassword, validEmail, validGrupo;

        validNombreUsuario = validarNombreUsuario(editText_nombreUsuario.getText().toString());
        validPassword = validarPassword(editText_password.getText().toString());
        validEmail = validarEmail(editText_email.getText().toString());
        validGrupo = validarGrupo();

        if((validNombreUsuario) && (validPassword) && (validEmail) && (validGrupo)){
            return true;
        } else {
            return false;
        }
    }

    private void inicializarListeners() {
        this.editText_nombreUsuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarNombreUsuario(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        this.editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarPassword(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        this.editText_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarEmail(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    public boolean validarNombreUsuario(String userName) {
        boolean valid = false;
        if ((userName.isEmpty() || (userName.trim().equals("")))) {    // Reviso si el nombre de usuario está vacio.
            textView_error_nombreUsuario.setText(getResources().getString(R.string.textview_nombre_usuario_obligatorio));
            textView_error_nombreUsuario.setVisibility(View.VISIBLE);
            valid = false;                  // Si está vacio entonces el texto de que es obligatorio y devuelvo false.
        } else {
            if (userName.length() < 4) {    // Si no está vacio reviso si el nombre de usuario tiene menos de 4 carácteres.
                textView_error_nombreUsuario.setText(getResources().getString(R.string.textview_longitud_minima_nombre_usuario));
                textView_error_nombreUsuario.setVisibility(View.VISIBLE);
                valid = false;              // Si tiene menos de 4, le asigno el texto de que la longitud que tiene que tener y devuelvo false.
            } else {
                textView_error_nombreUsuario.setVisibility(View.GONE);
                valid = true;               // Si tiene más de 4, entonces devuelvo true.
            }
        }
        return valid;
    }

    public boolean validarPassword(String password) {
        boolean valid = false;
        if ((password.isEmpty()) || (password.trim().equals(""))) {     // Reviso si la contraseña está vacia.
            textView_error_password.setText(R.string.textview_password_obligatoria);
            textView_error_password.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacia entonces le asigno el texto de que es obligatoria y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_password.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }

    public boolean validarEmail(String email) {
        boolean valid = false;
        if ((email.isEmpty()) || (email.trim().equals(""))) {     // Reviso si el email está vacio.
            textView_error_email.setText(R.string.textview_email_obligatorio);
            textView_error_email.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacio entonces le asigno el texto de que es obligatorio y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView desaparezca.
            textView_error_email.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }

    private boolean validarGrupo() {
        if(spinner_grupo.getSelectedItem() != null) {
            return true;
        } else{
            return false;
        }
    }
}