package com.KD.challengeTecnico.clases.tarjeta.marca;

import java.util.Calendar;

public class NARA extends Marca {

	public NARA() {
		super("NARA");
		// TODO Auto-generated constructor stub
	}

	@Override
	public double tasaDeServicio(Calendar fechaOperacion) {
		return fechaOperacion.get(Calendar.DAY_OF_MONTH)*0.5;
	}
	@Override
	public String nombreTarjeta() {
		return "NARA";
	}
}
