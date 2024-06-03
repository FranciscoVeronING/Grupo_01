package models;

import vista.ObservadorAbstracto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;


class SistemaRunnable extends ObservadorAbstracto implements Runnable {
    private ArrayList<Vehiculo> vehiculosDisponibles;
    private EventoSistema evento = null;
    private BolsaDeViajes bolsaViajes;

    public SistemaRunnable(BolsaDeViajes bolsa, ArrayList<Vehiculo> vehiculosDisponibles) {
        super(bolsa);
        this.vehiculosDisponibles = vehiculosDisponibles;
        this.bolsaViajes = bolsa; // medio raro
    }

    public void run() {
        while (evento == null || (evento != null && !evento.getMensaje().equalsIgnoreCase(EventoSistema.STOP))) {
            if (evento != null) { // evento = true si se agrega un viaje a la bolsa
                Viaje viaje = evento.getViaje(); // Obtiene viaje recien agregado, pasado por evento
                if (viaje != null && !vehiculosDisponibles.isEmpty()) {
                    // Asignar un vehículo al viaje
                    Vehiculo vehiculoAsignado = getVehiculoValido(viaje);
                    viaje.setVehiculo(vehiculoAsignado); // Método ficticio para asignar el vehículo al viaje
                    viaje.setEstado_de_viaje("CON VEHICULO");
                    // Lo muevo al final de la lista
                    vehiculosDisponibles.remove(vehiculoAsignado);
                    vehiculosDisponibles.add(vehiculoAsignado);
                    bolsaViajes.agregarViaje(viaje);
                }
                evento = null;
            }
            else { // Si no hay vehiculo se queda esperando (hasta recibir un evento)
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
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
        super.update(obs, arg);
        EventoSistema evento = (EventoSistema) arg;
        // 'activa' el thread para que le asigne un vehiculo al nuevo viaje o para que pare la simulacion
        if (evento.getMensaje().equalsIgnoreCase(EventoSistema.NUEVOVIAJE) || evento.getMensaje().equalsIgnoreCase(EventoSistema.STOP)) this.evento = evento;
    }
}
