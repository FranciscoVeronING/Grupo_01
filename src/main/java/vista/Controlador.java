package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import models.Sistema;

public class Controlador implements ActionListener {
private IVistaAppCliente_formulario formulario;
private IVistaAppCliente_login login;
private IVistaAppCliente_inicio inicio;
private IVistaAppCliente_Registrarse r;


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("PEDIR")) {
			//Ingresar metodo del modelo para cargar los datos
			//a partir de aca resuelve el modelo
		}
		if (e.getActionCommand().equalsIgnoreCase("REGISTRAR")) {
			this.login.setVisible(false);
			this.r.setVisible(true);
		}
		if (e.getActionCommand().equalsIgnoreCase("REGISTRARME")) {
			this.r.setVisible(false);
			this.login.setVisible(true);
			try {
				///Crear Funcion en sistema de crear cliente, esa funcion deberia llamar a añadir cliente.
				///debe validar si el nombre de usuario existe
				Sistema.getInstancia().creaCliente(r.getNombre(),r.getApellido(),r.getMail(),r.getTelefono(),r.getNombreUser(), r.getContrasenia(), r.getNombreCalle(), r.getAlturaCalle(), r.getPisoCalle(), r.getLetraCalle());			
			}
			catch(){
				JOptionPane.showMessageDialog(null, "Usuario ya registrado");	
			}
		}
		if (e.getActionCommand().equalsIgnoreCase("INGRESAR")) {
			try {
				//funcion que debeverificar si existe o no el cliente para poder hacer el login
				Sistema.getInstancia().verifica(this.login.getNombreUsuario(), this.login.getContrasenia());
				this.login.setVisible(false);
				this.inicio.setVisible(true);
				///aca se deben pedir los datos del cliente. hay que gardarlo en variables en el CONTROLADOR??
			}catch() {//hay que hacer nueva excepcion
				JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrecta");	
			}
			
		}
		if (e.getActionCommand().equalsIgnoreCase("LOGOUT")) {
			this.login.setVisible(true);
			this.inicio.setVisible(false);
		}
		if (e.getActionCommand().equalsIgnoreCase("FORMULARIO_PEDIDOS")) {
			this.formulario.setVisible(true);
			this.inicio.setVisible(false);
		}
		if (e.getActionCommand().equalsIgnoreCase("VOLVER")) {
			this.formulario.setVisible(false);
			this.inicio.setVisible(true);
		}
		
	}
	

}
