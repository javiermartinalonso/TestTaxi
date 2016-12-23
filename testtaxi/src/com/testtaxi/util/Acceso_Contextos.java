package com.testtaxi.util;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

//import org.springframework.context.ApplicationContext;
//import org.springframework.web.context.support.WebApplicationContextUtils;




/**
 * Utilidad para el acceso a los distintos contextos necesarios dentro de la
 * aplicacion. Aprovecha el echo de que los accesos a los mismo son todos
 * objetos estaticos y los expone de la misma forma estatica.
 */
public class Acceso_Contextos {

	//SEGUIMIENTO LOG
	private static Logger log = Logger.getLogger(Acceso_Contextos.class);
	
	/**
	 * Proceso de conveniencia para recoger el valor de un atributo de sesion.
	 * 
	 * @param nombre_atributo
	 * @return
	 */
	public static Object getAtributo(String nombre_atributo) {
		return getSesion().getAttribute(nombre_atributo);
	}

	/**
	 * Proceso de conveniencia para dar valor a un atributo de sesion.
	 * 
	 * @param nombre_atributo
	 * @return
	 */
	public static void setAtributo(String nombre_atributo, Object objeto_valor) {
		getSesion().setAttribute(nombre_atributo, objeto_valor);
	}

	/**
	 * Metodo de conveniencia para la inclusion de mensajes en los managedbean
	 * sin tener que llamar todo el rato al facescontext.
	 * 
	 * @param mensaje
	 * @param identificador
	 */
	public static void addMensaje(String mensaje_sumario,
			String mensaje_detalle, FacesMessage.Severity nivel,
			String identificador) {
		if (nivel == null) {
			nivel = FacesMessage.SEVERITY_INFO;
		}
		FacesContext.getCurrentInstance().addMessage(identificador, new FacesMessage(nivel, mensaje_sumario, mensaje_detalle));
	}

	/**
	 * Metodo de conveniencia para la inclusion de mensajes en los managedbean
	 * sin tener que llamar todo el rato al facescontext.<br/>
	 * En esta metodo no se envian los textos, solo las claves para los
	 * properties. Por lo tanto es necesario crear el ResourceBundle que lea del
	 * properties correspondiente.
	 * 
	 * @param mensaje
	 * @param identificador
	 */
	public static void addMensaje_Idioma(String clave_sumario,
			String clave_detalle, FacesMessage.Severity nivel,
			String identificador) {
		try {
			// CREO EL RB PARA LEER DEL PROPERTIES ADECUADO
			ResourceBundle rb = ResourceBundle.getBundle((String) getSesion().getAttribute("idioma_elegido"));
			// COMPRUEBO QUE NO VENGAN VACIOS O NULOS
			String sumario = "";
			String detalle = "";
			if (!clave_detalle.equals("") && clave_detalle != null) {
				detalle = rb.getString(clave_detalle);
			}
			if (!clave_sumario.equals("") && clave_sumario != null) {
				sumario = rb.getString(clave_sumario);
			}
			if (nivel == null) {
				nivel = FacesMessage.SEVERITY_INFO;
			}
			FacesContext.getCurrentInstance().addMessage(identificador,	new FacesMessage(nivel, sumario, detalle));
		} catch (Exception e) {
			log.error("NO EXISTE PROPERTIES DEFINIDO. REVISAR ATRIBUTO DE SESION IDIOMA_ELEGIDO",e);
		}
	}

	/**
	 * Devuelve la sesion si existe. De no existir no devuelve nada, no crea una
	 * nueva
	 * 
	 * @return Sesion en curso. (HTTPSESSION)
	 */
	public static HttpSession getSesion() {
		HttpSession salida = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		return salida;
	}

	/**
	 * Devuelve la peticion de en curso.
	 * 
	 * @return Peticion en curso. (HTTPSERVLETREQUEST)
	 */
	public static HttpServletRequest getPeticion() {
		HttpServletRequest peticion = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		return peticion;
	}

	/**
	 * Devuelve el contexto de aplicacion correspondiente.
	 * 
	 * @return Contexto de aplicion JEE. (SERVLETCONTEXT).
	 */
	public static ServletContext getAplicacion() {
		ServletContext aplicacion = ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getServletContext();
		return aplicacion;
	}

	/**
	 * Devuelve la respuesta de en curso.
	 * 
	 * @return Respuesta en curso. (HTTPSERVLETRESPONSE)
	 */
	public static HttpServletResponse getRespuesta() {
		HttpServletResponse respuesta = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		return respuesta;
	}


	/**
	 * Devuelve el applicationContext de spring para su uso.
	 * 
	 * @return ApplicationContext de spring. (APPLICATIONCONTEXT)
	 */
//	public static ApplicationContext getApplication_Context() {
//		ApplicationContext application = WebApplicationContextUtils.getWebApplicationContext(Acceso_Contextos.getAplicacion());
//		return application;
//	}

}
