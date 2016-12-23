package com.atrium.hibernate;

import java.util.Date;


/**
 * FormasPagoCliente entity. @author MyEclipse Persistence Tools
 */

public class FormasPagoCliente  implements java.io.Serializable {


    // Fields    

     private Integer claveFormaPagoCliente;
     private Clientes clientes;
     private FormasPago formasPago;
     private String estadoFormaPagoCliente;
     private Date fechaAltaPagoCliente;
     private Date fechaBajaPagoCliente;


    // Constructors

    /** default constructor */
    public FormasPagoCliente() {
    }

	/** minimal constructor */
    public FormasPagoCliente(Integer claveFormaPagoCliente) {
        this.claveFormaPagoCliente = claveFormaPagoCliente;
    }
    
    /** full constructor */
    public FormasPagoCliente(Integer claveFormaPagoCliente, Clientes clientes, FormasPago formasPago, String estadoFormaPagoCliente, Date fechaAltaPagoCliente, Date fechaBajaPagoCliente) {
        this.claveFormaPagoCliente = claveFormaPagoCliente;
        this.clientes = clientes;
        this.formasPago = formasPago;
        this.estadoFormaPagoCliente = estadoFormaPagoCliente;
        this.fechaAltaPagoCliente = fechaAltaPagoCliente;
        this.fechaBajaPagoCliente = fechaBajaPagoCliente;
    }

   
    // Property accessors

    public Integer getClaveFormaPagoCliente() {
        return this.claveFormaPagoCliente;
    }
    
    public void setClaveFormaPagoCliente(Integer claveFormaPagoCliente) {
        this.claveFormaPagoCliente = claveFormaPagoCliente;
    }

    public Clientes getClientes() {
        return this.clientes;
    }
    
    public void setClientes(Clientes clientes) {
        this.clientes = clientes;
    }

    public FormasPago getFormasPago() {
        return this.formasPago;
    }
    
    public void setFormasPago(FormasPago formasPago) {
        this.formasPago = formasPago;
    }

    public String getEstadoFormaPagoCliente() {
        return this.estadoFormaPagoCliente;
    }
    
    public void setEstadoFormaPagoCliente(String estadoFormaPagoCliente) {
        this.estadoFormaPagoCliente = estadoFormaPagoCliente;
    }

    public Date getFechaAltaPagoCliente() {
        return this.fechaAltaPagoCliente;
    }
    
    public void setFechaAltaPagoCliente(Date fechaAltaPagoCliente) {
        this.fechaAltaPagoCliente = fechaAltaPagoCliente;
    }

    public Date getFechaBajaPagoCliente() {
        return this.fechaBajaPagoCliente;
    }
    
    public void setFechaBajaPagoCliente(Date fechaBajaPagoCliente) {
        this.fechaBajaPagoCliente = fechaBajaPagoCliente;
    }
   








}