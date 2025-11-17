package Ejercicios2;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Nba2 {
	static Scanner entrada = new Scanner(System.in);
	static String url = "jdbc:mysql://localhost:3306/nba";
	static String usuario = "root";
	static String password = "cfgs";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		NombreNBA();
		Peso();
		Equipos();
		BorrarJugadores(url,usuario,password);
	}
	public static void NombreNBA() {
		try {
			//1 Cargar el drive de la bd
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2 Crear un conexion
			Connection conexion = DriverManager.getConnection(url,usuario,password);
			
			/*3 Crear un Statement
			Statement sentencia = conexion.createStatement();
			String Consulta = "select * from usuario where idUsuario=1 or 1=1";//Este formato de sql permite las inyecciones de SQL
			ResultSet resultado = sentencia.executeQuery(Consulta);*/
			
			//3. Crear un PreparedStatement
			String consulta = "Select * from jugadores where Nombre like ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			System.out.print("Introduce la inicial del nombre a buscar: ");
			String parametro = entrada.nextLine();
			sentencia.setString(1,parametro+"%");
			ResultSet resultado = sentencia.executeQuery();//ResultSet es una Coleccion de datos y el sentencia.executeQuery es el que ejecuta la sentenciaSQL
			System.out.println("Nombre de todos los jugadores que comienzan por la letra "+parametro+".\n");
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
	public static void Peso() {
		try {
			//1 Cargar el drive de la bd
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2 Crear un conexion
			Connection conexion = DriverManager.getConnection(url,usuario,password);
			
			/*3 Crear un Statement
			Statement sentencia = conexion.createStatement();
			String Consulta = "select * from usuario where idUsuario=1 or 1=1";//Este formato de sql permite las inyecciones de SQL
			ResultSet resultado = sentencia.executeQuery(Consulta);*/
			
			//3. Crear un PreparedStatement
			String consulta = "Select codigo from jugadores order by codigo desc limit 1";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			
			ResultSet resultado = sentencia.executeQuery();
			
			//Mostrar los resultados
			while (resultado.next()){
				int idJugador = resultado.getInt("avg(peso)");
				System.out.println("\nEl jugador de peso medio: "+idJugador);
				
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void Equipos() {
		try {
			//1 Cargar el drive de la bd
			Class.forName("com.mysql.cj.jdbc.Driver");
			//2 Crear un conexion
			Connection conexion = DriverManager.getConnection(url,usuario,password);
			
			/*3 Crear un Statement
			Statement sentencia = conexion.createStatement();
			String Consulta = "select * from usuario where idUsuario=1 or 1=1";//Este formato de sql permite las inyecciones de SQL
			ResultSet resultado = sentencia.executeQuery(Consulta);*/
			System.out.println("\nTodos los equipos de la NBA");
			//3. Crear un PreparedStatement
			String consulta = "Select * from equipos";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery();
			System.out.println("");
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
			
			/*3 Crear un Statement
			Statement sentencia = conexion.createStatement();
			String Consulta = "select * from usuario where idUsuario=1 or 1=1";//Este formato de sql permite las inyecciones de SQL
			ResultSet resultado = sentencia.executeQuery(Consulta);*/
			
			//3. Crear un PreparedStatement
			String consulta = "Select * from jugadores where Nombre_Equipo = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			System.out.print("\nIntroduce el nombre del equipo a buscar: ");
			String parametro = entrada.nextLine();
			sentencia.setString(1,parametro);
			ResultSet resultado = sentencia.executeQuery();
			System.out.println("");
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
	public static void BorrarJugadores(String url,String usuario,String password)  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conexion = DriverManager.getConnection(url,usuario,password);
			String consulta = "Select * from jugadores where Nombre_Equipo = ?";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			System.out.print("\nIntroduce el nombre del equipo a buscar del que quieras dar baja el jugador: ");
			String parametro = entrada.nextLine();
			sentencia.setString(1,parametro);
			ResultSet resultado = sentencia.executeQuery();
			System.out.println("");
			while(resultado.next()) {
				int idJugador = resultado.getInt("codigo");
				String nombre = resultado.getString("Nombre");
				String procedencia = resultado.getString("Procedencia");
				String altura = resultado.getString("Altura");
				int peso = resultado.getInt("Peso");
				String posicion = resultado.getString("Posicion");
				String equipo  = resultado.getString("Nombre_equipo");
				System.out.println("Codigo jugador: "+idJugador+", Nombre: " +nombre+ ", Procedencia: "+procedencia+", Altura: "+altura+", Peso: "+peso+", Posicion: "+posicion+", Nombre del equipo: "+equipo);
				
			}
			String consulta = "Select * from jugadores where Nombre_Equipo = ?";
			
			System.out.print("\nIntroduce el nombre del jugador a borrar: ");
			String parametro2 = entrada.nextLine();
			sentencia.setString(1,parametro2);
			ResultSet resultado = sentencia.executeQuery();
			
		} catch (ClassNotFoundException| SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2 Crear un conexion
		
		
	}
public static void FicharJugadore(String url,String usuario,String password) {
		
	}
public static void InsertarPartido(String url,String usuario,String password) {
	
}
}
