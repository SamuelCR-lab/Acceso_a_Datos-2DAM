package Ejercicios2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Ej_Escribir1 {
	public static boolean esPrimo(int num) {
		boolean primo = true;
		if(num>2) {
			for(int i=2;i<=Math.sqrt(num);i++) {
				if (num % i==0) {
					primo = false;
				}
			}
		}
		return primo;
	}
	public static void Ej_Escribir1() {
		String rutaFichero="primos.txt";
		File fichero = new File(rutaFichero);
		try {
			if (!fichero.exists()) {
				fichero.createNewFile();
			}
			FileWriter escritura = new FileWriter(fichero);
			BufferedWriter ficheroEscritura = new BufferedWriter(escritura);
			for (int i = 2;i<=500;i++) {
				if(esPrimo(i)) {
					ficheroEscritura.write(i+"");
					ficheroEscritura.newLine();
				}
			}
			ficheroEscritura.close();
		}catch(IOException e){
			e.getStackTrace();
		}
	}
	public static void Ej_Escribir4() {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce el nombre del fichero 1 ");
		String fichero1 = entrada.nextLine();
		System.out.println("Introduce el nombre del fichero 2 ");
		String fichero2 = entrada.nextLine();
		System.out.println("Introduce el nombre de la ruta ");
		String ruta = entrada.nextLine();
		
		
		try {
			File rutaPrograma = new File(ruta);
			if (rutaPrograma.isDirectory() && rutaPrograma.exists()) {
				File fichero1Prog = new File(rutaPrograma,fichero1);
				File fichero2Prog = new File(rutaPrograma,fichero2);
				String nombreNuevo = fichero1Prog+"_"+fichero2Prog;
				File ficheroNuevo = new File(rutaPrograma,nombreNuevo);
				if (!ficheroNuevo.exists()) {
					ficheroNuevo.createNewFile();
				}
			}
			
			FileWriter escritura = new FileWriter(fichero1);
			BufferedWriter ficheroEscritura = new BufferedWriter(escritura);
			while((linea=bufferLectura.readLine())!=null) {
				
			}
			ficheroEscritura.close();
		}catch(IOException e){
			e.getStackTrace();
		}
	}
	public static void main(String[] args) {
		Ej_Escribir1();
		Ej_Escribir4();
	}

}
