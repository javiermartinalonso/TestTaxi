package com.testtaxi.hibernate.modelo;

import java.io.Serializable;
import java.util.List;

import com.testtaxi.hibernate.Usuarios;
import com.testtaxi.hibernate.dao.UsuariosDAO;

public class F_Usuario implements I_Usuario,Serializable {

	private static final long serialVersionUID = 1L;
	private UsuariosDAO usuario_dao;

	// *********** CONSULTAS DE USUARIOS
	@Override
	public Usuarios consultar_PorClave(String nombre_usuario) {
		return usuario_dao.findById(nombre_usuario);
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

	@Override
	public List<Usuarios> consultar_todos()
	{
		List<Usuarios> lista_usuarios = (List<Usuarios>) usuario_dao.findAll();
		return lista_usuarios;
	}
	// ACCESORES PARA SPRING
	public void setUsuario_dao(UsuariosDAO usuario_dao) {
		this.usuario_dao = usuario_dao;
	}

}
