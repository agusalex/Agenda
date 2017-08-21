package com.mycompany.app.presentacion.vista;


import com.mycompany.app.presentacion.controlador.ControladorLocalidad;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class VentanaLocalidad extends JFrame
{
	private static final long serialVersionUID = 1L;
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
		btnAgregarLocalidad.setBounds(208, 92, 89, 23);
		panel.add(btnAgregarLocalidad);

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

}

