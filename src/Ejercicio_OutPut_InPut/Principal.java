package Ejercicio_OutPut_InPut;

import java.io.*;
import java.util.ArrayList;

public class Principal {
	public static void EscrituraDatos() {
		File ficheroDatos = new File("datos.bin");
		try {
			if(!ficheroDatos.exists()) {
				ficheroDatos.createNewFile();
			}
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(ficheroDatos));
			dos.writeInt(2);
			dos.writeDouble(4.93);
			dos.writeBoolean(false);
			dos.writeUTF("Hola, Voy con mis pies");
		}catch(IOException o){
			o.printStackTrace();
		}
		
		
		
	}
	public static void EstructuraPersonas(ArrayList <Persona> personas) {
		String fichero = "personas.dat";
		File ficheroEscritura = new File (fichero);
		if(!ficheroEscritura.exists()) {			
			try {
				ficheroEscritura.createNewFile();
			}catch(IOException u){
				u.getStackTrace();
			}
		}else {
			if(ficheroEscritura.isFile()) {
				try {
					//El fileOutput funciona como el FileWriter
					FileOutputStream fos = new FileOutputStream(ficheroEscritura);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					oos.writeObject(personas);
					oos.close();
					
					
				}catch(IOException e){
					e.getStackTrace();
				}
			}else{
			System.out.println("Fichero es un directorio y no se puede escribir.");
			}
		}
	}
	
	public static void LecturaPersonas() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("personas.dat"));
			ArrayList <Persona> personas = (ArrayList <Persona>) ois.readObject();
			System.out.println("Personas que estan almacenados en el fichero");
			for(Persona p:personas) {
				System.out.println(p);
			}
		}catch(IOException|ClassNotFoundException i){
			i.printStackTrace();
			
		}
	}
	public static void LecturaDatos() {
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream("datos.bin"));
			int entero = dis.readInt();
			double numero = dis.readDouble();
			boolean booleano = dis.readBoolean();
			String frase = dis.readUTF();
			System.out.println("Entero = "+entero+" Double= "+numero+" Booleano= "+booleano+ " frase: "+frase);
		}catch(IOException a) {
			a.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList <Persona> personas = new ArrayList<>();
		personas.add(new Persona("Ana",23));
		personas.add(new Persona("Samuel",20));
		personas.add(new Persona("Paco",19));
		EstructuraPersonas(personas);
		LecturaPersonas();
		EscrituraDatos();
		
		
		LecturaDatos();
	}

}
