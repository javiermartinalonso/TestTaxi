package com.testtaxi.hibernate.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.testtaxi.hibernate.Tests;

/**
 	* A data access object (DAO) providing persistence and search support for Tests entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.testtaxi.hibernate.Tests
  * @author MyEclipse Persistence Tools 
 */
public class TestsDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(TestsDAO.class);
		//property constants
	public static final String TIPO = "tipo";
	public static final String DESCRIPCION = "descripcion";
	public static final String DURACION = "duracion";
	public static final String DIFICULTAD = "dificultad";
	public static final String NUM_PREGUNTAS = "numPreguntas";
	public static final String PUNTOS_APROBADO = "puntosAprobado";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Tests transientInstance) {
        log.debug("saving Tests instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Tests persistentInstance) {
        log.debug("deleting Tests instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Tests findById( java.lang.Integer id) {
        log.debug("getting Tests instance with id: " + id);
        try {
            Tests instance = (Tests) getHibernateTemplate()
                    .get("com.testtaxi.hibernate.Tests", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Tests instance) {
        log.debug("finding Tests instance by example");
        try {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Tests instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Tests as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTipo(Object tipo
	) {
		return findByProperty(TIPO, tipo
		);
	}
	
	public List findByDescripcion(Object descripcion
	) {
		return findByProperty(DESCRIPCION, descripcion
		);
	}
	
	public List findByDuracion(Object duracion
	) {
		return findByProperty(DURACION, duracion
		);
	}
	
	public List findByDificultad(Object dificultad
	) {
		return findByProperty(DIFICULTAD, dificultad
		);
	}
	
	public List findByNumPreguntas(Object numPreguntas
	) {
		return findByProperty(NUM_PREGUNTAS, numPreguntas
		);
	}
	
	public List findByPuntosAprobado(Object puntosAprobado
	) {
		return findByProperty(PUNTOS_APROBADO, puntosAprobado
		);
	}
	

	public List findAll() {
		log.debug("finding all Tests instances");
		try {
			String queryString = "from Tests";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Tests merge(Tests detachedInstance) {
        log.debug("merging Tests instance");
        try {
            Tests result = (Tests) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Tests instance) {
        log.debug("attaching dirty Tests instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Tests instance) {
        log.debug("attaching clean Tests instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static TestsDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (TestsDAO) ctx.getBean("TestsDAO");
	}
}