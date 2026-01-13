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
	private static String url = "jdbc:mysql://localhost:3306/jugueteriaSamuelCarias";
	private static String usuario = "root";
	//private static String passwordCASA = "casaSQL";
	private static String passwordINSTI = "cfgs";
	private static Scanner entrada = new Scanner(System.in);
	public static String nombreUsuario;
	public static int idEmpleadoVenta;
	public static ArrayList<Juguete> arrayJuguetes = new ArrayList<>();
	public static ArrayList<Juguete> arrayJuguetestemporal = new ArrayList<>();
	private static ArrayList<Stock> arrayStocks = new ArrayList<>();
	
	public static Connection comprobarConexion() {
		Connection conexion = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexion = DriverManager.getConnection(url,usuario,passwordINSTI);
			//System.out.println("Se ha conectado a la base de datos.");
			return conexion;
		} catch (ClassNotFoundException|SQLException e) {
			e.printStackTrace();
		}
		
		return conexion;
	
	}
	
	
	
	public static void registroJuguetes(Juguete jugueteN) {
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
						comprobacionEnStock =sentencia2.executeUpdate();
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
										comprobacionEnStock =sentencia2.executeUpdate();
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
										comprobacionEnStock =sentencia2.executeUpdate();
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
						comprobacionEnStock =sentencia2.executeUpdate();
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
						comprobacionEnStock =sentencia2.executeUpdate();
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
			}
				if(tabla.equals("juguete")){
					Juguete jugueteABorrar = null;
					for(Juguete a : arrayJuguetes) {
						if (a.iD_Juguete == idAEliminar) {
							jugueteABorrar = a;
							break;
						}
					}
					if (jugueteABorrar != null) {
	                    arrayJuguetes.remove(jugueteABorrar);
	                }
					Stock stockAJuguete =null;
					for(Stock ab : arrayStocks) {
						if (ab.idJuguete == idAEliminar) {
							stockAJuguete=ab;
						}
					}
					arrayStocks.remove(stockAJuguete);
					mostrarJuguetes();
				}else if (tabla.equals("empleado")) {
					mostrarEmpleados();
				}else {
					mostrarVentas();
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
	public static void mostrarStand() {
		try {
			Connection conexion = comprobarConexion();
			String consulta = "SELECT * FROM stand;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery(consulta);
			while (resultado.next()){
				int idStand = resultado.getInt("idStand");
				String nombre = resultado.getString("Nombre");
				String descripcion = resultado.getString("Descripcion");
				System.out.println("Stand de ID = "+idStand+", "+nombre+", descripcion: "+descripcion);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void mostrarJugueteStand() {
		boolean bandera = false;
		int idStand,idZona = 0,idJuguete = 0, cantidad = 0;
		String nombreStand = null,nombreZona=null,nombreJuguete=null;
		try {
			Connection conexion = comprobarConexion();
			String consulta = "SELECT * FROM stock where STAND_ZONA_idzona = ?;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			do {
			System.out.println("Introduce el Id de la zona donde se encuentra el stand que quieras ver los juguetes ahi guardados: Infantil (1), Construccion (2), Juegos de Mesa (3), Juegos al Aire libre (4)");
			idZona = Jugueteria.controlDeErroresInt();
			
			if ((idZona>0)&&(idZona<=4)) {
				sentencia.setInt(1,idZona);
				ResultSet resultado = sentencia.executeQuery();
				System.out.println("");
				while(resultado.next()){
					idStand = resultado.getInt("STAND_idStand");
					idJuguete = resultado.getInt("JUGUETE_idJuguete");
					cantidad = resultado.getInt("CANTIDAD");
					String consultaStand = "SELECT Nombre FROM stand where idStand = ? AND Zona_idzona = ?;";
					PreparedStatement sentenciaStand = conexion.prepareStatement(consultaStand);
					sentenciaStand.setInt(1,idStand);
					sentenciaStand.setInt(2,idZona);
					ResultSet resultadoStand = sentenciaStand.executeQuery();
					if (resultadoStand.next()) {
						nombreStand = resultadoStand.getString("Nombre");
					}
					String consultaZona = "SELECT Nombre FROM zona where idzona = ?;";
					PreparedStatement sentenciaZona= conexion.prepareStatement(consultaZona);
					sentenciaZona.setInt(1,idZona);
					ResultSet resultadoZona = sentenciaZona.executeQuery();
					if (resultadoZona.next()) {
						nombreZona = resultadoZona.getString("Nombre");
					}
					String consultaJuguete = "SELECT Nombre,Cantidad_stock FROM juguete where idJuguete = ?;";
					PreparedStatement sentenciaJuguete= conexion.prepareStatement(consultaJuguete);
					sentenciaJuguete.setInt(1,idJuguete);
					ResultSet resultadoJuguete = sentenciaJuguete.executeQuery();
					if (resultadoJuguete.next()) {
						nombreJuguete = resultadoJuguete.getString("Nombre");
						cantidad = resultadoJuguete.getInt("Cantidad_stock");
					}
					System.out.println("Stand de id "+idStand+", de nombre = "+nombreStand+", se encuentra en la zona de "+nombreZona+" y encontraras "+nombreJuguete+", disponibles = "+cantidad);
				}
				bandera = true;
			}else{
				System.err.println("Introduce un valor entre el 1 y 4.");
			}
			}while(!bandera);
			
			System.out.println("");
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
			System.out.println("");
			while (resultado.next()){
				int cantidad = 0;
				int idJuguete = resultado.getInt("idJuguete");
				String nombre = resultado.getString("Nombre");
				String descripcion = resultado.getString("Descripcion");
				double precio = resultado.getDouble("Precio");
				String categoria = resultado.getString("Categoria");
				System.out.println("Juguete de ID = "+idJuguete+", de nombre = "+nombre+", Descripcion = "+descripcion+", precio = "+precio+", Categoria = "+categoria);
				if(categoria=="Pelota") {
					String consultaEnStock = "SELECT CANTIDAD FROM stock WHERE JUGUETE_idJuguete = " + idJuguete+";";
					PreparedStatement sentencia1 = conexion.prepareStatement(consultaEnStock);
					ResultSet resultado1 = sentencia1.executeQuery(consultaEnStock);
					if(resultado1.next()) {
						cantidad = resultado1.getInt("CANTIDAD");
					}
					Juguete jGuardado = new Juguete(idJuguete,cantidad,nombre,descripcion,precio,categoriaObjetos.Pelota);
					arrayJuguetes.add(jGuardado);
				}else if(categoria=="Muñeca") {
					String consultaEnStock = "SELECT CANTIDAD FROM stock WHERE JUGUETE_idJuguete = " + idJuguete+";";
					PreparedStatement sentencia1 = conexion.prepareStatement(consultaEnStock);
					ResultSet resultado1 = sentencia1.executeQuery(consultaEnStock);
					if(resultado1.next()) {
						cantidad = resultado1.getInt("CANTIDAD");
					}
					Juguete jGuardado = new Juguete(idJuguete,cantidad,nombre,descripcion,precio,categoriaObjetos.Muñeca);
					arrayJuguetes.add(jGuardado);
				}else if(categoria=="Construccion") {
					String consultaEnStock = "SELECT CANTIDAD FROM stock WHERE JUGUETE_idJuguete = " + idJuguete+";";
					PreparedStatement sentencia1 = conexion.prepareStatement(consultaEnStock);
					ResultSet resultado1 = sentencia1.executeQuery(consultaEnStock);
					if(resultado1.next()) {
						cantidad = resultado1.getInt("CANTIDAD");
					}
					Juguete jGuardado = new Juguete(idJuguete,cantidad,nombre,descripcion,precio,categoriaObjetos.Construccion);
					arrayJuguetes.add(jGuardado);
				}else if(categoria=="Juego_De_Mesa") {
					String consultaEnStock = "SELECT CANTIDAD FROM stock WHERE JUGUETE_idJuguete = " + idJuguete+";";
					PreparedStatement sentencia1 = conexion.prepareStatement(consultaEnStock);
					ResultSet resultado1 = sentencia1.executeQuery(consultaEnStock);
					if(resultado1.next()) {
						cantidad = resultado1.getInt("CANTIDAD");
					}
					Juguete jGuardado = new Juguete(idJuguete,cantidad,nombre,descripcion,precio,categoriaObjetos.Juego_De_Mesa);
					arrayJuguetes.add(jGuardado);
				}else if(categoria=="Plastilina") {
					String consultaEnStock = "SELECT CANTIDAD FROM stock WHERE JUGUETE_idJuguete = " + idJuguete+";";
					PreparedStatement sentencia1 = conexion.prepareStatement(consultaEnStock);
					ResultSet resultado1 = sentencia1.executeQuery(consultaEnStock);
					if(resultado1.next()) {
						cantidad = resultado1.getInt("CANTIDAD");
					}
					Juguete jGuardado = new Juguete(idJuguete,cantidad,nombre,descripcion,precio,categoriaObjetos.Plastilina);
					arrayJuguetes.add(jGuardado);
				}else if(categoria=="Peluche") {
					String consultaEnStock = "SELECT CANTIDAD FROM stock WHERE JUGUETE_idJuguete = " + idJuguete+";";
					PreparedStatement sentencia1 = conexion.prepareStatement(consultaEnStock);
					ResultSet resultado1 = sentencia1.executeQuery(consultaEnStock);
					if(resultado1.next()) {
						cantidad = resultado1.getInt("CANTIDAD");
					}
					Juguete jGuardado = new Juguete(idJuguete,cantidad,nombre,descripcion,precio,categoriaObjetos.Peluche);
					arrayJuguetes.add(jGuardado);
				}else {
					String consultaEnStock = "SELECT CANTIDAD FROM stock WHERE JUGUETE_idJuguete = " + idJuguete+";";
					PreparedStatement sentencia1 = conexion.prepareStatement(consultaEnStock);
					ResultSet resultado1 = sentencia1.executeQuery(consultaEnStock);
					if(resultado1.next()) {
						cantidad = resultado1.getInt("CANTIDAD");
					}
					Juguete jGuardado = new Juguete(idJuguete,cantidad,nombre,descripcion,precio,categoriaObjetos.Otro);
					arrayJuguetes.add(jGuardado);
				}
			}
			System.out.println("");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void mostrarVentas() {
		try {
			Connection conexion = comprobarConexion();
			String consulta = "SELECT * FROM venta;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery(consulta);
			System.out.println("");
			while (resultado.next()){
				int idventa = resultado.getInt("idventa");
				String fecha = resultado.getString("Fecha");
				double montoVenta = resultado.getDouble("Monto");
				int idEmpleado = resultado.getInt("EMPLEADO_idEMPLEADO");
				int idStand = resultado.getInt("stock_STAND_idStand");
				int idZona= resultado.getInt("stock_STAND_ZONA_idzona");
				int idJuguete = resultado.getInt("stock_JUGUETE_idJuguete");
				System.out.println("\nID venta = "+idventa+"\t\t realizada a fecha de = "+fecha+".\n Realizo la venta el empleado de id = "+idEmpleado+".\n Id de juguete vendido = "+idJuguete+", del stand "+idStand+", de la zona "+idZona+".\n el monto total del ticket = "+montoVenta+".\n");
			}
			System.out.println("");
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
			String consulta = "update juguete set Precio = ?, Cantidad_stock = ? WHERE idJuguete = ?;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			int parametroID;
			int idFinal = comprobacionDeIDs("juguete","idJuguete");
			do {
				System.out.println("Introduce el ID del juguete para cambiar su precio y stock: ");
				parametroID = Jugueteria.controlDeErroresInt();
				if ((parametroID > 0)&&(parametroID<=idFinal)) {
					bandera = true;
				}else {
					System.err.println("Introduce un Id que exista en la base de datos");
				}
			}while(!bandera);
			System.out.println("Introduce el nuevo precio del juguete de ID "+parametroID+".");
			double parametroPrecio = Jugueteria.controlDeErroresDouble();
			System.out.println("Introduce la nueva cantidad del juguete de ID "+parametroID+".");
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
					System.err.println("Introduce un Id que exista en la base de datos");
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
	

	public static int contadorDeDatos(String nombreTabla) {
		int datosCargados = 0;
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
			String insercionZona = "INSERT INTO `zona` (`idzona`, `Nombre`, `Descripcion`) VALUES (1, 'Infantil', 'Juguetes para 0-3 años'),(2, 'Construcción', 'Legos y bloques'),(3, 'Juegos de Mesa', 'Puzzles y cartas'),(4, 'Juegos al Exterior', 'Deportes y aire libre');";
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
			/*if (!existeColumnaCategoria()) {
				String creacionCategoria = "Alter table juguete add column Categoria ENUM ('Pelota', 'Muñeca', 'Coche', 'Juego_De_Mesa', 'Plastilina', 'Peluche', 'Otro') NULL DEFAULT 'Otro';";
				PreparedStatement sentenciaCategoria = conexion.prepareStatement(creacionCategoria);
				sentenciaCategoria.executeUpdate(creacionCategoria);
			}*/
			String insercionJuguetes = "INSERT INTO `juguete` (`idJuguete`, `Nombre`, `Descripcion`, `Precio`, `Cantidad_stock`,`Categoria`) VALUES (1, 'Plastilina', 'Set 3 envases', 15.50, 100,'Plastilina'),(2, 'Muñeca Clásica', 'Muñeca de trapo', 12.00, 50,'Muñeca'),(3, 'Balón de Fútbol', 'Tamaño oficial 5', 19.99, 30,'Pelota'),(4, 'Monopoly', 'Edición Clásica', 25.00, 40,'Juego_De_Mesa'),(5, 'Coche RC', 'Coche teledirigido rojo', 35.50, 20,'Otro'),(6, 'Lego Star wars', 'Set de 500 piezas coleccionable', 35.50, 40,'Construccion');";
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
			String insercionStand = "INSERT INTO `stand` (`idStand`, `ZONA_idzona`, `Nombre`, `Descripcion`) VALUES (1, 1, 'Stand Bebés', 'Artículos blandos'),(1, 2, 'Stand Lego StarWars', 'Ediciones especiales'),(2, 2, 'Stand Juegos variados', 'Piezas sueltas'),(1, 3, 'Stand Estrategia', 'Juegos largos'),(1, 4, 'Stand Juegos exteriores', 'Todo tipo de balones y objetos para jugar fuera de casa');";
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
			String insercionStock = "INSERT INTO `stock` (`STAND_idStand`, `STAND_ZONA_idzona`, `JUGUETE_idJuguete`, `CANTIDAD`) VALUES (1, 1, 1, 100),(1, 1, 2, 50),(1, 4, 3, 30),(1, 3, 4, 40),(1,4,5,20),(1,2,6,40);";
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
				System.err.println("No se han podido cargar todos los datos.");
			}
	}
	
	public static void registroVenta(Venta ventaNueva) {
		try {
			Connection conexion = comprobarConexion();
			String consultaCreacionVenta = "INSERT INTO `venta` (`idventa`,`Fecha`,`Monto`,`tipo_Pago`,`EMPLEADO_idEMPLEADO`,`stock_STAND_idStand`,`stock_STAND_ZONA_idzona`, `stock_JUGUETE_idJuguete`) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement sentenciaVenta = conexion.prepareStatement(consultaCreacionVenta);
			sentenciaVenta.setInt(1,ventaNueva.idVenta);
			sentenciaVenta.setDate(2,ventaNueva.fechaVenta);
			sentenciaVenta.setDouble(3,ventaNueva.Monto);
			sentenciaVenta.setString(4,ventaNueva.tipoDePago.name());
			sentenciaVenta.setInt(5,ventaNueva.idEmpleado);
			sentenciaVenta.setInt(6,ventaNueva.idStand);
			sentenciaVenta.setInt(7, ventaNueva.idZona);
			sentenciaVenta.setInt(8,ventaNueva.idJuguete);
			int ventaRealizada = sentenciaVenta.executeUpdate();
			if (ventaRealizada > 0) {
				System.out.println("Se ha realizado la venta correctamente.");
			}else {
				System.err.println("Ha habido un error al realizar la venta.");
			}
		}catch(SQLException o) {
			o.printStackTrace();
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
			String consultaEnZona = "SELECT Nombre as NombreZona FROM zona WHERE idzona = " + idZona+";";
			PreparedStatement sentenciaZona = conexion.prepareStatement(consultaEnZona);
			ResultSet resultado4 = sentenciaZona.executeQuery();
			if(resultado4.next()) {
				nombreZona = resultado4.getString("NombreZona");
			}
			System.out.println("El juguete de ID = "+idJugueteEnStock+", nombre = "+nombreJuguete+", de precio = "+precioJugueteEnStock+"€, se encuentra en el Stand "+nombreStand+" y en la zona de "+nombreZona+", hay disponibles en stock = "+CantidadEnStock);
			
		}catch(SQLException i){
			i.printStackTrace();
		}
	}
	public static double precioVenta(int idJugueteVender) {
		int cantidadEnStock = 0,cantidadAVender=0;
		double totalVenta = 0,precioUnidad = 0;
		String nombreJugueteAVender = null;
		try {
			Connection conexion = comprobarConexion();
			String consultaEnJuguete = "SELECT precio,Nombre FROM juguete WHERE idJuguete = " + idJugueteVender+";";
			PreparedStatement sentenciaJuguete = conexion.prepareStatement(consultaEnJuguete);
			ResultSet resultado = sentenciaJuguete.executeQuery();
			
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
			String consultaEnSTOCK = "SELECT "+Columna+" as id FROM stock WHERE  JUGUETE_idJuguete = ? ;";
			PreparedStatement sentenciaSTOCK = conexion.prepareStatement(consultaEnSTOCK);
			sentenciaSTOCK.setInt(1, idJuguete);
			ResultSet resultado = sentenciaSTOCK.executeQuery();
			if(resultado.next()) {
				id = resultado.getInt("id");
				return id;
			}
		}catch (SQLException i){
			i.printStackTrace();
		}
		return id;
	}
	public static int realizarCambio(Cambio cambiarProducto,double montoTotalCompra) {
		boolean banderaCambio = false,banderaSalirCambio=false;
		int verificacion=0,valorIDVenta = 0,cantidadEnStockJCanjeado = 0,cantidadEnStockJDevuelto = 0,cantidadCanjeadoAllevar,cantidadJuguetesDevueltos,opcion;
		double precioUnidadJugueteCanjeado = 0,precioUnidadJugueteDevuelto = 0,totalJugueteCanjeado;
		String nombreJugueteCanjeado ="";
		try {
			Connection conexion = comprobarConexion();
			String consultaComprobacion = "SELECT COUNT(*) as total FROM venta where Monto = ? and stock_JUGUETE_idJuguete=? ";
			PreparedStatement sentenciaCOMPROBACION = conexion.prepareStatement(consultaComprobacion);
			sentenciaCOMPROBACION.setDouble(1,montoTotalCompra);
			sentenciaCOMPROBACION.setInt(2,cambiarProducto.STOCK_JUGUETE_idJuguete_Original);
			ResultSet resultadoCOMPROBACION = sentenciaCOMPROBACION.executeQuery();
			if(resultadoCOMPROBACION.next()) {
				verificacion = resultadoCOMPROBACION.getInt("total");
				if (verificacion != 0) {
					String consultaEnVenta = "SELECT idventa,Fecha,tipo_pago,EMPLEADO_idEMPLEADO,stock_STAND_idStand,stock_STAND_ZONA_idzona,stock_JUGUETE_idJuguete FROM venta where Monto = ? and stock_JUGUETE_idJuguete=? ";
					PreparedStatement sentenciaVenta = conexion.prepareStatement(consultaEnVenta);
					sentenciaVenta.setDouble(1,montoTotalCompra);
					sentenciaVenta.setInt(2,cambiarProducto.STOCK_JUGUETE_idJuguete_Original);
					ResultSet resultado = sentenciaVenta.executeQuery();
					if(resultado.next()) {
							cambiarProducto.EMPLEADO_idEMPLEADO = resultado.getInt("EMPLEADO_idEMPLEADO");
							cambiarProducto.STOCK_STAND_idStand_Original = resultado.getInt("stock_STAND_idStand");
							cambiarProducto.STOCK_STAND_ZONA_idzona_Original = resultado.getInt("stock_STAND_ZONA_idzona");
							valorIDVenta = resultado.getInt("idventa");

					}
					String consultaEnJugueteCanje = "SELECT precio,Nombre FROM juguete WHERE idJuguete = " + cambiarProducto.STOCK_JUGUETE_idJuguete_Nuevo+";";
					PreparedStatement sentenciaJugueteCanje = conexion.prepareStatement(consultaEnJugueteCanje);
					ResultSet resultado1 = sentenciaJugueteCanje.executeQuery();
					if(resultado1.next()) {
						precioUnidadJugueteCanjeado = resultado1.getDouble("precio");
						nombreJugueteCanjeado = resultado1.getString("Nombre");
					}
					String consultaEnJuguete = "SELECT precio,Nombre FROM juguete WHERE idJuguete = " + cambiarProducto.STOCK_JUGUETE_idJuguete_Original+";";
					PreparedStatement sentenciaJuguete = conexion.prepareStatement(consultaEnJuguete);
					ResultSet resultado2 = sentenciaJuguete.executeQuery();
					if(resultado2.next()) {
						precioUnidadJugueteDevuelto = resultado2.getDouble("precio");
					}
					String consultaEnStockJCanjeado = "SELECT STAND_idStand,STAND_ZONA_idzona,CANTIDAD FROM stock WHERE JUGUETE_idJuguete = " +  cambiarProducto.STOCK_JUGUETE_idJuguete_Nuevo+";";
					PreparedStatement sentenciaStockJCanjeado = conexion.prepareStatement(consultaEnStockJCanjeado);
					ResultSet resultadoStockJCanjeado = sentenciaStockJCanjeado.executeQuery();
					if(resultadoStockJCanjeado.next()) {
						cantidadEnStockJCanjeado =resultadoStockJCanjeado.getInt("CANTIDAD");
						cambiarProducto.STOCK_STAND_idStand_Nuevo = resultadoStockJCanjeado.getInt("STAND_idStand");
						cambiarProducto.STOCK_STAND_ZONA_idzona_Nuevo = resultadoStockJCanjeado.getInt("STAND_ZONA_idzona");
					}
					String consultaEnStockJDevuelto = "SELECT CANTIDAD FROM stock WHERE JUGUETE_idJuguete = " +  cambiarProducto.STOCK_JUGUETE_idJuguete_Original+";";
					PreparedStatement sentenciaStockJDevuelto = conexion.prepareStatement(consultaEnStockJDevuelto);
					ResultSet resultadoStockJDevuelto = sentenciaStockJDevuelto.executeQuery();
					if(resultadoStockJDevuelto.next()) {
						cantidadEnStockJDevuelto =resultadoStockJDevuelto.getInt("CANTIDAD");
					}
					do {
						mostrarStock(cambiarProducto.STOCK_JUGUETE_idJuguete_Nuevo);
						System.out.println("¿ Cuant@s "+nombreJugueteCanjeado+" va a llevar el cliente ?");
						cantidadCanjeadoAllevar = Jugueteria.controlDeErroresInt();
						if (cantidadEnStockJCanjeado < cantidadCanjeadoAllevar) {
							System.err.println("Quieres intercambiar más cantidad de las que hay en Stock... introduce una cantidad que tengamos.");
							do {
								System.out.println("¿Quieres seguir realizando el cambio de juguete? si (1) o no (2)");
								opcion = Jugueteria.controlDeErroresInt();
								if(opcion==2) {
									banderaCambio=true;
									banderaSalirCambio=true;
									System.err.println("No se ha realizado el cambio del producto.");
								}else if (opcion==1) {
									banderaSalirCambio=true;
								}else {
									System.err.println("Introduce 1 o 2.");
								}
							}while(!banderaSalirCambio);
						}else {
							totalJugueteCanjeado = precioUnidadJugueteCanjeado*cantidadCanjeadoAllevar;
							if (montoTotalCompra < totalJugueteCanjeado) {
								System.err.println("Has excedido el precio de tu compra anterior vuelve a introducir una cantidad menor del nuevo juguete para que su precio no sobrepase, ya que no se realizan devoluciones de dinero.");
								do {
									System.out.println("¿Quieres seguir realizando el cambio de juguete? si (1) o no (2)");
									opcion = Jugueteria.controlDeErroresInt();
									if(opcion==2) {
										banderaCambio=true;
										banderaSalirCambio=true;
										System.out.println("No se ha realizado el cambio del producto.");
									}else if (opcion==1) {
										banderaSalirCambio=true;
									}else {
										System.err.println("Introduce 1 o 2.");
									}
								}while(!banderaSalirCambio);
							}else {
								do {
									System.out.println("¿Se va a realizar el intercambio estas seguro? si (1) o no (2)");
									opcion = Jugueteria.controlDeErroresInt();
									if(opcion==2) {
										banderaCambio=true;
										banderaSalirCambio=true;
										System.out.println("No se ha realizado el cambio del producto.");
									}else if (opcion==1) {
										banderaSalirCambio=true;
										cantidadEnStockJCanjeado -= cantidadCanjeadoAllevar;
										cantidadJuguetesDevueltos = (int) (montoTotalCompra/precioUnidadJugueteDevuelto);
										cantidadEnStockJDevuelto += cantidadJuguetesDevueltos;
										banderaCambio = true;
									}else {
										System.err.println("Introduce 1 o 2.");
									}
								}while(!banderaSalirCambio);
								
							}
						}
					}while(!banderaCambio);
					String actualizarInventarioJugueteCanjeado ="UPDATE stock set CANTIDAD = "+cantidadEnStockJCanjeado+" WHERE JUGUETE_idJuguete = "+cambiarProducto.STOCK_JUGUETE_idJuguete_Nuevo+";";
					PreparedStatement sentenciaActJugueteCanjeado = conexion.prepareStatement(actualizarInventarioJugueteCanjeado);
					sentenciaActJugueteCanjeado.executeUpdate();
					String actualizarInventarioJugueteDevuelto ="UPDATE stock set CANTIDAD = "+cantidadEnStockJDevuelto+" WHERE JUGUETE_idJuguete = "+cambiarProducto.STOCK_JUGUETE_idJuguete_Original+";";
					PreparedStatement sentenciaActuJugueteDevuelto = conexion.prepareStatement(actualizarInventarioJugueteDevuelto);
					sentenciaActuJugueteDevuelto.executeUpdate();
					
					int funcionoRegistro = registrarCambio(cambiarProducto);
					if (funcionoRegistro ==1){
						eliminarObjetos("venta","idventa",valorIDVenta);
					}
				}else {
					return verificacion;
				}
			}
		}catch(SQLException o) {
			o.getStackTrace();
		}
		return verificacion;
	}
	private static int registrarCambio(Cambio cambiarProducto) {
		int comprobacion = 0;
		try {
			Connection conexion = comprobarConexion();
			String consultaCreacionVenta = "INSERT INTO `cambio` (`idCAMBIO`,`MOTIVO`,`Fecha`,`STOCK_STAND_idStand_Original`,`STOCK_STAND_ZONA_idzona_Original`,`STOCK_JUGUETE_idJuguete_Original`,`STOCK_STAND_idStand_Nuevo`, `STOCK_STAND_ZONA_idzona_Nuevo`,`STOCK_JUGUETE_idJuguete_Nuevo`,`EMPLEADO_idEMPLEADO`) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement sentenciaVenta = conexion.prepareStatement(consultaCreacionVenta);
			sentenciaVenta.setInt(1,cambiarProducto.idCambio);
			sentenciaVenta.setString(2,cambiarProducto.Motivo);
			sentenciaVenta.setDate(3,cambiarProducto.fecha);
			sentenciaVenta.setInt(4,cambiarProducto.STOCK_STAND_idStand_Original);
			sentenciaVenta.setInt(5,cambiarProducto.STOCK_STAND_ZONA_idzona_Original);
			sentenciaVenta.setInt(6,cambiarProducto.STOCK_JUGUETE_idJuguete_Original);
			sentenciaVenta.setInt(7, cambiarProducto.STOCK_STAND_idStand_Nuevo);
			sentenciaVenta.setInt(8,cambiarProducto.STOCK_STAND_ZONA_idzona_Nuevo);
			sentenciaVenta.setInt(9,cambiarProducto.STOCK_JUGUETE_idJuguete_Nuevo);
			sentenciaVenta.setInt(10,cambiarProducto.EMPLEADO_idEMPLEADO);

			int ventaRealizada = sentenciaVenta.executeUpdate();
			if (ventaRealizada >0) {
				System.out.println("Se ha realizado correctamente el cambio.");
				comprobacion = 1;
				return comprobacion;
			}
		}catch(SQLException o) {
			o.printStackTrace();
		}
		return comprobacion;
	}
	public static void VentasPorMes() {
	    double totalEmpleado = 0;
	    int mes = 0,idEmpleado=0;
	    boolean bandera = false;
	    try {
	        Connection conexion = comprobarConexion();
	        String VentaPorMes = "SELECT idventa, Fecha, Monto, juguete.Nombre as Juguete FROM venta JOIN juguete ON stock_JUGUETE_idJuguete = juguete.idJuguete WHERE MONTH(Fecha) =  ?;";
	        System.out.println("Ventas realizadas por todo el més");
	        do {
		        System.out.print("Introduce el MES (1-12): ");
		        mes = Jugueteria.controlDeErroresInt();
		        if (mes > 0 && mes <= 12) {
		        	bandera=true;
		        }else {
		        	System.err.println("Escribe un número entre el 1 al 12.");
		        }
	        }while(!bandera);
	        
	        PreparedStatement sentenciaMes = conexion.prepareStatement(VentaPorMes);
	        sentenciaMes.setInt(1, mes);
	        ResultSet resultado = sentenciaMes.executeQuery();
	        
	        System.out.println("\nResultados para el empleado ID " + idEmpleado + ":");
	        
	        boolean hayDatos = false;
	        while(resultado.next()) {
	            hayDatos = true;
	            int idVenta = resultado.getInt("idventa");
	            Date fecha = resultado.getDate("Fecha");
	            String idJuguete = resultado.getString("Juguete");
	            double montoPorVenta = resultado.getDouble("Monto");
	            System.out.println("Venta ID: " +idVenta+" a Fecha: "+fecha+",  juguete: "+idJuguete+", de monto: "+montoPorVenta+"€");
	            totalEmpleado += resultado.getDouble("Monto");
	        }
	        if (!hayDatos) {
	            System.out.println("Este empleado no realizó ventas en el periodo seleccionado.");
	        } else {
	            System.out.println("TOTAL GENERADO POR EMPLEADO: "+totalEmpleado+"€");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
}
	public static void VentasEmpleadoPorMes() {
		    double totalEmpleado = 0;
		    int mes = 0,idEmpleado=0;
		    boolean bandera = false;
		    try {
		        Connection conexion = comprobarConexion();
		        String VentaPorMes = "SELECT v.idventa, v.Fecha, v.Monto, j.Nombre as Juguete " +
		                     "FROM venta v " +
		                     "JOIN juguete j ON v.stock_JUGUETE_idJuguete = j.idJuguete " +
		                     "WHERE v.EMPLEADO_idEMPLEADO = ? AND MONTH(v.Fecha) =  ?";
		        System.out.println("--- VENTAS POR EMPLEADO Y MES ---");
		        System.out.print("Introduce el ID del Empleado: ");
		        idEmpleado = Jugueteria.controlDeErroresInt();
		        do {
			        System.out.print("Introduce el MES (1-12): ");
			        mes = Jugueteria.controlDeErroresInt();
			        if (mes > 0 && mes <= 12) {
			        	bandera=true;
			        }else {
			        	System.err.println("Escribe un número entre el 1 al 12.");
			        }
		        }while(!bandera);
		        
		        PreparedStatement sentenciaMes = conexion.prepareStatement(VentaPorMes);
		        sentenciaMes.setInt(1, idEmpleado);
		        sentenciaMes.setInt(2, mes);
		        ResultSet resultado = sentenciaMes.executeQuery();
		        
		        System.out.println("\nResultados para el empleado ID " + idEmpleado + ":");
		        
		        boolean hayDatos = false;
		        while(resultado.next()) {
		            hayDatos = true;
		            int idVenta = resultado.getInt("idventa");
		            Date fecha = resultado.getDate("Fecha");
		            String idJuguete = resultado.getString("Juguete");
		            double montoPorVenta = resultado.getDouble("Monto");
		            System.out.println("Venta ID: " +idVenta+" a Fecha: "+fecha+",  juguete: "+idJuguete+", de monto: "+montoPorVenta+"€");
		            totalEmpleado += resultado.getDouble("Monto");
		        }
		        if (!hayDatos) {
		            System.out.println("Este empleado no realizó ventas en el periodo seleccionado.");
		        } else {
		            System.out.println("TOTAL GENERADO POR EMPLEADO: "+totalEmpleado+"€");
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
	}
	public static void mostrarCambios() {
		try {
			Connection conexion = comprobarConexion();
			String consulta = "SELECT * FROM cambio;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery(consulta);
			System.out.println("");
			while (resultado.next()){
				int idcambio = resultado.getInt("idCAMBIO");
				String MOTIVO = resultado.getString("MOTIVO");
				String Fecha = resultado.getString("Fecha");
				int idEmpleado = resultado.getInt("EMPLEADO_idEMPLEADO");
				int idStandOriginal = resultado.getInt("STOCK_STAND_idStand_Original");
				int idStandNuevo = resultado.getInt("STOCK_STAND_idStand_Nuevo");
				int idZonaOriginal = resultado.getInt("STOCK_STAND_ZONA_idzona_Original");
				int idZonaNuevo = resultado.getInt("STOCK_STAND_ZONA_idzona_Nuevo");
				int idJugueteOriginal = resultado.getInt("STOCK_JUGUETE_idJuguete_Original");
				int idJugueteNuevo= resultado.getInt("STOCK_JUGUETE_idJuguete_Nuevo");
				System.out.println("\nID cambio = "+idcambio+", tiene de motivo = "+MOTIVO+" a "+Fecha+".\n Se devolvio el juguete de ID = "+idJugueteOriginal+", al stand "+idStandOriginal+", de la zona "+idZonaOriginal+" y se llevo el juguete de ID "+idJugueteNuevo+", del stand"+idStandNuevo+", de la zona"+idZonaNuevo+" y realizo el cambio el empleado de ID = "+idEmpleado);
			}
			System.out.println("");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public static void reporteEmpleadosTopVentas() {
	    try {
	        Connection conexion = comprobarConexion();
	        String consultaEmpleadosVentas = "SELECT e.Nombre, COUNT(v.idventa) as TotalVentas, SUM(v.Monto) as TotalGenerado FROM venta v INNER JOIN empleado e ON v.EMPLEADO_idEMPLEADO = e.idEMPLEADO GROUP BY e.idEMPLEADO, e.Nombre ORDER BY TotalVentas DESC";
	        PreparedStatement sentenciasEmpleadosVentas = conexion.prepareStatement(consultaEmpleadosVentas);
	        ResultSet resultadoEmpVentas = sentenciasEmpleadosVentas.executeQuery();
	        System.out.println("Empleado que mas ventas han hecho.");
	        int posicion = 1;
	        boolean hayDatos = false;
	        while(resultadoEmpVentas.next()) {
	            hayDatos = true;
	            String nombre = resultadoEmpVentas.getString("Nombre");
	            int cantidad = resultadoEmpVentas.getInt("TotalVentas");
	            double dinero = resultadoEmpVentas.getDouble("TotalGenerado");
	            
	            System.out.println(posicion+"º posicion para "+nombre+", con "+cantidad+" ventas, y un total generado de = "+dinero);
	            posicion++;
	        }
	        if (!hayDatos) {
	            System.out.println("Aún no se han registrado ventas en el sistema.");
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void ProductoMasVendido() {
	    try {
	        Connection conexion = comprobarConexion();
	        String productoMasVendido = "SELECT juguete.Nombre, juguete.Precio, COUNT(venta.idventa) as CantidadVentas , SUM(venta.Monto) Monto FROM venta inner JOIN juguete  ON venta.stock_JUGUETE_idJuguete = juguete.idJuguete GROUP BY juguete.idJuguete, juguete.Nombre , juguete.Precio ORDER BY CantidadVentas DESC LIMIT 3"; 
	                     
	        PreparedStatement sentencia = conexion.prepareStatement(productoMasVendido);
	        ResultSet resultado = sentencia.executeQuery();
	        
	        System.out.println("\n===== Los 3 productos más Vendidos =====");
	        int ranking = 1;
	        while(resultado.next()) {
	            String nombre = resultado.getString("Nombre");
	            int cantidad = 0;
	            double precio = resultado.getInt("Precio");
	            double monto = resultado.getDouble("Monto");
	            cantidad = (int) (monto/precio);
	            System.out.println(ranking+"º "+nombre+" (Vendido " + cantidad + " veces)");
	            ranking++;
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	public static void ListadoProductosPorPrecio() {
		try {
			Connection conexion = comprobarConexion();
			String consulta = "SELECT * FROM juguete order by Precio DESC;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			ResultSet resultado = sentencia.executeQuery(consulta);
			System.out.println("");
			while (resultado.next()){
				int idJuguete = resultado.getInt("idJuguete");
				String nombre = resultado.getString("Nombre");
				String descripcion = resultado.getString("Descripcion");
				double precio = resultado.getDouble("Precio");
				String categoria = resultado.getString("Categoria");
				System.out.println("Juguete de ID = "+idJuguete+", de nombre = "+nombre+", Descripcion = "+descripcion+", precio = "+precio+", Categoria = "+categoria);
			}
			System.out.println("");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int obtencionCargoInicioSesion() {
		int cargo=0;
		try {
			Connection conexion = comprobarConexion();
			String consulta = "SELECT Cargo, Nombre, idEMPLEADO from empleado where idEMPLEADO = ?;";
			PreparedStatement sentencia = conexion.prepareStatement(consulta);
			System.out.print("\nIntroduce tu Id de empleado : ");
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
	public static void crearModeloDeDatos() {
	    // 1. URL ESPECIAL: Nos conectamos al servidor raíz, NO a la base de datos 'jugueteria'
	    // Quitamos "/jugueteria" de la URL temporalmente para poder crearla si no existe.
	    String urlServer = "jdbc:mysql://localhost:3306/?useSSL=false&allowPublicKeyRetrieval=true";
	    
	    try (Connection conexion = DriverManager.getConnection(urlServer, usuario, passwordINSTI);
	         java.sql.Statement sentencia = conexion.createStatement()) {

	        sentencia.execute("SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0");
	        sentencia.execute("SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0");
	        sentencia.executeUpdate("CREATE SCHEMA IF NOT EXISTS `jugueteriaSamuelCarias` DEFAULT CHARACTER SET utf8mb3");
	        sentencia.execute("USE `jugueteriaSamuelCarias`");

	        sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS `zona` (" +
	                "`idzona` INT NOT NULL, " +
	                "`Nombre` VARCHAR(45) NULL DEFAULT NULL, " +
	                "`Descripcion` VARCHAR(150) NULL DEFAULT NULL, " +
	                "PRIMARY KEY (`idzona`)) " +
	                "ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3");

	        sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS `empleado` (" +
	                "`idEMPLEADO` INT UNSIGNED NOT NULL AUTO_INCREMENT, " +
	                "`Nombre` VARCHAR(45) NULL DEFAULT NULL, " +
	                "`Cargo` ENUM('jefe', 'cajero') NULL DEFAULT NULL, " +
	                "`Fecha_ingreso` DATE NULL DEFAULT NULL, " +
	                "PRIMARY KEY (`idEMPLEADO`)) " +
	                "ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3");

	        sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS `juguete` (" +
	                "`idJuguete` INT NOT NULL, " +
	                "`Nombre` VARCHAR(45) NULL DEFAULT NULL, " +
	                "`Descripcion` VARCHAR(150) NULL DEFAULT NULL, " +
	                "`Precio` DOUBLE NULL DEFAULT NULL, " +
	                "`Cantidad_stock` INT UNSIGNED NOT NULL, " +
	                "`Categoria` ENUM('Pelota', 'Muñeca', 'Construccion', 'Juego_De_Mesa', 'Plastilina', 'Peluche', 'Otro') NULL DEFAULT 'Otro', " +
	                "PRIMARY KEY (`idJuguete`)) " +
	                "ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3");

	        sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS `stand` (" +
	                "`idStand` INT NOT NULL, " +
	                "`Nombre` VARCHAR(45) NULL DEFAULT NULL, " +
	                "`Descripcion` VARCHAR(150) NULL DEFAULT NULL, " +
	                "`ZONA_idzona` INT NOT NULL, " +
	                "PRIMARY KEY (`idStand`, `ZONA_idzona`), " +
	                "INDEX `fk_STAND_ZONA_idx` (`ZONA_idzona` ASC) VISIBLE, " +
	                "CONSTRAINT `fk_STAND_ZONA` " +
	                "FOREIGN KEY (`ZONA_idzona`) " +
	                "REFERENCES `zona` (`idzona`) " +
	                "ON DELETE CASCADE) " +
	                "ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3");

	        sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS `stock` (" +
	                "`STAND_idStand` INT NOT NULL, " +
	                "`STAND_ZONA_idzona` INT NOT NULL, " +
	                "`JUGUETE_idJuguete` INT NOT NULL, " +
	                "`CANTIDAD` INT NULL DEFAULT NULL, " +
	                "PRIMARY KEY (`STAND_idStand`, `STAND_ZONA_idzona`, `JUGUETE_idJuguete`), " +
	                "INDEX `fk_STAND_has_JUGUETE_JUGUETE1_idx` (`JUGUETE_idJuguete` ASC) VISIBLE, " +
	                "INDEX `fk_STAND_has_JUGUETE_STAND1_idx` (`STAND_idStand` ASC, `STAND_ZONA_idzona` ASC) VISIBLE, " +
	                "CONSTRAINT `fk_STAND_has_JUGUETE_JUGUETE1` " +
	                "FOREIGN KEY (`JUGUETE_idJuguete`) " +
	                "REFERENCES `juguete` (`idJuguete`) " +
	                "ON DELETE CASCADE, " +
	                "CONSTRAINT `fk_STAND_has_JUGUETE_STAND1` " +
	                "FOREIGN KEY (`STAND_idStand` , `STAND_ZONA_idzona`) " +
	                "REFERENCES `stand` (`idStand` , `ZONA_idzona`) " +
	                "ON DELETE CASCADE) " +
	                "ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3");

	        sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS `venta` (" +
	                "`idventa` INT NOT NULL AUTO_INCREMENT, " +
	                "`Fecha` DATE NULL DEFAULT NULL, " +
	                "`Monto` DOUBLE NULL DEFAULT NULL, " +
	                "`tipo_pago` ENUM('efectivo', 'tarjeta', 'paypal') NULL DEFAULT NULL, " +
	                "`EMPLEADO_idEMPLEADO` INT UNSIGNED NOT NULL, " +
	                "`stock_STAND_idStand` INT NOT NULL, " +
	                "`stock_STAND_ZONA_idzona` INT NOT NULL, " +
	                "`stock_JUGUETE_idJuguete` INT NOT NULL, " +
	                "PRIMARY KEY (`idventa`), " +
	                "INDEX `fk_VENTA_EMPLEADO1_idx` (`EMPLEADO_idEMPLEADO` ASC) VISIBLE, " +
	                "INDEX `fk_venta_stock1_idx` (`stock_STAND_idStand` ASC, `stock_STAND_ZONA_idzona` ASC, `stock_JUGUETE_idJuguete` ASC) VISIBLE, " +
	                "CONSTRAINT `fk_VENTA_EMPLEADO1` " +
	                "FOREIGN KEY (`EMPLEADO_idEMPLEADO`) " +
	                "REFERENCES `empleado` (`idEMPLEADO`), " +
	                "CONSTRAINT `fk_venta_stock1` " +
	                "FOREIGN KEY (`stock_STAND_idStand` , `stock_STAND_ZONA_idzona` , `stock_JUGUETE_idJuguete`) " +
	                "REFERENCES `stock` (`STAND_idStand` , `STAND_ZONA_idzona` , `JUGUETE_idJuguete`) " +
	                "ON DELETE NO ACTION ON UPDATE NO ACTION) " +
	                "ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3");

	        sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS `cambio` (" +
	                "`idCAMBIO` INT NOT NULL AUTO_INCREMENT, " +
	                "`MOTIVO` VARCHAR(150) NULL DEFAULT NULL, " +
	                "`Fecha` DATE NULL DEFAULT NULL, " +
	                "`STOCK_STAND_idStand_Original` INT NOT NULL, " +
	                "`STOCK_STAND_ZONA_idzona_Original` INT NOT NULL, " +
	                "`STOCK_JUGUETE_idJuguete_Original` INT NOT NULL, " +
	                "`STOCK_STAND_idStand_Nuevo` INT NOT NULL, " +
	                "`STOCK_STAND_ZONA_idzona_Nuevo` INT NOT NULL, " +
	                "`STOCK_JUGUETE_idJuguete_Nuevo` INT NOT NULL, " +
	                "`EMPLEADO_idEMPLEADO` INT UNSIGNED NOT NULL, " +
	                "PRIMARY KEY (`idCAMBIO`), " +
	                "CONSTRAINT `fk_CAMBIO_EMPLEADO1` FOREIGN KEY (`EMPLEADO_idEMPLEADO`) REFERENCES `empleado` (`idEMPLEADO`), " +
	                "CONSTRAINT `fk_CAMBIO_STOCK1` FOREIGN KEY (`STOCK_STAND_idStand_Original`, `STOCK_STAND_ZONA_idzona_Original`, `STOCK_JUGUETE_idJuguete_Original`) REFERENCES `stock` (`STAND_idStand`, `STAND_ZONA_idzona`, `JUGUETE_idJuguete`), " +
	                "CONSTRAINT `fk_CAMBIO_STOCK2` FOREIGN KEY (`STOCK_STAND_idStand_Nuevo`, `STOCK_STAND_ZONA_idzona_Nuevo`, `STOCK_JUGUETE_idJuguete_Nuevo`) REFERENCES `stock` (`STAND_idStand`, `STAND_ZONA_idzona`, `JUGUETE_idJuguete`)) " +
	                "ENGINE = InnoDB DEFAULT CHARACTER SET = utf8mb3");
	        
	        sentencia.execute("SET FOREIGN_KEY_CHECKS = 1");
	        sentencia.execute("SET UNIQUE_CHECKS = 1");

	        System.out.println("Base de datos 'jugueteriaSamuelCarias' y tablas verificadas correctamente.");

	    } catch (SQLException e) {
	        System.err.println("Error crítico creando el modelo de datos:");
	        e.printStackTrace();
	    }
	}
}
