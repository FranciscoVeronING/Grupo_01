package vista;

import java.awt.event.ActionListener;

import models.Pedido;

public interface IVistaAppCliente_formulario{
	public int getCantidadPasajeros();
	public boolean hayMascota();
	public boolean hayEquipaje();
	public double getDistancia();
	public String getZona();
	public void setVisible(boolean estado);
	void setActionListener(ActionListener actionListener);
}
