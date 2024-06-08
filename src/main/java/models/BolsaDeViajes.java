package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

public class BolsaDeViajes extends Observable implements Serializable {
    private ArrayList<IViaje> colaDeViajes = new ArrayList<IViaje>();
    private ArrayList<Pedido> colaDePedidos = new ArrayList<>();
    private boolean simulacionActiva = true;

    public BolsaDeViajes() {
    }

    public synchronized void agregarViaje(IViaje viaje) {
        colaDeViajes.add(viaje);
        setChanged();
        if (viaje.getVehiculo() == null)
            notifyObservers(new EventoSistema(viaje.getViaje(), EventoSistema.NUEVOVIAJE));
        else notifyObservers(new EventoSistema(viaje.getViaje(), EventoSistema.NUEVOVEHICULO));
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

    public synchronized IViaje obtenerViajeSinChofer() {
        IViaje v = null;
        int i = 0;
        while (v == null && i < colaDeViajes.size()) {
            if (colaDeViajes.get(i).getVehiculo() == null) {
                v = colaDeViajes.get(i);
            } else {
                i++;
            }
        }
        if (v != null) {
            colaDeViajes.remove(v);
        }
        return v;
    }


    public synchronized void viajePagado(Viaje viaje) {
        viaje.pagarse();
        setChanged();
        notifyObservers(new EventoSistema(viaje, EventoSistema.PAGADO));
    }

    public synchronized void viajeFinalizado(Viaje viaje) {
        viaje.finalizarse();
        setChanged();
        notifyObservers(new EventoSistema(viaje, EventoSistema.FINALIZADO));
    }

    // Manejo de pedidos

    public synchronized ArrayList<Pedido> getPedidos() {
        return new ArrayList<>(colaDePedidos);
    }

    public synchronized void agregarPedido(Pedido pedido) {
        colaDePedidos.add(pedido);
        setChanged();
        notifyObservers(new EventoSistema(pedido, EventoSistema.NUEVOPEDIDO));
    }

    public synchronized Pedido getPedido(int i) {
        return colaDePedidos.remove(i);
    }
}
