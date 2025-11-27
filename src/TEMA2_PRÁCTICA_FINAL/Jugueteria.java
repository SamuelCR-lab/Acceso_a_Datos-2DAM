package TEMA2_PRÁCTICA_FINAL;

import java.util.ArrayList;
import java.util.Scanner;

import EXAMEN_PRACTICA_FINAL_FICHEROS_2025.Empleado;

public class Jugueteria {
	
	
	static Scanner entrada = new Scanner(System.in); 
	ArrayList<Empleado>empleadosO = new ArrayList<Empleado>();
	
	
	
	public static int controlDeErrores() {
		int idEmpleado=0;
		boolean datosCorrectos=true;
		do {
			if(entrada.hasNextInt()) {
				idEmpleado=entrada.nextInt();
				if (idEmpleado >= 0) {
					datosCorrectos=false;
				} else {
	                System.err.println("ERROR. El número no puede ser negativo.");
	            }
			}else {
				System.err.println("ERROR. Introduce un número");
			}
		}while(datosCorrectos);	
		return idEmpleado;
	}
	
	private static int comprobacionEmpleados() {
		return 0;
		
	}
	
	private static void menuCajero() {
		System.out.println("========== Vienvenido al menú "+Funciones_y_Consultas.nombreUsuario+" ==========");
		System.out.println("1. Registrar un nuevo juguete.\n"
						+ "2. Modificar datos de juguetes.\n"
						+ "3. Eliminar Juguetes."
						+ "4. Salir.\n"
						+ "");
		
	}
	
	private static void menuJefe() {
		System.out.println("========== Vienvenido al menú "+Funciones_y_Consultas.nombreUsuario+" ==========");
		System.out.println("1. Crear un nuevo empleado.\n"
						+ "2. Modificar datos de un empleado.\n"
						+ "3. Eliminar empleado.\n"
						+ "4. Los empleados que más venden.\n"
						+ ""
						+ "");
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		Funciones_y_Consultas.ComprobacionBaseDatos();
		System.out.println("======== Bienvenido a la Jugueteria S <3 S ========");
		int empleado = Funciones_y_Consultas.obtencionCargoInicioSesion();
		switch (empleado) {
			case 1:
				menuCajero();
				break;
			case 2:
				menuJefe();
				break;
			default:
				
		}
	}
	
	
}
