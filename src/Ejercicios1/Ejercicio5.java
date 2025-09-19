package Ejercicios1;
import java.io.File;
import java.util.Scanner;

public class Ejercicio5 {

public static void main(String[] args) {
		        Scanner scanner = new Scanner(System.in);
		        System.out.print("Introduce el nombre del fichero: ");
		        String directorio = scanner.nextLine();

		        File fichero = new File(directorio);
		        if (!fichero.exists() || !fichero.isFile()) {
		            System.out.println("El fichero no existe.");
		        }

		        String permisos = "";
		        permisos += fichero.canRead() ? "r" : "_";
		        permisos += fichero.canWrite() ? "w" : "_";
		        permisos += fichero.canExecute() ? "x" : "_";

		        System.out.println("Permisos actuales: " + permisos);

		        if (permisos.equals("rwx")) {
		            System.out.print("Introduce nuevos permisos (r, w, x, separados sin espacios): ");
		            String nuevos = scanner.nextLine();

		            boolean permisoValido = nuevos.matches("[rwx]*");

		            if (!permisoValido) {
		                System.out.println("Permisos no válidos.");
		            } else {
		                fichero.setReadable(nuevos.contains("r"));
		                fichero.setWritable(nuevos.contains("w"));
		                fichero.setExecutable(nuevos.contains("x"));
		                System.out.println("Permisos modificados.");
		            }
		        } else {
		            System.out.println("El fichero no tiene todos los permisos.");
		        }

		        scanner.close();
		    }

	}

