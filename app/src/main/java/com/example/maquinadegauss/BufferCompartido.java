package com.example.maquinadegauss;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

class BufferCompartido {
    private BlockingQueue<Componente> buffer;
    private Semaphore semaphore;

    public BufferCompartido(int capacidad) {
        this.buffer = new ArrayBlockingQueue<>(capacidad);
        this.semaphore = new Semaphore(1);  // Sincronización con semáforo
    }

    // Getters
    public BlockingQueue<Componente> getBuffer() {
        return buffer;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    // Setters
    public void setBuffer(BlockingQueue<Componente> buffer) {
        this.buffer = buffer;
    }

    public void setSemaphore(Semaphore semaphore) {
        this.semaphore = semaphore;
    }

    public void producir(Componente componente) throws InterruptedException {
        semaphore.acquire();
        buffer.put(componente);  // Colocar componente en el buffer
        semaphore.release();
    }

    public Componente consumir() throws InterruptedException {
        semaphore.acquire();
        Componente componente = buffer.take();  // Consumir componente del buffer
        semaphore.release();
        return componente;
    }
}

