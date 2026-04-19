public class Evento {
    private int idSensor;
    private int consecutivo;
    private int tipoEvento;
    private boolean esFin;
    public Evento(int idSensor, int consecutivo, int tipoEvento ) {
        this.idSensor = idSensor;
        this.consecutivo = consecutivo;
        this.tipoEvento = tipoEvento;
        this.esFin = false;
    }


    public Evento(boolean esFin) {
        this.esFin = esFin;
    }

    public boolean esFin() {
        return esFin;
    }

    public int getTipo() {
        return tipoEvento;
    }
}
