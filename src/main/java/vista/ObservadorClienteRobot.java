package vista;

import models.EventoSistema;

import java.util.Observable;

public class ObservadorClienteRobot extends ObservadorAbstracto {
    IVistaGeneral vista;
    public ObservadorClienteRobot(Observable observado, IVistaGeneral vista) {
        super(observado);
        this.vista = vista;
    }

    @Override
    public void update(Observable obs, Object arg) {
        super.update(obs, arg);

        EventoSistema evento = (EventoSistema) arg;
        String mensaje = evento.getMensaje();

        if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOPEDIDO)) {
            vista.appendLogCliente(evento.getPedido().getCliente().toString() + " creó un pedido");
        } else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVIAJE)) {
            vista.appendLogCliente("++++ " + evento.getPedido().getCliente().toString() + " está en situación de viaje confirmado");
        } else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVEHICULO)) {
            vista.appendLogCliente("**** " + evento.getViaje().getPedido().getCliente().toString() + " le asignaron el vehículo " + evento.getViaje().getVehiculo().toString());
        } else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOCHOFER)) {
            vista.appendLogCliente("☺☺☺☺ " + evento.getViaje().getPedido().getCliente().toString() + " le asignaron el chofer " + evento.getViaje().getChofer().getNombre());
        } else if (mensaje.equalsIgnoreCase(EventoSistema.PAGADO)) {
            vista.appendLogCliente("$$$$ " + evento.getViaje().getPedido().getCliente().toString() + " pagó el viaje");
        } else if (mensaje.equalsIgnoreCase(EventoSistema.FINALIZADO)) {
            vista.appendLogCliente("•••• El chofer " + evento.getViaje().getChofer().getNombre() + " finalizó el viaje con el pasajero " + evento.getViaje().getPedido().getCliente().toString());
        }
    }
}
