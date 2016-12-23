package com.testtaxi.hibernate.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.testtaxi.hibernate.Preguntas;

/**
 	* A data access object (DAO) providing persistence and search support for Preguntas entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.testtaxi.hibernate.Preguntas
  * @author MyEclipse Persistence Tools 
 */
public class PreguntasDAO extends HibernateDaoSupport  {
	private static final Logger log = LoggerFactory.getLogger(PreguntasDAO.class);
	//property constants
	public static final String TEXTO_PREGUNTA = "textoPregunta";
	public static final String PUNTUACION = "puntuacion";
	public static final String INDICACIONES_PREGUNTA = "indicacionesPregunta";
	public static final String FOTO = "foto";
	public static final String DIFICULTAD = "dificultad";
	public static final String ID_RESPUESTA_CORRECTA = "idRespuestaCorrecta";
	public static final String NUM_RESPUESTAS = "numRespuestas";
	public static final String RESPUESTA1 = "respuesta1";
	public static final String RESPUESTA2 = "respuesta2";
	public static final String RESPUESTA3 = "respuesta3";
	public static final String RESPUESTA4 = "respuesta4";
	public static final String RESPUESTA5 = "respuesta5";
	public static final String RESPUESTA6 = "respuesta6";
	public static final String COMENTARIO = "comentario";
	public static final String TIPO_PREGUNTA = "tipoPregunta";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Preguntas transientInstance) {
        log.debug("saving Preguntas instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Preguntas persistentInstance) {
        log.debug("deleting Preguntas instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Preguntas findById( java.lang.Integer id) {
        log.debug("getting Preguntas instance with id: " + id);
        try {
            Preguntas instance = (Preguntas) getHibernateTemplate().get("com.testtaxi.hibernate.Preguntas", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Preguntas instance) {
        log.debug("finding Preguntas instance by example");
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
      log.debug("finding Preguntas instance with property: " + propertyName + ", value: " + value);
      try {
         String queryString = "from Preguntas as model where model." + propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTextoPregunta(Object textoPregunta) {
		return findByProperty(TEXTO_PREGUNTA, textoPregunta);
	}
	
	public List findByPuntuacion(Object puntuacion) {
		return findByProperty(PUNTUACION, puntuacion);
	}
	
	public List findByIndicacionesPregunta(Object indicacionesPregunta) {
		return findByProperty(INDICACIONES_PREGUNTA, indicacionesPregunta);
	}
	
	public List findByFoto(Object foto) {
		return findByProperty(FOTO, foto);
	}
	
	public List findByDificultad(Object dificultad) {
		return findByProperty(DIFICULTAD, dificultad);
	}
	
	public List findByIdRespuestaCorrecta(Object idRespuestaCorrecta) {
		return findByProperty(ID_RESPUESTA_CORRECTA, idRespuestaCorrecta);
	}
	
	public List findByNumRespuestas(Object numRespuestas) {
		return findByProperty(NUM_RESPUESTAS, numRespuestas);
	}
	
	public List findByRespuesta1(Object respuesta1) {
		return findByProperty(RESPUESTA1, respuesta1);
	}
	
	public List findByRespuesta2(Object respuesta2) {
		return findByProperty(RESPUESTA2, respuesta2);
	}
	
	public List findByRespuesta3(Object respuesta3) {
		return findByProperty(RESPUESTA3, respuesta3);
	}
	
	public List findByRespuesta4(Object respuesta4) {
		return findByProperty(RESPUESTA4, respuesta4);
	}
	
	public List findByRespuesta5(Object respuesta5) {
		return findByProperty(RESPUESTA5, respuesta5);
	}
	
	public List findByRespuesta6(Object respuesta6) {
		return findByProperty(RESPUESTA6, respuesta6);
	}
	
	public List findByComentario(Object comentario) {
		return findByProperty(COMENTARIO, comentario);
	}
	
	public List findByTipoPregunta(Object tipoPregunta) {
		return findByProperty(TIPO_PREGUNTA, tipoPregunta);
	}
	

	public List findAll() {
		log.debug("finding all Preguntas instances");
		try {
			String queryString = "from Preguntas";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Preguntas merge(Preguntas detachedInstance) {
        log.debug("merging Preguntas instance");
        try {
            Preguntas result = (Preguntas) getHibernateTemplate().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Preguntas instance) {
        log.debug("attaching dirty Preguntas instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Preguntas instance) {
        log.debug("attaching clean Preguntas instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static PreguntasDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (PreguntasDAO) ctx.getBean("PreguntasDAO");
	}
}