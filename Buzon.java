import java.util.LinkedList;
import java.util.Queue;

class Buzon {
    private Queue<Evento> cola = new LinkedList<>();
    private int capacidad; // usar Integer.MAX_VALUE si es ilimitado

    public Buzon(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized void depositar(Evento e) throws InterruptedException {
        while (cola.size() == capacidad) {
            wait();
        }
        cola.add(e);
        notifyAll();
    }

    public synchronized Evento retirar() throws InterruptedException {
        while (cola.isEmpty()) {
            wait();
        }
        Evento e = cola.poll();
        notify();
        return e;
    }
}   