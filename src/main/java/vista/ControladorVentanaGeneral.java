package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVentanaGeneral implements ActionListener{
	
	public ControladorVentanaGeneral() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("Iniciar_Simulacion")) {
		//hay que pasarle al modelo los parametros correspondiente a  la creacion de los clientes/choferes/vehiculos
			//hay que ver si esta activa persistencia o no
	}else if(e.getActionCommand().equalsIgnoreCase("Finalizar_Simulacion")) {
		//finaliza la simulacion. deberia hacer la persistencia aqui
		}
	}

}
