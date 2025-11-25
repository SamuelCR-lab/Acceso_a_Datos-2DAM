package EXAMEN_PRACTICA_FINAL_FICHEROS_2025;

import java.util.ArrayList;
import java.util.Scanner;
<<<<<<< HEAD
import java.io.File;
=======


>>>>>>> branch 'main' of https://github.com:443/SamuelCR-lab/Acceso_a_Datos-2DAM.git
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

public class MenuGestores {

    static ArrayList<plantasClass> catalogoPlantas = new ArrayList<>();
    static ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    static ArrayList<Empleado> empleadosDeBaja = new ArrayList<>();
    static Scanner entrada = new Scanner(System.in);
    static final String ficheroEmpleados = "Empleados//empleados.dat";
    static final String ficheroEmpleadosBajaDAT = "Empleados//empleados_baja.dat";

    
    

    private static void guardarEmpleados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroEmpleados))) {
            oos.writeObject(listaEmpleados);
        } catch (IOException e) {
            System.err.println("Error al guardar empleados: " + e.getMessage());
        }
    }

    public static void altaPlanta() {
    	System.out.println("\n\t\t==== Catálogo Actual =====");
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
        Principal.plantas = catalogoPlantas;
        Principal.guardarPlantasSalir();
    }

    public static void bajaPlanta() {
        System.out.println("\n\t\t===== Catálogo Actual =====\n");
        Principal.mostrarCatalogo(catalogoPlantas);
        System.out.print("Ingrese código de la planta a dar de baja: ");
        int codigoBaja = Principal.controlErroresInt();
        int cantidadPlanta = catalogoPlantas.size()+1;
        if (codigoBaja <= cantidadPlanta) {
	        for (plantasClass darbaja : catalogoPlantas) {
	        	if (codigoBaja == darbaja.codigo) {
	        		Principal.guardarPlantaBajas(darbaja);
	        		darbaja.stock = 0;
	        		darbaja.precio = 0;
	        		System.out.println("La planta con el código = "+codigoBaja+", ha sido dada de baja.");
	        	}
	        }
        }else {
        	System.out.println("La planta con el código = "+codigoBaja+", no se ha encontrado.");
        }
        Principal.plantas = catalogoPlantas;
        Principal.guardarPlantasSalir();
    }
    

    public static void modificarPlanta() {
        System.out.println("\n\t\t===== Catálogo Actual =====\n");
        Principal.mostrarCatalogo(catalogoPlantas);
        System.out.print("Ingrese código de la planta a modificar: ");
        int codigoModificar = Principal.controlErroresInt();
        int cantidad= catalogoPlantas.size()+1;
        if((codigoModificar>0)&&(codigoModificar<=cantidad)){
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
            }
        }
        }else {
        	System.out.println("Planta no encontrada.");
        }
        Principal.plantas = catalogoPlantas;
        Principal.guardarPlantasSalir();
    }

    public static void darAltaEmpleado() {
    	System.out.println("\n\t\t===== Dar de alta a un Empleado =====");
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
        Principal.empleados = listaEmpleados;
        guardarEmpleados();
    }
    
    public static void guardarEmpleadosBaja() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroEmpleadosBajaDAT))) {
            oos.writeObject(empleadosDeBaja);
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
    public static void guardarEmpleadosActivos() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroEmpleados))) {
            oos.writeObject(listaEmpleados);
        } catch (IOException e) {
        	e.getStackTrace();
        }
    }
    public static void darBajaEmpleado() {
        System.out.println("\n\t\t===== Baja de Empleado =====");
        listaEmpleados.forEach(System.out::println);
        System.out.print("Ingrese ID del empleado a dar de baja: ");
        int idEmpleadoBaja = Principal.controlErroresInt();
        
        Empleado empleadoBaja = null;
        int cantidadBajaEmpleados = listaEmpleados.size()+1;
        for (int i = 0; i < cantidadBajaEmpleados; i++) {
            if (listaEmpleados.get(i).getIdentificacion() == idEmpleadoBaja) {
                empleadoBaja = listaEmpleados.remove(i);
                i=cantidadBajaEmpleados;
            }
        }
        
        if (empleadoBaja != null) {
            empleadosDeBaja.add(empleadoBaja);
            guardarEmpleadosActivos();
            guardarEmpleadosBaja();
            System.out.println("Empleado "+empleadoBaja.getNombre()+"de ID: "+empleadoBaja.getIdentificacion()+" ha sido dado de baja.");
        } else {
            System.out.println("Error: Empleado no encontrado.");
        }
    }

    public static void recuperarEmpleadoBaja() {
        System.out.println("\n===== Recuperar Empleado de Baja =====");
        if (empleadosDeBaja.isEmpty()) {
            System.out.println("No hay empleados en la lista de baja.");
        }else {
        	empleadosDeBaja.forEach(System.out::println);
	        System.out.print("Ingrese ID del empleado a recuperar: ");
	        int idEmpleadoRecu = Principal.controlErroresInt();
	        
	        Empleado empleadoRecuperados = null;
	        int empleadoGuardados = empleadosDeBaja.size()+1;
	        for (int i = 0; i < empleadoGuardados; i++) {
	            if (empleadosDeBaja.get(i).getIdentificacion() == idEmpleadoRecu) {
	            	empleadoRecuperados = empleadosDeBaja.remove(i);
	                i=empleadoGuardados;
	            }
	        }
	        
	        if (empleadoRecuperados != null) {
	            listaEmpleados.add(empleadoRecuperados);
	            guardarEmpleadosActivos();
	            guardarEmpleadosBaja();
	            System.out.println("Empleado "+empleadoRecuperados.getNombre()+" de ID: "+empleadoRecuperados.getIdentificacion()+" ha sido recuperado.");
	        } else {
	            System.out.println("Error: Empleado no se ha encontrado en la lista de bajas.");
	        }
        }
    }

    // Funcion mostrar Estadisticas no me dio tiempo a hacerla y no se como
    public static void mostrarEstadisticas() {
        System.out.println("\nNo sabia como hacer la funcion Estadisticas.");

    }
    public static void menuGestores() {
        boolean salir = false;
        int opcion;
        catalogoPlantas = Principal.plantas;
        listaEmpleados = Principal.empleados;
        do {
            System.out.println("\n\t\tBienvenido al menú de Gestores "+Principal.nombreEmpleadoIS+".\n"
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
    }

    public static void main(String[] args) {
        MenuGestores.menuGestores();
    }
}