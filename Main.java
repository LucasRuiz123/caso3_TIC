public class Main {
    public static void main(String[] args) throws InterruptedException {

        int ni = 3;   // sensores
        int ns = 2;   // servidores
        int nc = 2;   // clasificadores
        int tam1 = 10;
        int tam2 = 10;

        int baseEventos = 5;

        // 🧱 BUZONES
        BuzonSemiActivaPasiva buzonEntrada = new BuzonSemiActivaPasiva(Integer.MAX_VALUE);
        BuzonSemiActivaSemiActiva buzonAlertas = new BuzonSemiActivaSemiActiva(tam1);
        BuzonSemiActivaPasiva buzonClasificacion = new BuzonSemiActivaPasiva(tam2);

        BuzonPasivo[] buzonesServidores = new BuzonPasivo[ns];
        for (int i = 0; i < ns; i++) {
            buzonesServidores[i] = new BuzonPasivo(Integer.MAX_VALUE);
        }

        // Creacion de servidores
        Servidores[] servidores = new Servidores[ns];
        for (int i = 0; i < ns; i++) {
            servidores[i] = new Servidores(buzonesServidores[i]);
            servidores[i].start();
        }

        // Creacion de clasificadores
        Clasificadores[] clasificadores = new Clasificadores[nc];
        for (int i = 0; i < nc; i++) {
            clasificadores[i] = new Clasificadores(i, buzonClasificacion, buzonesServidores);
            clasificadores[i].start();
        }

        // Creacion de administracion
        Administracion admin = new Administracion(buzonAlertas, buzonClasificacion, nc);
        admin.start();

        // Creacion de broker
        Broker broker = new Broker(buzonEntrada, buzonAlertas, buzonClasificacion);
        broker.start();

        // Creacion de sensores
        Sensores[] sensores = new Sensores[ni];
        for (int i = 0; i < ni; i++) {
            sensores[i] = new Sensores(i, buzonEntrada, baseEventos, ns);
            sensores[i].start();
        }
        System.out.println("Sistema iniciado correctamente");

        // esperar a unir todos los sensores
        for (int i = 0; i < ni; i++) {
            sensores[i].join();
        }
        System.out.println("Todos los sensores han terminado de generar eventos");

        // Evento que crea el evento fin. 
        buzonEntrada.depositar(new Evento(true));
        System.out.println("Evento de fin enviado al broker");

        // Esperar que el broker termine de procesar el evento fin
        broker.join();
        // Esperar que el admin termine de procesar el evento fin
        admin.join();
        // unir los clasificadores
        for (int i = 0; i < nc; i++) {
            clasificadores[i].join();
        }

        //  Crear los eventos fin para cada clasificador en el buzon de servidores
        for (int i = 0; i < ns; i++) {
            buzonesServidores[i].depositar(new Evento(true));
        }

        // union de los servidores
        for (int i = 0; i < ns; i++) {
            servidores[i].join();
        }
        // avisar que el sistema ha finalizado correctamente
        System.out.println("Sistema finalizado correctamente");
    }
}