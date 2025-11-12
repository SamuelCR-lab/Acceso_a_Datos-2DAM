package EXAMEN_PRACTICA_FINAL_FICHEROS_2025;




import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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



public class Principal {
	static Scanner entrada = new Scanner(System.in);
	static ArrayList <plantasClass> plantas = new ArrayList<>();
	static ArrayList <plantasClass> plantasVenta = new ArrayList<>();
	static ArrayList <Empleado> empleados = new ArrayList<>();
	static File ficheroDATBaja = new File("Plantas//plantasBaja.dat");
	static File ficheroXMLBaja = new File("Plantas//plantasBaja.xml");
	static int idIS, iDTickets = 0;
	static String nombreEmpleadoIS;
	static long saltoFicheroBaja = 12;
	static int plantasBajas=0;

	
	
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
					if(empleado.getCargo().equals("gestor")) {
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
		if (!directorio.exists()) {
			directorio.mkdirs();
		}
		if(!ficheroDAT.exists()){
			try {
				ficheroDAT.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			creacionEmpleadosYPlantas.EscribirFichero();
		}
		
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

		if (!directorio.exists()) {
			directorio.mkdirs();
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Empleados//empleado.dat"))) {
			ArrayList<Empleado> listaEmpleados = (ArrayList<Empleado>) ois.readObject();
			empleados = listaEmpleados;
        } catch (EOFException ei) {
        	ei.getStackTrace();
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
		 ficheroDATBaja = new File("Plantas//plantasBaja.dat");
		ficheroXMLBaja = new File("Plantas//plantasBaja.xml");
		try {
			if((!ficheroDATBaja.exists())&&(!ficheroXMLBaja.exists())) {
				ficheroDATBaja.createNewFile();	
				ficheroXMLBaja.createNewFile();
			}else {
				if ((!ficheroDATBaja.exists())) {
					ficheroDATBaja.createNewFile();
				}
				if ((!ficheroXMLBaja.exists())){
					ficheroXMLBaja.createNewFile();
				}
			}
			try (FileWriter escrituraXML = new FileWriter(ficheroXMLBaja,true)) {
				if (!ficheroXMLBaja.exists()) {
					escrituraXML.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n");
					escrituraXML.write("<plantas>\n");
				}
				escrituraXML.write("<planta>\n");
				escrituraXML.write("<codigo>"+plantasSinExistencias.codigo+"</codigo>\n");
				escrituraXML.write("<nombre>"+plantasSinExistencias.nombre+"</nombre>\n");
				escrituraXML.write("<foto>"+plantasSinExistencias.foto+"</foto>\n");
				escrituraXML.write("<descripcion>"+plantasSinExistencias.descripcion+"</descripcion>\n");
				escrituraXML.write("</planta>\n");
			}
			try (RandomAccessFile escrituraDAT = new RandomAccessFile(ficheroDATBaja, "rw")) {
				
				if (plantasBajas != 0) {
					escrituraDAT.seek(saltoFicheroBaja*plantasBajas);
					int codigo =plantasSinExistencias.codigo;
					escrituraDAT.writeInt(codigo);
	            	float precio= plantasSinExistencias.precio;
	            	escrituraDAT.writeFloat(precio);
	      	        int stock= plantasSinExistencias.stock;
	      	        escrituraDAT.writeInt(stock);  
	      	        plantasBajas++;
	            }else {
					int codigo =plantasSinExistencias.codigo;
					escrituraDAT.writeInt(codigo);
	            	float precio= plantasSinExistencias.precio;
	            	escrituraDAT.writeFloat(precio);
	      	        int stock= plantasSinExistencias.stock;
	      	        escrituraDAT.writeInt(stock);
	      	        escrituraDAT.seek(saltoFicheroBaja);
	      	        plantasBajas++;
				}
			}
	         
		}catch(IOException i) {
			i.getStackTrace();
		}
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
		System.out.println("Generando Ticket... ");
		generarTicket(plantasVenta);
	}
	public static int IDticketsGenerados() {
		int numTicketsCreados = 0;
		File directorio = new File("TICKETS");
		File[] ticketsCreados = directorio.listFiles();
		if(ticketsCreados==null||ticketsCreados.length==0) {
			return 1;
		}
		Pattern patron = Pattern.compile("Tickets(\\d+)\\.txt");
        for (File archivo : ticketsCreados) {
            if (archivo.isFile()) {
                String nombreArchivo = archivo.getName();
                Matcher matcher = patron.matcher(nombreArchivo);
                
                if (matcher.find()) {
                    try {
                        int id = Integer.parseInt(matcher.group(1));
                        if (id > numTicketsCreados) {
                        	numTicketsCreados = id;
                        }
                    } catch (Exception e) {
                        e.getStackTrace();
                    }
                }
            }
        }
	  	return numTicketsCreados+1;
	}
	public static void generarTicket(ArrayList<plantasClass> Venta){
		int iDTickets=IDticketsGenerados();
	  	int codigoProd,cantidadProd;
	  	float precio,total=0;
	  	File directorio = new File("TICKETS");
	  	File ficheroTickets= new File("TICKETS//Tickets"+iDTickets+".txt");
	  	
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
	
	public static void buscarTicket(int idTicketABuscar) {
	 File directorioDestino = new File("Devoluciones");
	 File ficheroOriginal = new File("Tickets"+idTicketABuscar+".txt");
	 File ficheroDevoluciones = new File("Devoluciones"+idTicketABuscar+".txt");
	 File directorioOrigen = new File("TICKETS");
	 File[] ticketsCreados = directorioOrigen.listFiles();
	 Pattern patron = Pattern.compile("Tickets(\\d+)\\.txt"); 
	    for (File archivo : ticketsCreados) {
	         if (archivo.isFile()) {
	             String nombreArchivo = archivo.getName();
	             Matcher matcher = patron.matcher(nombreArchivo);
	             if (matcher.find()) {
	                  try {
		                	if (!directorioDestino.exists()) {
		                    		directorioDestino.mkdirs();
		                    }
		                    Path rutaOrigen = Paths.get(""+directorioOrigen+"//"+ficheroOriginal);
		                    Path rutaDestino = Paths.get(""+directorioDestino+"//"+ficheroDevoluciones);
		                    
		                    Files.move(rutaOrigen, rutaDestino, StandardCopyOption.REPLACE_EXISTING);
		                   } catch (IOException e) {
		                       e.getMessage();
		                   }
	                 }
	           }
	      }
	}
	         
	public static void guardarPlantasSalir() {
		try (RandomAccessFile guardadoArrayPlantas = new RandomAccessFile("Plantas//plantas.dat", "rw")) {
            for(plantasClass guardadoDePlantas :plantas) {
            	guardadoArrayPlantas.writeInt(guardadoDePlantas.codigo);
            	guardadoArrayPlantas.writeFloat(guardadoDePlantas.precio);
            	guardadoArrayPlantas.writeInt(guardadoDePlantas.stock);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	public static void menuVendedores() {
		boolean bandera = false;
		System.out.println("\n\t\tBienvenido al menú de Vendedores.");
		Catalogo();
		do {
			System.out.println("\n1. Visualizar Catalogo\n"
							+ "2. Generar Ventas\n"
							+ "3. Generar devolución Tickets\n"
							+ "4. Salir.\n");
			System.out.print("Seleccione una opción: ");
			int eleccion = controlErroresInt();
			switch (eleccion) {
			case 1:
				mostrarCatalogo(plantas);
				break;
			case 2:
				generarVentas();
				break;
			case 3:
				System.out.println("Escribe el Id del ticket a devolver: ");
				int idTicket;
				idTicket = controlErroresInt();
				buscarTicket(idTicket);
				break;
			case 4:
				bandera = true;
				break;
				
			default:
				System.out.println("Escribe bien, por favor.");	
			}
		}while(!bandera);
	}

	
	public static void main(String[] args) {
		boolean bandera = true;
		System.out.println("============== Bienvenido al vivero Carías Ramos ==============\n");
		lecturaEmpleados();
		do{
			int contrCorrecta = comprobacionContraseña(empleados);
				switch (contrCorrecta) {
				case 1:
					Catalogo();
					MenuGestores.main(args);
					break;
				case 2:
					menuVendedores();
					break;
				default:
					System.out.println("Escribe bien, por favor.");	
				}
			
			
		}while (!bandera);
		guardarPlantasSalir();
	}
	
}