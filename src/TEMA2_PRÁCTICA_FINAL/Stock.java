package TEMA2_PRÁCTICA_FINAL;

public class Stock {
	int idStand,idZona,idJuguete,Cantidad;
	
	
	
	public Stock(int idStand, int idZona, int idJuguete, int cantidad) {
		super();
		this.idStand = idStand;
		this.idZona = idZona;
		this.idJuguete = idJuguete;
		Cantidad = cantidad;
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

	public int getIdJuguete() {
		return idJuguete;
	}

	public void setIdJuguete(int idJuguete) {
		this.idJuguete = idJuguete;
	}

	public int getCantidad() {
		return Cantidad;
	}

	public void setCantidad(int cantidad) {
		Cantidad = cantidad;
	}
	
	
	
}
