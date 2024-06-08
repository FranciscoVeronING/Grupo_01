package vista;

import java.awt.event.ActionListener;

public interface IVistaAppCliente_MisViajes {
	
	void setVisibleVentana(boolean estado);
	 void setActionListener(ActionListener actionListener);
	 public void appendText(String arg);
	 public void limpiarVentana();
}
