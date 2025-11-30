package TEMA2_PRÁCTICA_FINAL;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Funciones_y_Consultas {
	private static String url = "jdbc:mysql://localhost:3306/jugueteria";
	private static String usuario = "root";
	private static String passwordCASA = "casaSQL";
	//private static String passwordINSTI = "cfgs";
	private static Scanner entrada = new Scanner(System.in);
	public static String nombreUsuario;
	public static int idEmpleadoVenta;
	
	
	public static Connection comprobarConexion() {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url,usuario,passwordCASA);
			//System.out.println("Se ha conectado a la base de datos.");
			return conexion;
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		
		return conexion;
	
	}
	public static void registroJugueteNuevo(Juguete jugueteN) {
		boolean bandera = false,banderaZona2=false;
		int comprobacionEnStock;
		String insercionJugueteEnStock;
		PreparedStatement sentencia2;
		try {
			Connection conexion = comprobarConexion();
			String insercionJuguete = "INSERT INTO `juguete` (`idJuguete`, `Nombre`, `Descripcion`, `Precio`, `Cantidad_stock`,`Categoria`) VALUES (?,?,?,?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(insercionJuguete);
			sentencia.setInt(1,jugueteN.iD_Juguete);
			sentencia.setString(2,jugueteN.nombre);
			sentencia.setString(3,jugueteN.descripcion);
			sentencia.setDouble(4,jugueteN.precio);
			sentencia.setInt(5,jugueteN.cantidad);
			sentencia.setString(6, jugueteN.categoriaENUM.name());
			sentencia.executeUpdate();
			System.out.print("Introduce una zona en la que quieres que se guarde este juguete, Infantil (1), Construcción (2), Juegos de Mesa (3), Exterior (4): ");
			do {
				int opcion = Jugueteria.controlDeErroresInt();
				switch(opcion) {
					case 1:
						insercionJugueteEnStock = "INSERT INTO `stock` (`STAND_idStand`, `STAND_ZONA_idzona`, `JUGUETE_idJuguete`, `CANTIDAD`) VALUES (?,?,?,?)";
						sentencia2 = conexion.prepareStatement(insercionJugueteEnStock);
						sentencia2.setInt(1,1);//solo hay un stand en la zona 1 que es de cosas de bebes
						sentencia2.setInt(2,opcion);
						sentencia2.setInt(3,jugueteN.iD_Juguete);
						sentencia2.setInt(4,jugueteN.cantidad);
						comprobacionEnStock =sentencia.executeUpdate();
						if ((comprobacionEnStock > 0)) {
							System.out.println("Se ha agregado el juguete de forma correcta.");
							mostrarJuguetes();
						}
						bandera = true;
						break;
					case 2:
						insercionJugueteEnStock = "INSERT INTO `stock` (`STAND_idStand`, `STAND_ZONA_idzona`, `JUGUETE_idJuguete`, `CANTIDAD`) VALUES (?,?,?,?)";
						sentencia2 = conexion.prepareStatement(insercionJugueteEnStock);
						System.out.println("¿En cual stand quieres agregar el Juguete? Stand Lego StarWars (1) o Stand Bloques (2)");
						do {
							int eleccion = Jugueteria.controlDeErroresInt();
								switch(eleccion) {
									case 1:
										sentencia2.setInt(1,eleccion);
										sentencia2.setInt(2,opcion);
										sentencia2.setInt(3,jugueteN.iD_Juguete);
										sentencia2.setInt(4,jugueteN.cantidad);
										comprobacionEnStock =sentencia.executeUpdate();
										if ((comprobacionEnStock > 0)) {
											System.out.println("Se ha agregado el juguete de forma correcta.");
											mostrarJuguetes();
										}
										banderaZona2 = true;
										break;
									case 2:
										sentencia2.setInt(1,eleccion);
										sentencia2.setInt(2,opcion);
										sentencia2.setInt(3,jugueteN.iD_Juguete);
										sentencia2.setInt(4,jugueteN.cantidad);
										comprobacionEnStock =sentencia.executeUpdate();
										if ((comprobacionEnStock > 0)) {
											System.out.println("Se ha agregado el juguete de forma correcta.");
											mostrarJuguetes();
										}
										banderaZona2 = true;
										break;
									default:
										System.err.println("ERROR, introduce un 1 o 2.");
								}
						}while(!banderaZona2);
						bandera = true;
						break;
					case 3:
						insercionJugueteEnStock = "INSERT INTO `stock` (`STAND_idStand`, `STAND_ZONA_idzona`, `JUGUETE_idJuguete`, `CANTIDAD`) VALUES (?,?,?,?)";
						sentencia2 = conexion.prepareStatement(insercionJugueteEnStock);
						sentencia2.setInt(1,1);//solo hay un stand en la zona 1 que es de cosas de bebes
						sentencia2.setInt(2,opcion);
						sentencia2.setInt(3,jugueteN.iD_Juguete);
						sentencia2.setInt(4,jugueteN.cantidad);
						comprobacionEnStock =sentencia.executeUpdate();
						if ((comprobacionEnStock > 0)) {
							System.out.println("Se ha agregado el juguete de forma correcta.");
							mostrarJuguetes();
						}
						bandera = true;
						break;
					case 4:
						insercionJugueteEnStock = "INSERT INTO `stock` (`STAND_idStand`, `STAND_ZONA_idzona`, `JUGUETE_idJuguete`, `CANTIDAD`) VALUES (?,?,?,?)";
						sentencia2 = conexion.prepareStatement(insercionJugueteEnStock);
						sentencia2.setInt(1,1);//solo hay un stand en la zona 1 que es de cosas de bebes
						sentencia2.setInt(2,opcion);
						sentencia2.setInt(3,jugueteN.iD_Juguete);
						sentencia2.setInt(4,jugueteN.cantidad);
						comprobacionEnStock =sentencia.executeUpdate();
						if ((comprobacionEnStock > 0)) {
							System.out.println("Se ha agregado el juguete de forma correcta.");
							mostrarJuguetes();
						}
						bandera = true;
						break;
					default:
						System.err.println("ERROR, introduce un número de las opciones no mayor.");
						
				}
			}while(!bandera);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void registroEmpleadoNuevo(Empleado empleadoN) {
		try {
			Connection conexion = comprobarConexion();
			String insercionEmpleado = "INSERT INTO `empleado` (`Nombre`, `Cargo`, `Fecha_ingreso`) VALUES (?,?,?)";
			PreparedStatement sentencia = conexion.prepareStatement(insercionEmpleado);
			sentencia.setString(1,empleadoN.nombre);
			sentencia.setString(2,empleadoN.cargoGuardado.name());
			sentencia.setDate(3,empleadoN.fechaDeIngreso);
			int comprobacionStock =sentencia.executeUpdate();
			if ((comprobacionStock > 0)) {
				System.out.println("Se ha agregado el empleado de forma correcta.");
				mostrarEmpleados();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void eliminarObjetos(String tabla,String columna,int idAEliminar) {
		try {
			Connection conexion = comprobarConexion();
			String consulta = "DELETE FROM "+tabla+" WHERE "+columna+" = "+idAEliminar+";";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			int comprobacionStock =sentencia.executeUpdate();
			if ((comprobacionStock > 0)) {
				System.out.println("Se ha eliminado correctamente.");
				if (tabla.equals("juguete")) {
					mostrarJuguetes();
				}else {
					mostrarEmpleados();
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void mostrarEmpleados() {
		try {
			Connection conexion = comprobarConexion();
			String consulta = "SELECT * FROM empleado;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery(consulta);
			while (resultado.next()){
				int idEmpleado = resultado.getInt("idEMPLEADO");
				String nombre = resultado.getString("Nombre");
				String cargo = resultado.getString("Cargo");
				String fecha_Ingreso = resultado.getString("Fecha_ingreso");
				System.out.println("Empleado de ID = "+idEmpleado+", Nombre = "+nombre+", Cargo = "+cargo+", fecha de ingreso = "+fecha_Ingreso);
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void mostrarJuguetes() {
		try {
			Connection conexion = comprobarConexion();
			String consulta = "SELECT * FROM juguete;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery(consulta);
			while (resultado.next()){
				int idJuguete = resultado.getInt("idJuguete");
				String nombre = resultado.getString("Nombre");
				String descripcion = resultado.getString("Descripcion");
				double precio = resultado.getDouble("Precio");
				int cantidad = resultado.getInt("Cantidad_stock");
				String categoria = resultado.getString("Categoria");
				System.out.println("Juguete de ID = "+idJuguete+", de nombre = "+nombre+", Descripcion = "+descripcion+", precio = "+precio+", Cantidad en stock = "+cantidad+", Categoria = "+categoria);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int comprobacionDeIDs(String idTabla,String columna) {
		int idFinal=0;
		try {
			Connection conexion = comprobarConexion();
			String insercionEmpleado = "SELECT "+columna+" FROM "+idTabla+";";
			PreparedStatement sentencia = conexion.prepareStatement(insercionEmpleado);
			ResultSet resultado = sentencia.executeQuery();
			while(resultado.next()) {
				idFinal = resultado.getInt(columna);
			}
			return idFinal;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idFinal;
	}
	public static void updateDatosJuguete() {	
		boolean bandera = false;
		try {
			Connection conexion = comprobarConexion();
			String consulta = "update juguete set Precio = ?, Stock = ? WHERE idJuguete = ?;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			int parametroID;
			int idFinal = comprobacionDeIDs("juguete","idJuguete");
			do {
				System.out.println("Introduce el ID del juguete para cambiar su precio y stock: ");
				parametroID = Jugueteria.controlDeErroresInt();
				if ((parametroID > 0)&&(parametroID<=idFinal)) {
					bandera = true;
				}else {
					System.out.println("Introduce un Id que exista en la base de datos");
				}
			}while(!bandera);
			System.out.println("Introduce el nuevo precio del juguete de ID "+parametroID+".");
			double parametroPrecio = Jugueteria.controlDeErroresDouble();
			System.out.println("Introduce la nueva cantidad del juguete de ID"+parametroID+".");
			int parametroCantidad = Jugueteria.controlDeErroresInt();
			
			sentencia.setDouble(1,parametroPrecio);
			sentencia.setInt(2,parametroCantidad);
			sentencia.setInt(3,parametroID);
			int comprobacionActualizacionJuguete =sentencia.executeUpdate();
			if ((comprobacionActualizacionJuguete > 0)) {
				System.out.println("Se ha modificado correctamente el juguete de ID "+parametroID+".");
			}
			
			mostrarJuguetes();
					
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void updateDatosEmpleados() {
		boolean bandera = false,banderaCargo=false;
		try {
			Connection conexion = comprobarConexion();
			String consulta = "update empleado set Nombre = ?, Cargo = ? WHERE idEMPLEADO = ?;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			int parametroID;
			int idFinal = comprobacionDeIDs("empleado","idEMPLEADO");
			do {
				System.out.println("Introduce el ID del empleado para cambiar sus datos: ");
				parametroID = Jugueteria.controlDeErroresInt();
				if ((parametroID > 0)&&(parametroID<=idFinal)) {
					bandera = true;
				}else {
					System.out.println("Introduce un Id que exista en la base de datos");
				}
			}while(!bandera);
			
			System.out.println("Introduce el nuevo nombre del empleado de ID "+parametroID+".");
			String parametroN = entrada.nextLine();
			
			do {
				Date fecha = new Date(0);
				Empleado EmpleadoCJ = new Empleado(2,"A",cargo.Jefe,fecha);
				Empleado EmpleadoCC = new Empleado(2,"A",cargo.Cajero,fecha);
				System.out.print("¿Que cargo tendria el empleado de ID = "+parametroID+" ? Jefe (1), Cajero(2) : ");
				int opcion = Jugueteria.controlDeErroresInt();
				;
				switch (opcion) {
					case 1:
						sentencia.setString(2,EmpleadoCJ.cargoGuardado.name());
						banderaCargo=true;
						break;
					case 2:
						sentencia.setString(2,EmpleadoCC.cargoGuardado.name());
						banderaCargo=true;
						break;
					default:
						System.err.println("ERROR, introduce un número entre 1 y 2.");
				}
			}while(!banderaCargo);
			sentencia.setString(1,parametroN);
			sentencia.setInt(3,parametroID);
			int comprobacionActulizacionEmpleado =sentencia.executeUpdate();
			if ((comprobacionActulizacionEmpleado > 0)) {
				System.out.println("Se ha modificado correctamente el empleado de ID "+parametroID+".");
			}
			mostrarEmpleados();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	private static int contadorDeDatos(String nombreTabla) {
		int datosCargados = 1;
		try {
			Connection conexion = comprobarConexion();
			String consultaComun = "SELECT COUNT(*) as Total FROM " + nombreTabla;
			PreparedStatement sentencia = conexion.prepareStatement(consultaComun);
			ResultSet resultado = sentencia.executeQuery();
		    if (resultado.next()) {
		    	datosCargados = resultado.getInt("Total");
		        return datosCargados; 
		    }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return datosCargados;
	}
	
	public static void ComprobacionBaseDatos() {
		try {
			int datosEmpleados = contadorDeDatos("empleado");
	        int datosZona = contadorDeDatos("zona");
	        int datosJuguetes = contadorDeDatos("juguete");
	        int datosStand = contadorDeDatos("stand");
	        int datosStock = contadorDeDatos("stock");
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
			
			System.out.println();
		
			
		}catch(Exception e){
			e.getStackTrace();
		}
		
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

	public static boolean existeColumnaCategoria() {
	    try {
	    	Connection conexion =  comprobarConexion();
		    String consultaComprobacionCATEGORIA = "SELECT count(*) FROM information_schema.columns WHERE table_schema = 'jugueteria' AND table_name = 'juguete' AND column_name = 'Categoria'";
	    	PreparedStatement sentencia = conexion.prepareStatement(consultaComprobacionCATEGORIA);
	        ResultSet resultado = sentencia.executeQuery();
	        if (resultado.next()) {
	        	if(resultado.getInt(1) > 0){
	        		return true; 
	        	}else {
	        		return false;
	        	}
	            
	        }
	    }catch(SQLException e) {e.printStackTrace();}
	    return false;
	}
	private static int subidaDatosJuguetesIniciales() {
		int funciona=0;
		try {
			Connection conexion = comprobarConexion();
			if (!existeColumnaCategoria()) {
				String creacionCategoria = "Alter table juguete add column Categoria ENUM ('Pelota', 'Muñeca', 'Coche', 'Juego_De_Mesa', 'Plastilina', 'Peluche', 'Otro') NULL DEFAULT 'Otro';";
				PreparedStatement sentenciaCategoria = conexion.prepareStatement(creacionCategoria);
				sentenciaCategoria.executeUpdate(creacionCategoria);
			}
			String insercionJuguetes = "INSERT IGNORE INTO `juguete` (`idJuguete`, `Nombre`, `Descripcion`, `Precio`, `Cantidad_stock`,`Categoria`) VALUES (1, 'Bloques Básicos', 'Set de 50 piezas', 15.50, 100,'Otro'),(2, 'Muñeca Clásica', 'Muñeca de trapo', 12.00, 50,'Muñeca'),(3, 'Balón de Fútbol', 'Tamaño oficial 5', 19.99, 30,'Pelota'),(4, 'Monopoly', 'Edición Clásica', 25.00, 40,'Juego_De_Mesa'),(5, 'Coche RC', 'Coche teledirigido rojo', 35.50, 20,'Coche'),(6, 'Lego Star wars', 'Set de 500 piezas coleccionable', 35.50, 40,'Otro');";
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
			String insercionStand = "INSERT INTO `stand` (`idStand`, `ZONA_idzona`, `Nombre`, `Descripcion`) VALUES (1, 1, 'Stand Bebés', 'Artículos blandos'),(1, 2, 'Stand Lego StarWars', 'Ediciones especiales'),(2, 2, 'Stand Bloques', 'Piezas sueltas'),(1, 3, 'Stand Estrategia', 'Juegos largos'),(1, 4, 'Stand Juegos exteriores', 'Todo tipo de balones y objetos para jugar fuera de casa');";
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
			String insercionStock = "INSERT INTO `stock` (`STAND_idStand`, `STAND_ZONA_idzona`, `JUGUETE_idJuguete`, `CANTIDAD`) VALUES (1, 1, 1, 100),(1, 1, 2, 50),(1, 4, 3, 30),(1, 3, 4, 40),(4,4,5,20),(1,2,6,40);";
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
			int comprobacionStand =subidaDatosStandIniciales();
			int comprobacionStock = subidaDatosStockIniciales();
			if ((comprobacionEmpleados > 0)&&(comprobacionZona > 0)&&(comprobacionJuguetes > 0)&&(comprobacionStand > 0)&&(comprobacionStock > 0)) {
				System.out.println("Se han cargados todos los datos iniciales de la base de datos correctamente.");
			}else {
				System.out.println("No se han podido cargar todos los datos.");
			}
	}
	public static int comprobacionVentas() {
		int idVenta=0;
		try  {
			int datosVenta = contadorDeDatos("venta");
			if (datosVenta == 0) {
				return idVenta;
			}else {
				idVenta=datosVenta;
				return idVenta;
			}
		}catch(Exception e) {
			e.getStackTrace();
		}
		return idVenta;
	}
	
	public static void registroVenta(Venta ventaNueva) {
		try {
			Connection conexion = comprobarConexion();
			String consultaCreacionVenta = "INSERT INTO `venta` (`idventa`,`Fecha`,`Monto`,`tipoPago`,`EMPLEADO_idEMPLEADO`,`stock_STAND_idStand`,`stock_STAND_ZONA_idzona`, `stock_JUGUETE_idJuguete`) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement sentenciaVenta = conexion.prepareStatement(consultaCreacionVenta);
			sentenciaVenta.setInt(1,ventaNueva.idVenta);
			sentenciaVenta.setDate(2,ventaNueva.fechaVenta);
			sentenciaVenta.setDouble(3,ventaNueva.Monto);
			sentenciaVenta.setString(4,ventaNueva.tipoDePago.name());
			sentenciaVenta.setInt(5,ventaNueva.idEmpleado);
			sentenciaVenta.setInt(6,ventaNueva.idStand);
			sentenciaVenta.setInt(7, ventaNueva.idZona);
			sentenciaVenta.setInt(8,ventaNueva.idJuguete);
			sentenciaVenta.executeUpdate();
			
		}catch(SQLException o) {
			o.getStackTrace();
		}
	}
	private static void mostrarStock(int idJugueteEnStock) {
		String nombreJuguete = null,nombreZona=null,nombreStand = null;
		int precioJugueteEnStock = 0,idZona = 0,idStand = 0,CantidadEnStock=0;
		try {
			Connection conexion = comprobarConexion();
			String consultaEnJugueteId = "SELECT Nombre,Precio FROM juguete WHERE idJuguete = " + idJugueteEnStock+";";
			PreparedStatement sentenciaJuguete = conexion.prepareStatement(consultaEnJugueteId);
			ResultSet resultado1 = sentenciaJuguete.executeQuery();
			if(resultado1.next()) {
				nombreJuguete = resultado1.getString("Nombre");
				precioJugueteEnStock = resultado1.getInt("Precio");
			}
			String consultaEnStock = "SELECT STAND_idStand,STAND_ZONA_idzona,CANTIDAD FROM stock WHERE JUGUETE_idJuguete = " + idJugueteEnStock+";";
			PreparedStatement sentenciaStock = conexion.prepareStatement(consultaEnStock);
			ResultSet resultado2 = sentenciaStock.executeQuery();
			if(resultado2.next()) {
				idStand = resultado2.getInt("STAND_idStand");
				idZona = resultado2.getInt("STAND_ZONA_idzona");
				CantidadEnStock = resultado2.getInt("CANTIDAD");
			}
			String consultaEnStand = "SELECT Nombre as NombreStand FROM stand WHERE  idStand = " + idStand+";";
			PreparedStatement sentenciaStand = conexion.prepareStatement(consultaEnStand);
			ResultSet resultado3 = sentenciaStand.executeQuery();
			if(resultado3.next()) {
				nombreStand = resultado3.getString("NombreStand");
			}
			String consultaEnZona = "SELECT Nombre as NombreZona FROM stock WHERE idzona = " + idZona+";";
			PreparedStatement sentenciaZona = conexion.prepareStatement(consultaEnZona);
			ResultSet resultado4 = sentenciaZona.executeQuery();
			if(resultado4.next()) {
				nombreZona = resultado4.getString("NombreZona");
			}
			System.out.println("El juguete de ID = "+idJugueteEnStock+", nombre = "+nombreJuguete+", de precio = "+precioJugueteEnStock+", se encuentra en el Stand "+nombreStand+" y en la zona "+nombreZona+", tiene esta cantidad en stock = "+CantidadEnStock);
			
		}catch(SQLException i){
			i.getStackTrace();
		}
	}
	public static double precioVenta(int idJugueteVender) {
		int cantidadEnStock = 0,cantidadAVender=0;
		double totalVenta = 0,precioUnidad = 0;
		String nombreJugueteAVender = null;
		try {
			Connection conexion = comprobarConexion();
			String consultaEnJuguete = "SELECT precio,Nombre FROM juguete WHERE idJuguete = " + idJugueteVender+";";
			
			PreparedStatement sentenciaVenta = conexion.prepareStatement(consultaEnJuguete);
			ResultSet resultado = sentenciaVenta.executeQuery();
			
			if(resultado.next()) {
				precioUnidad = resultado.getDouble("precio");
				nombreJugueteAVender = resultado.getString("Nombre");
			}
			String consultaEnStock = "SELECT CANTIDAD FROM stock WHERE JUGUETE_idJuguete = " + idJugueteVender+";";
			PreparedStatement sentenciaStock = conexion.prepareStatement(consultaEnStock);
			ResultSet resultadoStock = sentenciaStock.executeQuery();
			if(resultadoStock.next()) {
				cantidadEnStock =resultadoStock.getInt("CANTIDAD");
			}
			
			
			boolean bandera = false;
			do {
				mostrarStock(idJugueteVender);
				System.out.println("¿ Cuant@s "+nombreJugueteAVender+" va a llevar el cliente ?");
				cantidadAVender = Jugueteria.controlDeErroresInt();
				if (cantidadEnStock < cantidadAVender) {
					System.err.println("Quieres comprar más cantidad de las que hay en Stock... introduce una cantidad que tengamos.");
				}else {
					totalVenta = precioUnidad*cantidadAVender;
					cantidadEnStock -= cantidadAVender;
					bandera = true;
				}
			}while(!bandera);
			String actualizarInventario ="UPDATE stock set CANTIDAD = "+cantidadEnStock+" WHERE JUGUETE_idJuguete = "+idJugueteVender+";";
			PreparedStatement sentenciaActualizacion = conexion.prepareStatement(actualizarInventario);
			sentenciaActualizacion.executeUpdate();
			mostrarJuguetes();
			return totalVenta;
		}catch (SQLException i){
			i.getStackTrace();
		}
		
		
		
		return totalVenta;
	}
	public static int buscarEnStock(int idJuguete,String Columna) {
		int id =0;
		try {
			Connection conexion = comprobarConexion();
			String consultaEnSTOCK = "SELECT "+Columna+" FROM stock WHERE  JUGUETE_idJuguete = " + idJuguete+";";
			PreparedStatement sentenciaStand = conexion.prepareStatement(consultaEnSTOCK);
			ResultSet resultado = sentenciaStand.executeQuery();
			if(resultado.next()) {
				id = resultado.getInt(Columna);
				return id;
			}
		}catch (SQLException i){
			i.getStackTrace();
		}
		return id;
	}
	public static int obtencionCargoInicioSesion() {
		int cargo=0;
		try {
			Connection conexion = comprobarConexion();
			String consulta = "SELECT Cargo, Nombre, idEMPLEADO from empleado where idEMPLEADO = ?;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			System.out.print("Introduce tu Id de empleado : ");
			int parametro = Jugueteria.controlDeErroresInt();
			sentencia.setInt(1,parametro);
			ResultSet resultado = sentencia.executeQuery();
			if (resultado.next()) {
				String respuesta = resultado.getString("Cargo");
				nombreUsuario = resultado.getString("Nombre");
				idEmpleadoVenta = resultado.getInt("idEMPLEADO");
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
