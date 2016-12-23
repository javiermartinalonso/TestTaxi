package com.atrium.hibernate.dao.ext;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.atrium.hibernate.Tareas;
import com.atrium.hibernate.Usuarios;
import com.atrium.hibernate.dao.UsuariosDAO;

public class Usuario_DAOEXT extends UsuariosDAO implements Serializable {

	/**
	 * Proceso de consulta de tareas a partir del usuario. Resolucion de dos
	 * cargas vagas.
	 * 
	 * @param nombre_usuario
	 * @return
	 */
	public List<Tareas> consultar_Tareas(String nombre_usuario) {
		DetachedCriteria consulta = DetachedCriteria.forClass(Usuarios.class);
		consulta.setFetchMode("roles", FetchMode.JOIN);
		consulta.setFetchMode("roles.tareases", FetchMode.JOIN);
		consulta.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
		consulta.add(Restrictions.idEq(nombre_usuario));
		List<Usuarios> lista_usu = getHibernateTemplate().findByCriteria(
				consulta);
		List<Tareas> lista_tareas = new ArrayList<Tareas>();
		if (!lista_usu.isEmpty()) {
			lista_tareas.addAll(lista_usu.get(0).getRoles().getTareases());
		}
		return lista_tareas;
	}

	public Usuarios consultar_UsuarioConRol(String nombre) {
		DetachedCriteria consulta = DetachedCriteria.forClass(Usuarios.class);
		consulta.setFetchMode("roles", FetchMode.JOIN);
		consulta.add(Restrictions.idEq(nombre));
		List<Usuarios> lista = getHibernateTemplate().findByCriteria(consulta);
		Usuarios usuario = null;
		if (!lista.isEmpty()) {
			usuario = lista.get(0);
		}
		return usuario;
	}
}
