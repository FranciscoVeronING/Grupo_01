package vista;



import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

public class VistaAppCliente_formulario extends JFrame implements IVistaAppCliente_formulario, KeyListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnPedir;
	private JTextField textField_Distancia;
	private JTextField textField_CantidadPersonas;
	private Choice choice_zona;
	private JRadioButton rdbtnMascota;
	private JRadioButton rdbtnEquipaje;
	private JButton btnVolver;
	
	public VistaAppCliente_formulario() {
		//setIconImage(Toolkit.getDefaultToolkit().getImage(VistaAppCliente_formulario.class.getResource("/vista/icon1.png")));
		setTitle("Subi que te Llevo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 398, 423);
		this.contentPane = new JPanel();
		this.contentPane.setBorder(new TitledBorder(null, "Completa el Formulario para realizar el pedido!", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		setContentPane(this.contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pDP_centro = new JPanel();
		pDP_centro.setBorder(null);
		pDP_centro.setBackground(new Color(163, 162, 126));
		contentPane.add(pDP_centro, BorderLayout.CENTER);
		pDP_centro.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_lbl_cantPasajeros = new JPanel();
		pDP_centro.add(panel_lbl_cantPasajeros);
		
		JLabel lblNewLabel_2_1 = new JLabel("Cantidad de Pasajeros (Max 10): ");
		panel_lbl_cantPasajeros.add(lblNewLabel_2_1);
		
		JPanel panel_TextField_CantPasaj = new JPanel();
		pDP_centro.add(panel_TextField_CantPasaj);
		
		this.textField_CantidadPersonas = new JTextField();
		this.textField_CantidadPersonas.setColumns(10);
		panel_TextField_CantPasaj.add(this.textField_CantidadPersonas);
		
		JPanel panel_lbl_Mascotas = new JPanel();
		pDP_centro.add(panel_lbl_Mascotas);
		
		JLabel lblNewLabel_3_1 = new JLabel("Mascotas :");
		panel_lbl_Mascotas.add(lblNewLabel_3_1);
		
		JPanel panel_rbtn_Mascotas_1 = new JPanel();
		pDP_centro.add(panel_rbtn_Mascotas_1);
		
		this.rdbtnMascota = new JRadioButton("Si");
		panel_rbtn_Mascotas_1.add(rdbtnMascota);
		
		JPanel panel_lbl_equipaje_1 = new JPanel();
		pDP_centro.add(panel_lbl_equipaje_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Equipaje : ");
		panel_lbl_equipaje_1.add(lblNewLabel_4_1);
		
		JPanel panel_rbtn_equipaje_1 = new JPanel();
		pDP_centro.add(panel_rbtn_equipaje_1);
		
		this.rdbtnEquipaje = new JRadioButton("Si");
		panel_rbtn_equipaje_1.add(rdbtnEquipaje);
		
		JPanel panel_lbl_zona_1 = new JPanel();
		pDP_centro.add(panel_lbl_zona_1);
		
		JLabel lblNewLabel_5_1 = new JLabel("Zona :");
		panel_lbl_zona_1.add(lblNewLabel_5_1);
		
		JPanel panel_choice_zona_1 = new JPanel();
		pDP_centro.add(panel_choice_zona_1);
		panel_choice_zona_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		this.choice_zona = new Choice();
		choice_zona.setPreferredSize(new Dimension(90, 20));
		panel_choice_zona_1.add(choice_zona);
		
		JPanel panel_lbl_Dist_1 = new JPanel();
		pDP_centro.add(panel_lbl_Dist_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("Distancia (Km): ");
		panel_lbl_Dist_1.add(lblNewLabel_6_1);
		
		JPanel panel_textField_Dist_1 = new JPanel();
		pDP_centro.add(panel_textField_Dist_1);
		
		this.textField_Distancia = new JTextField();
		this.textField_Distancia.setColumns(10);
		panel_textField_Dist_1.add(this.textField_Distancia);
		
		JPanel pDP_sur = new JPanel();
		pDP_sur.setPreferredSize(new Dimension(50, 40));
		contentPane.add(pDP_sur, BorderLayout.SOUTH);
		pDP_sur.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_7 = new JLabel("");
		pDP_sur.add(lblNewLabel_7);
		
		this.btnPedir = new JButton("Solicitar Pedido");
		btnPedir.setEnabled(false);
		btnPedir.setActionCommand("PEDIR");
		pDP_sur.add(btnPedir);
		
		JPanel panel = new JPanel();
		pDP_sur.add(panel);
		
		this.btnVolver = new JButton("Volver");
		panel.add(btnVolver);
		this.btnVolver.setActionCommand("VOLVER");

		this.setVisible(false);
	}
	public void setVisibleVentana(boolean estado) {
		this.setVisible(estado);
	}
	@Override
	public int getCantidadPasajeros() {
		return Integer.parseInt(textField_CantidadPersonas.getText());
	}
	@Override
	public boolean hayMascota() {
		return this.rdbtnMascota.isSelected();	
	}
	@Override
	public boolean hayEquipaje() {
		return this.rdbtnEquipaje.isSelected();	
	}
	@Override
	public double getDistancia() {
		return Double.parseDouble(textField_Distancia.getText());
		
	}
	@Override
	public String getZona() {
		return this.choice_zona.getSelectedItem();	
	}
	@Override
	public void setActionListener(ActionListener actionListener) {

		this.btnPedir.addActionListener(actionListener);
		this.btnVolver.addActionListener(actionListener);
	}
	
	public void verificaCampos() {
	    boolean camposValidos = true;

	    // Verifica que la distancia no esté vacía y sea un número válido.
	    try {
	        camposValidos &= this.getDistancia() > 0;
	    } catch (NumberFormatException e) {
	        camposValidos = false;
	    }

	    // Verifica que la cantidad de personas no esté vacía, sea un número válido y esté en el rango permitido.
	    try {
	        camposValidos &= this.getCantidadPasajeros() > 0 && this.getCantidadPasajeros() <= 10;
	        //verifica de que sihay mascota y numero de pasajeros > 4 no se pueda hacer pedido
	        if(this.hayMascota() && this.getCantidadPasajeros() > 4) {
	            camposValidos = false;
	        }
	    } catch (NumberFormatException e) {
	        camposValidos = false;
	    }

	    // Verifica que se haya seleccionado una zona.
	    camposValidos &= !choice_zona.getSelectedItem().isEmpty();

	    // Habilita o deshabilita el botón según la validez de los campos.
	    btnPedir.setEnabled(camposValidos);
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// No es necesario
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// No es necesario
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		this.verificaCampos();
		
		
	}

}
