package com.example.teleappsistencia.servicios;

import com.example.teleappsistencia.modelos.CentroSanitario;
import com.example.teleappsistencia.modelos.Paciente;
import com.example.teleappsistencia.modelos.Persona;
import com.example.teleappsistencia.modelos.RecursoComunitario;
import com.example.teleappsistencia.modelos.RelacionPacientePersona;
import com.example.teleappsistencia.modelos.RelacionTerminalRecursoComunitario;
import com.example.teleappsistencia.modelos.RelacionUsuarioCentro;
import com.example.teleappsistencia.modelos.Terminal;
import com.example.teleappsistencia.modelos.Token;
import com.example.teleappsistencia.modelos.Usuario;
import com.google.gson.internal.LinkedTreeMap;

import java.util.List;

import okhttp3.ResponseBody;
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

/**
 * Interfaz para realizar llamada a los distintas "entidades" del servidor.
 * Cada entidad tiene una URL correspondiente, y una lista de métodos para realizar las llamadas.
 * Cada método devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
 */
public interface APIService {

    // Peticiones del Token

    @Headers("Accept: application/json")
    @FormUrlEncoded
    @POST("api/token/")
    public Call<Token> getToken(@Field("username") String username, @Field("password") String password);

    //Peticiones de Paciente

    
    /**
     * Es una solicitud GET a la URL "api-rest/paciente" con un encabezado "Autorización" y un cuerpo
     * de tipo List<LinkedTreeMap>
     * 
     * @param token el token que obtengo del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor
     */
    @GET("api-rest/paciente")
    Call<List<LinkedTreeMap>> getPacientes(@Header("Authorization") String token);

    
    /**
     * Esta función enviará una solicitud POST a la URL "api-rest/paciente" siendo el cuerpo de la
     * solicitud el objeto Paciente pasado como parámetro. La función también enviará el token pasado
     * como parámetro en el encabezado de la solicitud.
     * 
     * @param paciente El objeto que se enviará al servidor.
     * @param token El token que obtiene de la solicitud de inicio de sesión.
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor
     */
    @POST("api-rest/paciente")
    Call<Paciente> addPaciente(@Body Paciente paciente, @Header("Authorization") String token);


    
    /**
     * Esta función actualizará al paciente con la identificación especificada en la ruta, con el
     * objeto paciente pasado en el cuerpo, y utilizará el token pasado en el encabezado para
     * autenticar la solicitud.
     * 
     * @param id El id del paciente que desea actualizar.
     * @param paciente El objeto que se enviará al servidor.
     * @param token El token que obtiene de la solicitud de inicio de sesión.
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor
     */
    @PUT("api-rest/paciente/{id}")
    Call<Paciente> updatePaciente(@Path("id") String id, @Body Paciente paciente, @Header("Authorization") String token);

    /**
     * Esta función eliminará al paciente con el id pasado como parámetro en la ruta, y utilizará el
        * token pasado en el encabezado para autenticar la solicitud.
     * 
     * @param id El id del paciente a ser borrado
     * @param token El token que obtiene de la solicitud de inicio de sesión.
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor
     */
    @DELETE("api-rest/paciente/{id}")
    Call<ResponseBody> deletePaciente(@Path("id") String id, @Header("Authorization") String token);

    //Peticiones de Relacion Paciente Persona

    /**
     * Es una petición GET al endpoint "api-rest/relacion_paciente_persona" con cabecera "Autorización". 
     * 
     * @param token el token que obtengo del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor
     */
    @GET("api-rest/relacion_paciente_persona")
    Call<List<LinkedTreeMap>> getListadoPacientesPersona(@Header("Authorization") String token);

    
    /**
     * Esta función enviará una solicitud POST al servidor siendo el cuerpo de la solicitud el objeto
     * relacionPacientePersona y el encabezado de la solicitud el token
     * 
     * @param relacionPacientePersona es el objeto que quiero enviar al servidor.
     * @param token el token que obtengo del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @POST("api-rest/relacion_paciente_persona")
    Call<RelacionPacientePersona> addRelacionPacientePersona(@Body RelacionPacientePersona relacionPacientePersona, @Header("Authorization") String token);

    /**
     * Esta función elimina un objeto RelaciónPacientePersona del servidor
     * 
     * @param id El id del objeto a eliminar.
     * @param token El token que obtienes del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @DELETE("api-rest/relacion_paciente_persona/{id}")
    Call<ResponseBody> deleteRelacionPacientePersona(@Path("id") String id, @Header("Authorization") String token);


    /**
     * Esta función actualizará el objeto RelaciónPacientePersona con el id especificado en la URL, con
     * los datos proporcionados en el cuerpo de la solicitud, y devolverá el objeto actualizado
     * 
     * @param id El id del objeto a actualizar.
     * @param relacionPacientePersona es el objeto que quiero actualizar
     * @param token El token que obtienes del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @PUT("api-rest/relacion_paciente_persona/{id}")
    Call<RelacionPacientePersona> updateRelacionPacientePersona(@Path("id") String id, @Body RelacionPacientePersona relacionPacientePersona, @Header("Authorization") String token);

    //Peticiones de Listado Relacion Terminal Recurso Comunitario

    /**
     * Esta función devolverá una llamada al servidor y pasará el token especificado en el encabezado de
     * Autorización.
     * 
     * @param token el token que obtengo del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @GET("api-rest/relacion_terminal_recurso_comunitario")
    Call<List<LinkedTreeMap>> getListadoRelacionTerminalRecursoComunitario(@Header("Authorization") String token);

    /**
     * Esta función enviará una solicitud POST al servidor siendo el cuerpo de la solicitud el objeto
     * relacionTerminalRecursoComunitario y el encabezado de la solicitud el token
     * 
     * @param relacionTerminalRecursoComunitario es el objeto que quiero enviar al servidor.
     * @param token el token que obtengo del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @POST("api-rest/relacion_terminal_recurso_comunitario")
    Call<RelacionTerminalRecursoComunitario> addRelacionTerminalRecursoComunitario(@Body RelacionTerminalRecursoComunitario relacionTerminalRecursoComunitario, @Header("Authorization") String token);

    /**
     * Esta función elimina un objeto RelaciónTerminalRecursoComunitario del servidor
     * 
     * @param id El id del objeto a eliminar.
     * @param token El token que obtienes del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @DELETE("api-rest/relacion_terminal_recurso_comunitario/{id}")
    Call<ResponseBody> deleteRelacionTerminalRecursoComunitario(@Path("id") String id, @Header("Authorization") String token);

    /**
     * Esta función actualizará el objeto RelaciónTerminalRecursoComunitario con el id especificado en la URL, con
     * los datos proporcionados en el cuerpo de la solicitud, y devolverá el objeto actualizado
     * 
     * @param id El id del objeto a actualizar.
     * @param relacionTerminalRecursoComunitario es el objeto que quiero actualizar
     * @param token El token que obtienes del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @PUT("api-rest/relacion_terminal_recurso_comunitario/{id}")
    Call<RelacionTerminalRecursoComunitario> updateRelacionTerminalRecursoComunitario(@Path("id") String id, @Body RelacionTerminalRecursoComunitario relacionTerminalRecursoComunitario, @Header("Authorization") String token);

    //Peticiones de Listado Relacion Usuario Centro

    /**
     * Esta función devolverá una llamada al servidor y pasará el token especificado en el encabezado de
     * Autorización.
     * 
     * @param token el token que obtengo del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @GET("api-rest/relacion_usuario_centro")
    Call<List<LinkedTreeMap>> getListadoRelacionUsuarioCentro(@Header("Authorization") String token);

    /**
     * Esta función enviará una solicitud POST al servidor siendo el cuerpo de la solicitud el objeto
     * relacionUsuarioCentro y el encabezado de la solicitud el token
     * 
     * @param relacionUsuarioCentro es el objeto que quiero enviar al servidor.
     * @param token el token que obtengo del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @POST("api-rest/relacion_usuario_centro")
    Call<RelacionUsuarioCentro> addRelacionUsuarioCentro(@Body RelacionUsuarioCentro relacionUsuarioCentro, @Header("Authorization") String token);

    /**
     * Esta función elimina un objeto RelaciónUsuarioCentro del servidor
     * 
     * @param id El id del objeto a eliminar.
     * @param token El token que obtienes del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @DELETE("api-rest/relacion_usuario_centro/{id}")
    Call<RelacionUsuarioCentro> deleteRelacionUsuarioCentro(@Path("id") String id, @Header("Authorization") String token);

    /**
     * Esta función actualizará el objeto RelaciónUsuarioCentro con el id especificado en la URL, con los datos
     * proporcionados en el cuerpo de la solicitud, y devolverá el objeto actualizado
     * 
     * @param id El id del objeto a actualizar.
     * @param relacionUsuarioCentro es el objeto que quiero actualizar
     * @param token El token que obtienes del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @PUT("api-rest/relacion_usuario_centro/{id}")
    Call<RelacionUsuarioCentro> updateRelacionUsuarioCentro(@Path("id") String id, @Body RelacionUsuarioCentro relacionUsuarioCentro, @Header("Authorization") String token);

    //Peticiones de Terminal

    /**
     * Esta función devolverá una llamada al servidor y pasará el token especificado en el encabezado de
     * Autorización.
     * 
     * @param token el token que obtengo del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @GET("api-rest/terminal")
    Call<List<Terminal>> getListadoTerminal(@Header("Authorization") String token);

    /**
     * Esta función enviará una solicitud POST al servidor siendo el cuerpo de la solicitud el objeto
     * terminal y el encabezado de la solicitud el token
     * 
     * @param terminal es el objeto que quiero enviar al servidor.
     * @param token el token que obtengo del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @POST("api-rest/terminal")
    Call<Terminal> addTerminal(@Body Terminal terminal, @Header("Authorization") String token);

    /**
     * Esta función elimina un objeto Terminal del servidor
     * 
     * @param id El id del objeto a eliminar.
     * @param token El token que obtienes del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @DELETE("api-rest/terminal/{id}")
    Call<Terminal> deleteTerminal(@Path("id") String id, @Header("Authorization") String token);

    /**
     * Esta función actualizará el objeto Terminal con el id especificado en la URL, con los datos proporcionados
     * en el cuerpo de la solicitud, y devolverá el objeto actualizado
     * 
     * @param id El id del objeto a actualizar.
     * @param terminal es el objeto que quiero actualizar
     * @param token El token que obtienes del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @PUT("api-rest/terminal/{id}")
    Call<Terminal> updateTerminal(@Path("id") String id, @Body Terminal terminal, @Header("Authorization") String token);

    // Peticiones de Usuarios

    /**
     * Esta función devolverá una llamada al servidor y pasará el token especificado en el encabezado de
     * Autorización.
     * 
     * @param token el token que obtengo del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @GET("api-rest/users")
    public Call<List<Usuario>> getUsuarios(@Header("Authorization") String token);

    
    /**
     * Esta función realizará una solicitud GET a la URL "api-rest/usuarios" con el parámetro de
     * consulta "nombre de usuario" y el encabezado "Autorización"
     * 
     * @param username El nombre de usuario del usuario que desea recuperar.
     * @param token el token que obtienes del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor
     */
    @GET("api-rest/users")
    Call<List<Usuario>> getUserByUsername(@Query("username") String username, @Header("Authorization") String token);


   /**
    * Esta función enviará una solicitud POST a la URL "api-rest/users" con el cuerpo de la solicitud
    * siendo el objeto Usuario pasado como parámetro. La función también enviará el token pasado como
    * parámetro en el encabezado de la solicitud.
    * 
    * @param usuario El objeto que se enviará al servidor.
    * @param token El token que obtienes del inicio de sesión
    * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
    */
    @POST("api-rest/users")
    public Call<Usuario> addUsuario(@Body Usuario usuario, @Header("Authorization") String token);

    //Peticiones de Personas

    /**
     * Esta función devolverá una llamada al servidor y pasará el token especificado en el encabezado de
     * Autorización.
     * 
     * @param token el token que obtengo del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @GET("api-rest/persona")
    Call<List<Persona>> getListadoPersona(@Header("Authorization") String token);

    //Listado de peticiones de Recurso Comunitario

    /**
     * Esta función devolverá una llamada al servidor y pasará el token especificado en el encabezado de
     * Autorización.
     *
     * @param token el token que obtengo del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @GET("api-rest/recurso_comunitario")
    Call<List<RecursoComunitario>> getListadoRecursoComunitario(@Header("Authorization") String token);

    //Listado de peticiones de Centros Sanitarios

    /**
     * Esta función devolverá una llamada al servidor y pasará el token especificado en el encabezado de
     * Autorización.
     *
     * @param token el token que obtengo del inicio de sesión
     * @return Devuelve una llamada a Retrofit, que se utiliza para realizar la llamada al servidor.
     */
    @GET("api-rest/centro_sanitario")
    Call<List<CentroSanitario>> getListadoCentroSanitario(@Header("Authorization") String token);
}
