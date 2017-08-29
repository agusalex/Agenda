package com.mycompany.app.presentacion.vista;


import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.negocio.Utils;
import com.mycompany.app.presentacion.controlador.ControladorEtiqueta;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class VentanaEtiqueta extends JFrame
{
	private static final long serialVersionUID = 1L;
	private  JButton btnGuardarEtiqueta;
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
		btnGuardarEtiqueta.setBounds(208, 92, 89, 23);
		panel.add(btnGuardarEtiqueta);

		this.setVisible(true);
	}

	private void showErrorMessage(){
		this.txtNombre.setBorder(BorderFactory.createLineBorder(Color.decode("#FF0000")));
	}

	private void restoreFieldsColor(){
		this.txtNombre.setBorder(BorderFactory.createLineBorder(Color.decode("#000000")));
	}

	public boolean checkNameField(){
		this.restoreFieldsColor();
		if(this.txtNombre.equals("")) {
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

