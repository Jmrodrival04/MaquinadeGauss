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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial, parent, false);
        return new HistorialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialViewHolder holder, int position) {
        HistorialItem historialItem = historialList.get(position);
        holder.tvFecha.setText(historialItem.getFecha());
        holder.tvTipoContenedor.setText(historialItem.getTipoContenedor());
        holder.tvCantidad.setText(historialItem.getCantidad());
    }

    @Override
    public int getItemCount() {
        return historialList.size();
    }

    public static class HistorialViewHolder extends RecyclerView.ViewHolder {
        TextView tvFecha, tvTipoContenedor, tvCantidad;

        public HistorialViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFecha = itemView.findViewById(R.id.tv_fecha);
            tvTipoContenedor = itemView.findViewById(R.id.tv_tipo_contenedor);
            tvCantidad = itemView.findViewById(R.id.tv_cantidad);
        }
    }
}
