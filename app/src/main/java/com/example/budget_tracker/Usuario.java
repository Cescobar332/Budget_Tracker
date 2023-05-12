package com.example.budget_tracker;

import com.google.firebase.firestore.Exclude;
import com.google.gson.annotations.SerializedName;

public class Usuario {

    @Exclude
    public String getId() {
        return id;
    }

    @Exclude
    public void setId(String id) {
        this.id = id;
    }
    private String id;
    @SerializedName("nombre")
    private String usuario;
    private String contrasena;
    private String correo;

    public Usuario() {
    }
    public Usuario(String usuario, String contrasena, String correo){
        this.usuario=usuario;
        this.contrasena=contrasena;
        this.correo=correo;
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
