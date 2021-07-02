package com.KD.challengeTecnico.clases.tarjeta;

public class CardHolder {
	public String nombre;
	public String apellido;
	
	
	
	public CardHolder(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	@Override
	public String toString() {
		return "CardHolder [nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	
}
