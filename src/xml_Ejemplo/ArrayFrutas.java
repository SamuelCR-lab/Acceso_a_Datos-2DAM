package xml_Ejemplo;

import java.util.ArrayList;

public class ArrayFrutas {
		private String nombre;
		private String tipo;
		private String color;
		private String origen;
		private int precio;
		private String temporada;
		ArrayList <String> nutrientes;
		public ArrayFrutas(String nombre, String tipo, String color, String origen, int precio,String temporada) {
			super();
			this.nombre = nombre;
			this.tipo = tipo;
			this.color = color;
			this.origen = origen;
			this.precio = precio;
			this.temporada = temporada;
			this.nutrientes = null;
		}
		
		
		public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public String getTipo() {
			return tipo;
		}


		public void setTipo(String tipo) {
			this.tipo = tipo;
		}


		public String getColor() {
			return color;
		}


		public void setColor(String color) {
			this.color = color;
		}


		public String getOrigen() {
			return origen;
		}


		public void setOrigen(String origen) {
			this.origen = origen;
		}


		public int getPrecio() {
			return precio;
		}


		public void setPrecio(int precio) {
			this.precio = precio;
		}


		public ArrayList<String> getNutrientes() {
			return nutrientes;
		}


		public void setNutrientes(ArrayList<String> nutrientes) {
			this.nutrientes = nutrientes;
		}


		public String getTemporada() {
			return temporada;
		}


		public void setTemporada(String temporada) {
			this.temporada = temporada;
		}


		@Override
		public String toString() {
			return "Fruta de nombre = " + nombre + ", tipo = " + tipo + ", color = " + color + ", origen = " + origen
					+ ", precio = " + precio + ", nutrientes = " + nutrientes;
		}

		
}
