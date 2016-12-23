package com.testtaxi.escuchadores.rastreo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * Procesos generales para todos los escuchadores de rastreo.
 * 
 */
public abstract class AbstractRastreo {
	// PROPIEDAD QUE DEFINE EL LOGGER A USAR PARA EL REGISTRO DE LAS ACCIONES
	public Logger log = Logger.getLogger(this.getClass());
	// PROPIEDADES DE CLASE PARA LOS PROCESOS DE CONVERSION
	private SimpleDateFormat formato_fecha = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	// constantes que representan los niveles de mensaje para el servicio log4j
	public static final int LOG_TRACE = 0;
	public static final int LOG_DEBUG = 1;
	public static final int LOG_INFO = 2;
	public static final int LOG_WARNING = 3;
	public static final int LOG_ERROR = 4;
	public static final int LOG_FATAL = 5;

	// PROPIEDAD PARA SEGUIR LA SECUENCIA DE LOS MENSAJES
	private static int numero_mensaje = 1;

	/**
	 * Proceso logico para poder eliminar de la estadistica los atributos propios del programa.<br/>
	 * Alguno de estos atributos se establecen a nivel de servletcontext, otros a nivel de sesion.
	 * 
	 * @param nombre_atributo. El nombre del atributo a procesar.
	 * @return Boolean que indicara si se procesa o no el atributo.
	 */
	protected boolean seguir_Proceso(String nombre_atributo) {
		boolean seguir = true;
		if (nombre_atributo.equals("contador_sesiones")
				|| nombre_atributo.equals("contador_peticiones")
				|| nombre_atributo.equals("contador_atributos_aplicacion")
				|| nombre_atributo.equals("contador_atributos_sesion")
				|| nombre_atributo.equals("contador_atributos_peticion")
				|| nombre_atributo.equals("numero_sesion")
				|| nombre_atributo.equals("numero_peticiones")) {
			seguir = false;
		}
		return seguir;
	}

	/**
	 * Proceso de escritura mediante el sistema de log4j.<br/>
	 * Ademas segun el nivel se formatea el texto de la siguiente manera:
	 * <ul>
	 * <li>
	 * Para trace las palabras capitalizadas.</li>
	 * <li>
	 * Para debug las palabras todo minusculas.</li>
	 * <li>
	 * Para info las palabras todo mayusculas.</li>
	 * <li>
	 * Para warn, error y fatal las palabras fraseadas.</li>
	 * </ul>
	 * 
	 * @param mensaje: Texto del mensaje a tratar.
	 * @param nivel: Prioridad dentro del sistema de log4j.
	 */
	public void registro(String mensaje, int nivel) {
		if (nivel == LOG_TRACE) {
			if (log.isTraceEnabled()) {
				mensaje = "Numero linea: " + String.valueOf(numero_mensaje) + " " + capitalizar_texto(mensaje);
				log.trace(mensaje);
				numero_mensaje++;
			}
		}
		if (nivel == LOG_DEBUG) {
			if (log.isDebugEnabled()) {
				mensaje = mensaje.toLowerCase().trim();
				log.debug(mensaje);
			}
		}
		if (nivel == LOG_INFO) {
			if (log.isInfoEnabled()) {
				mensaje = mensaje.toUpperCase().trim();
				log.info(mensaje);
			}
		}
		if (nivel == LOG_WARNING) {
			mensaje = fraseado_Texto(mensaje);
			log.warn(mensaje);
		}
		if (nivel == LOG_ERROR) {
			if (Priority.ERROR_INT == nivel) {
				log.error(mensaje);
			}
		}
		if (nivel == LOG_FATAL) {
			if (Priority.FATAL_INT == nivel) {
				log.fatal(mensaje);
			}
		}
	}
	
	

	private static String fraseado_Texto(String texto) {
		// int posicion = texto.indexOf("\n");
		// int posicion_inicio = 0;
		// while (posicion != -1) {
		// texto = texto.substring(posicion_inicio, posicion)
		// + texto.substring(posicion + 1).toUpperCase()
		// + texto.substring(posicion + 2);
		// posicion_inicio = posicion + 2;
		// posicion = texto.indexOf("\n", posicion);
		// }
		// texto = texto.substring(posicion + 1).toUpperCase();

		StringBuilder frasemayuscula = new StringBuilder();
		texto = texto.toLowerCase();
		String[] parrafos = texto.split("\\n");
		for (int i = 0, l = parrafos.length; i < l; ++i) {
			if (i > 0) {
				frasemayuscula.append(" ");
			}
			frasemayuscula.append(Character.toUpperCase(parrafos[i].charAt(0)))
					.append(parrafos[i].substring(1));
		}
		return texto;
	}

	/**
	 * Metodo de conveniencia para la traza de una forma mas simple.
	 * 
	 * @param mensaje
	 *            Texto a trazar en el log.
	 */
	public void registro(String mensaje) {
		this.registro(mensaje, AbstractRastreo.LOG_TRACE);
	}

	/**
	 * Proceso interno en donde capitalizamos el texto recibido.
	 * 
	 * @param texto_recibido
	 *            Texto a capitalizar
	 * @return Testo capitalizado
	 */
	private static String capitalizar_texto(String texto_recibido) {
		if (texto_recibido == null) {
			return "";
		}
		StringBuffer texto_atratar = new StringBuffer(texto_recibido.trim()
				.toLowerCase());
		if (texto_atratar.length() == 0) {
			return texto_atratar.toString();
		}
		Character siguiente_caracter;
		int i = 0;
		siguiente_caracter = texto_atratar.charAt(i);
		while (i < texto_atratar.length()) {
			texto_atratar.setCharAt(i++,
					Character.toUpperCase(siguiente_caracter.charValue()));
			if (i == texto_atratar.length()) {
				return texto_atratar.toString();
			}
			// Miramos los espacios en blanco
			siguiente_caracter = texto_atratar.charAt(i);
			while (i < texto_atratar.length() - 2
					&& !Character.isWhitespace(siguiente_caracter.charValue())) {
				siguiente_caracter = texto_atratar.charAt(++i);
			}

			if (!Character.isWhitespace(siguiente_caracter.charValue())) {
				// si no hay espacio en blanco es el final del texto
				return texto_atratar.toString();
			}

			// quitamos todos los espacios en blanco
			siguiente_caracter = texto_atratar.charAt(++i);
			while (i < texto_atratar.length()
					&& Character.isWhitespace(siguiente_caracter.charValue())) {
				texto_atratar.deleteCharAt(i);
				siguiente_caracter = texto_atratar.charAt(i);
			}
		}
		return texto_atratar.toString();
	}

	/**
	 * Proceso interno que nos sirve para conocer el contenido de los atributos
	 * que lanzan los eventos correspondientes. Solo se trata los tipos
	 * siguientes de atributos. <br/>
	 * Procesos definidos:<br/>
	 * <table border=1>
	 * <thead>
	 * <tr aling="center">
	 * <th colspan="2" >
	 * Procesos definidos</td></th>
	 * <tr>
	 * <th>
	 * Tipo atributo</th>
	 * <th>
	 * Descripcion</th>
	 * </tr>
	 * </thead> <tbody>
	 * <tr>
	 * <td>
	 * Atributo de tipo String.</td>
	 * <td>
	 * Cadena de texto pasada.</td>
	 * </tr>
	 * <tr>
	 * <td>
	 * Atributo de tipo Integer.</td>
	 * <td>
	 * Valor numerico entero.</td>
	 * </tr>
	 * <tr>
	 * <td>
	 * Atributo de tipo usuario.</td>
	 * <td>
	 * Se devuelve solo el nombre</td>
	 * </tr>
	 * <tr>
	 * <td>
	 * Atributo de tipo ResourceBundle.</td>
	 * <td>
	 * Se devuelve el idioma seleccionado.</td>
	 * </tr>
	 * <tr>
	 * <td>
	 * Atributo de tipo Double.</td>
	 * <td>
	 * Valor numerico decimal.</td>
	 * </tr>
	 * <tr>
	 * <td>
	 * Atributo de tipo Date.</td>
	 * <td>
	 * Fecha/Hora en texto.</td>
	 * </tr>
	 * </tbody>
	 * </table>
	 * 
	 * @param atributo
	 *            Objeto que se aniade/quita/reemplaza en la sesion.
	 * @return Contenido del mismo en tipo String.
	 */
	public String coger_Valor(Object atributo) {
		String valor_texto = null;
		if (atributo instanceof String) {
			valor_texto = (String) atributo;
		}
		if (atributo instanceof Integer) {
			valor_texto = ((Integer) atributo).toString();
		}
		if (atributo instanceof Double) {
			valor_texto = ((Double) atributo).toString();
		}

		if (atributo instanceof Date) {
			valor_texto = formato_fecha.format((Date) atributo);
		}

		// if (atributo instanceof Usuarios) {
		// valor_texto = " nombre usuario "
		// + ((Usuarios) atributo).getNombreUsuario();
		// }
		if (atributo instanceof ResourceBundle) {
			valor_texto = " idioma elegido " + ((ResourceBundle) atributo).getString("idioma");
		}
		return valor_texto;
	}
}
