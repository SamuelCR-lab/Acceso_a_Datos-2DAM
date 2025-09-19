package Ejercicios1;

import java.io.File;
import java.util.Scanner;

public class Ejercicio2 {

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);
	        System.out.print("Introduce el nombre del fichero: ");
	        String nombreFichero = scanner.nextLine();

	        File fichero = new File(nombreFichero);
	        if (fichero.exists() && fichero.isFile()) {
	            if (fichero.delete()) {
	                System.out.println("Fichero borrado correctamente.");
	            } else {
	                System.out.println("No se pudo borrar el fichero.");
	            }
	        } else {
	            System.out.println("El fichero no existe.");
	        }
	        scanner.close();
	    }
	}

