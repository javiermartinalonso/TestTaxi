package com.testtaxi.escuchadores.rastreo;

import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.testtaxi.util.Resta_Fechas;

/**
 * Proceso de seguimiento de los distintos eventos del contexto de aplicacion
 * para su volcado por el sistema de log y su posterior uso.<br/>
 * Actualizado la forma de emision de los mensajes
 */
public class Rastreo_Aplicacion extends AbstractRastreo implements
		ServletContextListener, ServletContextAttributeListener {

	// *************** EVENTOS DE SERVLETCONTEXTLISTENER ****************
	/**
	 * Seguimiento del inicio del programa.<br/>
	 * Se crean los valores iniciales de contador de sesiones y de peticiones,
	 * ademas de marcar la hora de inicio.
	 */
	@Override
	public void contextInitialized(ServletContextEvent evento_creacion) {
		// INICIALIZO CONTADORES PARA EL USO EN LOS OTROS RASTREADORES
		evento_creacion.getServletContext().setAttribute("contador_sesiones",new Integer(0));
		// TOTAL DE PETICIONES DE TODAS LAS SESIONES
		evento_creacion.getServletContext().setAttribute("contador_peticiones",new Integer(0));
		// TOTAL DE LOS ATRIBUTOS DE APLICACION
		evento_creacion.getServletContext().setAttribute("contador_atributos_aplicacion", new Integer(0));
		// TOTAL DE LOS ATRIBUTOS DE TODAS LAS SESIONES
		evento_creacion.getServletContext().setAttribute("contadorgeneral_atributos_sesion", new Integer(0));
		// TOTAL DE ATRIBUTOS DE TODAS LAS PETICIONES
		evento_creacion.getServletContext().setAttribute("contadorgeneral_atributos_peticion", new Integer(0));
		// ANIADO UN DATE PARA SABER LA CREACION DE LA APLICACION Y PODER
		// CALCULAR LA DURACION
		evento_creacion.getServletContext().setAttribute("hora_creacion",new Date());
		registro("----->>>>> CREADO EL CONTEXTO DE APLICACION (SERVLETCONTEXT) INICIO PROGRAMA <<<<<<<-----");
	}

	/**
	 * Seguimiento de la finaliacion del programa. Se indica ademas la duracion
	 * del mismo.<br/>
	 * Tambien se indica el numero de sesiones creadas junto con el numero de
	 * peticiones recibidas.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent evento_destruccion) {
		Date hora_fin = new Date();
		Date hora_creacion = (Date) evento_destruccion.getServletContext().getAttribute("hora_creacion");
		Map<String, String> resultado = Resta_Fechas.getDiferencia(hora_creacion, hora_fin);
		registro("*****  LA APLICACION DURO FUNCIONANDO " + resultado.get("horas") + " HORAS " + resultado.get("minutos")
				+ " MINUTOS " + resultado.get("segundos") + " SEGUNDOS ******");
		int numero_sesiones = ((Integer) (evento_destruccion.getServletContext().getAttribute("contador_sesiones"))).intValue();
		int numero_peticiones = ((Integer) evento_destruccion.getServletContext().getAttribute("contador_peticiones")).intValue();
		int numero_atributos_aplicacion = ((Integer) evento_destruccion.getServletContext().getAttribute("contador_atributos_aplicacion")).intValue();
		int numero_atributos_sesiones = ((Integer) evento_destruccion.getServletContext().getAttribute("contadorgeneral_atributos_sesion")).intValue();
		int numero_atributos_peticiones = ((Integer) evento_destruccion.getServletContext().getAttribute("contadorgeneral_atributos_peticion")).intValue();
		registro("***** estadistica de la aplicacion *****");
		registro("***** Se crearon " + numero_sesiones + " sesiones.");
		registro("***** Se crearon " + numero_peticiones + " peticiones.");
		registro("***** Se usaron " + numero_atributos_aplicacion + " atributos en la aplicacion.");
		registro("***** Se usaron " + numero_atributos_sesiones + " atributos en las sesiones.");
		registro("***** Se usaron " + numero_atributos_peticiones + " atributos en las peticiones.");
		registro("----->>>>> DESTRUIDO EL CONTEXTO DE APLICACION (SERVLETCONTEXT) FIN PROGRAMA <<<<<<<-----");
	}

	// ************** FIN EVENTOS DE SERVLETCONTEXTLISTENER **************

	// ************** EVENTOS DE SERVLETCONTEXTATTRIBUTELISTENER **************
	/**
	 * Proceso de recogida de el atributo que se introduce en el contexto.
	 * Mostramos el tipo y el valor del atributo.
	 */
	@Override
	public void attributeAdded(ServletContextAttributeEvent evento_sesion) {
		// CONTROL DEL NOMBRE DEL ATRIBUTO
		String nombre_atributo = evento_sesion.getName();
//		System.out.println(nombre_atributo);
		if (this.seguir_Proceso(nombre_atributo)) {
			// PROCESO DE CONTROL DE NUMERO DE ATRIBUTOS
			Integer contador_atributos_aplicacion = (Integer) evento_sesion.getServletContext().getAttribute("contador_atributos_aplicacion");
			if (contador_atributos_aplicacion!=null)
			{
				int numero_atributos = (contador_atributos_aplicacion).intValue();
				numero_atributos++;
				evento_sesion.getServletContext().setAttribute("contador_atributos_aplicacion",	new Integer(numero_atributos));
				// PROCESO DE CONTROL DEL VALOR Y TIPO DE ATRIBUTO
				Object valor = evento_sesion.getValue();
				String valor_texto = this.coger_Valor(valor);
				String tipo = null;
				tipo = valor.getClass().getSimpleName();
				registro("*** Aniadido el atributo de aplicacion del tipo " + tipo + " nombre: " + nombre_atributo + " valor: " + valor_texto);
			}
		}
	}

	/**
	 * Proceso de recogida de el atributo que se elimina del contexto. Mostramos
	 * el tipo y el valor del atributo.
	 */
	@Override
	public void attributeRemoved(ServletContextAttributeEvent evento_atributo) {
		// CONTROL DEL NOMBRE DEL ATRIBUTO
		String nombre_atributo = evento_atributo.getName();
		if (this.seguir_Proceso(nombre_atributo)) {
			Object valor = evento_atributo.getValue();
			String valor_texto = this.coger_Valor(valor);
			String tipo = null;
			tipo = valor.getClass().getSimpleName();
			registro("*** Eliminado el atributo de aplicacion del tipo " + tipo	+ " nombre: " + nombre_atributo + " valor: " + valor_texto);
		}
	}

	/**
	 * Proceso de recogida de el atributo que se modifica en el contexto.
	 * Mostramos el tipo y el valor nuevo del atributo.
	 */
	@Override
	public void attributeReplaced(ServletContextAttributeEvent evento_atributo) {
		// CONTROL DEL NOMBRE DEL ATRIBUTO
		String nombre_atributo = evento_atributo.getName();
		if (this.seguir_Proceso(nombre_atributo)) {
			Object valor = evento_atributo.getValue();
			String valor_texto = this.coger_Valor(valor);
			String tipo = null;
			tipo = valor.getClass().getSimpleName();
			registro("*** Modificado el atributo de aplicacion del tipo " + tipo + " nombre: " + nombre_atributo + " valor modificado: " + valor_texto);
		}
	}
	// ************ FIN EVENTOS DE SERVLETCONTEXTATTRIBUTELISTENER ************
}