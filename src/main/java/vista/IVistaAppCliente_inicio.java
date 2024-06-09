package vista;

import java.awt.event.ActionListener;

/**
 *  Interfaz que define los métodos que la clase VistaAppCliente_inicio debe implementar
 */
public interface IVistaAppCliente_inicio {
	
	void setActionListener(ActionListener actionListener);
	void setNombre(String nombre);
	public void	limpiarcampos();
	public void setVisibleVentana(boolean estado);
	public void setTitleUser(String nombre);
}
