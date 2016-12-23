package com.testtaxi.jsf.managedbean.sinuso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.testtaxi.hibernate.Preguntas;
import com.testtaxi.hibernate.modelo.I_Preguntas;

public class Pregunta_Test_Bean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	
	// PROPIEDADES DE LA PREGUNTA
	private String num_pregunta;   //X representa el numero de la pregunta dentro del text 
//	y sirve para identificar el radiobuton correspondiente en el html
	private String texto_pregunta; //Desarrollo de la cuestion a contestar numero X
	private String puntuacion;     //Puntos: 1
	private String indicaciones_pregunta;  //Seleccione una respuesta
	private String display;  //none para cuando no queramos verla
	private Integer respuesta_seleccionada = null;
	private String imagen=null; //Imagen
	//RUTA DE LAS IMAGENES EN EL SERVIDOR
	private static final String FILE_PATH = "/resources/images/test/";
	//LISTA DE RESPUESTAS
	private List<SelectItem> lista_respuestas = null;
	//SEGUIMIENTO LOG
	private static Logger log = Logger.getLogger(Pregunta_Test_Bean.class);
	


	/**
	 * Constructor que inicia las propiedades de clase.
	 */
	public Pregunta_Test_Bean() {
		if (log.isTraceEnabled())
			log.trace("-----******* CONFIGURAMOS LA PREGUNTA ******------");
				
		cargarDatos();
		
	}

	private void cargarDatos() {
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/com/testtaxi/spring/modelo.xml");
		I_Preguntas gestionPreguntas = contexto.getBean(I_Preguntas.class);
		//TODO acceder con la clase estatica para evitar crear objetos nuevos
//		IGestion_Preguntas gestionPreguntas = Acceso_ApplicationContext.getBean(IGestion_Preguntas.class);
	
				
		Integer id_pregunta = 3;		
		Preguntas pregunta = gestionPreguntas.consultar_PorClave(id_pregunta);
		
		//TODO generar numero de pregunta automaticamente.
		num_pregunta = "J";
		
		texto_pregunta = pregunta.getTextoPregunta();
		puntuacion = pregunta.getPuntuacion();
		indicaciones_pregunta = pregunta.getIndicacionesPregunta();
		String foto = pregunta.getFoto();
		
		if (foto==null)
		{
			display = "none";
		}
		else
		{
			display = "inline";
			imagen= FILE_PATH.concat(foto);
		}
				
		
		
		
		
		lista_respuestas = new ArrayList<SelectItem>();
				
		lista_respuestas.add(new SelectItem(1, pregunta.getRespuesta1()));
		lista_respuestas.add(new SelectItem(2, pregunta.getRespuesta2()));
		lista_respuestas.add(new SelectItem(3, pregunta.getRespuesta3()));
		lista_respuestas.add(new SelectItem(4, pregunta.getRespuesta4()));
		
		
//		Set lista_respuestases = ((PreguntasRespuestas) pregunta.getPreguntasRespuestases()).getRespuestas();
//		
//		lista_respuestas = new ArrayList<SelectItem>();
//		
//		Iterator iterator = lista_respuestases.iterator();
//		while(iterator.hasNext()) {
//			Respuestas respuesta = (Respuestas) iterator.next();
//			
//			//			 new SelectItem(value, label, description, disabled, escape)
//			//TODO aniadir id a la clase padre que lo tenga
//			lista_respuestas.add(new SelectItem(respuesta.getId(), respuesta.getRespuesta()));
//		}
	}

	// ACCESORES PARA JSF
	public String getNum_pregunta() {
		return num_pregunta;
	}

	public void setNum_pregunta(String num_pregunta) {
		this.num_pregunta = num_pregunta;
	}

	public String getTexto_pregunta() {
		return texto_pregunta;
	}

	public void setTexto_pregunta(String texto_pregunta) {
		this.texto_pregunta = texto_pregunta;
	}

	public String getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(String puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getIndicaciones_pregunta() {
		return indicaciones_pregunta;
	}

	public void setIndicaciones_pregunta(String indicaciones_pregunta) {
		this.indicaciones_pregunta = indicaciones_pregunta;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	public Integer getRespuesta_seleccionada() {
		return respuesta_seleccionada;
	}

	public void setRespuesta_seleccionada(Integer respuesta_seleccionada) {
		this.respuesta_seleccionada = respuesta_seleccionada;
	}

	public List<SelectItem> getLista_respuestas() {
		return lista_respuestas;
	}

	public void setLista_respuestas(List<SelectItem> lista_respuestas) {
		this.lista_respuestas = lista_respuestas;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
}
