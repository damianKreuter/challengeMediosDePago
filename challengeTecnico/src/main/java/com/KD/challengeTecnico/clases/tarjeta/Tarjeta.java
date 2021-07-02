package com.KD.challengeTecnico.clases.tarjeta;

import java.util.Calendar;

import com.KD.challengeTecnico.clases.tarjeta.fecha.FechaVencimiento;
import com.KD.challengeTecnico.clases.tarjeta.marca.Marca;

public class Tarjeta {
	public long nroTarjeta;
	public CardHolder datosPersona;
	public Marca marcaTarjeta;
	public FechaVencimiento fechaVencimiento;
	
	public Tarjeta(long nroTarjeta, CardHolder datosPersona, Marca marcaTarjeta, FechaVencimiento fechaVencimiento) {
		super();
		this.nroTarjeta = nroTarjeta;
		this.datosPersona = datosPersona;
		this.marcaTarjeta = marcaTarjeta;
		this.fechaVencimiento = fechaVencimiento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datosPersona == null) ? 0 : datosPersona.hashCode());
		result = prime * result + ((fechaVencimiento == null) ? 0 : fechaVencimiento.hashCode());
		result = prime * result + ((marcaTarjeta == null) ? 0 : marcaTarjeta.hashCode());
		result = prime * result + (int) (nroTarjeta ^ (nroTarjeta >>> 32));
		return result;
	}

	public Boolean sonMismaTarjeta(Tarjeta otraTarjeta) {
		return hashCode()==otraTarjeta.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarjeta other = (Tarjeta) obj;
		if (datosPersona == null) {
			if (other.datosPersona != null)
				return false;
		} else if (!datosPersona.equals(other.datosPersona))
			return false;
		if (fechaVencimiento == null) {
			if (other.fechaVencimiento != null)
				return false;
		} else if (!fechaVencimiento.equals(other.fechaVencimiento))
			return false;
		if (marcaTarjeta == null) {
			if (other.marcaTarjeta != null)
				return false;
		} else if (!marcaTarjeta.equals(other.marcaTarjeta))
			return false;
		if (nroTarjeta != other.nroTarjeta)
			return false;
		return true;
	}

	public double obtenerTasaDeOperacion(double monto, Calendar fecha) {
		return marcaTarjeta.montoDeInteres(monto, fecha);
	}


	public long getNroTarjeta() {
		return nroTarjeta;
	}

	public CardHolder getDatosPersona() {
		return datosPersona;
	}
	public void setDatosPersona(CardHolder datosPersona) {
		this.datosPersona = datosPersona;
	}
	public Marca getMarcaTarjeta() {
		return marcaTarjeta;
	}
	
	public Boolean esValido(Calendar fecha) {
		return fechaVencimiento.puedeOperar(fecha);
	}
	
	public Boolean esValidoParaMontoYFecha(double monto, Calendar fechaOperacion) {
		return fechaVencimiento.puedeOperar(fechaOperacion) && monto < 1000;
	}

	@Override
	public String toString() {
		return "Tarjeta [nroTarjeta=" + nroTarjeta + ", datosPersona=" + datosPersona.toString() + ", marcaTarjeta=" + marcaTarjeta.toString()
				+ ", fechaVencimiento=" + fechaVencimiento.toString() + "]";
	}
	
	public String nombre() {
		return "Tarjeta "+marcaTarjeta.nombreTarjeta()+" de "+datosPersona.getApellido() + ", "+datosPersona.getNombre();
	}
	
	
	
	
	
}
