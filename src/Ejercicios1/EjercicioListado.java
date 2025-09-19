package Ejercicios1;
import java.io.File;
import java.util.Scanner;
public class EjercicioListado {
		    public static void main(String[] args) {
		        Scanner scanner = new Scanner(System.in);
		        System.out.print("Introduce el path del directorio: ");
		        String Direccion = scanner.nextLine();

		        File directorio = new File(Direccion);
		        if (directorio.exists() && directorio.isDirectory()) {
		            String[] elementos = directorio.list();
		            for (String elem : elementos) {
		                System.out.println(elem);
		            }
		        } else {
		            System.out.println("No es un directorio válido.");
		        }
		        scanner.close();
		    }
		

	}


