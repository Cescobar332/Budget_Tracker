package com.example.budget_tracker;

public class Category {
    private String nombre;
    private  Integer monto;

    public Category(String nombre, Integer monto) {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }
}
