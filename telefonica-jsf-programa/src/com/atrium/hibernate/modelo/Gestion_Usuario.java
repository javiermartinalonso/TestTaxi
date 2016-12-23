package com.atrium.hibernate.modelo;

import java.io.Serializable;
import java.util.List;

import com.atrium.hibernate.Tareas;
import com.atrium.hibernate.Usuarios;
import com.atrium.hibernate.dao.UsuariosDAO;
import com.atrium.hibernate.dao.ext.Usuario_DAOEXT;

public class Gestion_Usuario implements IGestion_Usuario,Serializable {

	private Usuario_DAOEXT usuario_dao;

	// *********** CONSULTAS DE USUARIOS
	@Override
	public Usuarios consultar_PorClave(String nombre_usuario) {
		return usuario_dao.findById(nombre_usuario);
	}

	public List<Tareas> consultar_Tareas(String nombre_usuario) {
		return usuario_dao.consultar_Tareas(nombre_usuario);
	}

	public Usuarios consultar_UsuarioConRol(String nombre) {
		return usuario_dao.consultar_UsuarioConRol(nombre);
	}

	// **** PROCESOS CRUD DE USUARIOS
	@Override
	public void alta_Usuario(Usuarios usuario_transito) {
		usuario_dao.save(usuario_transito);
	}

	@Override
	public void baja_Usuario(Usuarios usuario_transito) {
		usuario_dao.delete(usuario_transito);
	}

	@Override
	public void modificacion_Usuario(Usuarios usuario_transito) {
		usuario_dao.attachDirty(usuario_transito);
	}

	// ACCESORES PARA SPRING
	public void setUsuario_dao(Usuario_DAOEXT usuario_dao) {
		this.usuario_dao = usuario_dao;
	}

}
