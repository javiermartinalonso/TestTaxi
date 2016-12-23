package com.atrium.hibernate.modelo;

import java.util.List;

import com.atrium.hibernate.Roles;

public interface IGestion_Roles {

	public abstract List<Roles> consultar_Todos();
	
	public Roles consultar_PorClave(Byte clave_rol);

}