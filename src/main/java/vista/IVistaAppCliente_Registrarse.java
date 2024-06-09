package vista;

import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

/**
 * Interfaz que define los métodos que VistaAppCliente_Registrarse debe implementar
 */
public interface IVistaAppCliente_Registrarse {

	 void setVisibleVentana(boolean estado);
	 void setActionListener(ActionListener actionListener);
	 String getNombre();
	 String getApellido();
	 String getNombreUser();
	 String getContrasenia();
	 String getMail();
	 String getTelefono();
	 String getNombreCalle();
	 String getAlturaCalle();
	 String getPisoCalle();
	 String getLetraCalle();
	 GregorianCalendar getFechaDeNacimiento();
	 public void	limpiarcampos();
}