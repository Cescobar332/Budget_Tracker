package com.example.budget_tracker;

public class Category {
    private String nombre;
    private Double montosav;
    private Double montoexp;
    private Double montoinc;

    public Category() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getMontosav() {
        return montosav;
    }

    public void setMontosav(Double montosav) {
        this.montosav = montosav;
    }

    public Double getMontoexp() {
        return montoexp;
    }

    public void setMontoexp(Double montoexp) {
        this.montoexp = montoexp;
    }

    public Double getMontoinc() {
        return montoinc;
    }

    public void setMontoinc(Double montoinc) {
        this.montoinc = montoinc;
    }
}
