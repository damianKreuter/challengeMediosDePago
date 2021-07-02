package com.KD.challengeTecnico.clases.tarjeta.marca;

import java.util.Calendar;

public class VISA extends Marca {

	public VISA() {
		super("VISA");
		// TODO Auto-generated constructor stub
	}

	@Override
	public double tasaDeServicio(Calendar fechaOperacion) {
		return fechaOperacion.get(Calendar.YEAR)/fechaOperacion.get(Calendar.MONTH);
	}
	
	@Override
	public String nombreTarjeta() {
		return "VISA";
	}
}
