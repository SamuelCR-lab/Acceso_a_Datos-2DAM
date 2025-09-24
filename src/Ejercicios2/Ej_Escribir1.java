package Ejercicios2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
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
	public static void Ej_Escribir01 () {
		String rutaFichero = "primos.txt";
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
	
	public static void lecturaFicheros(File fichero, File ficheroEscritura) {
		try {
			//Abro primer fichero para lectura
			FileReader lectura = new FileReader(fichero);
			BufferedReader bufferedLectura = new BufferedReader(lectura);
			String linea;
			while((linea=bufferedLectura.readLine())!=null) {
				//leo las lineas
				System.out.println(linea);
				//5. la llamda a la funcion para escriba en un fichero
				escribirFichero(linea,ficheroEscritura);
			}
			bufferedLectura.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void escribirFichero(String linea, File ficheroEscritura) {
		
		try {
			//Abro primer fichero para Escritu
			FileWriter escribir = new FileWriter(ficheroEscritura,true);//El true es un \n hace que 
			//haya un salto de linea entre las lineas que escribas 
			BufferedWriter bufferedEscritura = new BufferedWriter(escribir);
			
			bufferedEscritura.write(linea);
			bufferedEscritura.newLine();
			
			bufferedEscritura.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void Ej_Escribir4() {
		Scanner entrada = new Scanner(System.in);
		//1 Pido datos al usuario
		System.out.println("Introduce el nombre del fichero 1 ");
		String fichero1 = entrada.nextLine();
		System.out.println("Introduce el nombre del fichero 2 ");
		String fichero2 = entrada.nextLine();
		System.out.println("Introduce el nombre de la ruta ");
		String ruta = entrada.nextLine();
		
		//2. Comprobamos que el directorio existe o que es un directorio
		File rutaPrograma = new File(ruta);
		if (rutaPrograma.isDirectory() && rutaPrograma.exists()) {
			//3. Comprobacion de la existencia de los ficheros y crear un fichero de los datos introducidos 
			//3.1. Creamos el objeto File -> fichero1Prog,fichero2Prog y ficheroNuevo
			File fichero1Prog = new File(rutaPrograma,fichero1);
			File fichero2Prog = new File(rutaPrograma,fichero2);
			String nombreNuevo = fichero1+"_"+fichero2;
			File ficheroNuevo = new File(rutaPrograma,nombreNuevo);
			
			if(fichero1Prog.exists()&&fichero1Prog.isFile()&&fichero2Prog.exists()&&fichero2Prog.isFile()) {
				try {
					System.out.println(nombreNuevo);
					if (!ficheroNuevo.exists()) {
						ficheroNuevo.createNewFile();
					}
				
					//4.Abrir ficheo para lectura - hacemos la funcion arriba
					lecturaFicheros(fichero1Prog,ficheroNuevo);
					lecturaFicheros(fichero2Prog,ficheroNuevo);

				}
				catch(IOException e) {
					e.printStackTrace();
				}
			}else {
				System.out.println(" No existe el fichero o es un directorio");
			}
		}	
		else {
			System.out.println(" No existe el directorio");
		}
	
	}
	
	
	
	
	
	public static void main(String[] args) {
		Scanner entrada = new Scanner(System.in);
		System.out.println("Introduce el número del ejercicio: ");
		int eleccion = entrada.nextInt();
		switch(eleccion) {
			case 1:
				Ej_Escribir01();
				break;
			case 2:
				Ej_Escribir4();
				break;
		}
		entrada.close();
	}

}



