package vista;

import models.EventoSistema;
import models.IViaje;
import models.Pedido;

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
		EventoSistema evento = (EventoSistema) arg;
		String mensaje = evento.getMensaje();
		Pedido p = evento.getPedido();
		IViaje v = evento.getViaje();
		String nombreUsuario = null;
		if (v != null) nombreUsuario = evento.getViaje().getPedido().getCliente().getNombre_usuario();
		if (p != null) nombreUsuario = evento.getPedido().getCliente().getNombre_usuario();
		System.out.println(mensaje);

		switch (mensaje) {
			case EventoSistema.NUEVOPEDIDO:
				vista.appendLogGeneral("Usuario " + nombreUsuario + " creó un pedido\n");
				break;
			case EventoSistema.NUEVOVIAJE:
				vista.appendLogGeneral("++++ Usuario " + nombreUsuario + " creo un viaje\n");
				break;
			case EventoSistema.NUEVOVEHICULO:
				vista.appendLogGeneral("**** Al viaje de " + nombreUsuario + " le asignaron el vehículo " + evento.getViaje().getVehiculo().toString() + "\n");
				break;
			case EventoSistema.NUEVOCHOFER:
				vista.appendLogGeneral("☺☺☺☺ Al viaje de " + nombreUsuario + " le asignaron el chofer " + evento.getViaje().getChofer().getNombre() + "\n");
				break;
			case EventoSistema.PAGADO:
				vista.appendLogGeneral("$$$$ Usuario " + nombreUsuario + " pagó el viaje\n");
				break;
			case EventoSistema.FINALIZADO:
				vista.appendLogGeneral("•••• El chofer " + nombreUsuario + " finalizó el viaje con el usuario " + nombreUsuario + "\n");
				break;
			case EventoSistema.RECHAZADO:
				vista.appendLogGeneral("El viaje de " + nombreUsuario + " fue rechazado");
			default:
				// Evento desconocido
				break;
		}
	}
}

