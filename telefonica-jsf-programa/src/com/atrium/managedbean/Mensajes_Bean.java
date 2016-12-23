package com.atrium.managedbean;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;

import com.atrium.util.Acceso_Contextos;

/**
 * Procesos generales de la aplicacion para los mensajes de usuario.
 * 
 * @author Juan Antonio Solves Garcia.
 * @since 13-1-2014
 * @version 1.2
 * 
 */
public class Mensajes_Bean implements Serializable {
	// PROPIEDADES DE CLASE
	private Integer ancho;
	private Integer alto;
	private boolean visible;
	private String mensaje;
	private String icono_mensaje;
	private ResourceBundle rb;

	// ICONOS GENRICOS PARA LA VENTANA DE MENSAJES
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
		rb = ResourceBundle.getBundle((String) Acceso_Contextos.getSesion()
				.getAttribute("idioma_elegido"));
		setMensaje(rb.getString(clave_mensaje));
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
		rb = ResourceBundle.getBundle((String) Acceso_Contextos.getSesion()
				.getAttribute("idioma_elegido"));
		setMensaje(MessageFormat.format(rb.getString(clave_mensaje),
				valores_variable));
	}

	/**
	 * Proceso de carga de iconos segun necesidades de programa, con opciones
	 * por defecto.
	 * 
	 * @param icono_basico
	 */
	public void cargar_Icono(int icono_basico) {
		if (icono_basico == Mensajes_Bean.icono_correcto) {
			setIcono_mensaje("/imagenes/alta.png");
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

}
