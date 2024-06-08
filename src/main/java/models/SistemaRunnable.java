package models;

import vista.ObservadorAbstracto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

class SistemaRunnable extends Thread implements Observer {
    private ArrayList<Vehiculo> vehiculosDisponibles;
    private EventoSistema evento = null;
    private BolsaDeViajes bolsaViajes;

    public SistemaRunnable(BolsaDeViajes bolsa, ArrayList<Vehiculo> vehiculosDisponibles) {
        this.vehiculosDisponibles = vehiculosDisponibles;
        this.bolsaViajes = bolsa;
    }

    public void run() {
        while (evento == null || (evento != null && !evento.getMensaje().equalsIgnoreCase(EventoSistema.STOP))) {
            synchronized (this) {
                if (evento != null) {
                    Viaje viaje = evento.getViaje();
                    if (viaje != null && !vehiculosDisponibles.isEmpty()) {
                        Vehiculo vehiculoAsignado = getVehiculoValido(viaje);
                        viaje.setVehiculo(vehiculoAsignado);
                        viaje.setEstado_de_viaje("CON VEHICULO");
                        vehiculosDisponibles.remove(vehiculoAsignado);
                        vehiculosDisponibles.add(vehiculoAsignado);
                        bolsaViajes.agregarViaje(viaje);
                    }
                    evento = null;
                } else {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    private Vehiculo getVehiculoValido(Viaje viaje) {
        Vehiculo mejor = null;
        Iterator<Vehiculo> it = this.vehiculosDisponibles.iterator();
        int maxP = 0;
        while (it.hasNext()) {
            Vehiculo v = it.next();
            if (!v.isOcupado()) {
                Integer prioridad = v.getPrioridad(viaje.getPedido());
                if (prioridad != null && prioridad > maxP) {
                    maxP = prioridad;
                    mejor = v;
                }
            }
        }
        return mejor;
    }

    @Override
    public void update(Observable obs, Object arg) {
        if (obs != this.bolsaViajes) {
            throw new IllegalArgumentException();
        }
        synchronized (this) {
            EventoSistema evento = (EventoSistema) arg;
            if (evento.getMensaje().equalsIgnoreCase(EventoSistema.NUEVOVIAJE) || evento.getMensaje().equalsIgnoreCase(EventoSistema.STOP)) {
                this.evento = evento;
                notifyAll();
            }
        }
    }
}
