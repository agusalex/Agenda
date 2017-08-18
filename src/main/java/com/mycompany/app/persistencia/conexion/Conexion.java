package com.mycompany.app.persistencia.conexion;

import java.sql.*;



public class Conexion 
{
	public static Conexion instancia;
	private Connection conexion;




	public Conexion()

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
	}


	public static void initDB(){
		String initDB="CREATE TABLE `personas` \n" +
				"(\n" +
				"  `idPersona` int(11) NOT NULL AUTO_INCREMENT,\n" +
				"  `Nombre` varchar(45) NOT NULL,\n" +
				"  `Telefono` varchar(20) NOT NULL,\n" +
				"  PRIMARY KEY (`idPersona`)\n" +
				");";


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
			initDB();
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
