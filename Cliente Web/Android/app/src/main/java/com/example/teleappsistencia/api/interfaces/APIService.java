package com.example.teleappsistencia.api.interfaces;

import com.example.teleappsistencia.api.clases.Direccion;
import com.example.teleappsistencia.api.clases.UsuarioSistema;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @Headers("Accept: application/json")
    @GET("api-rest/direccion")
    Call<List<Direccion>> getDirecciones();

    @GET("api-rest/direccion/{id}")
    Call<Direccion> getDireccionesByID(@Path("id") String id);

    @GET("api-rest/alarma?estado_alarma={estado}")
    Call<List<Direccion>> getAlarmasByEstado(@Path("estado") String estado);

    @POST("api-rest/direccion")
    Call<Direccion> postDireccion(@Body Direccion direccion);

    @DELETE("api-rest/direccion/{id}")
    Call<Direccion> deleteDireccion(@Path("id") int id);

    @GET("api-rest/users/{pk}")
    Call<UsuarioSistema> getUserByPK(@Path("pk") int pk);

}
