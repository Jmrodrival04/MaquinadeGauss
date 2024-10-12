package com.example.maquinadegauss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        setContentView(R.layout.activity_main);

        // Botón para Iniciar Servicio
        Button btnIniciarServicio = findViewById(R.id.btn_iniciar_servicio);
        btnIniciarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reiniciarSimulacion();  // Refresca y reinicia la simulación
            }
        });

        // Botón para Detener Servicio
        Button btnDetenerServicio = findViewById(R.id.btn_detener_servicio);
        btnDetenerServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detenerServicio();  // Detiene el servicio de simulación
            }
        });

        // Botón para ir al Historial
        Button btnHistorial = findViewById(R.id.btn_historial);
        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HistorialActivity.class);
                startActivity(intent);
            }
        });

        // Añadir la vista de simulación al FrameLayout
        simulacionView = new SimulacionView(this);
        FrameLayout simulacionContainer = findViewById(R.id.simulacionContainer);
        simulacionContainer.addView(simulacionView);

        // Inicia la simulación
        iniciarSimulacion();
    }

    // Método para reiniciar la simulación
    private void reiniciarSimulacion() {
        finish();  // Termina la actividad actual
        startActivity(getIntent());  // Vuelve a lanzar la actividad
    }

    // Método para detener el servicio (simulación)
    private void detenerServicio() {
        simulacionView.clearSimulation();  // Limpiar la simulación
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
                    latch.await();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            simulacionView.postInvalidate();  // Refresca la vista
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
