package com.testtaxi.hibernate.dao.ext;

import java.io.Serializable;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.testtaxi.hibernate.Preguntas;
import com.testtaxi.hibernate.dao.PreguntasDAO;

public class PreguntasDAOEXT extends PreguntasDAO implements Serializable {

	
	private static final long serialVersionUID = 1L;

	private static final Logger log = LoggerFactory.getLogger(PreguntasDAOEXT.class);
	
	public Preguntas consultar_PreguntaConRespuestas(Integer id) {
		DetachedCriteria consulta = DetachedCriteria.forClass(Preguntas.class);
		consulta.setFetchMode("respuestases", FetchMode.JOIN);
//		consulta.setFetchMode("respuestas.preguntases", FetchMode.JOIN);
		consulta.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		consulta.add(Restrictions.idEq(id));
		List<Preguntas> lista = getHibernateTemplate().findByCriteria(consulta);
		Preguntas preguntas = null;
		if (!lista.isEmpty()) {
			preguntas = lista.get(0);
		}
		return preguntas;
	}
	
	
	public List getListaPreguntasAleatoria(int numReg, String tipoPregunta)
	{
		
		
		
		
		
		
//		Session session = ((HibernatePersistenceStrategy)this.strategy).getSession();
//		Dialect currentDialect = ((SessionFactoryImplementor) session.getSessionFactory()).getDialect();
//
//		String queryString = null;
//
//		if (currentDialect instanceof Oracle9Dialect) {
//		 	// Oracle 9 specific
//		    	queryString = "CUSTOM_QUERY_ORACLE";
//		}
//		else {
//			// MYSQL specific
//		    	queryString = "from Preguntas order by rand();";
//		}
//
//		Query query = session.createQuery(queryString);
//		query.setMaxResults(numReg);
//		
//		

		
		
		
		
		
		
		log.debug("finding Preguntas instance with property: tipoPregunta, value: " + tipoPregunta);
      try {
         String queryString = "from Preguntas as model where model.tipoPregunta= ? order by rand()";
         
         getHibernateTemplate().setMaxResults(numReg);
		 return getHibernateTemplate().find(queryString, tipoPregunta);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
		
	}
	
	
}
