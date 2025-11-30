package TEMA2_PRÁCTICA_FINAL;

import java.sql.Date;

public class Cambio {
	
	int idCambio,STOCK_STAND_idStand_Original  ,STOCK_STAND_ZONA_idzona_Original  ,STOCK_JUGUETE_idJuguete_Original  ,STOCK_STAND_idStand_Nuevo  ,STOCK_STAND_ZONA_idzona_Nuevo  ,STOCK_JUGUETE_idJuguete_Nuevo  ,EMPLEADO_idEMPLEADO;
	Date fecha;
	String Motivo;

	public Cambio(int idCambio, int sTOCK_STAND_idStand_Original, int sTOCK_STAND_ZONA_idzona_Original,
			int sTOCK_JUGUETE_idJuguete_Original, int sTOCK_STAND_idStand_Nuevo, int sTOCK_STAND_ZONA_idzona_Nuevo,
			int sTOCK_JUGUETE_idJuguete_Nuevo, int eMPLEADO_idEMPLEADO, Date fecha, String motivo) {
		super();
		this.idCambio = idCambio;
		STOCK_STAND_idStand_Original = sTOCK_STAND_idStand_Original;
		STOCK_STAND_ZONA_idzona_Original = sTOCK_STAND_ZONA_idzona_Original;
		STOCK_JUGUETE_idJuguete_Original = sTOCK_JUGUETE_idJuguete_Original;
		STOCK_STAND_idStand_Nuevo = sTOCK_STAND_idStand_Nuevo;
		STOCK_STAND_ZONA_idzona_Nuevo = sTOCK_STAND_ZONA_idzona_Nuevo;
		STOCK_JUGUETE_idJuguete_Nuevo = sTOCK_JUGUETE_idJuguete_Nuevo;
		EMPLEADO_idEMPLEADO = eMPLEADO_idEMPLEADO;
		this.fecha = fecha;
		Motivo = motivo;
	}
	public int getIdCambio() {
		return idCambio;
	}
	public void setIdCambio(int idCambio) {
		this.idCambio = idCambio;
	}
	public int getSTOCK_STAND_idStand_Original() {
		return STOCK_STAND_idStand_Original;
	}
	public void setSTOCK_STAND_idStand_Original(int sTOCK_STAND_idStand_Original) {
		STOCK_STAND_idStand_Original = sTOCK_STAND_idStand_Original;
	}
	public int getSTOCK_STAND_ZONA_idzona_Original() {
		return STOCK_STAND_ZONA_idzona_Original;
	}
	public void setSTOCK_STAND_ZONA_idzona_Original(int sTOCK_STAND_ZONA_idzona_Original) {
		STOCK_STAND_ZONA_idzona_Original = sTOCK_STAND_ZONA_idzona_Original;
	}
	public int getSTOCK_JUGUETE_idJuguete_Original() {
		return STOCK_JUGUETE_idJuguete_Original;
	}
	public void setSTOCK_JUGUETE_idJuguete_Original(int sTOCK_JUGUETE_idJuguete_Original) {
		STOCK_JUGUETE_idJuguete_Original = sTOCK_JUGUETE_idJuguete_Original;
	}
	public int getSTOCK_STAND_idStand_Nuevo() {
		return STOCK_STAND_idStand_Nuevo;
	}
	public void setSTOCK_STAND_idStand_Nuevo(int sTOCK_STAND_idStand_Nuevo) {
		STOCK_STAND_idStand_Nuevo = sTOCK_STAND_idStand_Nuevo;
	}
	public int getSTOCK_STAND_ZONA_idzona_Nuevo() {
		return STOCK_STAND_ZONA_idzona_Nuevo;
	}
	public void setSTOCK_STAND_ZONA_idzona_Nuevo(int sTOCK_STAND_ZONA_idzona_Nuevo) {
		STOCK_STAND_ZONA_idzona_Nuevo = sTOCK_STAND_ZONA_idzona_Nuevo;
	}
	public int getSTOCK_JUGUETE_idJuguete_Nuevo() {
		return STOCK_JUGUETE_idJuguete_Nuevo;
	}
	public void setSTOCK_JUGUETE_idJuguete_Nuevo(int sTOCK_JUGUETE_idJuguete_Nuevo) {
		STOCK_JUGUETE_idJuguete_Nuevo = sTOCK_JUGUETE_idJuguete_Nuevo;
	}
	public int getEMPLEADO_idEMPLEADO() {
		return EMPLEADO_idEMPLEADO;
	}
	public void setEMPLEADO_idEMPLEADO(int eMPLEADO_idEMPLEADO) {
		EMPLEADO_idEMPLEADO = eMPLEADO_idEMPLEADO;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getMotivo() {
		return Motivo;
	}
	public void setMotivo(String motivo) {
		Motivo = motivo;
	}
	
	public static void Devolucion() {
		int id = Funciones_y_Consultas.contadorDeDatos("cambio")+1,idStand_Original=0,idzona_Original=0,idJuguete_Original  ,idStand_Nuevo = 0,idzona_Nuevo = 0,idJuguete_Nuevo  ,idEMPLEADO=0,comprobacion;
		double montoTotalCompra;
		Date fecha = new Date(System.currentTimeMillis());
		String motivo;
		boolean bandera =false;
		do {
			Funciones_y_Consultas.mostrarVentas();
			System.out.print("Introduce el ID del juguete que quieres devolver: ");
			idJuguete_Original = Jugueteria.controlDeErroresInt();
			System.out.print("Introduce el ID del juguete que quieres cambiar por el anterior: ");
			idJuguete_Nuevo = Jugueteria.controlDeErroresInt();
			System.out.print("¿Cuanto fue el monto total de tu compra?: ");
			montoTotalCompra = Jugueteria.controlDeErroresDouble();
			Jugueteria.entrada.nextLine();
			System.out.println("Dinos los motivos de tu devolucion : ");
			motivo = Jugueteria.entrada.nextLine();
			Cambio inicioCambioProducto = new Cambio(id, idStand_Original, idzona_Original,idJuguete_Original, idStand_Nuevo, idzona_Nuevo,idJuguete_Nuevo, idEMPLEADO, fecha, motivo);
			comprobacion = Funciones_y_Consultas.realizarCambio(inicioCambioProducto,montoTotalCompra);
			if (comprobacion == 0) {
				System.err.println("Introduce bien el id del juguete o el monto de compra.");
			}else {
				bandera = true;
			}
		}while(!bandera);
	}
	
	
	
	
	
}
