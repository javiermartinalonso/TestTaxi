package com.testtaxi.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

/**
 * Carga de los parametros de la peticion web en un bean de forma reflexiva.
 */
public class Cargar_Valores {

	/**
	 * Metodo mediante el cual se pasa los valores de un objeto emisor request a
	 * cualquier otro objeto siempre cuando este definido como javabean y los
	 * nombre de las propiedades sean las mismas
	 * 
	 * @param objeto_transito
	 *            objeto al que se va a pasar los valores
	 * @param peticion
	 *            objeto emisor de donde se sacan los valores
	 */
	public static void cargar_Peticion(Object objeto_transito, HttpServletRequest peticion) {
		Class clase_transito = objeto_transito.getClass();
		// OBTENGO LOS METODOS DEL BEAN RECIBIDO
		Method lista_metodos_transito[] = clase_transito.getMethods();
		int numero_metodos_transito = lista_metodos_transito.length;
		// OBTENGO LAS PROPIEDADES DEL BEAN RECIBIDO
		Field campos[] = clase_transito.getDeclaredFields();
		int numero_atributos_reflexion = campos.length;
		// OBTENGO LA LISTA DE TODOS LOS PARAMETROS DE LA PETICION
		Enumeration<String> lista_atri = peticion.getParameterNames();
		String valor_parametro;
		// RECORRO TODAS LOS PARAMETROSD PARA COMPARALOS CON LOS NOMBRES DE LAS
		// PROPIEDADES DEL BEAN. CUANDO SEAN IGUALES INYECTO EL VALOR DEL
		// PARAMETRO EN EL BEAN
		// NO SE CONTEMPLAN TIPOS. SOLO TEXTO. AMPLIAR EN PROXIMAS REVISIONES
		while (lista_atri.hasMoreElements()) {
			valor_parametro = lista_atri.nextElement();
			for (int i = 0; i < numero_atributos_reflexion; i++) {
				if (campos[i].getName().equals(valor_parametro)) {
					for (int f = 0; f < numero_metodos_transito; f++) {
						if (lista_metodos_transito[f].getName().equalsIgnoreCase("set" + campos[i].getName())) {
							Object objeto_apasar[] = new Object[1];
							objeto_apasar[0] = peticion.getParameter(valor_parametro);
							try {
								lista_metodos_transito[f].invoke(objeto_transito, objeto_apasar);
							} catch (Exception e) {
							}
						}
					}
				}
			}
		}
	}
}
