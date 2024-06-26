package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import Exception.UsuarioIncorrectoException;
import Exception.UsuarioRepetidoException;

import models.Cliente;
import models.ClienteAppRunnable;
import models.Pedido;
import models.Sistema;

/**
 * Esta clase implementa la interfaz ActionListener y maneja las interacciones del usuario con las vistas de la aplicación.
 * <p>
 * El controlador actúa como un intermediario entre las vistas de la aplicación y el modelo (Sistema y Clientes).
 * Se encarga de gestionar las acciones del usuario, como el registro, inicio de sesión, solicitud de viajes, etc.
 * </p>
 */
public class Controlador implements ActionListener {
	private IVistaAppCliente_formulario form;
	private IVistaAppCliente_login login;
	private IVistaAppCliente_inicio inicio;
	private IVistaAppCliente_Registrarse r;
	private IVistaAppCliente_SituacionViaje sv;
	private IVistaAppCliente_MisDatos md;
	private IVistaAppCliente_MisViajes mv;

	private Cliente clienteVentana;
	private ClienteAppRunnable clienteAppRunneable;
	/**
	 * Constructor del controlador que inicializa las vistas y establece el controlador como su ActionListener.
	 *
	 * @param form  La vista del formulario de pedidos.
	 * @param login La vista de inicio de sesión.
	 * @param inicio La vista de inicio del cliente.
	 * @param r La vista de registro de nuevos usuarios.
	 * @param sv La vista de situación del viaje.
	 * @param md La vista de los datos del cliente.
	 * @param mv La vista de los viajes del cliente.
	 */
	public Controlador(IVistaAppCliente_formulario form,IVistaAppCliente_login login,IVistaAppCliente_inicio inicio,IVistaAppCliente_Registrarse r,IVistaAppCliente_SituacionViaje sv, IVistaAppCliente_MisDatos md, IVistaAppCliente_MisViajes mv) {
        this.form = form;
		this.login = login;
		this.inicio = inicio;
		this.r = r;
		this.sv = sv;
		this.md =md;
		this.mv = mv;

		this.form.setActionListener(this);
		this.login.setActionListener(this);
		this.inicio.setActionListener(this);
		this.r.setActionListener(this);
		this.sv.setActionListener(this);
		this.md.setActionListener(this);
		this.mv.setActionListener(this);

    }



	/**
	 * Método que maneja las acciones del usuario en las diferentes vistas.
	 *
	 * @param e El evento de acción que representa la interacción del usuario.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase("PEDIR")) {
			Pedido pedido = new Pedido(new GregorianCalendar(), this.form.getZona(),this.form.hayMascota(),this.form.getCantidadPasajeros(),this.form.hayEquipaje(),this.clienteVentana, this.form.getDistancia());
			this.clienteAppRunneable = new ClienteAppRunnable(Sistema.getInstancia().getBolsaDeViajes(), pedido);
			this.clienteAppRunneable.getBolsa().asignarClienteApp(this.clienteVentana);
			Thread thread = new Thread(clienteAppRunneable);
			thread.setName("ClienteAppRunneable");
			thread.start();
			this.form.setVisibleVentana(false);
			this.form.limpiarcampos();
			this.sv.setVisibleVentana(true);
			this.sv.setbotonPagar(false);
		}
		else if (e.getActionCommand().equalsIgnoreCase("REGISTRAR")) {
			this.login.setVisibleVentana(false);
			this.login.limpiarcampos();
			this.r.setVisibleVentana(true);
		}
		else if (e.getActionCommand().equalsIgnoreCase("REGISTRARME")) {
			this.r.setVisibleVentana(false);
			this.login.setVisibleVentana(true);
			try {
				Sistema.getInstancia().crearCliente(r.getNombreUser(),r.getContrasenia(),r.getNombre(),r.getApellido(),r.getTelefono(),r.getMail(),r.getNombreCalle(),r.getAlturaCalle(),r.getPisoCalle(),r.getLetraCalle(),r.getFechaDeNacimiento());}
			catch(UsuarioRepetidoException ex){
				JOptionPane.showMessageDialog(null, "Usuario ya registrado");	
			}
			this.r.limpiarcampos();
		}
		else if (e.getActionCommand().equalsIgnoreCase("INGRESAR")) {
			try {
				//funcion que debeverificar si existe o no el cliente para poder hacer el login
				Sistema.getInstancia().verificarExistenciaCliente(this.login.getNombreUsuario(), this.login.getContrasenia());
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
			this.clienteAppRunneable.getBolsa().viajePagado(this.clienteAppRunneable.getViaje());
            this.inicio.setVisibleVentana(true);
            this.sv.setVisibleVentana(false);
			this.sv.limpiarcampos();
			
		} else if (e.getActionCommand().equalsIgnoreCase("VOLVER_MD")) {
			this.md.setVisibleVentana(false);
			this.md.limpiarcampos();
			this.inicio.setVisibleVentana(true);

		} else if (e.getActionCommand().equalsIgnoreCase("MISDATOS")) {
			this.md.setVisibleVentana(true);
			this.md.setNombre(clienteVentana.getNombre());
			this.md.setApellido(clienteVentana.getApellido());
			this.md.setNombreUser(clienteVentana.getNombre_usuario());
			this.md.setContrasenia(clienteVentana.getContrasenia());
			this.md.setFechaDeNacimiento(clienteVentana.getFecha_nacimiento());
			this.md.setTelefono(clienteVentana.getTelefono());
			this.md.setMail(clienteVentana.getMail());
			this.md.setNombreCalle(clienteVentana.getDireccion().getNombre());
			this.md.setAlturaCalle(clienteVentana.getDireccion().getAltura());
			this.md.setPisoCalle(clienteVentana.getDireccion().getPiso());
			this.md.setLetraCalle(clienteVentana.getDireccion().getLetra());
			this.inicio.setVisibleVentana(false);
		} else if (e.getActionCommand().equalsIgnoreCase("VOLVER_MV")) {
			this.mv.limpiarVentana();
			this.mv.setVisibleVentana(false);
			this.inicio.setVisibleVentana(true);
		}else if (e.getActionCommand().equalsIgnoreCase("MISVIAJES")) {
			this.inicio.setVisibleVentana(false);
			this.mv.setVisibleVentana(true);
			this.mv.appendText(Sistema.getInstancia().viajesClienteFecha(clienteVentana,new GregorianCalendar(2000, Calendar.JANUARY,1),new GregorianCalendar()));
		}
		else if(e.getActionCommand().equalsIgnoreCase("FINALIZAR")) {
			Sistema.getInstancia().guardaSistema();
			Sistema.getInstancia().detenerSimulacion();
		}
	}

}
