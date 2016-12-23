package com.atrium.hibernate;

import java.util.Date;

/**
 * Usuarios entity. @author MyEclipse Persistence Tools
 */

public class Usuarios implements java.io.Serializable {

	// Fields

	private String nombreUsuario;
	private Roles roles;
	private String password;
	private Date fechaAlta;
	private Date fechaBaja;
	private String carpetaDocumentacion;
	private String idioma;

	// Constructors

	/** default constructor */
	public Usuarios() {
	}

	/** minimal constructor */
	public Usuarios(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	/** full constructor */
	public Usuarios(String nombreUsuario, Roles roles, String password,
			Date fechaAlta, Date fechaBaja, String carpetaDocumentacion,
			String idioma) {
		this.nombreUsuario = nombreUsuario;
		this.roles = roles;
		this.password = password;
		this.fechaAlta = fechaAlta;
		this.fechaBaja = fechaBaja;
		this.carpetaDocumentacion = carpetaDocumentacion;
		this.idioma = idioma;
	}

	// Property accessors

	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Roles getRoles() {
		return this.roles;
	}

	public void setRoles(Roles roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaAlta() {
		return this.fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaBaja() {
		return this.fechaBaja;
	}

	public void setFechaBaja(Date fechaBaja) {
		this.fechaBaja = fechaBaja;
	}

	public String getCarpetaDocumentacion() {
		return this.carpetaDocumentacion;
	}

	public void setCarpetaDocumentacion(String carpetaDocumentacion) {
		this.carpetaDocumentacion = carpetaDocumentacion;
	}

	public String getIdioma() {
		return this.idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

}