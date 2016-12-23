package com.testtaxi.escuchadores;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.testtaxi.util.Mantenimiento_Rutas;

/**
 * Proceso de carga de variables de contexto para la configuracion del programa.
 * Se leeran del WEB.XML.
 */
public class Eventos_ServletContext implements ServletContextListener {
	private Logger log = Logger.getLogger(Eventos_ServletContext.class);

	/**
	 * Cargamos las variables de contexto de la aplicacion y establecemos
	 * valores para los procesos. <br/>
	 * Inicialmente se cargan las siguientes variables.
	 * <ul>
	 * <li>ruta .Carpeta de los ficheros logs de la aplicacion</li>
	 * <li>ruta_depu. Carpeta para los ficheros logs del proceso propio de
	 * depuracion.</li>
	 * <li>ruta_logs. Carpeta donde se almacena el log4j.properties.</li>
	 * </ul>
	 */
	public void contextInitialized(ServletContextEvent evento_creacion) {
		// accedemos al parametro de inicio para establecer la ruta inicial de los ficheros logs
		String ruta = evento_creacion.getServletContext().getInitParameter("ruta_logs");
		ruta = evento_creacion.getServletContext().getRealPath(ruta);
		// comprobamos la existencia de la carpeta y sino existe se crea
		Mantenimiento_Rutas.comprobar_Carpeta(ruta);
		// REALIZAMOS LA MISMA TAREA PARA EL FICHERO DE REGISTRO EN DEPURACION
		String ruta_depu = evento_creacion.getServletContext().getInitParameter("ruta_seguimiento");
		ruta_depu = evento_creacion.getServletContext().getRealPath(ruta_depu);
		Mantenimiento_Rutas.comprobar_Carpeta(ruta_depu);
		
		// REALIZAMOS LA MISMA TAREA PARA EL FICHERO DE REGISTRO EN DEPURACION
		String ruta_segu = evento_creacion.getServletContext().getInitParameter("ruta_seguridad");
		ruta_segu = evento_creacion.getServletContext().getRealPath(ruta_segu);
		Mantenimiento_Rutas.comprobar_Carpeta(ruta_segu);
				
		// ruta para el escribir el fichero de registro (bitacora). esta
		// variable es para el log4j y esta en su fichero de configuracion
		System.setProperty("ruta_logs", ruta);
		System.setProperty("ruta_seguimiento", ruta_depu);
		System.setProperty("ruta_seguridad", ruta_segu);
		// ruta para que el log4j encuentre su fichero de configuracion
		String ruta_logs = evento_creacion.getServletContext().getRealPath("/WEB-INF/classes/com/testtaxi/util/log4j.properties");
																							
		PropertyConfigurator.configure(ruta_logs);
		if (log.isTraceEnabled()) {
			log.trace("----**********  ESTABLECIDO LOS PARAMETROS INICIALES DE LA APLICACION  ******------");
		}
	}

	/**
	 * Proceso vacio.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

}
