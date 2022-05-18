package com.example.teleappsistencia;

import com.example.teleappsistencia.clases.Paciente;
import com.example.teleappsistencia.clases.Persona;
import com.example.teleappsistencia.clases.RelacionPacientePersona;
import com.example.teleappsistencia.clases.RelacionTerminalRecursoComunitario;
import com.example.teleappsistencia.clases.RelacionUsuarioCentro;
import com.example.teleappsistencia.clases.Terminal;
import com.example.teleappsistencia.clases.Token;
import com.example.teleappsistencia.clases.Usuario;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import retrofit2.Call;
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

    // Peticiones del Token

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("api/token/")
    public Call<Token> getToken(@Field("username") String username, @Field("password") String password);

    //Peticiones de Paciente

    @GET("api-rest/paciente")
    Call<List<LinkedTreeMap>> getPacientes(@Header("Authorization") String token);

    @POST("api-rest/paciente")
    Call<Paciente> addPaciente(@Body Paciente paciente, @Header("Authorization") String token);

    @PUT("api-rest/paciente/{id}")
    Call<Paciente> updatePaciente(@Path("id") String id, @Body Paciente paciente, @Header("Authorization") String token);

    @DELETE("api-rest/paciente/{id}")
    Call<Paciente> deletePaciente(@Path("id") String id, @Header("Authorization") String token);

    //Peticiones de Relacion Paciente Persona
    @GET("api-rest/relacion_paciente_persona")
    Call<List<LinkedTreeMap>> getListadoPacientesPersona(@Header("Authorization") String token);

    @POST("api-rest/relacion_paciente_persona")
    Call<RelacionPacientePersona> addRelacionPacientePersona(@Body RelacionPacientePersona relacionPacientePersona, @Header("Authorization") String token);

    @DELETE("api-rest/relacion_paciente_persona/{id}")
    Call<RelacionPacientePersona> deleteRelacionPacientePersona(@Path("id") String id, @Header("Authorization") String token);

    @PUT("api-rest/relacion_paciente_persona/{id}")
    Call<RelacionPacientePersona> updateRelacionPacientePersona(@Path("id") String id, @Body RelacionPacientePersona relacionPacientePersona, @Header("Authorization") String token);

    //Peticiones de Listado Relacion Terminal Recurso Comunitario
    @GET("api-rest/relacion_terminal_recurso_comunitario")
    Call<List<RelacionTerminalRecursoComunitario>> getListadoRelacionTerminalRecursoComunitario(@Header("Authorization") String token);

    @POST("api-rest/relacion_terminal_recurso_comunitario")
    Call<RelacionTerminalRecursoComunitario> addRelacionTerminalRecursoComunitario(@Body RelacionTerminalRecursoComunitario relacionTerminalRecursoComunitario, @Header("Authorization") String token);

    @DELETE("api-rest/relacion_terminal_recurso_comunitario/{id}")
    Call<RelacionTerminalRecursoComunitario> deleteRelacionTerminalRecursoComunitario(@Path("id") String id, @Header("Authorization") String token);

    @PUT("api-rest/relacion_terminal_recurso_comunitario/{id}")
    Call<RelacionTerminalRecursoComunitario> updateRelacionTerminalRecursoComunitario(@Path("id") String id, @Body RelacionTerminalRecursoComunitario relacionTerminalRecursoComunitario, @Header("Authorization") String token);

    //Peticiones de Listado Relacion Usuario Centro

    @GET("api-rest/relacion_usuario_centro")
    Call<List<RelacionUsuarioCentro>> getListadoRelacionUsuarioCentro(@Header("Authorization") String token);

    @POST("api-rest/relacion_usuario_centro")
    Call<RelacionUsuarioCentro> addRelacionUsuarioCentro(@Body RelacionUsuarioCentro relacionUsuarioCentro, @Header("Authorization") String token);

    @DELETE("api-rest/relacion_usuario_centro/{id}")
    Call<RelacionUsuarioCentro> deleteRelacionUsuarioCentro(@Path("id") String id, @Header("Authorization") String token);

    @PUT("api-rest/relacion_usuario_centro/{id}")
    Call<RelacionUsuarioCentro> updateRelacionUsuarioCentro(@Path("id") String id, @Body RelacionUsuarioCentro relacionUsuarioCentro, @Header("Authorization") String token);

    //Peticiones de Terminal
    @GET("api-rest/terminal")
    Call<List<Terminal>> getListadoTerminal(@Header("Authorization") String token);

    @POST("api-rest/terminal")
    Call<Terminal> addTerminal(@Body Terminal terminal, @Header("Authorization") String token);

    @DELETE("api-rest/terminal/{id}")
    Call<Terminal> deleteTerminal(@Path("id") String id, @Header("Authorization") String token);

    @PUT("api-rest/terminal/{id}")
    Call<Terminal> updateTerminal(@Path("id") String id, @Body Terminal terminal, @Header("Authorization") String token);

    // Peticiones de Usuarios

    @GET("api-rest/users")
    public Call<List<Usuario>> getUsuarios(@Header("Authorization") String token);

    @GET("api-rest/users")
    Call<List<Usuario>> getUserByUsername(@Query("username") String username, @Header("Authorization") String token);

    @POST("api-rest/users")
    public Call<Usuario> addUsuario(@Body Usuario usuario, @Header("Authorization") String token);

    //Peticiones de Personas
    @GET("api-rest/persona")
    Call<List<Persona>> getListadoPersona(@Header("Authorization") String token);

}
