package com.testtaxi.pruebas;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.testtaxi.hibernate.Preguntas;
import com.testtaxi.hibernate.modelo.I_Preguntas;

public class Pruebas {

	public static void main(String[] args) {
		
		
		
		ApplicationContext contexto = new ClassPathXmlApplicationContext("/com/testtaxi/spring/modelo.xml");
		I_Preguntas gestionPreguntas = contexto.getBean(I_Preguntas.class);
//		IGestion_Preguntas gestionPreguntas = Acceso_ApplicationContext.getBean(IGestion_Preguntas.class);
	
		Preguntas pregunta = gestionPreguntas.consultar_PorClave(1);
		
		System.out.println(pregunta.getId());
		System.out.println(pregunta.getIndicacionesPregunta());
		System.out.println(pregunta.getPuntuacion());
		System.out.println(pregunta.getTextoPregunta());
		
		System.out.println(pregunta.getRespuesta1());
		System.out.println(pregunta.getRespuesta2());
		System.out.println(pregunta.getRespuesta3());
		System.out.println(pregunta.getRespuesta4());
		
//		Preguntas preguntaConRespuestas = gestionPreguntas.consultar_PreguntaConRespuestas(1);
//		Set lista_respuestases = preguntaConRespuestas.getRespuestases();
//		List<Respuestas> lista = (List<Respuestas>) preguntaConRespuestas.getRespuestases();
		// ORDENAMOS LA COLECCION POR LA TRADUCCION DEL TEXTO
//		Collections.sort(lista_respuestases, this);
//		System.out.println(pregunta.getRespuestases());
	}

}
