package vista;

import models.ClienteRunnable;
import models.EventoSistema;
import models.IViaje;
import models.Pedido;

import java.util.Observable;

/**
 * La clase actúa como un observador específico para los eventos relacionados
 */
public class ObservadorVentanaGral extends ObservadorAbstracto {
	private IVistaGeneral vistaG;
	private IVistaAppCliente_SituacionViaje vistaSV;

	public ObservadorVentanaGral(Observable observado, IVistaGeneral vistaG,IVistaAppCliente_SituacionViaje vistaSV) {
		super(observado);
		this.vistaG = vistaG;
		this.vistaSV = vistaSV;
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
				vistaG.appendLogGeneral("Usuario " + nombreUsuario + " creó un pedido\n");
				break;
			case EventoSistema.NUEVOVIAJE:
				vistaG.appendLogGeneral("++++ Usuario " + nombreUsuario + " creo un viaje\n");
				break;
			case EventoSistema.NUEVOVEHICULO:
				vistaG.appendLogGeneral("**** Al viaje de " + nombreUsuario + " le asignaron el vehículo " + evento.getViaje().getVehiculo().toString() + "\n");
				break;
			case EventoSistema.NUEVOCHOFER:
				vistaG.appendLogGeneral("☺☺☺☺ Al viaje de " + nombreUsuario + " le asignaron el chofer " + evento.getViaje().getChofer().getNombre() + "\n");
				break;
			case EventoSistema.PAGADO:
				vistaG.appendLogGeneral("$$$$ Usuario " + nombreUsuario + " pagó el viaje\n");
				break;
			case EventoSistema.FINALIZADO:
				vistaG.appendLogGeneral("•••• El chofer " + nombreUsuario + " finalizó el viaje con el usuario " + nombreUsuario + "\n");
				break;
			case EventoSistema.RECHAZADO:
				vistaG.appendLogGeneral("El viaje de " + nombreUsuario + " fue rechazado");
			default:
				// Evento desconocido
				break;
		}
	}
}

