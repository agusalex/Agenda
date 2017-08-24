package com.mycompany.app.presentacion.vista;


import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.dto.PersonaDTO;
import com.mycompany.app.presentacion.controlador.Controlador;
import com.mycompany.app.presentacion.controlador.Utils;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VentanaPersona extends JFrame
{
	private static final long serialVersionUID = 1L;
	private PersonaDTO personaDTO;

	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtCalle;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepartamento;
	private JTextField txtEmail;
	private JButton btnAgregarPersona;
	private  JButton btnGuardarPersona;
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
	cargarLocalidades();
	panel.add(Localidad);

	Etiqueta = new JComboBox<String>();
	Etiqueta.setBounds(133, base+400, 164, 20);
	cargarEtiquetas();
	panel.add(Etiqueta);

	btnAgregarPersona = new JButton("Agregar");
	btnAgregarPersona.addActionListener(this.controlador);
	btnAgregarPersona.setBounds(208, base+450, 89, 23);
	panel.add(btnAgregarPersona);

	this.setVisible(true);
}

	public JButton getBtnGuardarPersona() {
		return btnGuardarPersona;
	}

	public VentanaPersona(Controlador controlador, PersonaDTO personaDTO)
	{
		super();
		this.personaDTO=personaDTO;
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
		txtNombre.setText(personaDTO.getNombre());
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, base+50, 164, 20);
		txtTelefono.setText(personaDTO.getTelefono());
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		txtCalle = new JTextField();
		txtCalle.setBounds(133, base+100, 150, 20);
		txtCalle.setText(personaDTO.getCalle());
		panel.add(txtCalle);
		txtCalle.setColumns(10);

		txtAltura = new JTextField();
		txtAltura.setBounds(133, base+150, 200, 20);
		txtAltura.setText(Integer.toString(personaDTO.getAltura()));
		panel.add(txtAltura);
		txtAltura.setColumns(10);


		txtPiso = new JTextField();
		txtPiso.setBounds(133, base+200, 250, 20);
		txtPiso.setText(Integer.toString(personaDTO.getPiso()));
		panel.add(txtPiso);
		txtPiso.setColumns(10);

		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(133, base+250, 164, 20);
		txtDepartamento.setText(personaDTO.getDepartamento());
		panel.add(txtDepartamento);
		txtDepartamento.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(133, base+300, 164, 20);
		txtEmail.setText(personaDTO.getEmail());
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

		if(personaDTO.getFechaNacimmiento()!=null)
			calendario.setDate(personaDTO.getFechaNacimmiento());

		panel.add(panelFecha);


		Localidad = new JComboBox<String>();
		Localidad.setBounds(133, base+350, 164, 20);
		cargarLocalidades();
		Localidad.setSelectedItem(personaDTO.getLocalidad().toString());
		panel.add(Localidad);

		Etiqueta = new JComboBox<String>();
		Etiqueta.setBounds(133, base+400, 164, 20);
		cargarEtiquetas();
		Etiqueta.setSelectedItem(personaDTO.getEtiqueta().toString());
		panel.add(Etiqueta);

		btnGuardarPersona = new JButton("Guardar");
		btnGuardarPersona.addActionListener(this.controlador);
		btnGuardarPersona.setBounds(208, base+450, 89, 23);
		panel.add(btnGuardarPersona);


		this.setVisible(true);
	}

	public JTextField getTxtNombre()
	{
		return txtNombre;
	}


	public void cargarLocalidades(){
		for(LocalidadDTO loc:controlador.cargarLocalidades())
			if(loc!=null)
				Localidad.addItem(loc.getNombre());
	}


	public void cargarEtiquetas(){
		for(EtiquetaDTO et:controlador.cargarEtiquetas())
			if(et!=null)
				Etiqueta.addItem(et.getNombre());
	}
	
	
	public JButton getBtnAgregarPersona()
	{
		return btnAgregarPersona;
	}

	public JTextField[] getFields(){
		JTextField[] fields = { txtNombre,
								txtTelefono,
								txtCalle,
								txtAltura,
								txtPiso,
								txtDepartamento,
								txtEmail,
		};
		return fields;
	}

	public String[] getFieldValues(){
		String[] fieldValues = { txtNombre.getText(),
		 						 txtTelefono.getText(),
				                 txtCalle.getText(),
								 txtAltura.getText(),
								 txtPiso.getText(),
								 txtDepartamento.getText(),
								 txtEmail.getText(),
		};
		return fieldValues;
	}

	public String [] regex(){
		String[] regexValues = { "/^[A-z]+$/" ,
								 "^1?(\\d{10})",
								 "^\\d*\\s*((?:\\w+\\s*)*)ST$",
								 "/^\\d{2}(?:\\d{2})?$/",
								 "/^\\d{4}(?:\\d{2})?$/",
								 "/^\\d{2}(?:\\d{2})?$/",
								 "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
		};
		return regexValues;
	}

	public String [] errorMessages(){
		String[] errorMessages = {  "Nombre con caracteres invalidos",
									"Telefono con caracteres invalidos",
									"Nombre de calle con caracteres invalidos",
									"Numero de calle con caracteres invalidos",
								    "Numero de piso con caracteres invalidos",
				 					"numero de dpto con caracteres invalidos",
									"formato de mail invalido",
		};
		return errorMessages;
	}

	public void showErrorMessage(JTextField[] fields,String[] errorMessages, int index){
		Label label = new Label(errorMessages[index]);
		JTextField field = fields[index];
		JPanel panel = new JPanel();
		panel.setBounds(600,400,100,100);
		label.setBounds(600,400,100,100);
		label.setVisible(true);
	}


	public boolean allFieldsChecked(){
		boolean passed = true;
		String [] fieldValues = this.getFieldValues();
		JTextField [] fields = this.getFields();
		String [] regexPatterns = this.regex();
		String [] errorMessages = this.errorMessages();

		if (!Utils.isValidName(fieldValues[0])) {
			passed = false;
			this.showErrorMessage(fields,errorMessages,0);
			System.out.println(fieldValues[0]+ " es invalido");
		}

		if (!Utils.isCellphone(fieldValues[1])) {
			passed = false;
			this.showErrorMessage(fields,errorMessages,1);
			System.out.println(fieldValues[1]+ " es invalido");
		}
		if (!Utils.isValidName(fieldValues[2])) {
			passed = false;
			this.showErrorMessage(fields,errorMessages,2);
			System.out.println(fieldValues[2]+ " es invalido");
		}
		if (!Utils.isNumber(fieldValues[3])) {
			passed = false;
			this.showErrorMessage(fields,errorMessages,3);
			System.out.println(fieldValues[3]+ " es invalido");
		}
		if (!Utils.isNumber(fieldValues[4])) {
			passed = false;
			this.showErrorMessage(fields,errorMessages,4);
			System.out.println(fieldValues[4]+ " es invalido");
		}

		if (!Utils.isNumber(fieldValues[5])) {
			passed = false;
			this.showErrorMessage(fields,errorMessages,5);
			System.out.println(fieldValues[5]+ " es invalido");
		}

		if (!Utils.isEmail(fieldValues[6])) {
			passed = false;
			this.showErrorMessage(fields,errorMessages,6);
			System.out.println(fieldValues[6]+ " es invalido");
		}

		return passed;

	}


}
