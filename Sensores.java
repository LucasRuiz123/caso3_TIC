public class Sensores extends Thread {
    private int idSensor;
    private int numeroEventos;
    private BuzonSemiActivaPasiva buzonEntrada;
    private int ns;

    public Sensores(int idSensor, BuzonSemiActivaPasiva buzonEntrada, int numeroEventos, int ns) {
        this.idSensor = idSensor;
        this.buzonEntrada = buzonEntrada;
        this.numeroEventos = numeroEventos;
        this.ns = ns;
    }

    public void run() {
        for (int i = 1; i <= numeroEventos; i++) {
            int tipo = (int)(Math.random() * ns) + 1;

            Evento e = new Evento(idSensor, i, tipo);

            // 🔁 espera semi-activa
            while (!buzonEntrada.depositar(e)) {
                Thread.yield();
            }

            System.out.println("Sensor " + idSensor + " produjo " + e);
        }
    }
}