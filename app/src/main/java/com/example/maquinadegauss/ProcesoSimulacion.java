package com.example.maquinadegauss;

import java.util.concurrent.CountDownLatch;

class ProcesoSimulacion extends Thread {
    private SimulacionView simulacionView;
    private final int numDecisiones;  // Número de decisiones por hilo
    private final CountDownLatch latch;  // Para esperar a que todos los hilos terminen

    public ProcesoSimulacion(SimulacionView simulacionView, int numDecisiones, CountDownLatch latch) {
        this.simulacionView = simulacionView;
        this.numDecisiones = numDecisiones;
        this.latch = latch;
    }

    @Override
    public void run() {
        int posicion = SimulacionView.NUM_CONTENEDORES / 2;  // Iniciamos en el punto medio de la distribución

        // Simulamos el proceso de decisiones
        for (int i = 0; i < numDecisiones; i++) {
            if (Math.random() < 0.5) {
                // 50% de ir a la izquierda
                posicion--;
            } else {
                // 50% de ir a la derecha
                posicion++;
            }
        }

        // Aseguramos que la posición esté dentro de los límites de los contenedores
        if (posicion < 0) posicion = 0;
        if (posicion >= SimulacionView.NUM_CONTENEDORES) posicion = SimulacionView.NUM_CONTENEDORES - 1;

        // Añadimos el resultado final al contenedor correspondiente
        simulacionView.agregarBola(posicion);  // Aquí pasamos el índice correctamente

        // Notificamos que este hilo ha terminado
        latch.countDown();
    }
}


