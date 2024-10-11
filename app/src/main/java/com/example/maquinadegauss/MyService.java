package com.example.maquinadegauss;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    private static final String TAG = "MyService";
    private boolean isRunning;

    @Override
    public void onCreate() {
        super.onCreate();
        isRunning = true;
        Log.d(TAG, "Servicio creado");
        Toast.makeText(this, "Servicio iniciado", Toast.LENGTH_SHORT).show();
    }

    // Este método se llama cada vez que se inicia el servicio
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Servicio onStartCommand ejecutado");

        // Simulando un trabajo en segundo plano con un hilo
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    try {
                        Log.d(TAG, "Servicio ejecutándose...");
                        Thread.sleep(5000);  // Simula una tarea que se ejecuta cada 5 segundos
                    } catch (InterruptedException e) {
                        Log.e(TAG, "Error en el hilo del servicio", e);
                    }
                }
            }
        }).start();

        // Si el sistema mata el servicio, no lo recrea hasta que no se reinicie manualmente.
        return START_NOT_STICKY;
    }

    // Método que es llamado cuando el servicio es destruido
    @Override
    public void onDestroy() {
        super.onDestroy();
        isRunning = false;
        Log.d(TAG, "Servicio destruido");
        Toast.makeText(this, "Servicio detenido", Toast.LENGTH_SHORT).show();
    }

    // Se debe implementar pero no es necesario para un servicio no enlazado
    @Override
    public IBinder onBind(Intent intent) {
        // No se proporciona binding, así que retornamos null
        return null;
    }
}
