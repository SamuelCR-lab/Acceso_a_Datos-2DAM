package EjerciciosAccesoDirecto;
import java.io.*;
import java.util.*;

public class LibreriaApp {
    private static final String FICHERO = "libros.dat";

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\nMenú Librería:\n"
            					+"1. Insertar libro\n"
            					+"2. Listar libros\n"
            					+"3. Ordenar libros por precio\n"
            					+"4. Filtrar por rango de precio\n"
            					+"5. Salir\n");
            int opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Título: ");
                    String titulo = entrada.nextLine();
                    System.out.print("Autor: ");
                    String autor = entrada.nextLine();
                    System.out.print("Precio: ");
                    double precio = entrada.nextDouble();
                    Libro libro = new Libro(titulo, autor, precio);
                    guardarLibro(libro);
                    break;

                case 2:
                    listarLibros();
                    break;

                case 3:
                    ordenarLibros();
                    break;

                case 4:
                    System.out.print("Precio mínimo: ");
                    double min = entrada.nextDouble();
                    System.out.print("Precio máximo: ");
                    double max = entrada.nextDouble();
                    filtrarPorPrecio(min, max);
                    break;

                case 5:
                    salir = true;
                    break;
            }
        }
    }

    private static void guardarLibro(Libro libro) {
        try (ObjectOutputStream oos = 
                new ObjectOutputStream(new FileOutputStream(FICHERO, true)) {
                    @Override
                    protected void writeStreamHeader() throws IOException {
                        reset();
                    }
                }) {
            oos.writeObject(libro);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<Libro> cargarLibros() {
        List<Libro> lista = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FICHERO))) {
            while (true) {
                Libro l = (Libro) ois.readObject();
                lista.add(l);
            }
        } catch (EOFException e) {
            // fin de fichero
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    private static void listarLibros() {
        List<Libro> lista = cargarLibros();
        lista.forEach(System.out::println);
    }

    private static void ordenarLibros() {
        List<Libro> lista = cargarLibros();
        lista.sort(Comparator.comparingDouble(Libro::getPrecio));
        lista.forEach(System.out::println);
    }

    private static void filtrarPorPrecio(double min, double max) {
        List<Libro> lista = cargarLibros();
        lista.stream()
             .filter(l -> l.getPrecio() >= min && l.getPrecio() <= max)
             .forEach(System.out::println);
    }
}

