package com.mycompany.app.presentacion.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

public class VistaLocalidad
{
	private JFrame frame;
	private JTable tablaLocalidades;
	private JButton btnAgregarLocalidad;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelLocalidades;
	private  String[] nombreColumnas = {"Nombre"};
	private JPanel panel;

	public static VistaLocalidad getVistaLocalidad() {

		if(vistaLocalidad==null)
			vistaLocalidad=new VistaLocalidad();
		return vistaLocalidad;
	}

	private static VistaLocalidad vistaLocalidad;

	private VistaLocalidad()
	{
		super();
		initialize();
	}


	private void initialize() 
	{
		frame = new JFrame("Localidades");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		
		panel = new JPanel();
		panel.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spLocalidades = new JScrollPane();
		spLocalidades.setBounds(10, 11, 414, 182);
		panel.add(spLocalidades);

		modelLocalidades = new DefaultTableModel(null,nombreColumnas){
			public boolean isCellEditable(int row, int column)
			{
				return false;//This causes all cells to be not editable
			}
		};
		tablaLocalidades = new JTable(modelLocalidades);


		
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


		try{ImageIcon img = new ImageIcon("img"+File.separator+"addcicon.png");
			frame.setIconImage(img.getImage());}
		catch (Exception e){
			System.out.println("Error al cargar el icono:"+e.toString());
		}

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

	public void showError(String msj){
		JOptionPane.showMessageDialog(frame,msj);
	}

    public void enable() {
		frame.setEnabled(true);
    }
    public void disable(){
		frame.setEnabled(false);
	}

	public JFrame getFrame(){ return this.frame;}
}
