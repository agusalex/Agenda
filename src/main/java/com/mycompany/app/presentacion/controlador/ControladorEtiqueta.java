package com.mycompany.app.presentacion.controlador;

import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.modelo.ABMEtiquetas;

import com.mycompany.app.presentacion.vista.VentanaEtiqueta;

import com.mycompany.app.presentacion.vista.VistaEtiqueta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ControladorEtiqueta implements ActionListener
{
	

	public List<EtiquetaDTO> getEtiquetas_en_tabla() {
		return Etiquetas_en_tabla;
	}

	public VentanaEtiqueta getVentanaEtiqueta() {
		return ventanaEtiqueta;
	}

	public ABMEtiquetas getABMEtiquetas() {
		return ABMEtiquetas;
	}

	private VistaEtiqueta vista;
		private List<EtiquetaDTO> Etiquetas_en_tabla;

	public VistaEtiqueta getVista() {
		return vista;
	}

	private VentanaEtiqueta ventanaEtiqueta;
		private ABMEtiquetas ABMEtiquetas;
		private EtiquetaDTO BKP;
		
		public ControladorEtiqueta(VistaEtiqueta vista, ABMEtiquetas ABMEtiquetas)
		{
			this.vista = vista;
			this.vista.getBtnAgregarEtiqueta().addActionListener(this);
			this.vista.getBtnBorrar().addActionListener(this);
			this.vista.getBtnEditar().addActionListener(this);

			this.ABMEtiquetas = ABMEtiquetas;
			this.Etiquetas_en_tabla = null;
		}


	public void inicializar()
		{
			this.llenarTabla();
		}
		
		private void llenarTabla()
		{
			this.vista.getModelEtiquetas().setRowCount(0); //Para vaciar la tabla
			this.vista.getModelEtiquetas().setColumnCount(0);
			this.vista.getModelEtiquetas().setColumnIdentifiers(this.vista.getNombreColumnas());
			
			this.Etiquetas_en_tabla = ABMEtiquetas.obtenerEtiquetas();
			for (int i = 0; i < this.Etiquetas_en_tabla.size(); i ++)
			{
				Object[] fila = {this.Etiquetas_en_tabla.get(i).getIdEtiqueta(),this.Etiquetas_en_tabla.get(i).getNombre()};
				this.vista.getModelEtiquetas().addRow(fila);
			}
			this.vista.show();
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == this.vista.getBtnAgregarEtiqueta())
			{
				this.ventanaEtiqueta = new VentanaEtiqueta(this);
			}

			else if(e.getSource() == this.vista.getBtnEditar())
			{
				int[] filas_seleccionadas = this.vista.getTablaEtiquetas().getSelectedRows();
				for (int fila:filas_seleccionadas)
				{
					BKP=this.Etiquetas_en_tabla.get(fila);

					this.ventanaEtiqueta = new VentanaEtiqueta(this,this.Etiquetas_en_tabla.get(fila));
				}


			}
			else if(e.getSource() == this.vista.getBtnBorrar())
			{
				int[] filas_seleccionadas = this.vista.getTablaEtiquetas().getSelectedRows();
				for (int fila:filas_seleccionadas)
				{

					this.ABMEtiquetas.borrarEtiqueta(this.Etiquetas_en_tabla.get(fila));

				}
				
				this.llenarTabla();
				
			}



			else if(e.getSource() == this.ventanaEtiqueta.getBtnAgregarEtiqueta())
			{
				EtiquetaDTO Etiqueta = new EtiquetaDTO(0,this.ventanaEtiqueta.getTxtNombre().getText());



				this.ABMEtiquetas.borrarEtiqueta(BKP);
				this.ABMEtiquetas.agregarEtiqueta(Etiqueta);
				this.llenarTabla();
				this.ventanaEtiqueta.dispose();
			}

			else if(e.getSource() == this.ventanaEtiqueta.getBtnGuardarEtiqueta())
			{



				this.ABMEtiquetas.borrarEtiqueta(BKP);
				this.ABMEtiquetas.agregarEtiqueta(new EtiquetaDTO(BKP.getIdEtiqueta(),ventanaEtiqueta.getTxtNombre().getText()));
				this.llenarTabla();
				this.ventanaEtiqueta.dispose();
			}

		}





}
