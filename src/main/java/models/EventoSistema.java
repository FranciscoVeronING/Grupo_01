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
    public static final String PEDIDORECHAZADO = "PEDIDO RECHAZADO";
    public static final String STOP = "STOP";
    public static final String RECHAZADO = "RECHAZADO";
    public static final String NUEVOCLIENTE = "NUEVO_CLIENTE";
    public static final String PEDIDORECHAZADOPORCHOFERES = "PEDIDO_RECHAZADO_CHOFERES";
    public static final String ELIMINADOSIMULACION = "ELIMINADO_SIMULACION";

    private IViaje v;
    private Pedido p;
    private Cliente c;
    private String mensaje = null;


    public EventoSistema(IViaje v, String mensaje) {
        this.v = v;
        this.p = null;
        this.c = null;
        this.mensaje = mensaje;
    }

    public EventoSistema(Pedido p, String mensaje){
        this.p = p;
        this.mensaje = mensaje;
        this.c = null;
        this.v = null;
    }

    public EventoSistema(String m) {
        this.v = null;
        this.p = null;
        this.c = null;
        this.mensaje = m;
    }

    public EventoSistema(Cliente c, String m) {
        this.v = null;
        this.p = null;
        this.c = c;
        this.mensaje = m;
    }

    public IViaje getViaje() {
        return this.v;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Pedido getPedido() {
        return this.p;
    }

    public Cliente getCliente() {return this.c;}
}
