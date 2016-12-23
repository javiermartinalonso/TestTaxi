package com.atrium.hibernate.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.atrium.hibernate.Clientes;

/**
 	* A data access object (DAO) providing persistence and search support for Clientes entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.atrium.hibernate.Clientes
  * @author MyEclipse Persistence Tools 
 */
public class ClientesDAO extends HibernateDaoSupport  {
	     private static final Logger log = LoggerFactory.getLogger(ClientesDAO.class);
		//property constants
	public static final String NOMBRE_CLIENTE = "nombreCliente";
	public static final String TIPO_EMPRESA = "tipoEmpresa";
	public static final String CIF_CLIENTE = "cifCliente";
	public static final String NIF_CLIENTE = "nifCliente";
	public static final String NOMBRE_COMERCIAL_CLIENTE = "nombreComercialCliente";
	public static final String CALLE_CLIENTE = "calleCliente";
	public static final String CIUDAD_CLIENTE = "ciudadCliente";
	public static final String CP_CLIENTE = "cpCliente";
	public static final String PROVINCIA_CLIENTE = "provinciaCliente";
	public static final String TELEFONO_CLIENTE = "telefonoCliente";
	public static final String FAX_CLIENTE = "faxCliente";
	public static final String EMAIL_CLIENTE = "emailCliente";
	public static final String CONTADO_CLIENTE = "contadoCliente";
	public static final String CREDITO_CLIENTE = "creditoCliente";
	public static final String CIFRA_VENTAS_CLIENTE = "cifraVentasCliente";
	public static final String BENEFICIOS_CLIENTE = "beneficiosCliente";
	public static final String PREPAGO_CLIENTE = "prepagoCliente";
	public static final String CAPITAL_CLIENTE = "capitalCliente";
	public static final String INMOVILIZADO_CLIENTE = "inmovilizadoCliente";
	public static final String PASIVO_CLIENTE = "pasivoCliente";
	public static final String ACTIVO_CLIENTE = "activoCliente";
	public static final String LIMITE_CLIENTE = "limiteCliente";
	public static final String PENDIENTE_CLIENTE = "pendienteCliente";
	public static final String RESTO_CLIENTE = "restoCliente";
	public static final String CALLE_ENVIO_CLIENTE = "calleEnvioCliente";
	public static final String CIUDAD_ENVIO_CLIENTE = "ciudadEnvioCliente";
	public static final String CP_ENVIO_CLIENTE = "cpEnvioCliente";
	public static final String PROVINCIA_ENVIO_CLIENTE = "provinciaEnvioCliente";
	public static final String TELEFONO_ENVIO_CLIENTE = "telefonoEnvioCliente";
	public static final String FAX_ENVIO_CLIENTE = "faxEnvioCliente";
	public static final String EMAIL_ENVIO_CLIENTE = "emailEnvioCliente";
	public static final String OBSERVACIONES_CLIENTE = "observacionesCliente";
	public static final String ESTADO_CLIENTE = "estadoCliente";



	protected void initDao() {
		//do nothing
	}
    
    public void save(Clientes transientInstance) {
        log.debug("saving Clientes instance");
        try {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Clientes persistentInstance) {
        log.debug("deleting Clientes instance");
        try {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Clientes findById( java.lang.Integer id) {
        log.debug("getting Clientes instance with id: " + id);
        try {
            Clientes instance = (Clientes) getHibernateTemplate()
                    .get("com.atrium.hibernate.Clientes", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Clientes instance) {
        log.debug("finding Clientes instance by example");
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
      log.debug("finding Clientes instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Clientes as model where model." 
         						+ propertyName + "= ?";
		 return getHibernateTemplate().find(queryString, value);
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByNombreCliente(Object nombreCliente
	) {
		return findByProperty(NOMBRE_CLIENTE, nombreCliente
		);
	}
	
	public List findByTipoEmpresa(Object tipoEmpresa
	) {
		return findByProperty(TIPO_EMPRESA, tipoEmpresa
		);
	}
	
	public List findByCifCliente(Object cifCliente
	) {
		return findByProperty(CIF_CLIENTE, cifCliente
		);
	}
	
	public List findByNifCliente(Object nifCliente
	) {
		return findByProperty(NIF_CLIENTE, nifCliente
		);
	}
	
	public List findByNombreComercialCliente(Object nombreComercialCliente
	) {
		return findByProperty(NOMBRE_COMERCIAL_CLIENTE, nombreComercialCliente
		);
	}
	
	public List findByCalleCliente(Object calleCliente
	) {
		return findByProperty(CALLE_CLIENTE, calleCliente
		);
	}
	
	public List findByCiudadCliente(Object ciudadCliente
	) {
		return findByProperty(CIUDAD_CLIENTE, ciudadCliente
		);
	}
	
	public List findByCpCliente(Object cpCliente
	) {
		return findByProperty(CP_CLIENTE, cpCliente
		);
	}
	
	public List findByProvinciaCliente(Object provinciaCliente
	) {
		return findByProperty(PROVINCIA_CLIENTE, provinciaCliente
		);
	}
	
	public List findByTelefonoCliente(Object telefonoCliente
	) {
		return findByProperty(TELEFONO_CLIENTE, telefonoCliente
		);
	}
	
	public List findByFaxCliente(Object faxCliente
	) {
		return findByProperty(FAX_CLIENTE, faxCliente
		);
	}
	
	public List findByEmailCliente(Object emailCliente
	) {
		return findByProperty(EMAIL_CLIENTE, emailCliente
		);
	}
	
	public List findByContadoCliente(Object contadoCliente
	) {
		return findByProperty(CONTADO_CLIENTE, contadoCliente
		);
	}
	
	public List findByCreditoCliente(Object creditoCliente
	) {
		return findByProperty(CREDITO_CLIENTE, creditoCliente
		);
	}
	
	public List findByCifraVentasCliente(Object cifraVentasCliente
	) {
		return findByProperty(CIFRA_VENTAS_CLIENTE, cifraVentasCliente
		);
	}
	
	public List findByBeneficiosCliente(Object beneficiosCliente
	) {
		return findByProperty(BENEFICIOS_CLIENTE, beneficiosCliente
		);
	}
	
	public List findByPrepagoCliente(Object prepagoCliente
	) {
		return findByProperty(PREPAGO_CLIENTE, prepagoCliente
		);
	}
	
	public List findByCapitalCliente(Object capitalCliente
	) {
		return findByProperty(CAPITAL_CLIENTE, capitalCliente
		);
	}
	
	public List findByInmovilizadoCliente(Object inmovilizadoCliente
	) {
		return findByProperty(INMOVILIZADO_CLIENTE, inmovilizadoCliente
		);
	}
	
	public List findByPasivoCliente(Object pasivoCliente
	) {
		return findByProperty(PASIVO_CLIENTE, pasivoCliente
		);
	}
	
	public List findByActivoCliente(Object activoCliente
	) {
		return findByProperty(ACTIVO_CLIENTE, activoCliente
		);
	}
	
	public List findByLimiteCliente(Object limiteCliente
	) {
		return findByProperty(LIMITE_CLIENTE, limiteCliente
		);
	}
	
	public List findByPendienteCliente(Object pendienteCliente
	) {
		return findByProperty(PENDIENTE_CLIENTE, pendienteCliente
		);
	}
	
	public List findByRestoCliente(Object restoCliente
	) {
		return findByProperty(RESTO_CLIENTE, restoCliente
		);
	}
	
	public List findByCalleEnvioCliente(Object calleEnvioCliente
	) {
		return findByProperty(CALLE_ENVIO_CLIENTE, calleEnvioCliente
		);
	}
	
	public List findByCiudadEnvioCliente(Object ciudadEnvioCliente
	) {
		return findByProperty(CIUDAD_ENVIO_CLIENTE, ciudadEnvioCliente
		);
	}
	
	public List findByCpEnvioCliente(Object cpEnvioCliente
	) {
		return findByProperty(CP_ENVIO_CLIENTE, cpEnvioCliente
		);
	}
	
	public List findByProvinciaEnvioCliente(Object provinciaEnvioCliente
	) {
		return findByProperty(PROVINCIA_ENVIO_CLIENTE, provinciaEnvioCliente
		);
	}
	
	public List findByTelefonoEnvioCliente(Object telefonoEnvioCliente
	) {
		return findByProperty(TELEFONO_ENVIO_CLIENTE, telefonoEnvioCliente
		);
	}
	
	public List findByFaxEnvioCliente(Object faxEnvioCliente
	) {
		return findByProperty(FAX_ENVIO_CLIENTE, faxEnvioCliente
		);
	}
	
	public List findByEmailEnvioCliente(Object emailEnvioCliente
	) {
		return findByProperty(EMAIL_ENVIO_CLIENTE, emailEnvioCliente
		);
	}
	
	public List findByObservacionesCliente(Object observacionesCliente
	) {
		return findByProperty(OBSERVACIONES_CLIENTE, observacionesCliente
		);
	}
	
	public List findByEstadoCliente(Object estadoCliente
	) {
		return findByProperty(ESTADO_CLIENTE, estadoCliente
		);
	}
	

	public List findAll() {
		log.debug("finding all Clientes instances");
		try {
			String queryString = "from Clientes";
		 	return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Clientes merge(Clientes detachedInstance) {
        log.debug("merging Clientes instance");
        try {
            Clientes result = (Clientes) getHibernateTemplate()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Clientes instance) {
        log.debug("attaching dirty Clientes instance");
        try {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Clientes instance) {
        log.debug("attaching clean Clientes instance");
        try {
                      	getHibernateTemplate().lock(instance, LockMode.NONE);
                        log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static ClientesDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (ClientesDAO) ctx.getBean("ClientesDAO");
	}
}