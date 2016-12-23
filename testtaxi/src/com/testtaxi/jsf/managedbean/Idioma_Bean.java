package com.testtaxi.jsf.managedbean;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import org.apache.log4j.Logger;

import com.testtaxi.util.Acceso_Contextos;

/**
 * Procesos de control del idioma en el cual se va a mostrar la aplicaicion
 */
public class Idioma_Bean implements Serializable {
	private static final long serialVersionUID = 1L;
	// PROPIEDADES PARA EL PROCESO
	private String idioma_elegido;
	private String boton_idioma;
	//PROPIEDAD PARA TRATAR EL TEMA SELECCIONADO
	private String tema;
	// CONTROL DE VISIBILIDAD DEL PANEL DE LOGIN
	private boolean visible;


	//SEGUIMIENTO LOG
	private Logger log = Logger.getLogger(Idioma_Bean.class);

	/**
	 * Cargamos en el constructor el idioma por defecto establecido por el
	 * filtro inicial.
	 */
	public Idioma_Bean() {
		if (log.isTraceEnabled())
			log.trace("-----******* ACCEDEMOS A LA CONFIGURACION DEL IDIOMA/TEMA *******------");
			
//		visible = true;
		visible = false;
		
		idioma_elegido = (String) Acceso_Contextos.getSesion().getAttribute("idioma_elegido");
		//TODO harcodeado para que siempre muestre castellano
		idioma_elegido = "com/testtaxi/idiomas/textos_es";
	}

	/**
	 * Proceso de cambio dinamico de idioma por parte del usuario a traves de
	 * los hipervinculos del banner
	 * 
	 * @param evento
	 * Componente que lanza el evento.
	 */
	public void cambiar_Idioma(ActionEvent evento) {
		if (log.isTraceEnabled())
			log.trace("-----******* CAMBIO DE IDIOMA *******------");
		
		// RECOJEMOS EL VALOR SELECCIONADO POR EL USUARIO
		String bandera = evento.getComponent().getId();
		// MODIFICAMOS EL ATRIBUTO DE SESION QUE INDICA EL VALOR DEL IDIOMA
		idioma_elegido = idioma_elegido.substring(0, idioma_elegido.length() - 2)
				+ bandera.substring(bandera.length() - 2);
		// MODIFICAMOS EL ATRIBUTO EN LA SESION
		Acceso_Contextos.getSesion().setAttribute(idioma_elegido, "idioma_elegido");
	}

	// ACESORES PARA SPRING
	public String getIdioma_elegido() {
		return idioma_elegido;
	}

	public void setIdioma_elegido(String idioma_elegido) {
		this.idioma_elegido = idioma_elegido;
	}

	public String getBoton_idioma() {
		return boton_idioma;
	}

	public void setBoton_idioma(String boton_idioma) {
		this.boton_idioma = boton_idioma;
	}

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		if (log.isTraceEnabled())
			log.trace("-----******* CAMBIO DE TEMA *******------");
		this.tema = tema;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
}
