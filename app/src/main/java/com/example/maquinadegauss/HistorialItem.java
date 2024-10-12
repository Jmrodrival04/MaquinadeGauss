package com.example.maquinadegauss;

public class HistorialItem {
    private String fecha;
    private String tipoContenedor;
    private String cantidad;

    public HistorialItem(String fecha, String tipoContenedor, String cantidad) {
        this.fecha = fecha;
        this.tipoContenedor = tipoContenedor;
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public String getTipoContenedor() {
        return tipoContenedor;
    }

    public String getCantidad() {
        return cantidad;
    }
}

