package com.example.maquinadegauss;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.FrameLayout;

import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity {

    private SimulacionView simulacionView;
    private static final int NUM_HILOS = 100;  // Número de hilos
    private static final int NUM_DECISIONES = 10;  // Número de decisiones por hilo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enlazamos con el layout que contiene el SimulacionView
        setContentView(R.layout.activity_main);

        // Añadimos la vista de simulación al FrameLayout
        simulacionView = new SimulacionView(this);
        FrameLayout simulacionContainer = findViewById(R.id.simulacionContainer);
        simulacionContainer.addView(simulacionView);

        // Iniciamos la simulación
        iniciarSimulacion();
    }

    // Método para iniciar múltiples hilos que simulan el proceso de decisión
    private void iniciarSimulacion() {
        final CountDownLatch latch = new CountDownLatch(NUM_HILOS);

        for (int i = 0; i < NUM_HILOS; i++) {
            new ProcesoSimulacion(simulacionView, NUM_DECISIONES, latch).start();
        }

        // Una vez que todos los hilos hayan terminado, mostramos el resultado final
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Esperamos a que todos los hilos terminen
                    latch.await();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Actualizamos la vista para mostrar el resultado final
                            simulacionView.postInvalidate();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

