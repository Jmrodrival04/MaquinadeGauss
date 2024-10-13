package com.example.maquinadegauss;

import java.util.concurrent.CountDownLatch;

public class ProcesoSimulacion extends Thread {

    private SimulacionView simulacionView;
    private int numDecisiones;
    private CountDownLatch latch;
    private DatabaseHelper databaseHelper;

    public ProcesoSimulacion(SimulacionView simulacionView, int numDecisiones, CountDownLatch latch, DatabaseHelper databaseHelper) {
        this.simulacionView = simulacionView;
        this.numDecisiones = numDecisiones;
        this.latch = latch;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public void run() {
        try {
            // Simulación de lógica de decisión
            int posicionFinal = (int) (Math.random() * LineaDeEnsamblaje.NUM_CONTENEDORES);
            simulacionView.agregarBola(posicionFinal);

            // Inserción en la base de datos
            String fechaActual = "2024-10-13"; // Puedes cambiarlo a la fecha actual
            databaseHelper.insertarDatos(fechaActual, posicionFinal, numDecisiones);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            latch.countDown();  // Notificamos al CountDownLatch que este hilo ha terminado
        }
    }
}
