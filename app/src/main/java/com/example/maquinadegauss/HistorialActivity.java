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

        try {
            // Inicializamos el RecyclerView
            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            // Inicializamos la lista y el adaptador
            historialList = new ArrayList<>();
            historialAdapter = new HistorialAdapter(historialList);
            recyclerView.setAdapter(historialAdapter);

            // Inicializamos el helper de base de datos
            databaseHelper = new DatabaseHelper(this);

            // Cargamos los datos de la base de datos
            cargarDatosHistorial();

            // Botón para volver a la pantalla principal
            Button btnVolver = findViewById(R.id.btn_volver);
            btnVolver.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HistorialActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        } catch (Exception e) {
            Log.e("HistorialActivity", "Error en HistorialActivity: " + e.getMessage());
            Toast.makeText(this, "Error al cargar historial", Toast.LENGTH_SHORT).show();
        }
    }

    // Método para cargar los datos de la base de datos
    private void cargarDatosHistorial() {
        Cursor cursor = databaseHelper.getAllData(); // Método de base de datos para obtener los datos
        if (cursor != null && cursor.moveToFirst()) {
            do {
                String fecha = cursor.getString(1);  // Columna 1: Fecha
                String tipoContenedor = cursor.getString(2);  // Columna 2: Tipo de Contenedor
                String cantidad = cursor.getString(3);  // Columna 3: Cantidad

                // Añadimos el item a la lista
                historialList.add(new HistorialItem(fecha, tipoContenedor, cantidad));
            } while (cursor.moveToNext());

            // Notificamos al adaptador que los datos han cambiado
            historialAdapter.notifyDataSetChanged();
        } else {
            Log.e("HistorialActivity", "No se encontraron datos en la base de datos");
            Toast.makeText(this, "No hay datos para mostrar", Toast.LENGTH_SHORT).show();
        }
    }
}
