package com.example.maquinadegauss;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimulacionView extends View {

    private Paint paint;
    private List<Integer> contenedores;  // Cada contenedor representará un punto en la distribución
    public static final int NUM_CONTENEDORES = 21;  // Número de contenedores para la distribución

    public SimulacionView(Context context) {
        super(context);
        init();
    }

    public SimulacionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(40f);

        // Inicializamos los contenedores en cero
        contenedores = new ArrayList<>(Collections.nCopies(NUM_CONTENEDORES, 0));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth() / NUM_CONTENEDORES;  // Ancho de cada contenedor
        int canvasHeight = getHeight();

        // Dibujamos solo los contenedores con más de 0 bolas
        for (int i = 0; i < NUM_CONTENEDORES; i++) {
            int numBolas = contenedores.get(i);

            if (numBolas > 0) {  // Solo dibujamos si hay bolas (mayor que 0)
                int height = (numBolas * canvasHeight) / 100;  // Escalamos según un máximo de 100 bolas

                // Dibujamos un rectángulo para cada contenedor con bolas
                paint.setColor(Color.BLUE);
                canvas.drawRect(i * width, canvasHeight - height, (i + 1) * width, canvasHeight, paint);

                // Dibujamos el número de bolas encima del contenedor
                paint.setColor(Color.BLACK);
                canvas.drawText(String.valueOf(numBolas), i * width + (width / 4), canvasHeight - height - 20, paint);
            }
        }
    }

    // Método para actualizar la cantidad de bolas en un contenedor
    public void agregarBola(int contenedorIndex) {
        if (contenedorIndex >= 0 && contenedorIndex < NUM_CONTENEDORES) {
            contenedores.set(contenedorIndex, contenedores.get(contenedorIndex) + 1);
        }
    }

    // Método para limpiar la simulación cuando se detiene el servicio
    public void clearSimulation() {
        // Reseteamos los contenedores a cero
        contenedores = new ArrayList<>(Collections.nCopies(NUM_CONTENEDORES, 0));
        // Forzamos un redibujo de la vista para reflejar los cambios
        postInvalidate();
    }
}
