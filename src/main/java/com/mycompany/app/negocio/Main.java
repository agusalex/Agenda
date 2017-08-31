package com.mycompany.app.negocio;


import com.mycompany.app.modelo.Agenda;
import com.mycompany.app.presentacion.controlador.Controlador;

import com.mycompany.app.presentacion.vista.Vista;

public class Main
{


	private static Controlador controlador;
	private static Vista vista;
	private static Agenda modelo;
	private static Main main;


	public static Main getMain() {
		return main;
	}

	private Main(){
		vista = Vista.getVista();
		modelo = Agenda.getAgenda();
		controlador = new Controlador(vista, modelo);
		controlador.inicializar();


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
