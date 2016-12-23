package com.atrium.hibernate.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.atrium.hibernate.Pedidos;

/**
 	* A data access object (DAO) providing persistence and search support for Pedidos entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.atrium.hibernate.Pedidos
  * @author MyEclipse Persistence Tools 
 */
public class PedidosDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(PedidosDAO.class);
		//property constants
	public static final String PORTE_PEDIDO = "portePedido";
	public static final String SEGURO_PEDIDO = "seguroPedido";
	public static final String OTROS_CARGOS_PEDIDO = "otrosCargosPedido";
	public static final String TOTAL_CARGOS_PEDIDO = "totalCargosPedido";
	public static final String TOTAL_BRUTO_PEDIDO = "totalBrutoPedido";
	public static final String PORCENTAJE_IVA_PEDIDO = "porcentajeIvaPedido";
	public static final String IVA_PEDIDO = "ivaPedido";
	public static final String TOTAL_FACTURA_PEDIDO = "totalFacturaPedido";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Pedidos transientInstance) {
        log.debug("saving Pedidos instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Pedidos persistentInstance) {
        log.debug("deleting Pedidos instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Pedidos findById( java.lang.Integer id) {
        log.debug("getting Pedidos instance with id: " + id);
        try {
            Pedidos instance = (Pedidos) getHibernateTemplate()
                    .get("com.atrium.hibernate.Pedidos", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Pedidos instance) {
        log.debug("finding Pedidos instance by example");
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
      log.debug("finding Pedidos instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Pedidos as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByPortePedido(Object portePedido
	) {
		return findByProperty(PORTE_PEDIDO, portePedido
		);
	}
	
	public List findBySeguroPedido(Object seguroPedido
	) {
		return findByProperty(SEGURO_PEDIDO, seguroPedido
		);
	}
	
	public List findByOtrosCargosPedido(Object otrosCargosPedido
	) {
		return findByProperty(OTROS_CARGOS_PEDIDO, otrosCargosPedido
		);
	}
	
	public List findByTotalCargosPedido(Object totalCargosPedido
	) {
		return findByProperty(TOTAL_CARGOS_PEDIDO, totalCargosPedido
		);
	}
	
	public List findByTotalBrutoPedido(Object totalBrutoPedido
	) {
		return findByProperty(TOTAL_BRUTO_PEDIDO, totalBrutoPedido
		);
	}
	
	public List findByPorcentajeIvaPedido(Object porcentajeIvaPedido
	) {
		return findByProperty(PORCENTAJE_IVA_PEDIDO, porcentajeIvaPedido
		);
	}
	
	public List findByIvaPedido(Object ivaPedido
	) {
		return findByProperty(IVA_PEDIDO, ivaPedido
		);
	}
	
	public List findByTotalFacturaPedido(Object totalFacturaPedido
	) {
		return findByProperty(TOTAL_FACTURA_PEDIDO, totalFacturaPedido
		);
	}
	

	public List findAll() {
		log.debug("finding all Pedidos instances");
		try {
			String queryString = "from Pedidos";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Pedidos merge(Pedidos detachedInstance) {
        log.debug("merging Pedidos instance");
        try {
            Pedidos result = (Pedidos) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Pedidos instance) {
        log.debug("attaching dirty Pedidos instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Pedidos instance) {
        log.debug("attaching clean Pedidos instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static PedidosDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (PedidosDAO) ctx.getBean("PedidosDAO");
	}
}