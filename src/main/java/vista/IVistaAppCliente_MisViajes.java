package vista;

import java.awt.event.ActionListener;

/**
 * :Interfaz que define los métodos que la clase VistaAppCliente_MisViajes debe implementar
 */
public interface IVistaAppCliente_MisViajes {
	
	void setVisibleVentana(boolean estado);
	 void setActionListener(ActionListener actionListener);
	 public void appendText(String arg);
	 public void limpiarVentana();
}
