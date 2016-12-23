package com.testtaxi.dto;

import java.io.Serializable;
import java.util.Comparator;

import com.testtaxi.hibernate.Preguntas;

public class Respuesta extends Preguntas implements Serializable, Comparator<Respuesta>{
	
	private static final long serialVersionUID = 7515423423806172569L;
	private String estilo_respuesta_1 = "fila_1";
	private String estilo_respuesta_2 = "fila_2";
	private String estilo_respuesta_3 = "fila_3";
	private String estilo_respuesta_4 = "fila_4";
	
	private String display;
	
	private String imagen_respuesta =null;
	private String resultado_pregunta = null;
	
	
	public Respuesta(Preguntas pregunta)
	{
		setId(pregunta.getId());
		setTextoPregunta(pregunta.getTextoPregunta());
		setPuntuacion(pregunta.getPuntuacion());
		setIndicacionesPregunta(pregunta.getIndicacionesPregunta());
		setFoto(pregunta.getFoto());
		setDificultad(pregunta.getDificultad());
		setIdRespuestaCorrecta(pregunta.getIdRespuestaCorrecta());
		setNumRespuestas(pregunta.getNumRespuestas());
		setRespuesta1(pregunta.getRespuesta1());
		setRespuesta2(pregunta.getRespuesta2());
		setRespuesta3(pregunta.getRespuesta3());
		setRespuesta4(pregunta.getRespuesta4());
		setRespuesta5(pregunta.getRespuesta5());
		setRespuesta6(pregunta.getRespuesta6());
		setTestses(pregunta.getTestses());
		
		if(this.getComentario()==null)
		{
			setComentario("");
		}
		
		if(this.getFoto()==null)
		{
			setFoto("");
		}
		
		if(this.getRespuesta5()==null)
		{
			setRespuesta5("");
		}	
		
		if(this.getRespuesta6()==null)
		{
			setRespuesta5("");
		}			
	}
	
	public String getEstilo_respuesta_1() {
		return estilo_respuesta_1;
	}
	public void setEstilo_respuesta_1(String estilo_respuesta_1) {
		this.estilo_respuesta_1 = estilo_respuesta_1;
	}
	public String getEstilo_respuesta_2() {
		return estilo_respuesta_2;
	}
	public void setEstilo_respuesta_2(String estilo_respuesta_2) {
		this.estilo_respuesta_2 = estilo_respuesta_2;
	}
	public String getEstilo_respuesta_3() {
		return estilo_respuesta_3;
	}
	public void setEstilo_respuesta_3(String estilo_respuesta_3) {
		this.estilo_respuesta_3 = estilo_respuesta_3;
	}
	public String getEstilo_respuesta_4() {
		return estilo_respuesta_4;
	}
	public void setEstilo_respuesta_4(String estilo_respuesta_4) {
		this.estilo_respuesta_4 = estilo_respuesta_4;
	}
		
	public String getImagen_respuesta() {
		return imagen_respuesta;
	}

	public void setImagen_respuesta(String imagen_respuesta) {
		this.imagen_respuesta = imagen_respuesta;
	}

	public String getResultado_pregunta() {
		return resultado_pregunta;
	}

	public void setResultado_pregunta(String resultado_pregunta) {
		this.resultado_pregunta = resultado_pregunta;
	}

	public String getDisplay() {
		return display;
	}

	public void setDisplay(String display) {
		this.display = display;
	}

	/**
	 * Ordenacion de las respuestas.
	 */
	public int compare(Respuesta respuesta1, Respuesta respuesta2) {
		int comparacion = 0;
		comparacion = respuesta1.getId().compareTo(respuesta2.getId());
		return comparacion;
	}
}
