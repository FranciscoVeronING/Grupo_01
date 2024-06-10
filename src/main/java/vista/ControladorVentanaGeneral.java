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
			if (this.vistaG.isEnablePersistitdos()) {
				Sistema.getInstancia().cargaSistema();
				SistemaRunnable s = new SistemaRunnable(Sistema.getInstancia().getBolsaDeViajes(), Sistema.getInstancia().getVehiculos());
				s.start();
				ObservadorVentanaGral observadorGeneral = new ObservadorVentanaGral(Sistema.getInstancia().getBolsaDeViajes(), vistaG, vistaSV, Sistema.getInstancia().getArrayClientes().get(0), Sistema.getInstancia().getChoferes().get(0));
				Sistema.getInstancia().StartAll();
			} else {
				SistemaRunnable s = new SistemaRunnable(Sistema.getInstancia().getBolsaDeViajes(), Sistema.getInstancia().getVehiculos());
				s.start();
				ArrayList<ClienteRunnable> clientes = Sistema.getInstancia().crearCientesRandom(this.vistaG.getCantClientes());
				// TODO = ArrayList<ClienteRunnable> clientes = Sistema.getInstancia().crearCientesRandom(this.vistaG.getCantClientes(), this.vistaG.getCantViajesCliente());
				ArrayList<EmpleadoRunnable> empleados = Sistema.getInstancia().crearChoferesRandom(this.vistaG.getCantChoferes());
				// TODO = ArrayList<EmpleadoRunnable> empleados = Sistema.getInstancia().crearChoferesRandom(this.vistaG.getCantMax(), this.vistaG.getCantChoferesTemporarios(), this.vistaG.getCantChoferesPermanentes(), this.vistaG.getCantChoferesContratados());
				Sistema.getInstancia().crearVehiculosRandom(this.vistaG.getCantVehiculos());
				// TODO = Sistema.getInstancia().crearVehiculosRandom(this.vistaG.getCantMotos(), this.vistaG.getCantAutos(), this.vistaG.getCantCombis());

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
