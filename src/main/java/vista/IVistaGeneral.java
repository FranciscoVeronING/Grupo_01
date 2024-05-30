package vista;

import java.awt.event.ActionListener;

public interface IVistaGeneral {
	public void voidAgregarLogGeneral(String cartel);
	public void voidAgregarLogCliente(String cartel);
	public void voidAgregarLogChofer(String cartel);
	public void setActionListener(ActionListener actionListener);
}
