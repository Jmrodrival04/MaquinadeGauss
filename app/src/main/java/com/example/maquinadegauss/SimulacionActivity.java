package com.example.maquinadegauss;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SimulacionActivity extends AppCompatActivity {

    private SimulacionView simulacionView;
    private List<Integer> valoresSimulacion;  // Lista de valores para la simulación
    private TextView tituloSimulacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulacion);  // Asegúrate de que este es el layout correcto

        // Inicializa el TextView desde el layout
        tituloSimulacion = findViewById(R.id.tituloSimulacion);
        tituloSimulacion.setText("Simulación de la Campana de Gauss");

        // Genera los valores de la simulación
        generarValoresSimulacion();

        // Inicializa SimulacionView con los valores simulados
        simulacionView = new SimulacionView(this, valoresSimulacion);
        setContentView(simulacionView);  // Coloca la vista de la simulación en la pantalla
    }

    // Método para generar valores de simulación aleatorios en una distribución normal
    private void generarValoresSimulacion() {
        valoresSimulacion = new ArrayList<>();
        int numValores = 100;  // Número de puntos de simulación
        int media = 50;  // Media de la distribución
        int desviacionEstandar = 10;  // Desviación estándar

        for (int i = 0; i < numValores; i++) {
            int valor = (int) (media + desviacionEstandar * Math.random() * 2 - 1);  // Simula un valor alrededor de la media
            valoresSimulacion.add(valor);
        }
    }
}
