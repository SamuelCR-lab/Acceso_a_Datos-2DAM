package EXAMEN_PRACTICA_FINAL_FICHEROS_2025;

import java.util.Scanner;

public class Principal {
	static Scanner entrada = new Scanner(System.in);

	public static void main(String[] args) {
		boolean bandera = true;
		int opcion;
		do{
			System.out.println("Bienvenido a el vivero Carías Ramos");
			System.out.print("Introduce su número de idenificación: ");
			int id = entrada.nextInt();
			System.out.print("Introduce la contraseña: ");
			String contraseña = entrada.nextLine();
			if (bandera) {
				opcion = controlErrores();
				switch (opcion) {
				case 1:
					break;
				case 2:
					break;
				}
			}
		}while (!bandera);

	}
	public static int controlErrores() {
		boolean error = true;
		int dato =0;
		do {
			if(entrada.hasNextInt()) {
				dato = entrada.nextInt();
				error = false;
			}else {
				System.out.println("ERROR, Escribe un número.");
			}
			entrada.nextLine();
		}while(!error);
		return dato;
	}
}
