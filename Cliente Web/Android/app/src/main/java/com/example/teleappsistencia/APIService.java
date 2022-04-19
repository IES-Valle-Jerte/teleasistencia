package com.example.teleappsistencia;

import com.example.teleappsistencia.clases.Direccion;
import com.example.teleappsistencia.clases.DispositivoAuxiliar;
import com.example.teleappsistencia.clases.Grupo;
import com.example.teleappsistencia.clases.HistoricoTipoSituacion;
import com.example.teleappsistencia.clases.Persona;
import com.example.teleappsistencia.clases.TipoSituacion;
import com.example.teleappsistencia.clases.TipoVivienda;
import com.example.teleappsistencia.clases.Token;
import com.example.teleappsistencia.clases.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    // Peticiones del Token

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("api/token/")
    public Call<Token> getToken(@Field("username") String username, @Field("password") String password);

    // Peticiones de Direccion

    @GET("api-rest/direccion")
    public Call<List<Direccion>> getDirecciones(@Header("Authorization") String token);

    @GET("api-rest/direccion")
    public Call<Direccion> getDireccionById(@Query("id") String id, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api-rest/direccion")
    public Call<Direccion> addDireccion(@Body Direccion direccion, @Header("Authorization") String token);


    // Peticiones de Dispositivos Auxiliares

    @GET("api-rest/dispositivos_auxiliares_en_terminal")
    public Call<List<DispositivoAuxiliar>> getDispositivosAuxiliares(@Header("Authorization") String token);

    @GET("api-rest/dispositivos_auxiliares_en_terminal")
    public Call<DispositivoAuxiliar> getDispositivoAuxiliarById(@Query("id") String id, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api-rest/dispositivos_auxiliares_en_terminal")
    public Call<DispositivoAuxiliar> addDispositivoAuxiliar(@Body DispositivoAuxiliar dispositivoAuxiliar, @Header("Authorization") String token);


    // Peticiones de Grupo

    @GET("api-rest/groups")
    public Call<List<Grupo>> getGrupos(@Header("Authorization") String token);

    @GET("api-rest/groups")
    public Call<Grupo> getGrupoByPk(@Query("pk") String pk, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api-rest/groups")
    public Call<Grupo> addGrupo(@Body Grupo grupo, @Header("Authorization") String token);



    // Peticiones de Historico Tipo Situacion

    @GET("api-rest/historico_tipo_situacion")
    public Call<List<HistoricoTipoSituacion>> getHistoricoTipoSituacion(@Header("Authorization") String token);

    @GET("api-rest/historico_tipo_situacion")
    public Call<HistoricoTipoSituacion> getHistoricoTipoSituacionById(@Query("id") String id, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api-rest/historico_tipo_situacion")
    public Call<HistoricoTipoSituacion> addHistoricoTipoSituacion(@Body HistoricoTipoSituacion historicoTipoSituacion, @Header("Authorization") String token);


    // Peticiones de Persona

    @GET("api-rest/persona")
    public Call<List<Persona>> getPersonas(@Header("Authorization") String token);

    @GET("api-rest/persona")
    public Call<Persona> getPersonaById(@Query("id") String id, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api-rest/persona")
    public Call<Persona> addPersona(@Body Persona persona, @Header("Authorization") String token);


    // Peticiones de Tipo Situacion

    @GET("api-rest/tipo_situacion")
    public Call<List<TipoSituacion>> getTipoSituacion(@Header("Authorization") String token);

    @GET("api-rest/tipo_situacion")
    public Call<TipoSituacion> getTipoSituacionById(@Query("id") String id, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api-rest/tipo_situacion")
    public Call<TipoSituacion> addTipoSituacion(@Body TipoSituacion tipo_situacion, @Header("Authorization") String token);


    // Peticiones de Tipo Vivienda

    @GET("api-rest/tipo_vivienda")
    public Call<List<TipoVivienda>> getTipoVivienda(@Header("Authorization") String token);

    @GET("api-rest/tipo_vivienda")
    public Call<TipoVivienda> getTipoViviendaById(@Query("id") String id, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api-rest/tipo_vivienda")
    public Call<TipoVivienda> addTipoVivienda(@Field("nombre") String nombre, @Header("Authorization") String token);


    // Peticiones de Usuarios

    @GET("api-rest/users")
    public Call<List<Usuario>> getUsuarios(@Header("Authorization") String token);

    @GET("api-rest/users")
    Call<List<Usuario>> getUserByUsername(@Query("username") String username, @Header("Authorization") String token);

    @FormUrlEncoded
    @POST("api-rest/users")
    public Call<Usuario> addUsuario(@Body Usuario usuario, @Header("Authorization") String token);

}
