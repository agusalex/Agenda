package com.mycompany.app.negocio;


import com.mycompany.app.modelo.Agenda;
import com.mycompany.app.persistencia.Propiedades;
import com.mycompany.app.persistencia.conexion.Conexion;
import com.mycompany.app.presentacion.controlador.Controlador;

import com.mycompany.app.presentacion.vista.VentanaDBConfig;
import com.mycompany.app.presentacion.vista.Vista;

public class Main
{


	private static Controlador controlador;
	private static Vista vista;
	private static Agenda modelo;
	private static Main main;




	private static boolean firstRun=false;


	public static Main getMain() {
		return main;
	}
	/*Propiedades props=Main.getProperties(Main.getDEFAULTPROP());  //inicializa con las default


									if (props == null) {   //Si no la encuentra
	props= Main.getProperties(Main.getCUSTOMPROP());  //Busca las Custom
	if(props==null){
		Main.setProperties(Main.getDEFAULTPROP(),     //Si no las encuentra crea las default
				"jdbc:mysql://localhost:3306",
				"root","root");
		props=Main.getProperties(Main.getDEFAULTPROP());   //Intenta de nuevo
		if(props==null) {
			writeErrorLog("Error al crear las properties");
			return;*/
	private Main(){
		String defaultProperty= Propiedades.getDEFAULTPROP();
		String customProperty= Propiedades.getCUSTOMPROP();


		if((!Propiedades.exists(defaultProperty)&&!Propiedades.exists(customProperty))) {  //SI NO HAY NINGUN PROPERTY
			firstRun();

		}

		else {

			try {
				iniciarVentanas();
			} catch (Exception e) {
				VentanaDBConfig dbConfig=VentanaDBConfig.getVentanaDBConfig();
				dbConfig.showMsg("Error al conectarse con la DB revise su configuracion");
				System.out.println("Error al incializar la DB");
			}

		}
	}




	public void firstRun(){
		firstRun=true;
		String defaultProperty= Propiedades.getDEFAULTPROP();
		boolean success=Propiedades.setProperties(defaultProperty,
				"com.mysql.jdbc.Driver",
				"jdbc:mysql://localhost:3306",
				"root",
				"root");
		if(success){
			VentanaDBConfig dbConfig=VentanaDBConfig.getVentanaDBConfig();
			Conexion.setInstancia(Propiedades.getProperties(defaultProperty));
			dbConfig.showMsg("Bienvenido a Agenda!\n Para continuar elija una opcion, si no sabe de que se trata elija: \n \"Opciones por defecto\"");
		}
		else{
			VentanaDBConfig dbConfig=VentanaDBConfig.getVentanaDBConfig();
			dbConfig.showMsg("Error al inicializar las propiedades de conexión");
		}
	}

	public void iniciarVentanas(){


		if(Propiedades.exists(Propiedades.getCUSTOMPROP())){
			Conexion.setInstancia(Propiedades.getProperties(
					Propiedades.getCUSTOMPROP()));

		}

		else if(Propiedades.exists(Propiedades.getDEFAULTPROP())){
			Conexion.setInstancia(Propiedades.getProperties(
					Propiedades.getDEFAULTPROP()));
		}
		else{
			Propiedades.writeErrorLog("Error desconocido");
		}

		Conexion.getInstancia().incializar(firstRun);
		vista = Vista.getVista();
		modelo = Agenda.getAgenda();
		controlador = new Controlador(vista, modelo);
		controlador.inicializar();
		firstRun=false;
	}

	public static void main(String[] args)
	{
		Main.main=new Main();
	}

	public static void refreshAgenda(){

		if(controlador!=null)
			controlador.refresh();
	}




}
