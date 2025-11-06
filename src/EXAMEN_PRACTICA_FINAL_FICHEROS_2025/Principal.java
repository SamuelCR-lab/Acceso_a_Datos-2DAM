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

	public static void main(String[] args) {
		boolean bandera = true;
		int opcion;
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
					
					break;
				case 2:
					Catalogo();
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
		}while(!error);
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
					try (RandomAccessFile plantasDat = new RandomAccessFile (ficheroDAT,"rw")) {
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
			for(plantasClass elemento:plantasO) {
				System.out.println(elemento.toString());
			}
			}catch(Exception e){e.getStackTrace();}
	}
	public static int comprobacionContraseña(EmpleadoClass[] arrayEmpleados) {
		int id,respuesta=0;
		System.out.print("Introduce su número de identificación: ");
		id = controlErroresInt();
		System.out.print("Introduce la contraseña: ");
		String contraseña = entrada.nextLine();
		for(int i=0;i<4;i++) {
			if((arrayEmpleados[i].iD == id)&&(arrayEmpleados[i].contraseña.equals(contraseña))){
				if(arrayEmpleados[i].cargo.equals("Gestor")) {
					respuesta=1;
					
				}else {
					respuesta =2;
				}
			}else {
				System.out.println("Has introducido el ID o la contraseña mal.");
			}
		}
		/*File ficheroEmpleados = new File("empleado.dat");
		try	{
		
		}catch(Exception a){a.getStackTrace();}*/
		return respuesta;
	}
	public static void menuVendedores() {
		System.out.println("Bienvenido al menú de Vendedores.");
		
		int eleccion = controlErroresInt();
		switch (eleccion) {
		case 1:
			Catalogo();
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
	public static void menuGestores() {
		System.out.println("Bienvenido al menú de Gestores.");
		
		int eleccion = controlErroresInt();
		switch (eleccion) {
		case 1:
			Catalogo();
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