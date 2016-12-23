package com.atrium.hibernate.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.atrium.hibernate.Vencimientos;

/**
 	* A data access object (DAO) providing persistence and search support for Vencimientos entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.atrium.hibernate.Vencimientos
  * @author MyEclipse Persistence Tools 
 */
public class VencimientosDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(VencimientosDAO.class);
		//property constants
	public static final String CANTIDAD_VENCIMIENTO = "cantidadVencimiento";
	public static final String NUMERO_PEDIDO = "numeroPedido";
	public static final String NOMBRE_BANCO = "nombreBanco";
	public static final String ESTADO_VENCIMIENTO = "estadoVencimiento";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Vencimientos transientInstance) {
        log.debug("saving Vencimientos instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Vencimientos persistentInstance) {
        log.debug("deleting Vencimientos instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Vencimientos findById( java.lang.Long id) {
        log.debug("getting Vencimientos instance with id: " + id);
        try {
            Vencimientos instance = (Vencimientos) getHibernateTemplate()
                    .get("com.atrium.hibernate.Vencimientos", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Vencimientos instance) {
        log.debug("finding Vencimientos instance by example");
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
      log.debug("finding Vencimientos instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Vencimientos as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByCantidadVencimiento(Object cantidadVencimiento
	) {
		return findByProperty(CANTIDAD_VENCIMIENTO, cantidadVencimiento
		);
	}
	
	public List findByNumeroPedido(Object numeroPedido
	) {
		return findByProperty(NUMERO_PEDIDO, numeroPedido
		);
	}
	
	public List findByNombreBanco(Object nombreBanco
	) {
		return findByProperty(NOMBRE_BANCO, nombreBanco
		);
	}
	
	public List findByEstadoVencimiento(Object estadoVencimiento
	) {
		return findByProperty(ESTADO_VENCIMIENTO, estadoVencimiento
		);
	}
	

	public List findAll() {
		log.debug("finding all Vencimientos instances");
		try {
			String queryString = "from Vencimientos";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Vencimientos merge(Vencimientos detachedInstance) {
        log.debug("merging Vencimientos instance");
        try {
            Vencimientos result = (Vencimientos) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Vencimientos instance) {
        log.debug("attaching dirty Vencimientos instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Vencimientos instance) {
        log.debug("attaching clean Vencimientos instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static VencimientosDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (VencimientosDAO) ctx.getBean("VencimientosDAO");
	}
}