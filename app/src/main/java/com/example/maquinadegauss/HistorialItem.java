package com.example.maquinadegauss;

public class HistorialItem {
    private int id;
    private String fecha;
    private int posicionFinal;
    private int numBolas;

    // Constructor completo para Firebase
    public HistorialItem(int id, String fecha, int posicionFinal, int numBolas) {
        this.id = id;
        this.fecha = fecha;
        this.posicionFinal = posicionFinal;
        this.numBolas = numBolas;
    }

    // Constructor para la base de datos local (sin ID)
    public HistorialItem(String fecha, int posicionFinal, int numBolas) {
        this.fecha = fecha;
        this.posicionFinal = posicionFinal;
        this.numBolas = numBolas;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public String getFecha() {
        return fecha;
    }

    public int getPosicionFinal() {
        return posicionFinal;
    }

    public int getNumBolas() {
        return numBolas;
    }
}
