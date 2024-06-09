package vista;

import models.EventoSistema;

import java.util.Observable;

/**
 * La clase extiende ObservadorAbstracto y actúa como un observador específico para los eventos relacionados con los choferes en el sistema
 */
public class ObservadorChofer extends ObservadorAbstracto {
    IVistaGeneral vista;
    public ObservadorChofer(Observable observado, IVistaGeneral vista) {
        super(observado);
        this.vista = vista;
    }

    @Override
    public void update(Observable obs, Object arg) {
        super.update(obs, arg);

        EventoSistema evento = (EventoSistema) arg;
        String mensaje = evento.getMensaje();

        if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOCHOFER)) {
            vista.appendLogChofer("☺☺☺☺ " + evento.getViaje().getChofer().getNombre() + " fue asignado al viaje del cliente " + evento.getViaje().getPedido().getCliente().toString());
        } else if (mensaje.equalsIgnoreCase(EventoSistema.PAGADO)) {
            vista.appendLogChofer("$$$$ " + evento.getViaje().getChofer().getNombre() + " recibió el pago del viaje realizado por " + evento.getViaje().getPedido().getCliente().toString());
        } else if (mensaje.equalsIgnoreCase(EventoSistema.FINALIZADO)) {
            vista.appendLogChofer("•••• " + "El chofer " + evento.getViaje().getChofer().getNombre() + " finalizó el viaje con el pasajero " + evento.getViaje().getPedido().getCliente().toString());
        }

    }
}
