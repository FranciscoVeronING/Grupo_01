package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;

public class VentanaAppCliente extends JFrame implements IVentanaAppCliente{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_Distancia;
	private JTextField textField_CantidadPersonas;
	private JTabbedPane tabbedPane;
	private Choice choice_zona;
	public VentanaAppCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 423);
		//this.setPreferredSize(new Dimension(720,480)); //para poner el tamaño fijo de la ventana
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(this.contentPane);
		this.contentPane.setLayout(new BorderLayout(0, 0));
		
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.contentPane.add(this.tabbedPane, BorderLayout.CENTER);
		
		JPanel panel_Descripcion_Pedido = new JPanel();
		tabbedPane.addTab("Pedido", null, panel_Descripcion_Pedido, null);
		panel_Descripcion_Pedido.setLayout(new BorderLayout(0, 0));
		
		JPanel pDP_centro = new JPanel();
		pDP_centro.setBorder(null);
		pDP_centro.setBackground(new Color(163, 162, 126));
		panel_Descripcion_Pedido.add(pDP_centro);
		pDP_centro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_lbl_cantPasajeros = new JPanel();
		pDP_centro.add(panel_lbl_cantPasajeros);
		
		JLabel lblNewLabel_2 = new JLabel("Cantidad de Pasajeros : ");
		panel_lbl_cantPasajeros.add(lblNewLabel_2);
		
		JPanel panel_TextField_CantPasaj = new JPanel();
		pDP_centro.add(panel_TextField_CantPasaj);
		
		textField_CantidadPersonas = new JTextField();
		panel_TextField_CantPasaj.add(textField_CantidadPersonas);
		textField_CantidadPersonas.setColumns(10);
		
		JPanel panel_lbl_Mascotas = new JPanel();
		pDP_centro.add(panel_lbl_Mascotas);
		
		JLabel lblNewLabel_3 = new JLabel("Mascotas :");
		panel_lbl_Mascotas.add(lblNewLabel_3);
		
		JPanel panel_rbtn_Mascotas = new JPanel();
		pDP_centro.add(panel_rbtn_Mascotas);
		
		JRadioButton rdbtnMascota = new JRadioButton("Si");
		panel_rbtn_Mascotas.add(rdbtnMascota);
		
		JPanel panel_lbl_equipaje = new JPanel();
		pDP_centro.add(panel_lbl_equipaje);
		
		JLabel lblNewLabel_4 = new JLabel("Equipaje : ");
		panel_lbl_equipaje.add(lblNewLabel_4);
		
		JPanel panel_rbtn_equipaje = new JPanel();
		pDP_centro.add(panel_rbtn_equipaje);
		
		JRadioButton rdbtnEquipaje = new JRadioButton("Si");
		panel_rbtn_equipaje.add(rdbtnEquipaje);
		
		JPanel panel_lbl_zona = new JPanel();
		pDP_centro.add(panel_lbl_zona);
		
		JLabel lblNewLabel_5 = new JLabel("Zona :");
		panel_lbl_zona.add(lblNewLabel_5);
		
		JPanel panel_choice_zona = new JPanel();
		pDP_centro.add(panel_choice_zona);
		panel_choice_zona.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.choice_zona = new Choice();
		this.choice_zona.add("Peligrosa");
		this.choice_zona.add("Sin Asfaltar");
		this.choice_zona.add("Estandar"); ///PREGUNTAR QUE USAR
		panel_choice_zona.add(choice_zona);
		this.choice_zona.setPreferredSize(new Dimension(90,20));
		
		JPanel panel_lbl_Dist = new JPanel();
		pDP_centro.add(panel_lbl_Dist);
		
		JLabel lblNewLabel_6 = new JLabel("Distancia (Km): ");
		panel_lbl_Dist.add(lblNewLabel_6);
		
		JPanel panel_textField_Dist = new JPanel();
		pDP_centro.add(panel_textField_Dist);
		
		textField_Distancia = new JTextField();
		panel_textField_Dist.add(textField_Distancia);
		textField_Distancia.setColumns(10);
		
		JPanel panel_lbl_PrecioTot = new JPanel();
		pDP_centro.add(panel_lbl_PrecioTot);
		
		JLabel lblNewLabel_7 = new JLabel("Precio total : ");
		panel_lbl_PrecioTot.add(lblNewLabel_7);
		
		JPanel panel_lbl_PrecioTot2 = new JPanel();
		pDP_centro.add(panel_lbl_PrecioTot2);
		
		JLabel lbl_Precio_Total = new JLabel("--");
		panel_lbl_PrecioTot2.add(lbl_Precio_Total);
		
		JPanel pDP_sur = new JPanel();
		panel_Descripcion_Pedido.add(pDP_sur, BorderLayout.SOUTH);
		pDP_sur.setPreferredSize(new Dimension(50,40));
		pDP_sur.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		pDP_sur.add(lblNewLabel);
		
		JButton btnPedir = new JButton("Solicitar Pedido");
		pDP_sur.add(btnPedir);
		
		JLabel lblNewLabel_1 = new JLabel("");
		pDP_sur.add(lblNewLabel_1);
		
		textField_CantidadPersonas.setActionCommand("CantidadPasajeros");
		rdbtnEquipaje.setActionCommand("Equipaje");
		rdbtnMascota.setActionCommand("Mascota");
		//Choice como hacerlo?
		textField_Distancia.setActionCommand("Distancia");
		btnPedir.setActionCommand("Pedir");
	}


	@Override
	public int getCantidadPasajeros() {
		return Integer.parseInt(textField_CantidadPersonas.getText());
	}


	@Override
	public void hayMascota() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void hayEquipaje() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int getDistancia() {
		return Integer.parseInt(textField_Distancia.getText());
		
	}


	@Override
	public void getZona() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void getPedido() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setActionListener(ActionListener actionListener) {
				
		btnPedir.addActionListener(actionListener);
	}

}
