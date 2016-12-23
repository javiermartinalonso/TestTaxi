package com.testtaxi.jsf.managedbean.sinuso;

import java.io.Serializable;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.testtaxi.hibernate.Preguntas;
import com.testtaxi.hibernate.dao.PreguntasDAO;

public class PreguntasDAOEXT extends PreguntasDAO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Preguntas consultar_PreguntaConRespuestas(Integer id) {
		DetachedCriteria consulta = DetachedCriteria.forClass(Preguntas.class);
		consulta.setFetchMode("respuestases", FetchMode.JOIN);
//		consulta.setFetchMode("respuestas.preguntases", FetchMode.JOIN);
		consulta.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		consulta.add(Restrictions.idEq(id));
		List<Preguntas> lista = (List<Preguntas>) getHibernateTemplate().findByCriteria(consulta);
		Preguntas preguntas = null;
		if (!lista.isEmpty()) {
			preguntas = lista.get(0);
		}
		return preguntas;
		
		
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
