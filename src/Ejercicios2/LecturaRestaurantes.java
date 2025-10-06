package Ejecicios2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;




public class LecturaRestaurantes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try (BufferedReader buffer = new BufferedReader(new FileReader("C:\\Users\\samue\\eclipse-workspace\\Acceso-a-Datos\\src\\Ejecicios2\\Restaurants.txt"))) {

            // Leer la cabecera (campos)
            String frase = buffer.readLine();
            String[] campos = frase.split(",");

            String linea;
            while ((linea = buffer.readLine()) != null) {
                ArrayList<String> valores = new ArrayList<>();
                StringBuilder actual = new StringBuilder();
                boolean dentroComillas = false;

                // Recorremos carácter a carácter
                for (int i = 0; i < linea.length(); i++) {
                    char c = linea.charAt(i);

                    if (c == '"') {
                        // Entramos o salimos de comillas
                        dentroComillas = !dentroComillas;
                    } else if (c == ',' && !dentroComillas) {
                        // Si hay coma y no estamos dentro de comillas → fin de campo
                        valores.add(actual.toString());
                        actual.setLength(0); // vaciar acumulador
                    } else {
                        actual.append(c);
                    }
                }
                // Añadir último campo
                valores.add(actual.toString());

                // Imprimir campo: valor
                for (int i = 0; i < campos.length; i++) {
                    System.out.println(campos[i] + ": " + valores.get(i));
                }
                System.out.println("");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
