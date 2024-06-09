package vista;

import java.awt.event.ActionListener;

import models.Pedido;

/**
 * Interfaz que define los métodos que la clase Vehículo debe implementar
 */
public interface IVistaAppCliente_formulario{
	public int getCantidadPasajeros();
	public boolean hayMascota();
	public boolean hayEquipaje();
	public double getDistancia();
	public String getZona();
	public void setVisibleVentana(boolean estado);
	public void	limpiarcampos();
	void setActionListener(ActionListener actionListener);
}
