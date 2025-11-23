package TEMA2_PRÁCTICA_FINAL;

import java.sql.Date;

enum tipoPago{Efectivo, Tarjeta, Etc}

public class Venta {
	int idVenta, idEmpleado, idJuguete, idStand;
	Date fechaVenta;
	double Monto;
	tipoPago tipoDePago;
	public Venta(int idVenta, int idEmpleado, int idJuguete, int idStand, Date fechaVenta, double monto,
			tipoPago tipoDePago) {
		super();
		this.idVenta = idVenta;
		this.idEmpleado = idEmpleado;
		this.idJuguete = idJuguete;
		this.idStand = idStand;
		this.fechaVenta = fechaVenta;
		Monto = monto;
		this.tipoDePago = tipoDePago;
	}
	public int getIdVenta() {
		return idVenta;
	}
	public void setIdVenta(int idVenta) {
		this.idVenta = idVenta;
	}
	public int getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public int getIdJuguete() {
		return idJuguete;
	}
	public void setIdJuguete(int idJuguete) {
		this.idJuguete = idJuguete;
	}
	public int getIdStand() {
		return idStand;
	}
	public void setIdStand(int idStand) {
		this.idStand = idStand;
	}
	public Date getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public double getMonto() {
		return Monto;
	}
	public void setMonto(double monto) {
		Monto = monto;
	}
	public tipoPago getTipoDePago() {
		return tipoDePago;
	}
	public void setTipoDePago(tipoPago tipoDePago) {
		this.tipoDePago = tipoDePago;
	}
	@Override
	public String toString() {
		return "ID de venta = "+idVenta+", ID empleado = "+idEmpleado+", ID juguete = "+idJuguete+", ID stand = "
				+idStand+", fecha venta = "+fechaVenta+", Monto = "+Monto+", tipo de pago = "+tipoDePago;
	}
	
	
	
	
	
	
}
