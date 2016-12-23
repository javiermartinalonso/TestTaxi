package com.testtaxi.hibernate.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.testtaxi.hibernate.Preguntas;
import com.testtaxi.hibernate.Tests;
import com.testtaxi.hibernate.dao.PreguntasDAO;
import com.testtaxi.hibernate.dao.ext.PreguntasDAOEXT;

public class F_Preguntas implements I_Preguntas,Serializable {

	private static final long serialVersionUID = 1L;
	private PreguntasDAOEXT pregunta_dao;

	// *********** CONSULTAS DE PREGUNTAS
	@Override
	public Preguntas consultar_PorClave(Integer id) {
		return pregunta_dao.findById(id);
	}
	
	@Override
	public List<Preguntas> consultar_PorTipo(String tipo){
		return (ArrayList<Preguntas>) pregunta_dao.findByTipoPregunta(tipo);
	}
		
	@Override
	public List<Preguntas> consultar_todas()
	{
		return (List<Preguntas>) pregunta_dao.findAll();
	}
	
	
	@Override
	public List<Preguntas> consultar_ListaPreguntasAleatoriaPorTipo(int numReg, String tipoPregunta)
	{
		return (List<Preguntas>) pregunta_dao.getListaPreguntasAleatoria(numReg, tipoPregunta);
	}
	
	// **** PROCESOS CRUD DE PREGUNTAS
	@Override
	public void alta_Pregunta(Preguntas pregunta_transito) {
		pregunta_dao.save(pregunta_transito);
	}

	@Override
	public void baja_Pregunta(Preguntas pregunta_transito) {
		pregunta_dao.delete(pregunta_transito);
	}

	@Override
	public void modificacion_Pregunta(Preguntas pregunta_transito) {
		pregunta_dao.attachDirty(pregunta_transito);
	}

	// ACCESORES PARA SPRING
	public void setPregunta_dao(PreguntasDAOEXT pregunta_dao) {
		this.pregunta_dao = pregunta_dao;
	}
}
