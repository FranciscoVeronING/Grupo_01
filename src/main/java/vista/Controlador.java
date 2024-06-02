package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import models.Sistema;

public class Controlador implements ActionListener {
	private IVistaAppCliente_formulario form;
	private IVistaAppCliente_login login;
	private IVistaAppCliente_inicio inicio;
	private IVistaAppCliente_Registrarse r;
	private IVistaAppCliente_SituacionViaje sv;

	
	public Controlador() {
        Sistema.getInstancia().setControlador(this);
    }

    public void actualizarEstadoViaje(String nuevoEstado) {
        this.sv.actualizarEstadoViaje(nuevoEstado);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("PEDIR")) {
			Sistema.getInstancia().hacerPedido(new GregorianCalendar(), this.form.getZona(),this.form.hayMascota(),this.form.getCantidadPasajeros(),this.form.hayEquipaje(),/*ver como hacer para darle cliente*/, this.form.getDistancia());
			this.form.setVisible(false);
			this.sv.setVisible(true);
		}
		else if (e.getActionCommand().equalsIgnoreCase("REGISTRAR")) {
			this.login.setVisible(false);
			this.r.setVisible(true);
		}
		else if (e.getActionCommand().equalsIgnoreCase("REGISTRARME")) {
			this.r.setVisible(false);
			this.login.setVisible(true);
			try {
				///Crear Funcion en sistema de crear cliente, esa funcion deberia llamar a añadir cliente.
				///debe validar si el nombre de usuario existe
				Sistema.getInstancia().creaCliente(r.getNombreUser(),r.getContrasenia(),r.getNombre(),r.getApellido(),r.getTelefono(),r.getMail(),r.getNombreCalle(),r.getAlturaCalle(),r.getPisoCalle(),r.getLetraCalle(),r.getFechaDeNacimiento());}
			catch(Exception ex){
				JOptionPane.showMessageDialog(null, "Usuario ya registrado");	
			}
		}
		else if (e.getActionCommand().equalsIgnoreCase("INGRESAR")) {
			try {
				//funcion que debeverificar si existe o no el cliente para poder hacer el login
				Sistema.getInstancia().verifica(this.login.getNombreUsuario(), this.login.getContrasenia());
				this.login.setVisible(false);
				this.inicio.setVisible(true);
				///aca se deben pedir los datos del cliente. hay que gardarlo en variables en el CONTROLADOR??
			}catch(Exception ex) {//hay que hacer nueva excepcion
				JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrecta");	
			}
			
		}
		else if (e.getActionCommand().equalsIgnoreCase("LOGOUT")) {
			this.login.setVisible(true);
			this.inicio.setVisible(false);
		}
		else if (e.getActionCommand().equalsIgnoreCase("FORMULARIO_PEDIDOS")) {
			this.form.setVisible(true);
			this.inicio.setVisible(false);
		}
		else if (e.getActionCommand().equalsIgnoreCase("VOLVER")) {
			this.form.setVisible(false);
			this.inicio.setVisible(true);
		}else if(e.getActionCommand().equalsIgnoreCase("PAGAR")) {
			JOptionPane.showMessageDialog(null, "Viaje terminado");
            this.inicio.setVisible(true);
            this.sv.setVisible(false);
			
		}
		
	}

}
