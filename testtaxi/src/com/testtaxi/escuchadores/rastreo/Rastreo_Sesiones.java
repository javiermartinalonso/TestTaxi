package com.testtaxi.escuchadores.rastreo;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Procesos de traza en la sesiones.<br/>
 * Actualizado la forma de emision de los mensajes.
 * 
 * @author Juan Antonio Solves Garcia.
 * @version 2.0
 * @since 13-2-2013
 * 
 */
public class Rastreo_Sesiones extends AbstractRastreo implements
		HttpSessionListener, HttpSessionAttributeListener,
		HttpSessionActivationListener {
	// ************** EVENTOS DE HTTPSESSIONLISTENER **************
	/**
	 * Cada vez que se crea una sesion se detecta y se muestra el mensaje
	 * mediante el log4j.<br/>
	 * Recogemos del servletcontext el contador adecuado, les sumamos uno y
	 * actualizamos el contador. Guardamos este valor en la sesion tambien para
	 * que sea accesible como identificador valido de sesion.
	 */
	public void sessionCreated(HttpSessionEvent evento_creacion) {
		// PROCESO DE CONTEO DE LAS DISTINTAS SESIONES
		Integer contador = (Integer) evento_creacion.getSession().getServletContext().getAttribute("contador_sesiones");
		Integer nuevo_contador = new Integer(contador.intValue() + 1);
		evento_creacion.getSession().getServletContext().setAttribute("contador_sesiones", nuevo_contador);
		// IDENTIFICADOR DE LA SESION TAMBIEN ESTABLECE EL NUMERO DE SESIONES
		// CREADAS
		evento_creacion.getSession().setAttribute("numero_sesion", nuevo_contador);
		// CONTADOR DE NUMERO DE PETICIONES EN LA SESION
		evento_creacion.getSession().setAttribute("numero_peticiones", new Integer(0));
		// CONTADOR DE LOS ATRIBUTOS DE CADA SESION
		evento_creacion.getSession().setAttribute("contador_atributos_sesion", new Integer(0));
		registro("------>>>>>> CREAMOS LA SESION : IDENTIFICADOR -- "
				+ evento_creacion.getSession().getId() + " NUMERO DE SESION: "
				+ nuevo_contador.intValue());
	}

	/**
	 * Cada que se destruye una sesion se identifica para su rastreo.<br/>
	 * Se indica tambien el numero de peticiones que han sucedio en cada sesion.
	 */
	public void sessionDestroyed(HttpSessionEvent evento_creacion) {
		//COGEMOS EL IDENTIFICADOR PROPIO DE SESION
		Integer contador = (Integer) evento_creacion.getSession().getAttribute("numero_sesion");
		//VOLCAMOS EL NUMERO DE PETICIONES
		registro("--->>> *** NUMERO DE PETICIONES DE LA SESION " + ((Integer) evento_creacion.getSession().
				getAttribute("numero_peticiones")).intValue() + " *** <<<---");
		//VOLCAMOS EL NUMERO DE ATRIBUTOS EN ESTA SESION
		registro("--->>> *** NUMERO DE ATRIBUTOS DE LA SESION "	+ ((Integer) evento_creacion.getSession().
				getAttribute("contador_atributos_sesion")).intValue() + " *** <<<---");
		//TRAZAMOS LA FINALIZACION DE LA SESION
		registro("------>>>>>> DESTRUIMOS LA SESION : IDENTIFICADOR -- "
				+ evento_creacion.getSession().getId() + " NUMERO DE SESION: "
				+ contador.intValue());
	}

	// ************** FIN EVENTOS DE HTTPSESSIONLISTENER **************

	// ************** EVENTOS DE HTTPSESSIONATTRIBUTELISTENER **************
	/**
	 * Al aniadir un atributo a la sesion se registra en la bitacora
	 * correspondiente el nuevo valor indicando la clase y algun dato del objeto
	 * para una mejor identificacion del mismo.
	 */
	public void attributeAdded(HttpSessionBindingEvent evento_sesion) {
		// CONTROL DEL NOMBRE DEL ATRIBUTO
		String nombre_atributo = evento_sesion.getName();
		if (this.seguir_Proceso(nombre_atributo)) {
			// PROCESO DE CONTROL DE NUMERO DE ATRIBUTOS
			Integer contador_atributos = (Integer) evento_sesion.getSession().getAttribute("contador_atributos_sesion");
			//AUMNETAMOS EL CONTADOR DE LA SESION
			evento_sesion.getSession().setAttribute("contador_atributos_sesion", new Integer(contador_atributos.intValue() + 1));
			//AUMENTAMOS EL CONTADOR GENERAL
			evento_sesion.getSession().getServletContext().
				setAttribute("contadorgeneral_atributos_sesion",	new Integer(((Integer) evento_sesion.getSession().
						getServletContext().getAttribute("contadorgeneral_atributos_sesion")).intValue() + 1));
			// RECOGEMOS EL NUMERO DE LA SESION
			Integer contador = (Integer) evento_sesion.getSession().getAttribute("numero_sesion");
			Object valor = evento_sesion.getValue();
			String valor_texto = this.coger_Valor(valor);
			String tipo = null;
			tipo = valor.getClass().getSimpleName();
			registro("*** Aniadido el atributo de sesion del tipo " + tipo
					+ " nombre: " + nombre_atributo + " valor: " + valor_texto
					+ " de la sesion numero: " + contador.intValue());
		}
	}

	/**
	 * Al eliminar un atributo de la sesion se registra junto con los datos del
	 * objeto.<br/>
	 * Cuando la sesion finalize se pierde el acceso a los atributos cuando son
	 * eliminados. Se controla por el try-catch.
	 */
	public void attributeRemoved(HttpSessionBindingEvent evento_sesion) {
		// CONTROL DEL NOMBRE DEL ATRIBUTO
		String nombre_atributo = evento_sesion.getName();
		if (this.seguir_Proceso(nombre_atributo)) {
			// DATOS DEL ATRIBUTO
			Object valor = evento_sesion.getValue();
			String valor_texto = this.coger_Valor(valor);
			String tipo = null;
			tipo = valor.getClass().getSimpleName();
			try {
				Integer contador = (Integer) evento_sesion.getSession().getAttribute("numero_sesion");
				registro("*** Eliminado el atributo de sesion del tipo " + tipo
						+ " nombre: " + nombre_atributo + " valor: "
						+ valor_texto + " de la sesion numero: "
						+ contador.intValue());
			} catch (Exception e) {
				registro("*** Eliminado atributo de sesion invalida del tipo "
						+ tipo + " nombre: " + nombre_atributo + " valor: "
						+ valor_texto);
			}
		}
	}

	/**
	 * Al modificar algun atributo de sesion se registra junto con algun valor
	 * del atributo. Este valor es el reemplazado no en nuevo valor
	 */
	public void attributeReplaced(HttpSessionBindingEvent evento_sesion) {
		// CONTROL DEL NOMBRE DEL ATRIBUTO
		String nombre_atributo = evento_sesion.getName();
		if (this.seguir_Proceso(nombre_atributo)) {
			Integer contador = (Integer) evento_sesion.getSession().getAttribute("numero_sesion");
			Object valor = evento_sesion.getValue();
			String valor_texto = this.coger_Valor(valor);
			String tipo = null;
			tipo = valor.getClass().getSimpleName();
			registro("*** Modificado el atributo de sesion del tipo " + tipo
					+ " nombre: " + nombre_atributo + " valor modificado: "
					+ valor_texto + " de la sesion numero: "
					+ contador.intValue());
		}
	}

	// ************** FIN EVENTOS DE HTTPSESSIONATTRIBUTELISTENER **************

	// ************** EVENTOS DE HTTPSESSIONACTIVATIONLISTENER **************
	/**
	 * Registramos la activacion de la sesion.
	 */
	public void sessionDidActivate(HttpSessionEvent evento_activacion) {
		Integer contador = (Integer) evento_activacion.getSession().getAttribute("numero_sesion");
		registro("------>>>>>> LA SESION HA SIDO ACTIVADA: IDENTIFICADOR -- "
				+ evento_activacion.getSession().getId()
				+ " NUMERO DE SESION: " + contador.intValue());
	}

	/**
	 * Registramos el convertir la sesion a pasiva.
	 */
	public void sessionWillPassivate(HttpSessionEvent evento_activacion) {
		Integer contador = (Integer) evento_activacion.getSession().getAttribute("contador_sesiones");
		registro("------>>>>>> LA SESION VA HA SER DESACTIVADA: IDENTIFICADOR -- "
				+ evento_activacion.getSession().getId()
				+ " NUMERO DE SESION: " + contador.intValue());
	}
	// *********** FIN EVENTOS HTTPSESSIONACTIVATIONLISTENER ***********
}
