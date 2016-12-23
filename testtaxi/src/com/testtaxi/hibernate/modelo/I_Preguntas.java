package com.testtaxi.hibernate.modelo;

import java.util.List;

import com.testtaxi.hibernate.Preguntas;

public interface I_Preguntas {

	public abstract void modificacion_Pregunta(Preguntas pregunta_transito);

	public abstract void baja_Pregunta(Preguntas pregunta_transito);

	public abstract void alta_Pregunta(Preguntas pregunta_transito);

	public abstract List<Preguntas> consultar_todas();

	public abstract List<Preguntas> consultar_PorTipo(String tipo);

	public abstract Preguntas consultar_PorClave(Integer id);

	public abstract List<Preguntas> consultar_ListaPreguntasAleatoriaPorTipo(
			int numReg, String tipoPregunta);
}
