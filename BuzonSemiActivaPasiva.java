import java.util.LinkedList;
import java.util.Queue;

public class BuzonSemiActivaPasiva {
    private Queue<Evento> cola = new LinkedList<>();
    private int capacidad;

    public BuzonSemiActivaPasiva(int capacidad) {
        this.capacidad = capacidad;
    }

    public synchronized boolean depositar(Evento e) {
    if (cola.size() == capacidad) {
        return false;
    }
    cola.add(e);
    notifyAll(); 
    return true;
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