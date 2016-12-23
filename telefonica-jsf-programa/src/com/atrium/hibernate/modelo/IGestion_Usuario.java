package com.atrium.hibernate.modelo;

import java.util.List;

import com.atrium.hibernate.Tareas;
import com.atrium.hibernate.Usuarios;

public interface IGestion_Usuario {

	public Usuarios consultar_PorClave(String nombre_usuario);

	public List<Tareas> consultar_Tareas(String nombre_usuario);

	public Usuarios consultar_UsuarioConRol(String nombre);

	public abstract void modificacion_Usuario(Usuarios usuario_transito);

	public abstract void baja_Usuario(Usuarios usuario_transito);

	public abstract void alta_Usuario(Usuarios usuario_transito);

}