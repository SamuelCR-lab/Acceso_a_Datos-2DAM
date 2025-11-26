package TEMA2_PRÁCTICA_FINAL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FuncionesConsultas {
	static String url = "jdbc:mysql://localhost:3306/jugueteria";
	static String usuario = "root";
	static String passwordCASA = "casaSQL";
	static String passwordINSTI = "cfgs";
	static Scanner entrada = new Scanner(System.in);
	
	public static Connection comprobarConexion() {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url,usuario,passwordCASA);
			System.out.println("Se ha conectado a la base de datos.");
			return conexion;
		} catch (ClassNotFoundException|SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conexion;
	
	}
	public static void update() {
		
		try {
			Connection conexion = comprobarConexion();
			
			/*3 Crear un Statement
			Statement sentencia = conexion.createStatement();
			String Consulta = "select * from usuario where idUsuario=1 or 1=1";//Este formato de sql permite las inyecciones de SQL
			ResultSet resultado = sentencia.executeQuery(Consulta);*/
			
			//3. Crear un PreparedStatement
			String consulta = "update empleado set idEMPLEADO = 4 WHERE idEMPLEADO = ;";
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
	
	
	public static void cargarDatosIniciales() {
		try {
			Connection conexion = comprobarConexion();
			String insercionEmpleados = "INSERT INTO `empleado` (`Nombre`, `Cargo`, `Fecha_ingreso`) VALUES ('Juan Pérez', 'jefe', '2022-01-15'),('Ana Gómez', 'cajero', '2023-03-10'),('Carlos Ruiz', 'cajero', '2023-05-22'),('Lucía Fernández', 'cajero', '2023-08-01');";
			String insercionZona = "INSERT INTO `zona` (`idzona`, `Nombre`, `Descripcion`) VALUES (1, 'Infantil', 'Juguetes para 0-3 años'),(2, 'Construcción', 'Legos y bloques'),(3, 'Juegos de Mesa', 'Puzzles y cartas'),(4, 'Exterior', 'Deportes y aire libre');";
			String insercionJuguetes = "INSERT INTO `juguete` (`idJuguete`, `Nombre`, `Descripcion`, `Precio`, `Cantidad_stock`) VALUES (101, 'Bloques Básicos', 'Set de 50 piezas', 15.50, 100),(102, 'Muñeca Clásica', 'Muñeca de trapo', 12.00, 50),(103, 'Balón Fútbol', 'Tamaño oficial 5', 19.99, 30),(104, 'Monopoly', 'Edición Clásica', 25.00, 40),(105, 'Coche RC', 'Coche teledirigido rojo', 35.50, 20);";
			String insercionStand = "INSERT INTO `stand` (`idStand`, `ZONA_idzona`, `Nombre`, `Descripcion`) VALUES (1, 1, 'Stand Bebés', 'Artículos blandos'),(1, 2, 'Stand Lego StarWars', 'Ediciones especiales'),(2, 2, 'Stand Bloques', 'Piezas sueltas'),(1, 3, 'Stand Estrategia', 'Juegos largos'),(1, 4, 'Stand Pelotas', 'Todo tipo de balones');";
			String insercionStock = "INSERT INTO `stock` (`STAND_idStand`, `STAND_ZONA_idzona`, `JUGUETE_idJuguete`, `CANTIDAD`) VALUES (1, 1, 101, '10'),(2, 2, 102, '20'),(1, 4, 103, '15'),(1, 3, 104, '5'),(1, 1, 102, '12');";
			
			PreparedStatement sentenciaEMP = conexion.prepareStatement(insercionEmpleados);
			PreparedStatement sentenciaZONA = conexion.prepareStatement(insercionZona);
			PreparedStatement sentenciaJUGUETES = conexion.prepareStatement(insercionJuguetes);
			PreparedStatement sentenciaSTAND = conexion.prepareStatement(insercionStand);
			PreparedStatement sentenciaSTOCK = conexion.prepareStatement(insercionStock);
			
			sentenciaEMP.executeUpdate(insercionEmpleados);
			sentenciaZONA.executeUpdate(insercionZona);
			sentenciaJUGUETES.executeUpdate(insercionJuguetes);
			sentenciaSTAND.executeUpdate(insercionStand);
			sentenciaSTOCK.executeUpdate(insercionStock);
			
		}catch (SQLException e) {
			e.getStackTrace();
		}
	}
	public static void obtencionCargoInicioSesion() {
		try {
			Connection conexion = comprobarConexion();
			String consulta = "SELECT Cargo from empleado where idEMPLEADO = ?;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			System.out.println("Introduce el Id del empleado a buscar: ");
			int parametro = Jugueteria.controlDeErrores();
			sentencia.setString(1,parametro+"%");
			
		}catch(SQLException e){
			e.getStackTrace();
		}
		
	}
	
	
	
	
}
