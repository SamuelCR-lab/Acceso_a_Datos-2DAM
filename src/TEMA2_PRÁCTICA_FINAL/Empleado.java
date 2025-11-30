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
	
	
	public static void nuevoEmpleado() {
		int idEmpleadoN = Funciones_y_Consultas.comprobacionDeIDs("empleado","idEMPLEADO")+1;
		System.out.print("Nombre del empleado: ");
		String nombre = Jugueteria.entrada.next();
		
		Date fechaInicio = new Date(System.currentTimeMillis());
		boolean bandera=false; 
		do {
			System.out.print("¿Que cargo tendria el nuevo empleado ? Jefe (1), Cajero(2) : ");
			int opcion = Jugueteria.controlDeErroresInt();
			switch (opcion) {
				case 1:
					Empleado empleadoNj = new Empleado(idEmpleadoN, nombre,cargo.Jefe, fechaInicio);
					Funciones_y_Consultas.registroEmpleadoNuevo(empleadoNj);
					bandera=true;
					break;
				case 2:
					Empleado empleadoNc = new Empleado(idEmpleadoN, nombre,cargo.Cajero, fechaInicio);
					Funciones_y_Consultas.registroEmpleadoNuevo(empleadoNc);
					bandera=true;
					break;
				default:
					System.err.println("ERROR, introduce un número entre 1 y 2.");
			}
		}while(!bandera);
		
		
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "Empleado de ID = "+id_Empleado+", Nombre = "+nombre+", Cargo = "+cargoGuardado+", Fecha de ingreso = "+fechaDeIngreso;
	}
	
	
	
}
