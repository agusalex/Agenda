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
	private VentanaDBConfig dbConfig;


	public static Main getMain() {
		return main;
	}

	public static boolean isFirstRun() {
		return firstRun;
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
	public boolean checkFirstRun(){
		String defaultProperty= Propiedades.getDEFAULTPROP();
		String customProperty= Propiedades.getCUSTOMPROP();
		firstRun= (!Propiedades.exists(defaultProperty)&&!Propiedades.exists(customProperty));
		return firstRun;

	}




	private Main(){
		if(checkFirstRun())   //SI NO HAY NINGUN PROPERTY
			setup();

		else {
			inicializar();
		}
	}




	public void setup(){
		firstRun=true;
		String defaultProperty= Propiedades.getDEFAULTPROP();
		boolean success=Propiedades.setProperties(defaultProperty,
				"org.h2.Driver",
				"jdbc:h2:~//agenda",
				"root",
				"root");
		if(success){
			dbConfig=VentanaDBConfig.getVentanaDBConfig();
			Conexion.setInstancia(Propiedades.getProperties(defaultProperty));
			dbConfig.showMsg("Bienvenido a Agenda!\n Para continuar elija una opcion, si no sabe de que se trata elija: \n \"Opciones por defecto\"");
		}
		else{
			dbConfig=VentanaDBConfig.getVentanaDBConfig();
			dbConfig.showMsg("Error al inicializar las propiedades de conexión...Intente nuevamente");
		}
	}

	public void inicializar(){

		try {


			if (Propiedades.exists(Propiedades.getDEFAULTPROP())) {
				Conexion.setInstancia(Propiedades.getProperties(
						Propiedades.getDEFAULTPROP()));
			} else if (Propiedades.exists(Propiedades.getCUSTOMPROP())) {
				Conexion.setInstancia(Propiedades.getProperties(
						Propiedades.getCUSTOMPROP()));

			} else {
				Propiedades.writeErrorLog("Error desconocido");
			}


			boolean success=Conexion.getInstancia().incializar(firstRun); //Si es true crea las tablas, sino no las crea
			if(!success)
				throw new Exception("Error de conexion");
			vista = Vista.getVista();
			modelo = Agenda.getAgenda();
			controlador = new Controlador(vista, modelo);
			controlador.inicializar();
			firstRun = false;

		}
		catch (Exception e) {


			dbConfig=VentanaDBConfig.getVentanaDBConfig();
			dbConfig.showMsg("Error al conectarse con la DB revise su configuracion");
			cerrarVentanas();
			System.out.println("Error al incializar la DB!!!");
		}


	}



	public void cerrarVentanas(){
		if(vista!=null)
			vista.close();
		Vista.setVista(null);
		controlador=null;
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
