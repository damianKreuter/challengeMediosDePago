package com.KD.challengeTecnico.clases.operacion;

import com.KD.challengeTecnico.clases.tarjeta.marca.Marca;

public class TasaOperacional {
	private double monto;
	private Marca marcaTarjeta;
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public Marca getMarcaTarjeta() {
		return marcaTarjeta;
	}
	public void setMarcaTarjeta(Marca marcaTarjeta) {
		this.marcaTarjeta = marcaTarjeta;
	}
	public TasaOperacional(double monto, Marca marcaTarjeta) {
		super();
		this.monto = monto;
		this.marcaTarjeta = marcaTarjeta;
	}
	@Override
	public String toString() {
		return "TasaOperacional [monto=" + monto + ", marcaTarjeta=" + marcaTarjeta.toString() + "]";
	}
	
	
	
	
}
