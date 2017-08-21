package com.mycompany.app.presentacion.vista;


import com.mycompany.app.presentacion.controlador.ControladorEtiqueta;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class VentanaEtiqueta extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;

	private JButton btnAgregarEtiqueta;
	private ControladorEtiqueta controlador;

	public VentanaEtiqueta(ControladorEtiqueta controlador)
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

		JLabel lbletiqueta = new JLabel("etiqueta");
		lbletiqueta.setBounds(10, 11, 113, 14);
		panel.add(lbletiqueta);


		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);


		btnAgregarEtiqueta = new JButton("Agregar");
		btnAgregarEtiqueta.addActionListener(this.controlador);
		btnAgregarEtiqueta.setBounds(208, 92, 89, 23);
		panel.add(btnAgregarEtiqueta);

		this.setVisible(true);
	}

	public JTextField getTxtNombre()
	{
		return txtNombre;
	}



	public JButton getBtnAgregarEtiqueta()
	{
		return btnAgregarEtiqueta;
	}

}

