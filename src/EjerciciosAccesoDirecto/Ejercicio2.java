package EjerciciosAccesoDirecto;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Ejercicio2 {
	static Scanner entrada = new Scanner(System.in);
	public static void main(String[] args) {
		try {
			
			File fichero = new File("AlmacenamientosProductos.dat");
			fichero.createNewFile();
			//El segundo parametro indicamos de que forma vamos a crear el fichero con permiso de r-lectura w-escritura o rw con los dos
			RandomAccessFile raf = new RandomAccessFile (fichero,"rw");
			boolean bandera = true;
			while(bandera) {
				System.out.println("\nMenu :\n"
									+"1. Almacenar productos\n"
									+ "2. Visualizar productos (la lista entera)\n"
									+ "3. Visualizar los datos de un producto concreto basado en su ID\n"
									+ "4. Borrar productos dado un ID\n"
									+ "5. Modificar los campos de un producto (Cantidad y precio)\n"
									+ "6. Salir");
				int accion = entrada.nextInt();
				switch(accion) {
				case 1:
					
					System.out.print("Introduce el ID del producto ha almacenar: ");
					int iD = entrada.nextInt();
					
					
					System.out.print("Introduce Cantidad de este productos ha almacenar: ");
					int cantidadEnStock = entrada.nextInt();
					
					System.out.print("Introduce el precio del producto: ");
					double precio=entrada.nextDouble();
					
					raf.seek(raf.length());
					raf.write(iD);
					raf.write(cantidadEnStock);
					raf.writeDouble(precio);
					break;
				case 2:
					while(raf.getFilePointer()<raf.length()) {
						int iDImprimir=raf.readInt();
						int cantidadEnStockImprimir = raf.readInt();
						double precioImprimir = raf.readDouble();
						System.out.println("El libro de ID= "+iDImprimir+" de cantidad en stock= "+cantidadEnStockImprimir+" con un precio = "+precioImprimir);
					}
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				case 5:
					
					break;
				default:
					bandera = false;
				}
				}

		
		}catch(IOException e){
			e.getStackTrace();
		}

	}


}
