package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class BolsaDeViajes extends Observable implements Serializable {
    private ArrayList<IViaje> colaDeViajes = new ArrayList<IViaje>();
    private boolean simulacionActiva = true;

    public void agregarViaje(Viaje viaje) {
        colaDeViajes.add(viaje);
        setChanged();
        notifyObservers(new EventoSistema(viaje));
    }

    public ArrayList<IViaje> getViajes() {
        return colaDeViajes;
    }

    public void detenerSimulacion() {
        this.simulacionActiva = false;
        notifyAll();
        setChanged();
        notifyObservers();
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
        return v;
    }
}
