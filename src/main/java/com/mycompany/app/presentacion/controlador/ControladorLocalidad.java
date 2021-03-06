package com.mycompany.app.presentacion.controlador;

import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.dto.PersonaDTO;
import com.mycompany.app.modelo.ABMLocalidades;
import com.mycompany.app.presentacion.vista.VentanaLocalidad;
import com.mycompany.app.presentacion.vista.VistaLocalidad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;


public class ControladorLocalidad implements ActionListener
{

	
	private LocalidadDTO BKP;

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

	private static VentanaLocalidad ventanaLocalidad;
		private ABMLocalidades ABMLocalidades;
		
		public ControladorLocalidad(VistaLocalidad vista, ABMLocalidades ABMLocalidades)
		{
			this.vista = vista;
			this.vista.getBtnAgregarLocalidad().addActionListener(this);
			this.vista.getBtnBorrar().addActionListener(this);
			this.vista.getBtnEditar().addActionListener(this);


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
				Object[] fila = {this.Localidades_en_tabla.get(i).getNombre()};
				this.vista.getModelLocalidades().addRow(fila);
			}
			this.vista.show();
		}
		
		public void actionPerformed(ActionEvent e) 
		{
			if(this.ventanaLocalidad==null){
					if(e.getSource() == this.vista.getBtnAgregarLocalidad())
					{
						

							ventanaLocalidad = new VentanaLocalidad(this);
							ventanaLocalidad.addWindowListener(new WindowAdapter(){
								public void windowClosing(WindowEvent e) {
									vista.enable();
									ventanaLocalidad.dispose();
									ventanaLocalidad = null;
									
								}
							});


							this.vista.disable();
						
					}


					else if(e.getSource() == this.vista.getBtnEditar())
					{


						int[] filas_seleccionadas = this.vista.getTablaLocalidades().getSelectedRows();
							if(filas_seleccionadas.length>0) {
								BKP = this.Localidades_en_tabla.get(filas_seleccionadas[0]);
								
									ventanaLocalidad = new VentanaLocalidad(this, this.Localidades_en_tabla.get(filas_seleccionadas[0]));
									ventanaLocalidad.addWindowListener(new WindowAdapter(){
										public void windowClosing(WindowEvent e) {
											vista.enable();
											ventanaLocalidad.dispose();
											ventanaLocalidad = null;
											
										}
									});
									this.vista.disable();
								
							}
					}


					else if(e.getSource() == this.vista.getBtnBorrar())
					{
						int[] filas_seleccionadas = this.vista.getTablaLocalidades().getSelectedRows();
						for (int fila:filas_seleccionadas)
						{
							boolean borrar=this.ABMLocalidades.borrarLocalidad(this.Localidades_en_tabla.get(fila));
							if(!borrar)
									this.vista.showError("Error al borrar la Localidad!\n Es probable que este en uso...");
						}


						this.llenarTabla();

					}
			}
			//VENTANA LOCALIDAD


				else if (e.getSource() == this.ventanaLocalidad.getBtnCerrar()) {
					this.vista.enable();
					this.refresh();
					ventanaLocalidad.dispose();
					ventanaLocalidad=null;
				


				} else if (e.getSource() == this.ventanaLocalidad.getBtnAgregarLocalidad()) {

					if (this.ventanaLocalidad.checkNameField()) {
						LocalidadDTO Localidad = new LocalidadDTO(0, this.ventanaLocalidad.getTxtNombre().getText());
						boolean agregar = this.ABMLocalidades.agregarLocalidad(Localidad);
						if (!agregar)
							this.vista.showError("Error al agregar la Localidad!");
						this.vista.enable();
						this.llenarTabla();
						ventanaLocalidad.dispose();
						ventanaLocalidad=null;
						
					}
				} else if (e.getSource() == this.ventanaLocalidad.getBtnGuardarLocalidad())

				{

					if (this.ventanaLocalidad.checkNameField()) {

						boolean editar = this.ABMLocalidades.editarLocalidad(new LocalidadDTO(BKP.getIdLocalidad(), ventanaLocalidad.getTxtNombre().getText()));
						if (!editar)
							this.vista.showError("Error al editar la Localidad!");
						BKP = null;
						this.vista.enable();
						this.llenarTabla();
						ventanaLocalidad.dispose();
						ventanaLocalidad=null;
						

					}
				}




		}
		public void refresh(){
			this.llenarTabla();
		}


}
