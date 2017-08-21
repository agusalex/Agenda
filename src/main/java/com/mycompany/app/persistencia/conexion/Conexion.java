package com.mycompany.app.persistencia.conexion;

import java.io.*;
import java.sql.*;
import java.util.Properties;


public class Conexion
{
	private static Conexion instancia;
	private Connection conexion;
	private static String initDB=
			"CREATE TABLE `personas` \n" +
			"(\n" +
			"  `idPersona` int(11) NOT NULL AUTO_INCREMENT,\n" +
			"  `Nombre` varchar(45) NOT NULL,\n" +
			"  `Telefono` varchar(20) NOT NULL,\n" +
			"  PRIMARY KEY (`idPersona`)\n" +
			");\n";
	static private String username;
	static private String password;
	static private String url;
	static private String driver;
	static boolean defaultDB=false;


	private Conexion()
	{
		Properties props = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream("db.properties");
		} catch (FileNotFoundException e) {
			buildDefaultProperties();
			System.out.println("error al cargar db.properties, restaurando propiedades de la BD por defecto...");
			try {
				in = new FileInputStream("db.default");
				defaultDB=true;
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
				System.out.println("error al restaurar propiedades de la BD por defect, problema de permisos?");
			}
		}
		try {
			props.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

        driver = props.getProperty("jdbc.driver");
		url = props.getProperty("jdbc.url");
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
			conexion = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return this;
	}

	public void buildDefaultProperties(){
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

	public Conexion crearH2()

	{

		try {
			Class.forName("org.h2.Driver");
			String url = "jdbc:h2:" + "~//agenda";
			String user = "root";
			String password = "root";
			conexion = null;

			conexion = DriverManager.getConnection(url, user, password);


		}
		catch(Exception e)
		{
			System.out.println(e.toString() +"\n Conexion H2 fallida");
		}

		initH2DB();
		return this;
	}



	private static void initH2DB(){
		String initDBH2=initDB;

		Conexion conexion=Conexion.getConexion();

		Connection conn=conexion.getSQLConexion();

		Statement statement;
		try {
			statement=conn.createStatement();
			statement.execute(initDB);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	
	public static Conexion getConexion()   
	{								
		if(instancia == null)
		{

			instancia = new Conexion();
			instancia.crear();
			if(defaultDB)
				initH2DB();

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
