package com.atrium.hibernate.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.atrium.hibernate.Roles;

/**
 * A data access object (DAO) providing persistence and search support for Roles
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.atrium.hibernate.Roles
 * @author MyEclipse Persistence Tools
 */
public class RolesDAO extends HibernateDaoSupport {
	private static final Logger log = LoggerFactory.getLogger(RolesDAO.class);
	// property constants
	public static final String DESCRIPCION_ROL = "descripcionRol";

	protected void initDao() {
		// do nothing
	}

	public void save(Roles transientInstance) {
		log.debug("saving Roles instance");
		try {
			getHibernateTemplate().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Roles persistentInstance) {
		log.debug("deleting Roles instance");
		try {
			getHibernateTemplate().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Roles findById(java.lang.Byte id) {
		log.debug("getting Roles instance with id: " + id);
		try {
			Roles instance = (Roles) getHibernateTemplate().get(
					"com.atrium.hibernate.Roles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Roles instance) {
		log.debug("finding Roles instance by example");
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
		log.debug("finding Roles instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Roles as model where model."
					+ propertyName + "= ?";
			return getHibernateTemplate().find(queryString, value);
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescripcionRol(Object descripcionRol) {
		return findByProperty(DESCRIPCION_ROL, descripcionRol);
	}

	public List findAll() {
		log.debug("finding all Roles instances");
		try {
			String queryString = "from Roles";
			return getHibernateTemplate().find(queryString);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Roles merge(Roles detachedInstance) {
		log.debug("merging Roles instance");
		try {
			Roles result = (Roles) getHibernateTemplate().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Roles instance) {
		log.debug("attaching dirty Roles instance");
		try {
			getHibernateTemplate().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Roles instance) {
		log.debug("attaching clean Roles instance");
		try {
			getHibernateTemplate().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RolesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RolesDAO) ctx.getBean("RolesDAO");
	}
}