package com.mycompany.app.presentacion.vista;


import com.mycompany.app.presentacion.controlador.Controlador;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class VentanaLocalidad extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtlocalidad;
	
	private JButton btnAgregarLocalidad;
	private Controlador controlador;

	public VentanaLocalidad(Controlador controlador)
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
		
		JLabel lbllocalidad = new JLabel("localidad");
		lbllocalidad.setBounds(10, 11, 113, 14);
		panel.add(lbllocalidad);
		
		
		txtlocalidad = new JTextField();
		txtlocalidad.setBounds(133, 8, 164, 20);
		panel.add(txtlocalidad);
		txtlocalidad.setColumns(10);
		
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.addActionListener(this.controlador);
		btnAgregarLocalidad.setBounds(208, 92, 89, 23);
		panel.add(btnAgregarLocalidad);
		
		this.setVisible(true);
	}
	
	public JTextField getTxtlocalidad() 
	{
		return txtlocalidad;
	}



	public JButton getBtnAgregarLocalidad() 
	{
		return btnAgregarLocalidad;
	}
	
}

