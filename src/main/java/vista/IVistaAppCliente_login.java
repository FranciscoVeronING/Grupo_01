package vista;

import java.awt.event.ActionListener;

/**
 * Interfaz que define los métodos que la clase VistaAppCliente_login debe implementar
 */
public interface IVistaAppCliente_login {
	String getNombreUsuario();
	String getContrasenia();
	void setActionListener(ActionListener actionListener);
	public void setVisibleVentana(boolean estado);
	public void	limpiarcampos();
}
