package com.example.teleappsistencia;

import com.example.teleappsistencia.clases.Tipo_vivienda;
import com.example.teleappsistencia.clases.Token;
import com.example.teleappsistencia.clases.UsuarioSistema;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("api/token/")
    public Call<Token> getToken(@Field("username") String username, @Field("password") String password);


    @GET("api-rest/tipo_vivienda")
    public Call<List<Tipo_vivienda>> getTipoVivienda();

    @GET("api-rest/tipo_vivienda/{id}")
    public Call<Tipo_vivienda> getTipoViviendaById(@Path("id") String id);

    @GET("api-rest/users")
    Call<List<UsuarioSistema>> getUserByUsername(@Query("username") String username);
}
