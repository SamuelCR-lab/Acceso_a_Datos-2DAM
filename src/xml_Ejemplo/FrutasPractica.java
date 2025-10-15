package xml_Ejemplo;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FrutasPractica {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File ficheroXML = new File("frutas.xml");
			//Construye un documento en la memoria cachede modo temporal osea una instancia 
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = dbf.newDocumentBuilder();
			
			//Asociamos el xml al documento cache 
			Document doc = docB.parse(ficheroXML);
			//Normaliza el documento eluiminando saltos de linea y espacios en 
			doc.getDocumentElement().normalize();
			NodeList lista = doc.getElementsByTagName("fruta");
			
			int cantidad = lista.getLength();
			int [] precios = null;
			for(int i = 0;i<cantidad;i++) {
				Node nodo = lista.item(i);
				if(nodo.getNodeType() == Node.ELEMENT_NODE){
					Element frutas = (Element)nodo;
					String nombre = frutas.getElementsByTagName("nombre").item(0).getTextContent();
					String tipo = frutas.getElementsByTagName("tipo").item(0).getTextContent();
					String color = frutas.getElementsByTagName("color").item(0).getTextContent();
					String origen = frutas.getElementsByTagName("origen").item(0).getTextContent();
					String precio = frutas.getElementsByTagName("precio").item(0).getTextContent();
					String nutrientes = frutas.getElementsByTagName("nutrientes").item(0).getTextContent();
					int precioB = Integer.parseInt(precio);
					System.out.println("Nombre: "+nombre);
					System.out.println("Tipo: "+tipo);
					System.out.println("Color: "+color);
					System.out.println("Origen: "+origen);
					System.out.println("Precio: "+precio);
					System.out.println("Nutriente: "+nutrientes);
					//Abandonamos el casteo y hacer arrayList
					precios[i]= precioB;
				}
			}
			for (int j = 0;j<cantidad;j++) {
				if (precios[j] < precios[j+1]) {
					int preciotemp = precios[j];
					
				}
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
