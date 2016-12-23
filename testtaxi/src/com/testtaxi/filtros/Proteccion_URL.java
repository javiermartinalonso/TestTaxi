package com.testtaxi.filtros;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.testtaxi.hibernate.Usuarios;

/**
 * Proceso general y configurable para establecer un filtro de seguridad en los
 * accesos a las aplicaciones web.
 */
public class Proteccion_URL implements Filter {

	// PROPIEDADES PARA REALIZAR EL CONTROL DE ACCESOS
	private int contador = 0;
	private Properties lista_seguridad = new Properties();
	private Logger log = Logger.getLogger(Proteccion_URL.class);

	/**
	 * Cargamos del web.xml un parametro de configuracion que nos indica la ruta
	 * y el nombre del fichero y a continuacion lo leemos sobre un properties
	 * para su uso posterior en el proceso de seguridad.
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// SE RECOGE DEL WEB.XML LOS VALORES NECESARIOS PARA INDICAR DONDE ESTA
		// Y COMO SE LLAMA EL FICHERO DONDE DEFINIMOS NUESTRA ESTRUCTURA DE
		// SEGURIDAD.
		String ruta_seguridad = filterConfig.getServletContext().getRealPath(filterConfig.getInitParameter("ruta_config") + ".properties");
		// A PARTIR DE AQUI SE CARGA EN UN PROPERTIES PARA EL USO POR EL FILTRO.
		FileReader fichero_seguridad = null;
		try {
			fichero_seguridad = new FileReader(ruta_seguridad);
		} catch (FileNotFoundException e) {
			log.error("ERROR: al obtener la informacion de permisos de acceso", e);
		}
		try {
			lista_seguridad.load(fichero_seguridad);
		} catch (IOException e) {
			log.error("ERROR: al cargar la informacion de permisos de acceso", e);
		}
		if (log.isTraceEnabled()) {
			log.trace("---- ****** EMPIEZO EL INTERCEPTOR DE SEGURIDAD ******----");
		}
	}

	/**
	 * Proceso mediante el cual examinamos todas las peticiones que llegan al
	 * servidor. Esta basado en la logica de coger de la URL la parte que nos
	 * dice que esta pidiendo el cliente.<br/>
	 * Este texto se buscara en un fichero properties y sino se encuentra es un
	 * recurso protegido y por lo tanto se aplica la logica de proteccion. Si se
	 * encuentra el texto se mira si la clave tiene asociado un no o un si y en
	 * funcion de este valor aplicamos la proteccion o no. <br/>
	 * La logica de proteccion consiste en mirar si existe un objeto usuario en
	 * la sesion o no. Si existe, es una peticion de una sesion correcta, en
	 * caso contrario es una ataque y por lo tanto lo redirgimos a una pagina
	 * especial.
	 */

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain cadena_peticion) throws IOException, ServletException {
		// CAMBIAMOS EL TIPO A LA PETICION
		HttpServletRequest peticion = null;
		if (request instanceof HttpServletRequest) {
			peticion = (HttpServletRequest) request;
		}
		// RECOGEMOS LA URL DE LA PETICION A PARTIR DE LA APLICACION
		String url_peticion = peticion.getServletPath();
		// BUSCAMOS SI HAY COMPONENTE EN LA URL
		int segunda_barra = url_peticion.indexOf("/", 1);
		String recurso_peticion = null;
		// COGEMOS ESA PARTE FINAL DE LA URL PARA PODER SABER QUE SE PIDE EN
		// CONCRETO
		if (segunda_barra == -1) {
			recurso_peticion = url_peticion;
		} else {
			recurso_peticion = url_peticion.substring(url_peticion.lastIndexOf("/"), url_peticion.length());
//			inicio_url_peticion = url_peticion.substring(0, url_peticion.indexOf("/", 1));
		}
		// BUSCAMOS CON LA PARTE FINAL DE LA URL COMO CLAVE EN EL PROPERTIES
		// SI NO EXISTE ES UNA PETICION A ASEGURAR, SI EXISTE ES UNA PETICION
		// QUE NO SE TIENE QUE ASEGURAR (LOGICA INVERSA)
		String proceso_a_realizar = lista_seguridad.getProperty(recurso_peticion);
		if (log.isTraceEnabled()) {
			log.trace("---- ****** PETICION RECIBIDA " + recurso_peticion
					+ " " + url_peticion + " ******----");
			contador++;
			log.trace("---- ****** Numero de acceso -- " + contador
					+ " ******----");
			log.trace("---- ****** PETICION 1er NIVEL RECIBIDA ******----"+ recurso_peticion);
		}
		if (proceso_a_realizar == null) {
			proceso_a_realizar = "si";
		}
		// SI EXISTE O EL VALOR ES SI SE EJECUTA LA LOGICA DE SEGURIDAD
		if (proceso_a_realizar.equals("si")) {
			if (peticion != null) {
				// LA LOGICA DE SEGURIDAD ES QUE EXISTA EL OBJETO USARIO EN LA
				// SESION. SIEMPRE EMPEZAMOS POR EL LOGIN
				Usuarios usuario = (Usuarios) peticion.getSession().getAttribute("usuario");
				// CASO DE NO EXISTIR EN LA SESION
				if (usuario == null) {
					// PETICION RECHAZADA. LA DESVIAMOS A UNA PAGINA DE SALIDA
					if (log.isTraceEnabled()) {
						log.trace("---- ****** PETICION 1er NIVEL SEGURA RECHAZADA ******----");
					}
//					RequestDispatcher redirigir = peticion.getSession().getServletContext().getRequestDispatcher("/jsp/paginas_seguridad/intruso.jsp");
					RequestDispatcher redirigir = peticion.getSession().getServletContext().getRequestDispatcher("/xhtml/home.xhtml");
					redirigir.forward(request, response);
				} else {
					if (log.isTraceEnabled()) {
						log.trace("---- ****** PETICION 1er NIVEL SEGURA ACEPTADA ******----");
					}
					// PETICION ACEPTADA. SEGUIMOS EL CURSO DE LA MISMA.
					cadena_peticion.doFilter(request, response);
				}
			}
		} else {
			if (log.isTraceEnabled()) {
				log.trace("---- ****** PETICION 1er NIVEL NO ASEGURADA ******----");
			}
			// PETICION QUE NO HAY QUE ASEGURAR. SIGUE SU CURSO NORMAL.
			cadena_peticion.doFilter(request, response);
		}
	}

	/**
	 * Fin de filtro. Solo se registra en la bitacora.
	 */
	public void destroy() {
		if (log.isTraceEnabled()) {
			log.trace("---- ****** TERMINO EL INTERCEPTOR DE SEGURIDAD ******----");
		}
	}
}
