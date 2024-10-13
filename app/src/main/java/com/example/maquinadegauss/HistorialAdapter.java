package com.example.maquinadegauss;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder> {

    private List<HistorialItem> historialList;

    public HistorialAdapter(List<HistorialItem> historialList) {
        this.historialList = historialList;
    }

    @NonNull
    @Override
    public HistorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_historial, parent, false);
        return new HistorialViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialViewHolder holder, int position) {
        HistorialItem item = historialList.get(position);
        holder.fechaTextView.setText(item.getFecha());
        holder.numBolasTextView.setText(String.valueOf(item.getNumBolas()));

        // Aquí puedes agregar la lógica para mostrar los gráficos en miniatura, si es necesario
        // holder.miniSimulacionView.setSimulacionData(item.getPosicionFinal(), item.getNumBolas());
    }

    @Override
    public int getItemCount() {
        return historialList.size();
    }

    public static class HistorialViewHolder extends RecyclerView.ViewHolder {
        public TextView fechaTextView;
        public TextView numBolasTextView;
        // public SimulacionView miniSimulacionView; // Si lo necesitas

        public HistorialViewHolder(@NonNull View itemView) {
            super(itemView);
            fechaTextView = itemView.findViewById(R.id.fechaTextView);
            numBolasTextView = itemView.findViewById(R.id.numBolasTextView);
            // miniSimulacionView = itemView.findViewById(R.id.miniSimulacionView); // Si lo tienes en tu layout
        }
    }
}
