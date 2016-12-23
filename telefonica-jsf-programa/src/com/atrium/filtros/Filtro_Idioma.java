package com.atrium.filtros;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * Proceso de establecimiento de idioma por defecto en la primera peticion de la
 * aplicacion.<BR/>
 * Se amplia su funcionalidad para detectar el tipo de navegador usado por el
 * cliente y establecer un atributo de sesion para su uso en la apliacion.<BR/>
 * Se añade la deteccion de IE 7.
 * 
 * @author Juan Antonio Solves Garcia.
 * @version 2.1
 * @since 14-11-2013
 */
public class Filtro_Idioma implements Filter {

	// PARAMETROS DE CLASE PARA SU FUNCIONAMIENTO
	private boolean no_elegido = true;
	private String idioma_defecto;
	private Logger log = Logger.getLogger(Filtro_Idioma.class);

	/**
	 * Establecemos el idioma por defecto. Se lee este valor del fichero de
	 * configuracion web.xml y se establece como opcion por defecto.
	 * 
	 * @param filterConfig
	 *            Objeto que nos permitira acceder a los contexto y su
	 *            informacion para cualquier necesidad de proceso.
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		idioma_defecto = filterConfig.getServletContext().getInitParameter(
				"idioma_por_defecto");
		if (log.isTraceEnabled()) {
			log.trace("--->>>>>>>>> CREACION DEL FILTRO DE IDIOMA INICIAL  <<<<<<<<<<<-----");
		}
	}

	/**
	 * Cargamos el idioma por defecto en base a la eleccion de idioma del
	 * navegador del cliente que hace la peticion.<BR/>
	 * En caso de que no se tenga ese idioma se carga el idioma por defecto
	 * establecido en el metodo init.<br/>
	 * Adicionalmente establecemos el navegador del usuario para su posterior
	 * uso, contemplando solo dos navegadores Internet Explorer y Chrome.
	 * 
	 * @param peticion
	 *            Objeto que representa la peticion. En nuestro caso es un
	 *            objeto HTTPSERVLETREQUEST.
	 * @param respuesta
	 *            Objeto que representa la peticion. En nuestro caso es un
	 *            objeto HTTPSERVLETRESPONSE.
	 * @param cadena_peticion
	 *            Objeto que nos permitira seguir la secuencia de la peticion
	 *            una vez terminado el proceso.
	 */
	public void doFilter(ServletRequest peticion, ServletResponse respuesta,
			FilterChain cadena_peticion) throws IOException, ServletException {
		// ESTABLECEMOS EL IDIOMA POR DEFECTO
		String idioma_elegido_peticion = idioma_defecto;
		HttpServletRequest mi_request = null;
		if (peticion instanceof HttpServletRequest) {
			mi_request = (HttpServletRequest) peticion;
		}
		// RECOGEMOS EL PARAMETRO QUE NOS DICE EL IDIOMA PREFERIDO EN EL
		// NAVEGADOR DEL USUARIO
		String idioma_preferido = mi_request.getHeader("accept-language");
		// DIVIDIMOS LA CADENA EN PARTES PARA EL CASO DE TENER MAS DE UN IDIOMA
		// ELEGIDO
		String lista_idiomas[] = idioma_preferido.split(",");
		// BUSCAMOS DENTRO DE LOS IDIOMAS ACCESIBLES EL QUE CORRESPONDA A LA
		// PREFERANCIA DEL USUARIO
		for (int i = 0; i < lista_idiomas.length; i++) {
			if (lista_idiomas[i].substring(0, 2).equals("es") && no_elegido) {
				idioma_elegido_peticion = "com/atrium/idiomas/textos_es";
				no_elegido = false;
			}
			if (lista_idiomas[i].substring(0, 2).equals("en") && no_elegido) {
				idioma_elegido_peticion = "com/atrium/idiomas/textos_en";
				no_elegido = false;
			}
			if (lista_idiomas[i].substring(0, 2).equals("fr") && no_elegido) {
				idioma_elegido_peticion = "com/atrium/idiomas/textos_fr";
				no_elegido = false;
			}
		}
		mi_request.getSession().setAttribute("idioma_elegido",
				idioma_elegido_peticion);
		no_elegido = true;
		if (log.isTraceEnabled()) {
			log.trace("-----******* TRATAMOS UNA PETICION DE IDIOMA INICIAL "
					+ idioma_elegido_peticion + " ******------");
		}
		// CONTINUA LA PETICION COMO CORRESPONDA
		cadena_peticion.doFilter(peticion, respuesta);
	}

	/**
	 * Solo informamos de la finalizacion del filtro para el seguimiento de la
	 * aplicacion.
	 */
	public void destroy() {
		if (log.isTraceEnabled()) {
			log.trace("-->>>>>>>> DESTRUCCION DEL FILTRO DE IDIOMA INICIAL <<<<<<<<<<------");
		}
	}
}
