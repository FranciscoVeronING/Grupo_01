package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JTextArea;

public class VistaGeneral extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tf_cantChoferes;
	private JTextField tf_cantVehiculos;
	private JTextField tf_cantClientes;
	private JButton btnFinalizar;
	private JButton btnInicio;
	private JTextArea textAreaLogCliente;
	private JTextArea textArea_LogGral;
	private JTextArea textAreaLogChofer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VistaGeneral frame = new VistaGeneral();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VistaGeneral() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// contentPane.setPreferredSize(new Dimension(900,900));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));

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

		JPanel panelComienz_Persist_rdbtn = new JPanel();
		panelComienzo_Persist.add(panelComienz_Persist_rdbtn);

		JRadioButton rdbtnPersistidos = new JRadioButton("Si");
		panelComienz_Persist_rdbtn.add(rdbtnPersistidos);

		JPanel panelComienzo_Inicio = new JPanel();
		panel_Comienzo.add(panelComienzo_Inicio);

		this.btnInicio = new JButton("Comenzar");
		panelComienzo_Inicio.add(this.btnInicio);

		JPanel LogGeneral = new JPanel();
		LogGeneral
				.setBorder(new TitledBorder(null, "Log General:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.contentPane.add(LogGeneral, BorderLayout.CENTER);
		LogGeneral.setPreferredSize(new Dimension(500, 400));
		LogGeneral.setLayout(new BorderLayout(0, 0));

		this.textArea_LogGral = new JTextArea();
		LogGeneral.add(this.textArea_LogGral);

		JPanel panelCCH = new JPanel();
		this.contentPane.add(panelCCH, BorderLayout.EAST);
		panelCCH.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel panel_LogCliente = new JPanel();
		panel_LogCliente
				.setBorder(new TitledBorder(null, "Log Cliente:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCCH.add(panel_LogCliente);
		panel_LogCliente.setLayout(new BorderLayout(0, 0));

		this.textAreaLogCliente = new JTextArea();
		panel_LogCliente.add(this.textAreaLogCliente);

		JPanel panel_LogChofer = new JPanel();
		panel_LogChofer
				.setBorder(new TitledBorder(null, "Log Chofer :", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelCCH.add(panel_LogChofer);
		panel_LogChofer.setPreferredSize(new Dimension(300, 300));
		panel_LogChofer.setLayout(new BorderLayout(0, 0));

		this.textAreaLogChofer = new JTextArea();
		panel_LogChofer.add(this.textAreaLogChofer);

		JPanel panelSurFin = new JPanel();
		this.contentPane.add(panelSurFin, BorderLayout.SOUTH);
		panelSurFin.setLayout(new BorderLayout(0, 0));

		this.btnFinalizar = new JButton("Finalizar Simulacion");
		panelSurFin.add(this.btnFinalizar);

		this.btnFinalizar.setActionCommand("Finalizar_Simulacion");
		this.btnFinalizar.setActionCommand("Iniciar_Simulacion");
	}

}
