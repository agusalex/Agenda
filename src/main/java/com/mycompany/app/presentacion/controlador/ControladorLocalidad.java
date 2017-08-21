package com.mycompany.app.presentacion.controlador;

import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.modelo.ABMLocalidades;
import com.mycompany.app.presentacion.vista.VentanaLocalidad;
import com.mycompany.app.presentacion.vista.VistaLocalidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class ControladorLocalidad implements ActionListener
{
	

	public List<LocalidadDTO> getLocalidades_en_tabla() {
		return Localidades_en_tabla;
	}

	public VentanaLocalidad getVentanaLocalidad() {
		return ventanaLocalidad;
	}

	public ABMLocalidades getABMLocalidades() {
		return ABMLocalidades;
	}

	private VistaLocalidad vista;
		private List<LocalidadDTO> Localidades_en_tabla;

	public VistaLocalidad getVista() {
		return vista;
	}

	private VentanaLocalidad ventanaLocalidad;
		private ABMLocalidades ABMLocalidades;
		
		public ControladorLocalidad(VistaLocalidad vista, ABMLocalidades ABMLocalidades)
		{
			this.vista = vista;
			this.vista.getBtnAgregarLocalidad().addActionListener(this);
			this.vista.getBtnBorrar().addActionListener(this);

			this.ABMLocalidades = ABMLocalidades;
			this.Localidades_en_tabla = null;
		}


	public void inicializar()
		{
			this.llenarTabla();
		}
		
		private void llenarTabla()
		{
			this.vista.getModelLocalidades().setRowCount(0); //Para vaciar la tabla
			this.vista.getModelLocalidades().setColumnCount(0);
			this.vista.getModelLocalidades().setColumnIdentifiers(this.vista.getNombreColumnas());
			
			this.Localidades_en_tabla = ABMLocalidades.obtenerLocalidades();
			for (int i = 0; i < this.Localidades_en_tabla.size(); i ++)
			{
				Object[] fila = {this.Localidades_en_tabla.get(i).getIdLocalidad(),this.Localidades_en_tabla.get(i).getNombre()};
				this.vista.getModelLocalidades().addRow(fila);
			}
			this.vista.show();
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == this.vista.getBtnAgregarLocalidad())
			{
				this.ventanaLocalidad = new VentanaLocalidad(this);
			}

			else if(e.getSource() == this.vista.getBtnEditar())
			{

				this.ventanaLocalidad = new VentanaLocalidad(this);



				int[] filas_seleccionadas = this.vista.getTablaLocalidades().getSelectedRows();
				for (int fila:filas_seleccionadas)
				{
					this.ABMLocalidades.editarLocalidad(this.Localidades_en_tabla.get(fila));
				}

				this.llenarTabla();

			}

			else if(e.getSource() == this.vista.getBtnBorrar())
			{
				int[] filas_seleccionadas = this.vista.getTablaLocalidades().getSelectedRows();
				for (int fila:filas_seleccionadas)
				{
					this.ABMLocalidades.borrarLocalidad(this.Localidades_en_tabla.get(fila));
				}
				
				this.llenarTabla();
				
			}

			else if(e.getSource() == this.ventanaLocalidad.getBtnAgregarLocalidad())
			{
				LocalidadDTO Localidad = new LocalidadDTO(0,this.ventanaLocalidad.getTxtNombre().getText());


				//FIXME ARREGLAR COMO HACER PARA CARGAR Localidad

				this.ABMLocalidades.agregarLocalidad(Localidad);
				this.llenarTabla();
				this.ventanaLocalidad.dispose();
			}
		}



}
