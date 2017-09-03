package com.mycompany.app.presentacion.vista;


import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.negocio.Utils;
import com.mycompany.app.presentacion.controlador.ControladorLocalidad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class VentanaLocalidad extends JFrame
{
	private static final long serialVersionUID = 1L;
	private  JButton btnGuardarLocalidad;
	private JPanel contentPane;
	private JTextField txtNombre;


    public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public JPanel getContentPane() {
		return contentPane;
	}

	public ControladorLocalidad getControlador() {
		return controlador;
	}

	private JButton btnAgregarLocalidad;
	private ControladorLocalidad controlador;
	private  JButton btnCerrar;
	public JButton getBtnCerrar() {
		return btnCerrar;
	}

	public VentanaLocalidad(ControladorLocalidad controlador, LocalidadDTO localidadDTO) {

		super();

		this.controlador = controlador;

		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 343, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 123);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 11, 113, 14);
		panel.add(lblLocalidad);


		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		txtNombre.setText(localidadDTO.getNombre());
		panel.add(txtNombre);
		txtNombre.setColumns(10);


		btnGuardarLocalidad = new JButton("Guardar");
		btnGuardarLocalidad.addActionListener(controlador);
		btnGuardarLocalidad.setBounds(100, 92, 89, 23);
		panel.add(btnGuardarLocalidad);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this.controlador);
		btnCerrar.setBounds(208, 92, 89, 23);
		panel.add(btnCerrar);



		this.setVisible(true);
		try{ImageIcon img = new ImageIcon("addpicon.png");
			this.setIconImage(img.getImage());}
		catch (Exception e){
			System.out.println("Error al cargar el icono:"+e.toString());
		}


		
	}
	
	
	public VentanaLocalidad(ControladorLocalidad controlador)
	{
		super();
		this.controlador = controlador;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setBounds(100, 100, 343, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 123);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 11, 113, 14);
		panel.add(lblLocalidad);


		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);


		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.addActionListener(this.controlador);
		btnAgregarLocalidad.setBounds(100, 92, 89, 23);
		panel.add(btnAgregarLocalidad);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this.controlador);
		btnCerrar.setBounds(208, 92, 89, 23);
		panel.add(btnCerrar);


		this.setVisible(true);
		try{ImageIcon img = new ImageIcon("addcicon.png");
			this.setIconImage(img.getImage());}
		catch (Exception e){
			System.out.println("Error al cargar el icono:"+e.toString());
		}


		this.setVisible(true);

	}

	public JTextField getTxtNombre()
	{
		return txtNombre;
	}



	public JButton getBtnAgregarLocalidad()
	{
		return btnAgregarLocalidad;
	}

	public JButton getBtnGuardarLocalidad() {
		return btnGuardarLocalidad;
	}


	private void showErrorMessage(){
		this.txtNombre.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
	}

	private void restoreFieldsColor(){
		this.txtNombre.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
	}


	public boolean checkNameField(){
		this.restoreFieldsColor();
		if(this.txtNombre.getText().equals("")) {
			this.showErrorMessage();
			return false;
		}
		else{
			if(txtNombre.getText().length()>44) {
				this.showErrorMessage();
				return false;
			}
		}
		return true;
	}

}

