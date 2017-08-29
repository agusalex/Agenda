package com.mycompany.app.presentacion.vista;


import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.dto.PersonaDTO;
import com.mycompany.app.presentacion.controlador.Controlador;
import com.mycompany.app.negocio.Utils;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDayChooser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


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


	public java.util.Date fromString(String dateInString){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");


		try {

			Date date = formatter.parse(dateInString);
			System.out.println(date);
			System.out.println(formatter.format(date));
			return date;


		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
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
		txtAltura.setText(personaDTO.getAltura());
		panel.add(txtAltura);
		txtAltura.setColumns(10);


		txtPiso = new JTextField();
		txtPiso.setBounds(133, base+200, 250, 20);
		txtPiso.setText(personaDTO.getPiso());
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


			calendario.setDate(fromString(personaDTO.getFechaNacimmiento()));

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
		Localidad.addItem("Sin Localidad");
		for(LocalidadDTO loc:controlador.cargarLocalidades())
			if(loc!=null)
				Localidad.addItem(loc.getNombre());
	}


	public void cargarEtiquetas(){
		Etiqueta.addItem("Sin Etiqueta");
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

	private void setRedBorder(JTextField[] fields, String[] errorMessages, int index){
		JTextField field = fields[index];
		JPanel panel = new JPanel();
		field.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
	}

	private void restoreFieldsColor(){
		JTextField [] fields = this.getFields();
		for(JTextField field : fields){
			field.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
		}
	}


	public boolean allFieldsChecked(){
		this.restoreFieldsColor();
		boolean passed = true;
		String [] fieldValues = this.getFieldValues();
		JTextField [] fields = this.getFields();
		String [] errorMessages = this.errorMessages();

		if(!(fieldValues[0].equals("")) && !(fieldValues[1].length()>44)) {
			passed = false;
			this.setRedBorder(fields, errorMessages, 0);
			System.out.println("nombre no puede ser vacio");

		}

		if (!Utils.matchesRegex(Utils.REGEX_CELL_PHONE,fieldValues[1]) && !fieldValues[1].equals("") && !(fieldValues[1].length()>44) ) {
			passed = false;
			this.setRedBorder(fields,errorMessages,1);
			System.out.println(fieldValues[1]+ " es invalido");
		}
		if (!Utils.matchesRegex(Utils.REGEX_ADRESS_NAME,fieldValues[2]) && !fieldValues[2].equals("") && !(fieldValues[2].length()>44)) {
			passed = false;
			this.setRedBorder(fields,errorMessages,2);
			System.out.println(fieldValues[2]+ " es invalido");
		}
		if (!Utils.matchesRegex(Utils.REGEX_ADRESS_NUMBER,fieldValues[3]) && !fieldValues[3].equals("") && !(fieldValues[3].length()>44)) {
			passed = false;
			this.setRedBorder(fields,errorMessages,3);
			System.out.println(fieldValues[3]+ " es invalido");
		}



		if (!Utils.matchesRegex(Utils.REGEX_EMAIL,fieldValues[6]) && !fieldValues[6].equals("") && !(fieldValues[4].length()>44)) {
			passed = false;
			this.setRedBorder(fields,errorMessages,6);
			System.out.println(fieldValues[6]+ " es invalido");
		}


	    showError("Campos Invalidos");

		return passed;

	}
	public void showError(String msj){
		JOptionPane.showMessageDialog(contentPane,msj);
	}

}
