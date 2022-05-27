package com.example.teleappsistencia.servicios;

import com.example.teleappsistencia.utilidades.Constantes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteRetrofit {

    private static ClienteRetrofit clienteRetrofit = null;
    private APIService apiService;

    public static ClienteRetrofit getInstance() {
        if (clienteRetrofit == null) {
            clienteRetrofit = new ClienteRetrofit();
        }

        return clienteRetrofit;
    }

    private ClienteRetrofit() {
        buildRetrofit(Constantes.BASE_URL);
    }

    private void buildRetrofit(String urlAPI) {

        OkHttpClient clienteHttp =
                new OkHttpClient.Builder().connectTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        // Si la conexión del servidor es lenta, no intenta de nuevo y evita una nueva petición (OKHTTP si la conexión es lenta, intenta de nuevo)
                        .retryOnConnectionFailure(Boolean.FALSE)
                        .build();

        Gson gson = new GsonBuilder()
                .setDateFormat(Constantes.FORMATO_FECHAS_RETROFIT).create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(urlAPI)
                .client(clienteHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        // Build your services once
        this.apiService = retrofit.create(APIService.class);

    }

    public APIService getAPIService() {
        return this.apiService;
    }
}