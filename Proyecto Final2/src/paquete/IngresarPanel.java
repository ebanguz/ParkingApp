package paquete;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;

import paquete.*;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Font;

public class IngresarPanel extends JPanel implements Vehiculo {

	
	
	JTextField txtPlaca;
    JTextField txtPropietario;
    JTextField txtEmail;
	JRadioButton rbAuto;
	JRadioButton rbMoto;
	ButtonGroup grupo;

	public IngresarPanel() {
		setBackground(new java.awt.Color(65, 14, 51));

		setBounds(0, 0, 526, 690);

		setLayout(null);
		
		
				txtPlaca = new JTextField();
				txtPlaca.setFont(new Font("Avenir", Font.PLAIN, 20));
				txtPlaca.setBounds(168, 140, 222, 36);
				txtPlaca.setBorder(BorderFactory.createEmptyBorder());
				add(txtPlaca);
				txtPlaca.setColumns(10);

		JLabel lblNewLabel = new JLabel("Placa:");
		lblNewLabel.setForeground(UIManager.getColor("Button.highlight"));
		lblNewLabel.setFont(new Font("Avenir", Font.PLAIN, 27));
		lblNewLabel.setBounds(168, 92, 83, 36);
		add(lblNewLabel);

		JLabel lblPropietario = new JLabel("Propietario:");
		lblPropietario.setForeground(UIManager.getColor("Button.highlight"));
		lblPropietario.setFont(new Font("Avenir", Font.PLAIN, 24));
		lblPropietario.setBounds(168, 217, 145, 26);
		add(lblPropietario);

		JLabel lblTipoDeVehiculos = new JLabel("Tipo de Vehiculo:");
		lblTipoDeVehiculos.setForeground(UIManager.getColor("Button.highlight"));
		lblTipoDeVehiculos.setFont(new Font("Avenir", Font.PLAIN, 23));
		lblTipoDeVehiculos.setBounds(179, 444, 184, 23);
		add(lblTipoDeVehiculos);

		txtPropietario = new JTextField();
		txtPropietario.setFont(new Font("Avenir", Font.PLAIN, 20));
		txtPropietario.setColumns(10);
		txtPropietario.setBounds(168, 248, 222, 36);
		add(txtPropietario);

		rbAuto = new JRadioButton("Automovil");
		rbAuto.setForeground(UIManager.getColor("Button.highlight"));
		rbAuto.setFont(new Font("Avenir", Font.PLAIN, 18));
		rbAuto.setBounds(284, 493, 118, 23);
		add(rbAuto);

		rbMoto = new JRadioButton("Motocicleta");
		rbMoto.setForeground(UIManager.getColor("Button.highlight"));
		rbMoto.setFont(new Font("Avenir", Font.PLAIN, 18));
		rbMoto.setBounds(130, 493, 127, 23);
		add(rbMoto);

		grupo = new ButtonGroup();

		grupo.add(rbMoto);
		grupo.add(rbAuto);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Avenir", Font.PLAIN, 25));

		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegistrarVehiculo();

			}
		});
		btnRegistrar.setBounds(169, 585, 205, 75);
		add(btnRegistrar);

		JLabel lblEmail = new JLabel("E-mail:");
		lblEmail.setForeground(UIManager.getColor("Button.highlight"));
		lblEmail.setFont(new Font("Avenir", Font.PLAIN, 24));
		lblEmail.setBounds(168, 330, 105, 26);
		add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Avenir", Font.PLAIN, 20));
		txtEmail.setColumns(10);
		txtEmail.setBounds(168, 358, 222, 36);
		add(txtEmail);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);

			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("/Users/Eban/Desktop/Screen Shot 2020-04-01 at 12.22.46 PM.png"));
		lblNewLabel_1.setBounds(6, 6, 42, 36);
		add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.setBounds(162, 136, 232, 45);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(162, 244, 232, 45);
		add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("");
		btnNewButton_1_1.setBounds(162, 354, 232, 45);
		add(btnNewButton_1_1);
		
		txtPropietario.setBorder(BorderFactory.createEmptyBorder());
		txtEmail.setBorder(BorderFactory.createEmptyBorder());
		btnRegistrar.setForeground(new java.awt.Color(65, 14, 51));


	}

	public void Limpiar() {

		txtPlaca.setText("");
		txtPropietario.setText("");
		txtEmail.setText("");
		grupo.clearSelection();

	}

	
	
	@Override
	public void RegistrarVehiculo() {

		String fechaHora = "";
		String clasevehiculo = "";

		if (rbAuto.isSelected()) {
			clasevehiculo = "Automovil";
		}
		if (rbMoto.isSelected()) {
			clasevehiculo = "Motocicleta";
		}

		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ITLA",
					"root", "");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());

			fechaHora = dateFormat.format(sqlDate);

			System.out.println(dateFormat.format(sqlDate));

			java.sql.Statement stat = conexion.createStatement();

			String sql = "INSERT INTO `ITLA`.`vehiculos` (`placa`, `propietario`, `email` , `tipovehiculo`, `horaentrada` , `estado`) VALUES ('"
					+ txtPlaca.getText() + "' , '" + txtPropietario.getText() + "' , '" + txtEmail.getText()
					+ "' ,  '" + clasevehiculo + "' ,'" + fechaHora + "', 'Disponible')";

			stat.executeUpdate(sql);

			conexion.close();

			Limpiar();
		

		} catch (ClassNotFoundException o) {

			o.printStackTrace();
		}

		catch (SQLException i) {
			i.printStackTrace();
			JOptionPane.showMessageDialog(null, "INTENTA DE NUEVO");

		}
		
	}

	@Override
	public void RetirarVehiculo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void BuscarVehiculo() {
		// TODO Auto-generated method stub
		
	}


}
