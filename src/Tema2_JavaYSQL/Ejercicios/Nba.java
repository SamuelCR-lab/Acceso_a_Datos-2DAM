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
		/*NombreNBA(url,usuario,password);
		Peso(url,usuario,password);
		Equipos(url,usuario,password);*/
		InsertarJugadores(url,usuario,password);
	}
	public static void NombreNBA(String url,String usuario,String password) {
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
	public static void Peso(String url,String usuario,String password) {
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
			String consulta2 = "Select avg(peso) from jugadores";
			PreparedStatement sentencia2 = conexion.prepareStatement(consulta2);
			ResultSet resultado = sentencia.executeQuery();
			ResultSet resultado1 = sentencia2.executeQuery();
			//Mostrar los resultados
			while(resultado1.next()&&resultado.next()) {
				int PesoAverage = resultado.getInt("avg(peso)");
				System.out.println("\nEl peso medio de los jugadores: "+PesoAverage);
				int idJugador = resultado.getInt("codigo");
				System.out.println("\nEl jugador con el peso medio, es el jugador del ID: "+idJugador);
				
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

public static void InsertarJugadores(String url,String usuario,String password) {
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
		String consulta = "INSERT INTO jugadores VALUES (?,?,?,?,?,?,?);";
		PreparedStatement sentencia = conexion.prepareStatement(consulta);
		System.out.print("\nIntroduce el número del jugador a insertar: ");
		int parametroCodigo = entrada.nextInt();
		entrada.nextLine();
		System.out.print("\nIntroduce el nombre del equipo a insertar: ");
		String parametroEquipo = entrada.nextLine();
		System.out.print("\nIntroduce el nombre del jugador a insertar: ");
		String parametroNombre = entrada.nextLine();
		System.out.print("\nIntroduce la procedencia del jugador a insertar: ");
		String parametroProcedencia = entrada.nextLine();
		System.out.print("\nIntroduce el altura del jugador a insertar: ");
		String parametroAltura = entrada.nextLine();
		System.out.print("\nIntroduce el peso del jugador a insertar: ");
		int parametroPeso = entrada.nextInt();
		System.out.print("\nIntroduce el posicion del jugador a insertar: ");
		String parametroPosicion = entrada.nextLine();
		entrada.next();
		String consulta2 = "SELECT * FROM jugadores;";
		PreparedStatement sentencia2 = conexion.prepareStatement(consulta2);
		ResultSet resultado1 = sentencia2.executeQuery();
		


		sentencia.setInt(1,parametroCodigo);
		sentencia.setString(2,parametroNombre);
		sentencia.setString(3,parametroProcedencia);
		sentencia.setString(4,parametroAltura);
		sentencia.setInt(5,parametroPeso);
		sentencia.setString(6,parametroPosicion);
		sentencia.setString(7,parametroEquipo);

		//Mostrar los resultados
		int resultado = sentencia.executeUpdate();
		if (resultado > 0) {	
		System.out.println("Codigo jugador: "+parametroCodigo+", Nombre: " +parametroNombre+ ", Procedencia: "+parametroProcedencia+", Altura: "+parametroAltura+", Peso: "+parametroPeso+", Posicion: "+parametroPosicion+", Nombre del equipo: "+parametroEquipo);
		}else {
			System.out.println("Espabila");
		}	
				
	}catch(Exception e) {
		e.printStackTrace();
	}
}
}
