package TEMA2_PRÁCTICA_FINAL;

enum categoria{ Pelota, Muñeca, Coche, Juego_De_Mesa, Plastilina, Peluche, Otro}
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

	public static void nuevoJuguete() {
		int id = Funciones_y_Consultas.comprobacionDeIDs("Juguete","idJuguete")+1;
		System.out.print("Nombre del juguete: ");
		String nombre = Jugueteria.entrada.next();
		System.out.print("Descripcion del juguete: ");
		String descripcion = Jugueteria.entrada.next();
		System.out.print("Precio del juguete : ");
		double precio = Jugueteria.controlDeErroresInt();
		System.out.print("Cantidad en stock del juguete: ");
		int cantidad = Jugueteria.controlDeErroresInt();
		boolean bandera=false; 
		do {
			System.out.print("¿Que tipo de juguete es ? Pelota (1), Muñeca(2), Coche(3), Juego_De_Mesa(4), Plastilina(5), Peluche(6), Otro(7): ");
			int opcion = Jugueteria.controlDeErroresInt();
			
			switch (opcion) {
				case 1:
					Juguete jugueteN = new Juguete(id,cantidad,nombre,descripcion,precio,categoria.Pelota);
					Funciones_y_Consultas.registroJugueteNuevo(jugueteN);
					bandera=true;
					break;
				case 2:
					Juguete jugueteNm = new Juguete(id,cantidad,nombre,descripcion,precio,categoria.Muñeca);
					Funciones_y_Consultas.registroJugueteNuevo(jugueteNm);
					bandera=true;
					break;
				case 3:
					Juguete jugueteNc = new Juguete(id,cantidad,nombre,descripcion,precio,categoria.Coche);
					Funciones_y_Consultas.registroJugueteNuevo(jugueteNc);
					bandera=true;
					break;
				case 4:
					Juguete jugueteNjm = new Juguete(id,cantidad,nombre,descripcion,precio,categoria.Juego_De_Mesa);
					Funciones_y_Consultas.registroJugueteNuevo(jugueteNjm);
					break;
				case 5:
					Juguete jugueteNp = new Juguete(id,cantidad,nombre,descripcion,precio,categoria.Plastilina);
					Funciones_y_Consultas.registroJugueteNuevo(jugueteNp);
					bandera=true;
					break;
				case 6:
					Juguete jugueteNpe = new Juguete(id,cantidad,nombre,descripcion,precio,categoria.Peluche);
					Funciones_y_Consultas.registroJugueteNuevo(jugueteNpe);
					bandera=true;
					break;
				case 7:
					Juguete jugueteNo = new Juguete(id,cantidad,nombre,descripcion,precio,categoria.Otro);
					Funciones_y_Consultas.registroJugueteNuevo(jugueteNo);
					bandera=true;
					
					break;
				default:
					System.err.println("ERROR, introduce un número del 1 al 7.");
			}
		}while(!bandera);
		
		
	}
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Juguete de ID = "+iD_Juguete+", Cantidad = "+cantidad+", Nombre = "+nombre+", Descripcion = "+descripcion+", precio = "+precio+", categoria = "+categoriaENUM;
	}
	
	
	
	
	
}
