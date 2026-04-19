public class Administracion extends Thread {
    private BuzonSemiActivaSemiActiva buzonAlerta;
    private BuzonSemiActivaPasiva buzonClasificacion;
    private int nc;
    public Administracion(BuzonSemiActivaSemiActiva buzonAlerta, BuzonSemiActivaPasiva buzonClasificacion, int nc) {
        this.buzonAlerta = buzonAlerta;
        this.buzonClasificacion = buzonClasificacion;
        this.nc = nc;
    }
    public void run() {
    try {
        while (true) {
            Evento e;
            while ((e = buzonAlerta.retirar()) == null) {
                Thread.yield();
            }
            if (e.esFin()) {
                for (int i = 0; i < nc; i++) {
                    while (!buzonClasificacion.depositar(new Evento(true))) {
                        Thread.yield();
                    }
                }
                break;
            }
            int aleatorio = (int)(Math.random() * 21);
            // la coneccion con el buzon de clasificacion es semiactiva por lo que usa esta manera de depositar
            if (aleatorio % 4 == 0) {
                while (!buzonClasificacion.depositar(e)) {
                    Thread.yield();
                }
            }
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
}}
    

    

    


