package com.mycompany.app.presentacion.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VistaLocalidad
{
	private JFrame frame;
	private JTable tablaLocalidades;
	private JButton btnAgregarLocalidad;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelLocalidades;
	private  String[] nombreColumnas = {"id","Nombre"};

	public VistaLocalidad() 
	{
		super();
		initialize();
	}


	private void initialize() 
	{
		frame = new JFrame("Localidades");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spLocalidades = new JScrollPane();
		spLocalidades.setBounds(10, 11, 414, 182);
		panel.add(spLocalidades);
		
		modelLocalidades = new DefaultTableModel(null,nombreColumnas);
		tablaLocalidades = new JTable(modelLocalidades);
		
		tablaLocalidades.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaLocalidades.getColumnModel().getColumn(0).setResizable(false);
		tablaLocalidades.getColumnModel().getColumn(1).setPreferredWidth(103);
		tablaLocalidades.getColumnModel().getColumn(1).setResizable(false);

		
		spLocalidades.setViewportView(tablaLocalidades);
		
		btnAgregarLocalidad = new JButton("Agregar");
		btnAgregarLocalidad.setBounds(10, 228, 89, 23);
		panel.add(btnAgregarLocalidad);
		
		 btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);

	}
	
	public void show()
	{
		this.frame.setVisible(true);
	}
	
	public JButton getBtnAgregarLocalidad()
	{
		return btnAgregarLocalidad;
	}

	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public DefaultTableModel getModelLocalidades()
	{
		return modelLocalidades;
	}
	
	public JTable getTablaLocalidades()
	{
		return tablaLocalidades;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}


}
