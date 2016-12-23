package com.testtaxi.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * Utilidad de calculo de la diferencia entre dos fechas. Hay dos tipos de
 * calculo distintos. Calcular la diferencia en dias o dias,horas minutos y
 * segundos para una mayor precision.
 */

public class Resta_Fechas {
	public static final String DIAS = "dias";
	public static final String HORAS = "horas";
	public static final String MINUTOS = "minutos";
	public static final String SEGUNDOS = "segundos";
	
	private static Logger log = Logger.getLogger(Resta_Fechas.class);

	/**
	 * Calcula la diferencia entre dos fechas en horas minutos y segundos
	 * 
	 * @param fecha1
	 *            primera fecha
	 * @param fecha2
	 *            segunda fecha
	 * @return mapa que contiene separado los diferentes valores que son la
	 *         difenrecia entre las fechas dadas. Las claves del mapa son horas,
	 *         minutos,segundos,dias
	 */
	public static Map<String, String> getDiferencia(Date fecha1, Date fecha2) {
		Date fechaMayor = null;
		Date fechaMenor = null;
		Map<String, String> resultadoMap = new HashMap<String, String>();
		/*
		 * Verificamos cual es la mayor de las dos fechas, para no tener
		 * sorpresas al momento de realizar la resta.
		 */
		if (fecha1.compareTo(fecha2) > 0) {
			fechaMayor = fecha1;
			fechaMenor = fecha2;
		} else {
			fechaMayor = fecha2;
			fechaMenor = fecha1;
		}
		// los milisegundos diferencia entre fechas
		long diferenciaMils = fechaMayor.getTime() - fechaMenor.getTime();

		// obtenemos los segundos
		long segundos = diferenciaMils / 1000;

		// obtener los dias
		long dias = segundos / (3600 * 24);

		segundos = segundos % (3600 * 24);

		// obtenemos las horas
		long horas = segundos / 3600;

		// restamos las horas para continuar con minutos
		segundos -= horas * 3600;

		// igual que el paso anterior
		long minutos = segundos / 60;
		segundos -= minutos * 60;

		// ponemos los resultados en un mapa
		resultadoMap.put("horas", Long.toString(horas));
		resultadoMap.put("minutos", Long.toString(minutos));
		resultadoMap.put("segundos", Long.toString(segundos));
		resultadoMap.put("dias", Long.toString(dias));
		return resultadoMap;
	}

	public static void main(String[] args) {
		// 5:30:00 de Noviembre 10 - 1950 GMT-05:00
		java.util.Date fecha1 = new Date(-604070999750L);

		// 6:45:20 de Noviembre 10 - 1950 GMT-05:00
		java.util.Date fecha2 = new Date(-604066478813L);

		// Luego vemos como obtuve esas fechas
		System.out.println(getDiferencia(fecha1, fecha2));
		DateFormat form = DateFormat.getDateInstance(DateFormat.SHORT);
		Date fecha11 = null;
		Date fecha22 = null;
		try {
			fecha11 = form.parse("10/10/2009");
			fecha22 = form.parse("22/12/2010");
		} catch (ParseException e) {
			log.error("ERROR: Restando fechas ", e);
		}

		System.out.println(getDiferencia(fecha11, fecha22));
		System.out.println("SOLO DIAS "	+ fechasDiferenciaEnDias(fecha11, fecha22));
	}

	/**
	 * Devuelve la difencia entre dos fechas en dias
	 * 
	 * @param fechaInicial
	 *            primera fecha
	 * @param fechaFinal
	 *            segunda fecha
	 * @return valor que representa la diferencia entra las dos fechas dadas en
	 *         dias.
	 */
	public static int fechasDiferenciaEnDias(Date fechaInicial, Date fechaFinal) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String fechaInicioString = df.format(fechaInicial);
		try {
			fechaInicial = df.parse(fechaInicioString);
		} catch (java.text.ParseException ex) {
		}

		String fechaFinalString = df.format(fechaFinal);
		try {
			fechaFinal = df.parse(fechaFinalString);
		} catch (java.text.ParseException ex) {
		}

		long fechaInicialMs = fechaInicial.getTime();
		long fechaFinalMs = fechaFinal.getTime();
		long diferencia = fechaFinalMs - fechaInicialMs;
		double dias = Math.round(diferencia / (1000 * 60 * 60 * 24));
		return ((int) dias);
	}
}
