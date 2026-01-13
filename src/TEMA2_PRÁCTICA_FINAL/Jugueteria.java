package TEMA2_PRÁCTICA_FINAL;

import java.util.Scanner;


public class Jugueteria {
	static Scanner entrada = new Scanner(System.in); 
	public static int controlDeErroresInt() {
		int entero=0;
		boolean datosCorrectos=true;
		do {
			if(entrada.hasNextInt()) {
				entero=entrada.nextInt();
				if (entero >= 0) {
					datosCorrectos=false;
				
				} else {
	                System.err.println("ERROR. El número no puede ser negativo.");
	            }
			}else {
				System.err.println("ERROR. Introduce un número");
				
			}
			entrada.nextLine();
		}while(datosCorrectos);	
		return entero;
	}
	public static double controlDeErroresDouble() {
		double Double=0;
		boolean datosCorrectos=true;
		do {
			if(entrada.hasNextDouble()) {
				Double=entrada.nextDouble();
				if (Double >= 0) {
					datosCorrectos=false;
				} else {
	                System.err.println("ERROR. El número no puede ser negativo.");
	            }
			}else {
				System.err.println("ERROR. Introduce un número");
				entrada.next();
			}
		}while(datosCorrectos);	
		return Double;
	}
	
	
	private static void menuJuguetes() {
		boolean banderaSalida = false;
		System.out.println("\n========= Bienvenido al menú "+Funciones_y_Consultas.nombreUsuario+" =========");
		do {
			System.out.println("\n\t1. Registrar un nuevo juguete.\n"
							+ "\t2. Modificar datos de juguetes.\n"
							+ "\t3. Eliminar Juguetes.\n"
							+ "\t4. Salir.\n");
			System.out.print("Elige una opcion: ");
			int opcion = controlDeErroresInt();
			switch (opcion) {
				case 1:
					Funciones_y_Consultas.mostrarJuguetes();
					Juguete.nuevoJuguete();
					break;
				case 2:
					Funciones_y_Consultas.updateDatosJuguete();
					break;
				case 3:
					boolean bandera = false;
					int idEliminar =0, idFinal = Funciones_y_Consultas.comprobacionDeIDs("juguete", "idJuguete");
					do {
						System.out.println("Introduce el ID del juguete a eliminar: ");
						idEliminar = controlDeErroresInt();
						if ((idEliminar > 0)&&(idEliminar<=idFinal)) {
							bandera = true;
						}else {
							System.out.println("Introduce un Id que exista en la base de datos");
						}
					}while(!bandera);
					
					Funciones_y_Consultas.eliminarObjetos("juguete", "idJuguete",idEliminar);
					break;
				case 4:
					System.out.println("Saliendo al menú principal.");
					banderaSalida=true;
					break;
				default:
					System.err.println("Introduce un número del 1 al 4.");
			}
		}while(!banderaSalida);
	}
	private static void menuEmpleados() {
		boolean banderaSalida = false;
		System.out.println("\n========= Bienvenido al menú informativo de la tienda"+Funciones_y_Consultas.nombreUsuario+" =========");
		do {
			System.out.println("\n\t1. Registrar un nuevo empleado.\n"
							+ "\t2. Modificar datos de un empleado.\n"
							+ "\t3. Eliminar empleado.\n"
							+ "\t4. Salir.\n");
			System.out.print("Elige una opcion: ");
			int opcion = controlDeErroresInt();
			switch (opcion) {
				case 1:
					Empleado.nuevoEmpleado();
					break;
				case 2:
					Funciones_y_Consultas.updateDatosEmpleados();
					break;
				case 3:
					boolean bandera = false;
					int idEliminar =0, idFinal = Funciones_y_Consultas.comprobacionDeIDs("empleado", "idEMPLEADO");
					do {
						System.out.println("Introduce el ID del empleado a eliminar: ");
						idEliminar = controlDeErroresInt();
						if ((idEliminar > 0)&&(idEliminar<=idFinal)) {
							bandera = true;
						}else {
							System.out.println("Introduce un Id que exista en la base de datos");
						}
					}while(!bandera);
					
					Funciones_y_Consultas.eliminarObjetos("empleado", "idEMPLEADO",idEliminar);
					break;
				case 4:
					System.out.println("Saliendo al menú principal.");
					banderaSalida=true;
					break;
				default:
					System.err.println("Introduce un número del 1 al 4.");
			}
		}while(!banderaSalida);
	}
	private static void menuVentas() {
		boolean banderaSalida = false;
		System.out.println("\n========= Bienvenido al menú "+Funciones_y_Consultas.nombreUsuario+" =========");
		do {
			System.out.println("\n\t1. Realizar una Venta.\n"
							+ "\t2. Realizar una Devolucion.\n"
							+ "\t3. Ver el producto más vendido.\n"
							+ "\t4. Los empleados que más venden.\n"
							+ "\t5. Salir.\n");
			System.out.print("Elige una opcion: ");
			int opcion = controlDeErroresInt();
			switch (opcion) {
				case 1:
					Funciones_y_Consultas.mostrarJuguetes();
					Venta.realizarVenta();
					break;
				case 2:
					Cambio.Devolucion();
					break;
				case 3:
					Funciones_y_Consultas.ProductoMasVendido();
					break;
				case 4:
					Funciones_y_Consultas.reporteEmpleadosTopVentas();
					break;
				case 5:
					System.out.println("Saliendo al menú principal.");
					banderaSalida=true;
					break;
				default:
					System.err.println("Introduce un número del 1 al 5.");
			}
		}while(!banderaSalida);
	}
	private static void menuTiendas() {
		boolean banderaSalida = false;
		System.out.println("\n========= Bienvenido al menú informativo de la tienda "+Funciones_y_Consultas.nombreUsuario+" =========");
		do {
			System.out.println("\n\t1. Todos los juguetes que están disponibles en un stand específicos.\n"
							+ "\t2. Ventas realizados ordenadas por mes.\n"
							+ "\t3. Ventas realizadas por un empleado en un mes.\n"
							+ "\t4. Cambios de Objetos y motivos.\n"
							+ "\t5. Lista de los productos ordenados por precio de mayor a menor.\n"
							+ "\t6. Salir.\n");
			System.out.print("Elige una opcion: ");
			int opcion = controlDeErroresInt();
			switch (opcion) {
				case 1:
					Funciones_y_Consultas.mostrarJugueteStand();
					break;
				case 2:
					Funciones_y_Consultas.VentasPorMes();
					break;
				case 3:
					Funciones_y_Consultas.VentasEmpleadoPorMes();
					break;
				case 4:
					Funciones_y_Consultas.mostrarCambios();
					break;
				case 5:
					Funciones_y_Consultas.ListadoProductosPorPrecio();
					break;
				case 6:
					System.out.println("Saliendo...");
					banderaSalida=true;
					break;
				default:
					System.err.println("Introduce un número del 1 al 7.");
			}
		}while(!banderaSalida);
	}
	private static void menuPrincipal() {
		boolean banderaSalida = false;
		System.out.println("\n========= Bienvenido al menú "+Funciones_y_Consultas.nombreUsuario+" =========");
		do {
			System.out.println("\n\t1. Juguetes.\n"
							+ "\t2. Empleado.\n"
							+ "\t3. Ventas.\n"
							+ "\t4. Datos de la tienda.\n"
							+ "\t5. Salir.\n");
			System.out.print("Elige una opcion: ");
			int opcion = controlDeErroresInt();
			switch (opcion) {
				case 1:
					menuJuguetes();
					break;
				case 2:
					menuEmpleados();
					break;
				case 3:
					menuVentas();
					break;
				case 4:
					menuTiendas();
					break;
				case 5:
					System.out.println("Saliendo...");
					banderaSalida=true;
					break;
				default:
					System.err.println("Introduce un número del 1 al 5.");
			}
		}while(!banderaSalida);
	}
	
	
	public static void main(String[] args) {
		Funciones_y_Consultas.crearModeloDeDatos();
		Funciones_y_Consultas.ComprobacionBaseDatos();
		System.out.println("\n======== Bienvenido a la Jugueteria S <3 S ========");
		int empleado = Funciones_y_Consultas.obtencionCargoInicioSesion();
		switch (empleado) {
			case 1:
				menuPrincipal();
				break;
			case 2:
				menuPrincipal();
				break;
			default:
				System.err.println("ERROR, escribe un id correspondiente");
		}
		System.out.println("Recuerda, siempre con una sonrisa para los clientes.\nJugueteria S <3 S");
	}
	
	
}
