package EXAMEN_PRACTICA_FINAL_FICHEROS_2025;



import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class Principal {
	static Scanner entrada = new Scanner(System.in);
	static ArrayList <plantasClass> plantas;
	static ArrayList <plantasClass> plantasVenta;
	static ArrayList <Empleado> empleados;
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
	
	public static void Catalogo() {
		try {
			File ficheroDAT = new File("plantas.dat");
			File ficheroXML = new File("plantas.xml");
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
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("empleado.dat"))) {
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
	public static void guardarPlanta(ArrayList<plantasClass>plantasO) {
		try {
			File ficheroDAT = new File("plantasBaja.dat");
			File ficheroXML = new File("plantasBaja.xml");
			if((!ficheroDAT.exists())&&(!ficheroXML.exists())) {
				ficheroDAT.createNewFile();
				ficheroXML.createNewFile();
			}
			
		}catch(IOException i) {i.getStackTrace();}
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
						datosCorrectos = false;
						return respuesta=1;
					}else {
						datosCorrectos = false;
						return respuesta=2;
					}
				}
			}
			if (respuesta==0) {
				System.out.println("Has introducido mal los datos");
			}
		}while(datosCorrectos);	
		/*File ficheroEmpleados = new File("empleado.dat");
		try	{
		
		}catch(Exception a){a.getStackTrace();}*/
		return respuesta;
	}
	public static plantasClass buscadorPlanta(ArrayList<plantasClass> plantasO,int codigoPlantaVenta,int cantidadPlantaVenta){
		for(plantasClass ventasPlantas :plantasO) {
			if (codigoPlantaVenta == ventasPlantas.codigo) {
				if(cantidadPlantaVenta < ventasPlantas.stock) {
					String nombreTemp = ventasPlantas.nombre;
					String fotoTemp = ventasPlantas.foto;
					String descripcion = ventasPlantas.descripcion;
					Float precio =ventasPlantas.precio;
					plantasVenta.add(new plantasClass(codigoPlantaVenta,nombreTemp,fotoTemp,descripcion,precio,cantidadPlantaVenta));
					ventasPlantas.stock = ventasPlantas.stock - cantidadPlantaVenta;
					return ventasPlantas;
				}else {
					return null;
				}
			}
		}
		System.out.println("No se ha encontrado una planta con ese código.");
		return null;
	}
	public static void generarVentas(ArrayList<plantasClass> plantasO) {
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
				
				plantasClass plantaV = buscadorPlanta(plantasO,codigoPlantaVenta,cantidadPlantaVenta);
				plantasVenta.add(plantaV);
				mostrarCatalogo(plantas);
				
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
	
	public static void generarTicket(ArrayList<plantasClass> plantasVenta){
	  	iDTickets++;
	  	int codigoProd,cantidadProd;
	  	float precio,total=0;
	  	File ficheroTickets= new File("Tickets.txt");
		try (RandomAccessFile escrituraTickets = new RandomAccessFile("tickets.txt", "w")) {
			if (!ficheroTickets.exists()) {
		  		ficheroTickets.createNewFile();
		  	} 
			escrituraTickets.writeChars("Nombre del Tickets : "+iDTickets);
			escrituraTickets.writeChars("--------------------//--------------------");
			escrituraTickets.writeChars("Id de empleado que ha atendido : "+idIS);
			escrituraTickets.writeChars("Nombre del empleado que ha atendido : "+nombreEmpleadoIS);
			escrituraTickets.writeChars("");
			escrituraTickets.writeChars("Codigo del Producto\t Cantidad\t PrecioUnitario");
			for (plantasClass plantasVendidas : plantasVenta) {
				codigoProd= plantasVendidas.codigo;
				cantidadProd = plantasVendidas.stock;
				precio = plantasVendidas.precio;
				escrituraTickets.writeChars(""+codigoProd+"\t "+cantidadProd+"\t "+precio);
				total += cantidadProd*precio;	
				
			}	             
			escrituraTickets.writeChars("--------------------//--------------------");
			escrituraTickets.writeFloat(total);

	            System.out.println("Objetos escritos correctamente en empleado.dat");

	        } catch (IOException i) {
	            i.printStackTrace();
	        }
	         
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static void menuVendedores() {
		boolean bandera = false;
		System.out.println("Bienvenido al menú de Vendedores.");
		do {
			System.out.println("1. Visualizar Catalogo\n"
							+ "2. Generar Ventas\n"
							+ "3. Buscador de Tickets\n"
							+ "4. Salir.\n");
			int eleccion = controlErroresInt();
			Catalogo();
			
			switch (eleccion) {
			case 1:
				mostrarCatalogo(plantas);
				break;
			case 2:
				generarVentas(plantas);
				break;
			case 3:
				//buscadorTickects;
				break;
			case 4:
				bandera = true;
				break;
			default:
				System.out.println("Escribe bien, por favor.");	
			}
		}while(!bandera);
	}
	public static void menuGestores() {
		boolean bandera = false;
		do {
			System.out.println("Bienvenido al menú de Gestores.\n"
					+ "1. Dar de altas plantas.\n"
					+ "2. Dar de baja plantas.\n"
					+ "3. Modificar campos de las plantas.\n"
					+ "4. Dar de alta empleados.\n"
					+ "5. Dar de baja empleados.\n"
					+ "6. Recuperar empleados de baja.\n"
					+ "7. Estadisticas de las compras.\n"
					+ "8. Salir");
			int eleccion = controlErroresInt();
			Catalogo();
			switch (eleccion) {
				case 1:
					//Dar
					break;
				case 2:
					//GenerarVentas;
					break;
				case 3:
					//buscadorTickects;
					break;
				case 4:
					//buscadorTickects;
					break;
				case 5:
					//buscadorTickects;
					break;
				case 6:
					//buscadorTickects;
					break;
				case 7:
					//buscadorTickects;
					break;
				case 8:
					bandera = true;
					break;
				default:
					System.out.println("Escribe bien, por favor.");	
			}
		}while(!bandera);	
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