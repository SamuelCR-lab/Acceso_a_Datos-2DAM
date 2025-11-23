package TEMA2_PRÁCTICA_FINAL;

enum categoria{ Pelotas, Muñecas, Vehículos, Otro }
public class Juguete {
	int iD_Juguete,cantidad;
	String nombre,descripcion;
	double precio;
	categoria categoriaENUM;
	
	public Juguete(int iD_Juguete, int cantidad, String nombre, String descripcion, double precio,
			categoria categoriaENUM) {
		super();
		this.iD_Juguete = iD_Juguete;
		this.cantidad = cantidad;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.categoriaENUM = categoriaENUM;
	}
	
	public int getiD_Juguete() {
		return iD_Juguete;
	}
	public void setiD_Juguete(int iD_Juguete) {
		this.iD_Juguete = iD_Juguete;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public categoria getCategoriaENUM() {
		return categoriaENUM;
	}
	public void setCategoriaENUM(categoria categoriaENUM) {
		this.categoriaENUM = categoriaENUM;
	}

	@Override
	public String toString() {
		return "Juguete de ID = "+iD_Juguete+", Cantidad = "+cantidad+", Nombre = "+nombre+", Descripcion = "+descripcion+", precio = "+precio+", categoria = "+categoriaENUM;
	}
	
	
	
}
