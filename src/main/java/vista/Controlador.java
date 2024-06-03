package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import Exception.UsuarioIncorrectoException;
import Exception.UsuarioRepetidoException;

import models.Cliente;
import models.Sistema;

public class Controlador implements ActionListener {
	private IVistaAppCliente_formulario form;
	private IVistaAppCliente_login login;
	private IVistaAppCliente_inicio inicio;
	private IVistaAppCliente_Registrarse r;
	private IVistaAppCliente_SituacionViaje sv;

	private Cliente clienteVentana;

	public Controlador(IVistaAppCliente_formulario form,IVistaAppCliente_login login,IVistaAppCliente_inicio inicio,IVistaAppCliente_Registrarse r,IVistaAppCliente_SituacionViaje sv) {
        this.form = form;
		this.login = login;
		this.inicio = inicio;
		this.r = r;
		this.sv = sv;
		Sistema.getInstancia().setControlador(this); //preguntar si esto esta bien
		this.form.setActionListener(this);
		this.login.setActionListener(this);
		this.inicio.setActionListener(this);
		this.r.setActionListener(this);
		this.sv.setActionListener(this);
    }
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("PEDIR")) {
			Sistema.getInstancia().hacerPedido(new GregorianCalendar(), this.form.getZona(),this.form.hayMascota(),this.form.getCantidadPasajeros(),this.form.hayEquipaje(),this.clienteVentana, this.form.getDistancia());
			this.form.setVisibleVentana(false);
			this.form.limpiarcampos();
			this.sv.setVisibleVentana(true);
		}
		else if (e.getActionCommand().equalsIgnoreCase("REGISTRAR")) {
			this.login.setVisibleVentana(false);
			this.login.limpiarcampos();
			this.r.setVisibleVentana(true);
		}
		else if (e.getActionCommand().equalsIgnoreCase("REGISTRARME")) {
			this.r.setVisibleVentana(false);
			this.r.limpiarcampos();
			this.login.setVisibleVentana(true);
			try {
				Sistema.getInstancia().crearCliente(r.getNombreUser(),r.getContrasenia(),r.getNombre(),r.getApellido(),r.getTelefono(),r.getMail(),r.getNombreCalle(),r.getAlturaCalle(),r.getPisoCalle(),r.getLetraCalle(),r.getFechaDeNacimiento());}
			catch(UsuarioRepetidoException ex){
				JOptionPane.showMessageDialog(null, "Usuario ya registrado");	
			}
		}
		else if (e.getActionCommand().equalsIgnoreCase("INGRESAR")) {
			try {
				//funcion que debeverificar si existe o no el cliente para poder hacer el login
				Sistema.getInstancia().verificarExistenciaCliente(this.login.getNombreUsuario(), this.login.getContrasenia());
				///aca se deben pedir los datos del cliente. hay que gardarlo en variables en el CONTROLADOR??
				clienteVentana = Sistema.getInstancia().getCliente(this.login.getNombreUsuario());
				this.login.setVisibleVentana(false);
				this.login.limpiarcampos();
				this.inicio.setVisibleVentana(true);
				this.inicio.setTitleUser(clienteVentana.getNombre_usuario()
				);

			}catch(UsuarioIncorrectoException ex) {//hay que hacer nueva excepcion
				JOptionPane.showMessageDialog(null, "Usuario y/o contraseña incorrecta");	
			}
			
		}
		else if (e.getActionCommand().equalsIgnoreCase("LOGOUT")) {
			this.login.setVisibleVentana(true);
			this.inicio.setVisibleVentana(false);
			this.inicio.limpiarcampos();
		}
		else if (e.getActionCommand().equalsIgnoreCase("FORMULARIO_PEDIDOS")) {
			this.form.setVisibleVentana(true);
			this.inicio.setVisibleVentana(false);
			this.inicio.limpiarcampos();
		}
		else if (e.getActionCommand().equalsIgnoreCase("VOLVER")) {
			this.form.setVisibleVentana(false);
			this.form.limpiarcampos();
			this.inicio.setVisibleVentana(true);
		}else if(e.getActionCommand().equalsIgnoreCase("PAGAR")) {
			JOptionPane.showMessageDialog(null, "Viaje terminado");
            this.inicio.setVisibleVentana(true);
            this.sv.setVisibleVentana(false);
			this.sv.limpiarcampos();
			
		}
		
	}

}
