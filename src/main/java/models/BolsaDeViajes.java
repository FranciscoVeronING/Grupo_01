package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

/**
 * La clase bolsa de viajes maneja y administra una colección de viajes y pedidos, y notifica a sus observadores sobre ciertos eventos
 */
public class BolsaDeViajes extends Observable implements Serializable {
    private ArrayList<IViaje> colaDeViajes = new ArrayList<IViaje>();
    private boolean simulacionActiva = true;

    public BolsaDeViajes() {
    }

    /**
     * Agrega un nuevo viaje a la cola de viajes y notifica a los observadores sobre el nuevo viaje.
     * <b>Pre:</b> El parametro viaje no puede ser null ni estar vacio
     * @param viaje el viaje a ser agregado a la cola de viajes
     * <b>Post: </b>El viaje será añadido a `colaDeViajes`
     */
    public synchronized void agregarViaje(IViaje viaje) {
        colaDeViajes.add(viaje);
        setChanged();
        notifyObservers(new EventoSistema(viaje, EventoSistema.NUEVOVIAJE));
    }

    /**
     *  Devuelve una copia de la lista de viajes
     *  <b>Pre:</b> La instancia de `BolsaDeViajes` debe estar correctamente inicializada
     *  <b>Post:</b> Se devolverá una nueva lista `ArrayList<IViaje>` que contiene todos los elementos de `colaDeViajes` en el momento de la llamada
     * @return una copia de la lista de viajes actuales
     */
    public synchronized ArrayList<IViaje> getViajes() {
        return new ArrayList<>(colaDeViajes);
    }

    /**
     * Detiene la simulación, notifica a todos los hilos en espera y notifica a los observadores sobre la detención.
     * <b>Pre:</b> La instancia de `BolsaDeViajes` debe estar correctamente inicializada
     *<b>Post</b> La variable `simulacionActiva` se establecerá en `false`.
     *<b>Post</b> Se notificará a todos los hilos que están esperando en el monitor del objeto.
     */
    public synchronized void detenerSimulacion() {
        this.simulacionActiva = false;
        notifyAll();
        setChanged();
        notifyObservers(new EventoSistema(EventoSistema.STOP));
    }

    public synchronized boolean getSimulacionActiva() {
        return simulacionActiva;
    }
    /**
     * Devuelve el primer viaje en la cola que no tiene chofer asignado (estado "CON VEHICULO").
     *
     *<b>Pre:</b> - La instancia de `BolsaDeViajes` debe estar correctamente inicializada.
     * <b>Pre:</b> La lista `colaDeViajes` debe contener objetos que implementen la interfaz `IViaje`.
     * <b>Post:</b>Si existe un viaje con el estado "CON VEHICULO" en `colaDeViajes`, el primer viaje encontrado será devuelto.
     * <b>Post:</b> Si no existe tal viaje, se devolverá `null`.
     * @return el primer viaje con el estado "CON VEHICULO", o `null` si no se encuentra ninguno.
     */
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

    /**
     * Maneja el pago de un viaje, actualizando su estado y notificando a los observadores
     * <b>Pre:</b>
     * - La instancia de `BolsaDeViajes` debe estar correctamente inicializada.
     * - El parámetro `viaje` no debe ser nulo y debe ser un objeto válido que implementa la interfaz `IViaje`.
     * - El viaje debe estar en uno de los estados que permiten ser pagado ("INICIADO" o "RECHAZADO").
     * <b>Post:</b>
     * - Si la simulación está activa y el estado del viaje es "INICIADO", el viaje será marcado como pagado, se notificará a los observadores y se despertarán todos los hilos en espera.
     * - Si la simulación no está activa, el viaje será removido de `colaDeViajes`, finalizado, y se notificará a los observadores de su eliminación debido a la finalización de la simulación.
     * - Los observadores serán notificados del estado del viaje, ya sea pagado o eliminado debido a la detención de la simulación.
     *
     * @param viaje el viaje a ser procesado para pago.
     */
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

    /**
     * Maneja la finalización de un viaje, actualizando su estado y notificando a los observadores
     * <b>Pre:</b>
     * - La instancia de `BolsaDeViajes` debe estar correctamente inicializada.
     * - El parámetro `viaje` no debe ser nulo y debe ser un objeto válido que implementa la interfaz `IViaje`.
     * - El viaje debe estar en uno de los estados que permiten ser finalizado ("PAGADO" o "RECHAZADO").
     *
     * <b>Post:</b>
     * - Si la simulación está activa y el estado del viaje es "PAGADO", el viaje será finalizado después de una espera de 1 segundo.
     * - Se notificará a los observadores que el viaje ha sido finalizado.
     * - Si la simulación no está activa, el viaje será finalizado y se notificará a los observadores de su eliminación debido a la finalización de la simulación.
     * - Los observadores serán notificados del estado del viaje, ya sea finalizado o eliminado debido a la detención de la simulación.
     *
     * @param viaje el viaje a ser procesado para finalizar.
     * @throws InterruptedException si el hilo actual es interrumpido mientras espera.
     */
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
    /**
     * Asigna un vehículo a un viaje, actualizando el estado del viaje y notificando a los observadores.
     *
     * <b>Pre:</b>
     * - La instancia de `BolsaDeViajes` debe estar correctamente inicializada.
     * - El parámetro `viaje` no debe ser nulo y debe ser un objeto válido que implementa la interfaz `IViaje`.
     * - El parámetro `vehiculo` no debe ser nulo y debe ser un objeto válido que implementa la interfaz `IVehiculo`.
     * <b>Post:</b>
     * - El vehículo será asignado al viaje especificado mediante el método `setVehiculo` del objeto `IViaje`.
     * - Se notificará a los observadores con un evento que indique que un nuevo vehículo ha sido asignado al viaje.
     * - Todos los hilos en espera en el monitor del objeto serán notificados.
     *
     * @param viaje el viaje al cual se asignará el vehículo.
     * @param vehiculo el vehículo a ser asignado al viaje.
     */
    public synchronized void asignarVehiculo(IViaje viaje, IVehiculo vehiculo) {
        viaje.setVehiculo(vehiculo); // Setea el vehiculo como ocupado
        setChanged();
        notifyObservers(new EventoSistema(viaje, EventoSistema.NUEVOVEHICULO));
        notifyAll();
    }

    /**
     * Asigna un chofer a un viaje, actualizando el estado del viaje y notificando a los observadores.
     * <b>Pre:</b>
     * - La instancia de `BolsaDeViajes` debe estar correctamente inicializada.
     * - El parámetro `viaje` no debe ser nulo y debe ser un objeto válido que implementa la interfaz `IViaje`.
     * - El parámetro `e` no debe ser nulo y debe ser un objeto válido de tipo `Empleado`.
     * <b>Post:</b>
     * - El chofer será asignado al viaje especificado mediante el método `setChofer` del objeto `IViaje`.
     * - Se notificará a los observadores con un evento que indique que un nuevo chofer ha sido asignado al viaje.
     * - Todos los hilos en espera en el monitor del objeto serán notificados.
     *
     * @param viaje el viaje al cual se asignará el chofer.
     * @param e el chofer a ser asignado al viaje.
     */

    public synchronized void asignarChofer(IViaje viaje, Empleado e) {
        viaje.setChofer(e);
        setChanged();
        notifyObservers(new EventoSistema(viaje, EventoSistema.NUEVOCHOFER));
        notifyAll();
    }

    /**
     * Lanza un nuevo pedido, notificando a los observadores.
     * <b>Pre:</b>
     * - La instancia de `BolsaDeViajes` debe estar correctamente inicializada.
     * - El parámetro `p` no debe ser nulo y debe ser un objeto válido de tipo `Pedido`.
     * <b>Post:</b>
     * - Se notificará a los observadores con un evento que indique que se ha lanzado un nuevo pedido.
     * - Todos los hilos en espera en el monitor del objeto serán notificados.
     *
     * @param p el pedido que se va a lanzar.
     */
    public synchronized void lanzarPedido(Pedido p) {
        setChanged();
        notifyObservers(new EventoSistema(p, EventoSistema.NUEVOPEDIDO));
        notifyAll(); // si le saco el synchronized y el notifyall no funciona :)
    }

    /**
     * Rechaza un viaje de la lista de viajes y notifica a los observadores.
     * <b>Pre:</b>
     * - La instancia de `BolsaDeViajes` debe estar correctamente inicializada.
     * - El parámetro `viaje` no debe ser nulo y debe ser un objeto válido que implemente la interfaz `IViaje`.
     *
     * <b>Post:</b>
     * - El viaje será removido de la lista `colaDeViajes`.
     * - Se notificará a los observadores con un evento que indique que el viaje ha sido rechazado.
     *
     * @param viaje el viaje que se va a rechazar.
     */
    public synchronized void rechazarViaje(IViaje viaje) {
        colaDeViajes.remove(viaje);
        setChanged();
        notifyObservers(new EventoSistema(viaje, EventoSistema.RECHAZADO));
    }

    /**
     * Rechaza un pedido y notifica a los observadores.
     * <b>Pre:</b>
     * - La instancia de `BolsaDeViajes` debe estar correctamente inicializada.
     * - El parámetro `p` no debe ser nulo y debe ser un objeto válido de tipo `Pedido`.
     * <b>Post:</b>
     * - Se notificará a los observadores con un evento que indique que el pedido ha sido rechazado.
     * @param p el pedido que se va a rechazar.
     */
    public synchronized void rechazarPedido(Pedido p) {
        setChanged();
        notifyObservers(new EventoSistema(p, EventoSistema.PEDIDORECHAZADO));
    }

    /**
     * Rechaza un pedido del cliente debido a la falta de choferes disponibles y notifica a los observadores.
     * <b>Pre:</b>
     * - La instancia de `BolsaDeViajes` debe estar correctamente inicializada.
     * - El parámetro `c` no debe ser nulo y debe ser un objeto válido de tipo `Cliente`.
     * <b>Post:</b>
     * - Se notificará a los observadores con un evento que indique que el pedido del cliente ha sido rechazado debido a la falta de choferes disponibles.
     *
     * @param c el cliente cuyo pedido será rechazado debido a la falta de choferes disponibles.
     */
    public synchronized void rechazarPedidoPorChoferes(Cliente c) {
        setChanged();
        notifyObservers(new EventoSistema(c, EventoSistema.PEDIDORECHAZADOPORCHOFERES));
    }

    /**
     * Asigna un cliente a la aplicación y notifica a los observadores.
     * <b>Pre:</b>
     * - La instancia de `BolsaDeViajes` debe estar correctamente inicializada.
     * - El parámetro `c` no debe ser nulo y debe ser un objeto válido de tipo `Cliente`.
     * <b>Post:</b>
     * - Se notificará a los observadores con un evento que indique que se ha agregado un nuevo cliente a la aplicación.
     *
     * @param c el cliente que se va a asignar a la aplicación.
     */
    public void asignarClienteApp(Cliente c) {
        setChanged();
        notifyObservers(new EventoSistema(c, EventoSistema.NUEVOCLIENTE));
    }
}
