package EXAMEN_PRACTICA_FINAL_FICHEROS_2025;

public class EmpleadoClass {
	int iD;
	String nombre,contraseña,cargo;
	
	public EmpleadoClass(int iD, String nombre, String contraseña, String cargo) {
		super();
		this.iD = iD;
		this.nombre = nombre;
		this.contraseña = contraseña;
		this.cargo = cargo;
	}
	
	public int getiD() {
		return iD;
	}
	public void setiD(int iD) {
		this.iD = iD;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getContraseña() {
		return contraseña;
	}
	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	
	
}
