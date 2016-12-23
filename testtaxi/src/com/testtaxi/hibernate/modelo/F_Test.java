package com.testtaxi.hibernate.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.testtaxi.hibernate.Tests;
import com.testtaxi.hibernate.Usuarios;
import com.testtaxi.hibernate.dao.ext.TestDAOEXT;

public class F_Test implements I_Tests,Serializable {

	private static final long serialVersionUID = 1L;
	private TestDAOEXT test_dao;

	// *********** CONSULTAS DE TESTS
	@Override
	public Tests consultar_PorClave(Integer id) {
		return test_dao.findById(id);
	}

	@Override
	public List<Tests> consultar_PorTipo(String tipo){
		return (ArrayList<Tests>) test_dao.findByTipo(tipo);
	}
	
	@Override
	public List<Tests> consultar_todos()
	{
		return (List<Tests>) test_dao.findAll();
	}
	
	@Override
	public Tests consultar_TestConPreguntas(Integer id){
		return test_dao.consultar_TestConPreguntas(id);
	}
	
	// **** PROCESOS CRUD DE Tests
	@Override
	public void alta_Test(Tests test_transito) {
		test_dao.save(test_transito);
	}

	@Override
	public void baja_Test(Tests test_transito) {
		test_dao.delete(test_transito);
	}

	@Override
	public void modificacion_Test(Tests test_transito) {
		test_dao.attachDirty(test_transito);
	}
	
	// ACCESORES PARA SPRING
	public void setTest_dao(TestDAOEXT test_dao) {
		this.test_dao = test_dao;
	}	
}
