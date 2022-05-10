package com.example.teleappsistencia.ui.clases;

import com.google.gson.annotations.SerializedName;

public class Persona {

    @SerializedName("id")
    private int id;
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("apellidos")
    private String apellidos;
    @SerializedName("dni")
    private String dni;
    @SerializedName("fecha_nacimiento")
    private String fechaNacimiento;
    @SerializedName("sexo")
    private String sexo;
    @SerializedName("telefono_fijo")
    private String telefonoFijo;
    @SerializedName("telefono_movil")
    private String telefonoMovil;
    @SerializedName("id_direccion")
    private Object direccion;

    /**
     * Constructor para mostrar una Persona.
     * @param id
     * @param nombre
     * @param apellidos
     * @param dni
     * @param fechaNacimiento
     * @param sexo
     * @param telefonoFijo
     * @param telefonoMovil
     * @param direccion
     */
    public Persona(int id, String nombre, String apellidos, String dni, String fechaNacimiento, String sexo, String telefonoFijo, String telefonoMovil, Object direccion) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.telefonoFijo = telefonoFijo;
        this.telefonoMovil = telefonoMovil;
        this.direccion = direccion;
    }

    /**
     * Constructor parainsertar una nueva Persona.
     * @param nombre
     * @param apellidos
     * @param dni
     * @param fechaNacimiento
     * @param sexo
     * @param telefonoFijo
     * @param telefonoMovil
     * @param direccion
     */
    public Persona(String nombre, String apellidos, String dni, String fechaNacimiento, String sexo, String telefonoFijo, String telefonoMovil, Object direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.telefonoFijo = telefonoFijo;
        this.telefonoMovil = telefonoMovil;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getTelefonoFijo() {
        return telefonoFijo;
    }

    public void setTelefonoFijo(String telefonoFijo) {
        this.telefonoFijo = telefonoFijo;
    }

    public String getTelefonoMovil() {
        return telefonoMovil;
    }

    public void setTelefonoMovil(String telefonoMovil) {
        this.telefonoMovil = telefonoMovil;
    }

    public Object getDireccion() {
        return direccion;
    }

    public void setDireccion(Object direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", sexo='" + sexo + '\'' +
                ", telefonoFijo='" + telefonoFijo + '\'' +
                ", telefonoMovil='" + telefonoMovil + '\'' +
                ", direccion=" + direccion +
                '}';
    }
}
