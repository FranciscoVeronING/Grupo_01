package vista;

import models.Sistema;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Representa la interfaz gráfica de la ventana que muestra los viajes realizados por el cliente en la aplicación.
 */
public class VistaAppCliente_MisViajes extends JFrame implements IVistaAppCliente_MisViajes{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelSur;
	private JLabel lblNewLabel;
	private JButton btnVolver;
	private JPanel panel;
	private JPanel panelCentral;
	private JTextArea logViajes;
	private JScrollPane scrollPane;

	public VistaAppCliente_MisViajes() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\franc\\Documents\\GitHub\\Grupo_01\\src\\main\\resources\\icon1.png"));
		setTitle("Subi que te Llevo - Mis Viajes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// Código a ejecutar cuando se intenta cerrar la ventana
				Sistema.getInstancia().setClienteAppActivo(false);
				dispose(); // Cierra la ventana
			}
		});

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.panelSur = new JPanel();
		this.contentPane.add(this.panelSur, BorderLayout.SOUTH);
		this.panelSur.setLayout(new GridLayout(1, 0, 0, 0));
		
		this.lblNewLabel = new JLabel("");
		this.panelSur.add(this.lblNewLabel);
		
		this.panel = new JPanel();
		this.panelSur.add(this.panel);
		
		this.btnVolver = new JButton("Volver");
		this.panel.add(this.btnVolver);
		
		this.panelCentral = new JPanel();
		this.panelCentral.setBorder(new TitledBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null), "Viajes Realizados:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		this.contentPane.add(this.panelCentral, BorderLayout.CENTER);
		
		this.scrollPane = new JScrollPane();
		this.panelCentral.add(this.scrollPane);

		this.logViajes = new JTextArea();
		this.logViajes.setLineWrap(true);
		this.logViajes.setWrapStyleWord(true);
		this.scrollPane.setViewportView(this.logViajes);
		this.logViajes.setPreferredSize(new Dimension(400, 400));
		
		this.btnVolver.setActionCommand("VOLVER_MV");
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
	public void appendText(String arg) {
		this.logViajes.append(arg+"\n");
	}

	@Override
	public void limpiarVentana() {
	    this.logViajes.setText(""); 
	}

	
}
