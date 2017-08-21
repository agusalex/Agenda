package com.mycompany.app.presentacion.vista;


import com.mycompany.app.presentacion.controlador.Controlador;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.demo.JCalendarDemo;
import javafx.scene.control.ComboBox;
import org.jdatepicker.DateModel;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.time.LocalDate;


public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepartamento;
	private JTextField txtEmail;
	private JButton btnAgregarPersona;
	private Controlador controlador;

	private JCalendar calendario;
	private JDayChooser fechaNacimiento;
	private JComboBox<String> Localidad;

	private JComboBox<String> Etiqueta;

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public JTextField getTxtCalle() {
		return txtCalle;
	}

	public JTextField getTxtAltura() {
		return txtAltura;
	}

	public JTextField getTxtPiso() {
		return txtPiso;
	}

	public JTextField getTxtDepartamento() {
		return txtDepartamento;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public Controlador getControlador() {
		return controlador;
	}

	public JCalendar getCalendario() {
		return calendario;
	}

	public JDayChooser getFechaNacimiento() {
		return fechaNacimiento;
	}

	public JComboBox<String> getLocalidad() {
		return Localidad;
	}

	public JComboBox<String> getEtiqueta() {
		return Etiqueta;
	}

	public VentanaPersona(Controlador controlador)
	{
		super();
		this.controlador = controlador;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 790, 590);
		contentPane.add(panel);
		panel.setLayout(null);

		int base=10;

		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10,base+0 , 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelefono = new JLabel("Teléfono");
		lblTelefono.setBounds(10, base+50, 113, 14);
		panel.add(lblTelefono);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, base+100, 113, 14);
		panel.add(lblCalle);

		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, base+150, 113, 14);
		panel.add(lblAltura);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(10, base+200, 113, 14);
		panel.add(lblPiso);



		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(10, base+250, 113, 14);
		panel.add(lblDepartamento);


		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, base+300, 113, 14);
		panel.add(lblEmail);

		JLabel lblfechadeNacimiento = new JLabel("Fecha de Nacimiento:");
		lblfechadeNacimiento.setBounds(350, base+0, 200, 14);
		panel.add(lblfechadeNacimiento);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, base+350, 113, 14);
		panel.add(lblLocalidad);

		JLabel lblEtiqueta = new JLabel("Etiqueta");
		lblEtiqueta.setBounds(10, base+400, 113, 14);
		panel.add(lblEtiqueta);
		
		
		

		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, base+0, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, base+50, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);



		txtCalle = new JTextField();
		txtCalle.setBounds(133, base+100, 150, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);

		txtAltura = new JTextField();
		txtAltura.setBounds(133, base+150, 200, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);


		txtPiso = new JTextField();
		txtPiso.setBounds(133, base+200, 250, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);

		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(133, base+250, 164, 20);
		panel.add(txtDepartamento);
		txtDepartamento.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(133, base+300, 164, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);



		JPanel panelFecha=new JPanel();
		panelFecha.setBounds(450, base+0, 300, 600);

		calendario = new JCalendar();
		calendario.setBounds(0, 0, 200, 200);
		panelFecha.add(calendario);
		fechaNacimiento=calendario.getDayChooser();
		fechaNacimiento.setBounds(0, 0, 200, 200);
		panelFecha.add(fechaNacimiento);
		panel.add(panelFecha);




		Localidad = new JComboBox<String>();
		Localidad.setBounds(133, base+350, 164, 20);
		panel.add(Localidad);

		Etiqueta = new JComboBox<String>();
		Etiqueta.setBounds(133, base+400, 164, 20);
		panel.add(Etiqueta);


		Localidad.addItem("San Miguel");
		Etiqueta.addItem("Amigos");

		
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.addActionListener(this.controlador);
		btnAgregarPersona.setBounds(208, base+450, 89, 23);
		panel.add(btnAgregarPersona);
		
		
		
		
		this.setVisible(true);
	}
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}



	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}
	
}

