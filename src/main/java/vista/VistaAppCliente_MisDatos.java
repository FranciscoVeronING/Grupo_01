package vista;

import models.Utiles;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Font;

public class VistaAppCliente_MisDatos extends JFrame implements IVistaAppCliente_MisDatos{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel_Volver;
	private JLabel lblNewLabel;
	private JButton btnVolver;
	private JPanel panel;
	private JPanel panelCentral;
	private JPanel panelNorte;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNombre;
	private JLabel lblNewLabel_4;
	private JLabel lblApellido;
	private JLabel lblNewLabel_6;
	private JLabel lblTelefono;
	private JLabel lblNewLabel_8;
	private JLabel lblMail;
	private JLabel lblNewLabel_10;
	private JLabel lblNewLabel_12;
	private JLabel lblFecha;
	private JLabel lblContrasenia;
	private JLabel lblNewLabel_15;
	private JLabel lbl_User;
	private JLabel lblNewLabel_17;
	private JPanel panel_1;
	private JLabel lblCalle;
	private JLabel lblCalleAltura;
	private JLabel lblCallePiso;
	private JLabel lblCalleLetra;

	public VistaAppCliente_MisDatos() {
		setTitle("Subi que te llevo - Mis Datos");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\franc\\Documents\\GitHub\\Grupo_01\\src\\main\\resources\\icon1.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panel_Volver = new JPanel();
		this.contentPane.add(this.panel_Volver, BorderLayout.SOUTH);
		this.panel_Volver.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.lblNewLabel = new JLabel("");
		this.panel_Volver.add(this.lblNewLabel);
		
		this.panel = new JPanel();
		this.panel_Volver.add(this.panel);
		
		this.btnVolver = new JButton("Volver");
		this.panel.add(this.btnVolver);
		
		this.panelCentral = new JPanel();
		this.contentPane.add(this.panelCentral, BorderLayout.CENTER);
		this.panelCentral.setLayout(new GridLayout(0, 2, 0, 0));
		
		this.lblNewLabel_17 = new JLabel("Nombre Usuario:");
		this.panelCentral.add(this.lblNewLabel_17);
		
		this.lbl_User = new JLabel("");
		this.panelCentral.add(this.lbl_User);
		
		this.lblNewLabel_15 = new JLabel("Contraseña:");
		this.panelCentral.add(this.lblNewLabel_15);
		
		this.lblContrasenia = new JLabel("");
		this.panelCentral.add(this.lblContrasenia);
		
		this.lblNewLabel_2 = new JLabel("Nombre:");
		this.panelCentral.add(this.lblNewLabel_2);
		
		this.lblNombre = new JLabel("");
		this.panelCentral.add(this.lblNombre);
		
		this.lblNewLabel_4 = new JLabel("Apellido:");
		this.panelCentral.add(this.lblNewLabel_4);
		
		this.lblApellido = new JLabel("");
		this.panelCentral.add(this.lblApellido);
		
		this.lblNewLabel_6 = new JLabel("Telefono:");
		this.panelCentral.add(this.lblNewLabel_6);
		
		this.lblTelefono = new JLabel("");
		this.panelCentral.add(this.lblTelefono);
		
		this.lblNewLabel_8 = new JLabel("Mail:");
		this.panelCentral.add(this.lblNewLabel_8);
		
		this.lblMail = new JLabel("");
		this.panelCentral.add(this.lblMail);
		
		this.lblNewLabel_10 = new JLabel("Direccion:");
		this.panelCentral.add(this.lblNewLabel_10);
		
		this.panel_1 = new JPanel();
		this.panelCentral.add(this.panel_1);
		
		this.lblCalle = new JLabel("");
		this.panel_1.add(this.lblCalle);
		
		this.lblCalleAltura = new JLabel("");
		this.panel_1.add(this.lblCalleAltura);
		
		this.lblCallePiso = new JLabel("");
		this.panel_1.add(this.lblCallePiso);
		
		this.lblCalleLetra = new JLabel("");
		this.panel_1.add(this.lblCalleLetra);
		
		this.lblNewLabel_12 = new JLabel("Fecha Nacimiento:");
		this.panelCentral.add(this.lblNewLabel_12);
		
		this.lblFecha = new JLabel("");
		this.panelCentral.add(this.lblFecha);
		
		this.panelNorte = new JPanel();
		this.contentPane.add(this.panelNorte, BorderLayout.NORTH);
		
		this.lblNewLabel_1 = new JLabel("Mis Datos");
		this.lblNewLabel_1.setFont(new Font("Sitka Subheading", Font.BOLD | Font.ITALIC, 18));
		this.panelNorte.add(this.lblNewLabel_1);
		
		this.btnVolver.setActionCommand("VOLVER_MD");
		this.setVisible(false);
	}

	@Override
	public void setVisibleVentana(boolean estado) {
		this.setVisible(estado);
		
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnVolver.addActionListener(actionListener);
		
	}

	@Override
	public void setNombre(String nombre) {
		this.lblNombre.setText(nombre);
		
	}

	@Override
	public void setApellido(String Apellido) {
		this.lblApellido.setText(Apellido);
	}

	@Override
	public void setNombreUser(String user) {
		this.lbl_User.setText(user);	
	}

	@Override
	public void setContrasenia(String contrasenia) {
		this.lblContrasenia.setText(contrasenia);
		
	}

	@Override
	public void setMail(String mail) {
		this.lblMail.setText(mail);
	}

	@Override
	public void setTelefono(String tel) {
		this.lblTelefono.setText(tel);
		
	}

	@Override
	public void setNombreCalle(String calle) {
		this.lblCalle.setText(calle);
	}

	@Override
	public void setAlturaCalle(String calleAltura) {
		this.lblCalleAltura.setText(calleAltura);	
	}

	@Override
	public void setPisoCalle(String callePiso) {
		this.lblCallePiso.setText(callePiso);
	
	}

	@Override
	public void setLetraCalle(String calleLetra) {
		this.lblCalleLetra.setText(calleLetra);
		
	}

	@Override
	public void setFechaDeNacimiento(GregorianCalendar fecha) {
		this.lblFecha.setText(Utiles.formatFecha(fecha));
	}

	@Override
	public void limpiarcampos() {
		this.lblNombre.setText("");
		this.lblApellido.setText("");
		this.lbl_User.setText("");
		this.lblContrasenia.setText("");
		this.lblMail.setText("");
		this.lblTelefono.setText("");
		this.lblCalle.setText("");
		this.lblCalleAltura.setText("");
		this.lblCallePiso.setText("");
		this.lblCalleLetra.setText("");
		this.lblFecha.setText("");
	}

	
}
