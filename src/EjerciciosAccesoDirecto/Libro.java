package EjerciciosAccesoDirecto;
import java.io.Serializable;

public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String autor;
    private double precio;

    public Libro(String titulo, String autor, double precio) {
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
    }

    public double getPrecio() {
        return precio;
    }
    public String toString() {
        return "Título: " + titulo + ", Autor: " + autor + ", Precio: " + precio;
    }
}
