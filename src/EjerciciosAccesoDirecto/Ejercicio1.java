package EjerciciosAccesoDirecto;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio1 {

	public static void main(String[] args) {
		int a = 0, b = 1,siguiente = 0,cantidad,posicion;
		Scanner entrada = new Scanner(System.in);
		try {
			File fichero = new File("FibonacciEjercicio1.dat");
			fichero.createNewFile();
			//El segundo parametro indicamos de que forma vamos a crear el fichero con permiso de r-lectura w-escritura o rw con los dos
			RandomAccessFile raf = new RandomAccessFile (fichero,"rw");
			raf.writeInt(a);// 4 bytes
			raf.writeInt(b);
			System.out.print("¿Cuantos números de la serie de fibonacci quieres?");
			cantidad = entrada.nextInt();
			for(int i = 0;i<cantidad;i++) {
				siguiente = a+b;
				raf.writeInt(siguiente);
				a = b;
				b = siguiente;
			}
			System.out.println("Dime un posicion de la serie de fibonacci y te consigo el número: ");
			posicion = entrada.nextInt();
			posicion=posicion-1;
			raf.seek(posicion*4);
			
			//Imprimimos la posicion del puntero
			System.out.println(raf.readInt());
			/*raf.seek(4);
			System.out.println(raf.readInt());*/
			
			raf.close();
		}catch(IOException e){
			e.getMessage();
		}

	}

}
