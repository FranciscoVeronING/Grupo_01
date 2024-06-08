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
			vista.appendLogGeneral("Usuario "+ evento.getPedido().getCliente().getNombre_usuario() + " creo un pedido\n");
		} else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVIAJE)) {
			vista.appendLogGeneral("++++ Usuario "+ evento.getPedido().getCliente().getNombre_usuario() + " esta en Situacion de viaje Confirmado\n");
		} else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVEHICULO)) {
			vista.appendLogGeneral("**** Usuario "+ evento.getPedido().getCliente().getNombre_usuario() + " Le asignaron el vehiculo "+ evento.getViaje().getVehiculo().toString()+"\n");
		} else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOCHOFER)) {
			vista.appendLogGeneral("☺☺☺☺ Usuario "+ evento.getPedido().getCliente().getNombre_usuario() +" Le asignaron el chofer "+ evento.getViaje().getChofer().getNombre()+"\n");
		} else if (mensaje.equalsIgnoreCase(EventoSistema.PAGADO)) {
			vista.appendLogGeneral("$$$$ Usuario "+ evento.getPedido().getCliente().getNombre_usuario() + " Pago el viaje \n");
		} else if (mensaje.equalsIgnoreCase(EventoSistema.FINALIZADO)) {
			vista.appendLogGeneral("•••• " + "El chofer "+evento.getViaje().getChofer().getNombre() + " Finalizo el viaje con el  Usuario "+ evento.getPedido().getCliente().getNombre_usuario()+"\n");
		}
	}
}
