package Ficheros;
import java.io.*;

public class FicherosCaracteres {
	public static void main (String[] args) {
		//Dentro de las "" se escribe direccion relativa
	 File fichero = new File ("FicheroEjemplo.txt");
		if(!fichero.exists()) {
			try {
				fichero.createNewFile();
			 
			 }catch(IOException e) {
				 e.getMessage();
			 } 
		}
		
			System.out.println("Archivo encontrado");
		 System.out.println("Nombre del fichero: "+fichero.getName());
		 System.out.println("Ruta: "+fichero.getPath());
		 System.out.println("Ruta Absoluta: "+fichero.getAbsolutePath());
		 System.out.println("Tamaño: "+fichero.length());
		 
		try {
			 //FileWriter escritura = new FileWriter(fichero); 
			//PrintWriter realiza una funcion como el buffered solo que en el print tenemos el println 
			//mientras que en el buffered hay que 
			 BufferedWriter pw = new BufferedWriter(new FileWriter(fichero,true));
			 
			 //El buffered writer es como el FileWriter, Pero en el buffered se puede incorporar el FileWriter
			 for(int i = 1;i<=10;i++) {
				 pw.write("Línea "+i+" : \n");
				 
			 }
			 pw.close();

				 //Devuelve caracter a caracter
				 FileReader lector = new FileReader(fichero);

				 //llena el buffer de los caracteres para leer lineas
				 BufferedReader buffer = new BufferedReader(lector);
				 String linea;
				 while((linea = buffer.readLine())!=null) {
					 System.out.println(""+linea);
				 
			 	}
				 lector.close();
		}catch(IOException a) {
			a.printStackTrace();
		}finally {
			System.out.println("BYE BYE...");
		}
		
		}
}
