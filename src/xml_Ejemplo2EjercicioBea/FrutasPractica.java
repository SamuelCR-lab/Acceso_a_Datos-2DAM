package xml_Ejemplo2EjercicioBea;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FrutasPractica {
	public static int posicion(double precio, ArrayList<ArrayFrutas> frutasO) {
		int posicion =-1;
		double valorAnterior=0;

		
		for(int i=0;i<frutasO.size();i++) {
			if(frutasO.get(i).getPrecio()==precio){
				
				posicion=i ;
			}else {
				if(valorAnterior>frutasO.get(i).getPrecio()) {
					posicion = i-1;
				}
			}
			valorAnterior=frutasO.get(i).getPrecio();
		}
		return posicion;
		
	}
	public static void main(String[] args) {
		ArrayList<ArrayFrutas> frutasO = new ArrayList<>();
		
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
			int cantidad = lista.getLength();

			for(int i = 0;i<cantidad;i++) {
				Node nodo = lista.item(i);//
				if(nodo.getNodeType() == Node.ELEMENT_NODE){
					Element frutas = (Element)nodo;
					ArrayList<String> nutrientes = new ArrayList<>();
					ArrayFrutas piezaFruta = new ArrayFrutas(
					 frutas.getElementsByTagName("nombre").item(0).getTextContent(),
					 frutas.getElementsByTagName("tipo").item(0).getTextContent(),
					 frutas.getElementsByTagName("color").item(0).getTextContent(),
					 frutas.getElementsByTagName("origen").item(0).getTextContent(),
					 Double.parseDouble(frutas.getElementsByTagName("precio").item(0).getTextContent()),
					 frutas.getElementsByTagName("temporada").item(0).getTextContent()
					);
					NodeList listNutrientes = frutas.getElementsByTagName("nutriente");
					for(int j = 0;j<listNutrientes.getLength();j++) {
						nutrientes.add(listNutrientes.item(j).getTextContent());
						frutasO.add(piezaFruta);
					}
					piezaFruta.setNutrientes(nutrientes);
					
				}
				frutasO.sort(Comparator.comparing(ArrayFrutas::getPrecio));
			}
			
			for (ArrayFrutas elemento:frutasO) {
				System.out.println(elemento.toString());
                for (int j = 0; j < frutasO.size(); j++) {
                    ArrayFrutas f1 = frutasO.get(j);
                    ArrayFrutas f2 = frutasO.get(j+1);

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
