package com.KD.challengeTecnico.clases.tarjeta.fecha;

import java.util.Calendar;

public class FechaVencimiento {
	public Mes mes;
	public int anio;

	public FechaVencimiento(Mes mes, int anio) {
		super();
		this.mes = mes;
		this.anio = anio;
	}
	
	public Mes getMes() {
		return mes;
	}
	public void setMes(Mes mes) {
		this.mes = mes;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	
	public Boolean puedeOperar(Calendar fechaOperacion) {
		return anio >= fechaOperacion.get(Calendar.YEAR) && mes.ordinal()>fechaOperacion.get(Calendar.MONTH);
	}

	@Override
	public String toString() {
		return "FechaVencimiento [mes=" + mes + ", anio=" + anio + "]";
	}
	
	
	
}
