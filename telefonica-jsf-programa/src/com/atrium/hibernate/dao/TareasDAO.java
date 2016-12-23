package com.atrium.hibernate.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.atrium.hibernate.Tareas;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tareas entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.atrium.hibernate.Tareas
 * @author MyEclipse Persistence Tools
 */
public class TareasDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(TareasDAO.class);
	// property constants
	public static final String DESCRIPCION_TAREA = "descripcionTarea";
	public static final String VINCULO = "vinculo";

	protected void initDao() {
		// do nothing
	}

	public void save(Tareas transientInstance) {
		log.debug("saving Tareas instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tareas persistentInstance) {
		log.debug("deleting Tareas instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tareas findById(java.lang.Byte id) {
		log.debug("getting Tareas instance with id: " + id);
		try {
			Tareas instance = (Tareas) getHibernateTemplate().get(
					"com.atrium.hibernate.Tareas", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tareas instance) {
		log.debug("finding Tareas instance by example");
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
		log.debug("finding Tareas instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tareas as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescripcionTarea(Object descripcionTarea) {
		return findByProperty(DESCRIPCION_TAREA, descripcionTarea);
	}

	public List findByVinculo(Object vinculo) {
		return findByProperty(VINCULO, vinculo);
	}

	public List findAll() {
		log.debug("finding all Tareas instances");
		try {
			String queryString = "from Tareas";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tareas merge(Tareas detachedInstance) {
		log.debug("merging Tareas instance");
		try {
			Tareas result = (Tareas) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tareas instance) {
		log.debug("attaching dirty Tareas instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tareas instance) {
		log.debug("attaching clean Tareas instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static TareasDAO getFromApplicationContext(ApplicationContext ctx) {
		return (TareasDAO) ctx.getBean("TareasDAO");
	}
}