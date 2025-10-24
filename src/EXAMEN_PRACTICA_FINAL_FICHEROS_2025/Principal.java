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
		do{
			
			System.out.println("Bienvenido al vivero Carías Ramos");
			boolean contrCorrecta = true;
			
			if (contrCorrecta) {
				System.out.println("");
				
				
				opcion = controlErroresInt();
				switch (opcion) {
				case 1:
					Catalogo();
					break;
				case 2:
					break;
				}
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
					int saltoID=4;
					try (RandomAccessFile plantasDat = new RandomAccessFile (ficheroDAT,"rw")) {
						Element plantas = (Element)nodo;
						int codigo = Integer.parseInt(plantas.getElementsByTagName("codigo").item(0).getTextContent());
						String nombre = plantas.getElementsByTagName("nombre").item(0).getTextContent();
						String foto = plantas.getElementsByTagName("foto").item(0).getTextContent();
						String descripcion = plantas.getElementsByTagName("descripcion").item(0).getTextContent();
						plantasDat.seek(4*saltoID);
						float precio = plantasDat.readFloat();
						int stock = plantasDat.readInt();
						plantasO.add(new plantasClass(codigo,nombre,foto,descripcion,precio,stock));
					}
				}
			}
			for(plantasClass elemento:plantasO) {
				System.out.println(elemento.toString());
			}
			}catch(Exception e){e.getStackTrace();
			}
	}
	public static boolean comprobacionContraseña() {
		int id;
		System.out.print("Introduce su número de idenificación: ");
		id = controlErroresInt();
		System.out.print("Introduce la contraseña: ");
		String contraseña = entrada.nextLine();
		try {
			
		}catch(Exception a){a.getStackTrace();}
		return true;
	}
	
}
