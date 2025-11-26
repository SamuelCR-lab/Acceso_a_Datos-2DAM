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
	                System.out.println("ERROR. El número no puede ser negativo.");
	            }
			}else {
				System.out.println("ERROR. Introduce un número");
			}
		}while(datosCorrectos);	
		return idEmpleado;
	}
	
	private static int comprobacionEmpleados() {
		return 0;
		
	}
	
	private static void menuCajero() {
		
	}
	
	private static void menuJefe() {
		
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		System.out.println("====== Jugueteria S <3 S ======");
		int empleado =comprobacionEmpleados();
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
