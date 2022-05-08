package com.example.teleappsistencia;

import android.content.Context;

import com.example.teleappsistencia.clases.Grupo;
import com.example.teleappsistencia.clases.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {

    public static String twoDigitsDate(int number) {
        return (number<=9) ? ("0"+number) : String.valueOf(number);
    }

    public static Object getObjeto(Object lTM, String tipo) {
        Gson gson = new Gson();
        Type type = null;
        Object objeto = null;
        switch(tipo){
            case "Usuario":
                type = new TypeToken<Usuario>(){}.getType();
                break;
            case "Grupo":
                type = new TypeToken<Grupo>(){}.getType();
                break;
        }
        if(type != null){
            objeto = gson.fromJson(gson.toJson(lTM), type);
        }
        return objeto;
    }

    public static APIService inicializarApiService(Context context) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").create();

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                //Si la conexión del servidor es lenta, no intenta de nuevo y evita una nueva petición (OKHTTP si la conexión es lenta, intenta de nuevo)
                .retryOnConnectionFailure(Boolean.FALSE)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getResources().getString(R.string.api_base_url))
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(APIService.class);
    }
}
