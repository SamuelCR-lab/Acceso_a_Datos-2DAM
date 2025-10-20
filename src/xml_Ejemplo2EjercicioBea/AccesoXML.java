package xml_Ejemplo2EjercicioBea;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AccesoXML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File ficheroXML = new File("persona.xml");
			//Construye un documento en la memoria cachede modo temporal osea una instancia 
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder docB = dbf.newDocumentBuilder();
			
			//Asociamos el xml al documento cache 
			Document doc = docB.parse(ficheroXML);
			//Normaliza el documento eluiminando saltos de linea y espacios en 
			doc.getDocumentElement().normalize();
			NodeList lista = doc.getElementsByTagName("persona");
			
			int cantidad = lista.getLength();
			for(int i = 0;i<cantidad;i++) {
				Node nodo = lista.item(i);
				if(nodo.getNodeType() == Node.ELEMENT_NODE){
					Element persona = (Element)nodo;
					String nombre = persona.getElementsByTagName("nombre").item(0).getTextContent();
					String edad = persona.getElementsByTagName("edad").item(0).getTextContent();
					String ciudad = persona.getElementsByTagName("ciudad").item(0).getTextContent();
					System.out.println("La persona es: "+nombre+" La edad "+edad+" que vive en "+ciudad);
				}
				
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
