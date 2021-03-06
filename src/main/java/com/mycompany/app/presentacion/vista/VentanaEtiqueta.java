package com.mycompany.app.presentacion.vista;


import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.negocio.Utils;
import com.mycompany.app.presentacion.controlador.ControladorEtiqueta;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;


public class VentanaEtiqueta extends JFrame
{
	private static final long serialVersionUID = 1L;
	private  JButton btnGuardarEtiqueta;
	private JPanel contentPane;
	private JTextField txtNombre;

	private JButton btnAgregarEtiqueta;
	private ControladorEtiqueta controlador;
	private  JButton btnCerrar;
	public JButton getBtnCerrar() {
		return btnCerrar;
	}



	public VentanaEtiqueta(ControladorEtiqueta controlador)
	{
		super();
		this.controlador = controlador;
		setResizable(false);
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

		JLabel lbletiqueta = new JLabel("Etiqueta:");
		lbletiqueta.setBounds(10, 11, 113, 14);
		panel.add(lbletiqueta);


		txtNombre = new JTextField();
		txtNombre.setBounds(133, 8, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);


		btnAgregarEtiqueta = new JButton("Agregar");
		btnAgregarEtiqueta.addActionListener(this.controlador);
		btnAgregarEtiqueta.setBounds(100, 92, 89, 23);
		panel.add(btnAgregarEtiqueta);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this.controlador);
		btnCerrar.setBounds(208, 92, 89, 23);
		panel.add(btnCerrar);




		this.setVisible(true);
		try{ImageIcon img = new ImageIcon("img"+File.separator+"addticon.png");
			this.setIconImage(img.getImage());}
		catch (Exception e){
			System.out.println("Error al cargar el icono:"+e.toString());
		}
		this.setDefaultCloseOperation(0);
	}

	public VentanaEtiqueta(ControladorEtiqueta controlador, EtiquetaDTO etiquetaDTO) {
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
		txtNombre.setText(etiquetaDTO.getNombre());
		panel.add(txtNombre);
		txtNombre.setColumns(10);


		btnGuardarEtiqueta = new JButton("Guardar");
		btnGuardarEtiqueta.addActionListener(this.controlador);
		btnGuardarEtiqueta.setBounds(100, 92, 89, 23);
		panel.add(btnGuardarEtiqueta);

		btnCerrar = new JButton("Cerrar");
		btnCerrar.addActionListener(this.controlador);
		btnCerrar.setBounds(208, 92, 89, 23);
		panel.add(btnCerrar);

		this.setVisible(true);
		try{ImageIcon img = new ImageIcon("img"+ File.separator+"addpicon.png");
			this.setIconImage(img.getImage());}
		catch (Exception e){
			System.out.println("Error al cargar el icono:"+e.toString());
		}

		this.setDefaultCloseOperation(0);
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
			if(txtNombre.getText().length()>14) {
				this.showErrorMessage();
				return false;
			}
		}
		return true;
	}


	public JTextField getTxtNombre()
	{
		return txtNombre;
	}

	public JButton getBtnAgregarEtiqueta()
	{
		return btnAgregarEtiqueta;
	}

	public JButton getBtnGuardarEtiqueta() {
		return btnGuardarEtiqueta;
	}
}

