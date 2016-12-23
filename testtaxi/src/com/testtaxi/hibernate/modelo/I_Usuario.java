package com.testtaxi.hibernate.modelo;

import java.util.List;

import com.testtaxi.hibernate.Usuarios;

public interface I_Usuario {

	public Usuarios consultar_PorClave(String nombre_usuario);

	public abstract void modificacion_Usuario(Usuarios usuario_transito);

	public abstract void baja_Usuario(Usuarios usuario_transito);

	public abstract void alta_Usuario(Usuarios usuario_transito);
	
	public abstract List<Usuarios> consultar_todos();

	public static final int ADMINISTRADOR = 0;
	
	
	

}