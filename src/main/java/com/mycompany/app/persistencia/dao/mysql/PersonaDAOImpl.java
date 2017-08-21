package com.mycompany.app.persistencia.dao.mysql;

import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.dto.PersonaDTO;
import com.mycompany.app.persistencia.conexion.Conexion;
import com.mycompany.app.persistencia.dao.interfaz.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class PersonaDAOImpl implements DAO<PersonaDTO>
{
	private static final String insert = "INSERT INTO Personas(idPersona, nombre, telefono,calle,altura,piso,departamento,email,fechaNacimiento,idLocalidad,idEtiqueta) VALUES(?, ?, ?, ? , ?, ?, ?, ? ,? ,? ,?)";
	private static final String delete = "DELETE FROM Personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM Personas";


	private static final Conexion conexion = Conexion.getConexion();
	
	public boolean insert(PersonaDTO persona)
	{
		PreparedStatement statement=null;

		try 
		{

			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			statement.setString(4,persona.getCalle());
			statement.setInt(5,persona.getAltura());
			statement.setInt(6,persona.getPiso());
			statement.setString(7,persona.getDepartamento());
			statement.setString(8,persona.getEmail());
			statement.setDate(9,new java.sql.Date(persona.getFechaNacimmiento().getTime()));
			System.out.println(persona.getLocalidad().getIdLocalidad());
			System.out.println(persona.getEtiqueta().getIdEtiqueta());
			statement.setInt(10,persona.getLocalidad().getIdLocalidad());
			statement.setInt(11,persona.getEtiqueta().getIdEtiqueta());



			if(statement.executeUpdate() > 0) //Si se ejecutó devuelvo true
				return true;
		} 
		catch (SQLException e) 
		{
			if(statement!=null){
				System.out.println("error en la Sentencia SQL= "+statement.toString());
				e.printStackTrace();}
		}
		finally //Se ejecuta siempre
		{
			conexion.cerrarConexion();
		}
		return false;
	}


	public boolean delete(PersonaDTO persona_a_eliminar)
	{
		PreparedStatement statement;
		int chequeoUpdate=0;
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
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

	private HashMap<Integer,String> cargarEtiquetas() {
		EtiquetaDAOImpl EtiquetaDAO=new EtiquetaDAOImpl();

		HashMap<Integer,String>  map= new HashMap<>();
		for (EtiquetaDTO loc:
				EtiquetaDAO.readAll()) {

			map.put(loc.getIdEtiqueta(),loc.getNombre());

		};
		return map;
		
	}

	public HashMap<Integer,String> cargarLocalidades(){
		LocalidadDAOImpl localidadDAO=new LocalidadDAOImpl();

		HashMap<Integer,String>  map= new HashMap<>();
		for (LocalidadDTO loc:
		localidadDAO.readAll()) {

			map.put(loc.getIdLocalidad(),loc.getNombre());

		};
		return map;

	}

	public List<PersonaDTO> readAll()
	{

		HashMap<Integer,String> mapa= cargarLocalidades();
		HashMap<Integer,String> mapa2= cargarEtiquetas();


		PreparedStatement statement;
		ResultSet resultSet; //Guarda el resultado de la query
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		try 
		{
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			
			while(resultSet.next())
			{
				//personas.add(new PersonaDTO(resultSet.getInt("idPersona"), resultSet.getString("Nombre"), resultSet.getString("Telefono")));
				PersonaDTO personaDTO=new PersonaDTO();
				personaDTO.setIdPersona(resultSet.getInt("idPersona"));
				personaDTO.setNombre(resultSet.getString("Nombre"));
				personaDTO.setTelefono(resultSet.getString("Telefono"));
				personaDTO.setCalle(resultSet.getString("Calle"));
				personaDTO.setAltura(resultSet.getInt("Altura"));
				personaDTO.setPiso(resultSet.getInt("Piso"));
				personaDTO.setDepartamento(resultSet.getString("Departamento"));
				personaDTO.setEmail(resultSet.getString("Email"));
				personaDTO.setFechaNacimmiento(resultSet.getDate("FechaNacimiento"));

				int codigoLocalidad=resultSet.getInt("idLocalidad");
				String nombreLocalidad=mapa.get(codigoLocalidad);
				personaDTO.setLocalidad(new LocalidadDTO(codigoLocalidad,nombreLocalidad));

				int codigoEtiqueta=resultSet.getInt("idEtiqueta");
				String nombreEtiqueta=mapa2.get(codigoEtiqueta);
				personaDTO.setEtiqueta(new EtiquetaDTO(codigoEtiqueta,nombreEtiqueta));
				personas.add(personaDTO);



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
		return personas;
	}


}
