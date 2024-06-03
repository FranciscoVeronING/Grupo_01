package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class BolsaDeViajes extends Observable implements Serializable {
    private ArrayList<IViaje> colaDeViajes = new ArrayList<IViaje>();
    private boolean simulacionActiva = true;

    public BolsaDeViajes() {
    }

    public void agregarViaje(Viaje viaje) {
        colaDeViajes.add(viaje);
        setChanged();
        if (viaje.getVehiculo() == null)
            notifyObservers(new EventoSistema(viaje, EventoSistema.NUEVOVIAJE));
        else notifyObservers(new EventoSistema(viaje, EventoSistema.NUEVOVEHICULO));
    }

    public ArrayList<IViaje> getViajes() {
        return colaDeViajes;
    }

    public void detenerSimulacion() {
        this.simulacionActiva = false;
        notifyAll();
        setChanged();
        notifyObservers(new EventoSistema(EventoSistema.STOP));
    }

    public boolean getSimulacionActiva() {
        return simulacionActiva;
    }

    public Viaje obtenerViajeSinChofer() {
        Viaje v = null;
        int i = 0;
        while (v == null && i < colaDeViajes.size()) {
            if (colaDeViajes.get(i).getVehiculo() == null) v = (Viaje) colaDeViajes.get(i);
            else i++;
        }
        colaDeViajes.remove(v);
        return v;
    }

    public void viajePagado(Viaje viaje) {
        viaje.pagarse();
        setChanged();
        notifyObservers(new EventoSistema(viaje, EventoSistema.PAGADO));
    }

    public void viajeFinalizado(Viaje viaje) {
        viaje.finalizarse();
        setChanged();
        notifyObservers(new EventoSistema(viaje, EventoSistema.FINALIZADO));
    }
}
