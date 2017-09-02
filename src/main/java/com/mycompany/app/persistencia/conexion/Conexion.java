package com.mycompany.app.persistencia.conexion;

import java.io.*;
import java.sql.*;
import java.util.Properties;


public class Conexion
{
	private static Conexion instancia;
	private static Connection conexion;
	static private String username;
	static private String password;
	static private String url;
	static private String driver;
	static private boolean firstRun;
	static private String createTables="DROP DATABASE agenda;\n" +
			"CREATE DATABASE agenda;\n" +
			"USE agenda;\n" +
			"CREATE TABLE Localidades\n" +
			"(  idLocalidad int(11) NOT NULL AUTO_INCREMENT,\n" +
			"   nombre_Localidad varchar(45) ,\n" +
			"   PRIMARY KEY (idLocalidad)\n" +
			");\n" +
			"CREATE TABLE Etiquetas(\n" +
			"    idEtiqueta int(11) NOT NULL AUTO_INCREMENT,\n" +
			"\tnombre_Etiqueta varchar(45) ,\n" +
			"    PRIMARY KEY (idEtiqueta)\n" +
			");\n" +
			"\n" +
			"CREATE TABLE Personas\n" +
			"(\n" +
			"    idPersona int(11) NOT NULL AUTO_INCREMENT,\n" +
			"  Nombre varchar(45) NOT NULL,\n" +
			"  Telefono varchar(45) ,\n" +
			"  Calle varchar(45) ,\n" +
			"  Altura varchar(45) ,\n" +
			"  Piso varchar(45) ,\n" +
			"  Departamento varchar(45) ,\n" +
			"  Email varchar(45) ,\n" +
			"  fechaNacimiento varchar(45) ,\n" +
			"  idLocalidad int(11),\n" +
			"  idEtiqueta int(11) ,\n" +
			"\n" +
			"  PRIMARY KEY (idPersona),\n" +
			"  FOREIGN KEY (idLocalidad) REFERENCES Localidades(idLocalidad),\n" +
			"  FOREIGN KEY (idEtiqueta) REFERENCES Etiquetas(idEtiqueta)\n" +
			"\n" +
			");\n";



	private Properties getProperties(){

		Properties props = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream("db.properties");
			props.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			//buildDefaultProperties();
			buildMYSQLProperties();
			System.out.println("error al cargar db.properties, restaurando propiedades de la BD por defecto...");
			/*try {
				in = new FileInputStream("db.default");
				defaultDB=true;
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				System.out.println("error al restaurar propiedades de la BD por defect, problema de permisos?");
			}*/
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("error desconocido");
		}

		return props;
	}

	public void setProperties(String jdbcurl,
							  String jdbcusername,
							  String jdbcpassword)
	{
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("db.properties");

			// set the properties value
			prop.store(output,"#Defaults:");
			prop.setProperty("jdbc.driver", "com.mysql.jdbc.Driver");
			prop.setProperty("jdbc.url", jdbcurl);
			prop.setProperty("jdbc.username",jdbcusername);
			prop.setProperty("jdbc.password", jdbcpassword);

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



	private Conexion()
	{

		Properties props=getProperties();
        driver = props.getProperty("jdbc.driver");
		url = props.getProperty("jdbc.url");
		//url = props.getProperty("jdbc.url")+"/agenda";
		username = props.getProperty("jdbc.username");
		password =props.getProperty("jdbc.password");



	}

	public Conexion crear(){
		if((driver==null)||(url==null)||(username==null)||(password==null))
			return null;


		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}


		conexion = null;

			try {


				//TODO

			conexion = DriverManager.getConnection(url, username, password);



		} catch (SQLException e) {
			System.out.println("TODO MOSTRAR ERROR Y LANZAR CONFIGURACION DB");
			//TODO MOSTRAR ERROR Y LANZAR CONFIGURACION DB
			e.printStackTrace();
		}

		return this;
	}




/*	public void buildDefaultProperties(){
		Properties prop = new Properties();
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
	public void buildMYSQLProperties(){
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("db.default");

			// set the properties value
			prop.store(output,"#Defaults:");
			prop.setProperty("jdbc.driver", "com.mysql.jdbc.Driver");

			prop.setProperty("jdbc.url", "jdbc:mysql://tp1server.ddns.net:3306/agenda");

			prop.setProperty("jdbc.username", "user");

			prop.setProperty("jdbc.password", "mypass");

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


	public Conexion crearmySql()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conexion = DriverManager.getConnection("jdbc:mysql://tp1server.ddns.net:3306/agenda","user","mypass");
			System.out.println("Conexion exitosa");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("Conexion fallida");
		}
		return this;

	}

	public boolean createTables(){


		Conexion conexion=Conexion.getConexion();

		Connection conn=conexion.getSQLConexion();

		Statement statement;
		try {
			statement=conn.createStatement();
			statement.execute(createTables);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
}


	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{

			instancia = new Conexion();
			instancia.crear();


		}
		return instancia;
	}

	public Connection getSQLConexion() 
	{
		return conexion;
	}
	
	public void cerrarConexion()
	{

		instancia = null;
	}
}
