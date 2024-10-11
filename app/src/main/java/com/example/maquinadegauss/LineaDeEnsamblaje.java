package com.example.maquinadegauss;

class LineaDeEnsamblaje extends Thread {
    private BufferCompartido buffer;
    private SimulacionView simulacionView;

    public LineaDeEnsamblaje(BufferCompartido buffer, SimulacionView simulacionView) {
        this.buffer = buffer;
        this.simulacionView = simulacionView;
    }

    // Getters
    public BufferCompartido getBuffer() {
        return buffer;
    }

    public SimulacionView getSimulacionView() {
        return simulacionView;
    }

    // Setters
    public void setBuffer(BufferCompartido buffer) {
        this.buffer = buffer;
    }

    public void setSimulacionView(SimulacionView simulacionView) {
        this.simulacionView = simulacionView;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Consumimos un componente del buffer
                Componente componente = buffer.consumir();
                System.out.println("Línea de ensamblaje consumió: " + componente.getNombre());

                // Simulamos la adición de una bola en un contenedor aleatorio
                int contenedorIndex = (int) (Math.random() * SimulacionView.NUM_CONTENEDORES);
                simulacionView.agregarBola(contenedorIndex);  // Actualizamos la simulación pasando el índice correcto

                // Simulamos un retardo de procesamiento
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
