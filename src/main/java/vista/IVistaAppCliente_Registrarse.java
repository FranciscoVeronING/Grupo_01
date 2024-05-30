package vista;

import java.awt.event.ActionListener;

public interface IVistaAppCliente_Registrarse {

	 void setVisible(boolean estado);
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
}
