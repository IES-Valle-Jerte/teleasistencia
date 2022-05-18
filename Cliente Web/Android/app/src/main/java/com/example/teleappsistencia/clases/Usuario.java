package com.example.teleappsistencia.clases;

import java.io.Serializable;
import java.util.Date;

import com.google.gson.annotations.SerializedName;

public class Usuario implements Serializable
{

    private final static long serialVersionUID = 2592565805411682085L;

    /**
     * Atributos de la clase
     */

    @SerializedName("id")
    private int id;
    @SerializedName("url")
    private String url;
    @SerializedName("last_login")
    private String lastLogin;
    @SerializedName("username")
    private String username;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("email")
    private String email;
    @SerializedName("date_joined")
    private Date dateJoined;
    @SerializedName("groups")
    private Object groups = null;

    /**
     * Getters y setters
     */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Date dateJoined) {
        this.dateJoined = dateJoined;
    }

    public Object getGroups() {
        return groups;
    }

    public void setGroups(Object groups) {
        this.groups = groups;
    }

    @Override
    public String toString() {
        return "UsuarioSistema{" +
                "pk=" + id +
                ", url='" + url + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", dateJoined=" + dateJoined +
                ", groups=" + groups +
                '}';
    }
}