package TEMA2_PRÁCTICA_FINAL;

public class Stand {
	int idStand, idZona;
	String Nombre, Descripcion;
	
	public Stand(int idStand, int idZona, String nombre, String descripcion) {
		super();
		this.idStand = idStand;
		this.idZona = idZona;
		Nombre = nombre;
		Descripcion = descripcion;
	}
	
	public int getIdStand() {
		return idStand;
	}
	public void setIdStand(int idStand) {
		this.idStand = idStand;
	}
	public int getIdZona() {
		return idZona;
	}
	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Id del stand = " + idStand + ", id de la zona = "+idZona+", nombre = "+Nombre+", descripcion = "+Descripcion;
	}
	
	
	
}
