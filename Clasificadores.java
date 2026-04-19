public class Clasificadores extends Thread{
    private Buzon buzonClasificacion;
    private Buzon buzonConsolidacion;
    private int ns;
    public Clasificadores(Buzon buzonClasificacion, Buzon buzonConsolidacion, int ns) {
        this.buzonClasificacion = buzonClasificacion;
        this.buzonConsolidacion = buzonConsolidacion;
        this.ns = ns;
    }
    


}