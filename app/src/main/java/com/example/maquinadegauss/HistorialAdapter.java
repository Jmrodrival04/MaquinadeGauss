package com.example.maquinadegauss;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.ViewHolder> {

    private List<HistorialItem> historialItems;

    public HistorialAdapter(List<HistorialItem> historialItems) {
        this.historialItems = historialItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_historial, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistorialItem item = historialItems.get(position);
        holder.textView.setText(item.toString());  // Mostramos el texto del objeto HistorialItem
    }

    @Override
    public int getItemCount() {
        return historialItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;  // El TextView del item_historial.xml

        public ViewHolder(View itemView) {
            super(itemView);
            // Referenciamos el TextView desde el layout item_historial.xml
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
