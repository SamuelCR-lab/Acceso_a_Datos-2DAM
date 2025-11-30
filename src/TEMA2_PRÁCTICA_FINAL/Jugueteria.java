package TEMA2_PRÁCTICA_FINAL;

import java.util.ArrayList;
import java.util.Scanner;


public class Jugueteria {
	
	
	static Scanner entrada = new Scanner(System.in); 
	static ArrayList<Empleado>empleadoNuevo = new ArrayList<Empleado>();
	static ArrayList<Juguete>jugueteNuevo = new ArrayList<Juguete>();
	
	
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
				entrada.next();
			}
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
	
	
	private static void menuCajero() {
		boolean banderaSalida = false;
		System.out.println("\n========= Bienvenido al menú "+Funciones_y_Consultas.nombreUsuario+" =========");
		do {
			System.out.println("\n\t1. Registrar un nuevo juguete.\n"
							+ "\t2. Modificar datos de juguetes.\n"
							+ "\t3. Eliminar Juguetes.\n"
							+ "\t4. Realizar una Venta.\n"
							+ "\t5. Realizar una Devolucion.\n"
							+ "\t6. Juguetes en un Stand.\n"
							+ "\t7. Salir.\n");
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
						idEliminar = Jugueteria.controlDeErroresInt();
						if ((idEliminar > 0)&&(idEliminar<=idFinal)) {
							bandera = true;
						}else {
							System.out.println("Introduce un Id que exista en la base de datos");
						}
					}while(!bandera);
					
					Funciones_y_Consultas.eliminarObjetos("juguete", "idJuguete",idEliminar);
					break;
				case 4:
					Funciones_y_Consultas.mostrarJuguetes();
					Venta.realizarVenta();
					break;
				case 5:
					Cambio.Devolucion();
					break;
				case 6:
					
					break;
				case 7:
					System.out.println("Saliendo...");
					banderaSalida=true;
					break;
				default:
						
			}
		}while(!banderaSalida);
	}
	
	private static void menuJefe() {
		boolean banderaSalida = false;
		System.out.println("\n========= Bienvenido al menú "+Funciones_y_Consultas.nombreUsuario+" =========");
		do {
			System.out.println("\n\t1. Crear un nuevo empleado.\n"
							+ "\t2. Modificar datos de un empleado.\n"
							+ "\t3. Eliminar empleado.\n"
							+ "\t4. Los empleados que más venden.\n"
							+ "\t5. Ver el Producto más vendido.\n"
							+ "\t6. Ventas realizadas en un mes.\n"
							+ "\t7. Ventas realizadas por un empleado en un mes.\n"
							+ "\t8. Cambios de Objetos y motivos.\n"
							+ "\t9. Salir.\n");
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
					idEliminar = Jugueteria.controlDeErroresInt();
					if ((idEliminar > 0)&&(idEliminar<=idFinal)) {
						bandera = true;
					}else {
						System.out.println("Introduce un Id que exista en la base de datos");
					}
				}while(!bandera);
				
				Funciones_y_Consultas.eliminarObjetos("empleado", "idEMPLEADO",idEliminar);
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				System.out.println("Saliendo...");
				banderaSalida=true;
				break;
			default:
					
		}
	}while(!banderaSalida);
}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Funciones_y_Consultas.ComprobacionBaseDatos();
		System.out.println("\n======== Bienvenido a la Jugueteria S <3 S ========");
		int empleado = Funciones_y_Consultas.obtencionCargoInicioSesion();
		switch (empleado) {
			case 1:
				menuCajero();
				break;
			case 2:
				menuJefe();
				break;
			default:
				System.out.println("ERROR, escribe un id correspondiente");
		}
		System.out.println("Recuerda, siempre con una sonrisa para los clientes.\nJugueteria S <3 S");
	}
	
	
}
