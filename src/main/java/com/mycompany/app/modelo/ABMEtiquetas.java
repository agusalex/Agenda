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

	public void agregarEtiqueta(EtiquetaDTO nuevaEtiqueta)
	{
		etiqueta.insert(nuevaEtiqueta);

	}

	public void editarEtiqueta(EtiquetaDTO nuevaEtiqueta) {etiqueta.update(nuevaEtiqueta);
		Main.refreshAgenda();}


	public void borrarEtiqueta(EtiquetaDTO etiqueta_a_eliminar)
	{

		etiqueta.delete(etiqueta_a_eliminar);
	}


	public List<EtiquetaDTO> obtenerEtiquetas()
	{
		return etiqueta.readAll();
	}

}
