package EXAMEN_PRACTICA_FINAL_FICHEROS_2025;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
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

	public static void main(String[] args) {
		boolean bandera = true;
		System.out.println("Bienvenido al vivero Carías Ramos");
		EmpleadoClass empleado1 = new EmpleadoClass(1,"Juan","1234","Vendedor");
		EmpleadoClass empleado2 = new EmpleadoClass(2,"Sopita","5678","Gestor");
		EmpleadoClass empleado3 = new EmpleadoClass(3,"Samuel","246810","Gestor");
		EmpleadoClass empleado4 = new EmpleadoClass(4,"Isabella","13579","Vendedor");
		EmpleadoClass[] arrayEmpleados = {empleado1,empleado2,empleado3,empleado4};
		
		do{
			
			int contrCorrecta = comprobacionContraseña(arrayEmpleados);
			
			
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
			for (int i =0;i<cantidad;i++) {
				Node nodo = lista.item(i);
				if (nodo.getNodeType()== Node.ELEMENT_NODE) {
					int saltoID=0;
					try (RandomAccessFile plantasDat = new RandomAccessFile (ficheroDAT,"r")) {
						Element plantas = (Element)nodo;
						int codigo = Integer.parseInt(plantas.getElementsByTagName("codigo").item(0).getTextContent());
						String nombre = plantas.getElementsByTagName("nombre").item(0).getTextContent();
						String foto = plantas.getElementsByTagName("foto").item(0).getTextContent();
						String descripcion = plantas.getElementsByTagName("descripcion").item(0).getTextContent();
						float precio = plantasDat.readFloat();
						int stock = plantasDat.readInt();
						plantasDat.seek(12*saltoID);
						plantasO.add(new plantasClass(codigo,nombre,foto,descripcion,precio,stock));
						saltoID += 1; 
					}
				}
			}
			/*System.out.println("¿ Quieres añadir algo a la cesta de la compra ?, si(1) o no(2) ");
			int venta = controlErroresInt();
			if (venta==1) {
				generarVentas(plantasO);
			}*/
			plantas = plantasO;
			}catch(Exception e){e.getStackTrace();}
		
	}
	public static void mostrarCatalogo(ArrayList <plantasClass> plantasO){
			
			for(plantasClass mostrarPlantas:plantasO) {
				System.out.println(mostrarPlantas.toString());
			}
			/*System.out.println("¿ Quieres añadir algo a la cesta de la compra ?, si(1) o no(2) ");
			int venta = controlErroresInt();
			if (venta==1) {
				generarVentas(plantasO);
			}*/

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
	public static int comprobacionContraseña(EmpleadoClass[] arrayEmpleados) {
		int id,respuesta=0;
		boolean datosCorrectos=true;
		do {
			System.out.print("Introduce su número de identificación: ");
			id = controlErroresInt();
			System.out.print("Introduce la contraseña: ");
			String contraseña = entrada.nextLine();
			for(int i=0;i<arrayEmpleados.length;i++) {
				if((arrayEmpleados[i].iD == id)&&(arrayEmpleados[i].contraseña.equals(contraseña))){
					if(arrayEmpleados[i].cargo.equals("Gestor")) {
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
					return ventasPlantas;
				}else {
					return null;
				}
			}else {
				System.out.println("No se ha encontrado una planta con ese código.");
				return null;
			}
		}
		return null;
	}
	public static void generarVentas(ArrayList<plantasClass> plantasO) {
		boolean bandera =false;
		int contador=0;
		System.out.println("Generando Venta... ");
		do {
			if (contador==0) {
				System.out.println("Introduce el Codigo de la planta: ");
				int codigoPlantaVenta = controlErroresInt();
				System.out.println("Cantidad de la planta que quieres vender: ");
				int cantidadPlantaVenta = controlErroresInt();
				
				plantasClass plantaVenta = buscadorPlanta(plantasO,codigoPlantaVenta,cantidadPlantaVenta);
				plantaVenta.stock = plantaVenta.stock - cantidadPlantaVenta;
				if (plantaVenta.stock == 0) {
					//guardarPlanta();
				}
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
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void menuVendedores() {
		System.out.println("Bienvenido al menú de Vendedores.");
		System.out.println("");
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
		default:
			System.out.println("Escribe bien, por favor.");	
		}
	}
	public static void menuGestores() {
		System.out.println("Bienvenido al menú de Gestores."
				+ "1. Mostrar Catalogo."
				+ "2. Generar Venta."
				+ "3.");
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
		default:
			System.out.println("Escribe bien, por favor.");	
		}
	}
	
}