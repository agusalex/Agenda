package com.mycompany.app.presentacion.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VistaEtiqueta
{
	private JFrame frame;
	private JTable tablaEtiquetas;
	private JButton btnAgregarEtiqueta;
	private JButton btnBorrar;
	private JButton btnEditar;
	private DefaultTableModel modelEtiquetas;
	private  String[] nombreColumnas = {"Nombre"};
	private static VistaEtiqueta vistaEtiqueta;

	private VistaEtiqueta()
	{
		super();
		initialize();
	}

	public static VistaEtiqueta getVistaEtiqueta() {
		if(vistaEtiqueta==null)
			vistaEtiqueta = new VistaEtiqueta();

		return vistaEtiqueta;

	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	private void initialize()
	{
		frame = new JFrame("Etiquetas");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 262);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spEtiquetas = new JScrollPane();
		spEtiquetas.setBounds(10, 11, 414, 182);
		panel.add(spEtiquetas);

		modelEtiquetas = new DefaultTableModel(null,nombreColumnas){
			public boolean isCellEditable(int row, int column)
			{
				return false;//This causes all cells to be not editable
			}
		};
		tablaEtiquetas = new JTable(modelEtiquetas);
		


		
		spEtiquetas.setViewportView(tablaEtiquetas);
		
		btnAgregarEtiqueta = new JButton("Agregar");
		btnAgregarEtiqueta.setBounds(10, 228, 89, 23);
		panel.add(btnAgregarEtiqueta);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109, 228, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, 228, 89, 23);
		panel.add(btnBorrar);

		try{ImageIcon img = new ImageIcon("ticon.png");
			frame.setIconImage(img.getImage());}
		catch (Exception e){
			System.out.println("Error al cargar el icono:"+e.toString());
		}

	}
	
	public void show()
	{
		this.frame.setVisible(true);
	}
	public void showError(String msj){
		JOptionPane.showMessageDialog(frame,msj);
	}


	public JButton getBtnAgregarEtiqueta()
	{
		return btnAgregarEtiqueta;
	}

	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}

	
	public DefaultTableModel getModelEtiquetas()
	{
		return modelEtiquetas;
	}
	
	public JTable getTablaEtiquetas()
	{
		return tablaEtiquetas;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}


	public void enable() {
		frame.setEnabled(true);
	}
	public void disable(){
		frame.setEnabled(false);
	}

	public JFrame getFrame(){ return this.frame;}
}
