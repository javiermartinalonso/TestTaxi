package com.atrium.managedbean;

import java.io.Serializable;

import com.atrium.hibernate.Usuarios;
import com.atrium.hibernate.modelo.IGestion_Usuario;
import com.atrium.util.Acceso_Contextos;

public class Login_Bean implements Serializable{

	// PROPIEDADES DEL FORMULARIO DE LOGIN
	private String nombre_usuario;
	private String clave_usuario;
	// CONTROL DE VISIBILIDAD DEL PANEL DE LOGIN
	private boolean login_visible;

	private IGestion_Usuario gestion_usuario;

	/**
	 * Constructor que inicia las propiedades de clase.
	 */
	public Login_Bean() {
		login_visible = true;
		nombre_usuario = "Juan";
		clave_usuario = "admin";
	}

	public void comprobar_Credenciales() {
		Usuarios usuario = gestion_usuario
				.consultar_PorClave(getNombre_usuario());
		if (usuario != null) {
			if (usuario.getPassword().equals(getClave_usuario())) {
				// CORRECTO
				Acceso_Contextos.getSesion().setAttribute("usuario", usuario);
				login_visible = false;
				// HACEMOS VISIBLE EL MENU EN EL MOMENTO EN QUE EL USUARIO ES
				// CORRECTO
				Menu_DinamicoBean menu_bean = (Menu_DinamicoBean) Acceso_Contextos
						.getSesion().getAttribute("menu_bean");
				menu_bean.setVisible(true);
				menu_bean.crear_Menu();
			} else {
				// ERROR EN LA CLAVE
				((Mensajes_Bean) Acceso_Contextos.getSesion().getAttribute(
						"mensajes_bean")).setVisible(true);
				((Mensajes_Bean) Acceso_Contextos.getSesion().getAttribute(
						"mensajes_bean")).setMensaje("Error en la clave");
			}
		} else {
			// ERROR EN EL NOMBRE
			((Mensajes_Bean) Acceso_Contextos.getSesion().getAttribute(
					"mensajes_bean")).setVisible(true);
			((Mensajes_Bean) Acceso_Contextos.getSesion().getAttribute(
					"mensajes_bean")).setMensaje("Error en el nombre");
		}
	}

	// ACCESORES PARA JSF
	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getClave_usuario() {
		return clave_usuario;
	}

	public void setClave_usuario(String clave_usuario) {
		this.clave_usuario = clave_usuario;
	}

	public boolean isLogin_visible() {
		return login_visible;
	}

	public void setLogin_visible(boolean login_visible) {
		this.login_visible = login_visible;
	}

	// ACCESORES PARA SPRING
	public void setGestion_usuario(IGestion_Usuario gestion_usuario) {
		this.gestion_usuario = gestion_usuario;
	}
}
