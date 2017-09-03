package com.mycompany.app.persistencia.conexion;

import com.mycompany.app.persistencia.Propiedades;

import java.sql.*;
import java.util.Properties;


public class Conexion
{
	private static Conexion instancia;
	private static Connection jdbcConnection;
	static private String username;
	static private String password;
	static private String url;
	static private String driver;



	public Connection getjdbcConnection()
	{

			try {
				if(jdbcConnection.isClosed()){
					incializar(false);
				}
			} catch (SQLException e) {
			e.printStackTrace();
		}
		return jdbcConnection;
	}

	public void cerrarConexion()
	{

		try {
			jdbcConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}



	public static void setInstancia( Properties props){

		instancia=new Conexion(props);

	}

	public static Conexion getInstancia()
	{

		return instancia;
	}



	private Conexion(){

	}



	public boolean executeStatement(String statementt){


		PreparedStatement statement=null;

		try
		{

			statement = getjdbcConnection().prepareStatement(statementt);





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
			cerrarConexion();
		}
		return false;

	}


	private Conexion(java.util.Properties props){

		driver = props.getProperty("jdbc.driver");
		url = props.getProperty("jdbc.url");
		username = props.getProperty("jdbc.username");
		password =props.getProperty("jdbc.password");
	}

	public boolean incializar(boolean firstRun){
		if((driver==null)||(url==null)||(username==null)||(password==null))
			return false;


		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		jdbcConnection = null;

		try {

			if(!firstRun)
				jdbcConnection = DriverManager.getConnection(url+"/"+ Propiedades.getDATABASENAME()+Propiedades.getDATABASEOPTIONS(), username, password);
			else{
				jdbcConnection = DriverManager.getConnection(url, username, password);
				executeStatement("CREATE DATABASE "+Propiedades.getDATABASENAME()+";");
				jdbcConnection.close();
				jdbcConnection = DriverManager.getConnection(url+"/"+ Propiedades.getDATABASENAME(), username, password);
				createTables();
				
				incializar(false);

			}



		} catch (SQLException e) {
			System.out.println("Error al conectar...");
			e.printStackTrace();
			return false;


		}

		return true;
	}


	private void createTables(){
		executeStatement(createLocalidades());
		executeStatement(createEtiquetas());
		executeStatement(createPersonas());
		executeStatement(addExample());

	}

	private String createLocalidades() {
		return "CREATE TABLE Localidades" +
				"(  idLocalidad int(11) NOT NULL AUTO_INCREMENT," +
				"   nombre_Localidad varchar(45) ," +
				"   PRIMARY KEY (idLocalidad));";
	}




	private String createEtiquetas(){
		return "CREATE TABLE Etiquetas(" +
				"    idEtiqueta int(11) NOT NULL AUTO_INCREMENT," +
				"nombre_Etiqueta varchar(45) ," +
				"    PRIMARY KEY (idEtiqueta)" +
				");";
	}
	private String createPersonas(){
		return "CREATE TABLE Personas" +
				"(" +
				"    idPersona int(11) NOT NULL AUTO_INCREMENT," +
				"  Nombre varchar(45) NOT NULL," +
				"  Telefono varchar(45) ," +
				"  Calle varchar(45) ," +
				"  Altura varchar(45) ," +
				"  Piso varchar(45) ," +
				"  Departamento varchar(45) ," +
				"  Email varchar(45) ," +
				"  fechaNacimiento varchar(45) ," +
				"  idLocalidad int(11)," +
				"  idEtiqueta int(11) ," +
				"" +
				"  PRIMARY KEY (idPersona)," +
				"  FOREIGN KEY (idLocalidad) REFERENCES Localidades(idLocalidad)," +
				"  FOREIGN KEY (idEtiqueta) REFERENCES Etiquetas(idEtiqueta)" +
				"" +
				");";
	}

	private String addExample(){

		return "INSERT INTO Personas(nombre, telefono,calle,altura,piso,departamento,email,fechaNacimiento) VALUES(\"Ejemplo\", \"1234567890\", \"Calle\", \"123\", \"1\", \"1\", \"mail@example.com\", \"2017-09-01\");";
	}






	/*	public void buildDefaultProperties(){
            Propiedades prop = new Propiedades();
            OutputStream output = null;

            try {

                output = new FileOutputStream("db.default");

                // set the properties value
                prop.store(output,"#Defaults:");
                prop.setProperty("jdbc.driver", "org.h2.Driver");
                prop.setProperty("jdbc.url", "jdbc:h2:~//agenda");
                prop.setProperty("jdbc.username", "root");
                prop.setProperty("jdbc.password", "root");

                // save properties to project root folder
                prop.store(output, null);

            } catch (IOException io) {
                io.printStackTrace();
            } finally {
                if (output != null) {
                    try {

                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }

        }
    */









	
}