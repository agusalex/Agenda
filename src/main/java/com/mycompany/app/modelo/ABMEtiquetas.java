package com.mycompany.app.modelo;


import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.negocio.Main;
import com.mycompany.app.persistencia.dao.interfaz.DAO;
import com.mycompany.app.persistencia.dao.mysql.EtiquetaDAOImpl;

import java.util.List;

public class ABMEtiquetas

{
	private DAO etiqueta;

	public  ABMEtiquetas()
	{
		etiqueta = new EtiquetaDAOImpl();
	}

	public boolean agregarEtiqueta(EtiquetaDTO nuevaEtiqueta)
	{
		return etiqueta.insert(nuevaEtiqueta);

	}

	public boolean editarEtiqueta(EtiquetaDTO nuevaEtiqueta) {
		boolean ret=etiqueta.update(nuevaEtiqueta);

		Main.refreshAgenda();
	return ret;}


	public boolean borrarEtiqueta(EtiquetaDTO etiqueta_a_eliminar)
	{


		return etiqueta.delete(etiqueta_a_eliminar);
	}


	public List<EtiquetaDTO> obtenerEtiquetas()
	{
		return etiqueta.readAll();
	}

}
