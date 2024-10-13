package com.example.maquinadegauss;

public class HistorialItem {
    private String fecha;
    private int valor1;
    private int valor2;

    public HistorialItem() {
        // Constructor vacío para Firebase
    }

    public HistorialItem(String fecha, int valor1, int valor2) {
        this.fecha = fecha;
        this.valor1 = valor1;
        this.valor2 = valor2;
    }

    // Getters y Setters
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getValor1() {
        return valor1;
    }

    public void setValor1(int valor1) {
        this.valor1 = valor1;
    }

    public int getValor2() {
        return valor2;
    }

    public void setValor2(int valor2) {
        this.valor2 = valor2;
    }

    @Override
    public String toString() {
        return fecha + ": " + valor1 + ", " + valor2;
    }
}
