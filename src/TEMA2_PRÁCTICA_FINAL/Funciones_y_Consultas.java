package TEMA2_PRÁCTICA_FINAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Funciones_y_Consultas {
	private static String url = "jdbc:mysql://localhost:3306/jugueteria";
	private static String usuario = "root";
	private static String passwordCASA = "casaSQL";
	//private static String passwordINSTI = "cfgs";
	private static Scanner entrada = new Scanner(System.in);
	public static String nombreUsuario;
	
	
	public static Connection comprobarConexion() {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url,usuario,passwordCASA);
			System.out.println("Se ha conectado a la base de datos.");
			return conexion;
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		
		return conexion;
	
	}
	
	public static void updateDatos(String tabla,String columna) {
		
		try {
			Connection conexion = comprobarConexion();
			
			/*3 Crear un Statement
			Statement sentencia = conexion.createStatement();
			String Consulta = "select * from usuario where idUsuario=1 or 1=1";//Este formato de sql permite las inyecciones de SQL
			ResultSet resultado = sentencia.executeQuery(Consulta);*/
			
			//3. Crear un PreparedStatement
			String consulta = "update "+tabla+" set "+columna+" = 4 WHERE idEMPLEADO = ?;";
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
				
				
			}
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static int ComprobacionBaseDatos() {
		try {
			Connection conexion = comprobarConexion();
			String consultaEMP = "SELECT COUNT (*) as Total FROM empleado";
			String consultaZONA = "SELECT COUNT (*) as Total FROM zona";
			String consultaJUG = "SELECT COUNT (*) as Total FROM juguete";
			String consultaSTAND = "SELECT COUNT (*) as Total FROM stand";
			String consultaSTOCK = "SELECT COUNT (*) as Total FROM stock";
			PreparedStatement sentenciaEMP = conexion.prepareStatement(consultaEMP);
			PreparedStatement sentenciaZONA = conexion.prepareStatement(consultaZONA);
			PreparedStatement sentenciaJUGUETES = conexion.prepareStatement(consultaJUG);
			PreparedStatement sentenciaSTAND = conexion.prepareStatement(consultaSTAND);
			PreparedStatement sentenciaSTOCK = conexion.prepareStatement(consultaSTOCK);
			ResultSet resultado = sentenciaEMP.executeQuery();
			ResultSet resultado1 = sentenciaZONA.executeQuery();
			ResultSet resultado2 = sentenciaJUGUETES.executeQuery();
			ResultSet resultado3 = sentenciaSTAND.executeQuery();
			ResultSet resultado4 = sentenciaSTOCK.executeQuery();
			if(resultado.next()&&resultado1.next()&&resultado2.next()&&resultado3.next()&&resultado4.next()) {
				int datosEmpleados = resultado.getInt("Total");
				int datosZona = resultado1.getInt("Total");
				int datosJuguetes = resultado2.getInt("Total");
				int datosStand = resultado3.getInt("Total");
				int datosStock = resultado4.getInt("Total");
				if((datosEmpleados==0)&&(datosZona==0)&&(datosJuguetes==0)&&(datosStand==0)&&(datosStock==0)) {
					cargarDatosIniciales();
				}else {
					if(datosEmpleados==0) 
						subidaDatosEmpleadosIniciales();
					if(datosZona==0) 
						subidaDatosZonaIniciales();
					if (datosJuguetes==0)
						subidaDatosJuguetesIniciales();
					if (datosStand==0)
						subidaDatosStandIniciales();
					if (datosStock==0)
						subidaDatosStockIniciales();
				}
			}
			System.out.println();
		
			
		}catch(SQLException e){
			e.getStackTrace();
		}
		return 0;
		
	}
	private static int subidaDatosEmpleadosIniciales() {
		int funciona=0;
		try {
			Connection conexion = comprobarConexion();
			String insercionEmpleados = "INSERT INTO `empleado` (`Nombre`, `Cargo`, `Fecha_ingreso`) VALUES ('Juan Pérez', 'jefe', '2022-01-15'),('Ana Gómez', 'cajero', '2023-03-10'),('Carlos Ruiz', 'cajero', '2023-05-22'),('Lucía Fernández', 'cajero', '2023-08-01');";
			PreparedStatement sentenciaEMP;
			sentenciaEMP = conexion.prepareStatement(insercionEmpleados);
			int comprobacionEmpleados = sentenciaEMP.executeUpdate(insercionEmpleados);
			if ((comprobacionEmpleados > 0)) {
				funciona++;
				return funciona;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funciona;
		
	}
	private static int subidaDatosZonaIniciales() {
		int funciona=0;
		try {
			Connection conexion = comprobarConexion();
			String insercionZona = "INSERT INTO `zona` (`idzona`, `Nombre`, `Descripcion`) VALUES (1, 'Infantil', 'Juguetes para 0-3 años'),(2, 'Construcción', 'Legos y bloques'),(3, 'Juegos de Mesa', 'Puzzles y cartas'),(4, 'Exterior', 'Deportes y aire libre');";
			PreparedStatement sentenciaZONA = conexion.prepareStatement(insercionZona);
			int comprobacionZona = sentenciaZONA.executeUpdate(insercionZona);
			if ((comprobacionZona > 0)) {
				funciona++;
				return funciona;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funciona;
	}
	private static int subidaDatosJuguetesIniciales() {
		int funciona=0;
		try {
			Connection conexion = comprobarConexion();
			String insercionJuguetes = "INSERT INTO `juguete` (`idJuguete`, `Nombre`, `Descripcion`, `Precio`, `Cantidad_stock`) VALUES (101, 'Bloques Básicos', 'Set de 50 piezas', 15.50, 100),(102, 'Muñeca Clásica', 'Muñeca de trapo', 12.00, 50),(103, 'Balón Fútbol', 'Tamaño oficial 5', 19.99, 30),(104, 'Monopoly', 'Edición Clásica', 25.00, 40),(105, 'Coche RC', 'Coche teledirigido rojo', 35.50, 20);";
			PreparedStatement sentenciaJUGUETE = conexion.prepareStatement(insercionJuguetes);
			int comprobacionJuguetes = sentenciaJUGUETE.executeUpdate(insercionJuguetes);
			if ((comprobacionJuguetes > 0)) {
				funciona++;
				return funciona;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funciona;
	}
	private static int subidaDatosStandIniciales() {
		int funciona=0;
		try {
			Connection conexion = comprobarConexion();
			String insercionStand = "INSERT INTO `stand` (`idStand`, `ZONA_idzona`, `Nombre`, `Descripcion`) VALUES (1, 1, 'Stand Bebés', 'Artículos blandos'),(1, 2, 'Stand Lego StarWars', 'Ediciones especiales'),(2, 2, 'Stand Bloques', 'Piezas sueltas'),(1, 3, 'Stand Estrategia', 'Juegos largos'),(1, 4, 'Stand Pelotas', 'Todo tipo de balones');";
			PreparedStatement sentenciaStand = conexion.prepareStatement(insercionStand);
			int comprobacionSTAND = sentenciaStand.executeUpdate(insercionStand);
			if ((comprobacionSTAND > 0)) {
				funciona++;
				return funciona;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funciona;
	}
	private static int subidaDatosStockIniciales() {
		int funciona=0;
		try {
			Connection conexion = comprobarConexion();
			String insercionStock = "INSERT INTO `stock` (`STAND_idStand`, `STAND_ZONA_idzona`, `JUGUETE_idJuguete`, `CANTIDAD`) VALUES (1, 1, 101, '10'),(2, 2, 102, '20'),(1, 4, 103, '15'),(1, 3, 104, '5'),(1, 1, 102, '12');";
			PreparedStatement sentenciaSTOCK = conexion.prepareStatement(insercionStock);
			int comprobacionStock =sentenciaSTOCK.executeUpdate(insercionStock);
			if ((comprobacionStock > 0)) {
				funciona++;
				return funciona;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return funciona;
	}
	public static void cargarDatosIniciales() {
			int comprobacionEmpleados = subidaDatosEmpleadosIniciales();
			int comprobacionZona = subidaDatosZonaIniciales();
			int comprobacionJuguetes = subidaDatosJuguetesIniciales();
			int comprobacionStand =subidaDatosJuguetesIniciales();
			int comprobacionStock = subidaDatosJuguetesIniciales();
			if ((comprobacionEmpleados > 0)&&(comprobacionZona > 0)&&(comprobacionJuguetes > 0)&&(comprobacionStand > 0)&&(comprobacionStock > 0)) {
				System.out.println("Se han cargados todos los datos iniciales de la base de datos correctamente.");
			}else {
				System.out.println("No se han podido cargar todos los datos.");
			}
	}
	
	
	
	public static int obtencionCargoInicioSesion() {
		int cargo=0;
		try {
			Connection conexion = comprobarConexion();
			String consulta = "SELECT Cargo from empleado where idEMPLEADO = ?;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			System.out.println("Introduce el Id del empleado a buscar: ");
			int parametro = Jugueteria.controlDeErrores();
			sentencia.setInt(1,parametro);
			ResultSet resultado = sentencia.executeQuery();
			if (resultado.next()) {
				String respuesta = resultado.getString("Cargo");
				nombreUsuario = resultado.getString("Nombre");
				if (respuesta.equals("cajero")){
					cargo = 1;
					return cargo;
				}else {
					cargo=2;
					return cargo;
				}
			}
		}catch(SQLException e){
			e.getStackTrace();
		}

		return cargo;
	}
	
	
	
	
}
