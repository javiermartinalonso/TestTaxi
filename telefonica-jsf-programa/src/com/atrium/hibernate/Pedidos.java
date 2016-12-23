package com.atrium.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Pedidos entity. @author MyEclipse Persistence Tools
 */

public class Pedidos  implements java.io.Serializable {


    // Fields    

     private Integer numeroPedido;
     private Clientes clientes;
     private Date fechaPedido;
     private Double portePedido;
     private Double seguroPedido;
     private Double otrosCargosPedido;
     private Double totalCargosPedido;
     private Double totalBrutoPedido;
     private Byte porcentajeIvaPedido;
     private Double ivaPedido;
     private Double totalFacturaPedido;
     private Set lineaPedidos = new HashSet(0);


    // Constructors

    /** default constructor */
    public Pedidos() {
    }

	/** minimal constructor */
    public Pedidos(Integer numeroPedido, Clientes clientes) {
        this.numeroPedido = numeroPedido;
        this.clientes = clientes;
    }
    
    /** full constructor */
    public Pedidos(Integer numeroPedido, Clientes clientes, Date fechaPedido, Double portePedido, Double seguroPedido, Double otrosCargosPedido, Double totalCargosPedido, Double totalBrutoPedido, Byte porcentajeIvaPedido, Double ivaPedido, Double totalFacturaPedido, Set lineaPedidos) {
        this.numeroPedido = numeroPedido;
        this.clientes = clientes;
        this.fechaPedido = fechaPedido;
        this.portePedido = portePedido;
        this.seguroPedido = seguroPedido;
        this.otrosCargosPedido = otrosCargosPedido;
        this.totalCargosPedido = totalCargosPedido;
        this.totalBrutoPedido = totalBrutoPedido;
        this.porcentajeIvaPedido = porcentajeIvaPedido;
        this.ivaPedido = ivaPedido;
        this.totalFacturaPedido = totalFacturaPedido;
        this.lineaPedidos = lineaPedidos;
    }

   
    // Property accessors

    public Integer getNumeroPedido() {
        return this.numeroPedido;
    }
    
    public void setNumeroPedido(Integer numeroPedido) {
        this.numeroPedido = numeroPedido;
    }

    public Clientes getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public Date getFechaPedido() {
        return this.fechaPedido;
    }
    
    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Double getPortePedido() {
        return this.portePedido;
    }
    
    public void setPortePedido(Double portePedido) {
        this.portePedido = portePedido;
    }

    public Double getSeguroPedido() {
        return this.seguroPedido;
    }
    
    public void setSeguroPedido(Double seguroPedido) {
        this.seguroPedido = seguroPedido;
    }

    public Double getOtrosCargosPedido() {
        return this.otrosCargosPedido;
    }
    
    public void setOtrosCargosPedido(Double otrosCargosPedido) {
        this.otrosCargosPedido = otrosCargosPedido;
    }

    public Double getTotalCargosPedido() {
        return this.totalCargosPedido;
    }
    
    public void setTotalCargosPedido(Double totalCargosPedido) {
        this.totalCargosPedido = totalCargosPedido;
    }

    public Double getTotalBrutoPedido() {
        return this.totalBrutoPedido;
    }
    
    public void setTotalBrutoPedido(Double totalBrutoPedido) {
        this.totalBrutoPedido = totalBrutoPedido;
    }

    public Byte getPorcentajeIvaPedido() {
        return this.porcentajeIvaPedido;
    }
    
    public void setPorcentajeIvaPedido(Byte porcentajeIvaPedido) {
        this.porcentajeIvaPedido = porcentajeIvaPedido;
    }

    public Double getIvaPedido() {
        return this.ivaPedido;
    }
    
    public void setIvaPedido(Double ivaPedido) {
        this.ivaPedido = ivaPedido;
    }

    public Double getTotalFacturaPedido() {
        return this.totalFacturaPedido;
    }
    
    public void setTotalFacturaPedido(Double totalFacturaPedido) {
        this.totalFacturaPedido = totalFacturaPedido;
    }

    public Set getLineaPedidos() {
        return this.lineaPedidos;
    }
    
    public void setLineaPedidos(Set lineaPedidos) {
        this.lineaPedidos = lineaPedidos;
    }
   








}