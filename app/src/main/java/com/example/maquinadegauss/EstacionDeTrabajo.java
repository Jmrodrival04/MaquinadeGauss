package com.example.maquinadegauss;

class EstacionDeTrabajo extends Thread {
    private BufferCompartido buffer;
    private int id;

    public EstacionDeTrabajo(BufferCompartido buffer, int id) {
        this.buffer = buffer;
        this.id = id;
    }

    // Getters
    public BufferCompartido getBuffer() {
        return buffer;
    }

    public int getStationId() {
        return id;
    }

    // Setters
    public void setBuffer(BufferCompartido buffer) {
        this.buffer = buffer;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("ID del hilo: " + super.getId());  // Usando el ID del hilo
                Componente componente = new Componente("Componente de Estación " + id);
                buffer.producir(componente);
                System.out.println("Estación de trabajo " + getStationId() + " produjo: " + componente.getNombre());
                Thread.sleep(100);  // Simula tiempo de producción
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
