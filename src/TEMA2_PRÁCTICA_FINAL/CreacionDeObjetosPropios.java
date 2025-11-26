package TEMA2_PRÁCTICA_FINAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreacionDeObjetosPropios {
	static String url = "jdbc:mysql://localhost:3306/jugueteria";
	static String usuario = "root";
	static String password = "casaSQL";
	
	
	private static void creacionDeBasedeDatos(String tabla) {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url,usuario,password);
			System.out.println("Se ha conectado a la base de datos.");
			String consulta = "INSERT INTO "+tabla+" VALUES (?, ?, ?, ?, ?);";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			
			
			
		} catch (ClassNotFoundException|SQLException e) {
			
			e.getStackTrace();
		}
		//2 Crear un conexion
		
	}
	
	
	
	
	
	
	
	public static void main(String[] args) {
		
		creacionDeBasedeDatos("hola");
	}

}
