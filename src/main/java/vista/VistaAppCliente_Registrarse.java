package vista;

import java.awt.*;
import java.awt.event.*;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import com.toedter.calendar.JDateChooser;
import models.Sistema;

/**
 *  Representa la interfaz gráfica de la ventana de registro para nuevos clientes en la aplicación
 */
public class VistaAppCliente_Registrarse extends JFrame implements IVistaAppCliente_Registrarse{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tl_nombrenuevo;
	private JTextField tl_apellidonuevo;
	private JTextField tl_telefononuevo;
	private JTextField tl_mailnuevo;
	private JTextField tf_nombreCalle;
	private JTextField tl_Usuarionuevo;
	private JTextField tl_contrasenianuevo;
	private JTextField tf_alturaCalle;
	private JTextField tf_PisoCalle;
	private JTextField tf_letraCalle;
	private JButton btnRegistrarme;
	private JDateChooser dateChooser;

	public VistaAppCliente_Registrarse() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon1.png")));
		setTitle("Subi que te Llevo - Registrarse");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 100, 900, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Código a ejecutar cuando se intenta cerrar la ventana
				Sistema.getInstancia().setClienteAppActivo(false);
				dispose(); // Cierra la ventana
			}
		});

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("REGISTRATE");
		panelNorth.add(lblNewLabel);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_11 = new JPanel();
		panel.add(panel_11);

		JLabel lblNewLabel_1 = new JLabel("Nombre:");
		panel_11.add(lblNewLabel_1);

		JPanel panel_12 = new JPanel();
		panel.add(panel_12);

		tl_nombrenuevo = new JTextField();
		panel_12.add(tl_nombrenuevo);
		tl_nombrenuevo.setColumns(10);

		JPanel panel_10 = new JPanel();
		panel.add(panel_10);

		JLabel lblNewLabel_2 = new JLabel("Apellido:");
		panel_10.add(lblNewLabel_2);

		JPanel panel_13 = new JPanel();
		panel.add(panel_13);

		tl_apellidonuevo = new JTextField();
		panel_13.add(tl_apellidonuevo);
		tl_apellidonuevo.setColumns(10);

		JPanel panel_9 = new JPanel();
		panel.add(panel_9);

		JLabel lblNewLabel_3 = new JLabel("Telefono:");
		panel_9.add(lblNewLabel_3);

		JPanel panel_14 = new JPanel();
		panel.add(panel_14);

		tl_telefononuevo = new JTextField();
		panel_14.add(tl_telefononuevo);
		tl_telefononuevo.setColumns(10);
		tl_telefononuevo.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});

		JPanel panel_8 = new JPanel();
		panel.add(panel_8);

		JLabel lblNewLabel_4 = new JLabel("Mail:");
		panel_8.add(lblNewLabel_4);

		JPanel panel_15 = new JPanel();
		panel.add(panel_15);

		tl_mailnuevo = new JTextField();
		panel_15.add(tl_mailnuevo);
		tl_mailnuevo.setColumns(10);

		JPanel panel_7 = new JPanel();
		panel.add(panel_7);

		JLabel lblNewLabel_5 = new JLabel("Direccion:");
		panel_7.add(lblNewLabel_5);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JPanel panel_17 = new JPanel();
		panel_2.add(panel_17);

		JLabel lblNewLabel_9 = new JLabel("Nombre:");
		panel_17.add(lblNewLabel_9);

		JPanel panel_18 = new JPanel();
		panel_2.add(panel_18);

		tf_nombreCalle = new JTextField();
		panel_18.add(tf_nombreCalle);
		tf_nombreCalle.setColumns(6);

		JPanel panel_19 = new JPanel();
		panel_2.add(panel_19);

		JLabel lblNewLabel_10 = new JLabel("Altura:");
		panel_19.add(lblNewLabel_10);

		JPanel panel_20 = new JPanel();
		panel_2.add(panel_20);

		tf_alturaCalle = new JTextField();
		panel_20.add(tf_alturaCalle);
		tf_alturaCalle.setColumns(4);
		tf_alturaCalle.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});

		JPanel panel_21 = new JPanel();
		panel_2.add(panel_21);

		JLabel lblNewLabel_11 = new JLabel("Piso:");
		panel_21.add(lblNewLabel_11);

		JPanel panel_22 = new JPanel();
		panel_2.add(panel_22);

		tf_PisoCalle = new JTextField();
		panel_22.add(tf_PisoCalle);
		tf_PisoCalle.setColumns(2);
		tf_PisoCalle.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if (!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});

		JPanel panel_23 = new JPanel();
		panel_2.add(panel_23);

		JLabel lblNewLabel_12 = new JLabel("Letra:");
		panel_23.add(lblNewLabel_12);

		JPanel panel_24 = new JPanel();
		panel_2.add(panel_24);

		tf_letraCalle = new JTextField();
		panel_24.add(tf_letraCalle);
		tf_letraCalle.setColumns(2);

		JPanel panel_5 = new JPanel();
		panel.add(panel_5);

		JLabel lblNewLabel_6 = new JLabel("Fecha de nacimiento:");
		panel_5.add(lblNewLabel_6);

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);

		this.dateChooser = new JDateChooser();
		panel_1.add(this.dateChooser);
		this.dateChooser.setPreferredSize(new Dimension(100,20));

		JPanel panel_6 = new JPanel();
		panel.add(panel_6);

		JLabel lblNewLabel_7 = new JLabel("Nombre de Usuario:");
		panel_6.add(lblNewLabel_7);

		JPanel panel_16 = new JPanel();
		panel.add(panel_16);

		tl_Usuarionuevo = new JTextField();
		panel_16.add(tl_Usuarionuevo);
		tl_Usuarionuevo.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel.add(panel_4);

		JLabel lblNewLabel_8 = new JLabel("Contraseña:");
		panel_4.add(lblNewLabel_8);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);

		tl_contrasenianuevo = new JTextField();
		panel_3.add(tl_contrasenianuevo);
		tl_contrasenianuevo.setColumns(10);

		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);

		this.btnRegistrarme = new JButton("Registrarme");
		panelSur.add(btnRegistrarme);

		this.btnRegistrarme.setActionCommand("REGISTRARME");
		this.btnRegistrarme.setEnabled(false); // Initially disabled

		// Add Document Listeners to the text fields
		DocumentListener documentListener = new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				checkFields();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				checkFields();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				checkFields();
			}
		};

		tl_nombrenuevo.getDocument().addDocumentListener(documentListener);
		tl_apellidonuevo.getDocument().addDocumentListener(documentListener);
		tl_telefononuevo.getDocument().addDocumentListener(documentListener);
		tl_mailnuevo.getDocument().addDocumentListener(documentListener);
		tf_nombreCalle.getDocument().addDocumentListener(documentListener);
		tf_alturaCalle.getDocument().addDocumentListener(documentListener);
		tl_Usuarionuevo.getDocument().addDocumentListener(documentListener);
		tl_contrasenianuevo.getDocument().addDocumentListener(documentListener);
		dateChooser.getDateEditor().addPropertyChangeListener(e -> checkFields());

		this.setVisible(false);
	}

	private void checkFields() {
		boolean enabled = !tl_nombrenuevo.getText().trim().isEmpty() &&
				!tl_apellidonuevo.getText().trim().isEmpty() &&
				!tl_telefononuevo.getText().trim().isEmpty() &&
				!tl_mailnuevo.getText().trim().isEmpty() &&
				!tf_nombreCalle.getText().trim().isEmpty() &&
				!tf_alturaCalle.getText().trim().isEmpty() &&
				!tl_Usuarionuevo.getText().trim().isEmpty() &&
				!tl_contrasenianuevo.getText().trim().isEmpty() &&
				dateChooser.getDate() != null;
		btnRegistrarme.setEnabled(enabled);
	}

	@Override
	public void setVisibleVentana(boolean estado) {
		this.setVisible(estado);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnRegistrarme.addActionListener(actionListener);
	}

	@Override
	public String getNombre() {
		return this.tl_nombrenuevo.getText();
	}

	@Override
	public String getApellido() {
		return this.tl_apellidonuevo.getText();
	}

	@Override
	public String getNombreUser() {
		return this.tl_Usuarionuevo.getText();
	}

	@Override
	public String getContrasenia() {
		return this.tl_contrasenianuevo.getText();
	}

	@Override
	public String getMail() {
		return this.tl_mailnuevo.getText();
	}

	@Override
	public String getTelefono() {
		return this.tl_telefononuevo.getText();
	}

	@Override
	public String getNombreCalle() {
		return this.tf_nombreCalle.getText();
	}

	@Override
	public String getAlturaCalle() {
		return this.tf_alturaCalle.getText();
	}

	@Override
	public String getPisoCalle() {
		return this.tf_PisoCalle.getText();
	}

	@Override
	public String getLetraCalle() {
		return this.tf_letraCalle.getText();
	}

	@Override
	public GregorianCalendar getFechaDeNacimiento() {
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(this.dateChooser.getDate());
		return date;
	}

	@Override
	public void limpiarcampos() {
		this.dateChooser.cleanup();
		this.tf_alturaCalle.setText("");
		this.tf_letraCalle.setText("");
		this.tf_PisoCalle.setText("");
		this.tf_nombreCalle.setText("");
		this.tl_apellidonuevo.setText("");
		this.tl_telefononuevo.setText("");
		this.tl_nombrenuevo.setText("");
		this.tl_mailnuevo.setText("");
		this.tl_contrasenianuevo.setText("");
		this.tl_Usuarionuevo.setText("");
	}
}
