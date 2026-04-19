public class Broker extends Thread {
    private BuzonSemiActivaPasiva buzonEntrada;
    private BuzonSemiActivaSemiActiva buzonAlertas;
    private BuzonSemiActivaPasiva buzonClasificacion;
    
    public Broker(BuzonSemiActivaPasiva buzonEntrada, BuzonSemiActivaSemiActiva buzonAlertas, BuzonSemiActivaPasiva      buzonClasificacion) {
        this.buzonEntrada = buzonEntrada;
        this.buzonAlertas = buzonAlertas;
        this.buzonClasificacion = buzonClasificacion;
        
    }
    public void run() {
    try {
        while (true) {
            Evento e = buzonEntrada.retirar();

            if (e.esFin()) {
                buzonAlertas.depositar(e);
                break;
            }

            int aleatorio = (int)(Math.random() * 201);

            if (aleatorio % 8 == 0) {
                while (!buzonAlertas.depositar(e)) {
                    Thread.yield();
                }
            } else {
                while (!buzonClasificacion.depositar(e)) {
                    Thread.yield();
                }
            }
        }
    } catch (InterruptedException ex) {
        ex.printStackTrace();
    }
}
}


    
 