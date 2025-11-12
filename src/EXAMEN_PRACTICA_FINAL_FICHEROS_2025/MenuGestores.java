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
    private static Scanner scanner = new Scanner(System.in);
    private static final String FICHERO_EMPLEADOS = "Empleados//empleados.dat";
    private static final String FICHERO_BAJA = "Empleados//empleados_baja.dat";

    // --- Funciones Auxiliares ---
    
    // Simulación de carga inicial. Tu función original era "Catalogo()"
    
    
    // Método para guardar empleados (Serialización)
    private static void guardarEmpleados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO_EMPLEADOS))) {
            oos.writeObject(listaEmpleados);
        } catch (IOException e) {
            System.err.println("Error al guardar empleados: " + e.getMessage());
        }
    }
    
    // Método para cargar empleados (Deserialización)
    private static void cargarEmpleados() {
        File f = new File(FICHERO_EMPLEADOS);
        if (f.exists() && f.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
                listaEmpleados = (ArrayList<Empleado>) ois.readObject();
                System.out.println("Empleados cargados desde " + FICHERO_EMPLEADOS);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar empleados: " + e.getMessage());
            }
        }
    }
    
    // --- Lógica del Menú ---

    public static void menuGestores() {
        boolean salir = false;
        // Cargar datos iniciales
        int opcion;

        do {
            System.out.println("\nBienvenido al menú de Gestores");
            System.out.println("1. Dar de alta plantas.");
            System.out.println("2. Dar de baja plantas.");
            System.out.println("3. Modificar campos de las plantas.");
            System.out.println("4. Dar de alta empleados.");
            System.out.println("5. Dar de baja empleados.");
            System.out.println("6. Recuperar empleados de baja.");
            System.out.println("7. Estadísticas de las compras.");
            System.out.println("8. Salir");
            System.out.print("Seleccione una opción: ");

            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                scanner.nextLine(); // Consumir nueva línea
                
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
                    	altaEmpleado(); 
                    	break;
                    case 5: 
                    	bajaEmpleado(); 
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
                    default: System.out.println("Sigue instrucciones y escribe bien, intente de nuevo.");
                }
            } else {
                System.out.println("Entrada no válida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiar el buffer
            }
            
        } while (!salir);
        
        System.out.println("Saliendo del menú de Gestores.");
        guardarEmpleados(); // Guardar antes de salir
    }

    // --- Implementación de las Opciones ---

    // Opción 1: Dar de alta plantas
    private static void altaPlanta() {
        System.out.print("Ingrese código de la nueva planta: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();
        
        // Simplemente agregamos una nueva planta
        catalogoPlantas.add(new plantasClass(codigo, "Nueva Planta", "img_new", "Descripción de prueba", 1.00f, 10));
        System.out.println("✅ Planta con código " + codigo + " dada de alta.");
    }

    // Opción 2: Dar de baja plantas
    private static void bajaPlanta() {
        System.out.println("\n--- Catálogo Actual ---");
        catalogoPlantas.forEach(System.out::println);
        System.out.print("Ingrese código de la planta a dar de baja: ");
        int codigoBaja = scanner.nextInt();
        scanner.nextLine();

        boolean eliminada = catalogoPlantas.removeIf(p -> p.codigo == codigoBaja);
        if (eliminada) {
            System.out.println("✅ Planta con código " + codigoBaja + " eliminada.");
        } else {
            System.out.println("❌ Planta con código " + codigoBaja + " no encontrada.");
        }
    }
    
    // Opción 3: Modificar campos de las plantas
    private static void modificarPlanta() {
        System.out.println("\n--- Catálogo Actual ---");
        catalogoPlantas.forEach(System.out::println);
        System.out.print("Ingrese código de la planta a modificar: ");
        int codigoModificar = scanner.nextInt();
        scanner.nextLine();
        
        for (plantasClass p : catalogoPlantas) {
            if (p.codigo == codigoModificar) {
                System.out.println("Planta encontrada. Modificando stock (ejemplo)...");
                System.out.print("Nuevo stock: ");
                p.stock = scanner.nextInt();
                scanner.nextLine();
                System.out.println("✅ Planta modificada: " + p);
                return;
            }
        }
        System.out.println("❌ Planta no encontrada.");
    }

    // Opción 4: Dar de alta empleados
    private static void altaEmpleado() {
        System.out.print("Ingrese ID del nuevo empleado: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        
        // Simplemente agregamos un nuevo empleado
        listaEmpleados.add(new Empleado(id, nombre, "defaultPass", "Vendedor"));
        System.out.println("✅ Empleado " + nombre + " dado de alta.");
    }

    // Opción 5: Dar de baja empleados
    private static void bajaEmpleado() {
        System.out.println("\n--- Empleados Actuales ---");
        listaEmpleados.forEach(System.out::println);
        System.out.print("Ingrese ID del empleado a dar de baja: ");
        int idBaja = scanner.nextInt();
        scanner.nextLine();
        
        Empleado empBaja = null;
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getIdentificacion() == idBaja) {
                empBaja = listaEmpleados.remove(i);
                break;
            }
        }
        
        if (empBaja != null) {
            empleadosDeBaja.add(empBaja); // Mover a la lista de baja
            System.out.println("✅ Empleado " + empBaja.getIdentificacion() + " dado de baja. Puede recuperarlo en opción 6.");
        } else {
            System.out.println("❌ Empleado con ID " + idBaja + " no encontrado.");
        }
    }

    // Opción 6: Recuperar empleados de baja
    private static void recuperarEmpleadoBaja() {
        if (empleadosDeBaja.isEmpty()) {
            System.out.println("No hay empleados en la lista de baja.");
            return;
        }
        
        System.out.println("\n--- Empleados de Baja ---");
        empleadosDeBaja.forEach(System.out::println);
        System.out.print("Ingrese ID del empleado a recuperar: ");
        int idRecuperar = scanner.nextInt();
        scanner.nextLine();
        
        Empleado empRecuperado = null;
        for (int i = 0; i < empleadosDeBaja.size(); i++) {
            if (empleadosDeBaja.get(i).getIdentificacion() == idRecuperar) {
                empRecuperado = empleadosDeBaja.remove(i);
                break;
            }
        }
        
        if (empRecuperado != null) {
            listaEmpleados.add(empRecuperado); // Mover de vuelta a la lista activa
            System.out.println("✅ Empleado " + empRecuperado.getIdentificacion() + " recuperado y activo.");
        } else {
            System.out.println("❌ Empleado con ID " + idRecuperar + " no encontrado en la lista de baja.");
        }
    }

    // Opción 7: Estadísticas de las compras
    private static void mostrarEstadisticas() {
        long totalStock = catalogoPlantas.stream().mapToLong(p -> p.stock).sum();
        System.out.println("\n--- Estadísticas ---");
        System.out.println("Total de plantas en el catálogo: " + catalogoPlantas.size());
        System.out.println("Stock total disponible: " + totalStock);
        System.out.println("Total de empleados activos: " + listaEmpleados.size());
        System.out.println("Total de empleados de baja: " + empleadosDeBaja.size());
        // Aquí se agregarían estadísticas de ventas si tuvieras esa estructura de datos.
    }
    
    // Función main para probar el gestor
    public static void main(String[] args) {
        MenuGestores.menuGestores();
    }
}