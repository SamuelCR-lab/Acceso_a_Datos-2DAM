package TEMA2_PRÁCTICA_FINAL;

import java.sql.Date;

enum cargo{ Jefe,Cajero }
public class Empleado {
	int id_Empleado;
	String nombre;
	cargo cargoGuardado;
	Date fechaDeIngreso;
	
	public Empleado(int id_Empleado, String nombre, cargo cargoGuardado, Date fechaDeIngreso) {
		super();
		this.id_Empleado = id_Empleado;
		this.nombre = nombre;
		this.cargoGuardado = cargoGuardado;
		this.fechaDeIngreso = fechaDeIngreso;
	}
	public int getId_Empleado() {
		return id_Empleado;
	}
	public void setId_Empleado(int id_Empleado) {
		this.id_Empleado = id_Empleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public cargo getCargoGuardado() {
		return cargoGuardado;
	}
	public void setCargoGuardado(cargo cargoGuardado) {
		this.cargoGuardado = cargoGuardado;
	}
	public Date getFechaDeIngreso() {
		return fechaDeIngreso;
	}
	public void setFechaDeIngreso(Date fechaDeIngreso) {
		this.fechaDeIngreso = fechaDeIngreso;
	}
	@Override
	public String toString() {
		return "Empleado de ID = "+id_Empleado+", Nombre = "+nombre+", Cargo = "+cargoGuardado+", Fecha de ingreso = "+fechaDeIngreso;
	}
	
	
	
}
