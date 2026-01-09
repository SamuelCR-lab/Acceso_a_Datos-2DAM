package EXAMEN_PRACTICA_FINAL_FICHEROS_2025;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
	static int idIS, iDTickets = 0;
	static ArrayList <plantasClass> plantas = new ArrayList<>();
	static ArrayList <plantasClass> plantasVenta = new ArrayList<>();
	static ArrayList <Empleado> empleados = new ArrayList<>();
	static ArrayList <plantasClass> plantasDevueltas;

	static ArrayList<String> lineas;
	static File directorioPlantas = new File("Plantas");
	static File ficheroPlantasDAT = new File("Plantas//plantas.dat");
	static File ficheroPlantasXML = new File("Plantas//plantas.xml");
	static File directorioEmpleados = new File("Empleados");
	static File ficheroEmpleadosDAT = new File("Empleados//empleado.dat");
	static File ficheroDATBaja = new File("Plantas//plantasBaja.dat");
	static File ficheroXMLBaja = new File("Plantas//plantasBaja.xml");
	static File directorioTickets = new File("TICKETS");

	static File directorioDevoluciones = new File("Devoluciones");
	static String nombreEmpleadoIS;
	static long saltoFicheroBaja = 12;

	
	
	public static int controlErroresInt() {
		boolean error = true;
		int dato =0;
		do {
			if(entrada.hasNextInt()) {
				dato = entrada.nextInt();
				if (dato >= 0) {
				    error = false;
                } else {
                    System.err.println("ERROR, El número no puede ser negativo.");
                }
			}else {
				System.err.println("ERROR, Escribe un número.");
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
				System.err.println("Has introducido mal los datos");
			}
			
		}while(datosCorrectos);	
		return respuesta;
	}
	
	public static void Catalogo(int opcion) {
		if (opcion==0){
			if (!directorioPlantas.exists()) {
				directorioPlantas.mkdirs();
			}
			
			if(!ficheroPlantasDAT.exists()){
				try {
					System.out.println("No se ha encontrado el archivo plantas.dat por lo que procedemos a crear uno predeterminado de base.\n");
					ficheroPlantasDAT.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
				creacionEmpleadosYPlantas.EscribirFichero();
			}
					try {
						DocumentBuilderFactory docBF = DocumentBuilderFactory.newInstance();
						DocumentBuilder docB = docBF.newDocumentBuilder();
						Document doc = docB.parse(ficheroPlantasXML);
						doc.getDocumentElement().normalize();
						NodeList lista = doc.getElementsByTagName("planta");
						ArrayList<plantasClass> plantasO = new ArrayList<>();
						int cantidad = lista.getLength();
						RandomAccessFile plantasDat = new RandomAccessFile (ficheroPlantasDAT,"r");
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
					}catch(Exception e){
						e.getStackTrace();
					}
		}else if(opcion==1) {
			for(plantasClass a : plantas) {
				if (a.stock != 0) {
					System.out.println(a.toString());
				}
			}
		}else {
			for(plantasClass todasLasPlantas : plantas) {
				System.out.println(todasLasPlantas.toString());	
			}
		}
	}
		
	public static void lecturaEmpleados() {

		if (!directorioEmpleados.exists()) {
			directorioEmpleados.mkdirs();
		}
		if(!ficheroEmpleadosDAT.exists()){
			try {
				System.out.println("No se ha encontrado el archivo empleados.dat por lo que crearemos unos empleados predeterminados de base.\n");
				ficheroEmpleadosDAT.createNewFile();
				creacionEmpleadosYPlantas.EscribirEmpleado();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		boolean bandera = false;
		do {
			ArrayList<Empleado> listaEmpleados = creacionEmpleadosYPlantas.leerEmpleados();
			if (listaEmpleados != null) {
				empleados = listaEmpleados;
				for(Empleado a : listaEmpleados) {
					System.out.println(a);
				}
				bandera = true;
			}else {
				System.out.println("Existe el archivo empleados.dat pero esta vacio, por lo que escribiremos en el unos empleados predeterminados\n");
				
				creacionEmpleadosYPlantas.EscribirEmpleado();
			}
		}while(!bandera);
	}
		
	public static void mostrarCatalogo(ArrayList <plantasClass> plantasO){	
		boolean bandera=false;
			for(plantasClass mostrarPlantas:plantasO) {
				System.out.println(mostrarPlantas.toString());
			}
			System.out.println("¿ Quieres realizar una venta ?: Si (1) o No (2)");
			do {
				int realizarVentaCatalogo = controlErroresInt();
				switch (realizarVentaCatalogo) {
					case 1:
						generarVentas();
						bandera=true;
						break;
					case 2:
						bandera = true;
						break;
					default:
						System.out.println("Escribe 1 o 2 ");
				}
			}while(!bandera);
				
	}
	public static void guardarPlantaBajas(plantasClass plantasSinExistencias)  {
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
			ArrayList<String> lineasXML = new ArrayList<String>();
			try (FileWriter escrituraXML = new FileWriter(ficheroXMLBaja,true)) {
				if (!ficheroXMLBaja.exists()) {
					lineasXML.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?> \n");
					lineasXML.add("<plantas>\n");
				}
				lineasXML.add("\t<planta>\n");
				lineasXML.add("\t\t<codigo>"+plantasSinExistencias.codigo+"</codigo>\n");
				lineasXML.add("\t\t<nombre>"+plantasSinExistencias.nombre+"</nombre>\n");
				lineasXML.add("\t\t<foto>"+plantasSinExistencias.foto+"</foto>\n");
				lineasXML.add("\t\t<descripcion>"+plantasSinExistencias.descripcion+"</descripcion>\n");
				lineasXML.add("\t</planta>\n");
				BufferedWriter buffer = new BufferedWriter(escrituraXML);
		        for(String lineasDevolucion : lineasXML) {
		        	buffer.write(lineasDevolucion);
				}
					buffer.close();
			}
			try (RandomAccessFile escrituraDAT = new RandomAccessFile(ficheroDATBaja, "rw")) {
				
					escrituraDAT.seek(ficheroDATBaja.length());
					int codigo =plantasSinExistencias.codigo;
					escrituraDAT.writeInt(codigo);
	            	float precio= plantasSinExistencias.precio;
	            	escrituraDAT.writeFloat(precio);
	      	        int stock= plantasSinExistencias.stock;
	      	        escrituraDAT.writeInt(stock);  
		
			}
	         
		}catch(IOException i) {
			i.getStackTrace();
		}
	}
	
	public static void buscadorPlanta(int codigoPlantaVenta,int cantidadPlantaVenta){
		//plantasClass plantaSinStock = null;
		for(plantasClass ventasPlantas :plantas) {
			if (codigoPlantaVenta == ventasPlantas.codigo) {
				if(cantidadPlantaVenta <= ventasPlantas.stock) {
					String nombreTemp = ventasPlantas.nombre;
					String fotoTemp = ventasPlantas.foto;
					String descripcion = ventasPlantas.descripcion;
					Float precio =ventasPlantas.precio;
					plantasVenta.add(new plantasClass(codigoPlantaVenta,nombreTemp,fotoTemp,descripcion,precio,cantidadPlantaVenta));
					ventasPlantas.stock = ventasPlantas.stock- cantidadPlantaVenta;
					if(ventasPlantas.stock <= 0) {
						guardarPlantaBajas(ventasPlantas);
						ventasPlantas.stock = 0;
						ventasPlantas.precio = 0;
						//plantaSinStock = ventasPlantas;
						guardarPlantasSalir();
					}
				}else {
					System.out.println("No hay suficiente Stock, introduce menos cantidad o lleva otra planta.");
				}
			}
		}
		/*if (plantaSinStock != null) {
			plantas.remove(plantaSinStock);
		}*/
		if(plantasVenta == null)
		System.out.println("No se ha encontrado una planta con ese código.");
	}
	public static void generarVentas() {
		boolean bandera =false;
		int contador=0;
		System.out.println("\nGenerando Venta... ");
		do {
			if (contador==0) {
				System.out.print("\nIntroduce el codigo de la planta a vender: ");
				int codigoPlantaVenta = controlErroresInt();
				int tamanio=plantas.size()+1;
				if((codigoPlantaVenta > 0)  && (codigoPlantaVenta <= tamanio)) {
					System.out.print("Cantidad de plantas que quieres vender: \n");
					int cantidadPlantaVenta = controlErroresInt();
					buscadorPlanta(codigoPlantaVenta,cantidadPlantaVenta);
					Catalogo(1);
					contador++;
				}else {
					System.out.println("Escribe un ID correcto disponible en el catalogo de entre 1 a "+tamanio+". ");
				}
				
			}else {
				boolean resCorrecta = false;
				do {
					System.out.print("\n¿Quieres agregar otra planta, al ticket ? si(1) o no(2): ");
					int seguirVendiendo=controlErroresInt();
					if(seguirVendiendo==1) {
						contador=0;
						resCorrecta=true;
					}else if(seguirVendiendo==2){
						generarTicket(plantasVenta);
						previsualizacionTicket();
						System.out.print("¿Quieres generar el ticket de venta? si(1) o no(2): ");
						seguirVendiendo=controlErroresInt();
						if(seguirVendiendo==1) {
							resCorrecta=true;
							bandera=true;
						}else if(seguirVendiendo==2){
							eliminarTicketTemp();
							contador=0;
							resCorrecta=true;
						}else {
							System.out.println("\nEscribe solo 1 o 2.\n");
						}
						
					}else {
						System.out.println("\nEscribe solo 1 o 2.\n");
					}
					
				}while(!resCorrecta);
			}
		}while(!bandera);
		System.out.println("\nGenerando Ticket... ");
		plantasVenta = new ArrayList<>();
	}
	
	public static void eliminarTicketTemp(){
		if (iDTickets == 0) { 
	        System.out.println("No hay ningún ticket temporal que borrar.");
	    }else {
	    File ficheroABorrar = new File("TICKETS//Tickets"+iDTickets+".txt");
		    if (ficheroABorrar.exists()) {
		        if (ficheroABorrar.delete()) {
		            System.out.println("\nVenta cancelada. El ticket temporal "+ficheroABorrar.getName()+" ha sido borrado.\n");
		        }
		    }
	    }
    }
	public static int IDticketsGenerados() {
		int numTicketsCreados = 0;
		
		File[] ticketsCreados = directorioTickets.listFiles();
		if(ticketsCreados==null||ticketsCreados.length==0) {
			System.out.println("Entra");
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
		iDTickets=IDticketsGenerados();
		System.out.println();
	  	int codigoProd,cantidadProd;
	  	float precio,total=0;
	  	if(!directorioTickets.exists()) {
	  		directorioTickets.mkdirs();
	  	}
	  	File ficheroTickets= new File("TICKETS//Tickets"+iDTickets+".txt");
		if (!ficheroTickets.exists()) {
			try {
				ficheroTickets.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
			try  {
				lineas = new ArrayList<String>();
				FileWriter escribir = new FileWriter(ficheroTickets);
				BufferedWriter buffer = new BufferedWriter(escribir);
				lineas.add("TICKET ID: "+iDTickets+".");
				lineas.add("----------------------//----------------------\n");
				lineas.add("Id de empleado que ha atendido : "+idIS+".\n");
				lineas.add("Nombre del empleado que ha atendido : "+nombreEmpleadoIS+".\n\n");
				lineas.add("Codigo del Producto\t | Cantidad\t | PrecioUnitario\n");
				for (plantasClass plantasVendidas : Venta) {
					codigoProd= plantasVendidas.codigo;
					cantidadProd = plantasVendidas.stock;
					precio = plantasVendidas.precio;
					lineas.add(""+codigoProd+"\t\t\t| "+cantidadProd+"\t\t\t| "+precio+"\n");
					total += cantidadProd*precio;	
				}	             
				lineas.add("----------------------//----------------------\n");
				lineas.add("TOTAL A PAGAR: "+total+"€.\n\n");
				
				for (String linea : lineas) {
					buffer.write(linea);
					buffer.newLine();
				}
					buffer.close();
					guardarPlantasSalir();
		        } catch (IOException i) {
		            i.printStackTrace();
		        }
	         
	}
	public static void previsualizacionTicket() {
		for(String mostrarTicket:lineas) {
			System.out.println(mostrarTicket.toString());
		}
	}
	
	public static void generarDevolucion(int idTicketABuscar) {
		
		 File ficheroOriginal = new File("Tickets"+idTicketABuscar+".txt");
		 File ficheroDevoluciones = new File("Devoluciones"+idTicketABuscar+".txt");

		 File[] ticketsCreados = directorioTickets.listFiles();
		 Pattern patron = Pattern.compile("Tickets(\\d+)\\.txt"); 
		    for (File archivo : ticketsCreados) {
		         if (archivo.isFile()) {
		             String nombreArchivo = archivo.getName();
		             Matcher matcher = patron.matcher(nombreArchivo);
		             if (matcher.find()) {
		                  try {
			                	if (!directorioDevoluciones.exists()) {
			                		directorioDevoluciones.mkdirs();
			                    }
			                    Path rutaOrigen = Paths.get(""+directorioTickets+"//"+ficheroOriginal);
			                    Path rutaDestino = Paths.get(""+directorioDevoluciones+"//"+ficheroDevoluciones);
			                    
			                    Files.move(rutaOrigen, rutaDestino, StandardCopyOption.REPLACE_EXISTING);
			                   } catch (IOException e) {
			                       e.getMessage();
			                   }
		                 }
		           }
		      }
		Pattern cambioPrimeraLinea = Pattern.compile("TICKET ID: (\\d+)");    
	    Float totalDevolucion = 0f;
	    plantasDevueltas = new ArrayList<>();

	    Pattern pProductos = Pattern.compile("^(\\d+)\\s*\\|\\s*(\\d+)\\s*\\|");//He buscado una expresion regular o patron para poder leer el .txt
	    Pattern pTotal = Pattern.compile("^TOTAL A PAGAR: ([-]?\\d+[.,]\\d+)");//Pasa lo mismo con el Total

	    System.out.println("Procesando devolución para el ticket: " + ficheroDevoluciones);
	    lineas = new ArrayList<String>();
	    try (BufferedReader reader = new BufferedReader(new FileReader(directorioDevoluciones+"//"+ficheroDevoluciones))) {
	        String linea;
	        
	        while ((linea = reader.readLine()) != null) {
	        	Matcher nombreDevolucion = cambioPrimeraLinea.matcher(linea);
	            if(nombreDevolucion.find()) {
	            	int codigoTicket = Integer.parseInt(nombreDevolucion.group(1).trim());
	            	linea = "DEVOLUCIÓN DEL TICKET nº "+codigoTicket+".";
	            }
	            Matcher mProd = pProductos.matcher(linea);
	            if (mProd.find()) {
	                int codigo = Integer.parseInt(mProd.group(1).trim());
	                int cantidad = Integer.parseInt(mProd.group(2).trim());
	                plantasDevueltas.add(new plantasClass(codigo, "", "", "", 0, cantidad));
	            }
	            
	            Matcher mTotal = pTotal.matcher(linea);
	            if (mTotal.find()) {
	                totalDevolucion = Float.parseFloat(mTotal.group(1).replace(",", "."));
	                linea = "TOTAL A PAGAR: -"+totalDevolucion+"€.\n\n";
	            }
	            lineas.add(linea);
	        }
	        FileWriter escribir = new FileWriter(""+directorioDevoluciones+"//"+ficheroDevoluciones);
			BufferedWriter buffer = new BufferedWriter(escribir);
	        for(String lineasDevolucion : lineas) {
	        	buffer.write(lineasDevolucion);
				buffer.newLine();
			}
				buffer.close();
	    } catch (IOException e) {
	        e.getStackTrace();
	    }
	    plantasClass plantaDevuelta = null;
	    System.out.println("Actualizando stock...");
	    for (plantasClass plantasADevolver : plantasDevueltas) {
	    	System.out.println(plantasADevolver);
	    	 for (plantasClass plantaModStock : plantas) {
	    		 if (plantasADevolver.codigo == plantaModStock.codigo) {
	    			if (plantaModStock.stock == 0) {
	    				 int stockAntiguo = plantaModStock.getStock();
	    				 int nuevoStock = stockAntiguo + plantasADevolver.getStock();
	    				 plantaModStock.setStock(nuevoStock);
	    				 plantaDevuelta = plantaModStock;
	    				 if(plantaDevuelta!=null) {
	    					 plantas.add(plantaDevuelta);
	    					 guardarPlantasSalir();
	    					 System.out.println(" El Stock de "+ plantaModStock.getCodigo()+"se actualizado a "+nuevoStock+". El catalogo ha sido actualizado correctamente");
	    					 Catalogo(0);
	    				 }
			        } else {
			             System.out.println("Advertencia: No se encontró la planta con código "+plantasADevolver.getCodigo()+" en el catálogo actual.");
			        }
	    		}
	    	 }
	    }
	    
	    

	
	}
	
	public static void guardarPlantasSalir() {
		try (RandomAccessFile guardadoArrayPlantas = new RandomAccessFile("Plantas//plantas.dat", "rw")) {
            for(plantasClass guardadoDePlantas : plantas) {
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
		System.out.println("\n\t\tBienvenido al menú de Vendedores "+nombreEmpleadoIS+".");
		

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
				Catalogo(1);
				generarVentas();
				break;
			case 3:
				System.out.println("Escribe el Id del ticket a devolver: ");
				int idTicket;
				idTicket = controlErroresInt();
				generarDevolucion(idTicket);
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
		if(!ficheroPlantasXML.exists()) {
			System.out.println("No se ha encontra el archivo de Plantas.xml, por lo que no se puede iniciar el programa.");
			System.exit(0);
		}
		System.out.println("============== Bienvenido al vivero Carías Ramos ==============\n");
		Catalogo(0);
		lecturaEmpleados();
		do{
			int contrCorrecta = comprobacionContraseña(empleados);
				switch (contrCorrecta) {
				case 1:
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
		System.out.println("Adios "+nombreEmpleadoIS+".");
	}
	
}