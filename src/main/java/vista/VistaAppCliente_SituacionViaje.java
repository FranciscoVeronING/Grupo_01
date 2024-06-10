package vista;

import models.Sistema;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JButton;

/**
 * Representa la interfaz gráfica para mostrar la situación de un viaje en la aplicación
 */
public class VistaAppCliente_SituacionViaje extends JFrame implements IVistaAppCliente_SituacionViaje {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panelCentral;
	private JLabel lbl_ViajeConfirmado;
	private JLabel lblEstadoViaje;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panelSur;
	private JButton btnPagar;

	public VistaAppCliente_SituacionViaje() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon1.png")));
		setTitle("Subi que te llevo - Situacion de Viaje");
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

		this.panelCentral = new JPanel();
		this.contentPane.add(this.panelCentral, BorderLayout.CENTER);
		this.panelCentral.setLayout(new GridLayout(0, 1, 0, 0));

		this.panel = new JPanel();
		this.panelCentral.add(this.panel);

		this.lbl_ViajeConfirmado = new JLabel("Situación Viaje");
		this.panel.add(this.lbl_ViajeConfirmado);

		this.panel_1 = new JPanel();
		this.panelCentral.add(this.panel_1);

		this.lblEstadoViaje = new JLabel("Pedido Confirmado, esperando vehículo ...");
		this.panel_1.add(this.lblEstadoViaje);

		this.panelSur = new JPanel();
		this.contentPane.add(this.panelSur, BorderLayout.SOUTH);

		this.btnPagar = new JButton("Pagar");
		this.btnPagar.setEnabled(false);
		this.panelSur.add(this.btnPagar);
		this.btnPagar.setActionCommand("PAGAR");

		this.setVisible(false);

	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnPagar.addActionListener(actionListener);

	}
	@Override
	public void setVisibleVentana(boolean estado){
		this.setVisible(estado);
	}

	@Override
	public void actualizarEstadoViaje(String nuevoEstado) {
	    this.lblEstadoViaje.setText(nuevoEstado);
	}

	@Override
	public void limpiarcampos() {
		//TODO
	}

	@Override
	public void setbotonPagar(boolean x) {
		this.btnPagar.setEnabled(x);
	}

}
