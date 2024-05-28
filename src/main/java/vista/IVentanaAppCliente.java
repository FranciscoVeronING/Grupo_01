package vista;

import java.awt.event.ActionListener;

public interface IVentanaAppCliente{
	public int getCantidadPasajeros();
	public void hayMascota();
	public void hayEquipaje();
	public int getDistancia();
	public void getZona();
	public void getPedido();
	void setActionListener(ActionListener actionListener);
}
