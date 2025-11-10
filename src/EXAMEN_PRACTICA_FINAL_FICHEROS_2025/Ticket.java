package EXAMEN_PRACTICA_FINAL_FICHEROS_2025;

import java.util.Date;

public class Ticket {
	int codigoEmp,codigoProd,cantidadPro,unidades,cantidad,total;
	String nombreEmp;
	Date fecha;
	
	public Ticket(int codigoEmp, int codigoProd, int cantidadPro, int unidades, int cantidad, int total,
			String nombreEmp, Date fecha) {
		super();
		this.codigoEmp = codigoEmp;
		this.codigoProd = codigoProd;
		this.cantidadPro = cantidadPro;
		this.unidades = unidades;
		this.cantidad = cantidad;
		this.total = total;
		this.nombreEmp = nombreEmp;
		this.fecha = fecha;
	}
	public int getCodigoEmp() {
		return codigoEmp;
	}
	public void setCodigoEmp(int codigoEmp) {
		this.codigoEmp = codigoEmp;
	}
	public int getCodigoProd() {
		return codigoProd;
	}
	public void setCodigoProd(int codigoProd) {
		this.codigoProd = codigoProd;
	}
	public int getCantidadPro() {
		return cantidadPro;
	}
	public void setCantidadPro(int cantidadPro) {
		this.cantidadPro = cantidadPro;
	}
	public int getUnidades() {
		return unidades;
	}
	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public String getNombreEmp() {
		return nombreEmp;
	}
	public void setNombreEmp(String nombreEmp) {
		this.nombreEmp = nombreEmp;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "Ticket ID de Empleado = " + codigoEmp + ", nombre del Empleado = " + nombreEmp +  ", fecha de venta = " + fecha+", codigo de los productos =" + codigoProd
				+ ", cantidad de Plantas = " + cantidadPro + ", unidades = " + unidades + ", cantidad = " + cantidad + ", total = " + total ;
	}
	
}
