package com.mycompany.app.modelo;


import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.negocio.Main;
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

	public boolean agregarLocalidad(LocalidadDTO nuevaLocalidad)
	{
		return Localidad.insert(nuevaLocalidad);
	}

	public boolean editarLocalidad(LocalidadDTO nuevaLocalidad) {

		boolean ret=Localidad.update(nuevaLocalidad);
		Main.refreshAgenda();
		return ret;
	}



	public boolean borrarLocalidad(LocalidadDTO Localidad_a_eliminar)
	{


		return Localidad.delete(Localidad_a_eliminar);
	}

	public List<LocalidadDTO> obtenerLocalidades()
	{
		return Localidad.readAll();
	}

}
