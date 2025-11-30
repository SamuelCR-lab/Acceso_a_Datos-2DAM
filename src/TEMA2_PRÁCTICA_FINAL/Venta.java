package TEMA2_PRÁCTICA_FINAL;

import java.sql.Date;

enum tipoPago{Efectivo, Tarjeta, paypal}

public class Venta {
	int idVenta, idEmpleado, idJuguete, idStand,idZona;
	Date fechaVenta;
	double Monto;
	tipoPago tipoDePago;
	public Venta(int idVenta, int idEmpleado, int idJuguete, int idStand,int idZona, Date fechaVenta, double monto,
			tipoPago tipoDePago) {
		super();
		this.idVenta = idVenta;
		this.idEmpleado = idEmpleado;
		this.idJuguete = idJuguete;
		this.idStand = idStand;
		this.idZona = idZona;
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
	public int getIdZona() {
		return idZona;
	}
	public void setIdZona(int idZona) {
		this.idZona = idZona;
	}
	
	public static void realizarVenta() {
		int idVenta = Funciones_y_Consultas.contadorDeDatos("venta")+1, empleado = Funciones_y_Consultas.idEmpleadoVenta,idJuguete,stand,zona;
		Date fechaVenta = new Date(System.currentTimeMillis());
		double totalVenta;
		boolean bandera = false;
		System.out.print("Escribe el ID del juguete a vender: ");
		idJuguete = Jugueteria.controlDeErroresInt();
		totalVenta = Funciones_y_Consultas.precioVenta(idJuguete);
		stand = Funciones_y_Consultas.buscarEnStock(idJuguete,"STAND_idStand");
		zona = Funciones_y_Consultas.buscarEnStock(idJuguete,"STAND_ZONA_idzona");

		do {
			System.out.print("¿Que forma de pago elige ? Efectivo (1), Tarjeta(2), Paypal(3): ");
			int opcion = Jugueteria.controlDeErroresInt();
			switch (opcion) {
				case 1:
					Venta VentaNE = new Venta(idVenta,empleado,idJuguete,stand,zona,fechaVenta,totalVenta,tipoPago.Efectivo);
					Funciones_y_Consultas.registroVenta(VentaNE);
					bandera=true;
					break;
				case 2:
					Venta VentaNT = new Venta(idVenta,empleado,idJuguete,stand,zona,fechaVenta,totalVenta,tipoPago.Tarjeta);
					Funciones_y_Consultas.registroVenta(VentaNT);
					bandera=true;
					break;
				case 3:
					Venta VentaNETC = new Venta(idVenta,empleado,idJuguete,stand,zona,fechaVenta,totalVenta,tipoPago.paypal);
					Funciones_y_Consultas.registroVenta(VentaNETC);
					bandera=true;
					break;
				default:
					System.err.println("ERROR, introduce un número entre 1, 2 y 3.");
			}
		}while(!bandera);
		
	}
	
	
	
	
	
	
	
	
	
	@Override
	public String toString() {
		return "ID de venta = "+idVenta+", ID empleado = "+idEmpleado+", ID juguete = "+idJuguete+", ID stand = "
				+idStand+", fecha venta = "+fechaVenta+", Monto = "+Monto+", tipo de pago = "+tipoDePago;
	}
	
	
	
	
	
	
}
