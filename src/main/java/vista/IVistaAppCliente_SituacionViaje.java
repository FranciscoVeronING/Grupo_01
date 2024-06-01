package vista;

import java.awt.event.ActionListener;

public interface IVistaAppCliente_SituacionViaje {
	void setActionListener(ActionListener actionListener);

	public void setVisible(boolean estado);
	public void actualizarEstadoViaje(String nuevoEstado);
}
