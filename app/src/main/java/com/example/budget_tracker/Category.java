package com.example.budget_tracker;

public class Category {
    private String nombre;
    private Double monto;

    public Category(String nombre, Double monto) {

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
