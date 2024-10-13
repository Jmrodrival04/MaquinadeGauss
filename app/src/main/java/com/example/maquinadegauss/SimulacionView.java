package com.example.maquinadegauss;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import java.util.List;

public class SimulacionView extends View {

    private Paint paint;
    private List<Integer> contenedores;  // Simularemos los contenedores con una lista de enteros

    // Constructor que acepta el contexto y la lista de contenedores
    public SimulacionView(Context context, List<Integer> contenedores) {
        super(context);
        this.contenedores = contenedores;
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(2f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int numContenedores = contenedores.size();

        if (numContenedores > 0) {
            int maxValor = Math.max(1, contenedores.stream().max(Integer::compareTo).orElse(0));
            float escalaX = (float) width / numContenedores;
            float escalaY = (float) height / maxValor;

            for (int i = 1; i < numContenedores; i++) {
                int x1 = (int) ((i - 1) * escalaX);
                int y1 = height - (int) (contenedores.get(i - 1) * escalaY);
                int x2 = (int) (i * escalaX);
                int y2 = height - (int) (contenedores.get(i) * escalaY);
                canvas.drawLine(x1, y1, x2, y2, paint);
            }
        }
    }

    // Método para agregar una "bola" en un contenedor
    public void agregarBola(int contenedorIndex) {
        // Aquí incrementamos el valor en el contenedor seleccionado
        if (contenedorIndex >= 0 && contenedorIndex < contenedores.size()) {
            contenedores.set(contenedorIndex, contenedores.get(contenedorIndex) + 1);
            invalidate();  // Redibuja la vista para mostrar el nuevo estado
        }
    }
}

