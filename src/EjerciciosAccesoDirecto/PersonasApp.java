package EjerciciosAccesoDirecto;

	import java.io.*;
	import java.util.*;

	public class PersonasApp {
		
	    private static final String FICHERO = "personas.dat";

	    public static void main(String[] args) {
	    	Scanner entrada = new Scanner(System.in);
	        boolean salir = false;

	        while (!salir) {
	            System.out.println("\n Menú de Personas: \n"
	            					+"1. Insertar persona\n"
	            					+"2. Mostrar todas\n"
	            					+"3. Salir\n");
	            int opcion = entrada.nextInt();
	            entrada.nextLine(); // limpiar buffer

	            switch (opcion) {
	                case 1:
	                    System.out.print("Nombre: ");
	                    String nombre = entrada.nextLine();
	                    System.out.print("Edad: ");
	                    int edad = entrada.nextInt();
	                    Persona p = new Persona(nombre, edad);
	                    guardarPersona(p);
	                    break;

	                case 2:
	                    mostrarPersonas();
	                    break;

	                default:
	                    salir = true;
	            }
	        }
	    }

	    private static void guardarPersona(Persona p) {
	    	 try {
	    	        File fichero = new File(FICHERO);
	    	        ObjectOutputStream oos;

	    	        if (fichero.exists() && fichero.length() > 0) {
	    	            // Si ya hay datos, usamos MiObjectOutputStream
	    	            oos = new ObjectOutputStream(new FileOutputStream(fichero, true));
	    	        } else {
	    	            // Si el fichero no existe o está vacío, cabecera normal
	    	            oos = new ObjectOutputStream(new FileOutputStream(fichero));
	    	        }

	    	        oos.writeObject(p);
	    	        oos.close();

	    	    } catch (IOException e) {
	    	        e.printStackTrace();
	    	    }
	    }

	    private static void mostrarPersonas() {
	        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO))) {
	            while (true) {
	                Persona persona = (Persona) ois.readObject();
	                System.out.println(persona);
	            }
	        } catch (EOFException e) {
	            // fin de fichero
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}

