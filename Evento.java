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
    public int getid(){
        return idSensor;
    }
    public int getConsecutivo(){
        return consecutivo;
    }
    @Override
    public String toString() {
        return "Evento[sensor=" + idSensor + ", id=" + consecutivo + ", tipo=" + tipoEvento + "]";
    }
}
