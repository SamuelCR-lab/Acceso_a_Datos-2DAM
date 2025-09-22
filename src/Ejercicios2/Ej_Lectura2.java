package Ejercicios2;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Ej_Lectura2 {

	public static void main(String[] args) {
		File fichero = new File ("Ficherolectura2.txt");
		if(!fichero.exists()) {
			try {
				fichero.createNewFile();
			 
			 }catch(IOException e) {
				 e.getMessage();
			 } 
		}
		try {
			FileReader lector = new FileReader(fichero);
			int caracteres;
			int contadorCaracteres = 0;
			int numVocales = 0;
			while((caracteres = lector.read()) != -1) {
				char caracter =(char) caracteres;
				if((caracter == 'a' || caracter == 'e' || caracter == 'i'||caracter == 'o'||caracter == 'u')) {
					System.out.print(""+caracter+", ");
					numVocales++;
				}
				contadorCaracteres++;
			}
			System.out.println(".");

			System.out.println(" Hay en el archivo "+contadorCaracteres+" cantidad de caracteres y hay "+numVocales+" vocales");
		}catch(IOException a){
			a.getMessage();
		}
	}



}
