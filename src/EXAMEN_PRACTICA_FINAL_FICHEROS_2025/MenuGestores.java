package EXAMEN_PRACTICA_FINAL_FICHEROS_2025;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.IOException;

public class MenuGestores {

    static ArrayList<plantasClass> catalogoPlantas = new ArrayList<>();
    static ArrayList<Empleado> listaEmpleados = new ArrayList<>();
    static ArrayList<Empleado> empleadosDeBaja = new ArrayList<>();
    static Scanner entrada = new Scanner(System.in);
    static final String ficheroEmpleados = "Empleados//empleados.dat";
    static final String ficheroEmpleadosBajaDAT = "Empleados//empleados_baja.dat";
	static ArrayList <plantasClass> EstadisticaPlantas;
	static File ficheroDATBaja = new File("Plantas//plantasBaja.dat");
	static File ficheroXMLBaja = new File("Plantas//plantasBaja.xml");
    static ArrayList<plantasClass> plantasRecuperadas = new ArrayList<>();
	//private static String[] arrayOrdenadoDeVentas; 
    

    private static void guardarEmpleados() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ficheroEmpleados))) {
            oos.writeObject(listaEmpleados);
        } catch (IOException e) {
            System.err.println("Error al guardar empleados: " + e.getMessage());
        }
    }

    public static void altaPlanta() {
    	System.out.println("\n\t\t==== Catálogo Actual =====");
        Principal.Catalogo(1);
        int maxId = 0;
        for(plantasClass p : Principal.plantas) {
            if(p.codigo > maxId) maxId = p.codigo;
        }
        int codigo = maxId + 1;
        System.out.println("Introduce el nombre de la nueva Planta: ");
        String nuevaPlantaNombre = entrada.nextLine();
        System.out.println("Introduce la imagen de nueva planta (ej: foto.jpg): ");
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
        actualizarFicheroXML();
    }
    	public static void actualizarFicheroXML() {
        try (FileWriter fw = new FileWriter(Principal.ficheroPlantasXML)) {
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            fw.write("<plantas>\n");
            for (plantasClass p : Principal.plantas) {
                fw.write("\t<planta>\n");
                fw.write("\t\t<nombre>" + p.nombre + "</nombre>\n");
                fw.write("\t\t<foto>" + p.foto + "</foto>\n");
                fw.write("\t\t<descripcion>" + p.descripcion + "</descripcion>\n");
                fw.write("\t</planta>\n");
            }
            fw.write("</plantas>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void bajaPlanta() {
        System.out.println("\n\t\t===== Catálogo Actual =====\n");
        Principal.Catalogo(1);
        System.out.print("Ingrese código de la planta a dar de baja: ");
        int codigoBaja = Principal.controlErroresInt();
        int maxId = 0;
        for(plantasClass p : Principal.plantas) {
            if(p.codigo > maxId) maxId = p.codigo;
        }
        int codigo = maxId + 1;
        if (codigoBaja <= codigo) {
	        for (plantasClass darbaja : catalogoPlantas) {
	        	if (codigoBaja == darbaja.codigo) {
	        		Principal.guardarPlantaBajas(darbaja);
	        		darbaja.stock = 0;
	        		darbaja.precio = 0;
	        		System.out.println("La planta con el código = "+codigoBaja+", ha sido dada de baja.");
	        		darbaja.toString();
	        	}
	        }
        }else {
        	System.out.println("La planta con el código = "+codigoBaja+", no se ha encontrado.");
        }
        Principal.plantas = catalogoPlantas;
        Principal.guardarPlantasSalir();
        actualizarFicheroXML();
    }
    public static void CatalogoPlantasBajas() {
    	try {
				DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
				DocumentBuilder docB = docBF.newDocumentBuilder();
				Document doc = docB.parse(Principal.ficheroXMLBaja);
				doc.getDocumentElement().normalize();
				NodeList lista = doc.getElementsByTagName("planta");
				ArrayList<plantasClass> plantasOrganizadasRecuperadas = new ArrayList<>();
				int cantidad = lista.getLength();
				plantasRecuperadas = new ArrayList<>();
				RandomAccessFile plantasDat = new RandomAccessFile (Principal.ficheroDATBaja,"r");
				for (int i =0;i<cantidad;i++) {
					Node nodo = lista.item(i);
					
					if (nodo.getNodeType()== Node.ELEMENT_NODE) {
						Element plantas = (Element)nodo;
						int codigo = plantasDat.readInt();
						float precio = plantasDat.readFloat();
						int stock = plantasDat.readInt();
						String nombre = plantas.getElementsByTagName("nombre").item(0).getTextContent();
						String foto = plantas.getElementsByTagName("foto").item(0).getTextContent();
						String descripcion = plantas.getElementsByTagName("descripcion").item(0).getTextContent();
						plantasOrganizadasRecuperadas.add(new plantasClass(codigo,nombre,foto,descripcion,precio,stock));
							
					}
				}
				plantasRecuperadas = plantasOrganizadasRecuperadas;
				plantasDat.close();
			}catch(Exception e){
				e.getStackTrace();
			}
    }

    public static void modificarPlanta() {
        
        System.out.println("¿Quieres rescatar una planta?(1) o ¿Quieres dar stock a una planta?(2):");
        int opcion = entrada.nextInt();
        switch(opcion) {
        case 1:
        	CatalogoPlantasBajas();
        	if (plantasRecuperadas != null) {
	        	System.out.println("\n\t\t===== Catálogo Completo con Plantas dadas de baja =====\n");
	        	Principal.Catalogo(2);
	        	System.out.print("Ingrese código de la planta a rescatar: ");
	 	        int codigoRescatar = Principal.controlErroresInt();
	 	        int cantidadMaximaPlantas= catalogoPlantas.size()+1;
		        if((codigoRescatar>0)&&(codigoRescatar<=cantidadMaximaPlantas)){
		        	for (plantasClass plantaRescatar : catalogoPlantas) {
		        		if((codigoRescatar == plantaRescatar.codigo)&&(plantaRescatar.stock == 0)) {
		        			System.out.print("Planta encontrada. ¿Cual quieres que sea su nuevo stock?: ");
		 	                boolean bandera = false;
		 		            do { 	
		 	                	int stockTemp = Principal.controlErroresInt();
		 		                if (stockTemp > 0) {
		 		                	plantaRescatar.stock = stockTemp;
		 		                	CatalogoPlantasBajas();
		 		                	for(plantasClass precioPlantaRescatada : plantasRecuperadas) {
		 		                		if(precioPlantaRescatada.codigo == plantaRescatar.codigo) {
		 		                			plantaRescatar.precio = precioPlantaRescatada.precio;
		 		                			System.out.println("La planta ha sido recuperada de forma correcta.");
		 		                		}
		 		                	}
		 		                	bandera =true;
		 		                }else {
		 		                	System.out.println("El stock introducido, no puede ser menor ni igual que 0\n");
		 		                }
		 		            }while(!bandera);
		        		}else {
		        			System.out.println("No puedes rescatar esta planta porque no esta dada de baja.");
		        			break;
		        		}
		        	}
		        	Principal.plantas = catalogoPlantas;
			        Principal.guardarPlantasSalir();
			        Principal.Catalogo(1);
		        }else {
		        	System.out.println("Planta no encontrada. Escribe un número de planta que se encuentre en el catálogo");
		        }
		        
        	}else {
        		System.out.println("No hay plantas dadas de baja, para que puedas recuperarlas.");
        	}
        	break;
        case 2:
        	System.out.println("\n\t\t===== Catálogo Actual =====\n");
        	Principal.Catalogo(1);
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
	        break;
        default:
        	System.out.println("Introduce un 1 o 2.");
        }
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
        Principal.empleados = listaEmpleados;
        guardarEmpleados();
        System.out.println("El nuevo empleado de "+nombre+", ha sido dado de alta correctamente.");
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
        for (int i = 0; i < listaEmpleados.size(); i++) {
            if (listaEmpleados.get(i).getIdentificacion() == idEmpleadoBaja) {
                empleadoBaja = listaEmpleados.remove(i);
                break;
            }
        }
        if (empleadoBaja != null) {
            empleadosDeBaja.add(empleadoBaja);
            guardarEmpleadosActivos();
            guardarEmpleadosBaja();
            System.out.println("Empleado "+empleadoBaja.getNombre()+" de ID: "+empleadoBaja.getIdentificacion()+" ha sido dado de baja correctamente.");
        } else {
            System.out.println("Error: Empleado no encontrado.");
        }
    }

    public static void recuperarEmpleado() {
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

    public static void mostrarEstadisticas() {
    	System.out.println("\n\t\t===== Estadísticas de Ventas =====");
        ArrayList<plantasClass> estadisticas = new ArrayList<>(); 
        
        File[] ticketsCreados = Principal.directorioTickets.listFiles();
        
        if(ticketsCreados == null || ticketsCreados.length == 0) {
            System.out.println("No hay tickets de venta registrados.");
        }else {
	        float dineroGanado = 0;
	        Pattern pProductos = Pattern.compile("^(\\d+)\\s*\\|\\s*(\\d+)");
	        Pattern pTotal = Pattern.compile("TOTAL A PAGAR: ([-]?\\d+[.,]\\d+)");
	
	        for (File archivo : ticketsCreados) {
	            if (archivo.isFile() && archivo.getName().startsWith("Tickets")) {
	                try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
	                    String linea;
	                    while ((linea = reader.readLine()) != null) {
	                    	
	                        Matcher mProd = pProductos.matcher(linea);
	                        if (mProd.find()) {
	                            int codigo = Integer.parseInt(mProd.group(1).trim());
	                            int cantidadVendida = Integer.parseInt(mProd.group(2).trim());
	                            
	                            boolean yaExiste = false;
	                            
	                            for(plantasClass pStats : estadisticas) {
	                                if(pStats.codigo == codigo) {
	                                    pStats.stock += cantidadVendida; // Sumamos cantidad
	                                    yaExiste = true;
	                                    break;
	                                }
	                            }
	          
	                            if(!yaExiste) {
	
	                                for(plantasClass pCatalogo : Principal.plantas) {
	                                    if(pCatalogo.codigo == codigo) {
	                                        estadisticas.add(new plantasClass(codigo,pCatalogo.nombre,pCatalogo.foto,pCatalogo.descripcion,pCatalogo.precio,cantidadVendida));
	                                        break;
	                                    }
	                                }
	                            }
	                        }

	                        Matcher mTotal = pTotal.matcher(linea);
	                        if (mTotal.find()) {
	                            dineroGanado += Float.parseFloat(mTotal.group(1).replace(",", "."));
	                        }
	                    }
	                } catch(IOException io) {
	                    io.printStackTrace();
	                }
	            }
	        }
	
	        System.out.printf("Dinero total recaudado: %.2f€\n\n", dineroGanado);
	        System.out.println("===== Ventas Organizadas por Cantidad (Más vendidas primero) =====");
	
	        int n = estadisticas.size();
	        for (int i = 0; i < n - 1; i++) {
	            for (int j = 0; j < n - i - 1; j++) {
	                if (estadisticas.get(j).stock < estadisticas.get(j + 1).stock) {
	                    plantasClass temp = estadisticas.get(j);
	                    estadisticas.set(j, estadisticas.get(j + 1));
	                    estadisticas.set(j + 1, temp);
	                }
	            }
	        }
	        for(plantasClass p : estadisticas) {
	            System.out.println("Planta: " + p.nombre + " (ID: " + p.codigo + ") - Unidades vendidas: " + p.stock);
	        }
        }
    }
    public static void menuGestores() {
        boolean salir = false;
        int opcion;
        catalogoPlantas = Principal.plantas;
        listaEmpleados = Principal.empleados;
        Principal.Catalogo(0);
        do {
        	
            System.out.println("\n\t\tBienvenido al menú de Gestores "+Principal.nombreEmpleadoIS+".\n"
            					+"1. Dar de alta plantas.\n"
            					+"2. Dar de baja plantas.\n"
            					+"3. Rescatar planta o dar stock.\n"
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
                    	recuperarEmpleado(); 
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