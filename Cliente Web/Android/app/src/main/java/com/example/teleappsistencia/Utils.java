package com.example.teleappsistencia;

import android.content.Context;

import com.example.teleappsistencia.ui.clases.Grupo;
import com.example.teleappsistencia.ui.clases.Token;
import com.example.teleappsistencia.ui.clases.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Utils {

    /**
     * Token para poder realizar las peticiones a la API.
     */
    private static Token token;

    /**
     * Método que recibe un número y si se encuentra entre 1 y 9 le añade un 0 delante.
     * Este método es utilizado para los meses y días de las fechas.
     * @param number Número al que hay que añadirle el 0 si cumple con los requisitos.
     * @return Devuelve el número como un String y con el 0 delante si cumple con los requisitos.
     */
    public static String twoDigitsDate(int number) {
        return (number<=9) ? ("0"+number) : String.valueOf(number);
    }

    /**
     * Método que recibe un objeto y un tipo y transforma el objeto en el tipo indicado.
     * @param lTM Objeto a transformar.
     * @param tipo Tipo al que se transformará el objeto.
     * @return Devuelve el objeto con el tipo ya cambiado.
     */
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

    /**
     * Método para inicializar el servicio de la API.
     * @param context Recibe un contexto para poder recoger el recurso String URL.
     * @return Devuelve el servicio de la API inicializado.
     */
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

    /* Getters y Setters del token */

    public static Token getToken() {
        return token;
    }

    public static void setToken(Token token) {
        Utils.token = token;
    }
}
