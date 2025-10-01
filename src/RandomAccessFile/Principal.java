package RandomAccessFile;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Principal {

	public static void main(String[] args) {
		int numeroLista = 1;//4 bytes
		String nombre = "Pedro";//Sabemos que un caracter 2 bytes
		double nota = 5.12;//8 bytes
		
		try {
			File fichero = new File("datos.dat");
			fichero.createNewFile();
			//El segundo parametro indicamos de que forma vamos a crear el fichero con permiso de r-lectura w-escritura o rw con los dos
			RandomAccessFile raf = new RandomAccessFile (fichero,"rw");
			raf.writeInt(numeroLista);// 4 bytes
			raf.writeChars(nombre);//10 Bytes
			raf.writeDouble(nota);//8 Bytes
			
			//Imprimimos la posicion del puntero
			System.out.println(raf.getFilePointer());
			/*raf.seek(4);
			System.out.println(raf.readInt());*/
			String cadena="";
			raf.seek(0);
			while(raf.getFilePointer()<raf.length()) {
				System.out.println(raf.readInt());
					for(int i =0;i<nombre.length();i++) {
						cadena+=raf.readChar();	
					}
				System.out.print(cadena);
				System.out.println("\n"+raf.readDouble());
			}
			raf.close();
		}catch(IOException e){
			e.getMessage();
		}

	}

}
