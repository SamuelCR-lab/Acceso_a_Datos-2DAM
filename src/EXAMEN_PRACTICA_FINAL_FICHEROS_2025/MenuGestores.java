package EXAMEN_PRACTICA_FINAL_FICHEROS_2025;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class MenuGestores {

    // Listas estáticas para simular la base de datos en memoria
    private static ArrayList<plantasClass> catalogoPlantas = new ArrayList<>();
    private static ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    private static ArrayList<Empleado> empleadosDeBaja = new ArrayList<>();
    private static Scanner entrada = new Scanner(System.in);
    private static final String FICHERO_EMPLEADOS = "Empleados//empleados.dat";
    private static final String FICHERO_BAJA = "Empleados//empleados_baja.dat";
    
    

    private static void guardarEmpleados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_EMPLEADOS))) {
            oos.writeObject(listaEmpleados);
        } catch (IOException e) {
            System.err.println("Error al guardar empleados: " + e.getMessage());
        }
    }

    // Opción 1: Dar de alta plantas
    public static void altaPlanta() {
    	System.out.println("\n===== Catálogo Actual =====");
        Principal.mostrarCatalogo(catalogoPlantas);
        int codigo = catalogoPlantas.size()+1;
        System.out.println("Introduce el nombre de la nueva Planta: ");
        String nuevaPlantaNombre = entrada.nextLine();
        System.out.println("Introduce la imagen de nueva planta: ");
        String nuevaPlantaImagen = entrada.nextLine();
        System.out.println("Introduce la descripción nueva Planta: ");
        String nuevaPlantaDescripcion = entrada.nextLine();
        System.out.println("Introduce el precio de la nueva Planta: ");
        float nuevaPlantaPrecio = (float) Principal.controlErroresInt();
        System.out.println("Introduce el stock de la nueva Planta: ");
        int nuevaPlantaStock = Principal.controlErroresInt();
        
        catalogoPlantas.add(new plantasClass(codigo, nuevaPlantaNombre, nuevaPlantaImagen, nuevaPlantaDescripcion, nuevaPlantaPrecio, nuevaPlantaStock));
        System.out.println("Planta con código "+codigo+" dada de alta.");
    }

    public static void bajaPlanta() {
        System.out.println("\n===== Catálogo Actual =====");
        Principal.mostrarCatalogo(catalogoPlantas);
        System.out.print("Ingrese código de la planta a dar de baja: ");
        int codigoBaja = Principal.controlErroresInt();

        boolean eliminada = catalogoPlantas.removeIf(planta -> planta.codigo == codigoBaja);
        if (eliminada) {
            System.out.println("La planta con el código = "+codigoBaja+", ha sido eliminada.");
        } else {
            System.out.println("La planta con el código = "+codigoBaja+", no se ha encontrada.");
        }
    }
    

    public static void modificarPlanta() {
        System.out.println("\n===== Catálogo Actual =====");
        Principal.mostrarCatalogo(catalogoPlantas);
        System.out.print("Ingrese código de la planta a modificar: ");
        int codigoModificar = Principal.controlErroresInt();

        
        for (plantasClass plantaModificada : catalogoPlantas) {
            if (plantaModificada.codigo == codigoModificar) {
                System.out.print("Planta encontrada. ¿Cual quieres que sea su nuevo stock?: ");
                boolean bandera = false;
	            do { 	
                	int stockTemp = Principal.controlErroresInt();
	                if (stockTemp > 0) {
	                	plantaModificada.stock = stockTemp;
	                	bandera =true;
	                }else {
	                	System.out.println("El stock introducido no puede ser menor ni igual que 0\n");
	                }
	            }while(!bandera);
                System.out.println("La planta de ID = "+plantaModificada.codigo+", ha sido modificada: "+plantaModificada);
                return;
            }
        }
        System.out.println("Planta no encontrada.");
    }

    public static void darAltaEmpleado() {
        System.out.print("Ingrese ID del nuevo empleado: ");
        int id = Principal.controlErroresInt();
        System.out.print("Introduzca el nombre: ");
        String nombre = entrada.nextLine();
        System.out.print("Introduzca la contraseña: ");
        String contrasenia = entrada.nextLine();
        System.out.print("Ingrese nombre: ");
        String cargo = entrada.nextLine();

        listaEmpleados.add(new Empleado(id, nombre, contrasenia, cargo));
        System.out.println("El nuevo empleado de "+nombre+", ha sido dado de alta.");
    }


    public static void darBajaEmpleado() {
        System.out.println("\n===== Empleados Actuales =====");
        if (listaEmpleados != null) {
            for (Empleado empleado : listaEmpleados) {
                System.out.println(empleado);
            }
        }
        System.out.print("Ingrese ID del empleado a dar de baja: ");
        int idEmpleadoBaja = Principal.controlErroresInt();
        
        Empleado empleadoBajaGuardado = null;
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getIdentificacion() == idEmpleadoBaja) {
            	empleadoBajaGuardado = listaEmpleados.remove(i);
                break;
            }
        }
        
        if (empleadoBajaGuardado != null) {
            empleadosDeBaja.add(empleadoBajaGuardado);
            System.out.println("Empleado de " + empleadoBajaGuardado.getIdentificacion() + " se ha dado de baja.");
        } else {
            System.out.println("Empleado de ID = "+idEmpleadoBaja+" no se ha encontrado.");
        }
    }

    public static void recuperarEmpleadoBaja() {
        if (empleadosDeBaja.isEmpty()) {
            System.out.println("No hay empleados en la lista de baja.");
        }else {
        	 
            System.out.println("\n===== Empleados de Baja =====");
            empleadosDeBaja.forEach(System.out::println);
            System.out.print("Ingrese ID del empleado a recuperar: ");
            int idEmpleadoRecu = Principal.controlErroresInt();

            
            Empleado empleadoRecu = null;
            for (int i = 0; i < empleadosDeBaja.size(); i++) {
                if (empleadosDeBaja.get(i).getIdentificacion() == idEmpleadoRecu) {
                	empleadoRecu = empleadosDeBaja.remove(i);
                    break;
                }
            }
            
            if (empleadoRecu != null) {
                listaEmpleados.add(empleadoRecu);
                System.out.println("El empleado de ID = "+empleadoRecu.getIdentificacion()+", ha sido recuperado.");
            } else {
                System.out.println("Empleado con el ID = "+idEmpleadoRecu+" no se ha encontrado en la lista de baja.");
            }
        }
    }

    // Funcion mostrar Estadisticas no me dio tiempo a hacerla
    public static void mostrarEstadisticas() {
        System.out.println("No sabia como hacer la funcion Estadisticas.");

    }
    public static void menuGestores() {
        boolean salir = false;
        int opcion;
        catalogoPlantas = Principal.plantas;
        listaEmpleados = Principal.empleados;
        do {
            System.out.println("\nBienvenido al menú de Gestores\n"
            					+"1. Dar de alta plantas.\n"
            					+"2. Dar de baja plantas.\n"
            					+"3. Modificar campos de las plantas.\n"
            					+"4. Dar de alta empleados.\n"
            					+"5. Dar de baja empleados.\n"
            					+"6. Recuperar empleados de baja.\n"
            					+"7. Estadísticas de las compras.\n"
            					+"8. Salir.\n");
            System.out.print("Seleccione una opción: ");
                opcion = Principal.controlErroresInt();
                
                switch (opcion) {
                    case 1: 
                    	altaPlanta(); 
                    	break;
                    case 2: 
                    	bajaPlanta(); 
                    	break;
                    case 3: 
                    	modificarPlanta(); 
                    	break;
                    case 4: 
                    	darAltaEmpleado(); 
                    	break;
                    case 5: 
                    	darBajaEmpleado(); 
                    	break;
                    case 6: 
                    	recuperarEmpleadoBaja(); 
                    	break;
                    case 7: 
                    	mostrarEstadisticas(); 
                    	break;
                    case 8: 
                    	salir = true; 
                    	break;
                    default: 
                    	System.out.println("Escribe bien, intentalo de nuevo.");
                }
            
            
        } while (!salir);
        Principal.plantas = catalogoPlantas;
        Principal.empleados = listaEmpleados;
        System.out.println("Saliendo del menú de Gestores.");
        guardarEmpleados();
    }

    public static void main(String[] args) {
        MenuGestores.menuGestores();
    }
}