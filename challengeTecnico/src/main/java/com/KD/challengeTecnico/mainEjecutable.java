package com.KD.challengeTecnico;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;

import com.KD.challengeTecnico.clases.operacion.Operacion;
import com.KD.challengeTecnico.clases.operacion.TasaOperacional;
import com.KD.challengeTecnico.clases.tarjeta.CardHolder;
import com.KD.challengeTecnico.clases.tarjeta.Tarjeta;
import com.KD.challengeTecnico.clases.tarjeta.fecha.FechaVencimiento;
import com.KD.challengeTecnico.clases.tarjeta.fecha.Mes;
import com.KD.challengeTecnico.clases.tarjeta.marca.AMEX;
import com.KD.challengeTecnico.clases.tarjeta.marca.NARA;
import com.KD.challengeTecnico.clases.tarjeta.marca.VISA;
import com.KD.challengeTecnico.excepciones.ExcepcionComparacion2Tarjetas;
import com.KD.challengeTecnico.excepciones.ExcepcionNumeroNoPerteneceAConjunto;

public class mainEjecutable {

	private static Operacion operacionVISA;
	private static Operacion operacionVISA2;
	private static Operacion operacionAMEX;
	private static Operacion operacionNARA;
	private static Operacion operacionNARA2;
	private static Tarjeta tarjetaVisa;
	private static Tarjeta tarjetaAmex;
	private static Tarjeta tarjetaNara;
	private static ArrayList<Tarjeta> tarjetas;
	private static ArrayList<Operacion> operaciones;
	
	// Clase principal iniciadora del programa ejemplo aprenderaprogramar.com
	public static void mainEjecutable(String [] args) {
	   //Aquí las instrucciones de inicio y control del programa
	   System.out.println ("Empezamos la ejecución del programa");
	   Boolean ejecutando = true;
	   crearObjetos();
	   primerMensaje();
	   while(ejecutando) {
		   //INFORMACION DE LA TARJETA
		   System.out.println ("Ingrese solo el numero para poder interactuar con el sistema\n");
		   Scanner s = new Scanner(System.in);
		   try {
			   int input = s.nextInt();
			   if(input == 1) {
				   verTarjetas();
			   } else if(input == 2) {
				   consultarOperaciones(s);
			   } else if(input == 3) {
				   ejecutando=false;
			   } else {
				   mensajeError();
			   }

		   } catch(Exception e) {
			   mensajeError();
		   }
	   }	   
	   System.out.println ("Cerramos el sistema");
	   return;
	} 
	
	private static void consultarOperaciones(Scanner scan) {
		Boolean consultarOperaciones = true;
		while(consultarOperaciones) {
			try {
			TimeUnit.SECONDS.sleep(1);
			verOperaciones();
		
			int num = scan.nextInt();
			switch (num) {
			case 1:
				System.out.println("Ingrese el numero de operación al cual se quiere obtener chequear su validéz");
				int numeroABuscar = scan.nextInt();
				if(tomarNumeroDeInputParaConjuntoDeDatos(numeroABuscar, operaciones.size()) ) {
					if(operaciones.get(numeroABuscar).esValida()){
						System.out.println("La operación solicitada con id "+numeroABuscar+" es válida");
					} else {
						System.out.println("La operación solicitada con id "+numeroABuscar+" no es válida");
					}
				}
				TimeUnit.SECONDS.sleep(2);
				break;
			case 2:
				System.out.println("Ingrese el numero de operación al cual se quiere conocer su tasa operacional");
				int numeroABuscar2 = scan.nextInt();
				if(tomarNumeroDeInputParaConjuntoDeDatos(numeroABuscar2, operaciones.size()) ) {
					Operacion operacionBuscada = operaciones.get(numeroABuscar2);
					TasaOperacional infoTasaOperacional = operacionBuscada.obtenerInfoTasaOperacional();
					System.out.println("Información sobre la TASA DE OPERACION:\n"+infoTasaOperacional.toString());
				}
				TimeUnit.SECONDS.sleep(2);
				break;
			case 3:
				consultarOperaciones = false;
				break;
			default:
				System.out.println("Por favor, ingrese un numero correcto para iniciar alguna operación");
				verOperaciones();
				break;
			}			
		} catch(Exception e) {
			System.out.println("Se ha ingresado mal los datos y/o ha ocurrido un error, vuelva a intertar");
		}
		}
	}
	
	
	private static void verOperaciones() {
		System.out.println ("Operaciones registradas:\n"
				+ mostrarOperaciones()
			+ "-----------------------------------------------------------\n"
		   	+ "1- Validar operacion [Verifica si la operación es valida]\n"
		   	+ "2- Tasa de operacion [El monto de la operación realizada]\n"
		   	+ "3- Volver\n");
	}
	
	private static String mostrarOperaciones() {
		String mensaje = "";
		for(int i=0;i<operaciones.size();i++) {
			mensaje = mensaje+i+"- "+operaciones.get(i).toString()+"\n";
		}
		return mensaje;
		
	}
	
	private static void primerMensaje() {
		System.out.println ("Comandos que podras utilizar ingresando número u nombre:\n"
		   		+ "1- TARJETAS [Muestra todas las tarjetas existente en el sistema]\n"
		   		+ "2- OPERACIONES [Muestra las operaciones registradas]\n"
		   		+ "3- FIN");
	}
	
	private static void mensajeError() {
		System.out.println ("Por favor, ingrese solo un numero de los mostrados por pantalla\n");
	}
	
	private static void verTarjetas() {
		Boolean viendoTarjetas = true;
		
			
			String nombreTrajetas="";
			for(int i=0;i<tarjetas.size();i++) {
				nombreTrajetas =nombreTrajetas+ i+"- "+tarjetas.get(i).nombre()+"\n";
			}
			while(viendoTarjetas) {
				
				 try {
					 TimeUnit.SECONDS.sleep(1);
					 opcionesDeInformacion(nombreTrajetas);
					 Scanner s = new Scanner(System.in);
					 int input = s.nextInt();
					   if(input ==1) {
						   System.out.println ("Ingrese el número de la posicion de la tarjeta que se quiere obtener la información:\n\n");
						   
						   int input2 = s.nextInt();
						//   viendoTarjetas=false;
						   System.out.println (tarjetas.get(input2).toString()+"\n\n");
						   TimeUnit.SECONDS.sleep(3);
					//	   opcionesDeInformacion(nombreTrajetas);
					   } else if(input==2) {
						   //VALIDAR TARJETA
						   System.out.println ("Ingrese el número de la posicion de la tarjeta que se quiere obtener la información:\n\n");
							   int input2 = s.nextInt();
							   if(tomarNumeroDeInputParaConjuntoDeDatos(input2, tarjetas.size()) && tarjetas.get(input2).esValido(Calendar.getInstance())) {
								   System.out.println ("La tarjeta seleccionada es válida\n\n");
							   } else {
								   System.out.println ("La tarjeta seleccionada no puede ser utilizada para realizar operaciones por vencimiento\n\n");
							   }
							   TimeUnit.SECONDS.sleep(3);
					   } else if(input==3) {
						   //Son distintas tarjetas: verificar si 2 tarjetas son distintas
						   Boolean sonDistintas = son2TarjetasDistintas(s);
						   System.out.println("El análisis de ambas tarjetasdió como resultado en cuanto si son Distintas: "+sonDistintas);
						   TimeUnit.SECONDS.sleep(2);
					   } else if(input==4) {
						   //VOLVER
						   viendoTarjetas=false;
					   } else {
						   mensajeError();
					   }
				 } catch (ExcepcionComparacion2Tarjetas e){
					 System.out.println(e.getMessage());
				 } catch(ExcepcionNumeroNoPerteneceAConjunto e) {
					 System.out.println(e.getMessage());
				 } catch(Exception e) {
					 mensajeError();
				 }
				 
			}
			primerMensaje();
		
	}
	
	private static Boolean son2TarjetasDistintas(Scanner scan) throws ExcepcionComparacion2Tarjetas {
		try {
			System.out.println("Ingrese el número que aparece en pantalla de la primera tarjeta");
			int input1 = scan.nextInt();
			if(!tomarNumeroDeInputParaConjuntoDeDatos(input1, tarjetas.size())) {
				throw new ExcepcionNumeroNoPerteneceAConjunto();
			}
			Tarjeta t1 =tarjetas.get(input1);
			System.out.println("Ingrese el número que aparece en pantalla de la segunda tarjeta");
			int input2 = scan.nextInt();
			if(!tomarNumeroDeInputParaConjuntoDeDatos(input2, tarjetas.size())) {
				throw new ExcepcionNumeroNoPerteneceAConjunto();
			}
			Tarjeta t2 =tarjetas.get(input2);
			return t1.sonMismaTarjeta(t2);
		} catch (Exception e) {
			throw new ExcepcionComparacion2Tarjetas();
		}
	}

	
	private static Boolean tomarNumeroDeInputParaConjuntoDeDatos(int input, int maxNum) throws Exception {
		if(input >= 0 && input <= maxNum) {
			return true;
		}
		throw new Exception("El número ingresado debe pertenecer a los numeros asociados a las tarjetas en pantalla");
	}
	
	private static void opcionesDeInformacion(String nombreTrajetas) {
		System.out.println ("Tarjetas registradas:\n"
				+ nombreTrajetas+"\n"
				+ "Funciones disponibles:\n"
		  		+ "1- INFORMACION:  Devuelve toda la información de esa tarjeta (sin incluir operaciones)\n"
		  		+ "2- Validar tarjeta: Verifica si la tarjeta puede ser utilizada para operaciones\n"
		  		+ "3- Son distintas tarjetas: Verifica si 2 tarjetas son distintas\n"
		  		+ "4- VOLVER\n");
	}

	public static void crearObjetos() {
		CardHolder persona = new CardHolder("Ricardo", "Henrique");
		CardHolder persona2 = new CardHolder("Florencia", "Williams");
		tarjetaVisa = new Tarjeta(123l, persona, new VISA(), new FechaVencimiento(Mes.Diciembre, 2021));
		tarjetaAmex = new Tarjeta(564l, persona2, new AMEX(), new FechaVencimiento(Mes.Octubre, 2021));
		tarjetaNara = new Tarjeta(8735l, persona, new NARA(), new FechaVencimiento(Mes.Abril, 2021));
		operacionVISA = new Operacion(0, 300, tarjetaVisa, Calendar.getInstance());
		operacionVISA2 = new Operacion(1, 1300, tarjetaVisa, Calendar.getInstance());
		operacionAMEX = new Operacion(2, 100, tarjetaAmex, Calendar.getInstance());
		operacionNARA2 = new Operacion(3, 500, tarjetaNara, Calendar.getInstance());
		operacionNARA = new Operacion(4, 500, tarjetaNara, Calendar.getInstance());
		
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
