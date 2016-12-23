package com.atrium.hibernate.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.atrium.hibernate.Articulos;

/**
 	* A data access object (DAO) providing persistence and search support for Articulos entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.atrium.hibernate.Articulos
  * @author MyEclipse Persistence Tools 
 */
public class ArticulosDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(ArticulosDAO.class);
		//property constants
	public static final String DESCRIPCION_ARTICULO = "descripcionArticulo";
	public static final String PRECIO_UNIDAD_ARTICULO = "precioUnidadArticulo";
	public static final String CANTIDAD = "cantidad";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Articulos transientInstance) {
        log.debug("saving Articulos instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Articulos persistentInstance) {
        log.debug("deleting Articulos instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Articulos findById( java.lang.Integer id) {
        log.debug("getting Articulos instance with id: " + id);
        try {
            Articulos instance = (Articulos) getHibernateTemplate()
                    .get("com.atrium.hibernate.Articulos", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Articulos instance) {
        log.debug("finding Articulos instance by example");
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
      log.debug("finding Articulos instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Articulos as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByDescripcionArticulo(Object descripcionArticulo
	) {
		return findByProperty(DESCRIPCION_ARTICULO, descripcionArticulo
		);
	}
	
	public List findByPrecioUnidadArticulo(Object precioUnidadArticulo
	) {
		return findByProperty(PRECIO_UNIDAD_ARTICULO, precioUnidadArticulo
		);
	}
	
	public List findByCantidad(Object cantidad
	) {
		return findByProperty(CANTIDAD, cantidad
		);
	}
	

	public List findAll() {
		log.debug("finding all Articulos instances");
		try {
			String queryString = "from Articulos";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Articulos merge(Articulos detachedInstance) {
        log.debug("merging Articulos instance");
        try {
            Articulos result = (Articulos) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Articulos instance) {
        log.debug("attaching dirty Articulos instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Articulos instance) {
        log.debug("attaching clean Articulos instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static ArticulosDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (ArticulosDAO) ctx.getBean("ArticulosDAO");
	}
}