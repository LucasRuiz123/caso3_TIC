public class Servidores extends Thread {
    private BuzonPasivo buzonPasivo;

    public Servidores(BuzonPasivo buzonPasivo) {
        this.buzonPasivo = buzonPasivo;
    }

    public void run() {
        try {
            while (true) {
                Evento e = buzonPasivo.retirar(); // pasivo
                if (e.esFin()) {
                    break;
                }
                int aleatorio = (int)(Math.random() * (1000 - 100 + 1)) + 100;
                System.out.println("Servidor procesando evento, tiempo: " + aleatorio);
                Thread.sleep(aleatorio);
                System.out.println("Servidor terminó el evento ");
            }
            System.out.println("Servidor termina !");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}