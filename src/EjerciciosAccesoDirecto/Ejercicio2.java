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
			if(!fichero.exists()) {
				fichero.createNewFile();
			}
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
					raf.writeInt(iD);
					raf.writeInt(cantidadEnStock);
					raf.writeDouble(precio);
					break;
				case 2:
					raf.seek(0);
					while(raf.getFilePointer()<raf.length()) {
						int iDImprimir=raf.readInt();
						int cantidadEnStockImprimir = raf.readInt();
						double precioImprimir = raf.readDouble();
						System.out.println("El libro de ID= "+iDImprimir+" de cantidad en stock= "+cantidadEnStockImprimir+" con un precio = "+precioImprimir);
					}
					break;
				case 3: // Buscar producto por ID
				    System.out.print("Introduce el ID a buscar: ");
				    int idBuscar = entrada.nextInt();
				    boolean encontrado = false;

				    raf.seek(0);
				    while (raf.getFilePointer() < raf.length()) {
				        int idEncontrado = raf.readInt();
				        int cantidadEncontrado = raf.readInt();
				        double precioEncontrado = raf.readDouble();

				        if (idEncontrado == idBuscar) {
				            System.out.println("Producto encontrado:");
				            System.out.println("ID = " + idEncontrado + " Cantidad = " + cantidadEncontrado + "  Precio = " + precioEncontrado);
				            encontrado = true;
				            break;
				        }
				    }
				    if (!encontrado) {
				        System.out.println("No se encontró un producto con ID = " + idBuscar);
				    }
				    break;

				case 4: // Borrar producto por ID
				    System.out.print("Introduce el ID a borrar: ");
				    int idBorrar = entrada.nextInt();
				    boolean borrado = false;

				    File tempFile = new File("temp.dat");
				    RandomAccessFile rafTemp = new RandomAccessFile(tempFile, "rw");

				    raf.seek(0);
				    while (raf.getFilePointer() < raf.length()) {
				        int idEliminar = raf.readInt();
				        int cantidadEliminar = raf.readInt();
				        double precioEliminar = raf.readDouble();

				        if (idEliminar != idBorrar) {
				            rafTemp.writeInt(idEliminar);
				            rafTemp.writeInt(cantidadEliminar);
				            rafTemp.writeDouble(precioEliminar);
				        } else {
				            borrado = true;
				        }
				    }

				    raf.close();
				    rafTemp.close();

				    // Reemplazar fichero original
				    fichero.delete();
				    tempFile.renameTo(fichero);

				    raf = new RandomAccessFile(fichero, "rw"); // volver a abrir

				    if (borrado) {
				        System.out.println("Producto con ID " + idBorrar + " borrado con éxito.");
				    } else {
				        System.out.println("No se encontró un producto con ese ID.");
				    }
				    break;

				case 5: // Modificar cantidad y precio
				    System.out.print("Introduce el ID del producto a modificar: ");
				    int idModificar = entrada.nextInt();
				    boolean modificado = false;

				    raf.seek(0);
				    while (raf.getFilePointer() < raf.length()) {
				        long posicion = raf.getFilePointer();
				        int idLeido = raf.readInt();
				        int cantidadLeida = raf.readInt();
				        double precioLeido = raf.readDouble();

				        if (idLeido == idModificar) {
				            System.out.print("Cantidad que hay del producto: ");
				            cantidadLeida = entrada.nextInt();
				            System.out.print("Nuevo precio del producto : ");
				            precioLeido = entrada.nextDouble();

				            // volver a la posición exacta donde empieza cantidad
				            raf.seek(posicion + 4); // saltamos el ID
				            raf.writeInt(cantidadLeida);
				            raf.writeDouble(precioLeido);

				            modificado = true;
				            System.out.println("Producto modificado correctamente.");
				            break;
				        }
				    }
				    if (!modificado) {
				        System.out.println("No se encontró un producto con ese ID.");
				    }
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
