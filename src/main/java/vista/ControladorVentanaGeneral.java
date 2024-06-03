package vista;

import models.Sistema;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVentanaGeneral implements ActionListener {
	private IVistaGeneral vista;
	public ControladorVentanaGeneral(IVistaGeneral vista) {
		this.vista = vista;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Iniciar_Simulacion")) {
			if(e.getActionCommand().equalsIgnoreCase("Persistir")){
				Sistema.getInstancia().cargaSistema();
			}
			else{
				Sistema.getInstancia().crearCientesRandom(this.vista.getCantClientes());
				Sistema.getInstancia().crearChoferesRandom(this.vista.getCantChoferes());
				Sistema.getInstancia().crearVehiculosRandom(this.vista.getCantVehiculos());
			}

	}else if(e.getActionCommand().equalsIgnoreCase("Finalizar_Simulacion")) {
			Sistema.getInstancia().guardaSistema();
			Sistema.getInstancia().detenerSimulacion();
		}
	}


}
