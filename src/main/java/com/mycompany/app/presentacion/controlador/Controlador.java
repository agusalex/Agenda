package com.mycompany.app.presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.dto.PersonaDTO;
import com.mycompany.app.modelo.ABMEtiquetas;
import com.mycompany.app.modelo.ABMLocalidades;
import com.mycompany.app.modelo.Agenda;
import com.mycompany.app.negocio.Utils;
import com.mycompany.app.presentacion.reportes.ReporteAgenda;
import com.mycompany.app.presentacion.vista.VentanaPersona;
import com.mycompany.app.presentacion.vista.Vista;
import com.mycompany.app.presentacion.vista.VistaEtiqueta;
import com.mycompany.app.presentacion.vista.VistaLocalidad;

import javax.swing.*;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;


public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personas_en_tabla;
		private VentanaPersona ventanaPersona;
		private Agenda agenda;
		private PersonaDTO BKP;
		private VistaEtiqueta vistaEtiqueta;
		private VistaLocalidad vistaLocalidad;


	public Controlador(Vista vista, Agenda agenda)
		{
			this.vista = vista;
			this.vista.getBtnAgregar().addActionListener(this);
			this.vista.getBtnBorrar().addActionListener(this);
			this.vista.getBtnEditar().addActionListener(this);
			this.vista.getBtnReporte().addActionListener(this);
			this.vista.getBtnEtiquetas().addActionListener(this);
			this.vista.getBtnLocalidades().addActionListener(this);
			this.vista.getBtnReporteMail().addActionListener(this);
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
				Object[] fila = {this.personas_en_tabla.get(i).getNombre(),
						this.personas_en_tabla.get(i).getTelefono(),
						this.personas_en_tabla.get(i).getCalle(),
						this.personas_en_tabla.get(i).getAltura(),
						this.personas_en_tabla.get(i).getPiso(),
						this.personas_en_tabla.get(i).getDepartamento(),
						this.personas_en_tabla.get(i).getLocalidad(),
						this.personas_en_tabla.get(i).getEtiqueta(),
						this.personas_en_tabla.get(i).getEmail(), this.personas_en_tabla.get(i).getFechaNacimmiento()};
				this.vista.getModelPersonas().addRow(fila);
			}
			this.vista.show();
			if(this.personas_en_tabla.size()==0){
				this.vista.getBtnReporte().setEnabled(false);
				this.vista.getBtnReporteMail().setEnabled(false);
			}
			else{
				this.vista.getBtnReporte().setEnabled(true);
				this.vista.getBtnReporteMail().setEnabled(true);
			}



		}



	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.vista.getBtnEtiquetas()){
			this.vistaEtiqueta = VistaEtiqueta.getVistaEtiqueta();
			ABMEtiquetas modeloet = new ABMEtiquetas();
			ControladorEtiqueta controladoret = new ControladorEtiqueta(this.vistaEtiqueta, modeloet);
			controladoret.inicializar();
		}

		else if(e.getSource() == this.vista.getBtnLocalidades()){
			this.vistaLocalidad =VistaLocalidad.getVistaLocalidad();
			ABMLocalidades modeloLo = new ABMLocalidades();
			ControladorLocalidad controladorLo = new ControladorLocalidad(this.vistaLocalidad, modeloLo);
			controladorLo.inicializar();
		}

		else if(e.getSource() == this.vista.getBtnAgregar())
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
					boolean borrar=this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
					if(!borrar){
						vista.showError("Error al eliminar la Persona!");
					}


				}
				
				this.llenarTabla();
				
			}
			else if(e.getSource() == this.vista.getBtnReporte())
			{				
				ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas(), "reportes"+ File.separator+"ReporteAgenda.jasper");
				reporte.mostrar();				
			}

			else if(e.getSource() == this.vista.getBtnReporteMail()){

				ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas(), "reportes"+ File.separator+"MailReport.jasper");
				reporte.mostrar();
			}

		    else if(e.getSource() == this.ventanaPersona.getBtnAgregarPersona())
			{
				if(this.ventanaPersona.allFieldsChecked()) {
					PersonaDTO persona = new PersonaDTO();
					PersonaDTO nuevaPersona = cargarDatosPersona(persona);

					//	 nuevaPersona = new PersonaDTO(0,this.ventanaPersona.getTxtNombre().getText(), ventanaPersona.getTxtTelefono().getText());
					boolean agregar=this.agenda.agregarPersona(nuevaPersona);

					if(!agregar){
						vista.showError("Error al agregar la persona!");
					}
					this.llenarTabla();
					this.ventanaPersona.dispose();
				}
			}

			else if(e.getSource() == this.ventanaPersona.getBtnGuardarPersona())
			{


				if(this.ventanaPersona.allFieldsChecked()) {

					PersonaDTO persona = new PersonaDTO();
					PersonaDTO nuevaPersona = cargarDatosPersona(persona);


					boolean borrar=this.agenda.borrarPersona(BKP);

					nuevaPersona.setIdPersona(BKP.getIdPersona());

					boolean agregar=this.agenda.agregarPersona(nuevaPersona);

					if(!agregar||!borrar){
						vista.showError("Error error al editar la persona!");
					}

					this.llenarTabla();
					this.ventanaPersona.dispose();
				}
			}
	}


		public LocalidadDTO deStringaLocalidadDTO(String localidad){

			ArrayList<LocalidadDTO> localidadDTOS=cargarLocalidades();
			for (LocalidadDTO loc: localidadDTOS
				 ) {
				if(loc.getNombre() != null)
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

			if(et.getNombre() != null)
				if (et.getNombre().equals(etiqueta)){

					return et;
				}

		}
		return null;

	}


		private PersonaDTO cargarDatosPersona(PersonaDTO persona){
			PersonaDTO nuevaPersona = new PersonaDTO();

			nuevaPersona.setNombre(ventanaPersona.getTxtNombre().getText());


			String telefono = ventanaPersona.getTxtTelefono().getText();
			String nombreCalle = ventanaPersona.getTxtCalle().getText();
			String altura = null;
			String piso = null;
			//if(!ventanaPersona.getTxtAltura().getText().equals(""))
				altura = ventanaPersona.getTxtAltura().getText();
			//if(!ventanaPersona.getTxtPiso().getText().equals(""))
				piso = ventanaPersona.getTxtPiso().getText();
			String dpto = ventanaPersona.getTxtDepartamento().getText();
			String email = ventanaPersona.getTxtEmail().getText();

			//if(!telefono.equals(""))
				nuevaPersona.setTelefono(telefono);
			//if(!nombreCalle.equals(""))
				nuevaPersona.setCalle(nombreCalle);
			//if (altura != null)
				nuevaPersona.setAltura(altura);
			//if(piso != null)
				nuevaPersona.setPiso(piso);
			//if(!dpto.equals(""))
				nuevaPersona.setDepartamento(dpto);
			//if(!email.equals("")){
				nuevaPersona.setEmail(email);
				//nuevaPersona.setMailServer();
			//}


			if(!esHoy(ventanaPersona.getCalendario().getDate().toInstant().toString().substring(0,10))){
				nuevaPersona.setFechaNacimmiento(ventanaPersona.getCalendario().getDate().toInstant().toString().substring(0,10)); //PARA QUE SE GUARDE LA FECHA NOMAS Y NO EL RESTO, SEGUNDOS ETC
			}
			else {
				nuevaPersona.setFechaNacimmiento("");
			}

			String Localidad= (String)ventanaPersona.getLocalidad().getSelectedItem();
			String Etiqueta=(String)ventanaPersona.getEtiqueta().getSelectedItem();


			nuevaPersona.setLocalidad(deStringaLocalidadDTO(Localidad));
			nuevaPersona.setEtiqueta(deStringaEtiquetaDTO(Etiqueta));

			System.out.println(nuevaPersona.getFechaNacimmiento());
			return nuevaPersona;



		}


		public boolean esHoy(String fechaString){

			Date now=Utils.datefromString(Instant.now().toString().substring(0,10));
			Date fecha=Utils.datefromString(fechaString);
			return now.equals(fecha);


		}

	public void refresh() {
		this.llenarTabla();


	}
}
