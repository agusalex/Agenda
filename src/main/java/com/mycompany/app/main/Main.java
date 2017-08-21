package com.mycompany.app.main;


import com.mycompany.app.modelo.ABMEtiquetas;
import com.mycompany.app.modelo.Agenda;
import com.mycompany.app.persistencia.conexion.Conexion;
import com.mycompany.app.presentacion.controlador.Controlador;
import com.mycompany.app.presentacion.controlador.ControladorEtiqueta;
import com.mycompany.app.presentacion.vista.Vista;
import com.mycompany.app.presentacion.vista.VistaEtiqueta;

public class Main
{

	public static void main(String[] args) 
	{
		Vista vista = new Vista();
		Agenda modelo = new Agenda();
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();

		VistaEtiqueta VistaEtiqueta = new VistaEtiqueta();
		ABMEtiquetas modeloet = new ABMEtiquetas();
		ControladorEtiqueta controladoret = new ControladorEtiqueta(VistaEtiqueta, modeloet);
		controladoret.inicializar();
		

	}
}
