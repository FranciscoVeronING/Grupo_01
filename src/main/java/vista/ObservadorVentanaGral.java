package vista;

import models.*;

import java.util.Observable;

/**
 * La clase actúa como un observador específico para los eventos relacionados
 */
public class ObservadorVentanaGral extends ObservadorAbstracto {
	private IVistaGeneral vistaG;
	private IVistaAppCliente_SituacionViaje vistaSV;
	private Cliente clienteRobot;
	private Cliente clienteApp;
	private Empleado chofer;

	public ObservadorVentanaGral(Observable observado, IVistaGeneral vistaG,IVistaAppCliente_SituacionViaje vistaSV, Cliente clienteRobot, Empleado chofer) {
		super(observado);
		this.vistaG = vistaG;
		this.vistaSV = vistaSV;
		this.clienteRobot = clienteRobot;
		this.vistaG.appendLogCliente("EL CLIENTE OBSERVADO ES " + clienteRobot.getNombre_usuario());
		this.chofer = chofer;
		this.vistaG.appendLogChofer("EL CHOFER OBSERVADO ES " + chofer.getNombre());
	}

	@Override
	public void update(Observable obs, Object arg) {
		super.update(obs, arg);

		EventoSistema evento = (EventoSistema) arg;
		String mensaje = evento.getMensaje();


		if (mensaje.equalsIgnoreCase(EventoSistema.STOP)) {
			this.vistaG.appendLogGeneral("LA SIMULACION SE DETUVO");
		} else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOCLIENTE)) {
			this.clienteApp = evento.getCliente();
		}else {
			Cliente c = null;
			if (evento.getViaje() != null) c = evento.getViaje().getPedido().getCliente();
			else if (evento.getPedido() != null) c = evento.getPedido().getCliente();

			boolean esClienteRobot = c == clienteRobot;
			boolean esClienteApp = c == clienteApp;
			boolean esChofer = false;
			if (evento.getViaje() != null && evento.getViaje().getChofer() != null) {
				esChofer = chofer == evento.getViaje().getChofer();
			}

			if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOPEDIDO)) {
                assert c != null;
                this.vistaG.appendLogGeneral("//// Usuario "+ c.getNombre_usuario() + " creó un pedido\n");
				if (esClienteRobot) this.vistaG.appendLogCliente("Creó un pedido\n");
				if (esClienteApp) this.vistaSV.actualizarEstadoViaje("Creaste un pedido! Esperando confirmacion");
			} else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVIAJE)) {
                assert c != null;
                this.vistaG.appendLogGeneral("++++ Usuario "+ c.getNombre_usuario()  + " está en situación de viaje confirmado\n");
				if (esClienteRobot) this.vistaG.appendLogCliente("++++ Está en situación de viaje confirmado\n");
				if (esClienteApp) {
					this.vistaSV.actualizarEstadoViaje("Se confirmo tu viaje! Buscando un vehiculo...");
				}
			} else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOVEHICULO)) {
                assert c != null;
                this.vistaG.appendLogGeneral("**** Usuario "+ c.getNombre_usuario() + " le asignaron el vehículo " + evento.getViaje().getVehiculo().toString()+"\n");
				if (esClienteRobot) this.vistaG.appendLogCliente("**** Le asignaron el vehículo " + evento.getViaje().getVehiculo().toString()+"\n");
				if (esClienteApp) this.vistaSV.actualizarEstadoViaje("Vehiculo encontrado! Esperando chofer...");
			} else if (mensaje.equalsIgnoreCase(EventoSistema.NUEVOCHOFER)) {
                assert c != null;
                this.vistaG.appendLogGeneral("☺☺☺☺ Usuario "+ c.getNombre_usuario() + " le asignaron el chofer " + evento.getViaje().getChofer().getNombre()+"\n");
				if (esClienteRobot) this.vistaG.appendLogCliente("☺☺☺☺ Le asignaron el chofer " + evento.getViaje().getChofer().getNombre()+"\n");
				if (esChofer) this.vistaG.appendLogChofer("☺☺☺☺ Se asigno a un viaje\n");
				if (esClienteApp) {
					this.vistaSV.actualizarEstadoViaje("Tu chofer es " + evento.getViaje().getChofer().getNombre());
					this.vistaSV.setbotonPagar(true);
				}
			} else if (mensaje.equalsIgnoreCase(EventoSistema.PAGADO)) {
                assert c != null;
                this.vistaG.appendLogGeneral("$$$$ Usuario "+ c.getNombre_usuario() + " pagó el viaje\n");
				if (esClienteRobot) this.vistaG.appendLogCliente("$$$$ Pagó el viaje\n");
				if (esChofer) this.vistaG.appendLogChofer("$$$$ Le pagaron el viaje\n");
				if (esClienteApp) this.vistaSV.actualizarEstadoViaje("Viaje pagado, esperando finalizacion del chofer");
			} else if (mensaje.equalsIgnoreCase(EventoSistema.FINALIZADO)) {
                assert c != null;
                this.vistaG.appendLogGeneral("•••• El chofer " + evento.getViaje().getChofer() + " finalizó el viaje con el Usuario "+ c.getNombre_usuario()+"\n");
				if (esClienteRobot) this.vistaG.appendLogCliente("•••• El chofer " + evento.getViaje().getChofer().getNombre() + " finalizó el viaje\n");
				if (esChofer) this.vistaG.appendLogChofer("•••• Finalizo el viaje\n");
				if (esClienteApp) this.vistaSV.actualizarEstadoViaje("Viaje finalizado! Gracias por viajar con nosotros!");
			} else if (mensaje.equalsIgnoreCase(EventoSistema.PEDIDORECHAZADO)) {
				this.vistaG.appendLogGeneral("==== El pedido de " + evento.getPedido().getCliente().getNombre_usuario() + " fue rechazado\n");
				if (esClienteRobot) this.vistaG.appendLogCliente("==== El pedido fue rechazado\n");
				if (esClienteApp) this.vistaSV.actualizarEstadoViaje("Tu pedido fue rechazado, intentalo nuevamente");
			} else if (mensaje.equalsIgnoreCase(EventoSistema.RECHAZADO)) {
				this.vistaG.appendLogGeneral("==== El viaje de " + evento.getViaje().getPedido().getCliente().getNombre_usuario() + " fue rechazado por falta de vehiculos disponibles\n");
				if (esClienteRobot) this.vistaG.appendLogCliente("==== El viaje fue rechazado por falta de vehiculos disponibles\n");
				if (esClienteApp) this.vistaSV.actualizarEstadoViaje("Tu viaje fue rechazado por falta de vehiculos disponibles, intentalo nuevamente mas tarde");
			} else if (mensaje.equalsIgnoreCase(EventoSistema.PEDIDORECHAZADOPORCHOFERES)) {
				this.vistaG.appendLogGeneral("==== El pedido de " + evento.getCliente().getNombre_usuario() + " fue rechazado por falta de choferes disponibles\n");
				if (esClienteRobot) this.vistaG.appendLogCliente("==== El pedido fue rechazado por falta de choferes disponibles\n");
				if (esClienteApp) this.vistaSV.actualizarEstadoViaje("Tu pedido fue rechazado por falta de choferes disponibles, intentalo nuevamente mas tarde");
			} else if (mensaje.equalsIgnoreCase(EventoSistema.ELIMINADOSIMULACION)) {
				this.vistaG.appendLogGeneral("==== El viaje de " + evento.getViaje().getPedido().getCliente().getNombre_usuario() + " fue eliminado porque se pauso la simulacion\n");
				if (esClienteRobot) this.vistaG.appendLogCliente("==== El viaje de fue eliminado porque se pauso la simulacion\n");
				if (esChofer) this.vistaG.appendLogChofer("==== El viaje de fue eliminado porque se pauso la simulacion\n");
				if (esClienteApp) this.vistaSV.actualizarEstadoViaje("El viaje de fue eliminado porque se pauso la simulacion\n");
			}
		}
	}
}

