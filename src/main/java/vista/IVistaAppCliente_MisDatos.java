package vista;

import java.awt.event.ActionListener;
import java.util.GregorianCalendar;


public interface IVistaAppCliente_MisDatos {	
	 void setVisibleVentana(boolean estado);
	 void setActionListener(ActionListener actionListener);
	 void setNombre(String nombre);
	 void setApellido(String Apellido);
	 void setNombreUser(String user);
	 void setContrasenia(String contrasenia);
	 void setMail(String mail);
	 void setTelefono(String tel);
	 void setNombreCalle(String calle);
	 void setAlturaCalle(String calleAltura);
	 void setPisoCalle(String callePiso);
	 void setLetraCalle(String calleLetra);
	 void setFechaDeNacimiento(GregorianCalendar fecha);
	 public void	limpiarcampos();

}
