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
        while (simulacionActiva && !(viaje.getEstado_de_viaje().equalsIgnoreCase("INICIADO") || viaje.getEstado_de_viaje().equalsIgnoreCase("RECHAZADO")))
            try {
                wait();
            } catch (InterruptedException e) {}
        if (simulacionActiva) {
            if (viaje.getEstado_de_viaje().equalsIgnoreCase("INICIADO")) {
                viaje.pagarse();
                setChanged();
                notifyObservers(new EventoSistema(viaje, EventoSistema.PAGADO));
                notifyAll();
            }
        } else {
            colaDeViajes.remove(viaje);
            viaje.finalizarse();
            setChanged();
            notifyObservers(new EventoSistema(viaje, EventoSistema.ELIMINADOSIMULACION));
        }
    }

    public synchronized void viajeFinalizado(IViaje viaje) throws InterruptedException {
        while (simulacionActiva && !(viaje.getEstado_de_viaje().equalsIgnoreCase("PAGADO") || viaje.getEstado_de_viaje().equalsIgnoreCase("RECHAZADO"))) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        if (simulacionActiva) {
            Thread.currentThread().sleep(1000);
            if (viaje.getEstado_de_viaje().equalsIgnoreCase("PAGADO")) {
                viaje.finalizarse();
                setChanged();
                notifyObservers(new EventoSistema(viaje, EventoSistema.FINALIZADO));
                notifyAll();
            }
        } else {
            viaje.finalizarse();
            setChanged();
            notifyObservers(new EventoSistema(viaje, EventoSistema.ELIMINADOSIMULACION));
        }
    }

    public synchronized void asignarVehiculo(IViaje viaje, IVehiculo vehiculo) {
        viaje.setVehiculo(vehiculo); // Setea el vehiculo como ocupado
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

    public synchronized void rechazarPedido(Pedido p) {
        setChanged();
        notifyObservers(new EventoSistema(p, EventoSistema.PEDIDORECHAZADO));
    }

    public synchronized void rechazarPedidoPorChoferes(Cliente c) {
        setChanged();
        notifyObservers(new EventoSistema(c, EventoSistema.PEDIDORECHAZADOPORCHOFERES));
    }

    public void asignarClienteApp(Cliente c) {
        setChanged();
        notifyObservers(new EventoSistema(c, EventoSistema.NUEVOCLIENTE));
    }
}
