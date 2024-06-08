package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class BolsaDeViajes extends Observable implements Serializable {
    private ArrayList<IViaje> colaDeViajes = new ArrayList<IViaje>();
    private boolean simulacionActiva = true;

    public BolsaDeViajes() {
    }

    public synchronized void agregarViaje(IViaje viaje) {
        colaDeViajes.add(viaje);
        setChanged();
        notifyObservers(new EventoSistema(viaje, EventoSistema.NUEVOVIAJE));
    }

    public synchronized ArrayList<IViaje> getViajes() {
        return new ArrayList<>(colaDeViajes);
    }

    public synchronized void detenerSimulacion() {
        this.simulacionActiva = false;
        notifyAll();
        setChanged();
        notifyObservers(new EventoSistema(EventoSistema.STOP));
    }

    public synchronized boolean getSimulacionActiva() {
        return simulacionActiva;
    }

    public synchronized IViaje viajeSinChofer() {
        // Busca viaje
        IViaje v = null;
        int i = 0;
        while (v == null && i < colaDeViajes.size()) {
            if (colaDeViajes.get(i).getEstado_de_viaje().equalsIgnoreCase("CON VEHICULO")) {
                v = colaDeViajes.get(i);
            } else {
                i++;
            }
        }
        return v;
    }


    public synchronized void viajePagado(IViaje viaje) {
        while (!(viaje.getEstado_de_viaje().equalsIgnoreCase("INICIADO") || viaje.getEstado_de_viaje().equalsIgnoreCase("RECHAZADO")))
            try {
                wait();
            } catch (InterruptedException e) {}
        if (viaje.getEstado_de_viaje().equalsIgnoreCase("INICIADO")) {
            viaje.pagarse();
            setChanged();
            notifyObservers(new EventoSistema(viaje, EventoSistema.PAGADO));
            notifyAll();
        }
    }

    public synchronized void viajeFinalizado(IViaje viaje) {
        while (!(viaje.getEstado_de_viaje().equalsIgnoreCase("PAGADO") || viaje.getEstado_de_viaje().equalsIgnoreCase("RECHAZADO"))) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        System.out.println("Salgo del while");
        if (viaje.getEstado_de_viaje().equalsIgnoreCase("PAGADO")) {
            viaje.finalizarse();
            setChanged();
            notifyObservers(new EventoSistema(viaje, EventoSistema.FINALIZADO));
            notifyAll();
        }
    }

    public synchronized void asignarVehiculo(IViaje viaje, IVehiculo vehiculo) {
        viaje.setVehiculo(vehiculo);
        setChanged();
        notifyObservers(new EventoSistema(viaje, EventoSistema.NUEVOVEHICULO));
        notifyAll();
    }

    public synchronized void asignarChofer(IViaje viaje, Empleado e) {
        viaje.setChofer(e);
        setChanged();
        notifyObservers(new EventoSistema(viaje, EventoSistema.NUEVOCHOFER));
        notifyAll();
    }

    public synchronized void lanzarPedido(Pedido p) {
        setChanged();
        notifyObservers(new EventoSistema(p, EventoSistema.NUEVOPEDIDO));
        notifyAll(); // si le saco el synchronized y el notifyall no funciona :)
    }

    public synchronized void rechazarViaje(IViaje viaje) {
        colaDeViajes.remove(viaje);
        setChanged();
        notifyObservers(new EventoSistema(viaje, EventoSistema.RECHAZADO));
    }
}
