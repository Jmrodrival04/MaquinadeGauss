package com.example.maquinadegauss;

import android.util.Log;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class FirebaseService {

    private static final String TAG = "FirebaseService";
    private DatabaseReference databaseReference;

    public FirebaseService() {
        databaseReference = FirebaseDatabase.getInstance().getReference("maquinadegauss");
    }

    // Método para obtener todos los gráficos desde Firebase
    public void obtenerGraficos(final FirebaseCallback callback) {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<HistorialItem> listaGraficos = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HistorialItem item = snapshot.getValue(HistorialItem.class);
                    listaGraficos.add(item);
                }
                callback.onCallback(listaGraficos);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "Error al obtener los datos de Firebase", databaseError.toException());
            }
        });
    }

    public interface FirebaseCallback {
        void onCallback(List<HistorialItem> listaGraficos);
    }
}
