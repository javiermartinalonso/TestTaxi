package com.testtaxi.hibernate.modelo;

import java.util.List;

import com.testtaxi.hibernate.Tests;

public interface I_Tests {

	public abstract void modificacion_Test(Tests test_transito);

	public abstract void baja_Test(Tests test_transito);

	public abstract void alta_Test(Tests test_transito);

	public abstract Tests consultar_PorClave(Integer id);

	public abstract Tests consultar_TestConPreguntas(Integer id);

	public abstract List<Tests> consultar_PorTipo(String tipo);

	public abstract List<Tests> consultar_todos();

}
