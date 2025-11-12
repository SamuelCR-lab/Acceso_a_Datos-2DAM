package EXAMEN_PRACTICA_FINAL_FICHEROS_2025;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Principal {
	static Scanner entrada = new Scanner(System.in);
	static ArrayList <plantasClass> plantas = new ArrayList<>();
	static ArrayList <plantasClass> plantasVenta = new ArrayList<>();
	static ArrayList <Empleado> empleados = new ArrayList<>();
	static int idIS, iDTickets = 0;
	static String nombreEmpleadoIS;
	static Date fecha;
	
	
	public static int controlErroresInt() {
		boolean error = true;
		int dato =0;
		do {
			if(entrada.hasNextInt()) {
				dato = entrada.nextInt();
				error = false;
			}else {
				System.out.println("ERROR, Escribe un número.");
			}
			entrada.nextLine();
		}while(error);
		return dato;
	}
	public static int comprobacionContraseña(ArrayList<Empleado>empleadosO) {
		int respuesta=0;
		boolean datosCorrectos=true;
		do {
			System.out.print("Introduce su número de identificación: ");
			idIS = controlErroresInt();
			System.out.print("Introduce la contraseña: ");
			String contrasenia = entrada.nextLine();
			for(Empleado empleado:empleadosO) {
				if((empleado.getIdentificacion() == idIS)&&(empleado.getPassword().equals(contrasenia))){
					if(empleado.getCargo().equals("Gestor")) {
						nombreEmpleadoIS=empleado.getNombre();
						datosCorrectos = false;
						return respuesta=1;
					}else {
						nombreEmpleadoIS=empleado.getNombre();
						datosCorrectos = false;
						return respuesta=2;
					}
				}
			}
			if (respuesta==0) {
				System.out.println("Has introducido mal los datos");
			}
			
		}while(datosCorrectos);	
		return respuesta;
	}
	
	public static void Catalogo() {
		File directorio = new File("Plantas");
		File ficheroDAT = new File("Plantas//plantas.dat");
		File ficheroXML = new File("Plantas//plantas.xml");
		creacionEmpleadosYPlantas creacionDAT = new creacionEmpleadosYPlantas();
		if (!directorio.exists()) {
			directorio.mkdirs();
		}
		if(!ficheroDAT.exists()){
			try {
				ficheroDAT.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
				creacionDAT.EscribirFichero();
				try {
					DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
					DocumentBuilder docB = docBF.newDocumentBuilder();
					Document doc = docB.parse(ficheroXML);
					doc.getDocumentElement().normalize();
					NodeList lista = doc.getElementsByTagName("planta");
					ArrayList<plantasClass> plantasO = new ArrayList<>();
					int cantidad = lista.getLength();
					RandomAccessFile plantasDat = new RandomAccessFile (ficheroDAT,"r");
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
							plantasO.add(new plantasClass(codigo,nombre,foto,descripcion,precio,stock));
								
						}
					}
					plantas = plantasO;
					plantasDat.close();
					}catch(Exception e){e.getStackTrace();}
			}
		

	public static void lecturaEmpleados() {
		File directorio = new File("Empleados");
		creacionEmpleadosYPlantas creacionDAT = new creacionEmpleadosYPlantas();
		if (!directorio.exists()) {
			directorio.mkdirs();
		}
		creacionDAT.EscribirEmpleado();
		
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Empleados//empleado.dat"))) {
			ArrayList<Empleado> listaEmpleados = (ArrayList<Empleado>) ois.readObject();
			for (Empleado empleado : listaEmpleados) {
	            System.out.println(empleado);
	        }
			empleados = listaEmpleados;
        } catch (EOFException e) {
            // fin de fichero
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
		
	public static void mostrarCatalogo(ArrayList <plantasClass> plantasO){
			
			for(plantasClass mostrarPlantas:plantasO) {
				System.out.println(mostrarPlantas.toString());
			}

	}
	public static void guardarPlanta(plantasClass plantasSinExistencias)  {
		File ficheroDAT = new File("Plantas//plantasBaja.dat");
		File ficheroXML = new File("Plantas//plantasBaja.xml");
		try {
			if((!ficheroDAT.exists())&&(!ficheroXML.exists())) {
				ficheroDAT.createNewFile();	
				ficheroXML.createNewFile();
			}else {
				if ((!ficheroDAT.exists())) {
					ficheroDAT.createNewFile();
				}
				if ((!ficheroXML.exists())){
					ficheroXML.createNewFile();
				}
			}
			FileWriter EscrituraXML = new FileWriter(ficheroXML,true);//el true del final es para que el archivo escriba al final siempre
			if (!ficheroXML.exists()) {
				EscrituraXML.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n");
				EscrituraXML.write("<plantas>\n");
			}
			EscrituraXML.write("<planta>\n");
			EscrituraXML.write("<codigo>"+plantasSinExistencias.codigo+"</codigo>\n");
			EscrituraXML.write("<nombre>"+plantasSinExistencias.nombre+"</nombre>\n");
			EscrituraXML.write("<foto>"+plantasSinExistencias.foto+"</foto>\n");
			EscrituraXML.write("<descripcion>"+plantasSinExistencias.codigo+"</descripcion>\n");
			EscrituraXML.write("</planta>\n");
					
		}catch(IOException i) {i.getStackTrace();}
	}
	
	public static void buscadorPlanta(int codigoPlantaVenta,int cantidadPlantaVenta){
		for(plantasClass ventasPlantas :plantas) {
			if (codigoPlantaVenta == ventasPlantas.codigo) {
				if(cantidadPlantaVenta <= ventasPlantas.stock) {
					String nombreTemp = ventasPlantas.nombre;
					String fotoTemp = ventasPlantas.foto;
					String descripcion = ventasPlantas.descripcion;
					Float precio =ventasPlantas.precio;
					plantasVenta.add(new plantasClass(codigoPlantaVenta,nombreTemp,fotoTemp,descripcion,precio,cantidadPlantaVenta));
					ventasPlantas.stock = ventasPlantas.stock - cantidadPlantaVenta;
					if(ventasPlantas.stock <= 0) {
						guardarPlanta(ventasPlantas);
					}
				}else {
					System.out.println("No hay suficiente Stock.");
				}
			}
		}
		if(plantasVenta == null)
		System.out.println("No se ha encontrado una planta con ese código.");
	}
	public static void generarVentas() {
		boolean bandera =false;
		int contador=0;
		System.out.println("Generando Venta... ");
		//ArrayList<plantasClass> plantasVenta = new ArrayList<>(); 
		do {
			if (contador==0) {
				System.out.println("Introduce el Codigo de la planta: ");
				int codigoPlantaVenta = controlErroresInt();
				System.out.println("Cantidad de la planta que quieres vender: ");
				int cantidadPlantaVenta = controlErroresInt();
				buscadorPlanta(codigoPlantaVenta,cantidadPlantaVenta);
				mostrarCatalogo(plantasVenta);
				
				contador++;
			}else {
				boolean resCorrecta = false;
				do {
					System.out.println("¿Quieres agregar otra planta, a la venta? si(1) o no(2):");
					int seguirVendiendo=controlErroresInt();
					if(seguirVendiendo==1) {
						contador=0;
						resCorrecta=true;
					}else if(seguirVendiendo==2){
						bandera=true;
						resCorrecta=true;
					}else {
						System.out.println("Escribe solo 1 o 2");
					}
				}while(!resCorrecta);
			}
		}while(!bandera);
		/*System.out.println("¿ Quieres añadir algo a la cesta de la compra ?, si(1) o no(2) ");
		int venta = controlErroresInt();
		if (venta==1) {
			generarVentas(plantasO);
		}*/ 
		System.out.println("Generando Ticket... ");
		generarTicket(plantasVenta);
	}
	
	public static void generarTicket(ArrayList<plantasClass> Venta){
	  	int codigoProd,cantidadProd;
	  	float precio,total=0;
	  	File directorio = new File("TICKETS");
	  	File ficheroTickets= new File("TICKETS//Tickets.txt");
	  	if(!directorio.exists()) {
		  	directorio.mkdirs();
	  	}
		if (!ficheroTickets.exists()) {
			try {
				ficheroTickets.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ArrayList<String> lineas = new ArrayList<String>();
		iDTickets++;
		try  {
			FileWriter escribir = new FileWriter(ficheroTickets, true);
			BufferedWriter buffer = new BufferedWriter(escribir);
			lineas.add("TICKET ID: "+iDTickets);
			lineas.add("----------------------//----------------------\n");
			lineas.add("Id de empleado que ha atendido : "+idIS+"\n");
			lineas.add("Nombre del empleado que ha atendido : "+nombreEmpleadoIS+"\n\n");
			lineas.add("Codigo del Producto\t | Cantidad\t | PrecioUnitario\n");
			for (plantasClass plantasVendidas : Venta) {
				codigoProd= plantasVendidas.codigo;
				cantidadProd = plantasVendidas.stock;
				precio = plantasVendidas.precio;
				lineas.add(""+codigoProd+"\t\t\t\t\t "+cantidadProd+"\t\t\t\t\t "+precio+"\n");
				total += cantidadProd*precio;	
			}	             
			lineas.add("----------------------//----------------------\n");
			lineas.add(""+total+"€\n\n");
			
			for (String linea : lineas) {
				buffer.write(linea);
				buffer.newLine();
			}
			buffer.close();

	        } catch (IOException i) {
	            i.printStackTrace();
	        }
	         
	}
	
/*
	    private static final String DIRECTORIO = "TICKETS";
	    private static final String FICHERO_TICKETS = DIRECTORIO + File.separator + "Tickets.txt";
	    private static final String FICHERO_DEVOLUCIONES = DIRECTORIO + File.separator + "Devoluciones.txt";
	    
	     * Lee Tickets.txt, busca un ticket por ID, lo mueve a Devoluciones.txt
	     * y lo elimina del archivo original.
	     * @param idTicketABuscar El ID del ticket a devolver (ej: "5").
	     */
	    public static void buscarTicket(String idTicketABuscar) {
	        File directorio = new File("Devoluciones");
	        File ficheroOriginal = new File("Tickets.txt");
	        File ficheroDevoluciones = new File("Devoluciones.txt");
	        File ficheroTemporal = new File("Devoluciones//TempTickets.tmp");
	        
	        // Almacena el contenido del ticket encontrado para moverlo
	        ArrayList<String> ticketEncontrado = new ArrayList<>();
	        ArrayList<String> lineasRestantes = new ArrayList<>();
	        boolean encontrado = false;
	        boolean dentroDeTicket = false;
	        if(!directorio.exists()) {
	        	directorio.mkdirs();
	        }
	        if (!ficheroDevoluciones.exists()) {
	        	try {
					ficheroDevoluciones.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        try (BufferedReader lector = new BufferedReader(new FileReader(ficheroOriginal))) {
	            String linea;
	            
	            // 1. LEER, BUSCAR y AISLAR el ticket
	            while ((linea = lector.readLine()) != null) {
	                
	                // Si encontramos el inicio de un ticket
	                if (linea.startsWith("TICKET ID: ")) {
	                    
	                    // Si ya estábamos procesando un ticket anterior, lo guardamos como 'restante'
	                    if (dentroDeTicket) {
	                        // El ticket anterior (que no es el buscado) es parte del archivo restante
	                        lineasRestantes.add("--------------------//--------------------\n"); 
	                        lineasRestantes.add(lineasRestantes.remove(0)); // Mover la línea del ID al final para la reescritura
	                    }
	                    
	                    // Reiniciar el ticket actual
	                    ticketEncontrado.clear();
	                    dentroDeTicket = true;
	                    
	                    // Comprobar si es el ticket que buscamos
	                    if (linea.substring("TICKET ID: ".length()).trim().equals(idTicketABuscar)) {
	                        encontrado = true;
	                        ticketEncontrado.add(linea); // Guarda la línea del ID
	                        System.out.println("Ticket con ID " + idTicketABuscar + " encontrado.");
	                        continue; // No añadirlo a la lista de líneas restantes
	                    } else {
	                        // No es el ticket buscado, lo marcamos para ser reescrito
	                        lineasRestantes.add(linea);
	                    }
	                }
	                
	                // Si encontramos la línea de separación
	                if (linea.equals("--------------------//--------------------\n")) {
	                    // Si ya estamos dentro, es el separador de fin de ticket
	                    dentroDeTicket = false;
	                }
	                
	                // Si la línea pertenece al ticket encontrado, la guardamos para mover
	                if (encontrado && !lineasRestantes.isEmpty() && lineasRestantes.get(lineasRestantes.size() - 1).startsWith("TICKET ID: ")) {
	                    // Si el ticket es el que buscamos, lo añadimos a la lista de devolución
	                    ticketEncontrado.add(linea);
	                } else if (!encontrado) {
	                    // Si el ticket anterior no fue el buscado, la línea pertenece a las líneas restantes
	                    lineasRestantes.add(linea);
	                }
	            }
	            
	        } catch (IOException e) {
	            System.err.println("Error de lectura: No se pudo acceder o leer el archivo TICKETS");
	            e.printStackTrace();
	            return;
	        }
	        
	        // --- PROCESO DE ESCRITURA ---
	        if (!encontrado) {
	            System.out.println("ERROR: No se encontró ningún ticket con ID = " + idTicketABuscar);
	            return;
	        }
	        
	        try {
	            // 2. GUARDAR en el archivo de devoluciones
	            // Usamos true para modo 'append' (añadir al final)
	            try (BufferedWriter escritorDevoluciones = new BufferedWriter(new FileWriter(ficheroDevoluciones, true))) {
	                if (ficheroDevoluciones.length() > 0) {
	                	escritorDevoluciones.write("--------------------//--------------------\n");
	                }
	                for (String linea : ticketEncontrado) {
	                	escritorDevoluciones.write(linea);
	                	escritorDevoluciones.newLine();
	                }
	                System.out.println(" Ticket movido a Devoluciones.txt");
	            }
	            
	            // 3. REESCRIBIR el archivo original (eliminando el ticket)
	            try (BufferedWriter escritorTemp = new BufferedWriter(new FileWriter(ficheroTemporal))) {
	                // Aquí usamos el separador entre tickets restantes
	                for (String linea : lineasRestantes) {
	                    escritorTemp.write(linea);
	                    escritorTemp.newLine();
	                }
	            }
	            
	            // 4. REEMPLAZAR el archivo original con el temporal (eliminación física)
	            Files.move(ficheroTemporal.toPath(), ficheroOriginal.toPath(), StandardCopyOption.REPLACE_EXISTING);
	            
	            System.out.println("Tickets.txt actualizado (ticket " + idTicketABuscar + " eliminado)");

	        } catch (IOException e) {
	            System.err.println("Error de escritura/movimiento: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	            // Asegurarse de que el temporal se borre si algo falla
	            ficheroTemporal.delete();
	        }
	    }
	         


	
	
	
	
	
	
	
	
	
	
	public static void menuVendedores() {
		boolean bandera = false;
		System.out.println("\nBienvenido al menú de Vendedores.");
		Catalogo();
		do {
			System.out.println("\n1. Visualizar Catalogo\n"
							+ "2. Generar Ventas\n"
							+ "3. Buscador de Tickets\n"
							+ "4. Salir.\n");
			int eleccion = controlErroresInt();
			
			
			switch (eleccion) {
			case 1:
				mostrarCatalogo(plantas);
				break;
			case 2:
				generarVentas();
				break;
			case 3:
				System.out.println("Escribe el Id del ticket a buscar: ");
				int idTicket;
				idTicket = controlErroresInt();
				String idTicketABuscar;
				idTicketABuscar = (""+idTicket);
				buscarTicket(idTicketABuscar);
				break;
			case 4:
				bandera = true;
				break;
			default:
				System.out.println("Escribe bien, por favor.");	
			}
		}while(!bandera);
		try (FileWriter fw = new FileWriter("Plantas//plantasBaja.xml", true)) {
		    fw.write("</plantas>");
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	public static void menuGestores() {
		Catalogo();
		MenuGestores gestores = new MenuGestores();
		gestores.menuGestores();
		try (FileWriter fw = new FileWriter("Plantas//plantasBaja.xml", true)) {
		    fw.write("</plantas>");
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		boolean bandera = true;
		lecturaEmpleados();
		System.out.println("Bienvenido al vivero Carías Ramos");
		do{
			int contrCorrecta = comprobacionContraseña(empleados);
				switch (contrCorrecta) {
				case 1:
					menuGestores();
					break;
				case 2:
					menuVendedores();
					break;
				default:
					System.out.println("Escribe bien, por favor.");	
				}
			
			
		}while (!bandera);
	}
	
}