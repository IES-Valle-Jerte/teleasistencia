package com.example.teleappsistencia.servicios;

import com.example.teleappsistencia.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Clase que se encarga de crear una única instancia de la APIService.
 */
public class ClienteRetrofit {

    private static ClienteRetrofit clienteRetrofit = null;
    private APIService apiService;

    /**
     * Método que devuelve una instancia de esta clase y si no existe la crea.
     * @return
     */
    public static ClienteRetrofit getInstance() {
        if (clienteRetrofit == null) {
            clienteRetrofit = new ClienteRetrofit();
        }

        return clienteRetrofit;
    }

    private ClienteRetrofit() {
        buildRetrofit(Constantes.API_BASE_URL);
    }

    /**
     * Método encargado de crear Retrofit.
     * @param urlAPI
     */
    private void buildRetrofit(String urlAPI) {

        // Creación del clienteHttp para asignarlo a Retrofit.
        OkHttpClient clienteHttp =
                new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        // Si la conexión del servidor es lenta, no intenta de nuevo y evita una nueva petición (OKHTTP si la conexión es lenta, intenta de nuevo)
                        .retryOnConnectionFailure(Boolean.FALSE)
                        .build();

        // Creación del formateador de fechas de GSON.
        Gson gson = new GsonBuilder()
                .setDateFormat(Constantes.FORMATEADOR_API).create();

        // Creación del Retrofit encargado de realizar las peticiones a la API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .client(clienteHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // Creación del servicio a base de Retrofit.
        this.apiService = retrofit.create(APIService.class);

    }

    public APIService getAPIService() {
        return this.apiService;
    }

}