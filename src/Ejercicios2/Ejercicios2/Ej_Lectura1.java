package Ejercicios2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ej_Lectura1 {

	public static void main(String[] args) {
		File fichero = new File ("Ficherolectura.txt");
		if(!fichero.exists()) {
			try {
				fichero.createNewFile();
			 
			 }catch(IOException e) {
				 e.getMessage();
			 } 
		}
		try {
			FileReader lector = new FileReader(fichero);
	
			 //llena el buffer de los caracteres para leer lineas
			 BufferedReader buffer = new BufferedReader(lector);
			 //1 forma de hacerlo
			 /*int linea;
				 while((linea = lector.read()) != -1) {
					 if (linea != ' ') {
						 System.out.print(""+(char)linea);
					 }
				 }*/
			 //2 forma de hacerlo
			 String linea;
			 linea = buffer.readLine();
			 for(char caracter : linea.toCharArray() ) {
				 if (caracter != ' ') {
					 System.out.print(""+caracter);
				 }
			 }
		}catch(IOException a){
			a.getMessage();
		}
	}

}
