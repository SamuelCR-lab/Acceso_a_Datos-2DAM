package Ejercicios2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Ej_Lectura3 {
	public static void visualizar(ArrayList<String> palabra, ArrayList<Integer>veces) {
		int i=0;
		while(i<palabra.size()) {
			System.out.println(palabra.get(i)+": "+veces.get(i)    );
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList <String> palabra = new ArrayList<>();
		ArrayList <Integer> veces = new ArrayList<>();

		try (BufferedReader ficheroFrutas = new BufferedReader(new FileReader("C:/Users/DAM/eclipse-workspace/AccesoADatos/fruta.txt"))){
			String linea;
			while((linea=ficheroFrutas.readLine())!=null) {
				String fruta = linea.trim().toLowerCase();
				int indice = palabra.indexOf(fruta);
				if(indice==-1) {
					palabra.add(fruta);
					veces.add(1);
				}else {
					int cantidad=veces.get(indice)+1;
					veces.set(indice,cantidad);
				}
			}
			visualizar(palabra,veces);
		}catch (IOException e) {
			e.printStackTrace();
		}
	}

}
