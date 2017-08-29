package com.mycompany.app.persistencia.dao.interfaz;

import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.dto.PersonaDTO;

import java.util.List;



public interface DAO<T>
{
	
	public boolean insert(T objeto);

	public boolean update(T objeto);

	public boolean delete(T objeto_a_eliminar);
	
	public List<T> readAll();


}
