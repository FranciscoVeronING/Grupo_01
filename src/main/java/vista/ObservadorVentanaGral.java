package vista;

import models.EventoSistema;

import java.util.Observable;

public class ObservadorVentanaGral extends ObservadorAbstracto {
	IVistaGeneral vista;
	public ObservadorVentanaGral(Observable observado, IVistaGeneral vista) {
		super(observado);
		this.vista = vista;
	}

	@Override
	public void update(Observable obs, Object arg) {
		super.update(obs, arg);

		EventoSistema evento = (EventoSistema) arg;
		String mensaje = evento.getMensaje();

		if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOPEDIDO)) {
			vista.appendLogGeneral(evento.getPedido().getCliente().toString() + " creo un pedido");
		} else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVIAJE)) {
			vista.appendLogGeneral("++++ " + evento.getPedido().getCliente().toString() + " esta en Situacion de viaje Confirmado");
		} else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVEHICULO)) {
			vista.appendLogGeneral("**** " + evento.getViaje().getPedido().getCliente().toString() + " Le asignaron el vehiculo "+ evento.getViaje().getVehiculo().toString());
		} else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOCHOFER)) {
			vista.appendLogGeneral("☺☺☺☺ " + evento.getViaje().getPedido().getCliente().toString() + " Le asignaron el chofer "+ evento.getViaje().getChofer().getNombre());
		} else if (mensaje.equalsIgnoreCase(EventoSistema.PAGADO)) {
			vista.appendLogGeneral("$$$$ " + evento.getViaje().getPedido().getCliente().toString() + " Pago el viaje ");
		} else if (mensaje.equalsIgnoreCase(EventoSistema.FINALIZADO)) {
			vista.appendLogGeneral("•••• " + "El chofer "+evento.getViaje().getChofer().getNombre() + " Finalizo el viaje con el pasajero "+evento.getViaje().getPedido().getCliente().toString());
		}
	}
}
