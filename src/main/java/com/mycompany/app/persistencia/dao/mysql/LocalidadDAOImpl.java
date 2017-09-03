package com.mycompany.app.persistencia.dao.mysql;

import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.persistencia.conexion.Conexion;
import com.mycompany.app.persistencia.dao.interfaz.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LocalidadDAOImpl implements DAO<LocalidadDTO>
{
	private static final String insert = "INSERT INTO Localidades(idLocalidad, nombre_localidad) VALUES(? ,?)";
	private static final String update = "UPDATE Localidades SET nombre_localidad=? WHERE idLocalidad=?";
	private static final String delete = "DELETE FROM Localidades WHERE idLocalidad = ?";
	private static final String readall = "SELECT * FROM Localidades";


	private static final Conexion conexion = Conexion.getInstancia();


	public boolean update(LocalidadDTO localidad)
	{
		PreparedStatement statement=null;

		try
		{

			statement = conexion.getjdbcConnection().prepareStatement(update);
			statement.setInt(2, localidad.getIdLocalidad());
			statement.setString(1, localidad.getNombre());




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
	
	public boolean insert(LocalidadDTO localidad)
	{
		PreparedStatement statement=null;

		try 
		{

			statement = conexion.getjdbcConnection().prepareStatement(insert);
			statement.setInt(1, localidad.getIdLocalidad());
			statement.setString(2, localidad.getNombre());




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





	public boolean delete(LocalidadDTO localidad_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getjdbcConnection().prepareStatement(delete);
			statement.setString(1, Integer.toString(localidad_a_eliminar.getIdLocalidad()));
			chequeoUpdate = statement.executeUpdate();
			if(chequeoUpdate > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}
	
	public List<LocalidadDTO> readAll()
	{
		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidads = new ArrayList<LocalidadDTO>();
		try 
		{
			statement = conexion.getjdbcConnection().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				localidads.add(new LocalidadDTO(resultSet.getInt("idlocalidad"), resultSet.getString("nombre_localidad")));
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
		return localidads;
	}
}
