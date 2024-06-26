package vista;

import java.awt.event.ActionListener;

/**
 *Interfaz que define los métodos que la clase VistaGeneral debe implementar
 */
public interface IVistaGeneral {
/*
	public void voidAgregarLogGeneral(String cartel);
	public void voidAgregarLogCliente(String cartel);
	public void voidAgregarLogChofer(String cartel);
*/
	public void setActionListener(ActionListener actionListener);

	public void appendLogCliente(String linea);
	public void appendLogGeneral(String linea);
	public void appendLogChofer(String linea);

	public int getCantChoferes();
	public int getCantVehiculos();
	public int getCantClientes();
	public boolean isEnablePersistitdos();
}
