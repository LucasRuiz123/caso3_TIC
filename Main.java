public class Main {
    public static void main(String[] args) throws InterruptedException {

        int ni = 3;   // sensores
        int ns = 2;   // servidores
        int nc = 2;   // clasificadores
        int tam1 = 10;
        int tam2 = 10;

        int baseEventos = 5;

        // Aca se crean los buzones, los cuales son los monitores de cada thread. Las capacidades (ilimitados o limitados)
        //  son definidos por paramentro pues este indica el tamaño de la cola
        // Este buzon es el intermediario entre sensores y broker
        //BuzonSemiActiva EntradaBuzon = new BuzonSemiActiva(Integer.MAX_VALUE);

        BuzonSemiActivaPasiva buzonEntrada = new BuzonSemiActivaPasiva(Integer.MAX_VALUE);
        // Este buzon es el intermediar entre broker y Administrador
        BuzonSemiActivaSemiActiva buzonAlertas = new BuzonSemiActivaSemiActiva(Integer.MAX_VALUE);
        // Este Buzon es el intermediario entre broker y clasificadores
        BuzonSemiActivaPasiva buzonClasificacion = new BuzonSemiActivaPasiva(tam1);
        BuzonSemiActivaPasiva[] buzonClasificadores = new BuzonSemiActivaPasiva[nc];
        for (int i = 0; i < nc; i++) {
            buzonClasificadores[i] = new BuzonSemiActivaPasiva(tam2);
        }


    

        
        // Se crean los sensores. 
        Sensores[] sensores = new Sensores[ni];
        for (int i = 0; i < ni; i++) {
            int eventos = baseEventos * (i + 1);
            sensores[i] = new Sensores(i + 1, buzonEntrada, eventos, ns);

        }
        // se crea el broker. se neceista uno por cada lista de sensores
        Broker broker = new Broker(buzonEntrada, buzonAlertas, buzonClasificacion);

        broker.start();
        // aca se empiezan a ejecutar los sensores
        for (Sensores s : sensores) {
            s.start();
        }

        // Se necesita saber cual es el ultimo evento por lo cual se hace un join.
        //  de De esta manera podemos asegurar que todos lleguen al tiempo y no inicialicen el broker antes. 
        for (Sensores s : sensores) {
            s.join();
        }

        //Aca podemos depositar el evento fin, el cual el broker puede identificar y meter en el buzon de administrador.
        buzonEntrada.depositar(new Evento(true));

        // ⏳ Esperar broker
        broker.join();

        System.out.println("Sistema terminado (hasta broker)");
    
        Administracion admin = new Administracion(buzonAlertas, buzonClasificacion, nc);
        admin.start();
        admin.join();

    
    
}
}