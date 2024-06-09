package vista;

import models.Sistema;
import models.SistemaRunnable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controlador en un patrón de diseño MVC (Modelo-Vista-Controlador) que se encarga de gestionar las acciones provenientes de una vista general.
 * Este controlador maneja las interacciones del usuario con la vista general y ejecuta las operaciones correspondientes en el modelo del sistema.
 */
public class ControladorVentanaGeneral implements ActionListener {
	private IVistaGeneral vista;
	public ControladorVentanaGeneral(IVistaGeneral vista) {
		this.vista = vista;
		this.vista.setActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Iniciar_Simulacion")) {
			if (e.getActionCommand().equalsIgnoreCase("Persistir")) {
				Sistema.getInstancia().cargaSistema();
			} else {
				Sistema.getInstancia().crearCientesRandom(this.vista.getCantClientes());
				Sistema.getInstancia().crearChoferesRandom(this.vista.getCantChoferes());
				Sistema.getInstancia().crearVehiculosRandom(this.vista.getCantVehiculos());
				SistemaRunnable s = new SistemaRunnable(Sistema.getInstancia().getBolsaDeViajes(), Sistema.getInstancia().getVehiculos());
				s.start();
			}

		}
	}

}
