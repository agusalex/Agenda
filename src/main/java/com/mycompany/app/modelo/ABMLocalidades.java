package com.mycompany.app.modelo;


import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.persistencia.dao.interfaz.DAO;
import com.mycompany.app.persistencia.dao.mysql.LocalidadDAOImpl;

import java.util.List;

public class ABMLocalidades

{
	private DAO Localidad;

	public ABMLocalidades()
	{
		Localidad = new LocalidadDAOImpl();
	}

	public void agregarLocalidad(LocalidadDTO nuevaLocalidad)
	{
		Localidad.insert(nuevaLocalidad);
	}

	public void editarLocalidad(LocalidadDTO nuevaLocalidad)
	{
		borrarLocalidad(nuevaLocalidad);
		agregarLocalidad(nuevaLocalidad);
	}

	public void borrarLocalidad(LocalidadDTO Localidad_a_eliminar)
	{
		Localidad.delete(Localidad_a_eliminar);
	}

	public List<LocalidadDTO> obtenerLocalidades()
	{
		return Localidad.readAll();
	}

}
