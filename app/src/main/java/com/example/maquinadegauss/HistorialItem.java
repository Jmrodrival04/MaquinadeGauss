package com.example.maquinadegauss;

public class HistorialItem {
    private String fecha;
    private int posicionFinal;
    private int numBolas;

    public HistorialItem(String fecha, int posicionFinal, int numBolas) {
        this.fecha = fecha;
        this.posicionFinal = posicionFinal;
        this.numBolas = numBolas;
    }

    // Getters
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
