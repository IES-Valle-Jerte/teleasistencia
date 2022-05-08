package com.example.teleappsistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.teleappsistencia.clases.Token;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btn_iniciar_sesion;
    private EditText editText_usuario;
    private EditText editText_password;
    private TextView textView_error_usuario;
    private TextView textView_error_password;

    private Token token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.token = null;
        this.btn_iniciar_sesion = (Button) findViewById(R.id.btn_iniciar_sesion);
        this.editText_usuario = (EditText) findViewById(R.id.editText_usuario);
        this.editText_password = (EditText) findViewById(R.id.editText_password);
        this.textView_error_usuario = (TextView) findViewById(R.id.textView_error_nombre_usuario);
        this.textView_error_password = (TextView) findViewById(R.id.textView_error_password);

        textView_error_usuario.setVisibility(View.GONE);
        textView_error_password.setVisibility(View.GONE);

        this.btn_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean usuarioValido, passwordValido;
                if (validarNombreUsuario(editText_usuario.getText().toString())) {
                    usuarioValido = true;
                } else {
                    usuarioValido = false;
                }
                if (validarPassword(editText_password.getText().toString())) {
                    passwordValido = true;
                } else {
                    passwordValido = false;
                }

                if((usuarioValido) && (passwordValido)){
                    peticionToken();
                }
            }
        });

        // Añado un TextWatcher para mostrar los errores del nombre del usuario.
        this.editText_usuario.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarNombreUsuario(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // Añadimos un TextWatcher para mostrar los errores de la contraseña.
        this.editText_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int count, int after) {
                validarPassword(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    /**
     * Método que recibe un nombre de usuario y comprueba si es válido.
     *
     * @param userName Nombre del usuario.
     * @return Retorna true si es válido o de lo contrario retornará false.
     */
    public boolean validarNombreUsuario(String userName) {
        boolean valid = false;
        if ((userName.isEmpty() || (userName.trim().equals("")))) {    // Reviso si el nombre de usuario está vacio.
            textView_error_usuario.setText(getResources().getString(R.string.textview_nombre_usuario_obligatorio));
            textView_error_usuario.setVisibility(View.VISIBLE);
            valid = false;                  // Si está vacio entonces le asigno al textView_error_usuario el texto de que es obligatorio el nombre de usuario y devuelvo false.
        } else {
            if (userName.length() < 4) {    // Si no está vacio reviso si el nombre de usuario tiene menos de 4 carácteres.
                textView_error_usuario.setText(getResources().getString(R.string.textview_longitud_minima_nombre_usuario));
                textView_error_usuario.setVisibility(View.VISIBLE);
                valid = false;              // Si tiene menos de 4, le asigno al textView_error_usuario el texto de la longitud que tiene que tener el nombre del usuario y devuelvo false.
            } else {
                textView_error_usuario.setVisibility(View.GONE);
                valid = true;               // Si tiene más de 4, entonces devuelvo true.
            }
        }
        return valid;
    }

    /**
     * Método que recibe una contraseña y comprueba si es válida.
     * @param password
     * @return
     */
    public boolean validarPassword(String password) {
        boolean valid = false;
        if ((password.isEmpty()) || (password.trim().equals(""))) {     // Reviso si la contraseña está vacia.
            textView_error_password.setText(R.string.textview_password_obligatoria);
            textView_error_password.setVisibility(View.VISIBLE);
            valid = false;                                              // Si está vacia entonces le asigno al textView_error_password el texto de que es obligatoria y devuelvo false.
        } else {                                                        // De lo contrario devuelvo true y hago que el textView_error_password desaparezca.
            textView_error_password.setVisibility(View.GONE);
            valid = true;
        }
        return valid;
    }

    /**
     * Método que realiza una petición a la API, enviandole el nombre de usuario y la contraseña.
     * Si se recibe el token correctamente se abrirá la MainActivity, de lo contrario mostrará un Toast con el error ocurrido.
     */
    public void peticionToken() {
        APIService apiService = Utils.inicializarApiService(getBaseContext());

        Call<Token> call = apiService.getToken(this.editText_usuario.getText().toString(), this.editText_password.getText().toString());
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if (response.isSuccessful()) {
                    token = response.body();
                    System.out.println("\n" + token + "\n");
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("token", token);
                    intent.putExtra("usuario", editText_usuario.getText().toString());
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, getResources().getString(R.string.toast_activity_login), Toast.LENGTH_SHORT).show();
                    System.out.println(response.message());
                    System.out.println(response.body());
                    System.out.println(response.raw());
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                t.printStackTrace();
                System.out.println(t.getMessage());
            }
        });
    }
}