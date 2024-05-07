package default_package;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	
	    private static final String URL = "jdbc:mysql://localhost:3307/instituto";
	    private static final String USUARIO = "redis";
	    private static final String CONTRASEÑA = "contrasea_redis";

	    private static ConnectionDB instancia;
	    private Connection conexion;

	    private ConnectionDB() throws SQLException {
	        conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
	    }

	    public static synchronized ConnectionDB obtenerInstancia() throws SQLException {
	        if (instancia == null) {
	            instancia = new ConnectionDB();
	        }
	        return instancia;
	    }

	    public Connection obtenerConexion() {
	    	System.out.println("conectado");
	        return conexion;
	    }
	}