package com.atrium.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * Roles entity. @author MyEclipse Persistence Tools
 */

public class Roles implements java.io.Serializable {

	// Fields

	private Byte codigoRol;
	private String descripcionRol;
	private Set tareases = new HashSet(0);
	private Set usuarioses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Roles() {
	}

	/** minimal constructor */
	public Roles(Byte codigoRol) {
		this.codigoRol = codigoRol;
	}

	/** full constructor */
	public Roles(Byte codigoRol, String descripcionRol, Set tareases,
			Set usuarioses) {
		this.codigoRol = codigoRol;
		this.descripcionRol = descripcionRol;
		this.tareases = tareases;
		this.usuarioses = usuarioses;
	}

	// Property accessors

	public Byte getCodigoRol() {
		return this.codigoRol;
	}

	public void setCodigoRol(Byte codigoRol) {
		this.codigoRol = codigoRol;
	}

	public String getDescripcionRol() {
		return this.descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	public Set getTareases() {
		return this.tareases;
	}

	public void setTareases(Set tareases) {
		this.tareases = tareases;
	}

	public Set getUsuarioses() {
		return this.usuarioses;
	}

	public void setUsuarioses(Set usuarioses) {
		this.usuarioses = usuarioses;
	}

}