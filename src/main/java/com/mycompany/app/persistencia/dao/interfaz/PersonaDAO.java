package com.mycompany.app.persistencia.dao.interfaz;

import com.mycompany.app.dto.PersonaDTO;

import java.util.List;



public interface PersonaDAO 
{
	
	public boolean insert(PersonaDTO persona);

	public boolean delete(PersonaDTO persona_a_eliminar);
	
	public List<PersonaDTO> readAll();
}
