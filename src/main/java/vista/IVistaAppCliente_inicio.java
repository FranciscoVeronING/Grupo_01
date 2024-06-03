package vista;

import java.awt.event.ActionListener;

public interface IVistaAppCliente_inicio {
	
	void setActionListener(ActionListener actionListener);
	void setNombre(String nombre);
	public void	limpiarcampos();
	public void setVisibleVentana(boolean estado);
	public void setTitleUser(String nombre);
}
