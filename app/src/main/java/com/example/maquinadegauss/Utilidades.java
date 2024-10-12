package com.example.maquinadegauss;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utilidades {

    public static String obtenerFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        return sdf.format(new Date());
    }
}
