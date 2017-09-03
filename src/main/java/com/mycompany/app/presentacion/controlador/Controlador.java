package com.mycompany.app.presentacion.controlador;

import java.awt.event.*;
import java.io.File;

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
import com.mycompany.app.presentacion.vista.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;


public class Controlador implements ActionListener
{
		private Vista vista;
		private List<PersonaDTO> personas_en_tabla;
		private static VentanaPersona ventanaPersona;
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
			this.vista.getBtnRefresh().addActionListener(this);
			this.vista.getBtnDBConfig().addActionListener(this);
			this.agenda = agenda;
			this.personas_en_tabla = null;
		}


	public ArrayList<LocalidadDTO> cargarLocalidades(){
		ArrayList<LocalidadDTO> listaLocalidades=null;
		try {
			ABMLocalidades ABML = new ABMLocalidades();
			listaLocalidades = (ArrayList) ABML.obtenerLocalidades();
		}
		catch (Exception e ){
			this.vista.showError("Error de Conexion");
		}
		return  listaLocalidades;
	}

	
	public ArrayList<EtiquetaDTO> cargarEtiquetas() {
		ArrayList<EtiquetaDTO> listaEtiquetas=null;
		try{
		ABMEtiquetas ABML=new ABMEtiquetas();
		 listaEtiquetas=(ArrayList)ABML.obtenerEtiquetas();}
		 catch (Exception e){
			this.vista.showError("Error de Conexion");
		 }

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
			try{
			this.personas_en_tabla = agenda.obtenerPersonas();
			this.personas_en_tabla.size();}
			catch (Exception e){
				this.vista.show();
				this.vista.showError("Error de Conexion");
			}
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
		try {
			if (e.getSource() == this.vista.getBtnEtiquetas()) {
				this.vistaEtiqueta = VistaEtiqueta.getVistaEtiqueta();
				this.vistaEtiqueta.enable();
				this.vistaEtiqueta.getFrame().addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						vista.enable();
						vistaEtiqueta.getFrame().dispose();
					}
				});

				ABMEtiquetas modeloet = new ABMEtiquetas();
				ControladorEtiqueta controladoret = new ControladorEtiqueta(this.vistaEtiqueta, modeloet);
				controladoret.inicializar();

				this.vista.disable();
			} else if (e.getSource() == this.vista.getBtnRefresh()) {
				this.refresh();
				System.out.println("...");


			} else if (e.getSource() == this.vista.getBtnDBConfig()) {

				VentanaDBConfig vDBC = VentanaDBConfig.getVentanaDBConfig();
				System.out.println("...");


			} else if (e.getSource() == this.vista.getBtnLocalidades()) {
				this.vistaLocalidad = VistaLocalidad.getVistaLocalidad();
				this.vistaLocalidad.enable();
				this.vistaLocalidad.getFrame().addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						vista.enable();
						vistaLocalidad.getFrame().dispose();
					}
				});

				ABMLocalidades modeloLo = new ABMLocalidades();
				ControladorLocalidad controladorLo = new ControladorLocalidad(this.vistaLocalidad, modeloLo);
				controladorLo.inicializar();

				this.vista.disable();
			} else if (e.getSource() == this.vista.getBtnAgregar()) {
				this.ventanaPersona = new VentanaPersona(this);

				this.ventanaPersona.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						vista.enable();
						ventanaPersona.dispose();
					}
				});

				this.ventanaPersona.getCalendarCheckBox().addActionListener(this);
				JCheckBox calendarBox = this.ventanaPersona.getCalendarCheckBox();
				calendarBox.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent itemEvent) {
						if (calendarBox.isSelected()) {
							ventanaPersona.enableCalendar();
						} else {
							ventanaPersona.disableCalendar();
						}
					}
				});
				this.vista.disable();

			} else if (e.getSource() == this.vista.getBtnEditar()) {


				int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
				if (filas_seleccionadas.length > 0) {
					BKP = this.personas_en_tabla.get(filas_seleccionadas[0]);
					this.ventanaPersona = new VentanaPersona(this, this.personas_en_tabla.get(filas_seleccionadas[0]));

					this.ventanaPersona.addWindowListener(new WindowAdapter() {
						public void windowClosing(WindowEvent e) {
							vista.enable();
							ventanaPersona.dispose();
						}
					});

					this.ventanaPersona.getCalendarCheckBox().addActionListener(this);
					JCheckBox calendarBox = this.ventanaPersona.getCalendarCheckBox();
					calendarBox.addItemListener(new ItemListener() {
						@Override
						public void itemStateChanged(ItemEvent itemEvent) {
							if (calendarBox.isSelected()) {
								ventanaPersona.enableCalendar();
							} else {
								ventanaPersona.disableCalendar();
							}
						}
					});

					this.vista.disable();
				}


			} else if (e.getSource() == this.vista.getBtnBorrar()) {
				int[] filas_seleccionadas = this.vista.getTablaPersonas().getSelectedRows();
				for (int fila : filas_seleccionadas) {
					boolean borrar = this.agenda.borrarPersona(this.personas_en_tabla.get(fila));
					if (!borrar) {
						vista.showError("Error al eliminar la Persona!");
					}


				}

				this.llenarTabla();

			} else if (e.getSource() == this.vista.getBtnReporte()) {
				ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas(), "reportes" + File.separator + "ReporteAgenda.jasper");
				reporte.mostrar();
			} else if (e.getSource() == this.vista.getBtnReporteMail()) {

				ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas(), "reportes" + File.separator + "MailReport.jasper");
				reporte.mostrar();
			} else if (e.getSource() == this.ventanaPersona.getBtnAgregarPersona()) {
				if (this.ventanaPersona.allFieldsChecked()) {
					PersonaDTO persona = new PersonaDTO();
					PersonaDTO nuevaPersona = cargarDatosPersona(persona);

					//	 nuevaPersona = new PersonaDTO(0,this.ventanaPersona.getTxtNombre().getText(), ventanaPersona.getTxtTelefono().getText());
					boolean agregar = this.agenda.agregarPersona(nuevaPersona);

					if (!agregar) {
						vista.showError("Error al agregar la persona!");
					}
					this.llenarTabla();
					this.ventanaPersona.dispose();
					this.vista.enable();
				}
			} else if (e.getSource() == this.ventanaPersona.getBtnGuardarPersona()) {


				if (this.ventanaPersona.allFieldsChecked()) {

					PersonaDTO persona = new PersonaDTO();
					PersonaDTO nuevaPersona = cargarDatosPersona(persona);


					boolean borrar = this.agenda.borrarPersona(BKP);

					nuevaPersona.setIdPersona(BKP.getIdPersona());

					boolean agregar = this.agenda.agregarPersona(nuevaPersona);

					if (!agregar || !borrar) {
						vista.showError("Error error al editar la persona!");
					}

					this.llenarTabla();
					this.ventanaPersona.dispose();
					this.vista.enable();
				}
			} else if (e.getSource() == this.ventanaPersona.getBtnCerrar()) {
				this.vista.enable();
				this.refresh();
				ventanaPersona.dispose();


			}
		}
		catch (Exception f ){
			f.printStackTrace();
			this.vista.showError("Error de Conexion");

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

			altura = ventanaPersona.getTxtAltura().getText();
			piso = ventanaPersona.getTxtPiso().getText();
			String dpto = ventanaPersona.getTxtDepartamento().getText();
			String email = ventanaPersona.getTxtEmail().getText();
			nuevaPersona.setTelefono(telefono);
			nuevaPersona.setCalle(nombreCalle);
			nuevaPersona.setAltura(altura);
			nuevaPersona.setPiso(piso);
			nuevaPersona.setDepartamento(dpto);
			nuevaPersona.setEmail(email);


			if(this.ventanaPersona.getCalendario().isEnabled()) {
				Date fecha = ventanaPersona.getCalendario().getDate();
				if (!esHoy(Utils.stringfromDate(fecha))) {
					nuevaPersona.setFechaNacimmiento(Utils.stringfromDate(fecha)); //PARA QUE SE GUARDE LA FECHA NOMAS Y NO EL RESTO, SEGUNDOS ETC
				}
			}

			String Localidad= (String)ventanaPersona.getLocalidad().getSelectedItem();
			String Etiqueta=(String)ventanaPersona.getEtiqueta().getSelectedItem();


			nuevaPersona.setLocalidad(deStringaLocalidadDTO(Localidad));
			nuevaPersona.setEtiqueta(deStringaEtiquetaDTO(Etiqueta));

			return nuevaPersona;



		}


		public boolean esHoy(String fechaString){
			String now=Utils.stringfromDate(new Date());

			//System.out.println(fechaString+ "es igual a "+now+"?:"+now.equals(fechaString));

			return now.equals(fechaString);


		}



	public void refresh() {
		this.llenarTabla();


	}
}
