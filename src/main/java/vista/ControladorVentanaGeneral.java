package vista;

import models.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controlador en un patrón de diseño MVC (Modelo-Vista-Controlador) que se encarga de gestionar las acciones provenientes de una vista general.
 * Este controlador maneja las interacciones del usuario con la vista general y ejecuta las operaciones correspondientes en el modelo del sistema.
 */
public class ControladorVentanaGeneral implements ActionListener {
	private IVistaGeneral vistaG;
	private  IVistaAppCliente_SituacionViaje vistaSV;
	public ControladorVentanaGeneral(IVistaGeneral vista, IVistaAppCliente_SituacionViaje vistaSV) {
		this.vistaG = vista;
		this.vistaG.setActionListener(this);
		this.vistaSV = vistaSV;
		this.vistaSV.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Iniciar_Simulacion")) {
			if (e.getActionCommand().equalsIgnoreCase("Persistir")) {
				Sistema.getInstancia().cargaSistema();
			} else {
				SistemaRunnable s = new SistemaRunnable(Sistema.getInstancia().getBolsaDeViajes(), Sistema.getInstancia().getVehiculos());
				s.start();
				ArrayList<ClienteRunnable> clientes = Sistema.getInstancia().crearCientesRandom(this.vistaG.getCantClientes());
				ArrayList<EmpleadoRunnable> empleados = Sistema.getInstancia().crearChoferesRandom(this.vistaG.getCantChoferes());
				Sistema.getInstancia().crearVehiculosRandom(this.vistaG.getCantVehiculos());

				ObservadorVentanaGral observadorGeneral = new ObservadorVentanaGral(Sistema.getInstancia().getBolsaDeViajes(), vistaG, vistaSV, clientes.get(0), empleados.get(0));

				// Inicio los hilos
				for (ClienteRunnable cliente : clientes) {
					Thread thread = new Thread(cliente);
					thread.start();
				}

				for (EmpleadoRunnable empleado : empleados) {
					Thread hiloChoferContratado = new Thread(empleado);
					hiloChoferContratado.start();
				}


			}

		}
	}

}
