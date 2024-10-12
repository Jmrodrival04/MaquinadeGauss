package com.example.maquinadegauss;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SimulacionView extends View {

    public static final int NUM_CONTENEDORES = 10;  // Número de contenedores para la simulación
    private int[] contenedores;  // Array para almacenar el número de bolas en cada contenedor
    private Paint paint;
    private Paint textPaint;

    public SimulacionView(Context context) {
        super(context);
        init();
    }

    public SimulacionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SimulacionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    // Inicialización de la vista y los contenedores
    private void init() {
        contenedores = new int[NUM_CONTENEDORES];  // Inicializamos el array de contenedores
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(5);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40);  // Tamaño del texto para los números
        textPaint.setTextAlign(Paint.Align.CENTER);  // Alinear el texto al centro
    }

    // Método para agregar bolas a un contenedor específico
    public void agregarBola(int contenedorIndex) {
        if (contenedorIndex >= 0 && contenedorIndex < NUM_CONTENEDORES) {
            contenedores[contenedorIndex]++;  // Aumentamos el conteo de bolas en el contenedor
            invalidate();  // Forzar redibujado
        }
    }

    // Método para limpiar la simulación
    public void clearSimulation() {
        contenedores = new int[NUM_CONTENEDORES];  // Reinicializamos el array de contenedores
        invalidate();  // Forzar redibujado
    }

    // Método para actualizar los datos de simulación
    public void setSimulacionData(int posicionFinal, int numBolas) {
        // Reiniciamos el array de contenedores
        contenedores = new int[NUM_CONTENEDORES];
        // Colocamos las bolas en la posición final, solo si numBolas es mayor a 0
        if (posicionFinal >= 0 && posicionFinal < NUM_CONTENEDORES && numBolas > 0) {
            contenedores[posicionFinal] = numBolas;
        }
        invalidate();  // Forzar redibujado
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        int contenedorWidth = width / NUM_CONTENEDORES;

        paint.setStyle(Paint.Style.FILL);

        // Dibujar barras que representen el número de bolas en cada contenedor
        for (int i = 0; i < NUM_CONTENEDORES; i++) {
            if (contenedores[i] > 0) {  // Solo dibujamos si el valor es mayor a 0
                float left = i * contenedorWidth;
                float right = left + contenedorWidth;
                float top = height - (contenedores[i] * 10);  // Escalamos la altura en función de la cantidad de bolas
                float bottom = height;

                // Dibujar la barra
                canvas.drawRect(left, top, right, bottom, paint);

                // Dibujar el número de bolas encima de la barra
                canvas.drawText(String.valueOf(contenedores[i]), left + contenedorWidth / 2, top - 10, textPaint);
            }
        }
    }
}
