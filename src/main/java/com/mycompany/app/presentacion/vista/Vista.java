package com.mycompany.app.presentacion.vista;

import com.mycompany.app.modelo.ABMEtiquetas;
import com.mycompany.app.presentacion.controlador.ControladorEtiqueta;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.EventObject;
import java.util.Iterator;

public class Vista
{
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JButton btnReporte;
	private JButton btnReporteMail;
	private JButton btnEtiquetas;
	private JButton btnLocalidades;
	private static final int WIDTH=1000;
	private static final int HEIGHT=600;
	private JPanel panel;
	private DefaultTableModel modelPersonas;
	private  String[] nombreColumnas = {"Nombre y apellido","Teléfono","Calle", "Altura","Piso","Dpto.","Localidad","Etiqueta", "Mail"};

	public Vista() 
	{
		super();
		initialize();
	}


	public JButton getBtnEditar() {
		return btnEditar;
	}

	private void initialize()
	{
		frame = new JFrame("Agenda");
		frame.setBounds(100, 100, WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		panel = new JPanel();
		panel.setBounds(0, 0, WIDTH, HEIGHT);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11,  WIDTH-25, HEIGHT-75);
		panel.add(spPersonas);
		
		modelPersonas = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas = new JTable(modelPersonas);

		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);

		spPersonas.setViewportView(tablaPersonas);



		btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(10, HEIGHT-60, 89, 23);
		panel.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		btnEditar.setBounds(109 , HEIGHT-60, 89, 23);
		panel.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		btnBorrar.setBounds(208, HEIGHT-60, 89, 23);
		panel.add(btnBorrar);
		

		btnEtiquetas = new JButton("Etiquetas");
		btnEtiquetas.setBounds(307, HEIGHT-60, 89, 23);
		panel.add(btnEtiquetas);

		btnLocalidades = new JButton("Localidades");
		btnLocalidades.setBounds(409, HEIGHT-60, 105, 23);
		panel.add(btnLocalidades);

		btnReporte = new JButton("Reporte");
		btnReporte.setBounds(550, HEIGHT-60, 92, 23);
		panel.add(btnReporte);


		btnReporteMail = new JButton("Reporte Mail");
		btnReporteMail.setBounds(625, HEIGHT-60, 125, 23);
		panel.add(btnReporteMail);

	}


	public void bloquearBotonesEdicion(){
		btnEditar.setEnabled(false);
		btnAgregar.setEnabled(false);
		btnBorrar.setEnabled(false);
	}
	public void desbloquearBotonesEdicion(){
		btnEditar.setEnabled(true);
		btnAgregar.setEnabled(true);
		btnBorrar.setEnabled(true);
	}

	public void showError(String msj){
		JOptionPane.showMessageDialog(panel,msj);
	}


	public void show()
	{
		this.frame.setVisible(true);
	}
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}

	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
	
	public JButton getBtnReporte() 
	{
		return btnReporte;
	}
	
	public DefaultTableModel getModelPersonas() 
	{
		return modelPersonas;
	}
	
	public JTable getTablaPersonas()
	{
		return tablaPersonas;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}

	public JButton getBtnEtiquetas() { return btnEtiquetas; }

	public JButton getBtnLocalidades() { return btnLocalidades; }

	public JButton getBtnReporteMail() { return btnReporteMail; }

}
