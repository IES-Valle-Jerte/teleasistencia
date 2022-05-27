package com.example.teleappsistencia.servicios;

import com.example.teleappsistencia.modelos.CentroSanitario;
import com.example.teleappsistencia.modelos.RecursoComunitario;
import com.example.teleappsistencia.modelos.TipoModalidadPaciente;
import com.example.teleappsistencia.modelos.TipoRecursoComunitario;
import com.example.teleappsistencia.modelos.Token;
import com.example.teleappsistencia.modelos.TipoCentroSanitario;

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

public interface APIService {

    // Peticiones del Token.

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("api/token/")
    public Call<Token> getToken(@Field("username") String username, @Field("password") String password);

    //Peticiones de Tipo Centro Sanitario.

    @GET("/api-rest/tipo_centro_sanitario")
    public Call<List<TipoCentroSanitario>> getTipoCentroSanitario(@Header("Authorization") String token);

    @POST("/api-rest/tipo_centro_sanitario")
    public Call<Object> postTipoCentroSanitario(@Body TipoCentroSanitario tipoCentroSanitario, @Header("Authorization") String token);

    @PUT("/api-rest/tipo_centro_sanitario/{id_dato}")
    public Call<Object> putTipoCentroSanitario(@Path("id_dato") int id_dato, @Body TipoCentroSanitario tipoCentroSanitario, @Header("Authorization") String token);

    @DELETE("/api-rest/tipo_centro_sanitario/{id_dato}")
    public Call<Response<String>> deleteTipoCentroSanitario(@Path ("id_dato") int id_dato, @Header("Authorization") String token);

    //Peticiones de Centro Sanitario.

    @GET("/api-rest/centro_sanitario")
    public Call<List<CentroSanitario>> getCentroSanitario(@Header("Authorization") String token);

    @POST("/api-rest/centro_sanitario")
    public Call<Object> postCentroSanitario(@Body CentroSanitario centroSanitario, @Header("Authorization") String token);

    @PUT("/api-rest/centro_sanitario/{id_dato}")
    public Call<Object> putCentroSanitario(@Path("id_dato") int id_dato, @Body CentroSanitario centroSanitario, @Header("Authorization") String token);

    @DELETE("/api-rest/centro_sanitario/{id_dato}")
    public Call<Response<String>> deleteCentroSanitario(@Path ("id_dato") int id_dato, @Header("Authorization") String token);

    //Peticiones de Recurso Comunitario.

    @GET("/api-rest/recurso_comunitario")
    public Call<List<RecursoComunitario>> getRecursoComunitario(@Header("Authorization") String token);

    @POST("/api-rest/recurso_comunitario")
    public Call<Object> postRecursoComunitario(@Body RecursoComunitario recursoComunitario, @Header("Authorization") String token);

    @PUT("/api-rest/recurso_comunitario/{id_dato}")
    public Call<Object> putRecursoComunitario(@Path("id_dato") int id_dato, @Body RecursoComunitario recursoComunitario, @Header("Authorization") String token);

    @DELETE("/api-rest/recurso_comunitario/{id_dato}")
    public Call<Response<String>> deleteRecursoComunitario(@Path ("id_dato") int id_dato, @Header("Authorization") String token);

    //Peticiones de Tipo Modalidad Paciente.

    @GET("/api-rest/tipo_modalidad_paciente")
    public Call<List<TipoModalidadPaciente>> getTipoModalidadPaciente(@Header("Authorization") String token);

    @POST("/api-rest/tipo_modalidad_paciente")
    public Call<Object> postTipoModalidadPaciente(@Body TipoModalidadPaciente tipoModalidadPaciente, @Header("Authorization") String token);

    @PUT("/api-rest/tipo_modalidad_paciente/{id_dato}")
    public Call<Object> putTipoModalidadPaciente(@Path("id_dato") int id_dato, @Body TipoModalidadPaciente tipoModalidadPaciente, @Header("Authorization") String token);

    @DELETE("/api-rest/tipo_modalidad_paciente/{id_dato}")
    public Call<Response<String>> deleteTipoModalidadPaciente(@Path ("id_dato") int id_dato, @Header("Authorization") String token);

    //Peticiones de Tipo Recurso Comunitario.

    @GET("/api-rest/tipo_recurso_comunitario")
    public Call<List<TipoRecursoComunitario>> getTipoRecursoComunitario(@Header("Authorization") String token);

    @POST("/api-rest/tipo_recurso_comunitario")
    public Call<Object> postTipoRecursoComunitario(@Body TipoRecursoComunitario tipoRecursoComunitario, @Header("Authorization") String token);

    @PUT("/api-rest/tipo_recurso_comunitario/{id_dato}")
    public Call<Object> putTipoRecursoComunitario(@Path("id_dato") int id_dato, @Body TipoRecursoComunitario tipoRecursoComunitario, @Header("Authorization") String token);

    @DELETE("/api-rest/tipo_recurso_comunitario/{id_dato}")
    public Call<Response<String>> deleteTipoRecursoComunitario(@Path ("id_dato") int id_dato, @Header("Authorization") String token);
}