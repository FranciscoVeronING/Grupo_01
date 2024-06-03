package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;

public class VistaAppCliente_inicio extends JFrame implements IVistaAppCliente_inicio{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnViajes;
	private JButton btnIr_a_Pedido;
	private JButton btnDatos;
	private JButton btnLogOut;
	private JLabel lbl_NombreUsuario;

	public VistaAppCliente_inicio() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorth = new JPanel();
		contentPane.add(panelNorth, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Hola!,");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelNorth.add(lblNewLabel);
		
		this.lbl_NombreUsuario = new JLabel("");
		panelNorth.add(lbl_NombreUsuario);
		
		JPanel panelCenter = new JPanel();
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new GridLayout(3, 0, 0, 0));
		
		JPanel panel_1 = new JPanel();
		panelCenter.add(panel_1);
		
		this.btnIr_a_Pedido = new JButton("Hacer Pedido");
		panel_1.add(btnIr_a_Pedido);
		
		JPanel panel_2 = new JPanel();
		panelCenter.add(panel_2);
		
		this.btnViajes = new JButton("Mis Viajes");
		panel_2.add(btnViajes);
		
		JPanel panel_3 = new JPanel();
		panelCenter.add(panel_3);
		
		this.btnDatos = new JButton("Mis Datos");
		panel_3.add(btnDatos);
		
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel = new JPanel();
		panelSur.add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel.add(lblNewLabel_1);
		
		JPanel panel_4 = new JPanel();
		panelSur.add(panel_4);
		
		JLabel lblNewLabel_2 = new JLabel("");
		panel_4.add(lblNewLabel_2);
		
		JPanel panel_5 = new JPanel();
		panelSur.add(panel_5);
		
		this.btnLogOut = new JButton("Salir");
		panel_5.add(btnLogOut);
		
		this.btnDatos.setActionCommand("MISDATOS");
		this.btnLogOut.setActionCommand("LOGOUT");
		this.btnViajes.setActionCommand("MISVIAJES");
		this.btnIr_a_Pedido.setActionCommand("FORMULARIO_PEDIDOS");

		this.setVisible(false);
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnDatos.addActionListener(actionListener);
		this.btnLogOut.addActionListener(actionListener);
		this.btnIr_a_Pedido.addActionListener(actionListener);
		this.btnViajes.addActionListener(actionListener);
	}

	public void setVisibleVentana(boolean estado) {
		this.setVisible(estado);
	}

	@Override
	public void setNombre(String nombre) {
		this.lbl_NombreUsuario.setText(nombre);
	}


}
