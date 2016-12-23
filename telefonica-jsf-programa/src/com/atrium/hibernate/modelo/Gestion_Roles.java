package com.atrium.hibernate.modelo;

import java.io.Serializable;
import java.util.List;

import com.atrium.hibernate.Roles;
import com.atrium.hibernate.dao.RolesDAO;

public class Gestion_Roles implements IGestion_Roles, Serializable {

	private RolesDAO rol_DAO;

	@Override
	public List<Roles> consultar_Todos() {
		return rol_DAO.findAll();
	}

	public Roles consultar_PorClave(Byte clave_rol) {
		return rol_DAO.findById(clave_rol);
	}

	// ACCESORES PARA SPRING
	public void setRol_DAO(RolesDAO rol_DAO) {
		this.rol_DAO = rol_DAO;
	}

}
