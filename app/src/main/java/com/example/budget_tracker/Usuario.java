package com.example.budget_tracker;

import com.google.firebase.firestore.Exclude;
import com.google.gson.annotations.SerializedName;

public class Usuario {


    private long id_usuario;
    @SerializedName("nombre")
    private String usuario;
    private String contrasena;
    private String correo;

    public Usuario() {
    }

    @Exclude
    public long getId_usuario() {
        return id_usuario;
    }

    @Exclude
    public void setId_usuario(long id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
