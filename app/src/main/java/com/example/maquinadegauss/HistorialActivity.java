package com.example.maquinadegauss;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class HistorialActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistorialAdapter historialAdapter;
    private DatabaseHelper databaseHelper;
    private List<HistorialItem> historialList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        // Inicializamos el RecyclerView y el botón para volver
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btnVolver = findViewById(R.id.btn_volver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistorialActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Inicializamos la lista y el adaptador del RecyclerView
        historialList = new ArrayList<>();
        historialAdapter = new HistorialAdapter(historialList);
        recyclerView.setAdapter(historialAdapter);

        // Inicializamos la base de datos
        databaseHelper = new DatabaseHelper(this);

        // Cargamos los datos del historial
        cargarDatosHistorial();
    }

    private void cargarDatosHistorial() {
        historialList.clear();  // Limpiamos la lista actual antes de agregar los nuevos datos

        Cursor cursor = databaseHelper.getAllData();
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String fecha = cursor.getString(1);  // Columna 1: Fecha
                int posicionFinal = cursor.getInt(2);  // Columna 2: Posición final
                int numBolas = cursor.getInt(3);  // Columna 3: Número de bolas

                Log.d("HistorialActivity", "Cargando datos: Fecha = " + fecha + ", Posición final = " + posicionFinal + ", Número de bolas = " + numBolas);

                // Añadimos el item a la lista si el número de bolas es mayor a 0
                if (numBolas > 0) {
                    historialList.add(new HistorialItem(fecha, posicionFinal, numBolas));
                }
            } while (cursor.moveToNext());

            // Notificamos al adaptador que los datos han cambiado
            historialAdapter.notifyDataSetChanged();
        } else {
            Log.d("HistorialActivity", "No hay datos en la base de datos.");
            Toast.makeText(this, "No hay datos en el historial", Toast.LENGTH_SHORT).show();
        }

        if (cursor != null) {
            cursor.close();
        }
    }
}
