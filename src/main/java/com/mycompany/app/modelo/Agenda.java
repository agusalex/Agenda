package com.mycompany.app.modelo;


import com.mycompany.app.dto.PersonaDTO;
import com.mycompany.app.persistencia.dao.interfaz.DAO;
import com.mycompany.app.persistencia.dao.mysql.PersonaDAOImpl;

import java.util.List;

public class Agenda
{
	private DAO persona;
	
	public Agenda()
	{
		persona = new PersonaDAOImpl();
	}

	public void editarPersona(PersonaDTO nuevaPersona)
	{

		borrarPersona(nuevaPersona);
		agregarPersona(nuevaPersona);
	}
	
	public void agregarPersona(PersonaDTO nuevaPersona)
	{
		persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) 
	{
		persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return persona.readAll();		
	}
	
}
