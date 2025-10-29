package Ejercicios;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Nba {
	static Scanner entrada = new Scanner(System.in);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "jdbc:mysql://localhost:3306/nba";
		String usuario = "root";
		String password = "cfgs";
		NombreNBA(url,usuario,password);
		Peso(url,usuario,password);
		Equipos(url,usuario,password);
	}
	public static void NombreNBA(String url,String usuario,String password) {
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
			String consulta = "Select * from jugadores where Nombre like ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			System.out.println("Introduce la inicial del nombre a buscar.");
			String parametro = entrada.nextLine();
			sentencia.setString(1,parametro+"%");
			ResultSet resultado = sentencia.executeQuery();
			//Mostrar los resultados
			while (resultado.next()){
				int idJugador = resultado.getInt("codigo");
				String nombre = resultado.getString("Nombre");
				String procedencia = resultado.getString("Procedencia");
				String altura = resultado.getString("Altura");
				int peso = resultado.getInt("Peso");
				String posicion = resultado.getString("Posicion");
				String equipo  = resultado.getString("Nombre_equipo");
				System.out.println("Codigo jugador: "+idJugador+", Nombre: " +nombre+ ", Procedencia: "+procedencia+", Altura: "+altura+", Peso: "+peso+", Posicion: "+posicion+", Nombre del equipo: "+equipo);
				
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void Peso(String url,String usuario,String password) {
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
			String consulta = "Select avg(peso) from jugadores";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			
			ResultSet resultado = sentencia.executeQuery();
			
			//Mostrar los resultados
			while (resultado.next()){
				int idJugador = resultado.getInt("avg(peso)");
				System.out.println("Peso medio de los jugadores: "+idJugador);
				
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void Equipos(String url,String usuario,String password) {
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
			String consulta = "Select * from equipos";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery();
			
			//Mostrar los resultados
			while (resultado.next()){
				String nombre = resultado.getString("Nombre");
				String ciudad = resultado.getString("Ciudad");
				String conferencia = resultado.getString("Conferencia");
				String division = resultado.getString("Division");
				System.out.println("Nombre del equipo: "+nombre+", Ciudad: "+ciudad+", Conferencia: "+conferencia+", Division: "+division);
				
			}
			MostrarJugadoresEquipo(url,usuario,password);
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void MostrarJugadoresEquipo(String url,String usuario,String password) {
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
			String consulta = "Select * from jugadores where Nombre_Equipo = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			System.out.println("Introduce el nombre del euipo a buscar: ");
			String parametro = entrada.nextLine();
			sentencia.setString(1,parametro);;
			ResultSet resultado = sentencia.executeQuery();
			
			//Mostrar los resultados
			while (resultado.next()){
				int idJugador = resultado.getInt("codigo");
				String nombre = resultado.getString("Nombre");
				String procedencia = resultado.getString("Procedencia");
				String altura = resultado.getString("Altura");
				int peso = resultado.getInt("Peso");
				String posicion = resultado.getString("Posicion");
				String equipo  = resultado.getString("Nombre_equipo");
				System.out.println("Codigo jugador: "+idJugador+", Nombre: " +nombre+ ", Procedencia: "+procedencia+", Altura: "+altura+", Peso: "+peso+", Posicion: "+posicion+", Nombre del equipo: "+equipo);
				
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
