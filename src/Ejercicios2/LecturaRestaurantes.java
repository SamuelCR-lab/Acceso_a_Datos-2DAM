package Ejercicios2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class LecturaRestaurantes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		try (BufferedReader buffer = new BufferedReader(new FileReader(new File("Restaurants.txt")))){
			String linea;
			String[] campos;
			linea= buffer.readLine();
			campos = linea.split(",");
			while((linea = buffer.readLine())!= null) {
				
				String[] busquedaComillas = linea.split(",");
				for(int i = 0;i<campos.length;i++) {
					System.out.println("-"+campos[i]+": "+busquedaComillas[i]);
				}
			}
			System.out.println();
			
			
		}catch(IOException e) {
			e.getStackTrace();
		}
	}

}
