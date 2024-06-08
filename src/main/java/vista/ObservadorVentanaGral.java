package vista;

import models.EventoSistema;

import java.util.Observable;

public class ObservadorVentanaGral extends ObservadorAbstracto {
	private IVistaGeneral vista;

	public ObservadorVentanaGral(Observable observado, IVistaGeneral vista) {
		super(observado);
		this.vista = vista;
	}

	@Override
	public void update(Observable obs, Object arg) {
		super.update(obs, arg);

		if (arg instanceof EventoSistema) {
			EventoSistema evento = (EventoSistema) arg;
			String mensaje = evento.getMensaje();
			String nombreUsuario = evento.getPedido().getCliente().getNombre_usuario();

			switch (mensaje) {
				case EventoSistema.NUEVOPEDIDO:
					vista.appendLogGeneral("Usuario " + nombreUsuario + " creó un pedido\n");
					break;
				case EventoSistema.NUEVOVIAJE:
					vista.appendLogGeneral("++++ Usuario " + nombreUsuario + " está en Situación de viaje Confirmado\n");
					break;
				case EventoSistema.NUEVOVEHICULO:
					vista.appendLogGeneral("**** Usuario " + nombreUsuario + " le asignaron el vehículo " + evento.getViaje().getVehiculo().toString() + "\n");
					break;
				case EventoSistema.NUEVOCHOFER:
					vista.appendLogGeneral("☺☺☺☺ Usuario " + nombreUsuario + " le asignaron el chofer " + evento.getViaje().getChofer().getNombre() + "\n");
					break;
				case EventoSistema.PAGADO:
					vista.appendLogGeneral("$$$$ Usuario " + nombreUsuario + " pagó el viaje\n");
					break;
				case EventoSistema.FINALIZADO:
					vista.appendLogGeneral("•••• El chofer " + evento.getViaje().getChofer().getNombre() + " finalizó el viaje con el usuario " + nombreUsuario + "\n");
					break;
				default:
					// Evento desconocido
					break;
			}
		}
	}
}

