package com.testtaxi.jsf.managedbean;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.testtaxi.util.Acceso_Contextos;

/**
 * Procesos generales de la aplicacion para los mensajes de usuario.
 */
public class Mensajes_Bean implements Serializable {
	private static final long serialVersionUID = 2617634226610026747L;
	
	// PROPIEDADES DE CLASE
	private Integer ancho;
	private Integer alto;
	private boolean visible;

	private String cabecera;
	private String mensaje;
	private String icono_mensaje;
		
	private String metodo_aceptar_js;
	private String metodo_cancelar_js;
	
	private String texto_boton_aceptar;
	private String texto_boton_cancelar;	
	
	private String boton_cancelar_visible;
	
	
	private String accion;
	
	private ResourceBundle rb;
	
	
	//SEGUIMIENTO LOG
	private Logger log = Logger.getLogger(TestGenerico_Bean.class);

	// ICONOS GENERICOS PARA LA VENTANA DE MENSAJES
	private static final int icono_correcto = 10;
	private static final int icono_incorrecto = 20;

	/**
	 * Constructor con valores por defecto.
	 */
	public Mensajes_Bean() {
		visible = false;
		ancho = 0;
		alto = 0;
	}

	/**
	 * Proceso de carga de mensaje directo.
	 * 
	 * @param texto
	 *            Texto a mostrar.
	 */
	public void cargar_Mensaje(String texto) {
		setMensaje(texto);
	}

	/**
	 * Proceso de carga de mensaje idiomatizado.
	 * 
	 * @param clave_mensaje
	 *            Clave del properties a mostrar
	 */
	public void cargar_MensajeIdioma(String clave_mensaje) {
		rb = ResourceBundle.getBundle((String) Acceso_Contextos.getSesion().getAttribute("idioma_elegido"));
		setMensaje(rb.getString(clave_mensaje));
	}
	
	
	
	
	/**
	 * Proceso de carga de mensaje idiomatizado.
	 * 
	 * @param clave_mensaje
	 *            Clave del properties a mostrar
	 */
	public void cargar_Mensaje(String cabecera, String mensaje, String icono, String texto_boton_aceptar, String metodo_js_aceptar) {
		cargar_Mensaje(cabecera, mensaje, icono, texto_boton_aceptar, metodo_js_aceptar, false);
	}
	
	
	
	/**
	 * Proceso de carga de mensaje idiomatizado.
	 * 
	 * @param clave_mensaje
	 *            Clave del properties a mostrar
	 */
	public void cargar_Mensaje(String cabecera, String mensaje, String icono, String texto_boton_aceptar, String metodo_js_aceptar, String texto_boton_cancelar, String metodo_js_cancelar) {
		
		setTexto_boton_cancelar(texto_boton_cancelar);
		setMetodo_cancelar_js(metodo_js_cancelar);
		
		
		cargar_Mensaje(cabecera, mensaje, icono, texto_boton_aceptar, metodo_js_aceptar, true);
	}

	
	
	private void cargar_Mensaje(String cabecera, String mensaje, String icono, String texto_boton_aceptar, String metodo_js_aceptar, Boolean boton_cancelar_visible) {
		setCabecera(cabecera);
		cargar_Mensaje(mensaje);
		cargar_Icono(icono);
		
		setTexto_boton_aceptar(texto_boton_aceptar);
		setMetodo_aceptar_js(metodo_js_aceptar);
		
		//DESHABILITO EL SEGUNDO BOTON
		if (boton_cancelar_visible)
		{
			setBoton_cancelar_visible("display:inline");
		}
		else
		{
			setBoton_cancelar_visible("display:none");
		}
		
		setVisible(true);
	}

	
	/**
	 * Proceso de carga de mensaje idiomatizado con variables.
	 * 
	 * @param clave_mensaje
	 *            Clave del properties a mostrar
	 * @param valores_variable
	 *            Valores de las variables para el mensaje.
	 */
	public void cargar_MensajeIdioma(String clave_mensaje,
			String valores_variable[]) {
		rb = ResourceBundle.getBundle((String) Acceso_Contextos.getSesion().getAttribute("idioma_elegido"));
		setMensaje(MessageFormat.format(rb.getString(clave_mensaje),valores_variable));
	}

	/**
	 * Proceso de carga de iconos segun necesidades de programa, con opciones
	 * por defecto.
	 * 
	 * @param icono_basico
	 */
	public void cargar_Icono(int icono_basico) {
		if (icono_basico == Mensajes_Bean.icono_correcto) {
			setIcono_mensaje("resources/images/info/alta.png");
		}
		if (icono_basico == Mensajes_Bean.icono_incorrecto) {
			setIcono_mensaje("/imagenes/baja.png");
		}
	}

	/**
	 * Proceso de carga de iconos concretos.
	 * 
	 * @param icono_expecifico
	 */
	public void cargar_Icono(String icono_expecifico) {
		setIcono_mensaje(icono_expecifico);
	}

	/**
	 * Proceso de cierre de la ventana de mensajes por el boton propio.
	 * 
	 * @param evento
	 */
	public void cerrar_Mensaje(ActionEvent evento) {
		visible = false;
	}

	
	/**
	 * Proceso de cierre de la ventana de mensajes por el boton propio.
	 * 
	 * @param evento
	 */
	public void aceptar(ActionEvent evento) {
		if ((accion!=null)&&(accion.equals("exit")))
		{
			cerrar_sesion(evento);
		}
		else
		{
			cerrar_Mensaje(evento);	
		}
	}
	
	
	public void cerrar_sesion(ActionEvent evento)	{
		//CIERRO EL MENSAJE
		//CERRAR SESION
		accion="";
		cerrar_Mensaje(evento);

		
		//Redirigo a la pagina correcta
		if (log.isTraceEnabled())
			log.trace("-----******* REDIRIJO LA PAGINA AL INDEX ******------");
		
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		try {
			context.redirect(context.getRequestContextPath() + "/xhtml/logout/logout.xhtml");
		} catch (IOException e) {
			log.error("ERROR REDIRIGIENDO LA URL AL INDEX", e);// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		//RESETEO EL ESCRITORIO DE LA APLICACION Y LO OCULTO
		Menu_Bean menu = ((Menu_Bean) Acceso_Contextos.getAtributo("menu_bean"));
		menu.setPagina_elegida("/xhtml/fondo.xhtml");
		menu.setVisible(false);
		
		//OCULTO EL IDIOMA
		Idioma_Bean idioma = ((Idioma_Bean) Acceso_Contextos.getAtributo("idioma_bean"));
		idioma.setVisible(false);
		
		//ACTIVO EL LOGIN
//		Login_Bean login = ((Login_Bean) Acceso_Contextos.getAtributo("login_bean"));
//		login.setLogin_visible(true);
		
		

		
		
		//Caduco la sesion
		HttpSession sesion = Acceso_Contextos.getSesion();
		sesion.setMaxInactiveInterval(1);
		
		if(sesion!=null)
		{
//			Acceso_Contextos.getSesion().invalidate();
			
			
			HttpServletResponse respuesta = Acceso_Contextos.getRespuesta();

//			try 
//			{
//				sesion.setMaxInactiveInterval(10);
//				
//				//respuesta.sendRedirect("../index.jsp");
//			} catch (IOException e) {
//				log.error("-----******* ERROR CERRANDO LA SESION ******------");
//						
//			}
		

//			sesion.invalidate();
		}	
	}
	
	
	/**
	 * Proceso de cierre de la ventana de mensajes por el boton propio.
	 * 
	 * @param evento
	 */
	public void cancelar(ActionEvent evento) {
		cerrar_Mensaje(evento);
	}
	
	
	
	/**
	 * Proceso de cierre por el boton de cierre de la ventana de dialogo.
	 * 
	 * @param evento
	 */
	public void cerrar_Mensaje(AjaxBehaviorEvent evento) {
		visible = false;
	}

	// ACCESORES PARA JSF
	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Integer getAncho() {
		return ancho;
	}

	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}

	public Integer getAlto() {
		return alto;
	}

	public void setAlto(Integer alto) {
		this.alto = alto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getIcono_mensaje() {
		return icono_mensaje;
	}

	public void setIcono_mensaje(String icono_mensaje) {
		this.icono_mensaje = icono_mensaje;
	}

	public String getCabecera() {
		return cabecera;
	}

	public void setCabecera(String cabecera) {
		this.cabecera = cabecera;
	}

	public String getMetodo_aceptar_js() {
		return metodo_aceptar_js;
	}

	public void setMetodo_aceptar_js(String metodo_aceptar_js) {
		this.metodo_aceptar_js = metodo_aceptar_js;
	}

	public String getMetodo_cancelar_js() {
		return metodo_cancelar_js;
	}

	public void setMetodo_cancelar_js(String metodo_cancelar_js) {
		this.metodo_cancelar_js = metodo_cancelar_js;
	}

	public String getTexto_boton_aceptar() {
		return texto_boton_aceptar;
	}

	public void setTexto_boton_aceptar(String texto_boton_aceptar) {
		this.texto_boton_aceptar = texto_boton_aceptar;
	}

	public String getTexto_boton_cancelar() {
		return texto_boton_cancelar;
	}

	public void setTexto_boton_cancelar(String texto_boton_cancelar) {
		this.texto_boton_cancelar = texto_boton_cancelar;
	}

	public String getBoton_cancelar_visible() {
		return boton_cancelar_visible;
	}

	public void setBoton_cancelar_visible(String boton_cancelar_visible) {
		this.boton_cancelar_visible = boton_cancelar_visible;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	
	
	
	
}
