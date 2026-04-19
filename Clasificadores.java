public class Clasificadores extends Thread {
    private int id;
    private BuzonSemiActivaPasiva buzonClasificacion;
    private BuzonPasivo[] buzonesServidores;

    public Clasificadores(int id, BuzonSemiActivaPasiva buzonClasificacion, BuzonPasivo[] buzonesServidores) {
        this.id = id;
        this.buzonClasificacion = buzonClasificacion;
        this.buzonesServidores = buzonesServidores;
    }

    public void run() {
    try {
        while (true) {
            Evento e;

            while ((e = buzonClasificacion.retirar()) == null) {
                Thread.yield();
            }

            if (e.esFin()) {
                break;
            }

            int destino = e.getTipo() - 1;
            buzonesServidores[destino].depositar(e);
            System.out.println("Clasificador " + id + " envió evento a servidor " + destino);
        }
        System.out.println("Clasificador " + id + " terminó");
    } catch (InterruptedException ex) {
        ex.printStackTrace();
    }
}

}