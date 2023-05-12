package com.example.budget_tracker;

import com.google.firebase.firestore.Exclude;

import java.io.Serializable;

public class Bolsillo implements Serializable {
    private String id = "1";

    public Bolsillo(String sigla, String nombre, Double monto) {
        this.sigla = sigla;
        this.nombre = nombre;
        this.monto = monto;
    }
    public Bolsillo(){

    }

    public String sigla;
    public String nombre;
    public Double monto;
    @Exclude
    public String getId() {
        return id;
    }
    @Exclude
    public void setId(String id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }
}
