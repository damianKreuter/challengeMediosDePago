package com.KD.challengeTecnico.componentesSpringboot;

import java.util.ArrayList;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.KD.challengeTecnico.clases.operacion.Operacion;
import com.KD.challengeTecnico.clases.operacion.TasaOperacional;
import com.KD.challengeTecnico.clases.tarjeta.Tarjeta;
import com.KD.challengeTecnico.clases.tarjeta.marca.Marca;
import com.KD.challengeTecnico.excepciones.ExcepcionNumeroInvalidosParaOperacion;
import com.KD.challengeTecnico.excepciones.ExcepcionNumeroNoPerteneceAConjunto;
import com.KD.challengeTecnico.excepciones.ExcepcionTarjetaVencida;

@Service
public class Servicio {

	@Autowired
	private ComponentData data;
	
	
	public ArrayList<Operacion> obtenerTodasLasOperaciones(){
		return data.getOperaciones();
	}
	
	public Operacion obtenerOperacionEnParticular(int id){
		return data.operacionSegunID(id);
	}
	
	public Marca obtenerMarcaDeTarjetaDeOperacion(int id) {
		return data.operacionSegunID(id).getTarjeta().getMarcaTarjeta();
	}
	
	public TasaOperacional obtenerImporteDeTasaDeOperacion(int id) {
		return data.operacionSegunID(id).obtenerInfoTasaOperacional();
	}
	
	public void crearOperacion(Operacion operacion) throws ExcepcionNumeroInvalidosParaOperacion{
		if(!operacion.esValida()) {
			throw new ExcepcionNumeroInvalidosParaOperacion();
		}
		data.agregarOperacion(operacion);
	}
	
	public void crearTarjeta(Tarjeta tarjeta) throws ExcepcionTarjetaVencida{
		if(!tarjeta.esValido(Calendar.getInstance())) {
			throw new ExcepcionTarjetaVencida();
		}
		data.agregarTarjeta(tarjeta);
	}
}
