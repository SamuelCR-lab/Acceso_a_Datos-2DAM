package Ejercicios1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio3 {
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Introduce el nombre del directorio: ");
	        String direccion = scanner.nextLine();

	        System.out.print("Introduce el nombre del fichero: ");
	        String nombreFichero = scanner.nextLine();

	        File directorio = new File(direccion);
	        if (!directorio.exists()) {
	            System.out.println("El directorio no existe.");
	        } else {
	            File nuevoFichero = new File(directorio, nombreFichero);
	            try {
	                if (nuevoFichero.createNewFile()) {
	                    System.out.println("Fichero creado exitosamente.");
	                } else {
	                    System.out.println("El fichero ya existe.");
	                }
	            } catch (IOException e) {
	                System.out.println("Error al crear el fichero: " + e.getMessage());
	            }
	        }

	        scanner.close();
	    }
	}

