package com.example.maquinadegauss;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class SimulacionService extends Service {

    private static final int NUM_HILOS = 100;  // Número de hilos
    private static final int NUM_DECISIONES = 10;  // Número de decisiones por hilo
    private SimulacionView simulacionView;
    private DatabaseHelper databaseHelper;  // Instancia de DatabaseHelper para la base de datos

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("SimulacionService", "Servicio de simulación creado");

        // Crear la lista de contenedores para SimulacionView
        List<Integer> contenedores = new ArrayList<>();
        for (int i = 0; i < 10; i++) {  // Inicializamos con 10 contenedores
            contenedores.add(0);
        }

        // Inicializamos la vista de simulación y el helper de base de datos
        simulacionView = new SimulacionView(this, contenedores);  // Ahora con los contenedores
        databaseHelper = new DatabaseHelper(this);  // Añadimos DatabaseHelper para la gestión de la base de datos
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("SimulacionService", "Servicio de simulación iniciado");

        // Iniciamos la simulación en segundo plano
        iniciarSimulacionEnSegundoPlano();

        // Devolvemos START_STICKY para que el servicio continúe ejecutándose si es interrumpido
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SimulacionService", "Servicio de simulación destruido");
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;  // No es un servicio enlazado, así que devolvemos null
    }

    // Método para iniciar la simulación en segundo plano
    private void iniciarSimulacionEnSegundoPlano() {
        // Ejecutamos los hilos de la simulación en un nuevo Thread para no bloquear el servicio principal
        new Thread(new Runnable() {
            @Override
            public void run() {
                final CountDownLatch latch = new CountDownLatch(NUM_HILOS);  // Sincronización de hilos

                for (int i = 0; i < NUM_HILOS; i++) {
                    // Pasamos la instancia de DatabaseHelper al constructor de ProcesoSimulacion
                    new ProcesoSimulacion(simulacionView, NUM_DECISIONES, latch, databaseHelper).start();
                }

                try {
                    // Esperamos a que todos los hilos terminen antes de continuar
                    latch.await();

                    // Notificamos que la simulación ha terminado
                    Log.d("SimulacionService", "Simulación completada");

                    // Aquí podrías notificar a la actividad principal con un BroadcastReceiver o similar
                    Intent intent = new Intent("com.example.maquinadegauss.SIMULACION_COMPLETA");
                    sendBroadcast(intent);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
