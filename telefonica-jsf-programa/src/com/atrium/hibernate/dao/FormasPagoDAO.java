package com.atrium.hibernate.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.atrium.hibernate.FormasPago;

/**
 	* A data access object (DAO) providing persistence and search support for FormasPago entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.atrium.hibernate.FormasPago
  * @author MyEclipse Persistence Tools 
 */
public class FormasPagoDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(FormasPagoDAO.class);
		//property constants
	public static final String DESCRIPCION_FORMA_PAGO = "descripcionFormaPago";
	public static final String ESTADO_FORMA_PAGO = "estadoFormaPago";
	public static final String NUMERO_PAGOS = "numeroPagos";
	public static final String NUMERO_DIAS_ENTRE_PAGOS = "numeroDiasEntrePagos";
	public static final String NUMERO_DIAS_PRIMER_PAGO = "numeroDiasPrimerPago";



	protected void initDao() {
		//do nothing
	}
    
    public void save(FormasPago transientInstance) {
        log.debug("saving FormasPago instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(FormasPago persistentInstance) {
        log.debug("deleting FormasPago instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FormasPago findById( java.lang.Integer id) {
        log.debug("getting FormasPago instance with id: " + id);
        try {
            FormasPago instance = (FormasPago) getHibernateTemplate()
                    .get("com.atrium.hibernate.FormasPago", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(FormasPago instance) {
        log.debug("finding FormasPago instance by example");
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
      log.debug("finding FormasPago instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from FormasPago as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByDescripcionFormaPago(Object descripcionFormaPago
	) {
		return findByProperty(DESCRIPCION_FORMA_PAGO, descripcionFormaPago
		);
	}
	
	public List findByEstadoFormaPago(Object estadoFormaPago
	) {
		return findByProperty(ESTADO_FORMA_PAGO, estadoFormaPago
		);
	}
	
	public List findByNumeroPagos(Object numeroPagos
	) {
		return findByProperty(NUMERO_PAGOS, numeroPagos
		);
	}
	
	public List findByNumeroDiasEntrePagos(Object numeroDiasEntrePagos
	) {
		return findByProperty(NUMERO_DIAS_ENTRE_PAGOS, numeroDiasEntrePagos
		);
	}
	
	public List findByNumeroDiasPrimerPago(Object numeroDiasPrimerPago
	) {
		return findByProperty(NUMERO_DIAS_PRIMER_PAGO, numeroDiasPrimerPago
		);
	}
	

	public List findAll() {
		log.debug("finding all FormasPago instances");
		try {
			String queryString = "from FormasPago";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public FormasPago merge(FormasPago detachedInstance) {
        log.debug("merging FormasPago instance");
        try {
            FormasPago result = (FormasPago) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(FormasPago instance) {
        log.debug("attaching dirty FormasPago instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FormasPago instance) {
        log.debug("attaching clean FormasPago instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static FormasPagoDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (FormasPagoDAO) ctx.getBean("FormasPagoDAO");
	}
}