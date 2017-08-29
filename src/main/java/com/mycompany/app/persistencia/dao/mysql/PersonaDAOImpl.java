package com.mycompany.app.persistencia.dao.mysql;

import com.mycompany.app.dto.EtiquetaDTO;
import com.mycompany.app.dto.LocalidadDTO;
import com.mycompany.app.dto.PersonaDTO;
import com.mycompany.app.persistencia.conexion.Conexion;
import com.mycompany.app.persistencia.dao.interfaz.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class PersonaDAOImpl implements DAO<PersonaDTO>
{
	private static final String insert = "INSERT INTO Personas(idPersona, nombre, telefono,calle,altura,piso,departamento,email,fechaNacimiento,mailServer,idLocalidad,idEtiqueta) VALUES(?, ?, ?, ? , ?, ?, ?, ? ,? ,? ,? ,?)";
	private static final String insert_noFK = "INSERT INTO Personas(idPersona, nombre, telefono,calle,altura,piso,departamento,email,fechaNacimiento,mailServer) VALUES(?, ?, ?, ? , ?, ?, ?, ? ,? ,?)";
	private static final String insert_etiqueta = "INSERT INTO Personas(idPersona, nombre, telefono,calle,altura,piso,departamento,email,fechaNacimiento,mailServer,idEtiqueta) VALUES(?, ?, ?, ? , ?, ?, ?, ? ,? ,? ,? )";
	private static final String insert_localidad = "INSERT INTO Personas(idPersona, nombre, telefono,calle,altura,piso,departamento,email,fechaNacimiento,mailServer,idLocalidad) VALUES(?, ?, ?, ? , ?, ?, ?, ? ,?,? ,?)";
	private static final String delete = "DELETE FROM Personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM Personas";





	private static final Conexion conexion = Conexion.getConexion();






	public boolean insert(PersonaDTO persona)
	{
		boolean noForeignKey = (persona.getLocalidad()==null)&&(persona.getEtiqueta()==null);
		boolean etiquetaOnly = (persona.getLocalidad()==null)&&(persona.getEtiqueta()!=null);
		boolean localidadOnly = (persona.getLocalidad()!=null)&&(persona.getEtiqueta()==null);
		boolean Both = (persona.getLocalidad()!=null)&&(persona.getEtiqueta()!=null);


		PreparedStatement statement=null;


		try
		{


			if(Both) {
				statement = conexion.getSQLConexion().prepareStatement(insert);
				statement.setInt(11, persona.getLocalidad().getIdLocalidad());
				statement.setInt(12, persona.getEtiqueta().getIdEtiqueta());
			}
			else if(etiquetaOnly){
				statement = conexion.getSQLConexion().prepareStatement(insert_etiqueta);

				statement.setInt(11,persona.getEtiqueta().getIdEtiqueta());

			}
			else if(localidadOnly){
				statement = conexion.getSQLConexion().prepareStatement(insert_localidad);
				statement.setInt(11,persona.getLocalidad().getIdLocalidad());

			}
			else if(noForeignKey) {
				statement = conexion.getSQLConexion().prepareStatement(insert_noFK);
			}

			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			statement.setString(4,persona.getCalle());
			statement.setString(5,persona.getAltura());
			statement.setString(6,persona.getPiso());
			statement.setString(7,persona.getDepartamento());
			statement.setString(8,persona.getEmail());
			statement.setString(9,persona.getFechaNacimmiento());
			statement.setString(10,persona.getMailServer());





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

	@Override
	public boolean update(PersonaDTO objeto) {
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
				personaDTO.setAltura(resultSet.getString("Altura"));
				personaDTO.setPiso(resultSet.getString("Piso"));
				personaDTO.setDepartamento(resultSet.getString("Departamento"));
				personaDTO.setEmail(resultSet.getString("Email"));
				if(personaDTO.getEmail() != null )
					if(!personaDTO.getEmail().equals(""))
						personaDTO.setMailServer();
				personaDTO.setFechaNacimmiento(resultSet.getString("FechaNacimiento"));

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