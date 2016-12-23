package com.testtaxi.hibernate.dao.ext;

import java.io.Serializable;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.testtaxi.hibernate.Tests;
import com.testtaxi.hibernate.dao.PreguntasDAO;
import com.testtaxi.hibernate.dao.TestsDAO;

public class TestDAOEXT extends TestsDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tests consultar_TestConPreguntas(Integer id) {
		DetachedCriteria consulta = DetachedCriteria.forClass(Tests.class);
		consulta.setFetchMode("preguntases", FetchMode.JOIN);
//		consulta.setFetchMode("respuestas.preguntases", FetchMode.JOIN);
		consulta.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		consulta.add(Restrictions.idEq(id));
		List<Tests> lista = (List<Tests>) getHibernateTemplate().findByCriteria(consulta);
		Tests tests = null;
		if (!lista.isEmpty()) {
			tests = lista.get(0);
		}
		return tests;
		
		
//		
//		Criteria criteria = session.createCriteria(Alumno.class);
//		criteria.createCriteria("asignaturas").add(Restrictions.eq("casignaturaId", 12))
//		
//		
//		DetachedCriteria consulta = DetachedCriteria.forClass(Usuarios.class);
//		consulta.setFetchMode("roles", FetchMode.JOIN);
//		consulta.setFetchMode("roles.tareases", FetchMode.JOIN);
//		consulta.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
//		consulta.add(Restrictions.idEq(nombre_usuario));
//		List<Usuarios> lista_usu = getHibernateTemplate().findByCriteria(
//				consulta);
//		List<Tareas> lista_tareas = new ArrayList<Tareas>();
//		if (!lista_usu.isEmpty()) {
//			lista_tareas.addAll(lista_usu.get(0).getRoles().getTareases());
//		}
//		return lista_tareas;
	}
}
