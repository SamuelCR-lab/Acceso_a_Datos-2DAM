package Ejercicios2;

import java.sql.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/mydb";
		String usuario = "root";
		String password = "cfgs";
		try {
			//1 Cargar el drive de la bd
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2 Crear un conexion
			Connection conexion = DriverManager.getConnection(url,usuario,password);
			System.out.println("Se ha conectado a la base de datos.");
			
			/*3 Crear un Statement
			Statement sentencia = conexion.createStatement();
			String Consulta = "select * from usuario where idUsuario=1 or 1=1";//Este formato de sql permite las inyecciones de SQL
			ResultSet resultado = sentencia.executeQuery(Consulta);*/
			
			//3. Crear un PreparedStatement
			String consulta = "Select * from usuario where idUsuario=? or nombre=?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			int numero =1;
			sentencia.setInt(1, numero);
			sentencia.setString(2, "Manolo");
			ResultSet resultado = sentencia.executeQuery();
			
			//Mostrar los resultados
			while (resultado.next()){
				int idUsuario = resultado.getInt("idUsuario");
				String nombre = resultado.getNString("nombre");
				Date fecha = resultado.getDate("Fecha de nacimiento");
				String genero = resultado.getString("Genero");
				System.out.println("Usuario : "+idUsuario+", Nombre: " +nombre+ ", Fecha de nacimiento: "+fecha+", Genero: "+genero);
				
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
