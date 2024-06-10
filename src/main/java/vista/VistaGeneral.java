package vista;

import models.Sistema;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Representa la interfaz gráfica para configurar y controlar la simulación de un sistema de transporte
 */
public class VistaGeneral extends JFrame implements IVistaGeneral {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_cantChoferes;
	private JTextField tf_cantVehiculos;
	private JTextField tf_cantClientes;
	private JButton btnInicio;
	private JTextArea textAreaLogCliente;
	private JTextArea textArea_LogGral;
	private JTextArea textAreaLogChofer;
	private JRadioButton rdbtnPersistidos;
	/**
	 * Crea la ventana principal de la aplicación.
	 * Inicializa todos los componentes gráficos y configura los listeners necesarios.
	 */
	public VistaGeneral() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon1.png")));
		setTitle("Subi que te Llevo - Seteo de Simulacion y Logs");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(150, 50, 1200, 800);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Código a ejecutar cuando se intenta cerrar la ventana
				Sistema.getInstancia().detenerSimulacion();
				dispose(); // Cierra la ventana
			}
		});

		JPanel Seteo = new JPanel();
		Seteo.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		this.contentPane.add(Seteo, BorderLayout.NORTH);
		Seteo.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_Seteo = new JPanel();
		Seteo.add(panel_Seteo);
		panel_Seteo.setLayout(new GridLayout(3, 2, 0, 0));

		JPanel panelSet_lblChof = new JPanel();
		panel_Seteo.add(panelSet_lblChof);

		JLabel lblNewLabel = new JLabel("Cantidad de choferes :");
		panelSet_lblChof.add(lblNewLabel);

		JPanel panelSet_tfChof = new JPanel();
		panel_Seteo.add(panelSet_tfChof);

		this.tf_cantChoferes = new JTextField();
		panelSet_tfChof.add(this.tf_cantChoferes);
		this.tf_cantChoferes.setColumns(10);

		JPanel panelSet_lblCvehiculos = new JPanel();
		panel_Seteo.add(panelSet_lblCvehiculos);

		JLabel lblNewLabel_1 = new JLabel("Cantidad de vehiculos : ");
		panelSet_lblCvehiculos.add(lblNewLabel_1);

		JPanel panelSet_tfVehic = new JPanel();
		panel_Seteo.add(panelSet_tfVehic);

		this.tf_cantVehiculos = new JTextField();
		panelSet_tfVehic.add(this.tf_cantVehiculos);
		this.tf_cantVehiculos.setColumns(10);

		JPanel panelSet_lblClient = new JPanel();
		panel_Seteo.add(panelSet_lblClient);

		JLabel lblNewLabel_2 = new JLabel("Cantidad de clientes : ");
		panelSet_lblClient.add(lblNewLabel_2);

		JPanel panelSet_tfClient = new JPanel();
		panel_Seteo.add(panelSet_tfClient);

		this.tf_cantClientes = new JTextField();
		panelSet_tfClient.add(this.tf_cantClientes);
		this.tf_cantClientes.setColumns(10);

		JPanel panel_Comienzo = new JPanel();
		Seteo.add(panel_Comienzo);
		panel_Comienzo.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel panelComienzo_Persist = new JPanel();
		panel_Comienzo.add(panelComienzo_Persist);
		panelComienzo_Persist.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panelComienz_Persist_lbl = new JPanel();
		panelComienzo_Persist.add(panelComienz_Persist_lbl);

		JLabel lblNewLabel_3 = new JLabel("Empezar con datos persistidos : ");
		panelComienz_Persist_lbl.add(lblNewLabel_3);

		this.rdbtnPersistidos = new JRadioButton();

		JPanel panelComienz_Persist_rdbtn = new JPanel();
		panelComienzo_Persist.add(panelComienz_Persist_rdbtn);
		panelComienz_Persist_rdbtn.add(this.rdbtnPersistidos);

		JPanel panelComienzo_Inicio = new JPanel();
		panel_Comienzo.add(panelComienzo_Inicio);

		this.btnInicio = new JButton("Comenzar");
		panelComienzo_Inicio.add(this.btnInicio);
		this.btnInicio.setEnabled(false);

		JPanel LogGeneral = new JPanel();
		LogGeneral.setBorder(new TitledBorder(null, "Log General:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.contentPane.add(LogGeneral, BorderLayout.CENTER);
		LogGeneral.setPreferredSize(new Dimension(500, 400));
		LogGeneral.setLayout(new BorderLayout(0, 0));

		/*this.textArea_LogGral = new JTextArea();
		LogGeneral.add(this.textArea_LogGral);*/
		JScrollPane scrollPaneLogGral = new JScrollPane();
		this.textArea_LogGral = new JTextArea();
		scrollPaneLogGral.setViewportView(this.textArea_LogGral);
		LogGeneral.add(scrollPaneLogGral);

		JPanel panelCCH = new JPanel();
		this.contentPane.add(panelCCH, BorderLayout.EAST);
		panelCCH.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel panel_LogCliente = new JPanel();
		panel_LogCliente.setBorder(new TitledBorder(null, "Log Cliente:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCCH.add(panel_LogCliente);
		panel_LogCliente.setLayout(new BorderLayout(0, 0));

		/*this.textAreaLogCliente = new JTextArea();
		panel_LogCliente.add(this.textAreaLogCliente);*/
		JScrollPane scrollPaneLogCliente = new JScrollPane();
		this.textAreaLogCliente = new JTextArea();
		scrollPaneLogCliente.setViewportView(this.textAreaLogCliente);
		panel_LogCliente.add(scrollPaneLogCliente);

		JPanel panel_LogChofer = new JPanel();
		panel_LogChofer.setBorder(new TitledBorder(null, "Log Chofer :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCCH.add(panel_LogChofer);
		panel_LogChofer.setPreferredSize(new Dimension(300, 300));
		panel_LogChofer.setLayout(new BorderLayout(0, 0));

		/*this.textAreaLogChofer = new JTextArea();
		panel_LogChofer.add(this.textAreaLogChofer);*/
		JScrollPane scrollPaneLogChofer = new JScrollPane();
		this.textAreaLogChofer = new JTextArea();
		scrollPaneLogChofer.setViewportView(this.textAreaLogChofer);
		panel_LogChofer.add(scrollPaneLogChofer);


		this.rdbtnPersistidos.setActionCommand("Persistir");
		this.btnInicio.setActionCommand("Iniciar_Simulacion");

		// Añadir listeners a los campos de texto y al radio button
		KeyAdapter keyAdapter = new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				rdbtnPersistidos.setEnabled(false);
				checkInicioButtonStatus();
			}
		};

		tf_cantChoferes.addKeyListener(keyAdapter);
		tf_cantVehiculos.addKeyListener(keyAdapter);
		tf_cantClientes.addKeyListener(keyAdapter);

		rdbtnPersistidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdbtnPersistidos.isSelected()) {
					tf_cantChoferes.setEnabled(false);
					tf_cantVehiculos.setEnabled(false);
					tf_cantClientes.setEnabled(false);
					btnInicio.setEnabled(true);
				} else {
					tf_cantChoferes.setEnabled(true);
					tf_cantVehiculos.setEnabled(true);
					tf_cantClientes.setEnabled(true);
					checkInicioButtonStatus();
				}
			}
		});

		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnPersistidos.setEnabled(false);
			}
		});

		this.setVisible(true);
	}
	/**
	 * Verifica si el botón de inicio debe estar habilitado basándose en la validez de los campos de entrada.
	 */
	private void checkInicioButtonStatus() {
		if (isNumeric(tf_cantChoferes.getText()) && isNumeric(tf_cantVehiculos.getText()) && isNumeric(tf_cantClientes.getText())
				&& !tf_cantChoferes.getText().isEmpty() && !tf_cantVehiculos.getText().isEmpty() && !tf_cantClientes.getText().isEmpty()) {
			btnInicio.setEnabled(true);
			rdbtnPersistidos.setEnabled(false);
		} else {
			btnInicio.setEnabled(false);
			rdbtnPersistidos.setEnabled(true);
		}
	}
	/**
	 * Verifica si una cadena es numérica.
	 *
	 * @param str La cadena a verificar.
	 * @return true si la cadena es numérica, false en caso contrario.
	 */
	private boolean isNumeric(String str) {
		return str.matches("\\d+");  // Sólo números
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnInicio.addActionListener(actionListener);
		this.rdbtnPersistidos.addActionListener(actionListener);
	}

	@Override
	public void appendLogGeneral(String cartel) {
		textArea_LogGral.append(cartel + "\n");
	}

	@Override
	public void appendLogCliente(String cartel) {
		textAreaLogCliente.append(cartel + "\n");
	}

	@Override
	public void appendLogChofer(String cartel) {
		textAreaLogChofer.append(cartel + "\n");
	}

	@Override
	public int getCantChoferes() {
		return Integer.parseInt(this.tf_cantChoferes.getText());
	}

	@Override
	public int getCantVehiculos() {
		return Integer.parseInt(this.tf_cantVehiculos.getText());
	}

	@Override
	public int getCantClientes() {
		return Integer.parseInt(this.tf_cantClientes.getText());
	}

	public boolean isEnablePersistitdos(){
		return this.rdbtnPersistidos.isSelected();
	}

}
