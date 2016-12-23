package com.testtaxi.util;

import org.apache.log4j.Logger;

import java.io.File;

/**
 * Proceso generico de comprobacion de rutas.
 */
public class Mantenimiento_Rutas {
	private static Logger log = Logger.getLogger(Mantenimiento_Rutas.class);

	/**
	 * Proceso de comprobacion de una ruta dada. En caso de existir nos notifica
	 * que existe y en caso contrario crea la carpeta y nos lo notifica.
	 * 
	 * @param ruta
	 *            Ruta a comprobar.
	 */
	public static void comprobar_Carpeta(String ruta) {
		File carpeta = new File(ruta);
		boolean creado = false;
		if (!carpeta.exists()) {
			creado = carpeta.mkdirs();
			creado = true;
		}
		if (creado) {
			if (log.isDebugEnabled()) {
				log.debug("------>>>>>  CREADO LA CARPETA " + ruta);
			}
		} else {
			if (log.isDebugEnabled()) {
				log.debug("------->>>>>>> CARPETA EXISTENTE " + ruta);
			}
		}
	}
}
