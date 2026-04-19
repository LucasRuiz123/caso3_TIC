import java.util.LinkedList;
import java.util.Queue;
public class BuzonSemiActivaSemiActiva {
    private Queue<Evento> cola = new LinkedList<>();
    private int capacidad;
    public BuzonSemiActivaSemiActiva(int capacidad) {
        this.capacidad = capacidad;
    }

    
    public synchronized boolean depositar(Evento e) {
    if (cola.size() == capacidad) {
        return false;
    }
    cola.add(e);
    return true;
}

public synchronized Evento retirar() {
    if (cola.isEmpty()) {
        return null;
    }
    return cola.poll();
}
}
