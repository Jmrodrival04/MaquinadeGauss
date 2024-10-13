package com.example.maquinadegauss;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistorialAdapter adapter;
    private List<HistorialItem> historialList = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar Firebase
        FirebaseApp.initializeApp(this);

        // Inicializar DatabaseHelper para manejar SQLite
        databaseHelper = new DatabaseHelper(this);

        // Configurar RecyclerView para el historial
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistorialAdapter(historialList);
        recyclerView.setAdapter(adapter);

        // Cargar historial desde Firebase
        cargarHistorialDesdeFirebase();

        // Botón para iniciar la simulación
        Button botonSimulacion = findViewById(R.id.botonSimulacion);
        botonSimulacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la simulación
                Intent intent = new Intent(MainActivity.this, SimulacionActivity.class);
                startActivity(intent);
            }
        });
    }

    // Método para cargar historial desde Firebase
    private void cargarHistorialDesdeFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("historial");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                historialList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HistorialItem item = snapshot.getValue(HistorialItem.class);
                    historialList.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Error al cargar historial", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
