package com.example.teleappsistencia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.teleappsistencia.clases.Token;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private Button btn_iniciar_sesion;
    private EditText editText_usuario;
    private EditText editText_password;

    private Token token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.token = null;
        this.btn_iniciar_sesion = (Button) findViewById(R.id.btn_iniciar_sesion);
        this.editText_usuario = (EditText) findViewById(R.id.editText_usuario);
        this.editText_password = (EditText) findViewById(R.id.editText_password);

        this.btn_iniciar_sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                peticionToken();
            }
        });
    }

    public void peticionToken() {
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

        Call < Token > call = apiService.getToken(this.editText_usuario.getText().toString(), this.editText_password.getText().toString());
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.isSuccessful()){
                    token = response.body();
                    System.out.println("\n" + token + "\n");
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    intent.putExtra("token", token);
                    intent.putExtra("usuario", editText_usuario.getText().toString());
                    startActivity(intent);
                } else{
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