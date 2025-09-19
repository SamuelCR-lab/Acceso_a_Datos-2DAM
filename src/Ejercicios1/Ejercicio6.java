package Ejercicios1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio6 {

	public static void main(String[] args) {

		        Scanner scanner = new Scanner(System.in);
		        System.out.print("Introduce el nombre del fichero: ");
		        String nombreFichero = scanner.nextLine();

		        File fichero = new File(nombreFichero);

		        if (fichero.exists()) {
		            System.out.println("El fichero ya existe.");
		            System.out.println("Ruta absoluta: " + fichero.getAbsolutePath());
		        } else {
		            try {
		                if (fichero.createNewFile()) {
		                    // Permisos: solo lectura
		                    fichero.setWritable(false);
		                    fichero.setExecutable(false);
		                    fichero.setReadable(true);
		                    System.out.println("Fichero creado con permisos de solo lectura.");
		                } else {
		                    System.out.println("No se pudo crear el fichero.");
		                }
		            } catch (IOException e) {
		                System.out.println("Error al crear el fichero: " + e.getMessage());
		            }
		        }

		        scanner.close();
		    }

	}
