package models;

public class EventoSistema {
    private final Viaje v;
    private final boolean stop;

    public EventoSistema(Viaje v) {
        this.v = v;
        this.stop = false;
    }

    public EventoSistema(boolean b) {
        this.v = null;
        this.stop = b;
    }

    public Viaje getViaje() {
        return this.v;
    }

    public boolean isStop() {
        return stop;
    }
}
