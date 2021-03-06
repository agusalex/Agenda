package com.mycompany.app.presentacion.controlador;

import com.google.zxing.client.result.VEventResultParser;
import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.modelo.ABMEtiquetas;

import com.mycompany.app.presentacion.vista.VentanaEtiqueta;

import com.mycompany.app.presentacion.vista.VistaEtiqueta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

	private static VentanaEtiqueta ventanaEtiqueta;
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
				Object[] fila = {this.Etiquetas_en_tabla.get(i).getNombre()};
				this.vista.getModelEtiquetas().addRow(fila);
			}
			this.vista.show();
		}
		
		public void actionPerformed(ActionEvent e) 
		{


			if(this.ventanaEtiqueta==null) {

				if (e.getSource() == this.vista.getBtnAgregarEtiqueta()) {
						ventanaEtiqueta = new VentanaEtiqueta(this);
						ventanaEtiqueta.addWindowListener(new WindowAdapter(){
							public void windowClosing(WindowEvent e) {
								vista.enable();
								ventanaEtiqueta.dispose();
								ventanaEtiqueta = null;
							}
						});
						this.vista.disable();
					
				}
				else if (e.getSource() == this.vista.getBtnEditar()) {
					int[] filas_seleccionadas = this.vista.getTablaEtiquetas().getSelectedRows();
					if (filas_seleccionadas.length > 0) {
						BKP = this.Etiquetas_en_tabla.get(filas_seleccionadas[0]);
						
							ventanaEtiqueta = new VentanaEtiqueta(this, this.Etiquetas_en_tabla.get(filas_seleccionadas[0]));
							ventanaEtiqueta.addWindowListener(new WindowAdapter(){
								public void windowClosing(WindowEvent e) {
									vista.enable();
									ventanaEtiqueta.dispose();
									ventanaEtiqueta = null;
				
								}
							});
							this.vista.disable();
						
					}

				} else if (e.getSource() == this.vista.getBtnBorrar()) {
					int[] filas_seleccionadas = this.vista.getTablaEtiquetas().getSelectedRows();
					for (int fila : filas_seleccionadas) {

						boolean borrar = this.ABMEtiquetas.borrarEtiqueta(this.Etiquetas_en_tabla.get(fila));
						if (!borrar)
							this.vista.showError("Error al borrar la Etiqueta!\n Es probable que este en uso...");
					}
					this.llenarTabla();
				}
			}


			else if (e.getSource() == this.ventanaEtiqueta.getBtnCerrar()){
				this.vista.enable();
				ventanaEtiqueta.dispose();
				ventanaEtiqueta=null;
			}



			else if(e.getSource() == this.ventanaEtiqueta.getBtnAgregarEtiqueta())
			{
				if(this.ventanaEtiqueta.checkNameField()) {
					EtiquetaDTO Etiqueta = new EtiquetaDTO(0, this.ventanaEtiqueta.getTxtNombre().getText());

					boolean agregar=this.ABMEtiquetas.agregarEtiqueta(Etiqueta);
					if(!agregar)
						this.vista.showError("Error al agregar la etiqueta!");
					this.vista.enable();
					this.llenarTabla();
					ventanaEtiqueta.dispose();
					ventanaEtiqueta=null;
				}

			}

			else if(e.getSource() == this.ventanaEtiqueta.getBtnGuardarEtiqueta())
			{

				boolean editar=this.ABMEtiquetas.editarEtiqueta(new EtiquetaDTO(BKP.getIdEtiqueta(), ventanaEtiqueta.getTxtNombre().getText()));
				if(!editar)
					this.vista.showError("Error al editar la etiqueta!");


				if(this.ventanaEtiqueta.checkNameField()) {
					this.ABMEtiquetas.editarEtiqueta(new EtiquetaDTO(BKP.getIdEtiqueta(), ventanaEtiqueta.getTxtNombre().getText()));
					BKP = null;
					this.vista.enable();
					this.llenarTabla();
					ventanaEtiqueta.dispose();
					ventanaEtiqueta=null;
				}
			}

		}





}
