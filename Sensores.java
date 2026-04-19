public class Sensores extends Thread  {
    private int idSensor;
    private int numeroEventos;
    private Buzon buzonEntrada;
    private int ns;
     
    public Sensores(int idSensor, Buzon buzonEntrada,int numeroEventos, int ns) {
        this.idSensor = idSensor;
        this.buzonEntrada = buzonEntrada;
        this.numeroEventos = numeroEventos;
        this.ns = ns;
    }

    public  void run() {
        for (int i = 0; i < numeroEventos; i++) {
            try{
                int tipo = (int)(Math.random() * ns) + 1;

                Evento e = new Evento(idSensor, i, tipo);

                buzonEntrada.depositar(e);

            }catch(Exception e){
                e.printStackTrace();
            }
         }

    }
}