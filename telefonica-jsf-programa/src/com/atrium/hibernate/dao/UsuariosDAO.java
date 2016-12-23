package com.atrium.hibernate.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.atrium.hibernate.Usuarios;

/**
 * A data access object (DAO) providing persistence and search support for
 * Usuarios entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.atrium.hibernate.Usuarios
 * @author MyEclipse Persistence Tools
 */
public class UsuariosDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory
			.getLogger(UsuariosDAO.class);
	// property constants
	public static final String PASSWORD = "password";
	public static final String CARPETA_DOCUMENTACION = "carpetaDocumentacion";
	public static final String IDIOMA = "idioma";

	protected void initDao() {
		// do nothing
	}

	public void save(Usuarios transientInstance) {
		log.debug("saving Usuarios instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Usuarios persistentInstance) {
		log.debug("deleting Usuarios instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usuarios findById(java.lang.String id) {
		log.debug("getting Usuarios instance with id: " + id);
		try {
			Usuarios instance = (Usuarios) getHibernateTemplate().get(
					"com.atrium.hibernate.Usuarios", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Usuarios instance) {
		log.debug("finding Usuarios instance by example");
		try {
			List results = getHibernateTemplate().findByExample(instance);
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Usuarios instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Usuarios as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByCarpetaDocumentacion(Object carpetaDocumentacion) {
		return findByProperty(CARPETA_DOCUMENTACION, carpetaDocumentacion);
	}

	public List findByIdioma(Object idioma) {
		return findByProperty(IDIOMA, idioma);
	}

	public List findAll() {
		log.debug("finding all Usuarios instances");
		try {
			String queryString = "from Usuarios";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Usuarios merge(Usuarios detachedInstance) {
		log.debug("merging Usuarios instance");
		try {
			Usuarios result = (Usuarios) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Usuarios instance) {
		log.debug("attaching dirty Usuarios instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usuarios instance) {
		log.debug("attaching clean Usuarios instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UsuariosDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UsuariosDAO) ctx.getBean("UsuariosDAO");
	}
}