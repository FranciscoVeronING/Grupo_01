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
            vista.appendLogCliente(" Usuario "+ evento.getPedido().getCliente().getNombre_usuario() + " creó un pedido\n");
        } else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVIAJE)) {
            vista.appendLogCliente("++++ Usuario "+ evento.getPedido().getCliente().getNombre_usuario()  + " está en situación de viaje confirmado\n");
        } else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVEHICULO)) {
            vista.appendLogCliente("**** Usuario "+ evento.getPedido().getCliente().getNombre_usuario() + " le asignaron el vehículo " + evento.getViaje().getVehiculo().toString()+"\n");
        } else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOCHOFER)) {
            vista.appendLogCliente("☺☺☺☺ Usuario "+ evento.getPedido().getCliente().getNombre_usuario() + " le asignaron el chofer " + evento.getViaje().getChofer().getNombre()+"\n");
        } else if (mensaje.equalsIgnoreCase(EventoSistema.PAGADO)) {
            vista.appendLogCliente("$$$$ Usuario "+ evento.getPedido().getCliente().getNombre_usuario() + " pagó el viaje\n");
        } else if (mensaje.equalsIgnoreCase(EventoSistema.FINALIZADO)) {
            vista.appendLogCliente("•••• El chofer " + evento.getViaje().getChofer().getNombre() + " finalizó el viaje con el Usuario "+ evento.getPedido().getCliente().getNombre_usuario()+"\n");
        }
    }
}
