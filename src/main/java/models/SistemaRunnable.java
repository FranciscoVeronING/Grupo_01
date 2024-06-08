package models;

import vista.ObservadorAbstracto;

import java.util.*;

public class SistemaRunnable extends Thread implements Observer {
    private ArrayList<IVehiculo> vehiculosDisponibles;
    private Queue<EventoSistema> eventos;
    private BolsaDeViajes bolsaViajes;

    public SistemaRunnable(BolsaDeViajes bolsa, ArrayList<IVehiculo> vehiculosDisponibles) {
        this.vehiculosDisponibles = vehiculosDisponibles;
        this.bolsaViajes = bolsa;
        bolsa.addObserver(this);
        this.eventos = new LinkedList<>();
    }

    public void run() {
        while (bolsaViajes.getSimulacionActiva()) { // Hasta q se pare la simulacion
            if (!eventos.isEmpty()) { // Cuando recibe un evento de nuevo viaje
                IViaje viaje = eventos.poll().getViaje();
                if (viaje != null && !vehiculosDisponibles.isEmpty()) {
                    IVehiculo vehiculoAsignado = getVehiculoValido(viaje);
                    if (vehiculoAsignado != null) {
                        bolsaViajes.asignarVehiculo(viaje, vehiculoAsignado);
                        viaje.setVehiculo(vehiculoAsignado);
                    }
                    else {
                        viaje.setEstado_de_viaje("RECHAZADO");
                        bolsaViajes.rechazarViaje(viaje);
                    }
                }
            }
        }
    }

    private IVehiculo getVehiculoValido(IViaje viaje) {
        IVehiculo mejor = null;
        Iterator<IVehiculo> it = this.vehiculosDisponibles.iterator();
        int maxP = 0;
        while (it.hasNext()) {
            IVehiculo v = it.next();
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
                this.eventos.add(evento);
            }
        }
    }
}
