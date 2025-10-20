package xml_Ejemplo;

import java.io.File;
import java.util.ArrayList;

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
			//Normaliza el documento eliminando saltos de linea y espacios en el documento
			doc.getDocumentElement().normalize();
			NodeList lista = doc.getElementsByTagName("fruta");
			
			ArrayList<ArrayFrutas> frutasO = new ArrayList<>();
			int cantidad = lista.getLength();
			
			for(int i = 0;i<cantidad;i++) {
				Node nodo = lista.item(i);//
				if(nodo.getNodeType() == Node.ELEMENT_NODE){
					Element frutas = (Element)nodo;
					String nombre = frutas.getElementsByTagName("nombre").item(0).getTextContent();
					String tipo = frutas.getElementsByTagName("tipo").item(0).getTextContent();
					String color = frutas.getElementsByTagName("color").item(0).getTextContent();
					String origen = frutas.getElementsByTagName("origen").item(0).getTextContent();
					int precio = Integer.parseInt(frutas.getElementsByTagName("precio").item(0).getTextContent());
					String temporada = frutas.getElementsByTagName("temporada").item(0).getTextContent();
					String nutrientes = frutas.getElementsByTagName("nutrientes").item(0).getTextContent();
					System.out.println("Nombre: "+nombre);
					System.out.println("Tipo: "+tipo);
					System.out.println("Color: "+color);
					System.out.println("Origen: "+origen);
					System.out.println("Precio: "+precio);
					System.out.println("Temporada: "+temporada);
					System.out.println("Nutriente: "+nutrientes);
					//Abandonamos el casteo y hacer arrayList
					frutasO.add(new ArrayFrutas(nombre, tipo, color, origen, precio, nutrientes));
				}
			}
			for (int i = 0; i < frutasO.size(); i++) {
                for (int j = 1; j < frutasO.size(); j++) {
                    ArrayFrutas f1 = frutasO.get(i);
                    ArrayFrutas f2 = frutasO.get(j);

                    if (f1.getPrecio() > f2.getPrecio()) {
                        System.out.println(f1.getNombre() + " es más cara que " + f2.getNombre());
                    } else if (f1.getPrecio() < f2.getPrecio()) {
                        System.out.println(f1.getNombre() + " es más barata que " + f2.getNombre());
                    } else {
                        System.out.println(f1.getNombre() + " y " + f2.getNombre() + " tienen el mismo precio");
                    }
                }
            }
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
