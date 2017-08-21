package com.mycompany.app.presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.dto.PersonaDTO;
import com.mycompany.app.modelo.ABMEtiquetas;
import com.mycompany.app.modelo.ABMLocalidades;
import com.mycompany.app.modelo.Agenda;
import com.mycompany.app.presentacion.reportes.ReporteAgenda;
import com.mycompany.app.presentacion.vista.VentanaPersona;
import com.mycompany.app.presentacion.vista.Vista;


public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personas_en_tabla;
		private VentanaPersona ventanaPersona;
		private Agenda agenda;
		private PersonaDTO BKP;




	public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(this);
			this.vista.getBtnBorrar().addActionListener(this);
			this.vista.getBtnEditar().addActionListener(this);
			this.vista.getBtnReporte().addActionListener(this);
			this.agenda = agenda;
			this.personas_en_tabla = null;
		}



	public ArrayList<LocalidadDTO> cargarLocalidades(){
		ABMLocalidades ABML=new ABMLocalidades();
		ArrayList<LocalidadDTO> listaLocalidades=(ArrayList)ABML.obtenerLocalidades();

		return  listaLocalidades;
	}


	
	
	public ArrayList<EtiquetaDTO> cargarEtiquetas() {
		ABMEtiquetas ABML=new ABMEtiquetas();
		ArrayList<EtiquetaDTO> listaEtiquetas=(ArrayList)ABML.obtenerEtiquetas();

		return listaEtiquetas;
	}
	public void inicializar()
		{
			this.llenarTabla();
		}
		
		private void llenarTabla()
		{
			this.vista.getModelPersonas().setRowCount(0); //Para vaciar la tabla
			this.vista.getModelPersonas().setColumnCount(0);
			this.vista.getModelPersonas().setColumnIdentifiers(this.vista.getNombreColumnas());
			
			this.personas_en_tabla = agenda.obtenerPersonas();
			for (int i = 0; i < this.personas_en_tabla.size(); i ++)
			{
				Object[] fila = {this.personas_en_tabla.get(i).getNombre(), this.personas_en_tabla.get(i).getTelefono()};
				this.vista.getModelPersonas().addRow(fila);
			}
			this.vista.show();
		}




	public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == this.vista.getBtnAgregar())
			{
				this.ventanaPersona = new VentanaPersona(this);


			}

			else if(e.getSource() == this.vista.getBtnEditar())
			{


				int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
				for (int fila:filas_seleccionadas)
				{
					BKP=this.personas_en_tabla.get(fila);
					this.ventanaPersona = new VentanaPersona(this,this.personas_en_tabla.get(fila));

				}



			}

			else if(e.getSource() == this.vista.getBtnBorrar())
			{
				int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
				for (int fila:filas_seleccionadas)
				{
					this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
				}
				
				this.llenarTabla();
				
			}
			else if(e.getSource() == this.vista.getBtnReporte())
			{				
				ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
				reporte.mostrar();				
			}
			else if(e.getSource() == this.ventanaPersona.getBtnAgregarPersona())
			{
				PersonaDTO persona = new PersonaDTO();
				PersonaDTO nuevaPersona = cargarDatosPersona(persona);
			//	 nuevaPersona = new PersonaDTO(0,this.ventanaPersona.getTxtNombre().getText(), ventanaPersona.getTxtTelefono().getText());
				this.agenda.agregarPersona(nuevaPersona);
				this.llenarTabla();
				this.ventanaPersona.dispose();

			}

			else if(e.getSource() == this.ventanaPersona.getBtnGuardarPersona())
			{
				PersonaDTO persona = new PersonaDTO();
				PersonaDTO nuevaPersona = cargarDatosPersona(persona);

				this.agenda.borrarPersona(BKP);
				//	 nuevaPersona = new PersonaDTO(0,this.ventanaPersona.getTxtNombre().getText(), ventanaPersona.getTxtTelefono().getText());
				nuevaPersona.setIdPersona(BKP.getIdPersona());
				this.agenda.agregarPersona(nuevaPersona);
				this.llenarTabla();
				this.ventanaPersona.dispose();

			}


		}


		public LocalidadDTO deStringaLocalidadDTO(String localidad){

			ArrayList<LocalidadDTO> localidadDTOS=cargarLocalidades();
			for (LocalidadDTO loc: localidadDTOS
				 ) {
				if (loc.getNombre().equals(localidad)){
					return loc;
				}


			}
			return null;

		}
	public EtiquetaDTO deStringaEtiquetaDTO(String etiqueta){

		ArrayList<EtiquetaDTO> etiquetasDTOS=cargarEtiquetas();
		for (EtiquetaDTO et: etiquetasDTOS
				) {

			if (et.getNombre().equals(etiqueta)){
				System.out.println("SI");
				return et;
			}
			System.out.println("NO");


		}
		return null;

	}


		private PersonaDTO cargarDatosPersona(PersonaDTO persona){
			PersonaDTO nuevaPersona = new PersonaDTO();
			nuevaPersona.setNombre(ventanaPersona.getTxtNombre().getText());
			nuevaPersona.setTelefono(ventanaPersona.getTxtTelefono().getText());

			nuevaPersona.setCalle(ventanaPersona.getTxtCalle().getText());

	
			nuevaPersona.setAltura(Integer.valueOf(ventanaPersona.getTxtAltura().getText()));
			nuevaPersona.setPiso(Integer.valueOf(ventanaPersona.getTxtPiso().getText()));
			nuevaPersona.setDepartamento(ventanaPersona.getTxtDepartamento().getText());
			nuevaPersona.setEmail(ventanaPersona.getTxtEmail().getText());
			nuevaPersona.setFechaNacimmiento(ventanaPersona.getCalendario().getDate());



			String Localidad= (String)ventanaPersona.getLocalidad().getSelectedItem();
			String Etiqueta=(String)ventanaPersona.getEtiqueta().getSelectedItem();


			nuevaPersona.setLocalidad(deStringaLocalidadDTO(Localidad));
			nuevaPersona.setEtiqueta(deStringaEtiquetaDTO(Etiqueta));

			return nuevaPersona;



		}


}
