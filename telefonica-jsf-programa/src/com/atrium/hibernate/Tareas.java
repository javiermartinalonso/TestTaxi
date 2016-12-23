package com.atrium.hibernate;

import java.util.HashSet;
import java.util.Set;

/**
 * Tareas entity. @author MyEclipse Persistence Tools
 */

public class Tareas implements java.io.Serializable {

	// Fields

	private Byte codigoTarea;
	private String descripcionTarea;
	private String vinculo;
	private Set roleses = new HashSet(0);

	// Constructors

	/** default constructor */
	public Tareas() {
	}

	/** minimal constructor */
	public Tareas(Byte codigoTarea) {
		this.codigoTarea = codigoTarea;
	}

	/** full constructor */
	public Tareas(Byte codigoTarea, String descripcionTarea, String vinculo,
			Set roleses) {
		this.codigoTarea = codigoTarea;
		this.descripcionTarea = descripcionTarea;
		this.vinculo = vinculo;
		this.roleses = roleses;
	}

	// Property accessors

	public Byte getCodigoTarea() {
		return this.codigoTarea;
	}

	public void setCodigoTarea(Byte codigoTarea) {
		this.codigoTarea = codigoTarea;
	}

	public String getDescripcionTarea() {
		return this.descripcionTarea;
	}

	public void setDescripcionTarea(String descripcionTarea) {
		this.descripcionTarea = descripcionTarea;
	}

	public String getVinculo() {
		return this.vinculo;
	}

	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}

	public Set getRoleses() {
		return this.roleses;
	}

	public void setRoleses(Set roleses) {
		this.roleses = roleses;
	}

}