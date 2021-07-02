package com.KD.challengeTecnico.clases.tarjeta.marca;

import java.util.Calendar;

public class Marca {

	protected String nombre;
	protected double tasaInteres;
	
	public Marca(double tasa, String nombre) {
		this.tasaInteres = tasa;
		this.nombre = nombre;
	}
	
	
	
	public Marca(String nombre) {
		super();
		this.tasaInteres=1;
		this.nombre = nombre;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public double getTasaInteres() {
		return tasaInteres;
	}



	public void setTasaInteres(double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}



	public double tasaDeServicio(Calendar fechaOperacion) {
		return tasaInteres;
	}
	
	public double montoDeInteres(double monto, Calendar fechaOperacion) {
		return monto*tasaDeServicio(fechaOperacion);
	}


	
	@Override
	public String toString() {
		return "Marca [nombre=" + nombre + ", tasaInteres=" + tasaInteres + "]";
	}



	public String nombreTarjeta() {
		return "Generica";
	}
	
	
}
