package vista;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.util.Objects;
import javax.swing.ImageIcon;

public class VistaAppCliente_login extends JFrame implements IVistaAppCliente_login {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnRegistrar;
	private JTextField tf_nombre_usuario;
	private JPasswordField field_contrasenia;
	private JButton btnIngresar ;


	public VistaAppCliente_login() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon1.png")));
		setTitle("Subi que te Llevo");
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Sur = new JPanel();
		contentPane.add(panel_Sur, BorderLayout.SOUTH);
		panel_Sur.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel lbl_Ingresar = new JLabel("");
		panel_Sur.add(lbl_Ingresar);
		
		JPanel panel = new JPanel();
		panel_Sur.add(panel);
		
		this.btnIngresar = new JButton("Ingresar");
		panel.add(btnIngresar);
		
		JLabel lblNewLabel = new JLabel("");
		panel_Sur.add(lblNewLabel);
		
		JPanel panel_Registrarme = new JPanel();
		panel_Sur.add(panel_Registrarme);
		
		JLabel lbl_Registrar = new JLabel("¿Sos nuevo/a?");
		panel_Registrarme.add(lbl_Registrar);
		
		JPanel panel_1 = new JPanel();
		panel_Sur.add(panel_1);
		
		btnRegistrar = new JButton("Registrarme");
		panel_1.add(btnRegistrar);
		
		JLabel lblNewLabel_1 = new JLabel("");
		panel_Sur.add(lblNewLabel_1);
		
		JPanel panel_center = new JPanel();
		contentPane.add(panel_center, BorderLayout.CENTER);
		panel_center.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel panel_7 = new JPanel();
		panel_center.add(panel_7);
		
		JLabel lblNewLabel_2 = new JLabel("Nombre de ususario:");
		panel_7.add(lblNewLabel_2);
		
		JPanel panel_6 = new JPanel();
		panel_center.add(panel_6);
		
		tf_nombre_usuario = new JTextField();
		panel_6.add(tf_nombre_usuario);
		tf_nombre_usuario.setColumns(10);
		
		JPanel panel_5 = new JPanel();
		panel_center.add(panel_5);
		
		JLabel lblNewLabel_3 = new JLabel("");
		panel_5.add(lblNewLabel_3);
		
		JPanel panel_4 = new JPanel();
		panel_center.add(panel_4);
		
		JLabel lblNewLabel_4 = new JLabel("Contraseña:");
		panel_4.add(lblNewLabel_4);
		
		JPanel panel_3 = new JPanel();
		panel_center.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		field_contrasenia = new JPasswordField();
		field_contrasenia.setPreferredSize(new Dimension(90,20));
		panel_3.add(field_contrasenia);
		
		JPanel panel_2 = new JPanel();
		panel_center.add(panel_2);
		
		JLabel lblNewLabel_5 = new JLabel("");
		panel_2.add(lblNewLabel_5);
		
		JPanel panel_8 = new JPanel();
		contentPane.add(panel_8, BorderLayout.NORTH);
		
		JLabel Title = new JLabel("SUBI QUE TE LLEVO");
		Title.setIcon(new ImageIcon(Objects.requireNonNull(VistaAppCliente_login.class.getResource("/icon1.png"))));
		Title.setFont(new Font("Segoe Print", Font.BOLD, 20));
		Title.setHorizontalAlignment(SwingConstants.TRAILING);
		panel_8.add(Title);
		
		
		this.btnIngresar.setActionCommand("INGRESAR");
		this.btnRegistrar.setActionCommand("REGISTRAR");
		this.setVisible(true);
		
	}

	@Override
	public String getNombreUsuario() {
		return this.tf_nombre_usuario.getText();
	}

	@Override
	public String getContrasenia() {
		return this.field_contrasenia.getText();
	}

	@Override
	public void setActionListener(ActionListener actionListener) {
		this.btnIngresar.addActionListener(actionListener);
		this.btnRegistrar.addActionListener(actionListener);
		
	}
	public void setVisibleVentana(boolean estado) {
		this.setVisible(estado);
	}

	@Override
	public void limpiarcampos() {
		this.tf_nombre_usuario.setText("");
		this.field_contrasenia.setText("");
	}

}
