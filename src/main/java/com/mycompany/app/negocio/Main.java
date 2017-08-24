package com.mycompany.app.negocio;


import com.mycompany.app.modelo.Agenda;
import com.mycompany.app.presentacion.controlador.Controlador;
import com.mycompany.app.presentacion.vista.Vista;

public class Main
{

	public static void main(String[] args) 
	{
		/*
		VistaEtiqueta VistaEtiqueta = new VistaEtiqueta();
		ABMEtiquetas modeloet = new ABMEtiquetas();
		ControladorEtiqueta controladoret = new ControladorEtiqueta(VistaEtiqueta, modeloet);
		controladoret.inicializar();

		VistaLocalidad VistaLocalidad = new VistaLocalidad();
		ABMLocalidades modeloLo = new ABMLocalidades();
		ControladorLocalidad controladorLo = new ControladorLocalidad(VistaLocalidad, modeloLo);
		controladorLo.inicializar();
*/
		Vista vista = new Vista();
		Agenda modelo = new Agenda();
		Controlador controlador = new Controlador(vista, modelo);
		controlador.inicializar();




	}
}
