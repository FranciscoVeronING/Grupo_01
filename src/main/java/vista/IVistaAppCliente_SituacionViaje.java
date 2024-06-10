package vista;

import java.awt.event.ActionListener;

/**
 * Interfaz que define los métodos que la clase VistaAppCliente_SituacionViajes debe implementar
 */
public interface IVistaAppCliente_SituacionViaje {
	void setActionListener(ActionListener actionListener);

	public void setVisibleVentana(boolean estado);
	public void actualizarEstadoViaje(String nuevoEstado);
	public void	limpiarcampos();
	public void setbotonPagar(boolean x);
}
