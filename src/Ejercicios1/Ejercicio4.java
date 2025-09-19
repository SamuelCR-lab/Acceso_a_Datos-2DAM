package Ejercicios1;
import java.io.File;
import java.util.Scanner;

public class Ejercicio4 {

		    public static void main(String[] args) {
		        Scanner scanner = new Scanner(System.in);
		        System.out.print("Introduce la direccion del directorio: ");
		        String direccion = scanner.nextLine();

		        File archivo = new File(direccion);
		        if (archivo.exists() && archivo.isDirectory()) {
		            File[] archivos = archivo.listFiles();
		            for (File f : archivos) {
		                if (f.isDirectory()) {
		                    System.out.println("[DIR] " + f.getName());
		                } else {
		                    System.out.println("[FILE] " + f.getName());
		                }
		            }
		        } else {
		            System.out.println("No es un directorio válido.");
		        }
		        scanner.close();
		    }

	}


