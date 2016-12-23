package com.atrium.hibernate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * FormasPago entity. @author MyEclipse Persistence Tools
 */

public class FormasPago  implements java.io.Serializable {


    // Fields    

     private Integer codigoFormaPago;
     private String descripcionFormaPago;
     private String estadoFormaPago;
     private Byte numeroPagos;
     private Byte numeroDiasEntrePagos;
     private Short numeroDiasPrimerPago;
     private Date fechaAltaPago;
     private Date fechaBajaPago;
     private Set formasPagoClientes = new HashSet(0);


    // Constructors

    /** default constructor */
    public FormasPago() {
    }

	/** minimal constructor */
    public FormasPago(Integer codigoFormaPago) {
        this.codigoFormaPago = codigoFormaPago;
    }
    
    /** full constructor */
    public FormasPago(Integer codigoFormaPago, String descripcionFormaPago, String estadoFormaPago, Byte numeroPagos, Byte numeroDiasEntrePagos, Short numeroDiasPrimerPago, Date fechaAltaPago, Date fechaBajaPago, Set formasPagoClientes) {
        this.codigoFormaPago = codigoFormaPago;
        this.descripcionFormaPago = descripcionFormaPago;
        this.estadoFormaPago = estadoFormaPago;
        this.numeroPagos = numeroPagos;
        this.numeroDiasEntrePagos = numeroDiasEntrePagos;
        this.numeroDiasPrimerPago = numeroDiasPrimerPago;
        this.fechaAltaPago = fechaAltaPago;
        this.fechaBajaPago = fechaBajaPago;
        this.formasPagoClientes = formasPagoClientes;
    }

   
    // Property accessors

    public Integer getCodigoFormaPago() {
        return this.codigoFormaPago;
    }
    
    public void setCodigoFormaPago(Integer codigoFormaPago) {
        this.codigoFormaPago = codigoFormaPago;
    }

    public String getDescripcionFormaPago() {
        return this.descripcionFormaPago;
    }
    
    public void setDescripcionFormaPago(String descripcionFormaPago) {
        this.descripcionFormaPago = descripcionFormaPago;
    }

    public String getEstadoFormaPago() {
        return this.estadoFormaPago;
    }
    
    public void setEstadoFormaPago(String estadoFormaPago) {
        this.estadoFormaPago = estadoFormaPago;
    }

    public Byte getNumeroPagos() {
        return this.numeroPagos;
    }
    
    public void setNumeroPagos(Byte numeroPagos) {
        this.numeroPagos = numeroPagos;
    }

    public Byte getNumeroDiasEntrePagos() {
        return this.numeroDiasEntrePagos;
    }
    
    public void setNumeroDiasEntrePagos(Byte numeroDiasEntrePagos) {
        this.numeroDiasEntrePagos = numeroDiasEntrePagos;
    }

    public Short getNumeroDiasPrimerPago() {
        return this.numeroDiasPrimerPago;
    }
    
    public void setNumeroDiasPrimerPago(Short numeroDiasPrimerPago) {
        this.numeroDiasPrimerPago = numeroDiasPrimerPago;
    }

    public Date getFechaAltaPago() {
        return this.fechaAltaPago;
    }
    
    public void setFechaAltaPago(Date fechaAltaPago) {
        this.fechaAltaPago = fechaAltaPago;
    }

    public Date getFechaBajaPago() {
        return this.fechaBajaPago;
    }
    
    public void setFechaBajaPago(Date fechaBajaPago) {
        this.fechaBajaPago = fechaBajaPago;
    }

    public Set getFormasPagoClientes() {
        return this.formasPagoClientes;
    }
    
    public void setFormasPagoClientes(Set formasPagoClientes) {
        this.formasPagoClientes = formasPagoClientes;
    }
   








}