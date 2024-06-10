package models;

import java.util.*;
/**
 * Clase que representa un hilo de ejecución del sistema, encargado de manejar la asignación de vehículos a viajes en la simulación.
 */
public class SistemaRunnable extends Thread implements Observer {
    private ArrayList<IVehiculo> vehiculosSistema;
    private Queue<EventoSistema> eventos;
    private BolsaDeViajes bolsaViajes;
    /**
     * Constructor de la clase SistemaRunnable.
     * <b>Pre:</b> La bolsa de viajes y la lista de vehículos no deben ser null.
     *
     * @param bolsa La bolsa de viajes a observar.
     * @param vehiculosSistema La lista de vehículos disponibles en el sistema.
     */
    public SistemaRunnable(BolsaDeViajes bolsa, ArrayList<IVehiculo> vehiculosSistema) {
        this.vehiculosSistema = vehiculosSistema;
        this.bolsaViajes = bolsa;
        bolsa.addObserver(this);
        this.eventos = new LinkedList<>();
    }
    /**
     * Método que se ejecuta cuando inicia el hilo.
     * <b>Pre:</b> La simulación debe estar activa.
     * <b>Post:</b> El hilo se encarga de manejar la asignación de vehículos a viajes en la simulación.
     */
    public void run() {
        while (bolsaViajes.getSimulacionActiva()) { // Hasta q se pare la simulacion
            if (!eventos.isEmpty()) { // Cuando recibe un evento de nuevo viaje
                IViaje viaje = eventos.poll().getViaje();
                if (viaje != null && !vehiculosSistema.isEmpty()) {
                    IVehiculo vehiculoAsignado = getVehiculoValido(viaje);
                    if (vehiculoAsignado != null) {
                        bolsaViajes.asignarVehiculo(viaje, vehiculoAsignado);
                    }
                    else {
                        viaje.setEstado_de_viaje("RECHAZADO");
                        bolsaViajes.rechazarViaje(viaje);
                    }
                }
            }
        }
    }
    /**
     * Método privado para obtener el vehículo válido con mayor prioridad para un viaje dado.
     *
     * @param viaje El viaje para el cual se busca un vehículo válido.
     * @return El vehículo válido con mayor prioridad, o null si no se encuentra ninguno.
     */
    private IVehiculo getVehiculoValido(IViaje viaje) {
        IVehiculo mejor = null;
        Iterator<IVehiculo> it = this.vehiculosSistema.iterator();
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
    /**
     * Método de la interfaz Observer que se ejecuta cuando se actualiza el estado de la bolsa de viajes.
     * <b>Pre:</b> El observable debe ser la bolsa de viajes.
     *
     * @param obs El objeto Observable que notificó el cambio.
     * @param arg El argumento pasado por el objeto Observable.
     */
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
