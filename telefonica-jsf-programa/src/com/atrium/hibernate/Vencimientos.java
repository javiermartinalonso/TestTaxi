package com.atrium.hibernate;

import java.util.Date;

/**
 * Vencimientos entity. @author MyEclipse Persistence Tools
 */

public class Vencimientos implements java.io.Serializable {

	// Fields

	private Long codigoVencimiento;
	private Clientes clientes;
	private Date fechaVencimiento;
	private Double cantidadVencimiento;
	private Pedidos pedido;
	private Date fechaCobroVencimiento;
	private String nombreBanco;
	private String estadoVencimiento;

	// Constructors

	/** default constructor */
	public Vencimientos() {
	}

	/** minimal constructor */
	public Vencimientos(Long codigoVencimiento, Clientes clientes) {
		this.codigoVencimiento = codigoVencimiento;
		this.clientes = clientes;
	}

	/** full constructor */
	public Vencimientos(Long codigoVencimiento, Clientes clientes,
			Date fechaVencimiento, Double cantidadVencimiento,
			Integer numeroPedido, Date fechaCobroVencimiento,
			String nombreBanco, String estadoVencimiento) {
		this.codigoVencimiento = codigoVencimiento;
		this.clientes = clientes;
		this.fechaVencimiento = fechaVencimiento;
		this.cantidadVencimiento = cantidadVencimiento;

		this.fechaCobroVencimiento = fechaCobroVencimiento;
		this.nombreBanco = nombreBanco;
		this.estadoVencimiento = estadoVencimiento;
	}

	// Property accessors

	public Long getCodigoVencimiento() {
		return this.codigoVencimiento;
	}

	public void setCodigoVencimiento(Long codigoVencimiento) {
		this.codigoVencimiento = codigoVencimiento;
	}

	public Clientes getClientes() {
		return this.clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public Date getFechaVencimiento() {
		return this.fechaVencimiento;
	}

	public void setFechaVencimiento(Date fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public Double getCantidadVencimiento() {
		return this.cantidadVencimiento;
	}

	public void setCantidadVencimiento(Double cantidadVencimiento) {
		this.cantidadVencimiento = cantidadVencimiento;
	}

	public Pedidos getPedido() {
		return pedido;
	}

	public void setPedido(Pedidos pedido) {
		this.pedido = pedido;
	}

	public Date getFechaCobroVencimiento() {
		return this.fechaCobroVencimiento;
	}

	public void setFechaCobroVencimiento(Date fechaCobroVencimiento) {
		this.fechaCobroVencimiento = fechaCobroVencimiento;
	}

	public String getNombreBanco() {
		return this.nombreBanco;
	}

	public void setNombreBanco(String nombreBanco) {
		this.nombreBanco = nombreBanco;
	}

	public String getEstadoVencimiento() {
		return this.estadoVencimiento;
	}

	public void setEstadoVencimiento(String estadoVencimiento) {
		this.estadoVencimiento = estadoVencimiento;
	}

}