package models;

/**
 * Clase utilizada para indicar el estado en el que se encuentra el viaje
 * <b>Post:</b> El viaje tendra un mensaje con su estado actual
 */
public class EventoSistema {
    // Opciones de mensajes
    public static final String PAGADO = "PAGADO";
    public static final String NUEVOCHOFER = "NUEVO_CHOFER";
    public static final String NUEVOVEHICULO = "NUEVO_VEHICULO";
    public static final String FINALIZADO = "FINALIZADO";
    public static final String NUEVOVIAJE = "NUEVO_VIAJE";
    public static final String NUEVOPEDIDO = "NUEVO PEDIDO";
    public static final String STOP = "STOP";

    private Viaje v;
    private String mensaje = null;


    public EventoSistema(Viaje v, String mensaje) {
        this.v = v;
        this.mensaje = mensaje;
    }

    public EventoSistema(String m) {
        this.v = null;
        this.mensaje = m;
    }

    public Viaje getViaje() {
        return this.v;
    }

    public String getMensaje() {
        return mensaje;
    }
}
