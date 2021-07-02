package com.KD.challengeTecnico.clases.operacion;

import java.util.Calendar;

import com.KD.challengeTecnico.clases.tarjeta.Tarjeta;

public class Operacion {

	public int id;
	public double montoPagado;
	public Tarjeta tarjeta;
	public Calendar fechaOperacion;
	
	public Operacion(int _id, double _monto, Tarjeta _tarjeta, Calendar _fechaOperacion) {
		// TODO Auto-generated constructor stub
		montoPagado =_monto;
		id=_id;
		tarjeta =_tarjeta;
		fechaOperacion = _fechaOperacion;
	}

	public TasaOperacional obtenerInfoTasaOperacional() {
		return new TasaOperacional(tasaOperacion(), tarjeta.getMarcaTarjeta());
	}
	
	public double tasaOperacion() {
		return tarjeta.getMarcaTarjeta().montoDeInteres(montoPagado, fechaOperacion);
	}
	
	@Override
	public String toString() {
		return "Operacion [id="+id+", monto pagado=" + montoPagado + ", tarjeta=" + tarjeta.toString() + ", fechaOperacion=" + fechaOperacion.toString() + "]";
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getFechaOperacion() {
		return fechaOperacion;
	}

	public void setFechaOperacion(Calendar fechaOperacion) {
		this.fechaOperacion = fechaOperacion;
	}

	public double getMonto() {
		return montoPagado;
	}

	public void setMonto(double monto) {
		this.montoPagado = monto;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	public Boolean esValida() {
		return montoPagado<=1000;
	}

}
