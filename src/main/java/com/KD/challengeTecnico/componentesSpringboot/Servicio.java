package com.KD.challengeTecnico.componentesSpringboot;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KD.challengeTecnico.clases.operacion.Operacion;
import com.KD.challengeTecnico.clases.operacion.TasaOperacional;
import com.KD.challengeTecnico.clases.tarjeta.Tarjeta;
import com.KD.challengeTecnico.clases.tarjeta.marca.Marca;

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
	
	public void crearOperacion(Operacion operacion) {
		data.agregarOperacion(operacion);
	}
	
	public void crearTarjeta(Tarjeta tarjeta) {
		data.agregarTarjeta(tarjeta);
	}
}
