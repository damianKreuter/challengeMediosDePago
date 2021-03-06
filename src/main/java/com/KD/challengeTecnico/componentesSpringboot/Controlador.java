package com.KD.challengeTecnico.componentesSpringboot;

import java.util.ArrayList;
import java.util.Calendar;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.KD.challengeTecnico.clases.operacion.Operacion;
import com.KD.challengeTecnico.clases.operacion.TasaOperacional;
import com.KD.challengeTecnico.clases.tarjeta.Tarjeta;
import com.KD.challengeTecnico.clases.tarjeta.marca.Marca;
import com.KD.challengeTecnico.excepciones.ExcepcionConversionStringANumerica;
import com.KD.challengeTecnico.excepciones.ExcepcionNumeroInvalidosParaOperacion;
import com.KD.challengeTecnico.excepciones.ExcepcionNumeroNoPerteneceAConjunto;
import com.KD.challengeTecnico.excepciones.ExcepcionTarjetaVencida;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RestController
public class Controlador {

	@Autowired
	private Servicio servicio;
	
	@RequestMapping(method=RequestMethod.GET, value="/operacion/tasaInteres/{id_operacion}")
	public ResponseEntity<String> consultarTasaInteres(@PathVariable String id_operacion) {
//		System.out.println(payload);
		String respuesta1="";
		try {
			int id = obtenerNroIDDeString(id_operacion);
			TasaOperacional tasaOperacional = servicio.obtenerImporteDeTasaDeOperacion(id);
			return new ResponseEntity<String>(elementsToJson(tasaOperacional), HttpStatus.ACCEPTED);
		} catch (ExcepcionConversionStringANumerica e) {
			respuesta1 = e.getMessage();
			return new ResponseEntity<String>(respuesta1, HttpStatus.BAD_REQUEST);
		}
		catch (Exception e) {
			respuesta1 = e.getMessage();
			return new ResponseEntity<String>(respuesta1, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/operacion")
	public ResponseEntity<String> crearOperacion(@RequestBody String payload) {
		System.out.println(payload);
		String respuesta1="";
		try {
			Type type = new TypeToken<Operacion>(){}.getType();
			Operacion info =  new Gson().fromJson(payload, type);
			System.out.println(info.toString());
			servicio.crearOperacion(info);
			return new ResponseEntity<String>(elementsToJson("Se ha agregado la nueva operaci??n"), HttpStatus.ACCEPTED);
		}	catch(ExcepcionNumeroInvalidosParaOperacion e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		catch (Exception e) {
			respuesta1 = e.getMessage();
			return new ResponseEntity<String>(respuesta1, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/tarjeta")
	public ResponseEntity<String> crearTarjeta(@RequestBody String payload) {
		System.out.println(payload);
		String respuesta1="";
		try {
			Type type = new TypeToken<Tarjeta>(){}.getType();
			Tarjeta info =  new Gson().fromJson(payload, type);
			servicio.crearTarjeta(info);
			return new ResponseEntity<String>(elementsToJson("Se ha agregado la nueva operaci??n"), HttpStatus.ACCEPTED);
		} catch (ExcepcionTarjetaVencida e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		} 
		catch (Exception e) {
			respuesta1 = e.getMessage();
			return new ResponseEntity<String>(respuesta1, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	
	private int obtenerNroIDDeString(String id_string) throws ExcepcionConversionStringANumerica{
		try {
			return Integer.parseInt(id_string);
		} catch(Exception e) {
			throw new ExcepcionConversionStringANumerica("El ID Pasado ("+id_string+") no puede ser interpretado como n??mero");
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/operacion")
	public ResponseEntity<String> consultarTodasLasOperaciones() {
//		System.out.println(payload);
		String respuesta = "";
		try {
			ArrayList<Operacion> respuestaDeServicio = servicio.obtenerTodasLasOperaciones();
			Gson gson = new Gson();
			return new ResponseEntity<String>(elementsToJson(respuestaDeServicio), HttpStatus.ACCEPTED);
		} catch (Exception e) {
			respuesta = e.getMessage();
			return new ResponseEntity<String>(respuesta, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/operacion/marcaTarjeta/{id_operacion}")
	public ResponseEntity<String> consultarLaMarcaDeLaTarjetaDeOperacion(@PathVariable String id_operacion) {
		String respuesta = "";
		try {
			int id = obtenerNroIDDeString(id_operacion);
			Marca respuestaDeServicio = servicio.obtenerMarcaDeTarjetaDeOperacion(id);
			Gson gson = new Gson();
			return new ResponseEntity<String>(elementsToJson(respuestaDeServicio), HttpStatus.ACCEPTED);
		} catch (ExcepcionConversionStringANumerica e) {
			respuesta = e.getMessage();
			return new ResponseEntity<String>(respuesta, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			respuesta = e.getMessage();
			return new ResponseEntity<String>(respuesta, HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/operacion/{id_operacion}")
	public ResponseEntity<String> consultarOperacion(@PathVariable String id_operacion) {
		String respuesta = "";
		try {
			int id = obtenerNroIDDeString(id_operacion);
			Operacion respuestaDeServicio = servicio.obtenerOperacionEnParticular(id);
			Gson gson = new Gson();
			return new ResponseEntity<String>(elementsToJson(respuestaDeServicio), HttpStatus.ACCEPTED);
		} catch (ExcepcionConversionStringANumerica e) {
			respuesta = e.getMessage();
			return new ResponseEntity<String>(respuesta, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			respuesta = e.getMessage();
			return new ResponseEntity<String>(respuesta, HttpStatus.NOT_FOUND);
		}
	}
	
	private String elementsToJson(Object src) {
		Gson gson = new Gson();
		return gson.toJson(src);
	}
	
}
