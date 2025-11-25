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
		try {
			Connection Conectado = conexion_BBDD();
			boolean bandera = false;
			while(!bandera) {
				System.out.println("\n Menu de NBA"
						+ "1. Imprimir Jugadores por letra."
						+ "2. Peso medio de los jugadores."
						+ "3. Mostrar jugadores por equipos."
						+ "4. Insertar un jugador"
						+ "5. Borrar Jugadores"
						+ "6. Fichar jugador a equipo"
						+ "7. Insertar un partido"
						+ "8. Estadisticas de jugadores"
						+ "9. Salir");
				int opcion = entrada.nextInt();
				switch (opcion) {
				case 1:
					NombreNBA(Conectado);
					break;
				case 2:
					Peso(Conectado);
					break;
				case 3:
					Equipos(Conectado);
					break;
				case 4:
					
					break;
				case 5:
					BorrarJugadores(Conectado);
					break;
				case 6:
					BorrarJugadores(Conectado);
					break;
				case 7:
					BorrarJugadores(Conectado);
					break;
				case 8:
					BorrarJugadores(Conectado);
					break;
				case 9:
					System.out.println("Saliendo...");
					bandera = true;
					break;
				default :
					System.out.println("ERRO. Escribe ");
				}
			}
		} catch (ClassNotFoundException|SQLException  e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
	}
	public static void NombreNBA(Connection ConectadoBBDD) {
		try {
			//1 Cargar el drive de la bd

			
			/*3 Crear un Statement
			Statement sentencia = conexion.createStatement();
			String Consulta = "select * from usuario where idUsuario=1 or 1=1";//Este formato de sql permite las inyecciones de SQL
			ResultSet resultado = sentencia.executeQuery(Consulta);*/
			
			//3. Crear un PreparedStatement
			String consulta = "Select * from jugadores where Nombre like ?";
			PreparedStatement sentencia = ConectadoBBDD.prepareStatement(consulta);
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
	public static void Peso(Connection ConectadoBBDD) {
		try {
			//1 Cargar el drive de la bd
			
			/*3 Crear un Statement
			Statement sentencia = conexion.createStatement();
			String Consulta = "select * from usuario where idUsuario=1 or 1=1";//Este formato de sql permite las inyecciones de SQL
			ResultSet resultado = sentencia.executeQuery(Consulta);*/
			
			//3. Crear un PreparedStatement
			String consulta = "Select codigo from jugadores order by codigo desc limit 1";
			PreparedStatement sentencia = ConectadoBBDD.prepareStatement(consulta);
			
			ResultSet resultado = sentencia.executeQuery();
			
			//Mostrar los resultados
			while (resultado.next()){
				int idJugador = resultado.getInt("codigo");
				System.out.println("\nEl jugador de peso medio: "+idJugador);
				
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void Equipos(Connection ConectadoBBDD) {
		try {
			//1 Cargar el drive de la bd
			/*3 Crear un Statement
			Statement sentencia = conexion.createStatement();
			String Consulta = "select * from usuario where idUsuario=1 or 1=1";//Este formato de sql permite las inyecciones de SQL
			ResultSet resultado = sentencia.executeQuery(Consulta);*/
			System.out.println("\nTodos los equipos de la NBA");
			//3. Crear un PreparedStatement
			String consulta = "Select * from equipos";
			PreparedStatement sentencia = ConectadoBBDD.prepareStatement(consulta);
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
			MostrarJugadoresEquipo(ConectadoBBDD);
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void MostrarJugadoresEquipo(Connection ConectadoBBDD) {
		try {
			//1 Cargar el drive de la bd

			
			/*3 Crear un Statement
			Statement sentencia = conexion.createStatement();
			String Consulta = "select * from usuario where idUsuario=1 or 1=1";//Este formato de sql permite las inyecciones de SQL
			ResultSet resultado = sentencia.executeQuery(Consulta);*/
			
			//3. Crear un PreparedStatement
			String consulta = "Select * from jugadores where Nombre_Equipo = ?";
			PreparedStatement sentencia = ConectadoBBDD.prepareStatement(consulta);
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
	public static void BorrarJugadores(Connection ConectadoBBDD)  {
		try {
			int lineas = 0;
			String consulta = "Select * from jugadores";
			PreparedStatement sentencia = ConectadoBBDD.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery();
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
			System.out.print("\nIntroduce el id del jugador a borrar: ");
			int parametro1 = entrada.nextInt();
			String consulta2 = "Select * from estadisticas where jugador = ?";
			PreparedStatement sentencia1 = ConectadoBBDD.prepareStatement(consulta2);
			sentencia1.setInt(1,parametro1);
			if(sentencia1.execute()) {
				String consultaBorrado = "delete from estadisticas where jugador = ?";
				PreparedStatement sentencia2 = ConectadoBBDD.prepareStatement(consultaBorrado);
				sentencia2.setInt(1,parametro1);
				lineas= sentencia2.executeUpdate();
				sentencia2.close();
			}
			String consulta3 = "delete from jugadores where codigo = ?";
			PreparedStatement sentencia3 = ConectadoBBDD.prepareStatement(consulta3);
			sentencia3.setInt(1,parametro1);
			lineas=sentencia3.executeUpdate();
			sentencia3.close();
			if(lineas != 1) {
				System.out.println("El jugador no ha sido eliminado ");
			}
			System.out.println("El jugador ha sido eliminado ");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2 Crear un conexion
		
		
	}
public static Connection conexion_BBDD() throws SQLException, ClassNotFoundException{
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conexion = DriverManager.getConnection(url,usuario,password);
	System.out.println("Se ha realizado la conexion");
	return conexion;
	}
public static void InsertarPartido(String url,String usuario,String password) {
	
}
}
