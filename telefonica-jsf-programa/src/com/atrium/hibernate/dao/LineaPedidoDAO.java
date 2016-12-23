package com.atrium.hibernate.dao;

import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.atrium.hibernate.LineaPedido;

/**
 	* A data access object (DAO) providing persistence and search support for LineaPedido entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.atrium.hibernate.LineaPedido
  * @author MyEclipse Persistence Tools 
 */
public class LineaPedidoDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(LineaPedidoDAO.class);
		//property constants
	public static final String PRECIO_UNIDAD_ARTICULO = "precioUnidadArticulo";
	public static final String NUMERO_UNIDADES_ARTICULO = "numeroUnidadesArticulo";
	public static final String PORCENTAJE_DESCUENTO = "porcentajeDescuento";



	protected void initDao() {
		//do nothing
	}
    
    public void save(LineaPedido transientInstance) {
        log.debug("saving LineaPedido instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(LineaPedido persistentInstance) {
        log.debug("deleting LineaPedido instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public LineaPedido findById( java.lang.Long id) {
        log.debug("getting LineaPedido instance with id: " + id);
        try {
            LineaPedido instance = (LineaPedido) getHibernateTemplate()
                    .get("com.atrium.hibernate.LineaPedido", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(LineaPedido instance) {
        log.debug("finding LineaPedido instance by example");
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
      log.debug("finding LineaPedido instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from LineaPedido as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByPrecioUnidadArticulo(Object precioUnidadArticulo
	) {
		return findByProperty(PRECIO_UNIDAD_ARTICULO, precioUnidadArticulo
		);
	}
	
	public List findByNumeroUnidadesArticulo(Object numeroUnidadesArticulo
	) {
		return findByProperty(NUMERO_UNIDADES_ARTICULO, numeroUnidadesArticulo
		);
	}
	
	public List findByPorcentajeDescuento(Object porcentajeDescuento
	) {
		return findByProperty(PORCENTAJE_DESCUENTO, porcentajeDescuento
		);
	}
	

	public List findAll() {
		log.debug("finding all LineaPedido instances");
		try {
			String queryString = "from LineaPedido";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public LineaPedido merge(LineaPedido detachedInstance) {
        log.debug("merging LineaPedido instance");
        try {
            LineaPedido result = (LineaPedido) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(LineaPedido instance) {
        log.debug("attaching dirty LineaPedido instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(LineaPedido instance) {
        log.debug("attaching clean LineaPedido instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static LineaPedidoDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (LineaPedidoDAO) ctx.getBean("LineaPedidoDAO");
	}
}