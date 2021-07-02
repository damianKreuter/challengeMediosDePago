package com.KD.challengeTecnico.componentesSpringboot;

import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.KD.challengeTecnico.clases.operacion.Operacion;
import com.KD.challengeTecnico.clases.tarjeta.CardHolder;
import com.KD.challengeTecnico.clases.tarjeta.Tarjeta;
import com.KD.challengeTecnico.clases.tarjeta.fecha.FechaVencimiento;
import com.KD.challengeTecnico.clases.tarjeta.fecha.Mes;
import com.KD.challengeTecnico.clases.tarjeta.marca.AMEX;
import com.KD.challengeTecnico.clases.tarjeta.marca.NARA;
import com.KD.challengeTecnico.clases.tarjeta.marca.VISA;

@Component
public class ComponentData {

	private ArrayList<Operacion> operaciones;
	private ArrayList<Tarjeta> tarjetas;
	
	
	public ComponentData(ArrayList<Operacion> operaciones, ArrayList<Tarjeta> tarjetas) {
		super();
		this.operaciones = operaciones;
		this.tarjetas = tarjetas;
	}
	
	public Operacion operacionSegunID(int id) {
		return operaciones.stream()
				  .filter(operacion -> operacion.getId()==id)
				  .findAny().orElse(null);
	}
	
    public ArrayList<Operacion> getOperaciones() {
		return operaciones;
	}



	public void setOperaciones(ArrayList<Operacion> operaciones) {
		this.operaciones = operaciones;
	}
	
	public void agregarOperacion(Operacion operacion) {
		operaciones.add(operacion);
	}

	public void agregarTarjeta(Tarjeta tarjeta) {
		tarjetas.add(tarjeta);
	}

	public ArrayList<Tarjeta> getTarjetas() {
		return tarjetas;
	}



	public void setTarjetas(ArrayList<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}


	@PostConstruct
	public void init() {
    	CardHolder persona = new CardHolder("Ricardo", "Henrique");
		CardHolder persona2 = new CardHolder("Florencia", "Williams");
		Tarjeta tarjetaVisa = new Tarjeta(123l, persona, new VISA(), new FechaVencimiento(Mes.Diciembre, 2021));
		Tarjeta tarjetaAmex = new Tarjeta(564l, persona2, new AMEX(), new FechaVencimiento(Mes.Octubre, 2021));
		Tarjeta tarjetaNara = new Tarjeta(8735l, persona, new NARA(), new FechaVencimiento(Mes.Abril, 2021));
		Operacion operacionVISA = new Operacion(0, 300, tarjetaVisa, Calendar.getInstance());
		Operacion operacionVISA2 = new Operacion(1, 1300, tarjetaVisa, Calendar.getInstance());
		Operacion operacionAMEX = new Operacion(2, 100, tarjetaAmex, Calendar.getInstance());
		Operacion operacionNARA2 = new Operacion(3, 500, tarjetaNara, Calendar.getInstance());
		Operacion operacionNARA = new Operacion(4, 500, tarjetaNara, Calendar.getInstance());
		
		operaciones= new ArrayList<Operacion>();
		operaciones.add(operacionAMEX);
		operaciones.add(operacionNARA);
		operaciones.add(operacionNARA2);
		operaciones.add(operacionVISA);
		operaciones.add(operacionVISA2);
		
		tarjetas = new ArrayList<Tarjeta>();
		tarjetas.add(tarjetaVisa);
		tarjetas.add(tarjetaNara);
		tarjetas.add(tarjetaAmex);
		
		
    }
	
	
	
	
	
}
