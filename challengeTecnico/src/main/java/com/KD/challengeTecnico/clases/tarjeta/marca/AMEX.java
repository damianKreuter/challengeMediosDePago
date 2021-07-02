package com.KD.challengeTecnico.clases.tarjeta.marca;

import java.util.Calendar;

public class AMEX extends Marca {

	public AMEX() {
		super("AMEX");
		// TODO Auto-generated constructor stub
	}

	@Override
	public double tasaDeServicio(Calendar fechaOperacion) {
		return fechaOperacion.get(Calendar.MONTH)*0.1;
	}
	
	@Override
	public String nombreTarjeta() {
		return "AMEX";
	}
}
