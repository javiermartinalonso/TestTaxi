package com.atrium.hibernate;

import java.util.HashSet;
import java.util.Set;


/**
 * Articulos entity. @author MyEclipse Persistence Tools
 */

public class Articulos  implements java.io.Serializable {


    // Fields    

     private Integer codigoArticulo;
     private String descripcionArticulo;
     private Double precioUnidadArticulo;
     private Integer cantidad;
     private Set lineaPedidos = new HashSet(0);


    // Constructors

    /** default constructor */
    public Articulos() {
    }

	/** minimal constructor */
    public Articulos(Integer codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }
    
    /** full constructor */
    public Articulos(Integer codigoArticulo, String descripcionArticulo, Double precioUnidadArticulo, Integer cantidad, Set lineaPedidos) {
        this.codigoArticulo = codigoArticulo;
        this.descripcionArticulo = descripcionArticulo;
        this.precioUnidadArticulo = precioUnidadArticulo;
        this.cantidad = cantidad;
        this.lineaPedidos = lineaPedidos;
    }

   
    // Property accessors

    public Integer getCodigoArticulo() {
        return this.codigoArticulo;
    }
    
    public void setCodigoArticulo(Integer codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public String getDescripcionArticulo() {
        return this.descripcionArticulo;
    }
    
    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    public Double getPrecioUnidadArticulo() {
        return this.precioUnidadArticulo;
    }
    
    public void setPrecioUnidadArticulo(Double precioUnidadArticulo) {
        this.precioUnidadArticulo = precioUnidadArticulo;
    }

    public Integer getCantidad() {
        return this.cantidad;
    }
    
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Set getLineaPedidos() {
        return this.lineaPedidos;
    }
    
    public void setLineaPedidos(Set lineaPedidos) {
        this.lineaPedidos = lineaPedidos;
    }
   








}