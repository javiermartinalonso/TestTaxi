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

public class TestLegislacion_Bean implements Serializable{
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
	private List<SelectItem> lista_respuestas_1 = null;
	private List<SelectItem> lista_respuestas_2 = null;
	//SEGUIMIENTO LOG
	private Logger log = Logger.getLogger(TestLegislacion_Bean.class);
	
	private Preguntas pregunta_1 = null;
	private Preguntas pregunta_2 = null;
	


	/**
	 * Constructor que inicia las propiedades de clase.
	 */
	public TestLegislacion_Bean() {
		if (log.isTraceEnabled())
			log.trace("-----******* CONFIGURAMOS LA PREGUNTA ******------");
				
		cargarDatos();
		
	}

	private void cargarDatos() {
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/com/testtaxi/spring/modelo.xml");
		I_Preguntas gestionPreguntas = contexto.getBean(I_Preguntas.class);
		//TODO acceder con la clase estatica para evitar crear objetos nuevos
//		IGestion_Preguntas gestionPreguntas = Acceso_ApplicationContext.getBean(IGestion_Preguntas.class);
	
				
		Integer id_pregunta = 1;		
		pregunta_1 = gestionPreguntas.consultar_PorClave(1);
		
		pregunta_2 = gestionPreguntas.consultar_PorClave(2);


		

		
		//TODO generar numero de pregunta automaticamente.
		num_pregunta = "J";
		
		texto_pregunta = pregunta_1.getTextoPregunta();
		puntuacion = pregunta_1.getPuntuacion();
		indicaciones_pregunta = pregunta_1.getIndicacionesPregunta();
		String foto = pregunta_1.getFoto();
		
		if (foto==null)
		{
			display = "none";
		}
		else
		{
			display = "inline";
			imagen= FILE_PATH.concat(foto);
		}
				
		
		
		
		
		lista_respuestas_1 = new ArrayList<SelectItem>();
				
		lista_respuestas_1.add(new SelectItem(1, pregunta_1.getRespuesta1()));
		lista_respuestas_1.add(new SelectItem(2, pregunta_1.getRespuesta2()));
		lista_respuestas_1.add(new SelectItem(3, pregunta_1.getRespuesta3()));
		lista_respuestas_1.add(new SelectItem(4, pregunta_1.getRespuesta4()));
		
		
		
		lista_respuestas_2 = new ArrayList<SelectItem>();
		
		lista_respuestas_2.add(new SelectItem(1, pregunta_2.getRespuesta1()));
		lista_respuestas_2.add(new SelectItem(2, pregunta_2.getRespuesta2()));
		lista_respuestas_2.add(new SelectItem(3, pregunta_2.getRespuesta3()));
		lista_respuestas_2.add(new SelectItem(4, pregunta_2.getRespuesta4()));
		
		
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

	public Preguntas getPregunta_1() {
		return pregunta_1;
	}

	public void setPregunta_1(Preguntas pregunta_1) {
		this.pregunta_1 = pregunta_1;
	}

	public Preguntas getPregunta_2() {
		return pregunta_2;
	}

	public void setPregunta_2(Preguntas pregunta_2) {
		this.pregunta_2 = pregunta_2;
	}

	public List<SelectItem> getLista_respuestas_1() {
		return lista_respuestas_1;
	}

	public void setLista_respuestas_1(List<SelectItem> lista_respuestas_1) {
		this.lista_respuestas_1 = lista_respuestas_1;
	}

	public List<SelectItem> getLista_respuestas_2() {
		return lista_respuestas_2;
	}

	public void setLista_respuestas_2(List<SelectItem> lista_respuestas_2) {
		this.lista_respuestas_2 = lista_respuestas_2;
	}
	
	
	
	
	
}
