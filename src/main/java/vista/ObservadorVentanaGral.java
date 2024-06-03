package vista;

import models.EventoSistema;

import java.util.Observable;

public class ObservadorVentanaGral extends ObservadorAbstracto {
	public ObservadorVentanaGral(Observable observado) {
		super(observado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable obs, Object arg) {
		super.update(obs, arg);

		EventoSistema evento = (EventoSistema) arg;
		String mensaje = evento.getMensaje();

		if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOPEDIDO)) {
			// TODO Agregar mensajito en consola
		} else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVIAJE)) {
			// TODO Agregar mensajito en consola
		} else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVEHICULO)) {
			// TODO Agregar mensajito en consola
		} else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOCHOFER)) {
			// TODO Agregar mensajito en consola
		} else if (mensaje.equalsIgnoreCase(EventoSistema.PAGADO)) {
			// TODO Agregar mensajito en consola
		} else if (mensaje.equalsIgnoreCase(EventoSistema.FINALIZADO)) {
			// TODO Agregar mensajito en consola
		}

	}
}
