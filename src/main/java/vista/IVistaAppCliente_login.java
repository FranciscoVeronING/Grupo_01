package vista;

import java.awt.event.ActionListener;

public interface IVistaAppCliente_login {
	String getNombreUsuario();
	String getContrasenia();
	void setActionListener(ActionListener actionListener);
	public void setVisibleVentana(boolean estado);
}
