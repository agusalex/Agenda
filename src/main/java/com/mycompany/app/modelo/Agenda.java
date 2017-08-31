package com.mycompany.app.modelo;


import com.mycompany.app.dto.PersonaDTO;
import com.mycompany.app.persistencia.dao.interfaz.DAO;
import com.mycompany.app.persistencia.dao.mysql.PersonaDAOImpl;

import java.util.List;

public class Agenda
{
	private DAO persona;
	private static Agenda agenda;
	private Agenda()
	{
		persona = new PersonaDAOImpl();
	}

	public static Agenda getAgenda() {

		if(agenda==null)
			agenda=new Agenda();
		return agenda;
	}

	public boolean agregarPersona(PersonaDTO nuevaPersona)
	{

        return persona.insert(nuevaPersona);
    }

	public boolean borrarPersona(PersonaDTO persona_a_eliminar)
	{

		return persona.delete(persona_a_eliminar);
	}
	
	public List<PersonaDTO> obtenerPersonas()
	{
		return persona.readAll();		
	}
	
}
