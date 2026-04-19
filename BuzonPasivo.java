import java.util.LinkedList;
import java.util.Queue;

public class BuzonPasivo {
    private Queue<Evento> cola = new LinkedList<>();
    private int capacidad;

    public BuzonPasivo(int capacidad) {
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
        notifyAll();
        return e;
    }
}