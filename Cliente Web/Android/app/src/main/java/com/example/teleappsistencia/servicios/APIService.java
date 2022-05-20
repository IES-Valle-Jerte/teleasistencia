package com.example.teleappsistencia.servicios;

import com.example.teleappsistencia.modelos.Direccion;
import com.example.teleappsistencia.modelos.DispositivoAuxiliar;
import com.example.teleappsistencia.modelos.Grupo;
import com.example.teleappsistencia.modelos.HistoricoTipoSituacion;
import com.example.teleappsistencia.modelos.Persona;
import com.example.teleappsistencia.modelos.Terminal;
import com.example.teleappsistencia.modelos.TipoAlarma;
import com.example.teleappsistencia.modelos.TipoSituacion;
import com.example.teleappsistencia.modelos.TipoVivienda;
import com.example.teleappsistencia.modelos.Token;
import com.example.teleappsistencia.modelos.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    // Peticiones del Token y Usuario Logueado

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("api/token/")
    public Call<Token> getToken(@Field("username") String username, @Field("password") String password);

    @GET("api-rest/profile")
    public Call<List<Usuario>> getUsuarioLogueado(@Header("Authorization") String token);


    // Peticiones de Direccion

    @GET("api-rest/direccion")
    public Call<List<Direccion>> getDirecciones(@Header("Authorization") String token);

    @GET("api-rest/direccion")
    public Call<Direccion> getDireccionById(@Query("id") String id, @Header("Authorization") String token);

    @POST("api-rest/direccion")
    public Call<Object> addDireccion(@Body Direccion direccion, @Header("Authorization") String token);

    @PUT("api-rest/direccion/{id}")
    public Call<Object> modifyDireccion(@Path("id") int id, @Body Direccion direccion, @Header("Authorization") String token);

    @DELETE("api-rest/direccion/{id}")
    Call<Response<String>> deleteDireccion(@Path("id") int id, @Header("Authorization") String token);


    // Peticiones de Dispositivos Auxiliares

    @GET("api-rest/dispositivos_auxiliares_en_terminal")
    public Call<List<DispositivoAuxiliar>> getDispositivosAuxiliares(@Header("Authorization") String token);

    @GET("api-rest/dispositivos_auxiliares_en_terminal")
    public Call<DispositivoAuxiliar> getDispositivoAuxiliarById(@Query("id") String id, @Header("Authorization") String token);

    @POST("api-rest/dispositivos_auxiliares_en_terminal")
    public Call<Object> addDispositivoAuxiliar(@Body DispositivoAuxiliar dispositivoAuxiliar, @Header("Authorization") String token);

    @PUT("api-rest/dispositivos_auxiliares_en_terminal/{id}")
    public Call<Object> modifyDispositivoAuxiliar(@Path("id") int id, @Body DispositivoAuxiliar dispositivoAuxiliar, @Header("Authorization") String token);

    @DELETE("api-rest/dispositivos_auxiliares_en_terminal/{id}")
    Call<Response<String>> deleteDispositivosAuxiliar(@Path("id") int id, @Header("Authorization") String token);

    // Peticiones de Grupo

    @GET("api-rest/groups")
    public Call<List<Grupo>> getGrupos(@Header("Authorization") String token);

    @GET("api-rest/groups")
    public Call<Grupo> getGrupoByPk(@Query("pk") String pk, @Header("Authorization") String token);

    @POST("api-rest/groups")
    public Call<Object> addGrupo(@Body Grupo grupo, @Header("Authorization") String token);

    @PUT("api-rest/groups/{pk}")
    public Call<Object> modifyGrupo(@Path("pk") int pk, @Body Grupo grupo, @Header("Authorization") String token);

    @DELETE("api-rest/groups/{pk}")
    Call<Response<String>> deleteGrupo(@Path("pk") int pk, @Header("Authorization") String token);


    // Peticiones de Historico Tipo Situacion

    @GET("api-rest/historico_tipo_situacion")
    public Call<List<HistoricoTipoSituacion>> getHistoricoTipoSituacion(@Header("Authorization") String token);

    @GET("api-rest/historico_tipo_situacion")
    public Call<HistoricoTipoSituacion> getHistoricoTipoSituacionById(@Query("id") String id, @Header("Authorization") String token);

    @POST("api-rest/historico_tipo_situacion")
    public Call<Object> addHistoricoTipoSituacion(@Body HistoricoTipoSituacion historicoTipoSituacion, @Header("Authorization") String token);

    @PUT("api-rest/historico_tipo_situacion/{id}")
    public Call<Object> modifyHistoricoTipoSituacion(@Path("id") int id, @Body HistoricoTipoSituacion historicoTipoSituacion, @Header("Authorization") String token);

    @DELETE("api-rest/historico_tipo_situacion/{id}")
    Call<Response<String>> deleteHistoricoTipoSituacion(@Path("id") int id, @Header("Authorization") String token);


    // Peticiones de Persona

    @GET("api-rest/persona")
    public Call<List<Persona>> getPersonas(@Header("Authorization") String token);

    @GET("api-rest/persona")
    public Call<Persona> getPersonaById(@Query("id") String id, @Header("Authorization") String token);

    @POST("api-rest/persona")
    public Call<Object> addPersona(@Body Persona persona, @Header("Authorization") String token);

    @PUT("api-rest/persona/{id}")
    public Call<Object> modifyPersona(@Path("id") int id, @Body Persona persona, @Header("Authorization") String token);

    @DELETE("api-rest/persona/{id}")
    Call<Response<String>> deletePersona(@Path("id") int id, @Header("Authorization") String token);


    // Peticiones de Tipo Situacion

    @GET("api-rest/tipo_situacion")
    public Call<List<TipoSituacion>> getTipoSituacion(@Header("Authorization") String token);

    @GET("api-rest/tipo_situacion")
    public Call<TipoSituacion> getTipoSituacionById(@Query("id") String id, @Header("Authorization") String token);

    @POST("api-rest/tipo_situacion")
    public Call<Object> addTipoSituacion(@Body TipoSituacion tipoSituacion, @Header("Authorization") String token);

    @PUT("api-rest/tipo_situacion/{id}")
    public Call<Object> modifyTipoSituacion(@Path("id") int id, @Body TipoSituacion tipoSituacion, @Header("Authorization") String token);

    @DELETE("api-rest/tipo_situacion/{id}")
    Call<Response<String>> deleteTipoSituacion(@Path("id") int id, @Header("Authorization") String token);


    // Peticiones de Tipo Vivienda

    @GET("api-rest/tipo_vivienda")
    public Call<List<TipoVivienda>> getTipoVivienda(@Header("Authorization") String token);

    @GET("api-rest/tipo_vivienda")
    public Call<TipoVivienda> getTipoViviendaById(@Query("id") String id, @Header("Authorization") String token);

    @POST("api-rest/tipo_vivienda")
    public Call<Object> addTipoVivienda(@Body TipoVivienda tipoVivienda, @Header("Authorization") String token);

    @PUT("api-rest/tipo_vivienda/{id}")
    public Call<Object> modifyTipoVivienda(@Path("id") int id, @Body TipoVivienda tipoVivienda, @Header("Authorization") String token);

    @DELETE("api-rest/tipo_vivienda/{id}")
    Call<Response<String>> deleteTipoVivienda(@Path("id") int id, @Header("Authorization") String token);


    // Peticiones de Usuarios

    @GET("api-rest/users")
    public Call<List<Usuario>> getUsuarios(@Header("Authorization") String token);

    @GET("api-rest/users")
    Call<List<Usuario>> getUserByUsername(@Query("username") String username, @Header("Authorization") String token);

    @POST("api-rest/users")
    public Call<Object> addUsuario(@Body Usuario usuario, @Header("Authorization") String token);

    @PUT("api-rest/users/{id}")
    public Call<Object> modifyUsuario(@Path("id") int id, @Body Usuario usuario, @Header("Authorization") String token);

    @DELETE("api-rest/users/{id}")
    Call<Response<String>> deleteUser(@Path("id") int id, @Header("Authorization") String token);

    // Peticiones de Terminal

    @GET("api-rest/terminal")
    Call<List<Terminal>> getTerminales(@Header("Authorization") String token);

    // Peticiones de Tipo Alarma

    @GET("api-rest/tipo_alarma")
    Call<List<TipoAlarma>> getTiposAlarmas(@Header("Authorization") String token);
}
