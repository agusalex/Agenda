package com.mycompany.app.persistencia.dao.mysql;

import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.persistencia.conexion.Conexion;
import com.mycompany.app.persistencia.dao.interfaz.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class EtiquetaDAOImpl implements DAO<EtiquetaDTO>
{


	private static final String insert = "INSERT INTO Etiquetas(idEtiqueta, nombre_Etiqueta) VALUES(? ,?)";
	private static final String delete = "DELETE FROM Etiquetas WHERE idEtiqueta = ?";
	private static final String readall = "SELECT * FROM Etiquetas";


	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(EtiquetaDTO Etiqueta)
	{
		PreparedStatement statement=null;

		try 
		{

			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, Etiqueta.getIdEtiqueta());
			statement.setString(2, Etiqueta.getNombre());




			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			if(statement!=null)
				System.out.println("error en la Sentencia SQL= "+statement.toString());
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}




	public boolean delete(EtiquetaDTO Etiqueta_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(Etiqueta_a_eliminar.getIdEtiqueta()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{


		System.out.println("No es posible eliminar la etiqueta...");
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public List<EtiquetaDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<EtiquetaDTO> Etiquetas = new ArrayList<EtiquetaDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				Etiquetas.add(new EtiquetaDTO(resultSet.getInt("idEtiqueta"), resultSet.getString("nombre_etiqueta")));
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return Etiquetas;
	}
}
